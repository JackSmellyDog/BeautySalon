<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 13.05.18
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Haircut request</title>
</head>
<body>
    <fieldset>
        <legend>New Request</legend>
        <form method="post" action="/app?command=NewRequest">
            <table cellpadding="2" cellspacing="2">
                <tr>
                    <td>Master: </td>
                    <td>

                        <select name="master_id">
                            <c:forEach var="master" items="${masters}">
                                <option value="${master.id}">
                                        ${master.name}
                                </option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>

                <tr>
                    <td>Data: </td>
                    <td><input type="date" name="date"></td>
                </tr>

                <tr>
                    <td>Time: </td>
                    <td><input type="time" name="time"></td>
                </tr>

                <tr>
                    <td>&nbsp; </td>
                    <td><input type="submit" value="Add"></td>
                </tr>
            </table>
        </form>
    </fieldset>
</body>
</html>
