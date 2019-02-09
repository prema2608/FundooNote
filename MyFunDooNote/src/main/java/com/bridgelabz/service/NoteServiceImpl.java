package com.bridgelabz.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.dao.NoteDao;
import com.bridgelabz.dao.UserDao;
import com.bridgelabz.model.Labels;
import com.bridgelabz.model.User;
import com.bridgelabz.model.Note;
import com.bridgelabz.utility.TokenGenerator;

@Service
public class NoteServiceImpl implements NoteService {
	@Autowired
	private NoteDao noteDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Transactional

	@Override
	public boolean createNote(String token, Note usernote, HttpServletRequest request) {
		int id=tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(id);
		if (user != null) {
			usernote.setUserId(user);
			int noteid = noteDao.createNote(usernote);
			if (noteid > 0) {
				return true;
			}
		}		return false;
	}
	
	
	@Transactional
	public Note updateNote(String token, int noteId, Note note, HttpServletRequest request) {
		int userId = tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(userId);
		if (user != null) {
			Note currentNote = noteDao.getNoteById(noteId);
			if (currentNote != null) {
				currentNote.setTitle(note.getTitle());
				currentNote.setDescription(note.getDescription());
				currentNote.setArchive(note.isArchive());
				currentNote.setPinned(note.isPinned());
				currentNote.setInTrash(note.isInTrash());
				noteDao.updateNote(currentNote);
			}
			return currentNote;
		}
		return null;
}
	
	@Transactional
	public Note deleteNote(int noteId,String token, HttpServletRequest request) {
		
		int id=tokenGenerator.VerifyToken(token);
		User user =userDao.getUserById(id);
		if (user != null) {
			Note note=noteDao.getNoteById(noteId);
			if(note!=null)
			{
			noteDao.deleteNote(noteId);
			return note;
			}
		}
		return null;
	}

	@Transactional

	public List<Note> retrieveNote(String token, HttpServletRequest request) {
		int id=tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(id);
		if (user != null) {
			List<Note> notes = noteDao.retriveNote(id);
			if (!notes.isEmpty()) {
				return notes;
			}
		}
		return null;

	}

/////////
//label

	@Transactional
	public boolean createLabel(String token,Labels label, HttpServletRequest request) {
		int id=tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(id);
		if (user != null) {
			label.setUserId(user);
			int labelid = noteDao.createLabel(label);
			if (labelid > 0) {
				return true;
			}

		}
		return false;

	}


	@Transactional
	public Labels editLabel(String token, int id, Labels label, HttpServletRequest request) {
		int userId = tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(userId);
		if (user != null) {
			Labels currentLabel = noteDao.getLabelById(id);
			if (currentLabel != null) {
				currentLabel.setLabelName(label.getLabelName());
				noteDao.editLabel(currentLabel);
			}
			return currentLabel;
		}
		return null;
}
	


	@Transactional
	public Labels deleteLabel(String token, int id, HttpServletRequest request) {
		int userId = tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(userId);
		if (user != null) {
			Labels currentLabel = noteDao.getLabelById(id);
			if (currentLabel != null) {
				noteDao.deleteLabel(id);
			}
			return currentLabel;
		}
		return null;
}

	
	
	@Transactional
	public List<Labels> retriveLabel(String token, HttpServletRequest request) {
		int id=tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(id);
		if (user != null) {
			List<Labels> notes = noteDao.retriveLabel(id);
			if (!notes.isEmpty()) {
				return notes;
			}
		}
		return null;
	}

	@Transactional
	public boolean mergeLabelToNote(String token, int noteId, int labelId, HttpServletRequest request) {
		int id = tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(id);
		if (user != null) {
			Note existingNote = noteDao.getNoteById(noteId);
			Labels label = noteDao.getLabelById(labelId);
			List<Labels> labels = existingNote.getLabelList();
			labels.add(label);
			if (!labels.isEmpty()) {
				existingNote.setLabelList(labels);
				noteDao.updateNote( existingNote);
				return true;
			}
		}
		return false;
	}


	@Transactional
	public boolean deleteLabelToNote(String token, int noteId, int labelId, HttpServletRequest request) {
		
	        int userId = tokenGenerator.VerifyToken(token);
	        User user = userDao.getUserById(userId);
	        if (user != null) {
	            Note note = noteDao.getNoteById(noteId);
	            List<Labels> labels = note.getLabelList();
	            if (!labels.isEmpty()) {
	                labels = labels.stream().filter(label -> label.getLabelId() != labelId).collect(Collectors.toList());
	                note.setLabelList(labels);
	                noteDao.updateNote(note);
	                return true;
	            }
	        }
	        return false;
	   
	}


	



	

}
