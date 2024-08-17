<%@page import="utils.AppDateFormat"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dbcon.DbConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Notes</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<main class="container">
		<h1>Your Notes</h1>
		<section class="noteSection">
			<div class="card" id="add-new-note"
				data-context-path="<%=request.getContextPath()%>">
				<div>
					<img src="images/plus.png" />
				</div>
				<h1>New</h1>
			</div>
			<%
			try {
				Connection con = DbConnection.getConnection();
				String selectQuery = "SELECT * FROM notes ORDER BY time_edited DESC";
				PreparedStatement ps = con.prepareStatement(selectQuery);
				ResultSet rs = ps.executeQuery();

				// check if the set is not empty with isBeforeFirst
				if (rs.isBeforeFirst()) {
					// successfully fetched the notes
					while (rs.next()) {
						int id = rs.getInt("id");
						String title = rs.getString("title");
						String content = rs.getString("content");
						// ResultSetMetaData rsmetadata = rs.getMetaData();
						// System.out.println(rsmetadata.getColumnTypeName(4)); // DATETIME
						// System.out.println(rsmetadata.getColumnClassName(4)); // java.time.LocalDateTime
						
						LocalDateTime noteDateTime = (LocalDateTime)rs.getObject("time_edited");
						// System.out.println(id + ": "+noteDateTime);
						// System.out.println("Date: "+noteDateTime.getMonth() + " "+noteDateTime.getDayOfMonth()+" "+localDateTime.getYear());
						String formattedTime = AppDateFormat.convert(noteDateTime);
						// System.out.println(id + ": " + formattedTime);
			%>
			<div class="card" id="<%=id%>">
				<div class="noteContent">
					<%
					if (title == null || title.isEmpty()) {
					%>
					<h3 style="color: grey"><%="No title"%></h3>
					<%
					} else {
					%>
					<h3><%=title%></h3>
					<%
					}
					%>

					<p><%=content%></p>
				</div>
				<div class="noteFooter">
					
					<p><%=formattedTime %></p>
					
					<a href="delete-note/<%=rs.getInt("id")%>" class="noteDelete">
						<img src="images/trash_24px.png" width="18" />
					</a>
				</div>
			</div>
			<%
			}
			} else {
			// No notes present yet
			out.println("<h3 style='text-align:center'>No notes yet</h3>");
			}
			con.close();

			} catch (Exception e) {
			e.printStackTrace();
			}
			%>
		</section>
	</main>

	<!-- Script for navigation on click -->
	<script src="js/click-navigate.js"></script>
</body>
</html>