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
%>
<div class="card" id="<%=note.getId()%>">
<%
				if (!isDeletedNotes) {
%>
		<div class="favoritesBtn">
<%
					if (note.isFavorite()) {
%>
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
				if (note.getTitle() == null || note.getTitle().isEmpty()) {
%>
		<h3 style="color: grey"><%="No title"%></h3>
<%
				} 
				else {
%>
		<h3><%=note.getTitle()%></h3>
<%
				}
%>

		<p><%=note.getContent()%></p>
	</div>
	
	<div class="noteFooter">
<%
				if(!isDeletedNotes) {
%>
		<p><%=note.getTimeEdited()%></p>
		<a href="move-to-trash/<%=note.getId()%>" class="noteDelete" title="Move to bin"> 
			<img src="images/recycle-bin_24px.png" alt="Move to bin"/>
		</a>
<%
				}
				else {
%>
		<a href="delete-note/<%=note.getId()%>" class="noteDelete" title="Delete permanently"> 
			<img src="images/delete_24px.png" alt="Delete permanently"/>
		</a>
		<a href="restore-note/<%=note.getId()%>" class="noteDelete" title="Restore"> 
			<img src="images/restore_24px.png" alt="Restore"/>
		</a>
<%
				}
%>
		

		
	</div>
</div>
<%
			}
		}
	}
%>
