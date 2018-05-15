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
</head>
<body>
<fieldset>
    <legend>Login</legend>
    ${message} <br>
    <form method="post" action="/app?command=Code">
        <table cellpadding="2" cellspacing="2">
            <tr>
                <td>Code: </td>
                <td><input type="text" name="code"></td>
            </tr>
            <tr>
                <td>&nbsp; </td>
                <td><input type="submit" value="Send"></td>
            </tr>
        </table>
    </form>
</fieldset>
</body>
</html>
