<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 24.04.18
  Time: 3:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head class="">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/main.js"></script>
    <script>
        $('#carouselControls').carousel({
            interval: 2000
        });
    </script>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container" style="width:700px; height: 600px">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <div class="item active">
                <img src="resources/img/beast.jpeg" alt="Los Angeles" style="width:100%;">
            </div>

            <div class="item">
                <img src="resources/img/beast.jpeg" alt="Chicago" style="width:100%;">
            </div>

            <div class="item">
                <img src="resources/img/beast.jpeg" alt="New york" style="width:100%;">
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>

<jsp:include page="footer.jsp"/>


</body>
</html>
