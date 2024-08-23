<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Notes</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css" />
</head>
<body>
	<jsp:include page="../components/menu.jsp"/>
	<hr>
	<main class="container">
		<h2>All Notes</h2>
		<section class="noteSection">
			<div class="card" id="add-new-note">
				<div>
					<img src="images/plus.png" />
				</div>
				<h1>New</h1>
			</div>
			<jsp:include page="../components/noteList.jsp"/>
		</section>
	</main>

	<!-- Script for navigation on click -->
	<script src="js/menu.js"></script>
	<script src="js/add_and_edit_navigate.js"></script>
</body>
</html>