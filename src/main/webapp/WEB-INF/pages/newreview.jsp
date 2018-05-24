<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 19.05.18
  Time: 5:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Review</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="row" style="width: 55%">
    <div class="col-xs-8 col-xs-offset-7">
        <form method="post" action="/app?command=AddReview">
            <div class="form-group">
                <label for="req">Request: </label>
                <input class="form-control" id="req" value="${for_review.date.year}-${for_review.date.month}-${for_review.date.dayOfMonth} ${for_review.date.hour}:00" disabled>
            </div>
            <div class="form-group">
                <label for="master">Master: </label>
                <input class="form-control" id="master" value="${for_review.master.name}" disabled>
            </div>
            <div class="form-group">
                <label for="rating">Mark:  </label>
                <select id="rating" name="rating" class="form-control">
                    <option value="1"> 1 </option>
                    <option value="2"> 2 </option>
                    <option value="3"> 3 </option>
                    <option value="4" selected> 4 </option>
                    <option value="5"> 5 </option>
                </select>
            </div>
            <div class="form-group">
                <label for="text">Leave your review here: </label>
                <textarea name="text" id="text" class="form-control" rows="7"></textarea>
            </div>
            <button type="submit" class="btn btn-info">Add</button>
        </form>
    </div>
    <div>${message}</div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
