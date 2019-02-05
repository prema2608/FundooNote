package com.bridgelabz.dao;

import com.bridgelabz.model.User;

public interface UserDao {

	public int register(User user);

	public User loginUser(String emailId);

	public User getUserById(int id);

	public void updateUser(int id, User user);

	public void deleteUser(int id);

}

