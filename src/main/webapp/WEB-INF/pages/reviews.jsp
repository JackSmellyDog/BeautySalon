<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<t:genericpage>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/pages/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="/WEB-INF/pages/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>

        <c:set var="amount" value="${fn:length(reviews)}"/>
        <c:set var="page" value="${page == null? 1 : page}"/>
        <c:set var="itemsAmount" value="${amount == null? 1 : amount}"/>
        <c:set var="itemsPerPage" value="5"/>
        <c:set var="endPageNumber" value="${itemsAmount % itemsPerPage == 0? itemsAmount / itemsPerPage : itemsAmount / itemsPerPage + 1}"/>
        <div style="min-height: 100%">
        <table class="table table-striped">
            <thead>
            <tr>
                <th><fmt:message key="label.id"/></th>
                <th><fmt:message key="label.text"/></th>
                <th><fmt:message key="label.rating"/></th>
                <th><fmt:message key="label.request.id"/></th>
                <th><fmt:message key="label.master"/></th>
                <th><fmt:message key="label.client"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="review" items="${reviews}" begin="${(page-1) * itemsPerPage}" end="${page * itemsPerPage - 1}">
                <tr>
                    <td>${review.id}</td>
                    <td>${review.text}</td>
                    <td>${review.rating}</td>
                    <td>${review.request.id}</td>
                    <td>${review.request.master.name}</td>
                    <td>${review.request.client.login}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="row" style="margin-left: 0; margin-right: 0;">
            <div class="col-xs-6 col-xs-offset-5">
                <nav aria-label="Page navigation example" >
                    <ul class="pagination">
                        <li class="page-item">
                            <a class="page-link" href="/app?command=ReviewPage&page=${page > 1? page - 1 : page}">
                                Previous
                            </a>
                        </li>

                        <c:choose>
                            <c:when test="${amount / itemsPerPage <= 10}">
                                <c:forEach var="i"
                                           begin="1"
                                           end="${endPageNumber}">
                                    <li class="page-item"><a class="page-link" href="/app?command=ReviewPage&page=${i}">${i}</a></li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="#">...</a></li>
                            </c:otherwise>
                        </c:choose>

                        <li class="page-item">
                            <a class="page-link" href="/app?command=ReviewPage&page=${page >= 1 and page < endPageNumber - 1 ? page + 1 : page}">
                                Next
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        </div>

    </jsp:body>
</t:genericpage>