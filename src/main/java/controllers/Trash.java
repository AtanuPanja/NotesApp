package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import dbcon.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Note;

@WebServlet("/trash")
public class Trash extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Note> deletedNotes = new ArrayList<>();
		try {
			Connection con = DbConnection.getConnection();
			String selectQuery = "SELECT * FROM notes WHERE is_trashed=TRUE ORDER BY time_edited DESC";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();

			// check if the set is not empty with isBeforeFirst
			if (rs.isBeforeFirst()) {
				// successfully fetched the notes
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String content = rs.getString("content");
					
					Note note = new Note();
					note.setId(id);
					note.setTitle(title);
					note.setContent(content);
					
					deletedNotes.add(note);
					
				}
			} 
			else {
				// no notes deleted
			}
			con.close();
			req.setAttribute("notes", deletedNotes);
			req.setAttribute("isTrash", true);
			
			RequestDispatcher rd = req.getRequestDispatcher("/trash.jsp");
			rd.forward(req, resp);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
