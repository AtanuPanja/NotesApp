<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Note | Add</title>
<link rel="stylesheet" href="css/add_and_edit_forms.css" />
<style>
	button {
		display: block;
	}	
</style>
</head>
<body>
	

	<form action="add-note" method="post">
		<section class="pageHeader">
			<h1>Add Note</h1>
			<button id="save">Save</button>
		</section>

		<section>
			<textarea class="title" placeholder="Title" name="title"></textarea>
			<br />
			<br />
			<textarea class="content" placeholder="Content" name="content"></textarea>
		</section>

	</form>
	<script src="<%= request.getContextPath() %>/js/expand_textarea_show_btn.js"></script>
</body>
</html>