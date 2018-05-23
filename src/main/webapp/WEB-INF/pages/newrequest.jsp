<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 13.05.18
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://localhost:8080/" %>
<%@ taglib prefix="mct" uri="/WEB-INF/tld/schedule.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Haircut request</title>

    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/moment.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/bootstrap-datetimepicker.min.js"></script>
    <script src="resources/js/main.js"></script>

    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-datetimepicker.min.css">

</head>
<body>
    <jsp:include page="header.jsp"/>


    <div class="container">
        <div class="row">

            <div class="col-xs-8">
                <mct:schedule masterId="3"/>
            </div>

            <div class="col-xs-4">
                <form method="post" action="/app?command=AddRequest">
                    <div class="form-group">
                        <label for="master_id">Master: </label>
                        <select name="master_id" id="master_id" class="form-control">
                            <c:forEach var="master" items="${masters}">
                                <option value="${master.id}">
                                        ${master.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <div  class="input-group date"  id="datetimepicker" >
                            <input name="date" type='text' class="form-control" />
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-info">Add</button>

                </form>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
