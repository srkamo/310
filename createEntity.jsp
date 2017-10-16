<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="Business.KingManager" %>
<%@page import="Business.Entity" %>
<%@page import="java.util.ArrayList" %>

<%@page import="java.util.GregorianCalendar" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
		/*
		String title = request.getParameter("title");
		String allTags = request.getParameter("tags");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String infiniteCheckbox = request.getParameter("checkbox");
		String image = request.getParameter("image");
		
		System.out.println("Create entity:\n" + title + "\n" + allTags + "\n" + date + "\n" + time + 
		"\n" + infiniteCheckbox + "\n" + image);
		
		*/
		
		
		KingManager km = (KingManager)session.getAttribute("kingManager");
		String errorMessage = "";
		Boolean errorFound = false;
		
		if(km.getCurUser() == null){
			errorMessage = "You must log in";
			errorFound = true;
		}
		
		String title = request.getParameter("title");		//entity title
		int id = km.ids;								//entity id
		id++;
		String allTags = request.getParameter("tags");		//all tags, separated by commas
		ArrayList<String> tags = new ArrayList<String>();	//list of tags
		String[] separatedTags = allTags.split(", ");		//to separate the tags
		for(int i=0; i < separatedTags.length; i++){		//find the separated tags and add them to the list
			tags.add(separatedTags[i]);
		}//for()
		
		String description = request.getParameter("description");	//description of entity
		String image = request.getParameter("image");
		
		//if checkbox checked, entity open forever
		Boolean isInfinite = false;
		String infiniteCheckbox = request.getParameter("checkbox");
		if(infiniteCheckbox.equals("on"))
			isInfinite = true;
		
		System.out.println("isInfinite: " + isInfinite + "\ninfiniteCheckbox: " + infiniteCheckbox);
		
		Calendar timeEnd = Calendar.getInstance();
		
		if(isInfinite){
			
			String date = request.getParameter("date");
			String[] splitDate = date.split("/");
			int day = 0; 
			int month = 0;
			int year = 0;
		
			
			if(splitDate.length != 3){
				System.out.println("incorrect end date");
				errorFound = true;
				errorMessage = "Incorrect end date format";
			}
			else{
				month = Integer.parseInt(splitDate[0]);
				 day = Integer.parseInt(splitDate[1]);
				year = Integer.parseInt(splitDate[2]);
			}
			
			System.out.println("day: " + day + " month: " + month + "year: " + year);
	
			Calendar now = Calendar.getInstance();
			timeEnd = new GregorianCalendar(year, month - 1, day);
			
			
			if(timeEnd.before(now)){
				System.out.println("Can't make an end time before the current time, dumby");
				errorFound = true;
				errorMessage = "End date must be in the future";
			}
		
		}
		
		//missing info
		if(title.equals("") || image.equals("") || description.equals("")){
			System.out.println("missing info");
			errorFound = true;
			errorMessage = "Missing information";
		}
		
		if(!errorFound){
			System.out.println("no errors found");
			Entity newEntity = new Entity(title, id, description, tags, isInfinite,  timeEnd, image);
			
			//give new poll to km
			km.addEntity(newEntity);
			session.setAttribute("kingManager", km);
			session.setAttribute("errorMessageCreateEntity", "");
			response.sendRedirect("../feed.jsp");
		}
		else{
			System.out.println("error found");
			session.setAttribute("errorMessageCreateEntity", errorMessage);
			response.sendRedirect("../createEntityPage.jsp");
		}
		%>
	</body>
</html>