<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="ageCalculator.title"/></h1>

<p><spring:message code="ageCalculator.description"/></p>

<div id="wrongInfo" class="alert alert-danger" role="alert" style="display: none;"><spring:message
        code="ageCalculator.wrongInfo"/></div>

<div>
    <form method="post">
        <div class="form-horizontal">
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label"><spring:message
                        code="ageCalculator.testDate"/></label>

                <div class="input-group col-xs-12 col-sm-8">
                    <div class="col-xs-2">
                        <input type="text" class="form-control"
                               placeholder="<spring:message code="ageCalculator.year.placeholder"/>" name="testYear"
                               value="${helper.testYear}"/></div>
                    <div class="col-xs-1">
                        <input type="text" class="form-control"
                               placeholder="<spring:message code="ageCalculator.month.placeholder"/>" name="testMonth"
                               value="${helper.testMonth}"/></div>
                    <div class="col-xs-1">
                        <input type="text" class="form-control"
                               placeholder="<spring:message code="ageCalculator.day.placeholder"/>" name="testDay"
                               value="${helper.testDay}"/></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label"><spring:message
                        code="ageCalculator.birthDate"/></label>

                <div class="input-group col-xs-12 col-sm-8">
                    <div class="col-xs-2">
                        <input type="text" class="form-control"
                               placeholder="<spring:message code="ageCalculator.year.placeholder"/>" name="birthYear"
                               value="${helper.birthYear}"/></div>
                    <div class="col-xs-1">
                        <input type="text" class="form-control"
                               placeholder="<spring:message code="ageCalculator.month.placeholder"/>" name="birthMonth"
                               value="${helper.birthMonth}"/></div>
                    <div class="col-xs-1">
                        <input type="text" class="form-control"
                               placeholder="<spring:message code="ageCalculator.day.placeholder"/>" name="birthDay"
                               value="${helper.birthDay}"/></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 control-label"><spring:message
                        code="ageCalculator.ageResult"/></label>

                <div class="col-xs-12 col-sm-8">
                    <span id="age"></span>
                </div>
            </div>
            <div class="btn-group col-xs-12 col-sm-offset-3">
                <button type="button" id="process" class="btn btn-primary col-xs-12 col-sm-9 col-md-6 col-lg-1"><spring:message
                        code="ageCalculator.calculate"/></button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(function () {
        $("#process").click(function () {
            $.ajax({
                url: "${ctx}/utils/calculateAge",
                data: $(this).closest("form").serialize(),
                method: "post",
                success: function (res) {
                    $("#wrongInfo").hide();
                    if (res == undefined || res == null || res == "") {
                        $("#wrongInfo").show();
                    } else {
                        $("#age").text(res.years + " a\u00f1os, " + res.months + " meses, " + ", " + res.days + " d\u00edas");
                    }
                }
            });
        });
    });
</script>
</body>
</html>