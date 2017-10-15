<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="Business.KingManager" %>
<%@ page import="Business.Entity" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<meta charset="utf-8">
	        <meta http-equiv="X-UA-Compatible" content="IE=edge">
	        <title>KnowItAll | Rating Page</title>
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
	        <link rel="stylesheet" href="assets/css/lightslider.min.css">
	        <link rel="stylesheet" href="assets/css/style.css">
	        <link rel="stylesheet" href="assets/css/responsive.css">
	</head>
	<%
 		KingManager km  = (KingManager) session.getAttribute("kingManager");
		String subjectID = request.getParameter("subjectID"); 
		int subjectIDint = Integer.parseInt(subjectID); 
		
		if(km.isEntityExpired(subjectIDint)){
			System.out.println("Entity is closed");
			
		}
			Entity currEntity = (Entity) km.getEntity(subjectIDint);
			String title = currEntity.getTitle(); 
		    String description = currEntity.getDescription(); 
		    ArrayList<String> tags = currEntity.getTags(); 
		    int rating = currEntity.getRating(); 
		    String image = currEntity.getImage(); 
		    km.addEntityView(subjectIDint);
		
		
	
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
	
	        <div class="page-head"> 
	            <div class="container">
	                <div class="row">
	                    <div class="page-head-content">
	                        <h1 class="page-title"><%= title %></h1>               
	                    </div>
	                </div>
	            </div>
	        </div>
	        <!-- End page header -->
	        
	        <!-- property area -->
	        <div class="content-area single-property" style="background-color: #FCFCFC;">
	            <div class="container">
	
	                <div class="clearfix padding-top-40">
	                    <div class="col-md-8 single-property-content ">
	                        <div class="row">
	                            <div class="light-slide-item">            
	                                <div class="clearfix">
	                                    <div class="favorite-and-print">
	                                        <a class="add-to-fav" href="#login-modal" data-toggle="modal">
	                                            <i class="fa fa-star-o"></i>
	                                        </a>
	                                        <a class="printer-icon " href="javascript:window.print()">
	                                            <i class="fa fa-print"></i> 
	                                        </a>
	                                    </div> 
	
	                                    <ul id="image-gallery" class="gallery list-unstyled cS-hidden">
	                                        <li data-thumb="<%=image %>"> 
	                                            <img src="<%= image %>" />
	                                        </li>
	                                    </ul>
	                                </div>
	                            </div>
	                        </div>
	
	                        <div class="single-property-wrapper">
	                            <div class="single-property-header">                                          
	                                <h1 class="property-title pull-left"><%= title %></h1>
		                                <a href=<%="\" servlets/rateEntity.jsp?vote='upVote'&id=" + subjectIDint + "\""%> >
		                                	<input style="display: inline-block" class="btn btn-finish btn-primary" type="button" value='Up Vote'/>
		                                </a>
		                                <a href=<%="\" servlets/rateEntity.jsp?vote='downVote'&id=" + subjectIDint + "\""%> >
		                                	<input style="display: inline-block" class="btn btn-finish btn-primary" type="button" value='Down Vote'/>
		                                </a>
		                                <form action="servlets/comment.jsp">
											<textarea name="commentTextbox"></textarea>
											<input type="hidden" name="id" value=<%=subjectIDint %>/>
											<input type="hidden" name="subjectType" value="Entity"/>
											<input type="submit" value="Add Comment">
											Anonymous <input type="checkBox"  name="checkbox">
										</form>
	                            </div>
	                            
	                            <!-- .property-meta -->
	
	                            <div class="section">
	                                <h4 class="s-property-title">Description</h4>
	                                <div class="s-property-content">
	                                    <p><%= description %></p>
	                                </div>
	                            </div>
	                            <!-- End description area  -->
	
	
	                            <div class="section property-features">      
	
	                                <h4 class="tags">Tags</h4> 
	                                <ul>
	                                <%
	                                	for (int i=0; i < tags.size(); i ++){
	                                		out.println("<li>"+tags.get(i)+"</li>"); 
	                                	}
	                                %>
	                                </ul>
	
	                            </div>
	                            <!-- End features area  -->
	                        </div>
	                    </div>
	                </div>
	
	            </div>
	        </div>
	
	        <script src="assets/js/vendor/modernizr-2.6.2.min.js"></script>
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
	        <script type="text/javascript" src="assets/js/lightslider.min.js"></script>
	        <script src="assets/js/main.js"></script>
	
	        <script>
	                            $(document).ready(function () {
	
	                                $('#image-gallery').lightSlider({
	                                    gallery: true,
	                                    item: 1,
	                                    thumbItem: 9,
	                                    slideMargin: 0,
	                                    speed: 500,
	                                    auto: true,
	                                    loop: true,
	                                    onSliderLoad: function () {
	                                        $('#image-gallery').removeClass('cS-hidden');
	                                    }
	                                });
	                            });
	        </script>
	</body>
</html>