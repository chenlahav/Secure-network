<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="windows-1255"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Post"%>
<%@page import="model.Comment"%>
<%@page import="model.Event"%>

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
        <a href="#" class="close" data-dismiss="alert" aria-label="close">ª</a>
        <p><strong>Hi! ${sessionScope.firstname}</strong></p>
        People are looking for your questions and answers!
      </div>
    </div>
    <div class="col-sm-7">
     <form name="postForm" onsubmit="return validatepostForm()" id="postForm" class="postForm" action="Forum" method="post">
      <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default text-left">
            <div class="panel-body">
            <h3>Create new post</h3><br>
			<input name = "title" class="newPost panel-body col-sm-12 panel panel-default text-left" type="text" placeholder="Please add the Post title"><br>
			<input contenteditable="true" name = "content" class="newPost panel-body col-sm-12 panel panel-default text-left" type="text" placeholder="Please add your post"><br>
			<input onclick="validatepostForm()" class="submitPost btn btn-default btn-sm" type="submit" value="submit">    
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
	"</div>"+
	"</div>"+
	"<div class=\"col-sm-9\">"+
    "<div class=\"well\">"+
    "<h3><strong>"+p.getTitle()+"</strong></h3>"+
    "<p>"+p.getContent()+"</p><br>"+
	"<p style=\"float: left;\"><strong>Comments: </strong><p><br>");
	for(Comment c:comments){
		if(p.getId()==c.getPostId())
		out.print("<div class=\"well\">"+
				"<p>"+c.getContent()+"</p>"+
			    "<p style=\"float: right;\"><font size=\"1.7\">"+c.getDate()+"  "+c.getTime()+"</font></p>"+
				"<p style=\"float: left;\"><font size=\"1.7\">By: "+c.getCreator().getUsername()+"</font></p>"+
				"</div>");
	}
	out.print("<form name=\"commentForm\" id=\"\" class=\"\" action=\"Comment\" method=\"post\" onsubmit=\"return validatecommentForm()\">"+
		    "<div class=\"row\">"+
		    "<div class=\"col-sm-12\">"+
		    "<div class=\"panel panel-default text-left\">"+
		    "<div class=\"panel-body\">"+
			"<input name = \"content\" class=\"newC panel-body col-sm-12 panel panel-default text-left\" type=\"text\" placeholder=\"Please add your comment here\">"+"<br>"+
			"<input type=\"hidden\" name=\"postid\" value="+p.getId()+"></input>"+
			"<input onclick=\"validatecommentForm()\" class=\"submitPost btn btn-default btn-sm\" type=\"submit\" value=\"submit\"></input>"+    
		    "</div>"+
			"</div>"+
		    "</div>"+
			"</div>"+
		    "</form>"+"</div>"+"</div>"+"</div>");
}

%>
 </div>

</div>
<jsp:include page="Footer.jsp"></jsp:include>

</div>
</Footer>
</body>
<script type="text/javascript">

</script>
</html>
