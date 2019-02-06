package com.bridgelabz.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.User;
import com.bridgelabz.service.UserService;

@RestController
public class USerController {

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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<?> loginUser(@RequestParam("emailId") String emailId,
			@RequestParam("password") String password, HttpServletRequest request,HttpServletResponse response) {

		User user = userService.loginUser(emailId, password, request,response);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Incorrect emailId or password", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateUser(@RequestParam("id") int id, @RequestBody User user,
			HttpServletRequest request) {

		User user2 = userService.updateUser(id, user, request);
		if (user2 != null) {
			return new ResponseEntity<User>(user2, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@RequestParam("id") int id, HttpServletRequest request) {

		User user = userService.deleteUser(id, request);
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
