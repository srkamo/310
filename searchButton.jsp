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
		String frequentSearch="";
		if(search==null)
		{
			frequentSearch = request.getParameter("frequentSearch");
		}
		
		//list of objects to be displayed
		if(search!=null)
		{
		km.search(search);
		}
		else
		{
		km.search(frequentSearch);
		}
		session.setAttribute("kingManager", km);
		response.sendRedirect("../feed.jsp"); 

	%>
		
		
	
	</body>
</html>