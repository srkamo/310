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
	
			String toFollow = request.getParameter("userEmail");
			//if user is not logged in
			if(km.getCurUser() == null){
				System.out.println("You must be logged in to follow another user."); 
				String errorMessage = "You must be logged in to follow another user.";
				session.setAttribute("errorMessageLogFollow", errorMessage);
		
			}//if user not logged in 
			
			// if current user already follows the other user 
			else if (km.alreadyFollows(toFollow)){
				System.out.println("You already follow this user."); 
				String errorMessage = "You already follow this user.";
				session.setAttribute("errorMessageFollow", errorMessage);
			}// if current user already follows the other user 
			
			else{
				km.followUser(toFollow);
				session.setAttribute("kingManager", km);
			}
			
			//all this stuff happens only if no error exists
			session.setAttribute("errorMessage", "");
			
			
			response.sendRedirect("../publicUserPage.jsp?email=" + toFollow);
	%>
</body>
</html>