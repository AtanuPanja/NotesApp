package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dbcon.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/empty-trash")
public class DeleteAllNotes extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		try {
			Connection con = DbConnection.getConnection();
			String deleteQuery = "DELETE FROM notes WHERE is_trashed=TRUE";
			PreparedStatement ps = con.prepareStatement(deleteQuery);
			
			int count = ps.executeUpdate();
			
			if (count > 0) {
				// successfully deleted all trash notes permanently
				resp.sendRedirect(req.getContextPath() + "/trash");
			}
			else {
				// failed to delete the trashed notes
				out.println("<h3 style='color:red'>Couldn't delete the trashed notes</h3");
			}
		}
		catch(Exception e) {
			// some error occurred
			e.printStackTrace();
			// error occurred
			out.println("<h3 style='color:red'>Error -> "+e.toString()+"</h3>");
		}
		
		
	}
	
}
