package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dbcon.DbConnection;
import model.Note;

import utils.SQLQueries;

public class NoteDAOImpl implements NoteDAO {

	@Override
	public Note findById(int id) {
		String selectQuery = SQLQueries.SELECT_BY_ID;
		
		try (Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(selectQuery)) {
			
			ps.setInt(1, id);
	
			try(ResultSet rs = ps.executeQuery()) {
				
				// it means there is at least one next value in the set
				if (rs.next()) {
					String title = rs.getString("title");
					String content = rs.getString("content");
					LocalDateTime timeEdited = (LocalDateTime)rs.getObject("time_edited");
					
					boolean isFavorite = rs.getBoolean("favorite");
					boolean isTrashed = rs.getBoolean("is_trashed");
					
					// title can be null in the database, and it needs to returned as ""
					if (title == null)
						title = "";
					
					return new Note(id, title, content, timeEdited, isFavorite, isTrashed);
				}
			}
			
		}
		catch(SQLException se) {
			System.out.println("Cause: "+se.getCause());
			System.out.println("SQL state: "+se.getSQLState());
			System.out.println("SQL Error Code: "+se.getErrorCode());
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// if the id is not found return null - though error is also thrown
		return null;
		
	}
	
	@Override
	public Note findByTitle(String title) {
		// it assumed that the title won't be null, otherwise 
		// the search operation for title is invalid
		
		String selectQuery = SQLQueries.SELECT_BY_TITLE;

		try (Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(selectQuery)) {
			
			ps.setString(1, title);
	
			try(ResultSet rs = ps.executeQuery()) {
				
				// it means  there is at least one next value in the set
				if (rs.next()) {
					int id = rs.getInt("id");
					String content = rs.getString("content");
					LocalDateTime timeEdited = (LocalDateTime)rs.getObject("time_edited");
					
					boolean isFavorite = rs.getBoolean("favorite");
					boolean isTrashed = rs.getBoolean("is_trashed");
					
					return new Note(id, title, content, timeEdited, isFavorite, isTrashed);
				}
			}
			
		}
		catch(SQLException se) {
			System.out.println("Cause: "+se.getCause());
			System.out.println("SQL state: "+se.getSQLState());
			System.out.println("SQL Error Code: "+se.getErrorCode());
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// if the title is not found return null - though error is also thrown
		return null;
		
	}

	@Override
	public List<Note> getActiveNotes() {
		
		List<Note> notes = new ArrayList<Note>();
		String selectQuery = SQLQueries.SELECT_ALL_ACTIVE_NOTES;
		
		try (Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(selectQuery)) {
	
			try(ResultSet rs = ps.executeQuery()) {
				
				// it means there is a next element in the set, so access it
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String content = rs.getString("content");
					LocalDateTime timeEdited = (LocalDateTime)rs.getObject("time_edited");
					
					boolean isFavorite = rs.getBoolean("favorite");
					boolean isTrashed = rs.getBoolean("is_trashed");
					
					// title can be null in the database, and it needs to returned as ""
					if (title == null)
						title = "";
					
					Note note = new Note(id, title, content, timeEdited, isFavorite, isTrashed);
					
					notes.add(note);
				}
			}
			
		}
		catch(SQLException se) {
			System.out.println("Cause: "+se.getCause());
			System.out.println("SQL state: "+se.getSQLState());
			System.out.println("SQL Error Code: "+se.getErrorCode());
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// if there are active notes (not deleted), it will return the list
		// else returns an empty set
		return notes;
		
	}
	
	@Override
	public List<Note> getTrashedNotes() {
		List<Note> notes = new ArrayList<Note>();
		String selectQuery = SQLQueries.SELECT_ALL_TRASHED_NOTES;
		
		try (Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(selectQuery)) {
	
			try (ResultSet rs = ps.executeQuery()) {

				// it means there is a next element in the set, so access it
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String content = rs.getString("content");
					LocalDateTime timeEdited = (LocalDateTime)rs.getObject("time_edited");
					
					boolean isFavorite = rs.getBoolean("favorite");
					boolean isTrashed = rs.getBoolean("is_trashed");
					
					// title can be null in the database, and it needs to returned as ""
					if (title == null)
						title = "";
					
					Note note = new Note(id, title, content, timeEdited, isFavorite, isTrashed);
					
					notes.add(note);
				}
			}
			
		}
		catch(SQLException se) {
			System.out.println("Cause: "+se.getCause());
			System.out.println("SQL state: "+se.getSQLState());
			System.out.println("SQL Error Code: "+se.getErrorCode());
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		// if there are deleted notes, it will return the list
		// else returns an empty set
		return notes;
		
	}

	@Override
	public int update(Note note) {
		int result = 0;
		String updateQuery = SQLQueries.UPDATE_NOTE;
		
		try (Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(updateQuery)) {
			
			
			if(note.getTitle() == "") {
				ps.setNull(1, java.sql.Types.VARCHAR);
			}
			else {
				ps.setString(1, note.getTitle());
			}
			
			ps.setString(2, note.getContent());
			ps.setObject(3, note.getTimeEdited());
			ps.setBoolean(4, note.isFavorite());
			ps.setBoolean(5, note.isTrashed());
			ps.setInt(6, note.getId());
	
			result = ps.executeUpdate();
		}
		catch(SQLException se) {
			System.out.println("Cause: "+se.getCause());
			System.out.println("SQL state: "+se.getSQLState());
			System.out.println("SQL Error Code: "+se.getErrorCode());
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	@Override
	public int insert(String title, String content) {
		String insertQuery = SQLQueries.INSERT_NOTE;
		int result = 0;
		
		try (Connection con = DbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(insertQuery)) {
			
			// Check if the title is empty
			// we might have the title empty, but empty string cannot
			// be used for multiple rows, so instead we will insert
			// NULL whenever the title is empty. UNIQUE constraint
			// allows multiple NULL values.
			if(title == "") {
				ps.setNull(1, java.sql.Types.VARCHAR);
			}
			else {
				ps.setString(1, title);
			}
			
			ps.setString(2, content);

			result = ps.executeUpdate();
			
		}
		catch(SQLException se) {
			System.out.println("Cause: "+se.getCause());
			System.out.println("SQL state: "+se.getSQLState());
			System.out.println("SQL Error Code: "+se.getErrorCode());
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	
	@Override
	public int delete(int id) {
		
		String deleteQuery = SQLQueries.DELETE_NOTE;
		int result = 0;
		
		try (Connection con = DbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(deleteQuery)) {
			
			ps.setInt(1, id);

			result = ps.executeUpdate();
			
		}
		catch(SQLException se) {
			System.out.println("Cause: "+se.getCause());
			System.out.println("SQL state: "+se.getSQLState());
			System.out.println("SQL Error Code: "+se.getErrorCode());
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	@Override
	public int deleteAllFromTrash() {
		
		String deleteQuery = SQLQueries.DELETE_ALL_FROM_TRASH;
		int result = 0;
		
		try (Connection con = DbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(deleteQuery)) {
			result = ps.executeUpdate();
		}
		catch(SQLException se) {
			System.out.println("Cause: "+se.getCause());
			System.out.println("SQL state: "+se.getSQLState());
			System.out.println("SQL Error Code: "+se.getErrorCode());
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
