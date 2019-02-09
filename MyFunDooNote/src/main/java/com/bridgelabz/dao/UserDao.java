package com.bridgelabz.dao;


import com.bridgelabz.model.User;

public interface UserDao {

	 int register(User user);

	 User loginUser(String emailId);

	 User getUserById(int id);

	 void updateUser(User user);

	 void deleteUser(int id);

}

