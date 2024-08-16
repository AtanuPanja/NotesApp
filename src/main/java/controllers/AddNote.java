package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbcon.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add-note")
public class AddNote extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("/addNote.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		// Get data from the submitted form
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		if (content.isBlank()) {
			// show client error
			out.println("<h3 style='color:red'>Content must not be empty</h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/addNote.jsp");
			rd.include(req, resp);
		} else {
			// no client error
			// server error might occur
			try {
				// Establish database connection
				Connection con = DbConnection.getConnection();
				PreparedStatement ps;
				String insertQuery;
				
				// Check if the title is empty
				// we might have the title empty, but empty string cannot
				// be used for multiple rows, so instead we will insert
				// NULL whenever the title is empty. UNIQUE constraint
				// allows multiple NULL values.
				if (title.isEmpty()) {
					
					insertQuery = "INSERT INTO notes(title, content) VALUES(?,?)";
					ps = con.prepareStatement(insertQuery);
					ps.setNull(1, java.sql.Types.VARCHAR);
					ps.setString(2, content);
					
					int count = ps.executeUpdate();
					
					if (count > 0) {
						// successfully inserted data
						out.println("<h3 style='color:green'>Note created successfully</h3><br/><br/>");
						resp.sendRedirect(req.getContextPath() + "/");
					}
					else {
						// failed to insert data
						out.println("<h3 style='color:red'>Note was not created</h3>");
						RequestDispatcher rd = req.getRequestDispatcher("/addNote.jsp");
						rd.include(req, resp);
					}
				}
				else {
					// Check if the title already exists in the notes database
					String findTitleQuery = "SELECT * FROM notes WHERE title=?";
					ps = con.prepareStatement(findTitleQuery);
					ps.setString(1, title);
					
					ResultSet result = ps.executeQuery();
					// isBeforeFirst() method returns true if the cursor 
					// is before the first row, which would mean 
					// it has data
					// we want it to not have data
					if(result.isBeforeFirst()) {
						out.println("<h3 style='color:red'> Cannot use title \""+title+"\". It already exists.</h3>");
						RequestDispatcher rd = req.getRequestDispatcher("/addNote.jsp");
						rd.include(req, resp);
					}
					
					else {
						
						// if title is not empty doesn't exist then insert
						insertQuery = "INSERT INTO notes(title, content) VALUES(?,?)";
						ps = con.prepareStatement(insertQuery);
						ps.setString(1, title);
						ps.setString(2, content);
						
						int count = ps.executeUpdate();
						
						if (count > 0) {
							// successfully inserted data
							out.println("<h3 style='color:green'>Note created successfully</h3><br/><br/>");
							resp.sendRedirect(req.getContextPath() + "/");
						}
						else {
							// failed to insert data
							out.println("<h3 style='color:red'>Note was not created</h3>");
							RequestDispatcher rd = req.getRequestDispatcher("/addNote.jsp");
							rd.include(req, resp);
						}
					}
					
					
					con.close();
				}
				
				
			}
			catch(Exception e) {
				e.printStackTrace();
				// error occurred
				out.println("<h3 style='color:red'>Error -> "+e.toString()+"</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/addNote.jsp");
				rd.include(req, resp);
			}
			
		}
		
		
	}
}
