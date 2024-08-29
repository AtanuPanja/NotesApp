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

@WebServlet("/add-note")
public class AddNote extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/addNote.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		NoteDAO noteDAO = new NoteDAOImpl();
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		// Get data from the submitted form
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		boolean isValidTitle = false; // flag to check if title is valid for insertion
		
		if (content.isBlank()) {
			
			out.println("<h3 style='color:red'>Content must not be empty or only spaces</h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/addNote.jsp");
			rd.include(req, resp);
		} 
		else {
			// content isn't blank, proceed to insert with title validations
			
			// if title doesn't already exist, it is valid for the new
			// entry
			// else if it is empty, it is valid
			// else invalid
			if (!title.isEmpty()) {
				// check if title exists in the database
				Note note = noteDAO.findByTitle(title);
				isValidTitle = (note == null);
			}
			else {
				isValidTitle = true;
			}
			
			// if it is valid title, try to insert
			if (isValidTitle) {
				int result = noteDAO.insert(title, content);					
				if (result > 0) {
					// successfully inserted data
					resp.sendRedirect(req.getContextPath() + "/");
				}
				else {
					// failed to insert data
					out.println("<h3 style='color:red'>Note was not created</h3>");
					RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/addNote.jsp");
					rd.include(req, resp);
				}
			}
			else {
				out.println("<h3 style='color:red'> Cannot use title \""+title+"\". It already exists.</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("//WEB-INF/views/addNote.jsp");
				rd.include(req, resp);
			}
				
				
		}
		
		
	}
}
