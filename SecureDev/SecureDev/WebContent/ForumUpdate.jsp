<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" 
    import = "controllers.PostController"
    import = "model.Post"
    import ="java.util.ArrayList"
    import = "java.util.List" 
    import ="Repository.PostRepository" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
List<Post> posts = (List<Post>) request.getAttribute("allposts");


%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function LoadP(){ 
	var postT = document.getElementById('postsT');
	var header = postT.createTHead();
	var row = header.insertRow(0);     
	var cell = row.insertCell(0);
	cell.innerHTML = "<b>This is a table header</b>";
	var allpost = c.returnAllPosts();
	var count = allpost.length();
	for (i=0; i<count;i++)
	{
		var postT = document.getElementById('postsT');
		var header = postT.createTHead();
		var row = header.insertRow(0);     
		var cell = row.insertCell(0);
		cell.innerHTML = "<b>This is a table header</b>";
	}
}

function newP(){
	document.getElementById('newcomment').style.display='inline';
}
function pageload(){
	document.getElementById('newcomment').style.display='none';
}
function inputV(){
	var validateTitle = document.forms["newp"]["title"].value;
	var validatePost = document.forms["newp"]["post"].value;
	var validateLink = document.forms["newp"]["link"].value;
	if(validateTitle=="")
		alert("no");
	return false;
}
	
</script>
<body onload="pageload()">
<jsp:include page="Header.jsp" />
<br>
<br>
<button name="newPost" value="New Comment" onclick="newP()">New Post</button>
<div id="newcomment">
<form name="newp" action="PostController" method="post" onsubmit="return inputV()">
	<input name="title" type="text" placeholder="Title">
	<input name="post" type="text" placeholder="Post">
	<input name="link" type="text" placeholder="Link">
	<input type="submit" value="Submit" >
<%

Post p = new Post(1,"3","3","4","5","5");
out.print(p.returnInt());
out.print(p.getAuthor());
PostRepository j = new PostRepository();
out.print(j.getAllPosts());
%>
</form>
<button onclick="LoadP()"></button>

</div>
</body>
</html>