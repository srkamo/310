<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="Database.Database" %>
<%@page import="Business.User" %>
<%@page import="Business.KingManager" %>
<%@page import="Business.EmailVerifier" %>
<%@page import="Business.Hash" %>
<%@page import="Business.Poll" %>
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
		String search = request.getParameter("searchTextBar");
		//list of objects to be displayed
		km.search(search);
		session.setAttribute("kingManager", km);
		
		//debugging stuff
/* 		System.out.println("search: " + search);
		System.out.println("list size: " + list.size());
		for(int i = 0; i < list.size(); i++){
			Object o = list.get(i);
			
			if(o instanceof Entity){
				System.out.println((((Entity)o).getTitle() + " " + ((Entity)o).getNumViews()));
			}
			
			else if(o instanceof Poll){
				System.out.println("");
			}
		}//for */
	
	%>
		
		
	
	</body>
</html>