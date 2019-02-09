package com.bridgelabz.dao;

import java.util.List;

import com.bridgelabz.model.Labels;
import com.bridgelabz.model.Note;

public interface NoteDao
{
	 int createNote(Note note);
	 Note getNoteById(int id);
	 void updateNote( Note user);
	 void deleteNote(int id);
	 List<Note> retriveNote(int id);
	
	//labels
	 int createLabel(Labels label);
	 Labels getLabelById(int id);
	 void editLabel(Labels label);
	 void deleteLabel(int id);
	 List<Labels> retriveLabel(int id);
}
