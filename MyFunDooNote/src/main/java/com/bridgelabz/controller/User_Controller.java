package com.bridgelabz.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.User;
import com.bridgelabz.service.UserService;

@RestController
public class User_Controller {

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> registerUser(@RequestBody User user, HttpServletRequest request) {
		try {
			if (userService.register(user, request))
				return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) {

		User currentUser = userService.loginUser(user, request, response);
		if (currentUser != null) {
			return new ResponseEntity<User>(currentUser, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Please check the emailId or password", HttpStatus.NOT_FOUND);
		}
	}

                                      
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestHeader("token") String token, @RequestBody User user,
			HttpServletRequest request) {

		User currentUser = userService.updateUser(token, user, request);
		if (currentUser != null) {
			return new ResponseEntity<User>(currentUser, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}


	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@RequestHeader("token") String token, HttpServletRequest request) {

		User user = userService.deleteUser(token, request);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}


	@RequestMapping(value = "/activationstatus/{token:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> activateUser(@PathVariable("token") String token, HttpServletRequest request) {

		User user = userService.activateUser(token, request);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}

}
