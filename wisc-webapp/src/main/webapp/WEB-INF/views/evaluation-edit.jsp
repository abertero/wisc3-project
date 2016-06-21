<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><c:out value="${child.displayName}"/></h1>

<div>
    <form action="${ctx}/evaluation/save/${child.altKey}" method="post">
        <div class="form-group">
            <label class="col-sm-2 col-xs-12 control-label"><spring:message code="evaluation.test.day"/></label>

            <div class="col-sm-10 col-xs-12">
                <input type="number" class="form-control" name="DAY" value="${currentDay}" min="1" max="31">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-xs-12 control-label"><spring:message code="evaluation.test.month"/></label>

            <div class="col-sm-10 col-xs-12">
                <input type="number" class="form-control" name="MONTH" value="${currentMonth}" min="1" max="12">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-xs-12 control-label"><spring:message code="evaluation.test.year"/></label>

            <div class="col-sm-10 col-xs-12">
                <input type="number" class="form-control" name="YEAR" value="${currentYear}" min="1900" max="3000">
            </div>
        </div>
        <c:forEach items="${definitions}" var="definition">
            <div class="form-group">
                <label class="col-sm-2 col-xs-12 control-label"><c:out value="${definition.name}"/></label>

                <div class="col-sm-10 col-xs-12">
                    <input type="number" class="form-control" name="${definition.altKey}" min="0" max="200">
                </div>
            </div>
        </c:forEach>

        <button class="btn btn-primary" type="submit"><spring:message code="evaluation.add.save"/></button>
        <button class="btn btn-default" type="button" onclick="location.href='${ctx}/evaluation/child/${child.altKey}'"><spring:message code="evaluation.add.back"/></button>

    </form>
</div>

</body>
</html>