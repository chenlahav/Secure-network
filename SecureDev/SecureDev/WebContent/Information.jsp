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
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="Header.jsp" />
<div class="row">
<div class="well">
  <p><i class="">Hi ${sessionScope.firstname} ${sessionScope.lastname}</i></p><br>
  <p>Here you can find all your posts and events you published</p>
</div>
</div>
<div class="row">
<div class="well">
<p><i>Below you have your Posts: </i></p>
</div>
</div>
<%
	for (Post p:posts){
		
		if(nowConnected.getId().equals((p.getAuthor().getId()))){
			out.print("<div class=\"row\">"+"<div class=\"col-sm-9\">"+
				    "<div class=\"well\">"+
				    "<h3>"+p.getTitle()+"</h3>"+
				    "<br><p>"+p.getContent()+"</p><br>"+"</div>"+"</div>"+"</div>");
		}
	}
%>
<div class="row">
<div class="well">
<p><i>Below you have your Events: </i></p>
</div>
</div>
<%
	for (Event e:events){
		
		if(nowConnected.getId().equals((e.getCreator().getId()))){
			out.print("<div class=\"row\">"+"<div class=\"col-sm-9\">"+
				    "<div class=\"well\">"+
				    "<h3>"+e.getEvent_name()+"</h3>"+
				    "<br><p>"+e.getDescription()+"</p><br>"+"</div>"+"</div>"+"</div>");
		}
	}
%>
<jsp:include page="Footer.jsp" />
</body>
</html>