<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 13.05.18
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <style>
        .hidden {
            visibility: hidden;
        }
        .visible {
            visibility: visible;
        }
    </style>

</head>
<body>
    <jsp:include page="header.jsp"/>


    <div class="container">
        <div class="row">
            <div class="col-xs-9">
                <c:forEach var="master" items="${masters}" varStatus="loop">
                    <div id="schedule_${master.id}" class="${chosen_master_id == null and loop.index == 0? 'visible' : master.id == chosen_master_id? 'visible' : 'hidden'}">
                        <mct:schedule masterId="${master.id}" clientId="${user.id}"/>
                    </div>
                </c:forEach>
            </div>

            <div class="col-xs-3">
                <form method="post" action="/app?command=AddRequest">
                    <div class="form-group">
                        <label for="master_id"><fmt:message key="label.master"/>: </label>
                        <select name="master_id" id="master_id" class="form-control">
                            <c:forEach var="master" items="${masters}" varStatus="loop">
                                <option value="${master.id}"
                                    <c:if test="${(chosen_master_id == null and loop.index == 0) or master.id == chosen_master_id}">
                                        selected
                                    </c:if>
                                >
                                        ${master.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="datetimepicker"><fmt:message key="label.data.time"/>: </label>
                        <div  class="input-group date"  id="datetimepicker" >
                            <input name="date" type='text' class="form-control" />
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-info"><fmt:message key="button.add"/></button>

                </form>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
    <script>
        $( document ).ready(function() {

            $('#master_id').on('change', function () {
                var tableId = '#schedule_' + $(this).val();

                $(this.options).each(function () {
                    var id = '#schedule_' + $(this).val();
                    $(id).addClass('hidden');

                    console.log($(this).val())
                });

                $(tableId).removeClass('hidden');
            });
        });
    </script>
</body>
</html>
