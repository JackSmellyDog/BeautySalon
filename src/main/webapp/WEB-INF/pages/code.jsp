<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 15.05.18
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="EN_messages" var="lang" scope="session"/>

<html>
<head>
    <title>Code</title>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body class="freaking-ping">
<div class="pin-to-center code-form">

    <%--<fmt:message key="id" bundle="${lang}"/>--%>
    <form method="post" action="/app?command=Code">

        <div class="form-group">
            <label for="code"><fmt:message key="label.code" bundle="${lang}"/></label>
            <input type="text" name="code" id="code" class="form-control" placeholder="0000">
        </div>

        <button type="submit" class="btn btn-info"><fmt:message key="button.send" bundle="${lang}"/></button>

    </form>
    <div class="text-danger">${message}</div>

    <div class="text-center">
        <a href="/app?command=Register" ><fmt:message key="button.registration" bundle="${lang}"/> </a>
    </div>
</div>
</body>
</html>
