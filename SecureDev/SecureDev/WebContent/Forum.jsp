<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<form action="PostController" method="post">
<h2>External JavaScript</h2>
<div class="news-story">
 <h5>sample 1</h5>
 <input type="submit" value="get posts">
 <p>posts:</p>
  ${requestScope['allpost']}
 <h5>sample 2</h5>
 <p>By: author 2</p>
</div>
</form>
</body>
</html>
