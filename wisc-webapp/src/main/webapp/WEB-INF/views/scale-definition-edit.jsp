<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="scale.edit.title"/></h1>

<p><spring:message code="scale.edit.message"/>&nbsp;<c:out value="${scale.displayName}"/></p>

<form method="post" action="${ctx}/scale/save/${scale.code}">
    <div>
        <div class="col-md-6 col-xs-12">
            <table class="table table-bordered table-condensed table-extra-small">
                <thead>
                <tr>
                    <th><spring:message code="scale.edit.scaleScoreSum"/></th>
                    <th><spring:message code="scale.edit.ci"/></th>
                    <th><spring:message code="scale.edit.rankPercentage"/></th>
                    <th><spring:message code="scale.edit.confidenceInterval.90"/></th>
                    <th><spring:message code="scale.edit.confidenceInterval.95"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach begin="${scale.minRange}" end="${averageScaleRange}" var="i">
                    <tr>
                        <td class="text-center"><input type="hidden" name="definitions[${i}].scaleScoreSum" value="${i}"/><c:out
                                value="${i}"/></td>
                        <td class="text-center"><input type="number" name="definitions[${i}].ci" class="input-field input-sm"
                                   value="${definitions[i].ci}" size="4"/></td>
                        <td class="text-center"><input type="number" name="definitions[${i}].rankPercentage" class="input-field input-sm"
                                   value="${definitions[i].rankPercentage}" size="4"/></td>
                        <td class="text-center"><input type="number" name="definitions[${i}].minConfidenceIntervalNinetyPercent"
                                   class="input-field input-sm"
                                   value="${definitions[i].minConfidenceIntervalNinetyPercent}"
                                   size="4"/>&nbsp;-&nbsp;<input
                                type="number" name="definitions[${i}].maxConfidenceIntervalNinetyPercent"
                                class="input-field input-sm"
                                value="${definitions[i].maxConfidenceIntervalNinetyPercent}" size="4"/></td>
                        <td class="text-center"><input type="number" name="definitions[${i}].minConfidenceIntervalNinetyFivePercent"
                                   class="input-field input-sm"
                                   value="${definitions[i].minConfidenceIntervalNinetyFivePercent}"
                                   size="4"/>&nbsp;-&nbsp;<input
                                type="number" name="definitions[${i}].maxConfidenceIntervalNinetyFivePercent"
                                class="input-field input-sm"
                                value="${definitions[i].maxConfidenceIntervalNinetyFivePercent}" size="4"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-6 col-xs-12">
            <table class="table table-bordered table-condensed table-extra-small">
                <thead>
                <tr>
                    <th><spring:message code="scale.edit.scaleScoreSum"/></th>
                    <th><spring:message code="scale.edit.ci"/></th>
                    <th><spring:message code="scale.edit.rankPercentage"/></th>
                    <th><spring:message code="scale.edit.confidenceInterval.90"/></th>
                    <th><spring:message code="scale.edit.confidenceInterval.95"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach begin="${averageScaleRange+1}" end="${scale.maxRange}" var="i">
                    <tr>
                        <td class="text-center"><input type="hidden" name="definitions[${i}].scaleScoreSum" value="${i}"/><c:out
                                value="${i}"/></td>
                        <td class="text-center"><input type="number" name="definitions[${i}].ci" class="input-field input-sm"
                                   value="${definitions[i].ci}" size="4"/></td>
                        <td class="text-center"><input type="number" name="definitions[${i}].rankPercentage" class="input-field input-sm"
                                   value="${definitions[i].rankPercentage}" size="4"/></td>
                        <td class="text-center"><input type="number" name="definitions[${i}].minConfidenceIntervalNinetyPercent"
                                   class="input-field input-sm"
                                   value="${definitions[i].minConfidenceIntervalNinetyPercent}"
                                   size="4"/>&nbsp;-&nbsp;<input
                                type="number" name="definitions[${i}].maxConfidenceIntervalNinetyPercent"
                                class="input-field input-sm"
                                value="${definitions[i].maxConfidenceIntervalNinetyPercent}" size="4"/></td>
                        <td class="text-center"><input type="number" name="definitions[${i}].minConfidenceIntervalNinetyFivePercent"
                                   class="input-field input-sm"
                                   value="${definitions[i].minConfidenceIntervalNinetyFivePercent}"
                                   size="4"/>&nbsp;-&nbsp;<input
                                type="number" name="definitions[${i}].maxConfidenceIntervalNinetyFivePercent"
                                class="input-field input-sm"
                                value="${definitions[i].maxConfidenceIntervalNinetyFivePercent}" size="4"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <button type="submit" class="btn btn-primary"><spring:message code="scale.edit.save"/></button>
        <button class="btn btn-default" type="button" onclick="location.href='${ctx}/scale/select'"><spring:message
                code="scale.back"/></button>
    </div>
</form>

</body>
</html>