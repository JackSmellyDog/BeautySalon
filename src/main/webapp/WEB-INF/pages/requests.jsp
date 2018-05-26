<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 15.05.18
  Time: 2:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="EN_messages" var="lang" scope="session"/>
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
        <c:if test="${role != 'Client'}">
            <th>Client</th>
        </c:if>
        <c:if test="${role != 'Master'}">
            <th>Master</th>
        </c:if>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="request" items="${requests}">
        <tr>
            <td>${request.id}</td>
            <td>${request.date.year}-${request.date.month}-${request.date.dayOfMonth} ${request.date.hour}:00 </td>

            <c:if test="${role != 'Client'}">
                <td>${request.client.login}</td>
            </c:if>
            <c:if test="${role != 'Master'}">
                <td>
                    <a href="#">${request.master.name}</a><br>
                    <p>${request.master.login}</p>
                </td>
            </c:if>
            <td>
                <c:choose>
                    <c:when test="${request.status == 'ACTIVE'}">
                        <p class="text-success">${request.status}</p>
                        <a href="/app?command=CompleteRequest&id=${request.id}" class="h6">Complete</a> |
                        <a href="/app?command=CancelRequest&id=${request.id}" class="h6">Cancel</a>
                    </c:when>
                    <c:when test="${request.status == 'DONE'}">
                        <p class="text-primary">${request.status}</p>
                    </c:when>
                    <c:when test="${request.status == 'CANCELED'}">
                        <p class="text-danger">${request.status}</p>
                    </c:when>
                    <c:otherwise>
                        <p class="text-danger">---------</p>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<jsp:include page="footer.jsp"/>


</body>
</html>
