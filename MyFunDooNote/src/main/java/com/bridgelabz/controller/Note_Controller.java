package com.bridgelabz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.Labels;
import com.bridgelabz.model.Note;
import com.bridgelabz.service.NoteService;

@RestController
public class Note_Controller {

	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<?> createNote(@RequestHeader("token") String token,@RequestBody Note user, HttpServletRequest request) {

		try {
			if (noteService.createNote(token,user, request))
				return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}


	

	@RequestMapping(value = "/updatenote", method = RequestMethod.PUT)
	public ResponseEntity<?> updateNote(@RequestHeader("token") String token, @RequestParam("noteId") int noteId,
			@RequestBody Note note, HttpServletRequest request) {

		Note currentNote = noteService.updateNote(token, noteId, note, request);
		if (currentNote != null) {
			return new ResponseEntity<Note>(currentNote, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Please enter the value note id or verify your login",
					HttpStatus.NOT_FOUND);
		}
}
	
	

	@RequestMapping(value = "/deletenote", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteNote(@RequestParam("noteId") int noteId,@RequestHeader("token") String token, HttpServletRequest request) {
//try {
		Note user = noteService.deleteNote(noteId,token, request);
		if (user != null) {
			return new ResponseEntity<Note>(user, HttpStatus.FOUND);
		} 
//}catch (Exception e) {
//	e.printStackTrace();
//	return new ResponseEntity<String>("details not   present in database",
//			HttpStatus.NOT_FOUND);
//}
			return new ResponseEntity<String>("Id incorrect. Please enter valid Id  present in database",
					HttpStatus.NOT_FOUND);
		}
	



	@RequestMapping(value = "/retrivenote", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveNote(@RequestHeader("token") String token,HttpServletRequest request) {
		List<Note> listOfNote = noteService.retrieveNote(token,request);
		if (listOfNote!= null) {
			return new ResponseEntity<List<Note>>(listOfNote, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Id incorrect. Please enter valid Id  present in database",
					HttpStatus.NOT_FOUND);
		}
	}



	/////////////////////////////////////////////////////////
	//Labels

	@RequestMapping(value = "/createlabel", method = RequestMethod.POST)
	public ResponseEntity<?> createLabel(@RequestHeader("token") String token,@RequestBody Labels label, HttpServletRequest request) {

		//		try {
		if (noteService.createLabel(token,label, request))
			return new ResponseEntity<Void>(HttpStatus.OK);
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		//		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}


	@RequestMapping(value = "/updatelabel", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestHeader("token") String token, @RequestParam("labelId") int labelId,
			@RequestBody Labels label, HttpServletRequest request) {
		Labels currentLabel = noteService.editLabel(token, labelId, label, request);
		if (currentLabel != null) {
			return new ResponseEntity<Labels>(currentLabel, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("User id given is not present or Note yet been activated",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/deletelabel", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteLabel(@RequestHeader("token") String token, @RequestParam("labelId") int labelId,
			HttpServletRequest request) {

		Labels label = noteService.deleteLabel(token, labelId, request);
		if (label != null) {
			return new ResponseEntity<Labels>(label, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("User id given is not present or Note yet been activated",
					HttpStatus.NOT_FOUND);
		}
}


	@RequestMapping(value = "/retrivelabel", method = RequestMethod.GET)
	public ResponseEntity<?> retriveLabel(@RequestHeader("token") String token,HttpServletRequest request) {
		List<Labels> listOfNote = noteService.retriveLabel(token,request);
		if (!listOfNote.isEmpty()) {
			return new ResponseEntity<List<Labels>>(listOfNote, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Id incorrect. Please enter valid Id  present in database",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/mergelabelnote", method = RequestMethod.POST)
	public ResponseEntity<String> mergeLabelToNote(@RequestHeader("token") String token,@RequestParam("noteId") int noteId,@RequestParam("labelId") int labelId, HttpServletRequest request)
	{
		//try {
		if (noteService.mergeLabelToNote(token,noteId, labelId,request))
			return new ResponseEntity<String>("Mapped Succesfully",HttpStatus.OK);
		//} catch (Exception e) {
		//    e.printStackTrace();
		//    return new ResponseEntity<String>("Note not Found by given  Id",HttpStatus.CONFLICT);
		//    }
		return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/deletelabeltonote", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteLabelToNote(@RequestHeader("token") String token,@RequestParam("noteId") int noteId,@RequestParam("labelId") int labelId, HttpServletRequest request)
	{
		//try {
		if (noteService.deleteLabelToNote(token,noteId, labelId,request))
			return new ResponseEntity<String>("Mapped Succesfully",HttpStatus.OK);
		//} catch (Exception e) {
		//    e.printStackTrace();
		//    return new ResponseEntity<String>("Note not Found by given  Id",HttpStatus.CONFLICT);
		//    }
		return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}



}
