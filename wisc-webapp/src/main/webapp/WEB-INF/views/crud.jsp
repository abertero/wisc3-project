<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="home.title"/></h1>

<div>
    <ul>
        <li><a href="${ctx}/utils/ageCalculator"><spring:message code="home.ref.ageCalculator"/></a></li>
        <li><a href="${ctx}/definitions/equivalentScores"><spring:message
                code="home.ref.definitions.equivalentScores"/></a></li>
    </ul>
</div>

</body>
</html>