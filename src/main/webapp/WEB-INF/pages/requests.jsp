<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:genericpage>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/pages/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="/WEB-INF/pages/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <table class="table table-striped">
            <thead>
            <tr>
                <th><fmt:message key="label.id"/></th>
                <th><fmt:message key="label.data.time"/></th>
                <c:if test="${role != 'Client'}">
                    <th><fmt:message key="label.client"/></th>
                </c:if>
                <c:if test="${role != 'Master'}">
                    <th><fmt:message key="label.master"/></th>
                </c:if>
                <th><fmt:message key="label.status"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="request" items="${requests}">
                <tr>
                    <td>${request.id}</td>
                    <td>
                        ${request.date.year}-${request.date.month}-${request.date.dayOfMonth} ${request.date.hour}:00
                    </td>

                    <c:if test="${role != 'Client'}">
                        <td>${request.client.login}</td>
                    </c:if>
                    <c:if test="${role != 'Master'}">
                        <td>
                            <a href="#">${request.master.name}</a><br>
                            <p>${request.master.login}</p>
                        </td>
                    </c:if>
                    <td>
                        <c:choose>
                            <c:when test="${request.status == 'ACTIVE'}">
                                <p class="text-success">${request.status}</p>
                                <c:if test="${role != null and role != 'Client'}">
                                    <a href="/app?command=CompleteRequest&id=${request.id}" class="h6">
                                        <fmt:message key="button.complete"/>
                                    </a> |
                                </c:if>

                                <a href="/app?command=CancelRequest&id=${request.id}" class="h6">
                                    <fmt:message key="button.cancel"/>
                                </a>
                            </c:when>
                            <c:when test="${request.status == 'DONE'}">
                                <p class="text-primary">${request.status}</p>
                            </c:when>
                            <c:when test="${request.status == 'CANCELED'}">
                                <p class="text-danger">${request.status}</p>
                            </c:when>
                            <c:otherwise>
                                <p class="text-danger">---------</p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:genericpage>