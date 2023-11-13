<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>search product</title>
<link rel="stylesheet" type="text/css"
	href="static/css/searchproduct.css" />
</head>
<body>
	<form id="search" method="get" action="/easybuy/secure/searchproducts">

		<label>Search Keyword</label><br>
		<br> <input type="text" name="searchkeyword" id="searchkeyword"
			autofocus><br>
		<br> <input type="submit" id="search" value="Search">
	</form>
</body>
</html>