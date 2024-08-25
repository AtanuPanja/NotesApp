package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import dbcon.DbConnection;
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

		int id = Integer.parseInt(req.getPathInfo().substring(1));

		try {
			Connection con = DbConnection.getConnection();
			String selectQuery = "SELECT * FROM notes WHERE id=" + id;
			PreparedStatement ps = con.prepareStatement(selectQuery);

			ResultSet rs = ps.executeQuery();
			Note newNote = new Note();
			if (rs.next()) {
				// successfully found the data
				String title = rs.getString("title");
				// System.out.println("Title from database: "+title); // it is returning null
				// System.out.println("Title.isEmpty(): "+title.isEmpty()); // null object, and
				// not
				// "null" string because title.isEmpty() give NullPointerException here

				newNote.setId(id);
				// at the time of setting the value to the session, it converts null to
				// the string "null"
				// to avoid this we convert it back to empty string
				if (title == null) {
					newNote.setTitle("");
				} else {
					newNote.setTitle(title);
				}
				newNote.setContent(rs.getString("content"));

				HttpSession session = req.getSession();
				session.setAttribute("note", newNote);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editNote.jsp");
		rd.include(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		int id = Integer.parseInt(req.getPathInfo().substring(1));

		resp.setContentType("text/html");

		// Get the edited data from the submitted form
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		if (content.isBlank()) {
			// show client error
			out.println("<h3 style='color:red'>Content must not be empty or only spaces</h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editNote.jsp");
			rd.include(req, resp);
		} else {
			// no client error
			// server error might occur
			try {
				// Establish database connection
				Connection con = DbConnection.getConnection();
				PreparedStatement ps;
				String updateQuery;
				
				// Insert the datetime manually using jdbc
				LocalDateTime now = LocalDateTime.now();
				

				// Check if the title is empty
				// we might have the title empty, but empty string cannot
				// be used for multiple rows, so instead we will insert
				// NULL whenever the title is empty. UNIQUE constraint
				// allows multiple NULL values.
				if (title.isEmpty()) {

					updateQuery = "UPDATE notes SET title=?, content=?, time_edited=? WHERE id=?";
					ps = con.prepareStatement(updateQuery);
					ps.setNull(1, java.sql.Types.VARCHAR);
					ps.setString(2, content);
					
					// Converting to Timestamp before inserting
					ps.setTimestamp(3, Timestamp.valueOf(now));
					
					ps.setInt(4, id);

					int count = ps.executeUpdate();

					if (count > 0) {
						// successfully updated data
						out.println("<h3 style='color:green'>Note updated successfully</h3><br/><br/>");
						resp.sendRedirect(req.getContextPath() + "/");
					} 
					else {
						// failed to update data
						out.println("<h3 style='color:red'>Note was not updated</h3>");
						RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editNote.jsp");
						rd.include(req, resp);
					}
				} 
				else {
					// Check if the title already exists in the notes database
					// except the current note (otherwise it gives error)
					String findTitleQuery = "SELECT * FROM notes WHERE title=? AND id!=?";
					ps = con.prepareStatement(findTitleQuery);
					ps.setString(1, title);
					ps.setInt(2, id);

					ResultSet result = ps.executeQuery();
					// isBeforeFirst() method returns true if the cursor
					// is before the first row, which would mean
					// it has data
					// we want it to not have data
					if (result.isBeforeFirst()) {
						out.println(
								"<h3 style='color:red'> Cannot use title \"" + title + "\". It already exists.</h3>");
						RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editNote.jsp");
						rd.include(req, resp);
					}

					else {

						// if title is not empty, and doesn't match any other title in the database, then directly update title
						updateQuery = "UPDATE notes SET title=?, content=?, time_edited=? WHERE id=?";
						ps = con.prepareStatement(updateQuery);
						ps.setString(1, title);
						ps.setString(2, content);
						
						// Converting to Timestamp before inserting
						ps.setTimestamp(3, Timestamp.valueOf(now));
						
						ps.setInt(4, id);

						int count = ps.executeUpdate();

						if (count > 0) {
							// successfully updated data
							out.println("<h3 style='color:green'>Note updated successfully</h3><br/><br/>");
							resp.sendRedirect(req.getContextPath() + "/");
						} 
						else {
							// failed to update data
							out.println("<h3 style='color:red'>Note was not updated</h3>");
							RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editNote.jsp");
							rd.include(req, resp);
						}

						con.close();
					}

				}
			} 
			catch (Exception e) {
				e.printStackTrace();
				// error occurred
				out.println("<h3 style='color:red'>Error -> " + e.toString() + "</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editNote.jsp");
				rd.include(req, resp);
			}

		}

	}
}
