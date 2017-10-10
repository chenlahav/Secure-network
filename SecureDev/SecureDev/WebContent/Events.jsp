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

<html>
<head>
  <title>Events</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<br>
<br>
<form id="postForm" class="postForm" action="Events" method="post">
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
    <button type="submit" class="btn btn-default">Submit</button>
</form>
<br>
<br>

<%
List<Event> EventList = (List<Event>)request.getAttribute("allevents");

	for (Event event: EventList){
		out.print("<table class=\"eventT\">"+"<tr>"+
		"<td class=\"postTitle\">"+ event.getEvent_name()+"</td>"+"</tr>"+
		"<td class=\"postTitle\">"+ event.getLocation()+"</td>"+"</tr>"+
		"<tr class=\"rowP\">"+"<td >"+ "By:  "+ event.getCreator().getUsername()+"</td>"+"</tr>"+
		"<tr class=\"rowP\">"+"<td>"+ event.getDescription()+"</td>"+"</tr>" + 
		"<tr>"+"<td class=\"pTime\">"+event.getTime()+"</td>"+"</tr>"+
		"<tr class=\"rowP commentP\">"+"<td class=\"commentP\">"+"<form class=\"popuptext\" id=\"addC\" action=\"\" onsubmit=\"return inputV()\">"+
		"</form>"+"</td>"+"</tr>"+
		"</table>"+"<br>"+"<br>");
	}

%>
</body>
</html>

