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
			
			System.out.println(request.getParameter("id"));
			
			int id = Integer.parseInt(request.getParameter("id"));
			User currUser = km.getCurUser();
			
			//error message for if user is not logged in
			if(km.getCurUser()==null)
			{
				System.out.println("User is not logged in");
				String errorMessage = "You need to be logged in to rate this item."; 
				session.setAttribute("errorMessageRateEntity", errorMessage); 
				response.sendRedirect("../entityPage.jsp?subjectID=" + id);				
			}
			
			else if(km.isEntityExpired(id)){
				System.out.println("This item is expired");
				String errorMessage = "This item is expired"; 
				session.setAttribute("errorMessageRateEntity", errorMessage); 
				response.sendRedirect("../entityPage.jsp?subjectID=" + id);	
			}
			else
			{
			String errorMessage = ""; 
			session.setAttribute("errorMessageRateEntity", errorMessage); 
			String userEmail = currUser.getEmail();
			
			if(vote.equals("\'upVote\'")){
				km.newRating(id, true, userEmail, true);
				 errorMessage = "Upvote successful"; 
				session.setAttribute("errorMessageRateEntity", errorMessage); 
				

			}
			else{
				km.newRating(id, false, userEmail, true);
				errorMessage = "downvote successful"; 
				session.setAttribute("errorMessageRateEntity", errorMessage); 
				
			}
			
			response.sendRedirect("../entityPage.jsp?subjectID=" + id);					
			}
			%>
	
	</body>
</html>