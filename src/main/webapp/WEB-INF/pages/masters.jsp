<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 12.05.18
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>

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
    <jsp:include page="/WEB-INF/pages/header.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-xs-8">
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
            </div>

        <c:if test="${role == 'Admin'}">

            <div class="col-xs-4">
                <form method="post" action="/app?command=AddMaster">
                    <div class="form-group">
                        <label for="username"><fmt:message key="label.username" bundle="${lang}"/></label>
                        <input type="email" name="username" id="username" class="form-control" placeholder="example@example.com">
                    </div>

                    <div class="form-group">
                        <label for="password"><fmt:message key="label.password" bundle="${lang}"/></label>
                        <input type="password" name="password" id="password" class="form-control" placeholder="password">
                    </div>

                    <div class="form-group">
                        <label for="password"><fmt:message key="label.confirm.password" bundle="${lang}"/></label>
                        <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" placeholder="Confirmation Password">
                    </div>

                    <div class="form-group">
                        <label for="last_first_name"><fmt:message key="label.last.first.name" bundle="${lang}"/></label>
                        <input type="text" name="last_first_name" id="last_first_name" class="form-control" placeholder="Ivan Ivanov">
                    </div>

                    <div class="form-group">
                        <label for="description"><fmt:message key="label.short.info" bundle="${lang}"/></label>
                        <textarea name="description" id="description" class="form-control" rows="5"></textarea>
                    </div>
                    <button type="submit" class="btn btn-info"><fmt:message key="button.add" bundle="${lang}"/></button>
                </form>
                <div>${message}</div>
            </div>

        </c:if>
        </div>

    </div>
    <jsp:include page="WEB-INF/pages/footer.jsp"/>
</body>
</html>
