<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="Business.KingManager" %>
<%@ page import="Business.User" %>
<%@ page import="Business.Action" %>
<%@ page import="Business.CommentAction" %>
<%@ page import="Business.PollAction" %>
<%@ page import="Business.RatingAction" %>
<%@ page import="Business.Entity" %>
<%@ page import="Business.Poll" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>KnowItAll | User Profile Page </title>
        <meta name="description" content="GARO is a real-estate template">
        <meta name="author" content="Kimarotec">
        <meta name="keyword" content="html5, css, bootstrap, property, real-estate theme , bootstrap template">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
        <link rel="icon" href="favicon.ico" type="image/x-icon">

        
        <link rel="stylesheet" href="assets/css/normalize.css">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/fontello.css">
        <link href="assets/fonts/icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
        <link href="assets/fonts/icon-7-stroke/css/helper.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" href="assets/css/bootstrap-select.min.css"> 
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/icheck.min_all.css">
        <link rel="stylesheet" href="assets/css/price-range.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.css">  
        <link rel="stylesheet" href="assets/css/owl.theme.css">
        <link rel="stylesheet" href="assets/css/owl.transitions.css"> 
        <link rel="stylesheet" href="assets/css/wizard.css"> 
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
</head>
<%
	KingManager km  = (KingManager) session.getAttribute("kingManager");
	User currUser = km.getCurUser(); 
	String firstName = ""; 
	String lastName = ""; 
	String fullName = ""; 
	String email = ""; 
	ArrayList<Action> currUserActions = new ArrayList<Action>();
	if(currUser != null){
		firstName = currUser.getFName(); 
		lastName = currUser.getLName(); 
		fullName = firstName + " " + lastName; 
		email = currUser.getEmail(); 
		currUserActions = km.getCurrUserActions();
	}
	
	
