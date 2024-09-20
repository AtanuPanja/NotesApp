package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import dao.NoteDAO;
import dao.NoteDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Note;

@WebServlet("/edit-note/*")
public class EditNote extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		
		String pathParam = req.getPathInfo().substring(1);
		
		int id = 0;
		try {
			id = Integer.parseInt(pathParam);
			
			NoteDAO noteDAO = new NoteDAOImpl();

			Note note = noteDAO.findById(id);
			if (note == null) {
				// couldn't find the note with the respective id
				// show error
				session.setAttribute("notifyMessage", "Note with id "+id+" doesn't exist.");
				resp.sendRedirect(req.getContextPath() + "/");
			}
			else if (note.isTrashed()) {
				// note is in trash, so it should not be edited
				// show error
				session.setAttribute("notifyMessage", "The requested note is in trash. Restore it to view/edit.");
				resp.sendRedirect(req.getContextPath() + "/");
			}
			else {
				// note exists in the active notes section
				// set the note to session and pass it to editNote.jsp
				
				session.setAttribute("note", note);
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editNote.jsp");
				rd.include(req, resp);
			}
			
		}
		catch (NumberFormatException ne) {
			ne.printStackTrace();
			
			session.setAttribute("notifyMessage", "Invalid path parameter. It must be an integer.");
			resp.sendRedirect(req.getContextPath() + "/");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		
		String pathParam = req.getPathInfo().substring(1);
		
		int id = 0;
		
		try {
			id = Integer.parseInt(pathParam);
			
			NoteDAO noteDAO = new NoteDAOImpl();
			
			// Get the edited data from the submitted form
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			boolean isValidTitle = false; // flag to check if title is valid
			
			if (content.isBlank()) {
				session.setAttribute("notifyMessage", "Content must not be empty or only spaces");
				
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editNote.jsp");
				rd.include(req, resp);
			} 
			else {
				// Update the current time using code
				LocalDateTime currentTime = LocalDateTime.now();
					
				if (!title.isEmpty()) {
					// check if title exists in database
					Note note = noteDAO.findByTitle(title);
					
					// now the found note must either be null, 
					// or the note's id must be the same as the current one,
					// for a valid title
					
					isValidTitle = (note == null || note.getId() == id);
				}
				else {
					isValidTitle = true;
				}
				
				// if title is valid then try to update
				if (isValidTitle) {
					// find the current note, as some of its data remains same
					Note existingNote = noteDAO.findById(id);
					
					// update the existingNote fields with setter methods
					existingNote.setTitle(title);
					existingNote.setContent(content);
					existingNote.setTimeEdited(currentTime);
					
					// update the existing note with new data
					int result = noteDAO.update(existingNote);
					if (result > 0) {
						// successfully updated data
						
						session.setAttribute("notifyMessage", "Note updated");
						resp.sendRedirect(req.getContextPath() + "/");
					}
					else {
						// failed to update data
						session.setAttribute("notifyMessage", "Failed to update note");
						
						RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editNote.jsp");
						rd.include(req, resp);
					}
					
				}
				else {
					session.setAttribute("notifyMessage", "Cannot use title \""+title+"\". It already exists.");
					
					RequestDispatcher rd = req.getRequestDispatcher("//WEB-INF/views/editNote.jsp");
					rd.include(req, resp);
				}
						
			}
		}
		catch (NumberFormatException ne) {
			ne.printStackTrace();
			
			session.setAttribute("notifyMessage", "Invalid path parameter. It must be an integer.");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
