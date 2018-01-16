<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link type="text/css" rel="stylesheet" href="/resources/nav.css" >
</head>

<body>
<div>
<jsp:include page="/WEB-INF/views/navbar.jsp"></jsp:include>
</div>


<div class="container"><br>
<div class="page-header">
<div class="container">
<div class="row">
<div class="col-md-6">
<div class="panel with-nav-tabs panel-primary">
<div class="panel-heading" style="background:#252226;">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">Supplier</a>
		<li><a href="#tab2" data-toggle="tab">Category</a>
		<li><a href="#tab3" data-toggle="tab">Product</a>
	</ul>
</div>
	<div class="panel-body">
		<div class="tab-content">
			<div class="tab-pane fade in active" id="tab1">
				<form method="post" action="<c:url value="/saveSupp"/>"class="form-signin">
				<span id="reauth-email" class="reauth-email"></span>
				<h4 class="input-title">Supplier Id</h4><br>
				<input class="form-control" type="number" name="sid" required/>
				
				<h4 class="input-title">Supplier Name</h4><br>
				<input class="form-control" type="text" name="sname" required/>
				
				<br><br>
				<button class="btn btn-lg btn-primary" type="submit">Save</button>
				<button class="btn btn-lg btn-warning" type="reset">Cancel</button>
				
				</form>
			</div>
			<div class="tab-pane fade" id="tab2">
				<form method="post" action="<c:url value="/saveCat"/>"class="form-signin">
				<span id="reauth-email" class="reauth-email"></span>
				<h4 class="input-title">Category Id</h4><br>
				<input class="form-control" type="number" name="cid" required/>
				
				<h4 class="input-title">Category Name</h4><br>
				<input class="form-control" type="text" name="cname" required/>
				
				<br><br>
				<button class="btn btn-lg btn-primary" type="submit">Save</button>
				<button class="btn btn-lg btn-warning" type="reset">Cancel</button>
				
				</form>
			</div>
			
		</div>
	</div>
</div>
</div>
</div>
</div>
</div>
</div>

</body>
</html>