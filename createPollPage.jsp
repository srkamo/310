<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>KnowItAll | Create Poll Page</title>
<meta name="description" content="GARO is a real-estate template">
<meta name="author" content="Kimarotec">
<meta name="keyword"
	content="html5, css, bootstrap, property, real-estate theme , bootstrap template">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800'
	rel='stylesheet' type='text/css'>

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="icon" href="favicon.ico" type="image/x-icon">

<link rel="stylesheet" href="assets/css/normalize.css">
<link rel="stylesheet" href="assets/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/fontello.css">
<link href="assets/fonts/icon-7-stroke/css/pe-icon-7-stroke.css"
	rel="stylesheet">
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

	<%
		//error messages for creating a poll
		String errorMessageCreatePoll = "";
		errorMessageCreatePoll = (String) session.getAttribute("errorMessageCreatePoll"); 
		if(errorMessageCreatePoll == null){
			errorMessageCreatePoll = ""; 
		}
	      	
		//reset error message
		session.setAttribute("errorMessageCreatePoll", "");
	%>

</head>
<body>
	<div id="preloader">
		<div id="status">&nbsp;</div>
	</div>

	<nav class="navbar navbar-default ">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navigation">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp"><img
				src="assets/img/KnowItAllLogo.JPG" alt=""></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse yamm" id="navigation">
			<div class="button navbar-right">
				<button class="navbar-btn nav-button wow bounceInRight login" onclick=" window.location='login.jsp'" data-wow-delay="0.45s">Login/Sign Up</button>
                        <button class="navbar-btn nav-button wow fadeInRight poll" onclick=" window.location='createPollPage.jsp'" data-wow-delay="0.48s">Create a Poll</button>
                        <button class="navbar-btn nav-button wow fadeInRight rating" onclick=" window.location='createEntityPage.jsp'" data-wow-delay="0.54s">Create a Rating</button>
                        <button class="navbar-btn nav-button wow fadeInRight search" onclick=" window.location='feed.jsp'" data-wow-delay="0.59s">Search</button>
                        <button class="navbar-btn nav-button wow fadeInRight blog" onclick=" window.location='blog.html'" data-wow-delay="0.59s">Blog</button>
                        <button class="navbar-btn nav-button wow fadeInRight user" onclick=" window.location='userPage.jsp'" data-wow-delay="0.59s">User Profile</button>
                        <button class="navbar-btn nav-button wow fadeInRight logout" onclick=" window.location='servlets/logout.jsp'" data-wow-delay="0.59s">Logout</button>
			</div>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<!-- End of nav bar -->

	<div class="page-head">
		<div class="container">
			<div class="row">
				<div class="page-head-content">
					<h1 class="page-title">Submit new poll</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- End page header -->

	<!-- property area -->
	<div class="content-area submit-property"
		style="background-color: #FCFCFC;">
		&nbsp;
		<div class="container">
			<div class="clearfix">
				<div class="wizard-container">

					<div class="wizard-card ct-wizard-orange" id="wizardProperty">
						
							<div class="wizard-header">
								<h3>
									<b>Submit</b> NEW POLL <br> <small>Create a poll
										to ask about classes, restaurants, or anything you'd like to
										know.</small>
								</h3>
							</div>

							<h4 class="info-text" name="errorBox" style="color: red;"><%=errorMessageCreatePoll %></h4>


							<form action="servlets/createPoll.jsp" method="post" name="createPollForm">
                                <div class="form-group">
                                    <label>Poll Title <small>(required)</small></label>
                                     <input name="title" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Poll Tags (separated by commas) <small>(required)</small>
									</label> <input name="tags" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Poll Option 1 <small>(required)</small></label> 
                                    <input name="option1" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Poll Option 2 <small>(required)</small></label> 
                                    <input name="option2" type="text" class="form-control">
                                </div>
                                
                                <div class="form-group add-option">
                                	<button>Add Option</button>
                                </div>
                                
                                <div class="form-group">
	                                <label>Is Your Poll Open Forever? <small>(required)</small> </label> 
	                                <input name="checkbox" type="checkBox" class="form-control">
	                                <br>
                                </div>
                                
                                <div class="form-group">
                                	 <label>Poll End Date <small>(required)</small></label> 
                                	 <input name="date" type="text" class="form-control" placeholder="DD/MM/YYYY">
                                </div>
                                
                                <div class="form-group">
                                 	<label for="property-images">Choose an Image: <small>(input a URL)</small></label>
                              		<input name="image" type="text" id="property-images">
                                	<br>
                                </div>
                                
                                <div class="form-group">
	                                <label>Would you like your poll to be anonymous? <small>(if so, check the box below)</small> </label> 
	                                <input name="anon-checkbox" type="checkBox" class="form-control">
	                                <br>
                                </div>
                                
                                <div class="text-center">
                                    <button type="submit" class="btn btn-default">Submit Poll</button>
                                </div>
                            </form>
							
							
			</div>
		</div>
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
	<script src="assets/js/jquery.bootstrap.wizard.js"
		type="text/javascript"></script>
	<script src="assets/js/jquery.validate.min.js"></script>
	<script src="assets/js/wizard.js"></script>

	<script src="assets/js/main.js"></script>
	<script src="assets/js/createPollPage.js"></script>
</body>
</html>