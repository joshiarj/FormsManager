<%@include file="shared/header.jsp" %>
<h3 align="center">Log In</h3>
<form method="post"> <!--action="${SITE_URL}/logincheck">-->
    <div class="form-group">
        <label>User Name:</label>
        <input type="text" class="form-control" name="username" required="required" />
    </div>
    <div class="form-group">
        <label>Password:</label>
        <input type="password" class="form-control" name="password" required="required" />
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary">
        <span class="glyphicon glyphicon-log-in"></span> Log In
    </button>
</form>
<%@include file="shared/footer.jsp" %>