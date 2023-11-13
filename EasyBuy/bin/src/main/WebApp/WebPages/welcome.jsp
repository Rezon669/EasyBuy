<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>homepage</title>
<link rel="stylesheet" type="text/css" href="/static/css/welcome.css" />
</head>
<body>
	<h2>
		Welcome
		<%=request.getParameter("username")%></h2>
	<br>
	<form method="get" action="/easybuy/logout">
		<input id="submit" type="submit" value="Sign Out"><br> <br>
	</form>
	<div class="homepage">

		<label for="home"><b>Click on the provided options:</b></label> <br>
		<br>
		<br>
		<br>

		<form method="get" action="/easybuy/secure/addproduct">
			<input id="button" type="submit" value="Add Product"><br>
			<br>
			<br>
		</form>
		<form method="get" action="/easybuy/secure/listofproducts">
			<input id="button" type="submit" value="List of Products"><br>
			<br>
			<br>
		</form>
		<form method="get" action="/easybuy/secure/searchitem">
			<input id="button" type="submit" value="Search Product"><br>

		</form>


		<br> <br>


	</div>



</body>
</html>