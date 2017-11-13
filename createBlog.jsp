<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="Business.Poll" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Business.KingManager" %>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<% 
		KingManager km = (KingManager)session.getAttribute("kingManager");
		Boolean errorFound = false;
		String errorMessage = "";
		
		
		if(km == null)
			System.out.println("km is nul");
		
		if(km.getCurUser() == null){
			errorMessage = "You must log in";
			errorFound = true;
		}
		
		String title = request.getParameter("title");		//poll title
		String blogContent = request.getParameter("blogContent");
		String description = request.getParameter("description");
		String image = request.getParameter("image");

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal)); //2016/11/16 12:08:43
		
		//ERROR CHECKING FOR THE FOUR CRITERIA
		
		if(title == null){
			errorMessage = "You must have a title";
			errorFound = true;
		}
		
		else if(blogContent == null){
			errorMessage = "You must have some blog content";
			errorFound = true;
		}
		
		else if(description == null){
			errorMessage = "You must have a description";
			errorFound = true;
		}
		
		else if(image == null){
			errorMessage = "You must have an image";
			errorFound = true;
		}
		
		session.setAttribute("errorMessageCreateBlog", errorMessage);
		if(errorFound){
			System.out.println("Error Found");
			response.sendRedirect("../createPollPage.jsp");
		}
		else{
			
			//give new poll to 
			//km.addPoll(newPoll);
			session.setAttribute("kingManager", km);
			response.sendRedirect("../feed.jsp");
		}
		
		 
		%>


</body>
</html>