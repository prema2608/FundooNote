package com.bridgelabz.dao;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.User;
import com.bridgelabz.utility.TokenGenerator;


@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private TokenGenerator tokenGenerator;
	
	public int register(User user) {
		int userId = 0;
		Session session = sessionFactory.getCurrentSession();
		userId = (Integer) session.save(user);
		return userId;
	}

	public User loginUser(String emailId,HttpServletResponse response) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User where emailId= :emailId");
		query.setString("emailId", emailId);
		
		User user = (User) query.uniqueResult();
		tx.commit();
		if (user != null&& user.isActivate_Status()==true) {
			System.out.println("User detail is=" + user.getId() + "," + user.getName() + "," + user.getEmailId() + ","
					+ user.getMobileNumber());
			
			int id1=user.getId();
			String temp =String.valueOf(id1);
		String token =	tokenGenerator.generateToken(temp);
		response.setHeader("userId", token);
			session.close();
			return user;
		} else {
			return null;
		}

	}

	public User getUserById(int id) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User where id= :id");
		query.setInteger("id", id);
		User user = (User) query.uniqueResult();
		tx.commit();
		if (user != null) {
			System.out.println("User detail is=" + user.getId() + "," + user.getName() + "," + user.getEmailId() + ","
					+ user.getMobileNumber());
		
			session.close();
			return user;
		} else {
			return null;
		}
	}

	public void updateUser(int id, User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}

	public void deleteUser(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("DELETE from User u where u.id= :id");
		query.setInteger("id", id);
		query.executeUpdate();
		tx.commit();
		session.close();
	}

}

