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
import jakarta.servlet.http.HttpSession;
import model.Note;

@WebServlet("/empty-trash")
public class DeleteAllFromTrash extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		NoteDAO noteDAO = new NoteDAOImpl();
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		
		// first check if there are notes in trash
		List<Note> trashedNotes = noteDAO.getTrashedNotes();
		
		if (trashedNotes.size() == 0) {
			out.println("<h3 style='color:red'>There are no trashed notes.</h3");
		}
		else {
			int result = noteDAO.deleteAllFromTrash();
			
			if (result > 0) {
				// successfully deleted all trash notes permanently
				
				session.setAttribute("notifyMessage", "Bin emptied");
				// resp.sendRedirect(req.getContextPath() + "/trash");
			}
			else {
				// failed to delete the trashed notes
				session.setAttribute("notifyMessage", "Couldn't delete the trashed notes");
				
			}
		}
		
	}
	
}
