<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="home.title"/></h1>

<div>
    <ul>
        <li><a href="${ctx}/utils/ageCalculator"><spring:message code="home.ref.ageCalculator"/></a></li>
        <li><a href="${ctx}/crud/list/evaluacion"><spring:message
                code="home.ref.cruds.evaluacion"/></a></li>
        <li><a href="${ctx}/crud/list/childlevel"><spring:message
                code="home.ref.cruds.childlevel"/></a></li>
    </ul>
</div>

</body>
</html>