package com.bridgelabz.dao;

import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.model.User;

public interface UserDao {

	public int register(User user);

	public User loginUser(String emailId,HttpServletResponse response);

	public User getUserById(int id);

	public void updateUser(int id, User user);

	public void deleteUser(int id);

}

