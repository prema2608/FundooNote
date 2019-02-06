package com.bridgelabz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.UserNote;
import com.bridgelabz.service.NoteService;

@RestController
public class NoteController {

	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<?> createNote(@RequestBody UserNote user, HttpServletRequest request,@RequestParam int id) {
		
		try {
			if (noteService.createNote(user, request,id))
				return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	

	@RequestMapping(value = "/updatenote", method = RequestMethod.POST)
	public ResponseEntity<?> updateNote(@RequestParam("id") int id, @RequestBody UserNote user,
			HttpServletRequest request) {

		UserNote user2 = noteService.updateNote(id, user, request);
		if (user2 != null) {
			return new ResponseEntity<UserNote>(user2, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Id incorrect. Please enter valid Id present in database",
					HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value = "/deletenote", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteNote(@RequestParam("id") int id, HttpServletRequest request) {

		UserNote user = noteService.deleteNote(id, request);
		if (user != null) {
			return new ResponseEntity<UserNote>(user, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Id incorrect. Please enter valid Id  present in database",
					HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@RequestMapping(value = "/retrivenote", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveNote(@RequestParam("id") int id,HttpServletRequest request) {
        List<UserNote> listOfNote = noteService.retrieveNote(id,request);
        if (!listOfNote.isEmpty()) {
            return new ResponseEntity<List<UserNote>>(listOfNote, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<String>("Id incorrect. Please enter valid Id  present in database",
                    HttpStatus.NOT_FOUND);
        }
    }

}
