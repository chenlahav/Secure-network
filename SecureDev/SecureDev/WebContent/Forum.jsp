<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="windows-1255"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Post"%>
<%@page import="model.Comment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<Post> posts = (List<Post>) request.getAttribute("allposts");
    List<Comment> comments = (List<Comment>) request.getAttribute("allcomments");
%>
<%
		if(session.getAttribute("username") != null){
			String username = session.getAttribute("username").toString();
		}
%>
<html lang="en">
<head>
<title>Forum Page</title>
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
        People are looking for your questions and answers!
      </div>
    </div>
    <div class="col-sm-7">
     <form id="postForm" class="postForm" action="Forum" method="post">
      <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default text-left">
            <div class="panel-body">
            <h3>Create new post</h3><br>
			<input name = "title" class="newPost panel-body col-sm-12 panel panel-default text-left" type="text" placeholder="Please add the Post title"><br>
			<input contenteditable="true" name = "content" class="newPost panel-body col-sm-12 panel panel-default text-left" type="text" placeholder="Please add your post"><br>
			<input class="submitPost btn btn-default btn-sm" type="submit" value="submit">    
            </div>
          </div>
        </div>
      </div>
      </form>
<%
for (Post p: posts){
	out.print("<div class=\"row\">"+
	"<div class=\"col-sm-3\">"+
	"<div class=\"well\">"+
	"<p>"+p.getAuthor().getUsername()+"</p>"+
	"<p>"+p.getDate()+"</p>"+
	"<p>"+p.getTime()+"</p>"+
	"<img src=\"bird.jpg\" class=\"img-circle\" height=\"55\" width=\"55\" alt=\"Avatar\">"+
	"</div>"+
	"</div>"+
	"<div class=\"col-sm-9\">"+
    "<div class=\"well\">"+
    "<h3>"+p.getTitle()+"</h3>"+
    "<br><p>"+p.getContent()+"</p><br>"+
    "<form id=\"\" class=\"\" action=\"Comment\" method=\"post\">"+
    "<div class=\"row\">"+
    "<div class=\"col-sm-12\">"+
    "<div class=\"panel panel-default text-left\">"+
    "<div class=\"panel-body\">"+
	"<input name = \"content\" class=\"newC panel-body col-sm-12 panel panel-default text-left\" type=\"text\" placeholder=\"Please add your comment here\">"+"<br>"+
	"<input type=\"hidden\" name=\"postid\" value="+p.getId()+">"+
	"<input class=\"submitPost btn btn-default btn-sm\" type=\"submit\" value=\"submit\">"+    
    "</div>"+
	"</div>"+
    "</div>"+
	"</div>"+
    "</form>"+
    "</div>"+
    "</div>"+
	"</div>");
	for(Comment c:comments){
		if(p.getId()==c.getPostId())
		out.print("<br><p>"+c.getDate()+"  "+c.getTime()+"</p><br>"+
				"<br><p>"+"By: "+c.getCreator()+"</p><br>"+
				"<br><p>"+c.getContent()+"</p><br>");
	}
}

%>
 </div>
    <div class="col-sm-2 well">
      <div class="thumbnail">
        <p>Upcoming Events:</p>
        <img src="paris.jpg" alt="Paris" width="400" height="300">
        <p><strong>Paris</strong></p>
        <p>Fri. 27 November 2015</p>
        <button class="btn btn-primary">Info</button>
      </div>      
      <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div>
    </div>
  </div>
</div>

<jsp:include page="Footer.jsp"></jsp:include>


</body>
</html>
