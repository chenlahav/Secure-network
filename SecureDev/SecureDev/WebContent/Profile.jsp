<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>My Profile</title>
</head>

<body>
<jsp:include page="Header.jsp" />
<br>
<br>
<form>
<!-- Profile -->
    <p><i class="">Name: ${sessionScope.firstname} ${sessionScope.lastname}</i> </p>
    <p><i class=""></i>Email: ${sessionScope.email}</p>
    <p><i class=""></i>Birth Date: ${sessionScope.bdate}</p>
    </div>
</div>
</form>
</body>
</html>