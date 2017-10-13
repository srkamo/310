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
			
			//user exists in the database
			if(km.getUser(email) != null){
				User user = km.getUser(email);
				String userPass = user.getPassword();
				String hashPass = Hash.hash(password);
				
				//correct passwor entered
				if(hashPass.equals(userPass)){
					System.out.println("email and password correct");
					km.setCurUser(user);
					session.setAttribute("kingManager", km);
					response.sendRedirect("../feed.jsp");
				}
				//incorrect password
				else{
					System.out.println("Wrong Password");
				}
				
			}//if
			//user does not exist in the database
			else{
				System.out.println("User DNE");
			}
			
		
		%>
	</body>
</html>