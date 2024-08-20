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

@WebServlet("/move-to-trash/*")
public class MoveToTrash extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		int id = Integer.parseInt(req.getPathInfo().substring(1));
		
		try {
			Connection con = DbConnection.getConnection();
			String moveToTrashQuery = "UPDATE notes SET is_trashed=TRUE WHERE id=?";
			PreparedStatement ps = con.prepareStatement(moveToTrashQuery);
			ps.setInt(1, id);
			
			int count = ps.executeUpdate();
			
			if (count > 0) {
				resp.sendRedirect(req.getContextPath()+"/trash");
			}
			else {
				out.print("Couldn't move to trash");
				RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
				rd.include(req, resp);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
