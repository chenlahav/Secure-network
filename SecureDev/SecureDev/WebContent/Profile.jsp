<%@page import="model.Event"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>My Profile</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
  </style>
</head>
<body onload="pageLoad()">
<jsp:include page="Header.jsp" />
<div class="container text-center">    
  <div class="row">
    <div class="col-sm-3 well">
      <div class="well">
        <p><i class="">Name: ${sessionScope.firstname} ${sessionScope.lastname}</i></p>
        <img src=${sessionScope.ProfilePicture} class="img-circle" height="200" width="200" alt="Avatar">
        <p><i class="">Email: ${sessionScope.email}</i></p>
        <p><i class="">Birth Date: ${sessionScope.bdate}</i></p>
      </div>
      <div class="well">
        <p><a href="#">Interests</a></p>
        <p>
          <span class="label label-default">News</span>
          <span class="label label-primary">W3Schools</span>
          <span class="label label-success">Labels</span>
          <span class="label label-info">Football</span>
          <span class="label label-warning">Gaming</span>
          <span class="label label-danger">Friends</span>
        </p>
      </div>
    </div>
    <div class="col-sm-7">
    
      
      <div class="row">
        <div class="col-sm-9">
          <div class="well">
          	<button name="editInformation" onclick="EditInformation()">Edit my profile</button>
            <form id="EditInformationForm" action="Profile" method="post">
				<div class="registration">
				<div class="formFields">
					<div class="modal-header">
						<h1 class="text-center">Fill in the information you would like to change</h1>
					</div>
						<div class="modal-body">
							
							<div class="form-group">
								<label for="firstName" class="label">First Name</label>
								<input type="text" name="first name" class="form-control" placeholder="First name" value=${sessionScope.firstname}></input>
							</div>
							
							<div class="form-group">
								<label for="lastname" class="label">Last Name</label>
								<input type="text" name="last name" class="form-control" placeholder="Last name" value=${sessionScope.lastname}></input>
							</div>
							
							<div class="form-group">
								<label for="email" class="label">Email</label>
								<input type="email" name="email" class="form-control" placeholder="Your Email" value=${sessionScope.email}></input>
							</div>
							
							<div class="form-group">
								<label for="username" class="label">Username</label>
								<input type="text" name="username" class="form-control" placeholder="Username" value=${sessionScope.username}></input>
							</div>
							
							<div class="form-group">
							<label for="b-date" class="label">Date of Birth</label>
								<input type="date" name="bdate" class="form-control" placeholder="Date of Birth" value=${sessionScope.bdate}></input>
							</div>
							 
							<div class="form-group">
							<label for="telephone" class="label">Telephone Number</label>
								<input type="text" name="telephone" class="form-control" placeholder="Telephone Number" value=${sessionScope.telephone}></input>
							</div>
							  							
							<div class="form-group">
								<input type="submit" class="btn btn-block btn-lg btn-primary" value="Save Changes"/>
							</div>
						</div>
				</div>
		</div>
	</form>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-9">
          <div class="well">
            <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-9">
          <div class="well">
            <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-9">
          <div class="well">
            <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
          </div>
        </div>
      </div>     
    </div>
    <div class="col-sm-2 well">
      <p>Upcoming Events:</p>
      <%
      	List<Event> events = (List<Event>) request.getAttribute("latestEvents");
      for (Event e : events){
    	  out.print("<div class=\"thumbnail\">"+
      				"<p><strong>"+e.getEvent_name()+"</strong></p>"+
      				"<p>"+e.getDate()+" "+e.getTime()+"</p></div>");

      }
      %>
    </div>	
  </div>
</div>

<jsp:include page="Footer.jsp"></jsp:include>

</footer>

</body>
<script type="text/javascript">
function pageLoad(){
	document.getElementById('EditInformationForm').style.display='none';
}
function EditInformation(){
	document.getElementById('EditInformationForm').style.display='inline';
}
</script>

</html>

