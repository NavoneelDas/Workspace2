<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="Header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/resources/css/registration.css'></c:url>">
<script type="text/javascript"
    src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    
<script type="text/javascript">

function fillShippingAddress(form){
	if(form.shippingaddressform.checked==true){
		alert('checked')
	
		form["shippingaddress.apartmentnumber"].value=form["billingaddress.apartmentnumber"].value
		form["shippingaddress.streetname"].value=form["billingaddress.streetname"].value
		form["shippingaddress.city"].value=form["billingaddress.city"].value
		form["shippingaddress.state"].value=form["billingaddress.state"].value
		form["shippingaddress.country"].value=form["billingaddress.country"].value
		form["shippingaddress.zipcode"].value=form["billingaddress.zipcode"].value
	}
	if(form.shippingaddressform.checked==false){
		
		form["shippingaddress.apartmentnumber"].value=""
		form["shippingaddress.streetname"].value=""
		form["shippingaddress.city"].value=""
		form["shippingaddress.state"].value=""
		form["shippingaddress.country"].value=""
		form["shippingaddress.zipcode"].value=""
	}
}
$(document).ready(function(){
	$('#form').validate({
		rules:{
			firstname:{required:true},
			lastname:{required:true},
			phonenumber:{required:true,number:true,minlenght:10,maxlenght:10},
			"user.email":{required:true,email:true},
			"user.password":{required:true,minlenght:8},
			"billingaddress.apartmentnumber":{required:true},
			"billingaddress.streetname":{required:true},
			"billingaddress.city":{required:true},
			"billingaddress.state":{required:true},
			"billingaddress.country":{required:true},
			"billingaddress.zipcode":{required:true}
		},
		messages:{
			firstname:{required:"This is required"},
			lastname:{required:"This is required"},
			phonenumber:{required:"Should be phonenumber",number:"not valid"},
			"user.email":{required:"Should be email",email:"not valid"},
			"user.password":{required:"It is required",minlenght:"Should be atleast 8 characters"},
			"billingaddress.apartmentnumber":{required:"This is compulsary"},
			"billingaddress.streetname":{required:"This is compulsary"},
			"billingaddress.city":{required:"This is compulsary"},
			"billingaddress.state":{required:"This is compulsary"},
			"billingaddress.country":{required:"This is compulsary"},
			"billingaddress.zipcode":{required:"This is compulsary"}
		}
	})
})
</script>
</head>
<body>
<div class="container">
<c:url value="/all/register" var="url"></c:url>
<form:form modelAttribute="customer" action="${url }" id="form">

<form:hidden path="id"/>

<form:label path="firstname">Enter Firstname</form:label>
<form:input path="firstname" id="firstname"/>


<form:label path="lastname">Enter Lastname</form:label>
<form:input path="lastname" id="lastname"/>


<form:label path="phonenumber">Enter Phonenumber</form:label>
<form:input path="phonenumber" id="phonenumber"/>

<hr>
<b>Login Credentials</b><br>
<form:label path="user.email">Enter Email</form:label>
<form:input path="user.email" id="user.email" type="email"/>
<span style="color:orange">${error }</span>
<form:label path="user.password">Enter password</form:label>
<form:input path="user.password" id="user.password" type="password"/>

<hr>
<b>Billing Address</b><br>

<form:label path="billingaddress.apartmentnumber">Enter Apartmentnumber</form:label>
<form:input path="billingaddress.apartmentnumber" id="billingaddress.apartmentnumber"/>

<form:label path="billingaddress.streetname">Enter streetname</form:label>
<form:input path="billingaddress.streetname" id="billingaddress.streetname"/>

<form:label path="billingaddress.city">Enter city</form:label>
<form:input path="billingaddress.city" id="billingaddress.city"/>

<form:label path="billingaddress.state">Enter state</form:label>
<form:input path="billingaddress.state" id="billingaddress.state"/>

<form:label path="billingaddress.country">Enter country</form:label>
<form:input path="billingaddress.country" id="billingaddress.country"/>

<form:label path="billingaddress.zipcode">Enter Zipcode</form:label>
<form:input path="billingaddress.zipcode" id="billingaddress.zipcode"/>
<hr>
<b>Shipping address</b><br>
Check if shipping address is equal to billing address
<input type="checkbox" onclick="fillShippingAddress(this.form)" id="shippingaddressform">
<form:label path="shippingaddress.apartmentnumber">Enter Apartmentnumber</form:label>
<form:input path="shippingaddress.apartmentnumber" id="shippingaddress.apartmentnumber"/>

<form:label path="shippingaddress.streetname">Enter streetname</form:label>
<form:input path="shippingaddress.streetname" id="shippingaddress.streetname"/>

<form:label path="shippingaddress.city">Enter city</form:label>
<form:input path="shippingaddress.city" id="shippingaddress.city"/>

<form:label path="shippingaddress.state">Enter state</form:label>
<form:input path="shippingaddress.state" id="shippingaddress.state"/>

<form:label path="shippingaddress.country">Enter country</form:label>
<form:input path="shippingaddress.country" id="shippingaddress.country"/>

<form:label path="shippingaddress.zipcode">Enter zipcode</form:label>
<form:input path="shippingaddress.zipcode" id="shippingaddress.zipcode"/>
<br>
<input type="submit" value="Register">

</form:form>
</div>
</body>
</html>