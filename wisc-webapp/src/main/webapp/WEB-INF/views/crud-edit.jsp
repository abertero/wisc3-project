<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>

<h1><spring:message code="${title}"/></h1>


<div>
    <div>
        <form method="post" action="${ctx}/crud/save/${entity}">
            <input type="hidden" name="altKey" value="${crud.altKey}"/>
            <input type="hidden" name="type" value="${crud.type}"/>
            <div class="form-horizontal">
                <c:forEach items="${crud.rows}" var="row">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 control-label"><spring:message code="${row.displayName}"/></label>

                        <div class="col-xs-12 col-sm-8">${row.html}</div>
                    </div>
                </c:forEach>
            </div>
            <button class="btn btn-primary" type="submit"><spring:message code="crud.save"/></button>
            <button class="btn btn-default" type="button" onclick="location.href='${ctx}/crud/list/${entity}'"><spring:message code="crud.back"/></button>
        </form>
    </div>
</div>


</body>
</html>