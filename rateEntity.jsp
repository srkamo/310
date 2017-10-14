<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="Business.KingManager" %>
<%@page import="Business.Entity" %>
<%@page import="Business.User" %>
<%@page import="java.util.ArrayList" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	
		<%
			KingManager km = (KingManager)session.getAttribute("kingManager");
			String vote = request.getParameter("vote");
			int id = Integer.parseInt(request.getParameter("id"));
			User currUser = km.getCurUser();
			String userEmail = currUser.getEmail();
			
			if(vote.equals("upVote")){
				km.newRating(id, true, userEmail, true);
			}
			else{
				km.newRating(id, false, userEmail, true);
			}
		%>
	
	</body>
</html>