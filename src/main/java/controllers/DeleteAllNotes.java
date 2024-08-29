package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.NoteDAO;
import dao.NoteDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Note;

@WebServlet("/empty-trash")
public class DeleteAllNotes extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		NoteDAO noteDAO = new NoteDAOImpl();
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		// first check if there are notes in trash
		List<Note> trashedNotes = noteDAO.getTrashedNotes();
		
		if (trashedNotes.size() == 0) {
			out.println("<h3 style='color:red'>There are no trashed notes.</h3");
		}
		else {
			int result = noteDAO.deleteAllFromTrash();
			
			if (result > 0) {
				// successfully deleted all trash notes permanently
				resp.sendRedirect(req.getContextPath() + "/trash");
			}
			else {
				// failed to delete the trashed notes
				out.println("<h3 style='color:red'>Couldn't delete the trashed notes</h3");
			}
		}
		
	}
	
}
