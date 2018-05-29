<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/pages/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="/WEB-INF/pages/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <form method="post" action="/app?command=Test" enctype="multipart/form-data">
            <input type="file" name="avatar" size="50">
            <input type="submit" value="upload">
        </form>




    </jsp:body>
</t:genericpage>