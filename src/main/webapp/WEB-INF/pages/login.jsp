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
</head>
<body>
<fieldset>
    <legend>Login</legend>
    ${message} <br>
    <form method="post" action="/front?command=Login">
        <table cellpadding="2" cellspacing="2">
            <tr>
                <td>Username: </td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>&nbsp; </td>
                <td><input type="submit" value="Login"></td>
            </tr>
        </table>
    </form>
</fieldset>

<br>

<a href="../../registration.jsp">Registration</a>


</body>
</html>
