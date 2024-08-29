<%@page import="utils.AppDateFormat"%>
<%@page import="model.Note"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
	boolean isDeletedNotes = false;
	Object obj1 = request.getAttribute("isTrash");
	if (obj1 != null && obj1 instanceof Boolean) {
		isDeletedNotes = (boolean)obj1;
	}
	
	Object obj = request.getAttribute("notes");
	List<Note> notes = null;
	if (obj instanceof List<?>) {
		notes = (List<Note>)obj;
		
		if (notes.size() == 0) {
%>
<h3>No notes here</h3>
<%
		}
		
		else {
		
			for (Note note: notes) {
				int noteId = note.getId();
				String title = note.getTitle();
				String content = note.getContent();
				String timeEdited = AppDateFormat.convert(note.getTimeEdited());
				boolean isFavorite = note.isFavorite();
				
%>
<div class="card" id="<%=noteId%>">
<%			if (!isDeletedNotes) { %>
		<div class="favoritesBtn">
<%				if (isFavorite) { %>
			<img src="images/remove-favorite_24px.png" title="Remove from favorites" alt="Remove from favorites"/>
<%
					}
					else {
%>
			<img src="images/add-favorite_24px.png" title="Add to favorites" alt="Add to favorites"/>
<%
					}
%>
	</div>
<%
				}
%>

	<div class="noteContent" title="Read or Edit">
<%
				if (title == null || title.isEmpty()) {
%>
		<h3 style="color: grey"><%="No title"%></h3>
<%
				} 
				else {
%>
		<h3><%=title%></h3>
<%
				}
%>

		<p><%=content%></p>
	</div>
	
	<div class="noteFooter">
<%
				if(!isDeletedNotes) {
%>
		<p><%= timeEdited %></p>
		<form action="move-to-trash/<%=noteId%>" method="post">
			<button class="noteDelete" title="Move to bin"> 
				<img src="images/recycle-bin_24px.png" alt="Move to bin"/>
			</button>
		</form>
		
<%
				}
				else {
%>
		<form action="delete-note/<%=noteId%>" method="post">
			<button class="noteDelete" title="Delete permanently"> 
				<img src="images/delete_24px.png" alt="Delete permanently"/>
			</button>
		</form>
		
		<form action="restore-note/<%=noteId%>" method="post">
			<button class="noteDelete" title="Restore"> 
				<img src="images/restore_24px.png" alt="Restore"/>
			</button>
		</form>
		
<%				} %>
		

		
	</div>
</div>
<%
			}
		}
	}
%>
