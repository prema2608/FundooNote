package com.bridgelabz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.Labels;
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


	public void updateNote(UserNote user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}

	@Override
	public UserNote getNoteById(int id) {

		Session session = sessionFactory.openSession();
		String hqlQuery = "from UserNote note where note.noteId = :noteId";
		Query query = session.createQuery(hqlQuery);
		query.setInteger("noteId", id);
		UserNote note = (UserNote) query.uniqueResult();
		session.close();
		return note;
	}

	@Override
	public void deleteNote(int noteId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("DELETE from UserNote u where u.noteId= :noteId");
		query.setInteger("noteId", noteId);
		query.executeUpdate();
		tx.commit();
		session.close();
	}
	public List<UserNote> retriveNote(int id) {
		Session session = sessionFactory.openSession();
		Query query=session.createQuery("from UserNote where userId= :userId");
		query.setInteger("userId", id);
		@SuppressWarnings("unchecked")
		List<UserNote> listOfNote = query.list();
		return listOfNote;
	}


	//////////////////////////
	//labels
	
	public int createLabels(Labels label) 
	{
		int userId = 0;
		Session session = sessionFactory.getCurrentSession();
		userId = (Integer) session.save(label);
		return userId;
	}


	@Override
	public Labels getLabelById(int id) {
		Session session = sessionFactory.openSession();
		///Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Labels label where label.labelId= :labelId");
		query.setInteger("labelId", id);
		Labels label =  (Labels) query.uniqueResult();
		session.close();
		return label;
	}


	@Override
	public void editLabel(int id, Labels label) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(label);
		tx.commit();
		session.close();
	}		


	@Override
	public void deleteLabel(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("DELETE from Labels u where u.id= :id");
		query.setInteger("id", id);
		query.executeUpdate();
		tx.commit();
		session.close();
	}


	@Override
	public List<Labels> retriveLabel(int id) {
		Session session = sessionFactory.openSession();
		Query query=session.createQuery("from Labels where userId= :userId");
		query.setInteger("userId", id);
		@SuppressWarnings("unchecked")
		List<Labels> listOfNote = query.list();
		return listOfNote;
	}	
}












