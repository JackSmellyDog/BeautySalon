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

            <c:forEach var="request" items="${requests}" begin="${(page-1) * itemsPerPage}" end="${page * itemsPerPage - 1}">
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

        <div class="row">
            <div class="col-xs-6 col-xs-offset-4">
            <nav aria-label="Page navigation example" >
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="/app?command=RequestPage&page=${page > 1? page - 1 : page}">
                            Previous
                        </a>
                    </li>
                    <c:set var="endPageNumber" value="${amount % itemsPerPage == 0? amount / itemsPerPage : amount / itemsPerPage + 1}"/>
                    <c:choose>
                        <c:when test="${amount / itemsPerPage <= 10}">
                            <c:forEach var="i"
                                       begin="1"
                                       end="${endPageNumber}">
                                <li class="page-item"><a class="page-link" href="/app?command=RequestPage&page=${i}">${i}</a></li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                                <li class="page-item"><a class="page-link" href="#">...</a></li>
                        </c:otherwise>
                    </c:choose>

                    <li class="page-item">
                        <a class="page-link" href="/app?command=RequestPage&page=${page >= 1 and page < endPageNumber - 1 ? page + 1 : page}">
                            Next
                        </a>
                    </li>
                </ul>
            </nav>
            </div>
        </div>

    </jsp:body>
</t:genericpage>