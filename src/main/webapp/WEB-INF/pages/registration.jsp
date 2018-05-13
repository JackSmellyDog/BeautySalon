<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 12.05.18
  Time: 1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Registration</title>
</head>
<body>

<fieldset>
    <legend>Registration</legend>
    ${message} <br>
    <form method="post" action="/app?command=Register">
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
                <td>Confirm password: </td>
                <td><input type="password" name="confirmPassword"></td>
            </tr>
            <tr>
                <td>&nbsp; </td>
                <td><input type="submit" value="Register"></td>
            </tr>
        </table>
    </form>
</fieldset>
</body>
</html>
