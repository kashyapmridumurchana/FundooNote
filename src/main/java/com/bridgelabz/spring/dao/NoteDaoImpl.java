package com.bridgelabz.spring.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

@Repository
public class NoteDaoImpl implements NoteDao {

	@Autowired
	private SessionFactory sessionFactory;

	public int createNote(Note note) {
		int noteId = 0;
		Session session = sessionFactory.getCurrentSession();
		noteId = (Integer) session.save(note);
		return noteId;

	}

	public Note getNoteById(int noteId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Note where noteId= :noteId");
		query.setInteger("noteId", noteId);
		Note note = (Note) query.uniqueResult();
		if (note != null) {
			session.close();
			return note;
		} else {
			return null;
		}
	}

	public void updateNote(Note note) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(note);
		transaction.commit();
		session.close();
	}

	public void deleteNote(int noteId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("DELETE from Note u where u.noteId= :noteId");
		query.setInteger("noteId", noteId);
		query.executeUpdate();
		session.close();
	}

	public List<Note> retrieveNote(int userId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Note where userId= :userId");
		query.setInteger("userId", userId);
		@SuppressWarnings("unchecked")
		List<Note> notes =(List<Note>) query.list();
		return notes;
	}
	
	
	public int createLabel(Label label) {
		int noteId = 0;
		Session session = sessionFactory.getCurrentSession();
		noteId = (Integer) session.save(label);
		return noteId;

	}

	public Label getLabelById(int labelId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Label where labelId= :labelId");
		query.setInteger("labelId", labelId);
		Label label = (Label) query.uniqueResult();
		if (label != null) {
			session.close();
			return label;
		} else {
			return null;
		}
	}

	public void updateLabel(Label label) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(label);
		transaction.commit();
		session.close();
	}

	public void deleteLabel(int labelId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("DELETE from Label u where u.labelId= :labelId");
		query.setInteger("labelId", labelId);
		query.executeUpdate();
		session.close();
	}

	public List<Label> retrieveLabel(int userId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Label where userId= :userId");
		query.setInteger("userId", userId);
		@SuppressWarnings("unchecked")
		List<Label> labels =(List<Label>) query.list();
		return labels;
	}

}



