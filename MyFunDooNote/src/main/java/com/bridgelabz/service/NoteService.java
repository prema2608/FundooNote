package com.bridgelabz.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.model.Labels;
import com.bridgelabz.model.Note;

public interface NoteService {

	boolean createNote(String token, Note user, HttpServletRequest request);

	 Note updateNote(String token, int id, Note user, HttpServletRequest request) ;

	Note deleteNote(int noteId,String token, HttpServletRequest request);

	List<Note> retrieveNote(String token,HttpServletRequest request);

	
	/////
	//labels
	
	boolean createLabel(String token,Labels label, HttpServletRequest request);
	
	Labels deleteLabel(String token, int id, HttpServletRequest request);

	List<Labels> retriveLabel(String token, HttpServletRequest request);

	boolean mergeLabelToNote(String token, int noteId, int labelId, HttpServletRequest request);

	boolean deleteLabelToNote(String token, int noteId, int labelId, HttpServletRequest request);

	Labels editLabel(String token, int id, Labels label, HttpServletRequest request);
	
	
	

	
	
	
	
}
