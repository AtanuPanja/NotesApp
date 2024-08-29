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

@WebServlet("/restore-note/*")
public class RestoreNote extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
			else if (!note.isTrashed()) {
				// note is already active - cannot be restored
				// show error
				out.println("<h3 style='color:red'> The note with id "+id+" is active, and not in trash.</h3>");
			}
			else {
				// restore from trash - update operation
				// first update the object using setter method
				// then call the update method
				note.setTrashed(false);
				int result = noteDAO.update(note);
				
				if (result > 0) {
					// successfully restored from trash
					resp.sendRedirect(req.getContextPath() + "/home");
				}
				else {
					// failed to restore from trash
					out.println("<h3 style='color:red'>Couldn't restore from trash</h3>");
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
