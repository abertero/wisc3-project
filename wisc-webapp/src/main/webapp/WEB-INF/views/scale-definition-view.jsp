<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="scale.edit.title"/></h1>

<div>
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
        <c:forEach begin="${scale.minRange}" end="${scale.maxRange}" var="i">
            <tr>
                <td><c:out value="${i}"/></td>
                <td><c:out value="${definitions[i].ci}"/></td>
                <td><c:out value="${definitions[i].rankPercentage}"/></td>
                <td><c:out value="${definitions[i].minConfidenceIntervalNinetyPercent}"/>&nbsp;-&nbsp;<c:out
                        value="${definitions[i].minConfidenceIntervalNinetyFivePercent}"/></td>
                <td><c:out value="${definitions[i].minConfidenceIntervalNinetyPercent}"/>&nbsp;-&nbsp;<c:out
                        value="${definitions[i].maxConfidenceIntervalNinetyFivePercent}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button class="btn btn-default" type="button" onclick="location.href='${ctx}/scale/select'"><spring:message
            code="scale.back"/></button>
</div>

</body>
</html>