<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Event"%>
<%@page import="model.Post"%>
<%@page import="model.User"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
		if(session.getAttribute("username") != null){
			String username = session.getAttribute("username").toString();
		}
List<Event> EventList = (List<Event>)request.getAttribute("allevents");
List<Post> PostList = (List<Post>)request.getAttribute("allposts");
List<User> UserList = (List<User>)request.getAttribute("allusers");
%>

<html lang="en">
<head>
<title>Admin Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="Header.jsp" />
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> Users Table</div>
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Birth Date</th>
                  <th>Email</th>
                  <th>User Name</th>
                  <th>Telephone Number</th>
                  <th>Is Admin</th>
                  <th>Delete</th>
                </tr>
              </thead>
              <tbody>
<%
	for(User user:UserList)
	{	
     	out.print("<tr>"+
                  "<td>"+user.getId()+"</td>"+
                  "<td>"+user.getFirstName()+"</td>"+
                  "<td>"+user.getLastName()+"</td>"+
                  "<td>"+user.getBday()+"</td>"+
                  "<td>"+user.getEmail()+"</td>"+
                  "<td>"+user.getUsername()+"</td>"+
                  "<td>"+user.getTelephone()+"</td>"+
                  "<td id=\"admin\">"+user.isAdmin()+"</td>"+
                  "<td>"+"<form name=\"deleteu\"action=\"Admin\" method=\"post\">"+
                  "<input type=\"hidden\" name=\"hiddenu\"value="+user.getId()+"></input>"+
                  "<input type=\"submit\"/ value=\"Press to Delete User\">"+"</input>"+"</form>"+"</td>"+
                  "<td>"+"<form name=\"deleteu\"action=\"Admin\" method=\"post\">"+
                  "<input type=\"hidden\" name=\"hiddenAdmin\"value="+user.getId()+">"+"</input>"+
                  "<input type=\"submit\" value=\"Press to Change Admin Ststus\"/>"+"</input>"+"</form>"+"</td>"+
                  "</tr>");
	}
%>
				</tbody>
            </table>
          </div>
      </div>
    </div>
       <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> Posts Table</div>
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Title</th>
                  <th>Content</th>
                  <th>Author</th>
                  <th>Posted Date</th>
                  <th>Posted Hour</th>
                  <th>Delete</th>
                </tr>
              </thead>
              <tbody>
<%
	for(Post post:PostList)
	{	
     	out.print("<tr>"+
                  "<td>"+post.getId()+"</td>"+
                  "<td>"+post.getTitle()+"</td>"+
                  "<td>"+post.getContent()+"</td>"+
                  "<td>"+post.getAuthor().getUsername()+"</td>"+
                  "<td>"+post.getDate()+"</td>"+
                  "<td>"+post.getTime()+"</td>"+
                  "<td>"+"<form name=\"deletep\"action=\"Admin\" method=\"post\">"+
                  "<input type=\"hidden\" name=\"hiddenp\" value="+post.getId()+"></input>"+
                  "<input type=\"submit\" value=\"Press to Delete Post\"/>"+"</input>"+"</form>"+"</td>"+
                  "</tr>");
	}

%>
				</tbody>
            </table>
          </div>
      </div>
    </div>
       <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> Events Table</div>
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Event Name</th>
                  <th>Description</th>
                  <th>Creator</th>
                  <th>Date</th>
                  <th>Time</th>
                  <th>Location</th>
                  <th>Delete</th>
                </tr>
              </thead>
              <tbody>
<%
	for(Event event:EventList)
	{	
		
     	out.print("<tr>"+
                  "<td>"+event.getId()+"</td>"+
                  "<td>"+event.getEvent_name()+"</td>"+
                  "<td>"+event.getDescription()+"</td>"+
                  "<td>"+event.getCreator().getUsername()+"</td>"+
                  "<td>"+event.getDate()+"</td>"+
                  "<td>"+event.getTime()+"</td>"+
                  "<td>"+event.getLocation()+"</td>"+
                  "<td>"+"<form name=\"deletee\"action=\"Admin\" method=\"post\">"+
                  "<input type=\"hidden\" name=\"hiddene\"value="+event.getId()+"></input>"+
                  "<input type=\"submit\"value=\"Press to Delete Event\"/>"+"</input>"+"</form>"+"</td>"+
                  "</tr>");
	}
%>
				</tbody>
            </table>
          </div>
      </div>
    </div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
<script type="text/javascript">

</script>
</html>
