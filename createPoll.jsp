<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="Business.Poll" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Business.KingManager" %>
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
		String option1 = request.getParameter("option1");
		String option2 = request.getParameter("option2");
		String option3 = request.getParameter("option3");
		String option4 = request.getParameter("option4");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String infiniteCheckbox = request.getParameter("checkbox");
		
		System.out.println("Create poll:\n" + title + "\n" + allTags + "\n" + 
		option1 + "\n" + option2 + "\n" + option3 + "\n" + option4 + "\n" + date + "\n" + time + 
		"\n" + infiniteCheckbox);
		
		*/
		
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
		int id = km.ids; 								//poll id
		id++;
		String allTags = request.getParameter("tags");		//all tags, separated by commas
		ArrayList<String> tags = new ArrayList<String>();	//list of tags
		String[] separatedTags = allTags.split(", ");		//to separate the tags
		for(int i=0; i < separatedTags.length; i++){		//find the separated tags and add them to the list
			tags.add(separatedTags[i]);
		}//for()
		
		String image = request.getParameter("image");
			
		String option1 = request.getParameter("option1");
		String option2 = request.getParameter("option2");
		ArrayList<String> options = new ArrayList<String>();	//list of options
		options.add(option1);
		options.add(option2);
		
		//get all of the rest of the optional options
		String optionNum = "option3";
		int optionCount = 3;
		while(request.getParameter(optionNum) != null){
			String option = request.getParameter(optionNum);
			options.add(option);
			
			optionNum = "option" + optionCount;
			optionCount++;
		}
		
		//check to see that user entered at least 2 poll options
		if(option1.equals("") || option2.equals("")){
			System.out.println("Must add at least 2 options");
			errorMessage = "Must provide at least 2 options";
			errorFound = true;
		}
		
		//if checkbox checked, poll open forever
		Boolean isInfinite = false;
		String infiniteCheckbox = request.getParameter("checkbox");
		if(infiniteCheckbox != null)
			isInfinite = true;
		
		//if anon-checkbox checked, poll is anonymous
		Boolean isAnonymous = false;
		String isAnonymousCheckbox = request.getParameter("anon-checkbox");
			if(isAnonymousCheckbox != null)
				isAnonymous = true;
				
		Calendar timeEnd = Calendar.getInstance();
		
		if(!isInfinite){
			String date = request.getParameter("date");
			String[] splitDate = date.split("/");
			int day = 0; 
			int month = 0;
			int year = 0;
			
			if(splitDate.length != 3){
				System.out.println("incorrect end date");
				errorMessage = "Incorrect end date format";
				errorFound = true;
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
				errorMessage = "End date must be in the future";
				errorFound = true;
			}
			else{
				System.out.println("end time is okay.");
				
			}
		
		}
		
		
		session.setAttribute("errorMessageCreatePoll", errorMessage);
		if(errorFound){
			System.out.println("Error Found");
			response.sendRedirect("../createPollPage.jsp");
		}
		else{
			System.out.println("No Errors ");
			Poll newPoll = new Poll(title, id, tags, options, isInfinite, timeEnd, 
					image, km.getCurUser().getEmail());
			//give new poll to km
			km.addPoll(newPoll);
			session.setAttribute("kingManager", km);
			response.sendRedirect("../feed.jsp");
		}
		
		 
		%>
	</body>
</html>