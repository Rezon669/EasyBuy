<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id="search" method="get" action="/searchproducts">

  <label>Search Keyword</label><br>
 <input type="text" name="searchkeyword" id="searchkeyword"><br><br>
<!-- 
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
  </select> --> 

<input type="submit" value="submit">
</form>
</body>
</html>