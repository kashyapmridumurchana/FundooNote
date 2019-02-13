package com.bridgelabz.spring.service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

public interface NoteService {
	boolean createNote(String token, Note note, HttpServletRequest request);

	List<Note> retrieveNote(String token, HttpServletRequest request);

	Note updateNote(String token, int noteId, Note note, HttpServletRequest request);

	Note deleteNote(String token, int noteId, HttpServletRequest request);

	boolean createLabel(String token, Label label, HttpServletRequest request);

	List<Label> retrieveLabel(String token, HttpServletRequest request);

	Label updateLabel(String token, int labelId, Label label, HttpServletRequest request);

	Label deleteLabel(String token, int labelId, HttpServletRequest request);

	boolean addNoteLabel(String token, int noteId, int labelId, HttpServletRequest request);

	boolean removeNoteLabel(String token, int noteId, int labelId, HttpServletRequest request);
}
