package com.bridgelabz.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.model.UserNote;

public interface NoteService {

	boolean createNote(UserNote user, HttpServletRequest request);

	 UserNote updateNote(int id, UserNote user, HttpServletRequest request) ;

	UserNote deleteNote(int id, HttpServletRequest request);

	List<UserNote> retrieveNote(HttpServletRequest request);
	
	

	
	
	
	
}