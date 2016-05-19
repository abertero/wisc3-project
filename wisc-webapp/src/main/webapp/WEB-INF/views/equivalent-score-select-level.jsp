<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="equivalentScore.selectLevel.title"/></h1>
<p><spring:message code="equivalentScore.selectLevel.message"/></p>

<div>
    <ul>
        <c:forEach var="level" items="${levels}">
            <li><a href="${ctx}/definition/score/equivalent/view/${level.altKey}"><c:out value="${level.name}"/> (<c:out
                    value="${level.description}"/>)</a>&nbsp;&nbsp;<a href="${ctx}/definition/score/equivalent/edit/${level.altKey}"><span class="glyphicon glyphicon-edit"
                                                                                                                                           aria-hidden="true"
                                                                                                                                           aria-label="<spring:message code="equivalentScore.selectLevel.edit"/>"></span></a></li>
        </c:forEach>
    </ul>
</div>

</body>
</html>