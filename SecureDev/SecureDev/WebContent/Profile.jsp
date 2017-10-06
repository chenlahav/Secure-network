<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>My Profile</title>
</head>

<body>
<form>
<!-- Profile -->
	
  <div class="w3-card-2 w3-round w3-white">
    <div class="w3-container">
    <h4 class="w3-center">${requestScope['user'].username}</h4>
    <p class="w3-center"><img src="/w3images/avatar3.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
    <hr>
    <img alt="pic" src="‪/SecureDev/WebContent/Images/hacking-self-defence-750x400.jpg‬">
    <p><i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"><label>Name:</label><%out.print(request.getSession().getAttribute("name"));%></i> </p>
    <p><i class="fa fa-home fa-fw w3-margin-right w3-text-theme"></i> London, UK</p>
    <p><i class="fa fa-birthday-cake fa-fw w3-margin-right w3-text-theme"></i> April 1, 1988</p>
    </div>
</div>
</form>
</body>
</html>