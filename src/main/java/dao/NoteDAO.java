package dao;

import java.util.List;

import model.Note;

public interface NoteDAO {
	
	Note findById(int id);
	
	Note findByTitle(String title);
	
	List<Note> getActiveNotes();
	
	List<Note> getTrashedNotes();
	
	int update(Note note);
	
	int insert(String title, String content);
	
	int delete(int id);
	
	int deleteAllFromTrash();
	
}
