<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 12.05.18
  Time: 1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body class="freaking-ping">

<div class="pin-to-center auth-form">
    <form method="post" action="/app?command=Register">
        <div class="form-group">
            <label for="username"><fmt:message key="label.username"/> </label>
            <input type="text" name="username" id="username" class="form-control" placeholder="example@example.com">
        </div>

        <div class="form-group">
            <label for="password"><fmt:message key="label.password"/> </label>
            <input type="password" name="password" id="password" class="form-control" placeholder="Password">
        </div>

        <div class="form-group">
            <label for="password"><fmt:message key="label.confirm.password"/> </label>
            <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" placeholder="Confirmation Password">
        </div>

        <button type="submit" class="btn btn-info"><fmt:message key="button.sign.in"/></button>
    </form>
    <div class="text-danger">${message}</div>
</div>

</body>
</html>
