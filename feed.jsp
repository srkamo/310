<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Database.Database" %>
<%@ page import="Business.User" %>
<%@ page import="Business.KingManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Business.Entity" %>
<%@ page import="Business.Poll" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KnowItAll Home | Feed</title>
<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
        <link href="assets/css/animate.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" href="assets/css/bootstrap-select.min.css"> 
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/icheck.min_all.css">
        <link rel="stylesheet" href="assets/css/price-range.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.css">  
        <link rel="stylesheet" href="assets/css/owl.theme.css">
        <link rel="stylesheet" href="assets/css/owl.transitions.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
</head>
<%
	KingManager km  = (KingManager) session.getAttribute("kingManager");
	
    ArrayList<Object> allItems = km.getAllItemsDisplayed();
    
  	//error messages for rating an entity without being logged in
  	String errorMessageRateEntity = "";
  	errorMessageRateEntity = (String) session.getAttribute("errorMessageRateEntity"); 
  	if(errorMessageRateEntity == null){
  		errorMessageRateEntity = ""; 
  	}
  	
  	//reset error messages
  	session.setAttribute("errorMessageRateEntity", "");
        
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
        
                        
        
        <!--  BEGINNING OF FEED -- Grab from KingManager  -->

        <!-- property area -->
        <div class="content-area home-area-1 recent-property" style="background-color: #FCFCFC; padding-bottom: 55px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title">
                        <!-- /.feature title -->
                        <h2>Top submitted ratings and polls</h2>
                        <p>Search through the ratings and polls your fellow Trojans are looking at right now.  </p>
                        <h4 class="info-text" style="color: red;"><center><%=errorMessageRateEntity %></h4></center>
                    </div>
                </div>
                
                <center><div style="width:900px;" >

               		<form action="servlets/searchButton.jsp" class=" form-inline" name="searchform">
                    	<div style="width:600px;">
                        	<input type="text" class="form-control" placeholder="Search for USC-related campus content and the opinions of your fellow Trojans" name="searchTextBar">
                        </div>
 
                   		<button class="btn search-btn" type="submit"><i class="fa fa-search" ></i></button>

                      	<div style="display: none;" class="search-toggle">                              
                       		<button class="btn search-btn prop-btm-sheaerch" type="submit"><i class="fa fa-search"></i></button>  
                        </div>
                	
                	  <select name="filter">
 									<option value="everything" selected>Everything</option>
  									<option value="entities">Entities</option>
  									<option value="polls">Polls</option>
								</select>
                	</form>
                </div></center>
                
                <div class="row">
                <div class="proerty-th">
                
                <%
		        int i; 
                int id = -1;
		        String type = null;  
		        String title = null; 
		        String image = null; 
		        for(i=0; i < allItems.size(); i++){
		        	if(allItems.get(i) instanceof Entity){
		        		id = ((Entity)allItems.get(i)).getID(); 
		        		type = "Entity"; 
		        		title = ((Entity)allItems.get(i)).getTitle(); 
		        		image = ((Entity)allItems.get(i)).getImage(); 
					}
		        	else if(allItems.get(i) instanceof Poll){
		        		id = ((Poll)allItems.get(i)).getID();
		        		type = "Poll"; 
		        		title = ((Poll)allItems.get(i)).getTitle(); 
		        		image = ((Poll)allItems.get(i)).getImage(); 
					}
		        	
		        	if(type == "Entity"){
		        	%>
		        	
			        	<div class="col-sm-6 col-md-3 p0">
		                    <div class="box-two proerty-item">
		                        <div class="item-thumb">
		                            <a  href=<%="\" entityPage.jsp?subjectID=" + id + "\"" %>>
		                            	<img height="357.5" width="265" src="<%= image %>" />
		                            </a>
		                        </div>
		                        <div class="item-entry overflow">
		                            <h5 name="titleHeading"><a href="title"><%= title %></a></h5>
		                            <div class="dot-hr"></div>
		                            <%
		                            if(type=="Entity") { %>
		                            	<span class="type"><%= type %></span>
		                            	<a href=<%="\" servlets/rateEntity.jsp?vote='upVote'&id=" + id + "\""%> >
		                            		<button style="float: right; margin: 2px; ">UpVote</button>
		                            	</a>
		                            	<a href=<%="\" servlets/rateEntity.jsp?vote='downVote'&id=" + id + "\""%> >
		                            		<button style="float: right; margin: 2px; ">DownVote</button>
		                            	</a>
		                              	<%
		                            }
		                            	%>
		                        </div>
		                    </div>
	                	</div>
	                
	                <%
	                
		        	}
		        	else if (type == "Poll"){
		        		
		        	%>
		        		<div class="col-sm-6 col-md-3 p0">
		                    <div class="box-two proerty-item">
		                        <div class="item-thumb">
		                           <a  href=<%="\" pollPage.jsp?subjectID=" + id + "\"" %>>
		                           	<img height="357.5" width="265" src="<%= image %>" />
		                           </a>
		                        </div>
		                        <div class="item-entry overflow">
		                            <h5><%= title %></h5>
		                            <div class="dot-hr"></div>
		                            <span class="type"><%= type %></span>
		                        </div>
		                    </div>
	                	</div>
		        	
		        	<%
		        	}
		        }
		       
		        %>


                    </div>
                </div>
            </div>
        </div>

        <script src="assets/js/modernizr-2.6.2.min.js"></script>

        <script src="assets/js/jquery-1.10.2.min.js"></script> 
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/bootstrap-select.min.js"></script>
        <script src="assets/js/bootstrap-hover-dropdown.js"></script>

        <script src="assets/js/easypiechart.min.js"></script>
        <script src="assets/js/jquery.easypiechart.min.js"></script>

        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/js/wow.js"></script>

        <script src="assets/js/icheck.min.js"></script>
        <script src="assets/js/price-range.js"></script>

        <script src="assets/js/main.js"></script>
</body>
</html>