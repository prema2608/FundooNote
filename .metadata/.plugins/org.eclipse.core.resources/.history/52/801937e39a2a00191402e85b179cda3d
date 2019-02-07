package com.bridgelabz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.User;
import com.bridgelabz.model.UserNote;


@Repository
public class NoteDaoImpl implements NoteDao {


	@Autowired
	private SessionFactory sessionFactory;

	public int createNote(UserNote user) {
		int userId = 0;
		Session session = sessionFactory.getCurrentSession();
		userId = (Integer) session.save(user);
		return userId;
	}


	public void updateNote(int id, UserNote user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}



	@Override
	public UserNote getNoteById(int id) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from UserNote where id= :id");
		query.setInteger("id", id);
		UserNote user = (UserNote) query.uniqueResult();
		tx.commit();
		if (user != null) {
			//			System.out.println("User detail is=" + user.getId() + "," + user.getName() + "," + user.getEmailId() + ","
			//					+ user.getMobileNumber());
			session.close();
			return user;
		} else {
			return null;
		}
	}





	@Override
	public void deleteNote(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("DELETE from UserNote u where u.id= :id");
		query.setInteger("id", id);
		query.executeUpdate();
		tx.commit();
		session.close();
	}
	public List<UserNote> retriveNote(int id) {
		Session session = sessionFactory.openSession();
		Query query=session.createQuery("from UserNote where userId= :userId");
		query.setInteger("userId", id);
		List<UserNote> listOfNote = query.list();
		return listOfNote;
	}








}




