<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/product.css"/>
<title>additem</title>
</head>
<body>
<h2>Enter your Product details</h2>
<div class="addproduct">
<form method="get" action= "/saveproduct">

 <label>Product name</label><br>
 <input type="text" name="name" id="name"><br><br>
 
 <label>Price</label><br>
 <input type="text" name="price" id="price"><br><br>
 
 <label>Quantity</label><br>
 <input type="text" name="quantity" id="quantity"><br><br>
 
 <label>Category</label><br>
 <select name="category" id="category">
  <option value="select one">select one</option>
  <option value="grocery">Grocery</option>
  <option value="Mobiles">Mobiles</option>
  <option value="Fashion">Fashion</option>
  <option value="Electronics">Electronics</option>
  <option value="Home">Home</option>
  <option value="Furniture">Furniture</option>
  <option value="Beauty">Beauty</option>
  <option value="Sports">Sports</option>
  </select><br><br>
  
  <label>Search Keyword</label><br>
 <input type="text" name="searchkeyword" id="searchkeyword"><br><br>

<input id="submit" type="submit" value="Save">
</form>
</div>

</body>
</html>