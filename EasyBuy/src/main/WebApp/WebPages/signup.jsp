<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>create-account</title>
<link rel="stylesheet" type="text/css" href="css/signup.css"/>
</head>
<body>
<h2>Enter your details to create Account</h2>
<div class="signup">
<form method="get" action="/easybuy/createaccount">


    <p>${errorMessage}</p>
 <label>Name</label><br>
 <input type="text" name="username" id="username"><br><br>
 
 <label>Mobile Number</label><br>
 <input type="text" name="mobilenumber" id="mobilenumber"><br><br>
 
 <label>Address</label><br>
 <input type="text" name="address" id="address"><br><br>
 
 <label>Password</label><br>
 <input type="password" name="password" id="password"><br><br>

<input id="submit" type="submit" value="Save"><br><br>


</form>

<a href="/easybuy/signin">Already have an Account</a>
</div>

</body>
</html>