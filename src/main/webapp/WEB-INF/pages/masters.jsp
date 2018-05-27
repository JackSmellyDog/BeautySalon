<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/pages/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="/WEB-INF/pages/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-xs-8">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th><fmt:message key="label.name"/></th>
                            <th><fmt:message key="label.login"/></th>
                            <th><fmt:message key="label.description"/></th>
                            <c:if test="${role == 'Client'}">
                                <th><fmt:message key="label.order"/></th>
                            </c:if>

                            <c:if test="${role == 'Admin'}">
                                <th><fmt:message key="label.delete"/></th>
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
                                    <td>
                                        <a href="/app?command=AddRequest">
                                            <fmt:message key="button.make.order"/>
                                        </a>
                                    </td>
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
                                <label for="username"><fmt:message key="label.username"/></label>
                                <input type="email" name="username" id="username" class="form-control" placeholder="example@example.com">
                            </div>

                            <div class="form-group">
                                <label for="password"><fmt:message key="label.password"/></label>
                                <input type="password" name="password" id="password" class="form-control" placeholder="password">
                            </div>

                            <div class="form-group">
                                <label for="password"><fmt:message key="label.confirm.password"/></label>
                                <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" placeholder="Confirmation Password">
                            </div>

                            <div class="form-group">
                                <label for="last_first_name"><fmt:message key="label.last.first.name"/></label>
                                <input type="text" name="last_first_name" id="last_first_name" class="form-control" placeholder="Ivan Ivanov">
                            </div>

                            <div class="form-group">
                                <label for="description"><fmt:message key="label.short.info"/></label>
                                <textarea name="description" id="description" class="form-control" rows="5"></textarea>
                            </div>
                            <button type="submit" class="btn btn-info"><fmt:message key="button.add"/></button>
                        </form>
                        <div>${message}</div>
                    </div>

                </c:if>
            </div>
        </div>
    </jsp:body>
</t:genericpage>