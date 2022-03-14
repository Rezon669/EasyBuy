<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/app.css"/>
<title>Welcome</title>
</head>

<body>
<h1>Welcome...!</h1>
  <div class="home">
  <form id="home" method="get" action="#">   
       <label for="home"><b>Select any one option:</b></label><br><br>

				
					<input type="button" onclick="window.location.href='/addproduct';" value="Add Product" />	
					<br><br>
				
						
						<input type="button" onclick="window.location.href='/listofproducts';" value="List of Products" />
					<br><br>
					
					
					<input type="button" onclick="window.location.href='/searchproduct';" value="Search Product" />
					<br><br>
				
	</ul>

</form>
  </div>
      
</body>
</html>
