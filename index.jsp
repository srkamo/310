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
<title>KnowItAll Home</title>
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
	KingManager km;
	//if KingManager doesn't exist yet (first entry of website), create it
	if(session.getAttribute("kingManager") == null){
		km = new KingManager();
	}
	//if just going back to home page, get king manager
	else{
		km = (KingManager)session.getAttribute("kingManager");
	}

	km.search("");
	session.setAttribute("kingManager", km);
    ArrayList<Object> allItems = km.getAllItemsDisplayed();
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
                        <button class="navbar-btn nav-button wow fadeInRight poll" onclick=" window.location='createPollPage.jsp'" data-wow-delay="0.48s">Create a Poll</button>
                        <button class="navbar-btn nav-button wow fadeInRight rating" onclick=" window.location='createEntityPage.jsp'" data-wow-delay="0.54s">Create a Rating</button>
                        <button class="navbar-btn nav-button wow fadeInRight search" onclick=" window.location='feed.jsp'" data-wow-delay="0.59s">Search</button>
                        <button class="navbar-btn nav-button wow fadeInRight home" onclick=" window.location='index.jsp'" data-wow-delay="0.59s">Home</button>
                    </div>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <!-- End of nav bar -->

        <div class="slider-area">
            <div class="slider">
                <div id="bg-slider" class="owl-carousel owl-theme">

                    <div class="item"><img src="assets/img/palmtrees.jpg" alt="USC1"></div>
                    <div class="item"><img src="assets/img/USC2.jpg" alt="USC2"></div>
                    <div class="item"><img src="assets/img/USC3.jpg" alt="USC3"></div>

                </div>
            </div>
            <div class="slider-content">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
                        <h2><span>Knowing It All Just Got So Easy</span></h2>
                        <div class="search-form wow pulse" data-wow-delay="0.8s">

                            <form action="servlets/searchButton.jsp" class=" form-inline">

                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Search for USC-related campus content and the opinions of your fellow Trojans" name="searchTextBar">
                                </div>
                                
                                <button class="btn search-btn" type="submit"><i class="fa fa-search" ></i></button>

                                <div style="display: none;" class="search-toggle">                              
                                	<button class="btn search-btn prop-btm-sheaerch" type="submit"><i class="fa fa-search"></i></button>  
                                </div>                    
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        
        <!--  BEGINNING OF FEED -- Grab from KingManager  -->

        <!-- property area -->
        <div class="content-area home-area-1 recent-property" style="background-color: #FCFCFC; padding-bottom: 55px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title">
                        <!-- /.feature title -->
                        <h2>Top submitted ratings and polls</h2>
                        <p>Search through the ratings and polls your fellow Trojans are looking at right now.  </p>
                    </div>
                </div>
                
                <div class="row">
                <div class="proerty-th">
                
                <%
		        int i; 
		        int id = -1; 
                String type = null;  
		        String title = null; 
		        String image = null; 
		        for(i=0; i < allItems.size(); i++){
		        	if(i > 7)
		        		break; 
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
		                            	<img src="<%= image %>" />
		                            </a>
		                            
		                        </div>
		                        <div class="item-entry overflow">
		                            <h5><a href="title" ><%= title %></a></h5>
		                            <div class="dot-hr"></div>
		                            <span class="type"><%= type %></span>
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
		                           	<img src="<%= image %>" />
		                           </a>
		                        </div>
		                        <div class="item-entry overflow">
		                            <h5><a href="title" ><%= title %></a></h5>
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

        <!--Welcome area -->
        <div class="Welcome-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 Welcome-entry  col-sm-12">
                        <div class="col-md-5 col-md-offset-2 col-sm-6 col-xs-12">
                            <div class="welcome_text wow fadeInLeft" data-wow-delay="0.3s" data-wow-offset="100">
                                <div class="row">
                                    <div class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title">
                                        <!-- /.feature title -->
                                        <h2>KNOW IT ALL</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5 col-sm-6 col-xs-12">
                            <div  class="welcome_services wow fadeInRight" data-wow-delay="0.3s" data-wow-offset="100">
                                <div class="row">
                                    <div class="col-xs-6 m-padding">
                                        <div class="welcome-estate">
                                            <div class="welcome-icon">
                                                <i class="pe-7s-home pe-4x"></i>
                                            </div>
                                            <h3>Resource Bank</h3>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 m-padding">
                                        <div class="welcome-estate">
                                            <div class="welcome-icon">
                                                <i class="pe-7s-users pe-4x"></i>
                                            </div>
                                            <h3>All Trojans</h3>
                                        </div>
                                    </div>


                                    <div class="col-xs-12 text-center">
                                        <i class="welcome-circle"></i>
                                    </div>

                                    <div class="col-xs-6 m-padding">
                                        <div class="welcome-estate">
                                            <div class="welcome-icon">
                                                <i class="pe-7s-notebook pe-4x"></i>
                                            </div>
                                            <h3>Easy to use</h3>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 m-padding">
                                        <div class="welcome-estate">
                                            <div class="welcome-icon">
                                                <i class="pe-7s-help2 pe-4x"></i>
                                            </div>
                                            <h3>Any help </h3>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--TESTIMONIALS -->
        <div class="testimonial-area recent-property" style="background-color: #FCFCFC; padding-bottom: 15px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title">
                        <!-- /.feature title -->
                        <h2>What Your Fellow Trojans Are Saying...  </h2> 
                    </div>
                </div>

                <div class="row">
                    <div class="row testimonial">
                        <div class="col-md-12">
                            <div id="testimonial-slider">
                                <div class="item">
                                    <div class="client-text">                                
                                        <p>KnowItAll helped me find the best classes for my interests. I love
                                        listening to the opinions of my fellow Trojans; it really helps 
                                        me decide the best direction for me in terms of my education. </p>
                                        <h4><strong>Michael, </strong><i>Aerospace Engineering Major</i></h4>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="client-text">                                
                                        <p>With KnowItAll, I can easily post questions about any topic I want to (ranging 
                                        from pizza to opinions on classes and professors!) and receive open, honest feedback.</p>
                                        <h4><strong>Michelle, </strong><i>Electrical Engineering Major</i></h4>
                                     
                                    </div>
                                    
                                </div>
                                <div class="item">
                                    <div class="client-text">                                
                                        <p>Thanks to KnowItAll, I was able to take the best classes offered for my general 
                                        education requirements and learn about new topics I would not have otherwise learned. </p>
                                        <h4><strong>Shana,</strong><i>Mechanical Engineering Major</i></h4>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="client-text">                                
                                        <p>KnowItAll is great. Where else could I post a picture of my cat in her 
                                        Halloween costume and receive honest feedback about her cuteness?
                                        Plus, I love asking for opinions on food (one of my main priorities in life). </p>
                                        <h4><strong>Sophie, </strong><i>Chemical Engineering Major</i></h4>
                                    </div>
                                </div>
                            </div>
                        </div>
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