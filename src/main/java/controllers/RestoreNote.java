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

@WebServlet("/restore-note/*")
public class RestoreNote extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		int id = Integer.parseInt(req.getPathInfo().substring(1));
		
		try {
			Connection con = DbConnection.getConnection();
			String restoreQuery = "UPDATE notes SET is_trashed=FALSE WHERE id=?";
			PreparedStatement ps = con.prepareStatement(restoreQuery);
			ps.setInt(1, id);
			
			int count = ps.executeUpdate();
			
			if (count > 0) {
				resp.sendRedirect(req.getContextPath()+"/home");
			}
			else {
				out.print("Couldn't restore the note");
				RequestDispatcher rd = req.getRequestDispatcher("/home");
				rd.include(req, resp);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
