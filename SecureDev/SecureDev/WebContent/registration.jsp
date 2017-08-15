<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="login.css">
<title>Registration</title>
</head>
<body>
	<form action="RegisterController" method="post">
		<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="text-center">Create new account</h1>
					</div>
						<div class="modal-body">
			
							<div class="form-group">
								<label for="id" class="col-sm-3 control-label">ID</label>
								<input type="text" name="id" class="form-control input-lg" placeholder="Your ID"/>
							</div>
							
							<div class="form-group">
								<label for="firstName" class="col-sm-3 control-label">First Name</label>
								<input type="text" name="first name" class="form-control input-lg" placeholder="First name"/>
							</div>
							
							<div class="form-group">
								<label for="lastname" class="col-sm-3 control-label">Last Name</label>
								<input type="text" name="last name" class="form-control input-lg" placeholder="Last name"/>
							</div>
							
							<div class="form-group">
								<label for="email" class="col-sm-3 control-label">Email</label>
								<input type="email" name="email" class="form-control input-lg" placeholder="Your Email"/>
							</div>
							
							<div class="form-group">
								<label for="username" class="col-sm-3 control-label">Username</label>
								<input type="text" name="username" class="form-control input-lg" placeholder="Username"/>
							</div>
							
							<div class="form-group">
								<label for="password" class="col-sm-3 control-label">Password</label>
								<input type="password" name="password" class="form-control input-lg" placeholder="Password"/>
							</div>
							
							<div class="form-group">
							<label for="b-date" class="col-sm-3 control-label">Date of Birth</label>
								<input type="date" name="bdate" class="form-control input-lg" placeholder="Date of Birth"/>
							</div>
							
							<div class="form-group">
								 <label for="gender" class="col-sm-3 control-label">Gender</label>
							 	 <input type="radio" name="gender" value="Male" checked>Male<br>
  								 <input type="radio" name="gender" value="Female">Female
  							</div> 
  							
							<div class="form-group">
								<input type="submit" class="btn btn-block btn-lg btn-primary" value="Create account"/>
							</div>
						</div>
				</div>
		</div>
	</form>

</body>
</html>