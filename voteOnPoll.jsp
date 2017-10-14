<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="Business.KingManager" %>
<%@ page import="Business.User" %>
<%@ page import="Business.Poll" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			KingManager km = (KingManager)session.getAttribute("kingManager");
			User currUser = km.getCurUser();
			String userEmail = currUser.getEmail();
			int id = Integer.parseInt(request.getParameter("id"));
			String choice = request.getParameter("vote");
			
			km.newVote(id, choice, true, userEmail);
			session.setAttribute("kingManager", km);
			response.sendRedirect("\" pollPage.jsp?subjectID=" + id + "\"");
		%>
	</body>
</html>