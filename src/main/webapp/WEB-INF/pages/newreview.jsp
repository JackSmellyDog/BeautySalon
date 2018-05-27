<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:genericpage>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/pages/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="/WEB-INF/pages/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <div class="row" style="width: 55%">
            <div class="col-xs-8 col-xs-offset-7">
                <form method="post" action="/app?command=AddReview">
                    <div class="form-group">
                        <label for="req"><fmt:message key="label.request"/>: </label>
                        <input class="form-control" id="req" value="${for_review.date.year}-${for_review.date.month}-${for_review.date.dayOfMonth} ${for_review.date.hour}:00" disabled>
                    </div>
                    <div class="form-group">
                        <label for="master"><fmt:message key="label.master"/>: </label>
                        <input class="form-control" id="master" value="${for_review.master.name}" disabled>
                    </div>
                    <div class="form-group">
                        <label for="rating"><fmt:message key="label.mark"/></label>
                        <select id="rating" name="rating" class="form-control">
                            <option value="1"> 1 </option>
                            <option value="2"> 2 </option>
                            <option value="3"> 3 </option>
                            <option value="4" selected> 4 </option>
                            <option value="5"> 5 </option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="text"><fmt:message key="label.leave.review"/></label>
                        <textarea name="text" id="text" class="form-control" rows="7"></textarea>
                    </div>
                    <button type="submit" class="btn btn-info"><fmt:message key="button.add"/></button>
                </form>
            </div>
            <div>${message}</div>
        </div>
    </jsp:body>
</t:genericpage>