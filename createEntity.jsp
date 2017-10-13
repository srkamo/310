<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="Business.KingManager" %>
<%@page import="Business.Entity" %>
<%@page import="java.util.ArrayList" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
		KingManager km = (KingManager)session.getAttribute("kingManager");
		
		
		String title = request.getParameter("title");		//poll title
		int id = km.getIds();								//poll id
		id++;
		String allTags = request.getParameter("tags");		//all tags, separated by commas
		ArrayList<String> tags = new ArrayList<String>();	//list of tags
		String[] separatedTags = allTags.split(", ");		//to separate the tags
		for(int i=0; i < separatedTags.length; i++){		//find the separated tags and add them to the list
			tags.add(separatedTags[i]);
		}//for()
		
		String description = request.getParameter("description");
		
		
		//check to see that user entered at least 2 poll options
		
		
		Boolean isInfinite = true;
		String infiniteCheckbox = request.getParameter("checkbox");
		if(infiniteCheckbox == null)
			isInfinite = false;
		
		int day = Integer.parseInt(request.getParameter("day"));
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		int hour = Integer.parseInt(request.getParameter("hour"));
		int minute = Integer.parseInt(request.getParameter("minute"));
		Calendar timeEnd = Calendar.getInstance();
		timeEnd.set(year, month, day, hour, minute); //time that poll will close 
		
		String image = request.getParameter("image");
		
		Entity newEntity = new Entity(title, id, description, tags, isInfinite,  timeEnd, image);
	
		//give new poll to km
		km.addEntity(newEntity);
		session.setAttribute("kingManager", km);
		
		response.sendRedirect("../feed.jsp");
		%>
	</body>
</html>