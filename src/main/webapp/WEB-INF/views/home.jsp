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
<link rel="stylesheet" type="text/css" href="css/menu.css" />
<link rel="stylesheet" type="text/css" href="css/notificationAlert.css"/>
</head>
<body>
	<jsp:include page="../components/notificationAlert.jsp"/>
	<jsp:include page="../components/menu.jsp"/>
	<main class="container">
		<h2>All Notes</h2>
		<section class="noteSection">
			<div class="card" id="add-new-note">
				<div>
					<img class="add-icon" src="images/plus.png" alt="Create"/>
				</div>
				<h1>New</h1>
			</div>
			<jsp:include page="../components/noteList.jsp"/>
		</section>
	</main>

	<!-- Script for navigation on click -->
	<script src="js/themes.js"></script>
	<script src="js/menu.js"></script>
	<script src="js/add_and_edit_navigate.js"></script>
	<script src="js/add_remove_favorites.js"></script>
	<script src="js/delete_actions.js"></script>
	<script src="js/handle_notification_alert.js"></script>
</body>
</html>