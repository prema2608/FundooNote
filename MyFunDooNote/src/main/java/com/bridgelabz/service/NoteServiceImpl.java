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
import com.bridgelabz.model.UserNote;
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
	public boolean createNote(String token, UserNote usernote, HttpServletRequest request) {
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


	@Override
	public UserNote updateNote(int id, UserNote user, HttpServletRequest request) {
		UserNote user2 = noteDao.getNoteById(id);
		if (user2 != null) {
			user2.setTitle(user.getTitle());
			user2.setDescription(user.getDescription());
			user2.setArchive(user.isArchive());
			user2.setPinned(user.isPinned());
			user2.setInTrash(user.isInTrash());
			noteDao.updateNote(user2);
		}
		return user2;
	}

	@Transactional
	public UserNote deleteNote(int noteId,String token, HttpServletRequest request) {
		
		int id=tokenGenerator.VerifyToken(token);
		UserNote user2 = noteDao.getNoteById(id);
		if (user2 != null) {
			noteDao.deleteNote(noteId);
		}
		return user2;
	}

	@Transactional

	public List<UserNote> retrieveNote(String token, HttpServletRequest request) {
		int id=tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(id);
		if (user != null) {
			List<UserNote> listOfNote = noteDao.retriveNote(id);
			if (!listOfNote.isEmpty()) {
				return listOfNote;
			}
		}
		return null;

	}

	
		


	@Transactional
	public boolean createLabel(String token,Labels label, HttpServletRequest request) {
		int id=tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(id);
		if (user != null) {
			label.setUserId(user);
			int labelid = noteDao.createLabels(label);
			if (labelid > 0) {
				return true;
			}

		}
		return false;

	}

	@Transactional
	public Labels editLabel(int id, Labels label, HttpServletRequest request) {
		Labels label1 = noteDao.getLabelById(id);
		if (label1 != null) {
			label1.setLabelName(label.getLabelName());
			noteDao.editLabel(id, label1);
		}
		return null;
	}

	@Transactional
	@Override
	public Labels deleteLabel(int id, HttpServletRequest request) {
		
		Labels label = noteDao.getLabelById(id);
		if (label != null) {
			noteDao.deleteLabel(id);
		}
		return label;

	}
	
	@Transactional
	public List<Labels> retriveLabel(String token, HttpServletRequest request) {
		int id=tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(id);
		if (user != null) {
			List<Labels> listOfNote = noteDao.retriveLabel(id);
			if (!listOfNote.isEmpty()) {
				return listOfNote;
			}
		}
		return null;
	}

	@Transactional
	public boolean mergeLabelToNote(String token, int noteId, int labelId, HttpServletRequest request) {
		int id = tokenGenerator.VerifyToken(token);
		User user = userDao.getUserById(id);
		if (user != null) {
			UserNote existingNote = noteDao.getNoteById(noteId);
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
	            UserNote note = noteDao.getNoteById(noteId);
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
