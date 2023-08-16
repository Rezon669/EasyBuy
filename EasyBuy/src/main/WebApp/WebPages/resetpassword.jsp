<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>reset password</title>
<link rel="stylesheet" type="text/css"
	href="/static/css/resetpassword.css" />
</head>
<body>
	<h2>Please reset your password</h2>
	<div class="emailverify">
		<form method="post" action="/easybuy/resetpassword">


			<p>${errorMessage}</p>

			<label>Password</label><br> <input type="text" name="password"
				id="password" placeholder="Enter Password"><br>
			<br> <label>Confirm Password</label><br> <input
				type="password" name="confirmpassword" id="confirmpassword"
				placeholder="Confirm Password"><br>
			<br> <input id="submit" type="submit" value="Submit"><br>
			<br>


		</form>
	</div>
</body>
</html>