<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="model.Post"%>
<%@page import="model.Event"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<Post> posts = (List<Post>) request.getAttribute("allposts");
    List<Event> events = (List<Event>) request.getAttribute("allevents");
    User nowConnected = (User)request.getAttribute("userConnected");
%>

<html lang="en">
<head>
  <title>Information</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="Header.jsp" />

  <br><h3><center>Here you can find all your posts and events you published</center></h3><br><br>

<p><i><strong><center>Below you have your Posts: </center></strong></i></p>

<center>
<%
	for (Post p:posts){
		
		if(nowConnected.getId().equals((p.getAuthor().getId()))){
			out.print("<div class=\"row\">"+"<div class=\"col-sm-20\">"+
				    "<div class=\"well\" style=\"height: 220px; width: 550px;\">"+
				    "<h3><strong>"+p.getTitle()+"</strong></h3>"+
				    "<br><p>"+p.getContent()+"</p><br>"+"</div>"+"</div>"+"</div>");
		}
	}
%>
</center>

<p><i><strong><center>Below you have your Events: </center></strong></i></p>
</div>
</div>
<center>
<%
	for (Event e:events){
		
		if(nowConnected.getId().equals((e.getCreator().getId()))){
			out.print("<div class=\"row\">"+"<div class=\"col-sm-20\">"+
				    "<div class=\"well\" style=\"height: 220px; width: 550px;\">"+
				    		"<h3><strong>"+e.getEvent_name()+"</strong></h3><br>"+
				    	    "<p>"+e.getDescription()+"</p><br><br>"+
				    	    "<p><strong>Location: </strong>"+e.getLocation()+",  <strong>Date: </strong>"+e.getDate()+",  <strong>Time: </strong>"+e.getTime()+"</p>"+
				    "</div>"+"</div>"+"</div>");
		}
	}
%>
</center>
<jsp:include page="Footer.jsp" />
</body>
</html>