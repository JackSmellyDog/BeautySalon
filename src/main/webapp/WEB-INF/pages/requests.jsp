<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 15.05.18
  Time: 2:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requests</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>

<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th>
        <th>Date & Time</th>
        <th>Client ID</th>
        <th>Master ID</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="request" items="${requests}">
        <tr>
            <td>${request.id}</td>
            <td>${request.date}</td>
            <td>${request.client}</td>
            <td>${request.master}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<jsp:include page="footer.jsp"/>


</body>
</html>
