<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Event"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
		if(session.getAttribute("username") != null){
			String username = session.getAttribute("username").toString();
		}
%>

<html lang="en">
<head>
<title>Events Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js\sc.js" ></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="Header.jsp" />
<div class="container text-center">    
  <div class="row">
    <div class="col-sm-3 well">
      <div class="alert alert-success fade in">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">�</a>
        <p><strong>Hi! ${sessionScope.firstname}</strong></p>
        We are hoping you will find the event for you!
        <br>
        <form name="eventForm" id="postForm" class="postForm" action="Events" method="post" onsubmit="return validateeventForm()">
	<div class="form-group">
      <label for="email">Event Name:</label>
      <input type="text" class="form-control" id="event_name" placeholder="Enter name" name="event_name">
    </div>
	<div class="form-group">
      <label for="email">Date:</label>
      <input type="date" class="form-control" id="date" placeholder="Enter date" name="date">
    </div>
	<div class="form-group">
      <label for="email">Event Hour:</label>
      <input type="text" class="form-control" id="time" placeholder="Enter time" name="time">
    </div>
	<div class="form-group">
      <label for="email">Description:</label>
      <input type="text" class="form-control" id="description" placeholder="Enter description" name="description">
    </div>
	<div class="form-group">
      <label for="email">Location:</label>
      <input type="text" class="form-control" id="location" placeholder="Enter location" name="location">
    </div>
    <button type="submit" class="btn btn-default" onclick="validateeventForm()">Submit</button>
</form>
      </div>
    </div>
    <div class="col-sm-7">
<%
List<Event> EventList = (List<Event>)request.getAttribute("allevents");

for (Event event: EventList){
	out.print("<div class=\"row\">"+
	"<div class=\"col-sm-3\">"+
	"<div class=\"well\">"+
	"<p>"+"By: "+event.getCreator().getUsername()+"</p>"+
	"</div>"+
	"</div>"+
	"<div class=\"col-sm-9\">"+
    "<div class=\"well\">"+
	"<h3>"+event.getEvent_name()+"</h3>"+
    "<p>"+event.getDescription()+"</p>"+
    "<p><strong>Location: </strong>"+event.getLocation()+",  <strong>Date: </strong>"+event.getDate()+",  <strong>Time: </strong>"+event.getTime()+"</p>"+
    "</div>"+
    "</div>"+
	"</div>");
}

%>
 </div>

</div>

<jsp:include page="Footer.jsp"></jsp:include>

</body>
<script type="text/javascript">


</script>
</html>



