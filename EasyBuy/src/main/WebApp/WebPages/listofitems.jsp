<%@page import="com.easybuy.app.Additem"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/listofitems.css"/>
<title>listofproducts</title>
</head>
<body>
<h2>List of Products</h2>
 

<!-- <a  href="#"> Click here to apply Filter</a> <br></br> -->

<table border="2">

		<th>id</th>
		<th>Product Name</th>
		<th>Price</th>
		<th>Quantity</th>
		<th>Category</th>
 <%
 List<Additem> s = (List<Additem>)request.getAttribute("list");
 for(int c=0; c<s.size(); c++){
	 out.print("<tr> ");
	 out.print("<td>"+ s.get(c).getId() + "</td>");
	 out.print("<td>"+ s.get(c).getName() + "</td>");
	 out.print("<td>"+ s.get(c).getPrice() + "</td>");
	 out.print("<td>"+ s.get(c).getQuantity() + "</td>");
	 out.print("<td>"+ s.get(c).getCategory() + "</td>");
     out.print(" <tr>");
 }
 %> 
</table>
<h4><a href="/app">click here</a> to go back</h4> 
</body>
</html>