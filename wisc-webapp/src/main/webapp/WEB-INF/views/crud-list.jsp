<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="${title}"/></h1>

<c:if test="${not empty cruds}">
    <c:set var="first" value="${cruds[0]}"/>
    <div>
        <table>
            <tr>
                <c:forEach items="${first.attributeValue.keys}" var="cols">
                    <th><c:out value="${cols}"/></th>
                </c:forEach>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${cruds}" var="crud">
                <tr>
                    <c:forEach items="${crud.attributeValue.values}" var="vals">
                        <td><c:set value="${vals}"/></td>
                    </c:forEach>
                    <td>Edit</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
<c:if test="${empty cruds}">
    <p><spring:message code="cruds.empty"/></p>
</c:if>

<button class="btn btn-primary" type="button" onclick="location.href='${ctx}/crud/edit/${entity}'"><spring:message code="cruds.create"/> </button>
</body>
</html>