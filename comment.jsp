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
			}
			
			//user is logged in
			else{
				//if subject type is entity
				if(subjectType.equals("Entity")){
					String editEntity = request.getParameter("editEntityComment");
					
					//if entity expired
					if(km.isEntityExpired(id)){
						String errorMessage = "This entity is expired";
						session.setAttribute("errorMessageComment", errorMessage);
					}//expired
					
					//if edit comment
					else if(editEntity != null && editEntity.equals("true")){
						String comment = request.getParameter("commentTextbox");
						String oldComment = request.getParameter("oldComment");
						
						km.editComment(id, oldComment, comment);
						session.setAttribute("kingManager", km);
					}//edit comment
					
					//not edit comment - just comment
					else{
						session.setAttribute("errorMessageComment", "");
						String comment = request.getParameter("commentTextbox");
						
						User currUser = km.getCurUser();
						String userEmail = currUser.getEmail();
						
						Boolean isAnon = true;
						String checkbox = request.getParameter("checkbox");
						if(checkbox == null)
							isAnon = false;
						
						km.newEntityComment(id, userEmail, comment, isAnon);
						session.setAttribute("kingManager", km);
					}//comment
					
					response.sendRedirect("../entityPage.jsp?subjectID="+id);
				}//subject type is entity
				
				//if subject type is poll
				else{
					String editPoll = request.getParameter("editPollComment");
					
					//if poll expired
					if(km.isPollExpired(id)){
						String errorMessage = "This poll is expired";
						session.setAttribute("errorMessageComment", errorMessage);
					}//if poll expired
					
					//if edit comment
					else if(editPoll != null && editPoll.equals("true")){
						String comment = request.getParameter("commentTextbox");
						String oldComment = request.getParameter("oldComment");
						
						km.editComment(id, oldComment, comment);
						session.setAttribute("kingManager", km);
					}//if edit poll comment
					
					//not edit comment - just commment
					else{
						session.setAttribute("errorMessageComment", "");
						String comment = request.getParameter("commentTextbox");
						
						User currUser = km.getCurUser();
						String userEmail = currUser.getEmail();
						
						Boolean isAnon = true;
						String checkbox = request.getParameter("checkbox");
						if(checkbox == null)
							isAnon = false;
						
						km.newPollComment(id, userEmail, comment, isAnon);
						session.setAttribute("kingManager", km);
					}//comment on poll
					
					response.sendRedirect("../pollPage.jsp?subjectID="+id);
				}//else poll
			}//else logged in
				
	
		%>
	</body>
</html>