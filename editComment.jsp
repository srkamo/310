<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="Business.KingManager" %>
<%@ page import="Business.Entity" %>
<%@ page import="java.util.ArrayList" %>
<%@page import="java.util.GregorianCalendar" %>
<%@page import="java.util.Calendar" %>
<%@ page import="Business.Action" %>
<%@ page import="Business.CommentAction" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Comment</title>
</head>
<body>
<%
	KingManager km  = (KingManager) session.getAttribute("kingManager");
	String subjectID = request.getParameter("subjectID"); 
	String curComment = request.getParameter("curComment");
	System.out.println("curComment: " + curComment);
	int subjectIDint = Integer.parseInt(subjectID);


%>

What up, subjectID <%= subjectID %>


	<form action="servlets/comment.jsp">
		<center><textarea name="commentTextbox" style="width:600px" placeholder= <%= "\"" + curComment +"\""%> ></textarea></center>
		<input type="hidden" name="id" value=<%=subjectIDint %>/>
		<input type="hidden" name="subjectType" value="Entity"/>
		<input type="hidden" name="editEntityComment" value="true"/>
		<input type="hidden" name="oldComment" value=<%= "\"" + curComment +"\""%>/>
		<center><input type="submit" value="Add Comment" style="width:600px"></center>
		<center>Check this box if you would like to 
		leave your comment anonymously: <input type="checkBox"  name="checkbox"></center>
	</form>

</body>
</html>