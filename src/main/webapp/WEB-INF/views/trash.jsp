<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Notes</title>
<link rel="stylesheet" type="text/css" href="css/themes.css"/>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/trash.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css" />
<link rel="stylesheet" type="text/css" href="css/notificationAlert.css"/>
</head>
<body>
	<jsp:include page="../components/notificationAlert.jsp"/>
	<jsp:include page="../components/menu.jsp"/>
	<main class="container">
		<section class="heading">
			<h2>Deleted Notes</h2>
<% if (!("0".equals(String.valueOf(request.getAttribute("numberOfNotes"))))) { %>
			
			<button id="emptyBin">Empty Bin</button>
			
<% } %>
		</section>
		<section class="noteSection">
			
			<jsp:include page="../components/noteList.jsp"/>
		</section>
		<script src="js/themes.js"></script>
		<script src="js/menu.js"></script>
		<script src="js/delete_actions.js"></script>
		<script src="js/handle_notification_alert.js"></script>
	</main>
</body>
</html>