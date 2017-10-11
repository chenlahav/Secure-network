<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="windows-1255"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<User> users = (List<User>) request.getAttribute("usersresults");
%>
<%
		if(session.getAttribute("username") != null){
			String username = session.getAttribute("username").toString();
		}
%>
<html lang="en">
<head>
<title>Results</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
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
        <a href="#" class="close" data-dismiss="alert" aria-label="close">ª</a>
        <p><strong>Hi! ${sessionScope.firstname}</strong></p>
        Here you can find all results!
      </div>
    </div>
  </div>
</div>

<%
for (User user: users){
	out.print("<div class=\"row\">"+
	"<div class=\"col-sm-3\">"+
	"<div class=\"well\">"+
	"<img src=\"bird.jpg\" class=\"img-circle\" height=\"55\" width=\"55\" alt=\"Avatar\">"+
	"</div>"+
	"</div>"+
	"<div class=\"col-sm-9\">"+
    "<div class=\"well\">"+
    "<h3>"+user.getFirstName()+" "+user.getLastName()+"</h3>"+
    "<br><p><strong>Username: </strong>"+user.getUsername()+"</p><br>"+
    "<p><strong>Email: </strong>"+user.getEmail()+"</p><br>"+
    "<p><strong>Telephone Number: </strong>"+user.getTelephone()+"</p><br>"+
    "<p><strong>Gender: </strong>"+user.getGender()+"</p><br>"+
    "<p><strong>Birth Date: </strong>"+user.getBday()+"</p><br>"+
    "</div>"+
    "</div>"+
	"</div>");
}
%>

<jsp:include page="Footer.jsp"></jsp:include>

</body>
</html>
