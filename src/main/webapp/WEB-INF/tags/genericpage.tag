<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<fmt:setLocale value="en" scope="session"/>
<fmt:setBundle basename="EN_messages" scope="session"/>

<html>
<head>
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/moment.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/bootstrap-datetimepicker.min.js"></script>
    <script src="resources/js/main.js"></script>

    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-datetimepicker.min.css">
</head>

<body>
<div id="pageheader">
    <jsp:invoke fragment="header"/>
</div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="pagefooter">
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>