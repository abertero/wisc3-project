<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="${title}"/></h1>

<c:if test="${not empty cruds}">
    <c:set var="first" value="${cruds[0]}"/>
    <div>
        <table class="table table-bordered">
            <tr>
                <c:forEach items="${first.attributeValue}" var="entry">
                    <th><spring:message code="${entry.key}"/></th>
                </c:forEach>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${cruds}" var="crud">
                <tr>
                    <c:forEach items="${crud.attributeValue}" var="entry">
                        <td><c:out value="${entry.value}"/></td>
                    </c:forEach>
                    <td>
                        <a href="${ctx}/crud/edit/${entity}/${crud.altKey}"><span class="glyphicon glyphicon-edit"
                                                                                  aria-hidden="true"
                                                                                  aria-label="<spring:message code="crud.edit"/>"></span></a>
                        <a href="${ctx}/crud/view/${entity}/${crud.altKey}"><span class="glyphicon glyphicon-search"
                                                                                  aria-hidden="true"
                                                                                  aria-label="<spring:message code="crud.view"/>"></span></a>
                        <a href="#"><span class="glyphicon glyphicon-remove" style="color: red;" aria-hidden="true"
                                          aria-label="<spring:message code="crud.delete"/>"></span></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
<c:if test="${empty cruds}">
    <p><spring:message code="cruds.empty"/></p>
</c:if>

<button class="btn btn-primary" type="button" onclick="location.href='${ctx}/crud/edit/${entity}'"><spring:message
        code="cruds.create"/></button>
</body>
</html>