<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<script src="${ctx}/static/js/validate-equivalent-score.js"></script>
<body>

<h1><spring:message code="equivalentScore.edit.title"/></h1>

<p><spring:message code="equivalentScore.edit.message"/>&nbsp;<span class="span-bold"><c:out
        value="${childLevel.name}"/>(<c:out
        value="${childLevel.description}"/>)</span></p>

<div>
    <h2><spring:message code="equivalentScore.edit.verbal.message"/></h2>

    <table class="table table-bordered table-condensed table-extra-small">
        <thead>
        <tr>
            <th><spring:message code="equivalentScore.edit.equivalentScore.label"/></th>
            <c:forEach items="${verbalDefinitions}" var="definition">
                <th><c:out value="${definition.name}"/></th>
            </c:forEach>
            <th><spring:message code="equivalentScore.edit.equivalentScore.label"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach begin="1" end="${maxEquivalentScore}" var="i">
            <tr>
                <td class="text-center"><span class="span-bold"><c:out value="${i}"/></span></td>
                <c:forEach items="${verbalDefinitions}" var="definition">
                    <td class="td-center cell_${definition.altKey}"><c:out value="${tableColumnsByDefinition[definition.altKey][i]}"/></td>
                </c:forEach>
                <td class="text-center"><span class="span-bold"><c:out value="${i}"/></span></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div>
    <h2><spring:message code="equivalentScore.edit.execution.message"/></h2>

    <table class="table table-bordered table-condensed table-extra-small">
        <thead>
        <tr>
            <th><spring:message code="equivalentScore.edit.equivalentScore.label"/></th>
            <c:forEach items="${executionDefinitions}" var="definition">
                <th><c:out value="${definition.name}"/></th>
            </c:forEach>
            <th><spring:message code="equivalentScore.edit.equivalentScore.label"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach begin="1" end="${maxEquivalentScore}" var="i">
            <tr>
                <td class="text-center"><span class="span-bold"><c:out value="${i}"/></span></td>
                <c:forEach items="${executionDefinitions}" var="definition">
                    <td class="td-center cell_${definition.altKey}"><c:out value="${tableColumnsByDefinition[definition.altKey][i]}"/></td>
                </c:forEach>
                <td class="text-center"><span class="span-bold"><c:out value="${i}"/></span></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<button class="btn btn-default" type="button" onclick="location.href='${ctx}/definition/score/equivalent/level'">
    <spring:message code="equivalentScore.back"/></button>


</body>
</html>