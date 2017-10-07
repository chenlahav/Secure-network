<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>x
<%@page import="model.Post"%>x

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
	List<Post> allpost = (List<Post>) request.getAttribute("allpost");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<form action="PostController" method="post">

for (Post post : allpost) {
				out.print(post.gettitle());
			}
			
 <input type="submit" value="get posts">
</form>


</body>
</html>
