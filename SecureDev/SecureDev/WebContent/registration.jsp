<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js\sc.js" ></script>
<link rel="stylesheet" href="Reg.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Registration</title>
</head>
<body>
<jsp:include page="FirstHeader.jsp"></jsp:include>


	<form name="registrationForm" action="RegisterController" method="post" enctype="multipart/form-data" onsubmit="return validateRegistrationForm()">
		<div class="registration">
				<div class="formFields">
					<div class="modal-header">
						<h1 class="text-center">Create new account</h1>
					</div>
						<div class="modal-body">
			
							<div class="form-group">
								<label for="id" class="label">ID</label>
								<input type="text" name="id" class="form-control" placeholder="Your ID"/>
							</div>
							
							<div class="form-group">
								<label for="firstName" class="label">First Name</label>
								<input type="text" name="firstname" class="form-control" placeholder="First name"/>
							</div>
							
							<div class="form-group">
								<label for="lastname" class="label">Last Name</label>
								<input type="text" name="lastname" class="form-control" placeholder="Last name"/>
							</div>
							
							<div class="form-group">
								<label for="email" class="label">Email</label>
								<input type="email" name="email" class="form-control" placeholder="Your Email"/>
							</div>
							
							<div class="form-group">
								<label for="username" class="label">Username</label>
								<input type="text" name="username" class="form-control" placeholder="Username"/>
							</div>
							
							<div class="form-group">
								<label for="password" class="label">Password</label>
								<input type="password" name="password" class="form-control" placeholder="Password"/>
							</div>
							
							<div class="form-group">
							<label for="b-date" class="label">Date of Birth</label>
								<input type="date" name="bdate" class="form-control" placeholder="Date of Birth"/>
							</div>
							
							<div class="form-group">
							<label for="telephone" class="label">Telephone Number</label>
								<input type="text" name="telephone" class="form-control" placeholder="Telephone Number"/>
							</div>
							
							<div class="form-group">
								 <label for="gender" class="label">Gender</label><br>
							 	 <input type="radio" name="gender" value="Male" checked>Male<br>
  								 <input type="radio" name="gender" value="Female">Female
  							</div> 
							<div class="form-group">
								<label for="image" class="label">Add your image</label>
								<input type="file" name="img" class="form-control" accept="image/jpeg" />
							</div>  							
							<div class="form-group">
								<input type="submit" class="btn btn-block btn-lg btn-primary" onclick="validateRegistrationForm()" value="Create account"/>
							</div>
						</div>
				</div>
		</div>

	</form>

<jsp:include page="Footer.jsp"></jsp:include>

</body>

</html>