%>
<body>
        <div id="preloader">
            <div id="status">&nbsp;</div>
        </div>

        <nav class="navbar navbar-default ">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navigation">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.jsp"><img src="assets/img/KnowItAllLogo.JPG" alt=""></a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse yamm" id="navigation">
                    <div class="button navbar-right">
                        <button class="navbar-btn nav-button wow bounceInRight login" onclick=" window.location='login.jsp'" data-wow-delay="0.45s">Login/Sign Up</button>
                        <button class="navbar-btn nav-button wow fadeInRight poll" onclick=" window.location='createPollPage.jsp'" data-wow-delay="0.48s">Create Poll</button>
                        <button class="navbar-btn nav-button wow fadeInRight rating" onclick=" window.location='createEntityPage.jsp'" data-wow-delay="0.54s">Create Rating</button>
                        <button class="navbar-btn nav-button wow fadeInRight create-blog" onclick=" window.location='createBlogPage.jsp'" data-wow-delay="0.54s">Create Blog</button>
                        <button class="navbar-btn nav-button wow fadeInRight search" onclick=" window.location='feed.jsp'" data-wow-delay="0.59s">Search</button>
                        <button class="navbar-btn nav-button wow fadeInRight blog" onclick=" window.location='blogFeed.jsp'" data-wow-delay="0.59s">Blog</button>
                        <button class="navbar-btn nav-button wow fadeInRight user" onclick=" window.location='userPage.jsp'" data-wow-delay="0.59s">User Profile</button>
                        <button class="navbar-btn nav-button wow fadeInRight logout" onclick=" window.location='servlets/logout.jsp'" data-wow-delay="0.59s">Logout</button>
                    </div>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <!-- End of nav bar -->

        <div class="page-head"> 
            <div class="container">
                <div class="row">
                    <div class="page-head-content">
                    	<%
                    	if(fullName != null){
                    		%><h1 class="page-title">Hello  <span class="orange strong"><%= fullName %></span></h1>      
                    	<%}
                    	else {
                    		%><h1 class="page-title">Hello  <span class="orange strong">Guest</span></h1>      
                    	<%}
                    	%>
                                 
                    </div>
                </div>
            </div>
        </div>
        <!-- End page header --> 

        <!-- property area -->
        <div class="content-area user-profiel" style="background-color: #FCFCFC;">&nbsp;
            <div class="container"> 
            <%   
                if(firstName != "" && lastName != "" && email != "")
                {
                	%>
                	<h4><center>Your History</center></h4>
	            	<%
	            	if(currUserActions.size() == 0){
	            		%><center><p>You have no history. </p></center>
	            	<%}
	            	else
	            	{
	            		String content = null; 
	            		String title = null; 
	            		Entity entityWithURL = null; 
	            		Poll pollWithURL = null; 
	            		int pollID; 
	            		int entityID; 
	            		for(int i=0; i < currUserActions.size(); i++){
	            			int subjectID = currUserActions.get(i).getSubjectID(); 
		            		if(currUserActions.get(i) instanceof CommentAction){
				        		if(km.getEntity(subjectID) == null){
				        			title = km.getPoll(subjectID).getTitle();
				        			pollWithURL = km.getPollByTitle(title); 
				        			pollID = pollWithURL.getID(); 
				        			%><center><p>You commented on a poll titled: <strong><a href=<%="\" pollPage.jsp?subjectID=" + pollID + "\"" %>><%= title %></a></strong></p></center> <%
				        		} 
				        		else {
				        			title = km.getEntity(subjectID).getTitle(); 
				        			entityWithURL = km.getEntityByTitle(title); 
				        			entityID = entityWithURL.getID(); 
				        			%><center><p>You commented on a rating titled: <strong><a href=<%="\" entityPage.jsp?subjectID=" + entityID + "\"" %>><%= title %></a></strong></p></center> <%
				        		}
							}
				        	else if(currUserActions.get(i) instanceof PollAction){
				        		content = ((PollAction)currUserActions.get(i)).getOptionChosen();
				        		title = km.getPoll(subjectID).getTitle(); 
				        		pollWithURL = km.getPollByTitle(title); 
			        			pollID = pollWithURL.getID(); 
				        		%><center><p>You voted <strong><%= content %></strong> on a poll titled: <strong><a href=<%="\" pollPage.jsp?subjectID=" + pollID + "\"" %>><%= title %></a></strong></p></center> <%
				        		
							}
							else if(currUserActions.get(i) instanceof RatingAction){
								Boolean isUpVote = ((RatingAction)currUserActions.get(i)).isUpVote();
								System.out.println("isUpVote" + isUpVote); 
				        		title = km.getEntity(subjectID).getTitle(); 
				        		entityWithURL = km.getEntityByTitle(title); 
			        			entityID = entityWithURL.getID(); 
				        		if(isUpVote){
				        			%><center><p>You gave a thumbs up to a rating titled: <strong><a href=<%="\" entityPage.jsp?subjectID=" + entityID + "\"" %>><%= title %></a></strong></p></center> <%
				        		}
				        			
				        		else{
				        			%><center><p>You gave a thumbs down to a rating titled: <strong><a href=<%="\" entityPage.jsp?subjectID=" + entityID + "\"" %>><%= title %></a></strong></p></center> <%
				        		}
				        			
							}
	            			
	            		}
	            	}
            	%>
            	
            	<h4><center>Your Followers</center></h4>
            	<%
            	if(currUser == null || currUser.getFollowers().size() == 0){
            		%><center><p>You have no followers. </p></center>
            	<%}
            	else
            	{
            		for(int i=0; i < currUser.getFollowers().size(); i++){
            			%><center><p><%= currUser.getFollowers().get(i) %> </p></center> <%
            		}
            	}
            	%>
            	
            	<h4><center>Who You Follow</center></h4>
            	<%
            	if(currUser == null || currUser.getFollowing().size() == 0){
            		%><center><p>You are not following anyone. </p></center>
            	<%}
            	else
            	{
            		for(int i=0; i < currUser.getFollowing().size(); i++){
            			%><center><p><%= currUser.getFollowing().get(i) %> </p></center> <%
            		}
            	}
                } 
                else
                {
                	%><h4><center>Welcome, Guest. Please login or create an 
                	account to continue. </center></h4>
                	<br>
                	<center><button onclick=" window.location='login.jsp'">Login/Sign Up</button></center>
                <%}
            %>
                
        	</div>
    	</div>

        <script src="assets/js/vendor/modernizr-2.6.2.min.js"></script>
        <script src="assets/js//jquery-1.10.2.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/bootstrap-select.min.js"></script>
        <script src="assets/js/bootstrap-hover-dropdown.js"></script>
        <script src="assets/js/easypiechart.min.js"></script>
        <script src="assets/js/jquery.easypiechart.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/js/wow.js"></script>
        <script src="assets/js/icheck.min.js"></script>

        <script src="assets/js/price-range.js"></script> 
        <script src="assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>
        <script src="assets/js/jquery.validate.min.js"></script>
        <script src="assets/js/wizard.js"></script>

        <script src="assets/js/main.js"></script>
</body>
</html>