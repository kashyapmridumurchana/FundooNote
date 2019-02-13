package com.bridgelabz.spring.dao;

import java.util.List;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

public interface NoteDao {
	int createNote(Note note);

	List<Note> retrieveNote(int userId);

	Note getNoteById(int noteId);

	void updateNote(Note note);

	void deleteNote(int noteId);

	int createLabel(Label label);

	List<Label> retrieveLabel(int userId);

	Label getLabelById(int labelId);

	void updateLabel(Label label);

	void deleteLabel(int labelId);

}
