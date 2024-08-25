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

@WebServlet("/remove-from-favorites/*")
public class RemoveFromFavorites extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getPathInfo().substring(1));
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		try {
			Connection con = DbConnection.getConnection();
			String addToFavoritesQuery = "UPDATE notes SET favorite=? WHERE id=?";
			PreparedStatement ps = con.prepareStatement(addToFavoritesQuery);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			
			int count = ps.executeUpdate();
			
			if (count <= 0) {
				out.println("Couldn't remove from favorites");
				RequestDispatcher rd = req.getRequestDispatcher("/home");
				rd.include(req, resp);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
