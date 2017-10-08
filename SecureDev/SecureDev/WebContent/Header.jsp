<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <meta name="description" content="">
   <meta name="author" content="">
   <title>The SecNetwork</title>
   <!-- Bootstrap core CSS -->
   <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <!-- Custom styles for this template -->
   <link href="css/modern-business.css" rel="stylesheet">
</head>
<body>
<script type="text/javascript">
function mychoose(){
	if(document.pressed == 'Home')
		document.headerf.action = "Home.jsp";
	if(document.pressed == 'Profile')
		document.headerf.action = "Profile.jsp";
	if(document.pressed == 'Forum')
		document.headerf.action = "Forum.jsp";
	if(document.pressed == 'Event')
		document.headerf.action = "Event.jsp";
	return true;
	
}
	
</script>
<form name="headerf" onsubmit="return mychoose()">

<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<input class="navbar-brand" type="submit" id="navbarResponsive" value="Home" onclick="document.pressed=this.value">
		<input class="navbar-toggler navbar-toggler-right collapse navbar-collapse navbar-nav ml-auto" id="navbarResponsive" type="submit" value="Profile" onclick="document.pressed=this.value">
		<input class="navbar-toggler navbar-toggler-right collapse navbar-collapse navbar-nav ml-auto" type="submit" id="navbarResponsive" value="Forum" onclick="document.pressed=this.value">
		<input class="navbar-toggler navbar-toggler-right collapse navbar-collapse navbar-nav ml-auto" type="submit" id="navbarResponsive" value="Event" onclick="document.pressed=this.value">
		<input class="navbar-toggler navbar-toggler-right collapse navbar-collapse navbar-nav ml-auto" type="submit" id="navbarResponsive" value="Admin" onclick="document.pressed=this.value">

	</div>
</nav> 
</form>

</body>
</html>