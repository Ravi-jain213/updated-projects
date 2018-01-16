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
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

  <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="index.jsp">Fashion Country</a>
    </div>
    </div>
    
    <nav id="navbar-red" class="navbar navbar-inverse navbar-static-top" role="navigation">
    <div class="container">
    <ul class="nav navbar-nav">
    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="navbar">
    <span class="icon-bar"> </span>
    <span class="icon-bar"> </span>
    <span class="icon-bar"> </span>
    <span class="icon-bar"> </span>
    <span class="icon-bar"> </span>
    </button>
    </ul>
    </div>
    
    <div class="collapse navbar-collapse" id="navDemo">
    <li><a href="index">Home</a></li>
    <li><a href="contact"><i class="fa fa-address-book" aria-hidden="true"></i></a></li>
    <li><a href="productList">Product List</a></li>
    <li><a href="adding">Admin</a></li>
    <li><a href="goToRegister">Register</a></li>
    </div>
    
</nav>
    
</body>
</html>