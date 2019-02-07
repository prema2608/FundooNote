package com.bridgelabz.dao;

import java.util.List;

import com.bridgelabz.model.Labels;
import com.bridgelabz.model.UserNote;

public interface NoteDao
{
	public int createNote(UserNote note);
	public UserNote getNoteById(int id);
	public void updateNote(int id, UserNote user);
	public void deleteNote(int id);
	public List<UserNote> retriveNote(int id);
	
	//labels
	public int createLabels(Labels label);
	public Labels getLabelById(int id);
	public void editLabel(int id, Labels label);
	public void deleteLabel(int id);
	public List<Labels> retriveLabel(int id);
}
