<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta charset="utf-8">
   <title>The SecNetwork</title>
   <link rel="stylesheet" href="Home.css">
</head>
<body>
<jsp:include page="Header.jsp" />
<br>
<br>
<h1>Welcome ${sessionScope.firstname}</h1>
<p>You are in the biggest social security network.</p><br>
<p>We it into our mission to have the most updated information on all the security event.</p><br>
<p>In the forum page you can ask security related questions and answer others.</p><br>

<jsp:include page="Footer.jsp"/>
	
    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper/popper.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>