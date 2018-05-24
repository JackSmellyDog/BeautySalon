
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <th>Text</th>
        <th>Rating</th>
        <th>Request ID</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="review" items="${reviews}">
        <tr>
            <td>${review.id}</td>
            <td>${review.text}</td>
            <td>${review.rating}</td>
            <td>${review.request.id}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<jsp:include page="footer.jsp"/>


</body>
</html>
