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
<head>
    <title>Title</title>
</head>

<jsp:include page="header.jsp"/>

<body>
    <p>Wow! You are logged in!</p><br>
    <a href="/app?command=MasterPage">Masters</a><br>
    <a href="/app?command=RequestPage">Requests</a><br>
    <a href="/app?command=AddRequest">New Request</a><br>

<jsp:include page="footer.jsp"/>

</body>
</html>
