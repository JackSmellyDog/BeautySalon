<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mct" uri="/WEB-INF/tld/schedule.tld" %>

<t:genericpage>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/pages/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="/WEB-INF/pages/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <div class="container" style="min-height: 100%">
            <div class="row">
                <div class="col-xs-9">
                    <c:forEach var="master" items="${masters}" varStatus="loop">
                        <div id="schedule_${master.id}" class="${chosen_master_id == null and loop.index == 0? 'visible' : master.id == chosen_master_id? 'visible' : 'hidden'}">
                            <mct:schedule masterId="${master.id}" clientId="${user.id}" lang="${lang}"/>
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

                        <p class="text-danger">${error}</p>
                        <button type="submit" class="btn btn-info"><fmt:message key="button.add"/></button>

                    </form>
                </div>
            </div>
        </div>
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

    </jsp:body>
</t:genericpage>