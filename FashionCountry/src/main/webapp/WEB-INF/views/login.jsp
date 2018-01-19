<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <title>Login page</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"></jsp:include>

<div class = "container">
<h2>Welcome to Login page</h2>
<form action="${pageContext.request.contextPath }/login" class = "form-signin" method = "post">
<h3><lable>Email:</lable></h3>
<input type = "text" name = "username" class = "login-box" required>

<h3><label>Password:</label></h3>
<input type = "password" name = "password" class ="login-box" required>
<button class = "btn btn-lg btn-primary" type = "submit">Login</button>
<button class = "btn btn-lg btn-warning" type = "reset">Cancel</button>

</form>
</div>
</body>
</html>