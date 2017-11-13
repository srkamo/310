<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="Business.KingManager" %>
<%@ page import="Business.User" %>
<%@ page import="Business.Action" %>
<%@ page import="Business.PollAction" %>
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
			String idStr=request.getParameter("id");
			idStr=idStr.substring(0,idStr.length()-1);
			int id = Integer.parseInt(idStr);
			Action userRatedOrVoted = null;
			userRatedOrVoted = km.userRatedOrVoted(id, currUser.getEmail());
			
			if(km.getCurUser()==null)
			{
				String errorMessage = "You need to be logged in to vote on this poll."; 
				session.setAttribute("errorMessageVoteOnPoll", errorMessage); 
				response.sendRedirect("../pollPage.jsp?subjectID=" + id);				
			}
			
			else if(km.isPollExpired(id) && !km.getPoll(id).isInfinite()){
				String errorMessage = "This item is expired"; 
				session.setAttribute("errorMessageVoteOnPoll", errorMessage); 
				response.sendRedirect("../pollPage.jsp?subjectID=" + id);	
			}
			
			//if user has already voted
			else if((userRatedOrVoted != null) && (userRatedOrVoted instanceof PollAction)){
				String choice = request.getParameter("vote");
				PollAction action = (PollAction) userRatedOrVoted;
				String errorMessage = "";
				
				//if user is trying to vote on the same option again
				if(choice.equals(action.getOptionChosen())){
					errorMessage = "You have already voted on this poll"; 
				}
				//else user wants to change their vote
				else{
					errorMessage = "You have changed your vote on this poll";
					km.editVote(id, currUser.getEmail(), choice);
				}
				
				session.setAttribute("errorMessageVoteOnPoll", errorMessage); 
				response.sendRedirect("../pollPage.jsp?subjectID=" + id);	
			}
			
			else{
				String userEmail = currUser.getEmail();
				//int id = Integer.parseInt(request.getParameter("id"));
				
				String choice = request.getParameter("vote");
				
				km.newVote(id, choice, true, userEmail);
				session.setAttribute("kingManager", km);
				
				String message = "You voted on the poll.";
				session.setAttribute("errorMessageVoteOnPoll", message);
				
				response.sendRedirect("../pollPage.jsp?subjectID=" + id);
			}
		%>
	</body>
</html>