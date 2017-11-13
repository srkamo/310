<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="Business.KingManager" %>
<%@ page import="Business.Poll" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@page import="java.util.GregorianCalendar" %>
<%@page import="java.util.Calendar" %>
<%@ page import="Business.Action" %>
<%@ page import="Business.CommentAction" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>KnowItAll | Poll Page</title>
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
	km.addPollView(subjectIDint);
	
	if(km.isPollExpired(subjectIDint)){ 
		System.out.println("Poll is expired. time for JS");
	}

		Poll currPoll = (Poll) km.getPoll(subjectIDint);
				
		ArrayList<CommentAction> Comments = currPoll.getComments();
		
		String title = currPoll.getTitle(); 
	    ArrayList<String> tags = currPoll.getTags(); 
	    ArrayList<String> options = currPoll.getOptions(); 
	    HashMap<String,Integer> results = currPoll.getPollResults(); 
	    String image = currPoll.getImage(); 
	    Calendar cal = currPoll.getTimeEnd();
	    int month=cal.get(Calendar.MONTH)+1;
	    String timeEnd = month + "/" + cal.get(Calendar.DATE) + "/" +cal.get(Calendar.YEAR);
	    Boolean openForever = currPoll.isInfinite();
	    String creator = currPoll.getCreator();
	    Boolean creatorIsAnon = currPoll.creatorIsAnon();

	    
	    //error messages for rating an entity without being logged in
		String errorMessageVoteOnPoll = "";
		errorMessageVoteOnPoll = (String) session.getAttribute("errorMessageVoteOnPoll"); 
	    		session.setAttribute("errorMessageVoteOnPoll", "");
		if(errorMessageVoteOnPoll == null){
			errorMessageVoteOnPoll = ""; 
		}
		
		//error messages for commenting without being logged in
		String errorMessageComment = "";
		errorMessageComment = (String) session.getAttribute("errorMessageComment"); 
		session.setAttribute("errorMessageComment", "");
		if(errorMessageComment == null){
			errorMessageComment = ""; 
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
                        <h1 class="page-title"><%= title %></h1>               
                    </div>
                </div>
            </div>
        </div>
        <!-- End page header -->

        <!-- Main area -->
        <div class="content-area single-property" style="background-color: #FCFCFC;">
            <div class="container">
            
           		<h2><center>POLL: <%= title %></center></h2>
           		<h4 class="info-text" style="color: red;" id="errorMessageVoteOnPoll"><center><%=errorMessageVoteOnPoll %></h4></center>
           		<h4 class="info-text" style="color: red;" id="errorMessageComment"><center><%=errorMessageComment %></h4></center>
	       		<br>
	       		<div class="imagespot">
					<center><img src="<%=image %>"/></center>
				</div>	
				
				<h4><center>What do you think?  Vote now! </center></h4>
				<p><center>Select the option you most agree with, and then click submit. </center></p>
				
				
				<center><form action="servlets/voteOnPoll.jsp" name="submitVoteForm">
                	<%
                    for(int i=0; i<options.size(); i++){
           	    		String option = options.get(i);
            		%>
                   		<input type="radio" name="vote" value="<%=option %>" checked> <%=option %><br>
                    <%
                    }
                    %>
                    <input type="hidden" name="id" value=<%=subjectIDint %>/>
                    <input type="submit" value="Submit" style="width:600px">
				</form></center>
				
				<h4 id="scores"><center>Current Scores: <%= results %></center></h4>
				<br>
					
				<h4><center>Leave a comment on this poll with your own opinions. </center></h4>
                                
                <div id="form-div">
					<form action="servlets/comment.jsp">
						<center><textarea name="commentTextbox" style="width:600px"></textarea></center>
						<input type="hidden" name="id" value=<%=subjectIDint %>/>
						<input type="hidden" name="subjectType" value="Poll"/>
						<center><input type="submit" value="Add Comment" style="width:600px"></center>
						<center>Check this box if you would like to 
						leave your comment anonymously: <input type="checkBox"  name="checkbox"></center>
					</form>
				</div>
						<h4><Center>Comments: </Center></h4>
						
						<%
						for(int i = 0; i<Comments.size(); i++){
							// edit comment
							if(km.getCurUser() != null){
								if(km.getCurUser().getEmail().equalsIgnoreCase( Comments.get(i).getUser())){
									%>
										<p id="comment"> <a href=<%="\" editCommentPoll.jsp?subjectID=" + Comments.get(i).getSubjectID()  + "&curComment=" + Comments.get(i).getContent() + "\""%> >Edit</a>
									<% 
								}
				
							}
							
							
							//comment not anon
							if(!Comments.get(i).getIsAnon()){
								%>
								<p id="comment"><a href=<%="\" publicUserPage.jsp?email=" + Comments.get(i).getUser() + "\""%> ><%=Comments.get(i).getUser() %></a>: <strong><%= Comments.get(i).getContent() %></strong></p>
								
								<%
							}
							//comment anonymous
							else{
								%>
								<p id="comment">Anonymous: <strong><%= Comments.get(i).getContent() %></strong></p>
								
								<%
							}
						}
						%>
				<br>
				<br>			
								
				<%-- <center><div class="section">
                	<h4 class="s-property-title">Options</h4>
                    <div class="s-property-content">
	                	<ul>
	                    <%
	                  		for (int i=0; i < options.size(); i ++){
	                     		out.println("<li>"+options.get(i)+"</li>"); 
	              			}
	                 	%>
	                    </ul>
                	</div>
             	</div></center> --%>
                                     
                <center><h4 class="tags">Tags: </h4>
	                <%
	                	for (int i=0; i < tags.size(); i ++){
	   	           			out.print(tags.get(i)+", "); 
	   	            	}
	            	%></center>
	            	
	            <br>
				<br>
				
				<h4 id="lifespan"><center>Poll Lifespan: </center></h4>
	                <%
	                	String lifespan = "";  
	                	if(openForever)
	                	{
	                		lifespan+=" forever";
	                	} 
	                	else
	                	{
	                		lifespan+=" until " + timeEnd; 
	                	}
	                %>
	            <p><center>This poll will be open <%= lifespan %>.</center></p>
	            
	            <%
	            	//only display delete button if curr user is the creator of the poll
	            	if(km.getCurUser() != null){
	            	if(creator.equals(km.getCurUser().getEmail())){
	            %>		
	            		<a href=<%="\" servlets/deletePollEntity.jsp?type=poll&subjectID=" + subjectIDint + "\""%> >
            				<center><button style="width:200px; "height=200px;">Delete Poll</button></center>
            			</a>
	            <% 		
	            	}
	            	}
	            %>
	            
	            
	            <br>
				<br>
				<%
				if(creatorIsAnon == false){ %>
					<h4 id="creator"><center>Created by: <a href=<%="\" publicUserPage.jsp?email=" + creator + "\""%> ><%= creator %></a></center></h4>
				<%} 
				else {
					%><h4 id="creator"><center>Created by: Anonymous</center></h4>
				<%}
				%>
				
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