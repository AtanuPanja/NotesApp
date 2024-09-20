<%
	String message = (String)session.getAttribute("notifyMessage");
	
	if (message != null && message != "") {
%>
<div class="notification">
	<p><%= message %></p>
	<img src="<%=request.getContextPath() %>/images/close-window_24px.png"/>
</div>
<%	
	}
%>
<%
	session.setAttribute("notifyMessage", "");
%>
