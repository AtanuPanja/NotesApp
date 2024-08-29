package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import dao.NoteDAO;
import dao.NoteDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Note;

@WebServlet("/remove-from-favorites/*")
public class RemoveFromFavorites extends HttpServlet {
	
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
			else if (note.isTrashed()) {
				// note is in trash - cannot be removed from favorites
				// show error
				out.println("<h3 style='color:red'>The requested note is in trash. Restore it first to remove from favorites.</h3>");
			}
			else if (!note.isFavorite()) {
				// note is not added to favorites
				// show error
				out.println("<h3 style='color:red'>The requested note is already not a favorite.</h3>");
			}
			else {
				
				// remove from favorites - update operation
				note.setFavorite(false);
				int result = noteDAO.update(note);
				
				if (result > 0) {
					// successfully removed from favorites
					resp.sendRedirect(req.getContextPath() + "/home");
				}
				else {
					// failed to remove from favorites
					out.println("<h3 style='color:red'>Couldn't remove from favorites</h3>");
					RequestDispatcher rd = req.getRequestDispatcher("/home");
					rd.include(req, resp);
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
