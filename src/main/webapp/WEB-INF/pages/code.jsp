<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 15.05.18
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="pin-to-center">
    <form method="post" action="/app?command=Code">

        <div class="form-group">
            <label for="code">Code: </label>
            <input type="text" name="code" id="code" class="form-control" placeholder="0000">
        </div>

        <button type="submit" class="btn btn-info">Send</button>

    </form>
    <div>${message}</div>

    <div class="text-center">
        <a href="/app?command=Register" >Registration</a>
    </div>
</div>
</body>
</html>
