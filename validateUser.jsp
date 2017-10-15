<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="Database.Database" %>
<%@page import="Business.User" %>
<%@page import="Business.KingManager" %>
<%@page import="Business.EmailVerifier" %>
<%@page import="Business.Hash" %>
<%@page import="java.io.PrintWriter" %>
<%@page import="javax.servlet.RequestDispatcher" %>
<%@page import="javax.script.ScriptEngineManager" %>
<%@page import="javax.script.ScriptEngine" %>
<%@page import="javax.script.Invocable" %>


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
			
			//user exists in the database
			if(km.getUser(email) != null){
				System.out.println("validateUser.jsp: inside of getUser call");
				User user = km.getUser(email);
				String userPass = user.getPassword();
				String hashPass = Hash.hash(password);
				
				//correct passwor entered
				if(hashPass.equals(userPass)){
					System.out.println("validateUser.jsp: inside hashing call"); 
					System.out.println("email and password correct");
					session.setAttribute("errorMessageSignIn", "");
					km.setCurUser(user);
					session.setAttribute("kingManager", km);
					response.sendRedirect("../feed.jsp");
				}
				//incorrect password
				else{
					System.out.println("Wrong Password");
 					String errorMessage = "Incorrect password. Please try again. "; 
					session.setAttribute("errorMessageSignIn", errorMessage); 
					response.sendRedirect("../login.jsp");

				}
				
			}//if
			//user does not exist in the database
			else{
				System.out.println("User DNE");
				String errorMessage = "Incorrect username. Please try again. "; 
				session.setAttribute("errorMessageSignIn", errorMessage); 
				response.sendRedirect("../login.jsp");
			}
			
		
		%>
	</body>
</html>