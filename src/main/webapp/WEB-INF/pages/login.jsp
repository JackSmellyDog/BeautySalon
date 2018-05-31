<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 24.04.18
  Time: 3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${lang == 'EN'}">
        <fmt:setLocale value="EN" scope="session"/>
        <fmt:setBundle basename="EN_messages" scope="session"/>
    </c:when>
    <c:when test="${lang == 'UA'}">
        <fmt:setLocale value="UA" scope="session"/>
        <fmt:setBundle basename="UA_messages" scope="session"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="EN" scope="session"/>
        <fmt:setBundle basename="EN_messages" scope="session"/>
    </c:otherwise>
</c:choose>

<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body class="freaking-ping">
<div class="pin-to-center auth-form">
    <div>
        <a href="/app?command=SetLanguage&lang=UA"><img src="/resources/img/flags/ukraine.png"></a>
        <a href="/app?command=SetLanguage&lang=EN"><img src="/resources/img/flags/uk.png"></a>
    </div>

    <form method="post" action="/app?command=Login">
        <div class="form-group">
            <label for="username"><fmt:message key="label.username"/></label>
            <input type="text" name="username" id="username" class="form-control" placeholder="example@example.com">
        </div>

        <div class="form-group">
            <label for="password"><fmt:message key="label.password"/></label>
            <input type="password" name="password" id="password" class="form-control" placeholder="password">
        </div>

        <button type="submit" class="btn btn-info"><fmt:message key="button.login"/></button>
    </form>
    <div class="text-danger">${message}</div>

    <div class="text-center">
        <a href="/app?command=Register" ><fmt:message key="button.registration"/></a>
    </div>

</div>

<br>




</body>
</html>
