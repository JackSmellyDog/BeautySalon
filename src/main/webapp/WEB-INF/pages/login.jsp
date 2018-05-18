<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 24.04.18
  Time: 3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body class="freaking-ping">
<div class="pin-to-center">
    <form method="post" action="/app?command=Login">
        <div class="form-group">
            <label for="username">Username: </label>
            <input type="text" name="username" id="username" class="form-control" placeholder="example@example.com">
        </div>

        <div class="form-group">
            <label for="password">Password: </label>
            <input type="password" name="password" id="password" class="form-control" placeholder="password">
        </div>

        <button type="submit" class="btn btn-info">Log in</button>
    </form>
    <div>${message}</div>

    <div class="text-center">
        <a href="/app?command=Register" >Registration</a>
    </div>

</div>

<br>




</body>
</html>
