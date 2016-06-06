<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="evaluation.listChildren.title"/></h1>

<p><spring:message code="evaluation.listChildren.message"/></p>

<div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th><spring:message code="evaluation.listChildren.name"/></th>
            <th><spring:message code="evaluation.listChildren.age"/></th>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${children}" var="child">
            <tr>
                <td><c:out value="${child.displayName}"/></td>
                <td><c:out value="${child.displayAge}"/></td>
                <td><a href="${ctx}/evaluation/child/${child.altKey}"><span class="glyphicon glyphicon-edit"
                                                                           aria-hidden="true"
                                                                           aria-label="<spring:message code="evaluation.listChildren.edit"/>"></span></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button class="btn btn-primary" type="button" onclick="location.href='${ctx}/crud/edit/${entity}'"><spring:message
            code="evaluation.listChildren.add"/></button>
</div>

</body>
</html>