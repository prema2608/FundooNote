package com.bridgelabz.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.model.Labels;
import com.bridgelabz.model.UserNote;

public interface NoteService {

	boolean createNote(String token, UserNote user, HttpServletRequest request);

	 UserNote updateNote(int id, UserNote user, HttpServletRequest request) ;

	UserNote deleteNote(int id,String token, HttpServletRequest request);

	List<UserNote> retrieveNote(String token,HttpServletRequest request);

	
	
	
	boolean createLabel(String token,Labels label, HttpServletRequest request);

	Labels editLabel(int id, Labels label, HttpServletRequest request);

	Labels deleteLabel(int id, HttpServletRequest request);

	List<Labels> retriveLabel(String token, HttpServletRequest request);

	boolean mergeLabelToNote(String token, int noteId, int labelId, HttpServletRequest request);

	boolean deleteLabelToNote(String token, int noteId, int labelId, HttpServletRequest request);
	
	
	

	
	
	
	
}
