<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Note | Add</title>
<link rel="stylesheet" href="css/add_and_edit_forms.css" />
<link rel="stylesheet" type="text/css" href="css/notificationAlert.css"/>
<style>
	button {
		display: block;
	}
	
	.notification {
		position: unset;
	}
	
</style>

</head>
<body>
	<jsp:include page="../components/notificationAlert.jsp"/>
	<form action="add-note" method="post">
		<section class="pageHeader">
			<h1>Add Note</h1>
			<button id="save">Save</button>
		</section>

		<section>
			<textarea class="title" placeholder="Title" name="title" maxlength=255></textarea>
			<br />
			<br />
			<textarea class="content" placeholder="Content" name="content"></textarea>
		</section>

	</form>
	<script src="<%= request.getContextPath() %>/js/expand_textarea_show_btn.js"></script>
	<script src="<%= request.getContextPath() %>/js/handle_notification_alert.js"></script>
</body>
</html>