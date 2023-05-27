<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>back-to-login</title>
<link rel="stylesheet" type="text/css" href="css/backtologin.css"/>
</head>
<body>
<h2>Thank you...! <%= request.getParameter("name") %> Successfully created the account</h2>  
<h2><a href="/easybuy/signin">click here</a> to go back to login page</h2>
</body>
</html>  