package controllers;

import java.io.IOException;
import java.util.List;

import dao.NoteDAO;
import dao.NoteDAOImpl;

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
		
		NoteDAO noteDAO = new NoteDAOImpl();
		
		List<Note> notes = noteDAO.getTrashedNotes();
		
		req.setAttribute("notes", notes);
		req.setAttribute("isTrash", true);
		req.setAttribute("numberOfNotes", notes.size());
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/trash.jsp");
		rd.forward(req, resp);
	}
	
}
