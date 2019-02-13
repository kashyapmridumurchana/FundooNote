package com.bridgelabz.spring.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;
import com.bridgelabz.spring.service.NoteService;

@RestController
public class NoteController {
	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<String> createNote(@RequestHeader("token") String token, @RequestBody Note note,
			HttpServletRequest request) {
		try {
			if (noteService.createNote(token, note, request))
				return new ResponseEntity<String>("Successfully Registered", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Cannot create a note", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Cannot create a note", HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/retrievenote", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveNote(@RequestHeader("token") String token, HttpServletRequest request) {
		List<Note> notes = noteService.retrieveNote(token, request);
		if (!notes.isEmpty()) {
			return new ResponseEntity<List<Note>>(notes, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Please enter the note id or verify your login",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/updatenote", method = RequestMethod.PUT)
	public ResponseEntity<?> updateNote(@RequestHeader("token") String token, @RequestParam("noteId") int noteId,
			@RequestBody Note note, HttpServletRequest request) {

		Note newNote = noteService.updateNote(token, noteId, note, request);
		if (newNote != null) {
			return new ResponseEntity<Note>(newNote, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Please enter the note id or verify your login",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/deletenote", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteNote(@RequestHeader("token") String token, @RequestParam("noteId") int noteId,
			HttpServletRequest request) {
		Note note = noteService.deleteNote(token, noteId, request);
		if (note != null) {
			return new ResponseEntity<Note>(note, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Please enter the note id or verify your login",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/createlabel", method = RequestMethod.POST)
	public ResponseEntity<String> createLabel(@RequestHeader("token") String token, @RequestBody Label label,
			HttpServletRequest request) {
		try {
			if (noteService.createLabel(token, label, request))
				return new ResponseEntity<String>("Successfully Registered", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("User id given is not present or not yet been activated",
					HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("User id given is not present or not yet been activated",
				HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/retrievelabel", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveLabel(@RequestHeader("token") String token, HttpServletRequest request) {
		List<Label> labels = noteService.retrieveLabel(token, request);
		if (!labels.isEmpty()) {
			return new ResponseEntity<List<Label>>(labels, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("User id given is not present or not yet been activated",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/updatelabel", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestHeader("token") String token, @RequestParam("labelId") int labelId,
			@RequestBody Label label, HttpServletRequest request) {
		Label newLabel = noteService.updateLabel(token, labelId, label, request);
		if (newLabel != null) {
			return new ResponseEntity<Label>(newLabel, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("User id given is not present or not yet been activated",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/deletelabel", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteLabel(@RequestHeader("token") String token, @RequestParam("labelId") int labelId,
			HttpServletRequest request) {

		Label label = noteService.deleteLabel(token, labelId, request);
		if (label != null) {
			return new ResponseEntity<Label>(label, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("User id given is not present or not yet been activated",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/addnotelabel", method = RequestMethod.PUT)
	public ResponseEntity<?> addNoteLabel(@RequestHeader("token") String token, @RequestParam("noteId") int noteId,
			@RequestParam("labelId") int labelId, HttpServletRequest request) {
		if (noteService.addNoteLabel(token, noteId, labelId, request)) {
			return new ResponseEntity<String>("Successfully mapped", HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("User id given is not present or not yet been activated",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/removenotelabel", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeNoteLabel(@RequestHeader("token") String token, @RequestParam("noteId") int noteId,
			@RequestParam("labelId") int labelId, HttpServletRequest request) {
		if (noteService.removeNoteLabel(token, noteId, labelId, request)) {
			return new ResponseEntity<String>("Successfully mapped", HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("User id given is not present or not yet been activated",
					HttpStatus.NOT_FOUND);
		}
	}

}