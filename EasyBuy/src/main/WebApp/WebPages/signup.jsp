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
<form method="post" action= "/createaccount">

 <label>Name</label><br>
 <input type="text" name="name" id="name"><br><br>
 
 <label>Mobile Number</label><br>
 <input type="text" name="mobilenummber" id="mobilenummber"><br><br>
 
 <label>Address</label><br>
 <input type="text" name="address" id="address"><br><br>
 
 <label>Password</label><br>
 <input type="password" name="password" id="password"><br><br>

<input id="submit" type="submit" value="Save"><br><br>


</form>

</div>

</body>
</html>