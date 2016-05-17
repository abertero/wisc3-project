<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="equivalentScore.edit.title"/></h1>

<p><spring:message code="equivalentScore.edit.message"/>&nbsp;<span class="span-bold"><c:out value="${childLevel.name}"/>(<c:out
        value="${childLevel.description}"/>)</span></p>

<form method="post" action="${ctx}/definition/score/equivalent/save/${childLevel.altKey}">
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
                    <td class="text-center"><c:out value="${i}"/></td>
                    <c:forEach items="${verbalDefinitions}" var="definition">
                        <td class="td-center"><input type="text" name="values[${i}#${definition.altKey}]" class="input-field input-sm"
                                   value="${tableColumnsByDefinition[definition.altKey][i]}" size="4"/></td>
                    </c:forEach>
                    <td class="text-center"><c:out value="${i}"/></td>
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
                    <td class="text-center"><c:out value="${i}"/></td>
                    <c:forEach items="${executionDefinitions}" var="definition">
                        <td class="td-center"><input type="text" name="values[${i}#${definition.altKey}]" class="input-field input-sm"
                                   value="${tableColumnsByDefinition[definition.altKey][i]}" size="4"/></td>
                    </c:forEach>
                    <td class="text-center"><c:out value="${i}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>

</body>
</html>