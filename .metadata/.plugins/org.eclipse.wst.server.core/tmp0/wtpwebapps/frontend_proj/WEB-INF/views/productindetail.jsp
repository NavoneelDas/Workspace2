<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="Header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="panel panel-info">
			<div class="panel panel-heading">Product Details</div>
			<div class="panel panel-body">
		<c:url value="/cart/addtocart/${productAttr.id }" var="url"></c:url>
		<form action="${url }">
			<img src="<c:url value='/resources/images/${productAttr.id }.png'></c:url>"  height="70px" width="50px">
			<br>
			<c:url value="/cart/addtocart/${productAttr.id }" var="url"></c:url>
			<form action="${url } ">
			<b>Product Name:</b> ${productAttr.productname }  <br>
			<b>Product Description</b> ${productAttr.description } <br>
			<b>Price </b> ${productAttr.price }  <br>
			
			<b>In Stock </b>${productAttr.quantity }  <br>
			
			<security:authorize access="hasRole('ROLE_USER')">
			<b>Enter Quantity:</b>
			<input type="number" min="1" max="${productAttr.quantity }" name="requestedQuantity">
			<br>
			<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-shopping-cart"></span>Add to cart</button>
			</security:authorize>
			
	<a href="<c:url value='/all/getallproducts'></c:url>">Back to previous page</a>
	</form>
	
			</div>
		</div>
	</div>
</body>
</html>