<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>

<h1><spring:message code="${title}"/></h1>


<div>
    <dl class="dl-horizontal">
        <c:forEach items="${crud.attributeValue}" var="entry">
            <dt><spring:message code="${entry.key}"/></dt>
            <dd><c:out value="${entry.value}"/></dd>
        </c:forEach>
    </dl>

    <button class="btn btn-default" type="button" onclick="location.href='${ctx}/crud/list/${entity}'"><spring:message code="crud.back"/></button>
</div>

</body>
</html>