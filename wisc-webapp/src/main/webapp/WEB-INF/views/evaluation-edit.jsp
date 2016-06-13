<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><c:out value="${child.displayName}"/></h1>

<div>
    <form action="${ctx}/evaluation/save/${child.altKey}" method="post">
        <c:forEach items="${definitions}" var="definition">
            <div class="form-group">
                <label class="col-sm-2 col-xs-12 control-label"><c:out value="${definition.name}"/></label>

                <div class="col-sm-10 col-xs-12">
                    <input type="number" class="form-control" name="${definition.altKey}">
                </div>
            </div>
        </c:forEach>


    </form>
</div>

</body>
</html>