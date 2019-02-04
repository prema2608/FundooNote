package com.bridgelabz.model;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


@SuppressWarnings("serial")
@Entity
@Table(name = "UserNote")
public class UserNote implements Serializable
{

	@Id
	@GeneratedValue
	@Column(name = "noteId")
	private int noteId;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	
	@CreationTimestamp
	@Column(name = "createdDate")
	private Timestamp createdDate;

	@CreationTimestamp
	@Column(name = "updatedDate")
	private Timestamp updatedDate;

	@Column(name = "isArchive")
	private boolean isArchive;

	
	@Column(name = "isPinned")
	private boolean isPinned;


	@Column(name = "inTrash")
	private boolean inTrash;


	public int getNoteId() {
		return noteId;
	}


	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Timestamp getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}


	public Timestamp getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}


	public boolean isArchive() {
		return isArchive;
	}


	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}


	public boolean isPinned() {
		return isPinned;
	}


	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}


	public boolean isInTrash() {
		return inTrash;
	}


	public void setInTrash(boolean inTrash) {
		this.inTrash = inTrash;
	}


	@Override
	public String toString() {
		return "UserNote [noteId=" + noteId + ", title=" + title + ", description=" + description + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", isArchive=" + isArchive + ", isPinned=" + isPinned
				+ ", inTrash=" + inTrash + "]";
	}


	
	
	
}
