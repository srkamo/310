<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="Business.KingManager" %>
<%@page import="Business.Entity" %>
<%@page import="Business.User" %>
<%@page import="Business.Action" %>
<%@page import="Business.RatingAction" %>
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
			
			Action userRatedOrVoted = null;
			
			if(currUser != null){
				userRatedOrVoted = km.userRatedOrVoted(id, currUser.getEmail());
			}
			
			//error message for if user is not logged in
			if(km.getCurUser()==null)
			{
				String errorMessage = "You need to be logged in to rate this item."; 
				session.setAttribute("errorMessageRateEntity", errorMessage); 
				response.sendRedirect("../entityPage.jsp?subjectID=" + id);				
			}
			
			else if(km.isEntityExpired(id) && !km.getEntity(id).isInfinite()){
				String errorMessage = "This item is expired."; 
				session.setAttribute("errorMessageRateEntity", errorMessage); 
				response.sendRedirect("../entityPage.jsp?subjectID=" + id);	
			}
			//user already rated, change rating value
			else if((userRatedOrVoted != null) && (userRatedOrVoted instanceof RatingAction)){
				RatingAction rAction = (RatingAction)userRatedOrVoted;
				String errorMessage = "";
				Boolean upVote = false;
				if(vote.equals("\'upVote\'")){ upVote = true; }
				
				//if user already upvoted and is trying to upvote again
				if(upVote && rAction.isUpVote()){
					errorMessage = "You have already upvoted this item.";
				}
				//if user has already downvoted and is trying to downvote again
				else if(!upVote && !rAction.isUpVote()){
					errorMessage = "You have already downvoted this item.";
				}
				
				//else user wants to change their vote
				else{
					errorMessage = "You have changed your rating on this entity."; 
					km.editRating(id, currUser.getEmail());
				}
				
				
				session.setAttribute("errorMessageRateEntity", errorMessage); 
				response.sendRedirect("../entityPage.jsp?subjectID=" + id);	
			}
			
			//user has not yet rated the item
			else{
			String errorMessage = ""; 
			session.setAttribute("errorMessageRateEntity", errorMessage); 
			String userEmail = currUser.getEmail();
			
			if(vote.equals("\'upVote\'")){
				km.newRating(id, true, userEmail, true);
				 errorMessage = "You gave this rating a thumbs up."; 
				session.setAttribute("errorMessageRateEntity", errorMessage); 
				
			}
			else{
				km.newRating(id, false, userEmail, true);
				errorMessage = "You gave this rating a thumbs down."; 
				session.setAttribute("errorMessageRateEntity", errorMessage); 
				
			}
			
			response.sendRedirect("../entityPage.jsp?subjectID=" + id);					
			}
			%>
	
	</body>
</html>