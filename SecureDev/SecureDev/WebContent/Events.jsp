<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Event"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
		if(session.getAttribute("username") != null){
			String username = session.getAttribute("username").toString();
		}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<br>
<br>
<form id="postForm" class="postForm" action="Forum" method="post">
	<input name = "title" class="newPost" type="text" placeholder="Please add title">
	<input name = "content" class="newPost" type="text" placeholder="Please add your post">
	<input class="submitPost" type="submit" value="submit">
</form>
<br>
<br>

<%
List<Event> EventList = (List<Event>)request.getAttribute("allevents");

	for (Event event: EventList){
		out.print("<table class=\"eventT\">"+"<tr>"+
		"<td class=\"postTitle\">"+ event.getEvent_name()+"</td>"+"</tr>"+
		"<td class=\"postTitle\">"+ event.getLocation()+"</td>"+"</tr>"+
		"<tr class=\"rowP\">"+"<td >"+ "By:  "+ event.getCreator().getUsername()+"</td>"+"</tr>"+
		"<tr class=\"rowP\">"+"<td>"+ event.getDescription()+"</td>"+"</tr>" + 
		"<tr>"+"<td class=\"pTime\">"+event.getTime()+"</td>"+"</tr>"+
		"<tr class=\"rowP commentP\">"+"<td class=\"commentP\">"+"<form class=\"popuptext\" id=\"addC\" action=\"\" onsubmit=\"return inputV()\">"+
		"</form>"+"</td>"+"</tr>"+
		"</table>"+"<br>"+"<br>");
	}

%>
</body>
</html>

