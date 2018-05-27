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
                <th><fmt:message key="label.text"/></th>
                <th><fmt:message key="label.rating"/></th>
                <th><fmt:message key="label.request.id"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="review" items="${reviews}">
                <tr>
                    <td>${review.id}</td>
                    <td>${review.text}</td>
                    <td>${review.rating}</td>
                    <td>${review.request.id}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:genericpage>