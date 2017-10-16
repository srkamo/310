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
			if(email.equals("") || password.equals("") || fName.equals("") || lName.equals("")){
				System.out.println("Invalid credentials");
				String errorMessage = "ERROR: Must fill out all boxes.";
				session.setAttribute("errorMessageSignUp", errorMessage);
				response.sendRedirect("../login.jsp");
			}
		
			//email is valid and not already in use, let user sign up
			else if(EmailVerifier.validate(email) && km.getUser(email) == null){
				System.out.println("Greetings Fellow Trojan");
				User newUser = new User(email, Hash.hash(password), fName, lName);
				km.addUser(newUser);
				session.setAttribute("kingManager", km);
				session.setAttribute("errorMessageSignUp", "");
				response.sendRedirect("../feed.jsp");
			}
			//email not valid OR email already in use  **THIS IS WHERE MY CHANGE BEGINS**
			else{
				System.out.println("Not Validated");
				if (km.getUser(email) != null){
					String errorMessage = "ERROR: Email address is already in use.";
					session.setAttribute("errorMessageSignUp", errorMessage);
					response.sendRedirect("../login.jsp");
				}
				else {
					String errorMessage = "ERROR: Invalid USC email address.";
					session.setAttribute("errorMessageSignUp", errorMessage);
					response.sendRedirect("../login.jsp");
				}
			}  // **THIS IS WHERE MY CHANGE ENDS**
			
			
		%>
	</body>
</html>