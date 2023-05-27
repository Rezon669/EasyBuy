<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>signin</title>
<link rel="stylesheet" type="text/css" href="signin.css"/>
</head>
<body>
<h2>Enter your details to login</h2>
<div class="signin">

<form method="get" action="/easybuy/loginvalidation">
 <label>User Name</label><br>
 <input type="text" name="username" id="username"><br><br>
 <label>Password</label><br>
 <input type="password" name="password" id="password"><br><br>


<input id="submit" type="submit" value="Sign in"><br><br>

</form>
<a href="/signup">Create Account</a>
</div>
</body>
</html>