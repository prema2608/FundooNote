package com.bridgelabz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.model.User;

public interface UserService {
	boolean register(User user, HttpServletRequest request);

	User loginUser(User user, HttpServletRequest request, HttpServletResponse response);

	User updateUser(String token, User user, HttpServletRequest request);

	User deleteUser(String token, HttpServletRequest request);

	User activateUser(String token, HttpServletRequest request);


}
