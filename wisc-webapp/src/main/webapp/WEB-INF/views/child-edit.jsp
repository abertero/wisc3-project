<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><c:out value="${child.displayName}"/></h1>

<div>
    <table class="table table-bordered">
        <tr>
            <th><spring:message code="evaluation.editChildren.age"/></th>
            <td><c:out value="${child.displayAge}"/></td>
            <th><spring:message code="evaluation.editChildren.birth"/></th>
            <td><c:out value="${child.birthDetail}"/></td>
        </tr>
        <tr>
            <th><spring:message code="evaluation.editChildren.fatherName"/></th>
            <td><c:out value="${child.fatherName}"/></td>
            <th><spring:message code="evaluation.editChildren.motherName"/></th>
            <td><c:out value="${child.motherName}"/></td>
        </tr>
    </table>
    <button class="btn btn-primary right" type="button" onclick="location.href='${ctx}/crud/edit/${entity}/${child.altKey}'"><spring:message code="evaluation.editChildren.editChild"/></button>


    <h2><spring:message code="evaluation.editChildren.previousEvaluations"/></h2>

    <c:if test="${not empty evaluations }">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><spring:message code="evaluation.editChildren.evaluation.date"/></th>
                <th><spring:message code="evaluation.editChildren.evaluation.age"/></th>
                <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${evaluations}" var="evaluation">
                <tr>
                    <td><c:out value="${evaluation.testDateDetails}"/></td>
                    <td><c:out value="${evaluation.childAgeDetails}"/></td>
                    <td><a href="${ctx}/evaluation/view/${evaluation.altKey}"><span class="glyphicon glyphicon-search"
                                                                               aria-hidden="true"
                                                                               aria-label="<spring:message code="evaluation.editChildren.evaluation.view"/>"></span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty evaluations}">
        <p><spring:message code="evaluation.editChildren.noEvaluation"/></p>
    </c:if>


    <button class="btn btn-primary" type="button" onclick="location.href='${ctx}/evaluation/new/${child.altKey}'"><spring:message code="evaluation.editChildren.create"/></button>
    <button class="btn btn-default" type="button" onclick="location.href='${ctx}/evaluation/list'"><spring:message code="evaluation.editChildren.back"/></button>
</div>

</body>
</html>