<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>createaccount</title>
</head>   
<body>    
    <h2>Enter your Details to Create Account</h2><br>    
    <div class="login">    
    <form id="login" method="post" action ="detailss">    
        <label><b>User Name     
        </b>    
        </label>    
        <input type="text" name="uname" id="uname" placeholder=" Enter Username">    
        <br><br>
<label><b>Email id     
        </b>    
        </label>    
        <input type="text" name="uemail" id="uemail" placeholder="Enter email id / Phone no">    
        <br><br> 
        <label><b>Phone no     
        </b>    
        </label>    
        <input type="text" name="uphno" id="uphno" placeholder="Enter email id / Phone no">    
        <br><br>    
        <label><b>Password     
        </b>    
        </label>    
        <input type="Password" name="pass" id="pass" placeholder=" Enter Password">    
        <br><br>
<label><b>Confirm Password     
        </b>    
        </label>    
        <input type="Password" name="repass" id="repass" placeholder="Reenter Password">    
        <br><br>
   
        <input type="submit" name="log" id="log" value="Create Account"  >
      
        <br><br>    
        <input type="checkbox" id="check">    
        <span>Remember me</span>   
    </form>     
</div>    
</body>    
</html>
