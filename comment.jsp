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
			String idStr=request.getParameter("id");
			idStr=idStr.substring(0,idStr.length()-1);
			int id = Integer.parseInt(idStr);
			
			String editEntity = request.getParameter("editEntityComment");
			if(editEntity != null && editEntity.equals("true")){
				System.out.println("editEntity is true");
				String comment = request.getParameter("commentTextbox");
				
				String oldComment = request.getParameter("oldComment");
				System.out.println("comment: " +  comment);
				response.sendRedirect("../entityPage.jsp?subjectID="+id);
			}
		
			//if user is not logged in
			if(km.getCurUser() == null){
				String errorMessage = "You must be logged in to comment on this item.";
				session.setAttribute("errorMessageComment", errorMessage);
				
				if(subjectType.equals("Entity")){
					response.sendRedirect("../entityPage.jsp?subjectID="+id);
				}
				else{
					response.sendRedirect("../pollPage.jsp?subjectID="+id);
				}
			}//if user not logged in 
			
			//user is logged in
			else{
				//if comment on entity
				if(subjectType.equals("Entity")){
					
					//if entity is expired
					if(km.isEntityExpired(id)){
						String errorMessage = "This entity is expired";
						session.setAttribute("errorMessageComment", errorMessage);
						response.sendRedirect("../entityPage.jsp?subjectID="+id);
					}//if entity expired

					
				}//if comment on entity
				//else comment on poll
				else{
					
					//if poll expired
					if(km.isPollExpired(id)){
						String errorMessage = "This poll is expired";
						session.setAttribute("errorMessageComment", errorMessage);
						response.sendRedirect("../pollPage.jsp?subjectID="+id);
					}//if poll expired

					
				}//else comment on poll
				
				
				
				session.setAttribute("errorMessageComment", "");
				String comment = request.getParameter("commentTextbox");
				
				User currUser = km.getCurUser();
				String userEmail = currUser.getEmail();
				
				Boolean isAnon = true;
				String checkbox = request.getParameter("checkbox");
				if(checkbox == null)
					isAnon = false;
				
				if(subjectType.equals("Entity")){
					km.newEntityComment(id, userEmail, comment, isAnon);
					session.setAttribute("kingManager", km);
					response.sendRedirect("../entityPage.jsp?subjectID=" + id);
				}
				else{
					km.newPollComment(id, userEmail, comment, isAnon);
					session.setAttribute("kingManager", km);
					response.sendRedirect("../pollPage.jsp?subjectID=" + id);
				}
				
			}//else user logged in
			
			
			//all this stuff happens only if no error exists
			
			
			
			

			
/* 			//error checking if the poll is expired
			else if(km.isEntityExpired(id)){
				String errorMessage = "This poll is expired";
				session.setAttribute("errorMessageComment", errorMessage);
				
				if(subjectType.equals("Entity")){
					response.sendRedirect("../entityPage.jsp?subjectID="+id);
				}
				else{
					response.sendRedirect("../pollPage.jsp?subjectID="+id);
				}
			} */
			//user is logged in
/* 			else{
				session.setAttribute("errorMessageComment", "");
				String comment = request.getParameter("commentTextbox");
				
				User currUser = km.getCurUser();
				String userEmail = currUser.getEmail();
				
				Boolean isAnon = true;
				
				String checkbox = request.getParameter("checkbox");
				if(checkbox == null)
					isAnon = false;
				
				if(subjectType.equals("Entity")){
					km.newEntityComment(id, userEmail, comment, isAnon);
					session.setAttribute("kingManager", km);
					response.sendRedirect("../entityPage.jsp?subjectID=" + id);
				}
				else{
					km.newPollComment(id, userEmail, comment, isAnon);
					session.setAttribute("kingManager", km);
					response.sendRedirect("../pollPage.jsp?subjectID=" + id);
				}
			} */
				
	
		%>
	</body>
</html>