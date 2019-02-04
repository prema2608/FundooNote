package com.bridgelabz.service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Transactional
	public boolean register(User user, HttpServletRequest request) {
		int id = userDao.register(user);
		if (id > 0) {
			String token = tokenGenerator.generateToken(String.valueOf(id));
			System.out.println(token);
			emailUtil.sendEmail("", "hai", "bye");
			return true;
		}
		return false;
	}

	public User loginUser(String emailId, String password, HttpServletRequest request) {
		return userDao.loginUser(emailId, password);
	}

	public User updateUser(int id, User user, HttpServletRequest request) {
		User user2 = userDao.getUserById(id);
		if (user2 != null) {
			user2.setEmailId(user.getEmailId());
			user2.setMobileNumber(user.getMobileNumber());
			user2.setName(user.getName());
			user2.setPassword(user.getPassword());
			userDao.updateUser(id, user2);
		}
		return user2;
	}

	public User deleteUser(int id, HttpServletRequest request) {
		User user2 = userDao.getUserById(id);
		if (user2 != null) {
			userDao.deleteUser(id);
		}
		return user2;
	}

}
