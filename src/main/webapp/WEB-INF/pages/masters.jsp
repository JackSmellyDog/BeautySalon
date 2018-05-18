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
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="header.jsp"/>

    <table cellpadding="2" cellspacing="2" >
        <c:forEach var="master" items="${masters}">
            <tr>
                <td>${master.name}</td>
                <td>${master.login}</td>
                <td>${master.description}</td>
                <td><a href="#">Оформить стрижку</a></td>
                <td><a href="/app?command=DeleteMaster&id=${master.id}">X</a></td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <br>

    <fieldset>
        <legend>Add master</legend>
        <form method="post" action="/app?command=AddMaster">
            <table cellpadding="2" cellspacing="2">
                <tr>
                    <%--Should add type - email--%>
                    <td>Username:</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>Confirm password: </td>
                    <td><input type="password" name="confirmPassword"></td>
                </tr>

                <tr>
                    <td>Last & First Name: </td>
                    <td><input type="text" name="last_first_name"></td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><textarea rows="5" name="description"></textarea></td>
                </tr>
                <tr>
                    <td>&nbsp; </td>
                    <td><input type="submit" value="Add"></td>
                </tr>
            </table>
        </form>
    </fieldset>
    <jsp:include page="footer.jsp"/>
</body>
</html>
