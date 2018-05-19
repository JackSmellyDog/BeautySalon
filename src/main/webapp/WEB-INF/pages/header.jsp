<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Beauty Salon</a>
        </div>
        <ul class="nav navbar-nav navbar-left">
            <li class="active"><a href="/app?command=HomePage">Home</a></li>
            <li><a href="/app?command=MasterPage">Masters</a></li>

            <c:if test="${role != null && role != 'Client'}">
                <li><a href="/app?command=RequestPage">Requests</a></li>
            </c:if>

            <li><a href="/app?command=AddRequest">New Request</a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${user != null}">
                    <li><a href="/app?command=Logout">Log out</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/app?command=Login">Log in</a></li>
                    <li><a href="/app?command=Register">Sign in</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>




