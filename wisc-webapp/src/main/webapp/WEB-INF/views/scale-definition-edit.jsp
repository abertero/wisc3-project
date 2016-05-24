<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="scale.edit.title"/></h1>

<form method="post" action="${ctx}/scale/view/${childLevel.altKey}">
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
                        <td><input type="hidden" name="definitions[${i}].scaleScoreSum" value="${i}"/><c:out
                                value="${i}"/></td>
                        <td><input type="text" name="definitions[${i}].ci" class="input-field input-sm"
                                   value="${definitions[i].ci}" size="4"/></td>
                        <td><input type="text" name="definitions[${i}].rankPercentage" class="input-field input-sm"
                                   value="${definitions[i].rankPercentage}" size="4"/></td>
                        <td><input type="text" name="definitions[${i}].minConfidenceIntervalNinetyPercent"
                                   class="input-field input-sm"
                                   value="${definitions[i].minConfidenceIntervalNinetyPercent}"
                                   size="4"/>&nbsp;-&nbsp;<input
                                type="text" name="definitions[${i}].maxConfidenceIntervalNinetyPercent"
                                class="input-field input-sm"
                                value="${definitions[i].minConfidenceIntervalNinetyFivePercent}" size="4"/></td>
                        <td><input type="text" name="definitions[${i}].minConfidenceIntervalNinetyFivePercent"
                                   class="input-field input-sm"
                                   value="${definitions[i].minConfidenceIntervalNinetyPercent}"
                                   size="4"/>&nbsp;-&nbsp;<input
                                type="text" name="definitions[${i}].maxConfidenceIntervalNinetyFivePercent"
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
                        <td><input type="hidden" name="definitions[${i}].scaleScoreSum" value="${i}"/><c:out
                                value="${i}"/></td>
                        <td><input type="text" name="definitions[${i}].ci" class="input-field input-sm"
                                   value="${definitions[i].ci}" size="4"/></td>
                        <td><input type="text" name="definitions[${i}].rankPercentage" class="input-field input-sm"
                                   value="${definitions[i].rankPercentage}" size="4"/></td>
                        <td><input type="text" name="definitions[${i}].minConfidenceIntervalNinetyPercent"
                                   class="input-field input-sm"
                                   value="${definitions[i].minConfidenceIntervalNinetyPercent}"
                                   size="4"/>&nbsp;-&nbsp;<input
                                type="text" name="definitions[${i}].maxConfidenceIntervalNinetyPercent"
                                class="input-field input-sm"
                                value="${definitions[i].minConfidenceIntervalNinetyFivePercent}" size="4"/></td>
                        <td><input type="text" name="definitions[${i}].minConfidenceIntervalNinetyFivePercent"
                                   class="input-field input-sm"
                                   value="${definitions[i].minConfidenceIntervalNinetyPercent}"
                                   size="4"/>&nbsp;-&nbsp;<input
                                type="text" name="definitions[${i}].maxConfidenceIntervalNinetyFivePercent"
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