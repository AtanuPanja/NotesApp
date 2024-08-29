package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import dao.NoteDAO;
import dao.NoteDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Note;

@WebServlet("/move-to-trash/*")
public class MoveToTrash extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		String pathParam = req.getPathInfo().substring(1);
		int id = 0;
		try {
			id = Integer.parseInt(pathParam);
			
			NoteDAO noteDAO = new NoteDAOImpl();
			
			Note note = noteDAO.findById(id);
			
			if (note == null) {
				// couldn't find the note with the respective id
				// show error
				out.println("<h3 style='color:red'>Note with id "+id+" doesn't exist.</h3>");
			}
			else if (note.isTrashed()) {
				// note is already in trash
				// show error
				out.println("<h3 style='color:red'> The note with id "+id+" is already in trash.</h3>");
			}
			else {
				// move to trash - update operation
				// first update the object using setter method
				// then call the update method
				note.setTrashed(true);
				int result = noteDAO.update(note);
				
				if (result > 0) {
					// successfully moved to trash
					resp.sendRedirect(req.getContextPath() + "/trash");
				}
				else {
					// failed to move to trash
					out.println("<h3 style='color:red'>Couldn't move to trash</h3>");
				}
			}
			
		}
		catch (NumberFormatException ne) {
			ne.printStackTrace();
			out.println("<h3 style='color:red'>Invalid path parameter. It must be an integer.</h3>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
