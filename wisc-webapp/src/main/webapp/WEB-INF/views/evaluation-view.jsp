<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><c:out value="${child.displayName}"/></h1>

<div>
    <div class="form-group">
        <label class="col-sm-2 col-xs-12 control-label"><spring:message code="evaluation.test.day"/></label>

        <div class="col-sm-10 col-xs-12">
            <c:out value="${evaluation.testDay}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 col-xs-12 control-label"><spring:message code="evaluation.test.month"/></label>

        <div class="col-sm-10 col-xs-12">
            <c:out value="${evaluation.testMonth}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 col-xs-12 control-label"><spring:message code="evaluation.test.year"/></label>

        <div class="col-sm-10 col-xs-12">
            <c:out value="${evaluation.testYear}"/>
        </div>
    </div>
    <c:forEach items="${evaluation.scores}" var="score">
        <div class="form-group">
            <label class="col-sm-2 col-xs-12 control-label"><c:out value="${score.definition.name}"/></label>

            <div class="col-sm-10 col-xs-12">
                <c:out value="${score.score}"/> (Equivalente: <c:out value="${score.realScore}"/>)
            </div>
        </div>
    </c:forEach>
    <div class="form-group">
        <label class="col-sm-2 col-xs-12 control-label"><spring:message code="evaluation.test.total"/></label>

        <div class="col-sm-10 col-xs-12">
            <c:out value="${evaluation.total}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 col-xs-12 control-label"><spring:message code="evaluation.test.realTotal"/></label>

        <div class="col-sm-10 col-xs-12">
            <c:out value="${evaluation.realTotal}"/>
        </div>
    </div>

    <button class="btn btn-default" type="button"
            onclick="location.href='${ctx}/evaluation/child/${evaluation.child.altKey}'"><spring:message
            code="evaluation.add.back"/></button>
</div>

</body>
</html>