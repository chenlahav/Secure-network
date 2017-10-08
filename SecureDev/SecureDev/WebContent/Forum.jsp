<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>x
<%@page import="model.Post"%>x

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
	List<Post> posts = (List<Post>) request.getAttribute("allposts");
	%>
<form action="/forum" method="get">
	<div>
		<%
			for(Post post: posts){
				out.print("<div>" + post.getTitle() + "</div>");
			}
		%>
	</div>
</form>