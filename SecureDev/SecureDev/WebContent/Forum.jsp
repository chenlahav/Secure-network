<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Post"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
	List<Post> posts = (List<Post>) request.getAttribute("allposts");
	%>

<div>
<%
	for (Post p: posts){
		out.print("<table>"+"<tr>"+
		"<td h3>"+ p.getTitle()+"</td>"+
		"<td>"+p.getTime()+"</td>"+
		"<td>"+ "By:"+ p.getAuthor().getUsername()+"</td>"+
		"<td>"+ p.getContent()+"</td>"+
		"</tr>" +"</table>");
	}
%>

</div>
