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

@WebServlet("/delete-note/*")
public class DeleteNote extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		String pathParam = req.getPathInfo().substring(1);
		int id = 0;
		try {
			id = Integer.parseInt(pathParam);
			
			NoteDAO noteDAO = new NoteDAOImpl();
			
			// try to find if there is an actual note with this id
			Note note = noteDAO.findById(id);
			if (note == null) {
				// couldn't find the note with the respective id
				// show error
				out.println("<h3 style='color:red'>Note with id "+id+" doesn't exist.</h3>");
			}
			else if (!note.isTrashed()) {
				// the note is not trashed
				// currently restricting the active notes from being deleted permanently
				// so, only the note which is in trash, can be deleted
				out.println("<h3 style='color:red'>Note with id "+id+" is not in trash. Move to trash instead.</h3>");
			}
			else {
				int result = noteDAO.delete(id);
				
				if (result > 0) {
					// successfully deleted the note permanently
					resp.sendRedirect(req.getContextPath() + "/");
				}
				else {
					// failed to delete the note permanently
					out.println("<h3 style='color:red'>Couldn't delete the note</h3");
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
