<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="evaluation.scale.title"/></h1>

<form action="${ctx}/evaluation/scale/save" method="post">
    <div>
        <table class="table table-bordered table-condensed">
            <thead>
            <tr>
                <th>&nbsp;</th>
                <c:forEach items="${scales}" var="scale">
                    <th><c:out value="${scale.displayName}"/></th>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${definitions}" var="definition">
                <tr>
                    <th><c:out value="${definition.name}"/></th>
                    <c:forEach items="${scales}" var="scale">
                        <td><input type="checkbox" name="scales"
                                   value="${definition.altKey}#${scale.code}" ${values[definition.altKey][scale.code] ? "checked" : ""} >
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <button class="btn btn-primary" type="submit"><spring:message code="evaluation.scale.save"/></button>
    <button class="btn btn-default" type="button" onclick="location.href='${ctx}/'"><spring:message code="evaluation.scale.back"/></button>

</form>

</body>
</html>