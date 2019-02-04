package com.bridgelabz.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.dao.NoteDao;
import com.bridgelabz.model.UserNote;
import com.bridgelabz.utility.TokenGenerator;

@Service
public class NoteServiceImpl implements NoteService {
	@Autowired
	private NoteDao noteDao;
	@Autowired
	private TokenGenerator tokenGenerator;

	@Transactional
	public boolean createNote(UserNote user, HttpServletRequest request) {
		int id = noteDao.createNote(user);
		if (id > 0) {
			String token = tokenGenerator.generateToken(String.valueOf(id));
			System.out.println(token);
			return true;
		}
		return false;
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
			noteDao.updateNote(id, user2);
		}
		return user2;
	}

	@Override
	public UserNote deleteNote(int id, HttpServletRequest request) {
		UserNote user2 = noteDao.getNoteById(id);
		if (user2 != null) {
			noteDao.deleteNote(id);
		}
		return user2;
	}

	@Transactional
	public List<UserNote> retrieveNote(HttpServletRequest request) {
		List<UserNote> listOfNote = noteDao.retriveNote();
		if (!listOfNote.isEmpty()) {
			return listOfNote;
		}
		return null;

	}

}