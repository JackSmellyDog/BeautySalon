<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav class="navbar navbar-default">


    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="resources/img/owl2.png">
            </a>
        </div>
        <ul class="nav navbar-nav navbar-left">
            <li class="active"><a href="/app?command=HomePage"><fmt:message key="label.home" bundle="${lang}"/></a></li>
            <li><a href="/app?command=MasterPage"><fmt:message key="label.masters" bundle="${lang}"/></a></li>

            <c:if test="${role != null}">
                <li><a href="/app?command=RequestPage"><fmt:message key="label.requests" bundle="${lang}"/></a></li>
            </c:if>

            <li><a href="/app?command=AddRequest"><fmt:message key="label.new.request" bundle="${lang}"/></a></li>


            <li><a href="/app?command=ReviewPage"><fmt:message key="label.reviews" bundle="${lang}"/></a></li>
            <li><a href="/app?command=AddReview"><fmt:message key="label.new.review" bundle="${lang}"/></a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${user != null}">
                    <li><a href="/app?command=Logout"><fmt:message key="button.logout" bundle="${lang}"/></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/app?command=Login"><fmt:message key="button.login" bundle="${lang}"/></a></li>
                    <li><a href="/app?command=Register"><fmt:message key="button.sign.in" bundle="${lang}"/></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>




