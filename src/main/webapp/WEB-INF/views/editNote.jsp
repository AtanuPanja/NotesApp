<%@page import="model.Note"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Note | Edit</title>
<!-- Since I am using RequestDispatcher to redirect to EditTodo controller to editTodo.jsp,
I need to set the absolute path to the style.css file, as it wouldn't be applied then.
 -->
<!-- pageContext.request.contextPath doesn't give the appropriate results
Somehow, it mixes the request of style.css, with the edit-note/:id,
to ensure separation, we use request.getContextPath() -->

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/add_and_edit_forms.css"/>

</head>
<body>
	<%
	Note note = (Note)session.getAttribute("note");
			int id = note != null ? note.getId() : 0;
			String title = note != null ? note.getTitle(): "";
			
			String content = note != null ? note.getContent(): "";
	%>

     <form action="<%= id %>" method="post">
     	<section class="pageHeader">
     		<h1>Edit Note</h1>
     		<button id="save">Save changes</button>
     	</section>
     	
     	<section>
     		<textarea class="title" placeholder="Title" name="title"><%=title %></textarea><br/><br/>
         	<textarea class="content" placeholder="Content" name="content"><%=content %></textarea>
     	</section>
         
     </form>
     
     <script src="<%= request.getContextPath() %>/js/expand_textarea_show_btn.js"></script>
</body>
</html>