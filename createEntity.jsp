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
		
		
		String title = request.getParameter("title");		//entity title
		int id = km.getIds();								//entity id
		id++;
		String allTags = request.getParameter("tags");		//all tags, separated by commas
		ArrayList<String> tags = new ArrayList<String>();	//list of tags
		String[] separatedTags = allTags.split(", ");		//to separate the tags
		for(int i=0; i < separatedTags.length; i++){		//find the separated tags and add them to the list
			tags.add(separatedTags[i]);
		}//for()
		
		String description = request.getParameter("description");	//description of entity
		
		//if checkbox checked, entity open forever
		Boolean isInfinite = false;
		String infiniteCheckbox = request.getParameter("checkbox");
		if(infiniteCheckbox == null)
			isInfinite = true;
		
		String date = request.getParameter("endDate");
		String[] splitDate = date.split("/");
		int day = 0; 
		int month = 0;
		int year = 0;
		
		if(splitDate.length != 3){
			System.out.println("incorrect end date");
		}
		else{
			day = Integer.parseInt(splitDate[0]);
			month = Integer.parseInt(splitDate[1]);
			year = Integer.parseInt(splitDate[2]);
		}

		Calendar timeEnd = Calendar.getInstance();
		timeEnd.set(year, month, day, 12, 00); 					//date that poll will close (at midnight)
		
		String image = request.getParameter("image");
		
		//missing info
		if(title == null || image == null || description == null)
			System.out.println("missing info");
		
		Entity newEntity = new Entity(title, id, description, tags, isInfinite,  timeEnd, image);
	
		//give new poll to km
		km.addEntity(newEntity);
		session.setAttribute("kingManager", km);
		
		response.sendRedirect("../feed.jsp");
		%>
	</body>
</html>