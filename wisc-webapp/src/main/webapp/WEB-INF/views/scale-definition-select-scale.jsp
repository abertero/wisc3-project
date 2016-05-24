<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="scale.selectScale.title"/></h1>

<p><spring:message code="scale.selectScale.message"/></p>

<div>
    <ul>
        <c:forEach var="scale" items="${scales}">
            <li><a href="${ctx}/scale/view/${scale.code}"><c:out value="${scale.displayName}"/></a>&nbsp;&nbsp;<a
                    href="${ctx}/scale/edit/${scale.code}"><span class="glyphicon glyphicon-edit"
                                                                 aria-hidden="true"
                                                                 aria-label="<spring:message code="scale.selectScale.edit"/>"></span></a>
            </li>
        </c:forEach>
    </ul>
</div>

</body>
</html>