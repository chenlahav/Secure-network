<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="model.Post"%>
<%@page import="model.Event"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<User> users = (List<User>) request.getAttribute("allusers");
%>

<html lang="en">
<head>
  <title>All Users</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="Header.jsp" />

  <br><h3><center>Here you can find all users members in our community</center></h3><br><br>
<center>
<%
	for (User user:users){
		if((!user.getUsername().equals("admin")&&(!user.getId().equals(session.getAttribute("userID"))))){
			out.print("<div class=\"row\">"+"<div class=\"col-sm-20\">"+
				    "<div class=\"well\" style=\"height: 440px; width: 440px;\">"+
		    	    "<h3><strong>"+user.getFirstName()+" "+user.getLastName()+"</strong></h3>"+
		    	    "<br><p><strong>Username: </strong>"+user.getUsername()+"</p><br>"+
		    	    "<p><strong>Email: </strong>"+user.getEmail()+"</p><br>"+
		    	    "<p><strong>Telephone Number: </strong>"+user.getTelephone()+"</p><br>"+
		    	    "<p><strong>Gender: </strong>"+user.getGender()+"</p><br>"+
		    	    "<p><strong>Birth Date: </strong>"+user.getBday()+"</p>"+
				    "</div>"+"</div>"+"</div>");
		}
	}
%>
</center>
<jsp:include page="Footer.jsp" />
</body>
</html>