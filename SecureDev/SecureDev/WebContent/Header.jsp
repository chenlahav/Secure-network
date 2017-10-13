<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
	<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
  <title>Security on Top</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/SecureDev/Home">Security on Top</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/SecureDev/Profile">Profile</a></li>
      <li><a href="/SecureDev/Forum">Forum</a></li>
      <li><a href="/SecureDev/Events">Events</a></li>
      <li><a href="/SecureDev/Admin">Admin</a></li>
      <li><a href="/SecureDev/Information">Info</a></li>
      <li><a href="/SecureDev/AllUsers">AllUsers</a></li>
      <li><a href="/SecureDev/login.jsp">LogOut</a></li>

    </ul>
    <form class="navbar-form navbar-left" action="Search" method="post">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search user by first name" name= "firstname">
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>
  </div>
</nav>	

</body>
</html>