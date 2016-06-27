<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><c:out value="${evaluation.child.displayName}"/></h1>


<h2><spring:message code="evaluation.test.scores"/></h2>

<div>
    <dl class="dl-horizontal">
        <dt><spring:message code="evaluation.test.day"/></dt>
        <dd><c:out value="${evaluation.testDay}"/></dd>
        <dt><spring:message code="evaluation.test.month"/></dt>
        <dd><c:out value="${evaluation.testMonth}"/></dd>
        <dt><spring:message code="evaluation.test.year"/></dt>
        <dd><c:out value="${evaluation.testYear}"/></dd>
        <c:forEach items="${scores}" var="score">
            <dt><c:out value="${score.definition.name}"/></dt>
            <dd><c:out value="${score.score}"/> (Equivalente: <c:out value="${score.realScore}"/>)</dd>
        </c:forEach>
    </dl>

    <button class="btn btn-default" type="button"
            onclick="location.href='${ctx}/evaluation/child/${evaluation.child.altKey}'"><spring:message
            code="evaluation.add.back"/></button>
</div>

<h2><spring:message code="evaluation.scale.scores"/></h2>

<div>
    <dl class="dl-horizontal">
        <c:forEach items="${scaleScores}" var="scaleScore">
            <dt><c:out value="${scaleScore.definition.scale.displayName}"/></dt>
            <dd><c:out value="${scaleScore.ci}"/>(CI: <c:out value="${scaleScore.iq.label}"/>)</dd>
        </c:forEach>
    </dl>
</div>

</body>
</html>