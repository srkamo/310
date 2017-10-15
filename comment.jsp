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
			String subjectType = request.getParameter("subjectType");
			
		
			//if user is not logged in
			if(km.getCurUser() == null){
				String errorMessage = "You must be logged in to comment on this item.";
				session.setAttribute("errorMessageComment", errorMessage);
				
				if(subjectType.equals("Entity")){
					response.sendRedirect("../entityPage.jsp");
				}
				else{
					response.sendRedirect("../pollPage.jsp");
				}
			}
			//user is logged in
			else{
				session.setAttribute("errorMessageComment", "");
				int id = Integer.parseInt(request.getParameter("id"));
				String comment = request.getParameter("commentTextbox");
				User currUser = km.getCurUser();
				String userEmail = currUser.getEmail();
				
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
			}
				
	
		%>
	</body>
</html>