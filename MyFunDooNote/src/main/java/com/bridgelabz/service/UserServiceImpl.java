package com.bridgelabz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.dao.UserDao;
import com.bridgelabz.model.User;
import com.bridgelabz.utility.EmailUtil;
import com.bridgelabz.utility.TokenGenerator;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private EmailUtil emailUtil;


	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Transactional
	public boolean register(User user, HttpServletRequest request) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		int id = userDao.register(user);
		if (id > 0) {
			String token = tokenGenerator.generateToken(String.valueOf(id));
			StringBuffer requestURL = request.getRequestURL();
			String activationURL = requestURL.substring(0, requestURL.lastIndexOf("/"));
			activationURL = activationURL + "/activationstatus/" + token;
			emailUtil.sendEmail("", "please click the link below to verify your Email", activationURL);
			return true;
		}
		return false;
	}



	@Transactional
	public User loginUser(User user, HttpServletRequest request, HttpServletResponse response) {
		User userVerification = userDao.loginUser(user.getEmailId());
		if (bcryptEncoder.matches(user.getPassword(), userVerification.getPassword()) && userVerification.isActivate_Status()) {
			String token = tokenGenerator.generateToken(String.valueOf(userVerification.getId()));
			response.setHeader("token", token);
			return userVerification;
		}
		return null;
	}


	@Transactional
	public User updateUser(String token, User user, HttpServletRequest request) {
		int userId = tokenGenerator.VerifyToken(token);
		User currentUser = userDao.getUserById(userId);
		if (currentUser != null) {
			currentUser.setMobileNumber(user.getMobileNumber());
			currentUser.setName(user.getName());
			currentUser.setEmailId(user.getEmailId());
			currentUser.setPassword(user.getPassword());
			currentUser.setPassword(bcryptEncoder.encode(currentUser.getPassword()));
			userDao.updateUser(currentUser);
		}
		return currentUser;
	}


	@Transactional
	public User deleteUser(String token, HttpServletRequest request) {
		int userId = tokenGenerator.VerifyToken(token);
		User currentUser = userDao.getUserById(userId);
		if (currentUser != null)
		{
			userDao.deleteUser(userId);
		}
		return currentUser;
	}	

	
	

	@Transactional
	public User activateUser(String token, HttpServletRequest request) {
		int id=tokenGenerator.VerifyToken(token);
		User user=userDao.getUserById(id);
		if(user!=null)
		{
			user.setActivate_Status(true);
			userDao.updateUser(user);
		}
		return user;
	}



}
