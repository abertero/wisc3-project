<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<script src="${ctx}/static/js/validate-equivalent-score.js"></script>
<body>

<h1><spring:message code="equivalentScore.edit.title"/></h1>

<p><spring:message code="equivalentScore.edit.message"/>&nbsp;<span class="span-bold"><c:out
        value="${childLevel.name}"/>(<c:out
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
                        <td class="td-center cell_${definition.altKey}"><input type="text"
                                                                               name="${i}#${definition.altKey}"
                                                                               class="input-field input-sm"
                                                                               data-alt-key="${definition.altKey}"
                                                                               data-range="${definition.maxRange}"
                                                                               id="cell_${definition.altKey}_${i}"
                                                                               value="${tableColumnsByDefinition[definition.altKey][i]}"
                                                                               size="4"/></td>
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
                        <td class="td-center cell_${definition.altKey}"><input type="text"
                                                                               name="${i}#${definition.altKey}"
                                                                               class="input-field input-sm"
                                                                               data-alt-key="${definition.altKey}"
                                                                               data-range="${definition.maxRange}"
                                                                               id="cell_${definition.altKey}_${i}"
                                                                               value="${tableColumnsByDefinition[definition.altKey][i]}"
                                                                               size="4"/></td>
                    </c:forEach>
                    <td class="text-center"><c:out value="${i}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <button type="submit" class="btn btn-primary"><spring:message code="equivalentScore.edit.save"/></button>
    <button class="btn btn-default" type="button" onclick="location.href='${ctx}/definition/score/equivalent/level'"><spring:message code="crud.back"/></button>
</form>

<script type="text/javascript">
    $(function () {
        $("input").on("change", function () {
            var equivalentMaxRangeString = "<c:out value="${maxEquivalentScore}"/>";
            if (!isNaN(equivalentMaxRangeString)) {
                var altKey = $(this).data("altKey");
                var equivalentMaxRange = parseInt(equivalentMaxRangeString);
                var definitionRangeString = $(this).data("range");
                if (!isNaN(definitionRangeString)) {
                    var definitionRange = parseInt(definitionRangeString);
                    if (validateRow(altKey, equivalentMaxRange, definitionRange)) {
                        $("td.cell_" + altKey).removeClass("danger");
                        $("td.cell_" + altKey).addClass("success");
                    } else {
                        $("td.cell_" + altKey).removeClass("success");
                        $("td.cell_" + altKey).addClass("danger");
                    }
                }
            }
        });
    });
</script>
</body>
</html>