package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dbcon.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete-note/*")
public class DeleteNote extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		int id = Integer.parseInt(req.getPathInfo().substring(1));
		// out.println(id);
		
		try {
			Connection con = DbConnection.getConnection();
			String deleteQuery = "DELETE FROM notes WHERE id=?";
			PreparedStatement ps = con.prepareStatement(deleteQuery);
			ps.setInt(1,id);
			
			int count = ps.executeUpdate();
			
			if (count > 0) {
				// successfully deleted the note
				// out.println("<h3 style='color:green'>Deleted the note successfully</h3>");
				resp.sendRedirect(req.getContextPath() + "/");
			}
			else {
				// failed to delete the note
				out.println("<h3 style='color:red'>Couldn't delete the note</h3");
			}
		}
		catch(Exception e) {
			// some error occurred
			e.printStackTrace();
			// error occurred
			out.println("<h3 style='color:red'>Error -> "+e.toString()+"</h3>");
		}
		
		// RequestDispatcher rd = req.getRequestDispatcher("/");
		// rd.include(req, resp);
		
	}
}
