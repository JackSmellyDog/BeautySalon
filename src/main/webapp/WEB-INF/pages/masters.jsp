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

    <table class="table table-striped">
        <thead>
            <tr>
                <th>Name</th>
                <th>Login</th>
                <th>Description</th>
                <c:if test="${role == 'Client'}">
                    <th>Order</th>
                </c:if>

                <c:if test="${role == 'Admin'}">
                    <th>Delete</th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="master" items="${masters}">
                <tr>
                    <td>${master.name}</td>
                    <td>${master.login}</td>
                    <td>${master.description}</td>

                    <c:if test="${role == 'Client'}">
                        <td><a href="/app?command=AddRequest" >Make order</a></td>
                    </c:if>

                    <c:if test="${role == 'Admin'}">
                        <td><a href="/app?command=DeleteMaster&id=${master.id}">X</a></td>
                    </c:if>
                </tr>
            </c:forEach>

        </tbody>
    </table>

    <br>
    <br>

    <c:if test="${role == 'Admin'}">

        <div>
            <form method="post" action="/app?command=AddMaster">
                <div class="form-group">
                    <label for="username">Username: </label>
                    <input type="email" name="username" id="username" class="form-control" placeholder="example@example.com">
                </div>

                <div class="form-group">
                    <label for="password">Password: </label>
                    <input type="password" name="password" id="password" class="form-control" placeholder="password">
                </div>

                <div class="form-group">
                    <label for="password">Confirm password: </label>
                    <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" placeholder="Confirmation Password">
                </div>

                <div class="form-group">
                    <label for="last_first_name">Last & First Name: </label>
                    <input type="text" name="last_first_name" id="last_first_name" class="form-control" placeholder="Ivan Ivanov">
                </div>

                <div class="form-group">
                    <label>Short information: </label>
                    <textarea name="description" id="description" class="form-control" rows="5"></textarea>
                </div>
                <button type="submit" class="btn btn-info">Add</button>
            </form>
            <div>${message}</div>
        </div>

    </c:if>

    <jsp:include page="footer.jsp"/>
</body>
</html>
