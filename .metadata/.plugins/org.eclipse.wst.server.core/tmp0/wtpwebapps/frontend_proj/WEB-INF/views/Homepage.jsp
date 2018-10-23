<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="Header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width,initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-fluid">
<div id="myCarousel" class="carousel slide" data-ride="carousel">
           
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
                <!--  <li data-target="#myCarousel" data-slide-to="3"></li>-->
            </ol>
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img class="first-slide home-image" src="resources/images/Furniture.jpg" alt="first slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>WORLD'S GORGEOUS FURNITURES</h1>
                            <p>Furnitures which will make home paradise </p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="second-slide home-image" src="resources/images/HTML CSS book.jpg" alt="Second slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>NEW TECHNOLOGY BOOKS FOR BEGINEER'S</h1>
                            <p>The in-depth knowledge will help understand better</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="third-slide home-image " src="resources/images/Tshirt.jpg" alt="Third slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>NEW AND EXCITING OUTLOOKS</h1>
                            <p>Beautiful artistic work at much affordable price</p>
                        </div>
                    </div>
                </div>
                 
            </div>
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" ></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

</div>
</body>
</html>