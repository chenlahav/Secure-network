<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="login.css">
<title>SecureApp</title>
</head>
<body>
	
	<!-- 
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	The LoginController servlet was mapped in the web.xml file. 
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	-->
	
	<form action="LoginController" method="post">
		<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="text-center">Welcome</h1>
					</div>
						<div class="modal-body">
							<div class="form-group">
								<input type="text" name="username" class="form-control input-lg" placeholder="Username"/>
							</div>
			
							<div class="form-group">
								<input type="password" name="password" class="form-control input-lg" placeholder="Password"/>
							</div>
			
							<div class="form-group">
								<input type="submit" class="btn btn-block btn-lg btn-primary" value="Login"/>
								<span class="pull-right"><a href="registration.jsp">Register</a></span><span><a href="#">Forgot Password</a></span>
							</div>
						</div>
				</div>
		</div>
	</form>
</body>
</html>