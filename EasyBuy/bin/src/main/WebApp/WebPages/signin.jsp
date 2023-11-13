<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>signin</title>
<link rel="stylesheet" type="text/css" href="/static/css/signin.css" />
</head>
<body>
	<h2>Enter your details to login</h2>
	<div class="signin">

		<form method="get" action="/easybuy/loginvalidation">

			<p>${errorMessage}</p>
			<label>Username</label><br> <input type="text" name="username"
				id="username" placeholder="Enter your Username"><br>
			<br> <label>Password</label><br> <input type="password"
				name="password" id="password" placeholder="Enter your Password"><br>
			<br> <input id="submit" type="submit" value="Sign in"><br>
			<br>

		</form>
		<t>Click here to <a href="/signup">Create an Account</a>
		<t>
		<br>
		<br>
		<t>
		<a href="/easybuy/emailverification">Forgot Password</a>
		<t>
	</div>

</body>
</html>