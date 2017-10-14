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
			int id = Integer.parseInt(request.getParameter("id"));
			String comment = request.getParameter("commentTextbox");
			User currUser = km.getCurUser();
			String userEmail = currUser.getEmail();
			String subjectType = request.getParameter("subjectType");
			
			Boolean isAnon = false;
			String checkbox = request.getParameter("checkbox");
			if(checkbox == null)
				isAnon = false;
			
			if(subjectType.equals("Entity")){
				km.newEntityComment(id, userEmail, comment, isAnon);
				session.setAttribute("kingManager", km);
				response.sendRedirect("\" entityPage.jsp?subjectID=" + id + "\"");
			}
			else{
				km.newPollComment(id, userEmail, comment, isAnon);
				session.setAttribute("kingManager", km);
				response.sendRedirect("\" pollPage.jsp?subjectID=" + id + "\"");
			}
	
		%>
	</body>
</html>