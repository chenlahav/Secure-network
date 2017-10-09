<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Post"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<Post> posts = (List<Post>) request.getAttribute("allposts");
%>
<%
		if(session.getAttribute("username") != null){
			String username = session.getAttribute("username").toString();
		}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
<link href="all.css" rel="stylesheet">
</head>
<body onload="openW()">
<jsp:include page="Header.jsp" />
<br>
<br>
<p class="introForum">Hello ${sessionScope.firstname},
<br>
Below you can find security related questions and posts. <br>
We invite you to add posts as you wish. <br>
Please fill free to comment and answer on others posts.
</p>
<br>
<br>
<div class="newPostHead">
<button class="newP" onclick="openW()">Add Post</button>
<br>
<br>
<form id="postForm" class="postForm" action="" method="" onsubmit="return inputV()">
	<input class="newPost" type="text" placeholder="Please add your post">
	<input class="submitPost" type="submit" onclick="inputV()" value="submit">
</form>
<br>
<br>
</div>
<%
	for (Post p: posts){
		out.print("<table class=\"postsT\">"+"<tr>"+
		"<td class=\"postTitle\">"+ p.getTitle()+"</td>"+"</tr>"+
		"<tr class=\"rowP\">"+"<td >"+ "By:  "+ p.getAuthor().getUsername()+"</td>"+"</tr>"+
		"<tr class=\"rowP\">"+"<td>"+ p.getContent()+"</td>"+"</tr>" + 
		"<tr>"+"<td class=\"pTime\">"+p.getTime()+"</td>"+"</tr>"+
		"<tr class=\"rowP commentP\">"+"<td class=\"commentP\">"+"<form class=\"popuptext\" id=\"addC\" action=\"\" onsubmit=\"return inputV()\">"
		+"<input class=\"placeholderC\"type=\"text\" placeholder=\"Please add your comment here and press submit\">"+
		"<input class=\"submith\" type=\"submit\" onclick=\"inputV()\" value=\"Submit\">"+
		"</form>"+"</td>"+"</tr>"+
		"</table>"+"<br>"+"<br>");
	}

%>

</body>
<script type="text/javascript">
function openW(){
	var x = document.getElementById("postForm");
	if (x.style.display==="none")
		x.style.display="block";
	 else {
	        x.style.display = "none";
	    }
}
</script>
</html>
