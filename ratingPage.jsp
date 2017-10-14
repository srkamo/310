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
	        <!-- Body content -->
	
	
	        <div class="header-connect">
	            <div class="container">
	                <div class="row">
	                    <div class="col-md-5 col-sm-8  col-xs-12">
	                        <div class="header-half header-call">
	                            <p>
	                                <span><i class="pe-7s-call"></i> +1 234 567 7890</span>
	                                <span><i class="pe-7s-mail"></i> your@company.com</span>
	                            </p>
	                        </div>
	                    </div>
	                    <div class="col-md-2 col-md-offset-5  col-sm-3 col-sm-offset-1  col-xs-12">
	                        <div class="header-half header-social">
	                            <ul class="list-inline">
	                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
	                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
	                                <li><a href="#"><i class="fa fa-vine"></i></a></li>
	                                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
	                                <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
	                                <li><a href="#"><i class="fa fa-instagram"></i></a></li>
	                            </ul>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>            
	        <!--End top header -->
	
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
	                    <a class="navbar-brand" href="index.html"><img src="assets/img/KnowItAllLogo.JPG" alt=""></a>
	                </div>
	
	                <!-- Collect the nav links, forms, and other content for toggling -->
	                <div class="collapse navbar-collapse yamm" id="navigation">
	                    <div class="button navbar-right">
	                        <button class="navbar-btn nav-button wow bounceInRight login" onclick=" window.open('login.jsp')" data-wow-delay="0.4s">Login</button>
	                        <button class="navbar-btn nav-button wow fadeInRight" onclick=" window.open('createPollPage.jsp')" data-wow-delay="0.5s">Create Poll</button>
	                        <button class="navbar-btn nav-button wow fadeInRight" onclick=" window.open('createRatingPage.jsp')" data-wow-delay="0.5s">Create Rating</button>
	                    </div>
	                    <ul class="main-nav nav navbar-nav navbar-right">
							<li class="wow fadeInDown" data-wow-delay="0.4s"><a href="feed.jsp">Home</a></li>
	                        <li class="wow fadeInDown" data-wow-delay="0.4s"><a href="contact.html">Contact</a></li>
	                    </ul>
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
	
	        <!-- Footer area-->
	        <div class="footer-area">
	
	            <div class=" footer">
	                <div class="container">
	                    <div class="row">
	
	                        <div class="col-md-3 col-sm-6 wow fadeInRight animated">
	                            <div class="single-footer">
	                                <h4>About us </h4>
	                                <div class="footer-title-line"></div>
	
	                                <img src="assets/img/KnowItAllLogo.JPG" alt="" class="wow pulse" data-wow-delay="1s">
	                                <p>Lorem ipsum dolor cum necessitatibus su quisquam molestias. Vel unde, blanditiis.</p>
	                                <ul class="footer-adress">
	                                    <li><i class="pe-7s-map-marker strong"> </i> 9089 your adress her</li>
	                                    <li><i class="pe-7s-mail strong"> </i> email@yourcompany.com</li>
	                                    <li><i class="pe-7s-call strong"> </i> +1 908 967 5906</li>
	                                </ul>
	                            </div>
	                        </div>
	                        <div class="col-md-3 col-sm-6 wow fadeInRight animated">
	                            <div class="single-footer">
	                                <h4>Quick links </h4>
	                                <div class="footer-title-line"></div>
	                                <ul class="footer-menu">
	                                    <li><a href="properties.html">Properties</a>  </li> 
	                                    <li><a href="#">Services</a>  </li> 
	                                    <li><a href="submit-property.html">Submit property </a></li> 
	                                    <li><a href="contact.html">Contact us</a></li> 
	                                    <li><a href="faq.html">fqa</a>  </li> 
	                                    <li><a href="faq.html">Terms </a>  </li> 
	                                </ul>
	                            </div>
	                        </div>
	                        <div class="col-md-3 col-sm-6 wow fadeInRight animated">
	                            <div class="single-footer">
	                                <h4>Last News</h4>
	                                <div class="footer-title-line"></div>
	                                <ul class="footer-blog">
	                                    <li>
	                                        <div class="col-md-3 col-sm-4 col-xs-4 blg-thumb p0">
	                                            <a href="single.html">
	                                                <img src="assets/img/demo/small-proerty-2.jpg">
	                                            </a>
	                                            <span class="blg-date">12-12-2016</span>
	
	                                        </div>
	                                        <div class="col-md-8  col-sm-8 col-xs-8  blg-entry">
	                                            <h6> <a href="single.html">Add news functions </a></h6> 
	                                            <p style="line-height: 17px; padding: 8px 2px;">Lorem ipsum dolor sit amet, nulla ...</p>
	                                        </div>
	                                    </li> 
	
	                                    <li>
	                                        <div class="col-md-3 col-sm-4 col-xs-4 blg-thumb p0">
	                                            <a href="single.html">
	                                                <img src="assets/img/demo/small-proerty-2.jpg">
	                                            </a>
	                                            <span class="blg-date">12-12-2016</span>
	
	                                        </div>
	                                        <div class="col-md-8  col-sm-8 col-xs-8  blg-entry">
	                                            <h6> <a href="single.html">Add news functions </a></h6> 
	                                            <p style="line-height: 17px; padding: 8px 2px;">Lorem ipsum dolor sit amet, nulla ...</p>
	                                        </div>
	                                    </li> 
	
	                                    <li>
	                                        <div class="col-md-3 col-sm-4 col-xs-4 blg-thumb p0">
	                                            <a href="single.html">
	                                                <img src="assets/img/demo/small-proerty-2.jpg">
	                                            </a>
	                                            <span class="blg-date">12-12-2016</span>
	
	                                        </div>
	                                        <div class="col-md-8  col-sm-8 col-xs-8  blg-entry">
	                                            <h6> <a href="single.html">Add news functions </a></h6> 
	                                            <p style="line-height: 17px; padding: 8px 2px;">Lorem ipsum dolor sit amet, nulla ...</p>
	                                        </div>
	                                    </li> 
	
	
	                                </ul>
	                            </div>
	                        </div>
	                        <div class="col-md-3 col-sm-6 wow fadeInRight animated">
	                            <div class="single-footer news-letter">
	                                <h4>Stay in touch</h4>
	                                <div class="footer-title-line"></div>
	                                <p>Lorem ipsum dolor sit amet, nulla  suscipit similique quisquam molestias. Vel unde, blanditiis.</p>
	
	                                <form>
	                                    <div class="input-group">
	                                        <input class="form-control" type="text" placeholder="E-mail ... ">
	                                        <span class="input-group-btn">
	                                            <button class="btn btn-primary subscribe" type="button"><i class="pe-7s-paper-plane pe-2x"></i></button>
	                                        </span>
	                                    </div>
	                                    <!-- /input-group -->
	                                </form> 
	
	                                <div class="social pull-right"> 
	                                    <ul>
	                                        <li><a class="wow fadeInUp animated" href="https://twitter.com/kimarotec"><i class="fa fa-twitter"></i></a></li>
	                                        <li><a class="wow fadeInUp animated" href="https://www.facebook.com/kimarotec" data-wow-delay="0.2s"><i class="fa fa-facebook"></i></a></li>
	                                        <li><a class="wow fadeInUp animated" href="https://plus.google.com/kimarotec" data-wow-delay="0.3s"><i class="fa fa-google-plus"></i></a></li>
	                                        <li><a class="wow fadeInUp animated" href="https://instagram.com/kimarotec" data-wow-delay="0.4s"><i class="fa fa-instagram"></i></a></li>
	                                        <li><a class="wow fadeInUp animated" href="https://instagram.com/kimarotec" data-wow-delay="0.6s"><i class="fa fa-dribbble"></i></a></li>
	                                    </ul> 
	                                </div>
	                            </div>
	                        </div>
	
	                    </div>
	                </div>
	            </div>
	
	            <div class="footer-copy text-center">
	                <div class="container">
	                    <div class="row">
	                        <div class="pull-left">
	                            <span> (C) <a href="http://www.KimaroTec.com">KimaroTheme</a> , All rights reserved 2016  </span> 
	                        </div> 
	                        <div class="bottom-menu pull-right"> 
	                            <ul> 
	                                <li><a class="wow fadeInUp animated" href="#" data-wow-delay="0.2s">Home</a></li>
	                                <li><a class="wow fadeInUp animated" href="#" data-wow-delay="0.3s">Property</a></li>
	                                <li><a class="wow fadeInUp animated" href="#" data-wow-delay="0.4s">Faq</a></li>
	                                <li><a class="wow fadeInUp animated" href="#" data-wow-delay="0.6s">Contact</a></li>
	                            </ul> 
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