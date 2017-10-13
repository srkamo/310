<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="Database.Database" %>
<%@page import="Business.User" %>
<%@page import="Business.KingManager" %>
<%@page import="Business.EmailVerifier" %>
<%@page import="Business.Hash" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<%
			KingManager km = (KingManager)session.getAttribute("kingManager");
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String fName = request.getParameter("fName");
			String lName = request.getParameter("lName");
			
			//error: need to fill all boxes
			if(email == null || password == null || fName == null || lName == null){
				System.out.println("Invalid credentials");
			}
		
			//email is valid, let user sign up
			if(EmailVerifier.validate(email)){
				System.out.println("Greetings Fellow Trojan");
				User newUser = new User(email, Hash.hash(password), fName, lName);
				km.addUser(newUser);
				session.setAttribute("kingManager", km);
				response.sendRedirect("../feed.jsp");
			}
			//email not valid
			else
				System.out.println("Not Validated");
			
			
		%>
	</body>
</html>