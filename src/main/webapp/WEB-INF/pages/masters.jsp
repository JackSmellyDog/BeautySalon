<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 12.05.18
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Masters</title>
</head>
<body>
    <table cellpadding="2" cellspacing="2" >
        <c:forEach var="master" items="${masters}">
            <tr>
                <td>${master.name}</td>
                <td>${master.login}</td>
                <td>${master.description}</td>
                <td><a href="#">Оформить стрижку</a></td>
                <td><a href="#">X</a></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
