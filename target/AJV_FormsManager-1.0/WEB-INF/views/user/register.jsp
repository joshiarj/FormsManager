<%@include file="../shared/header.jsp" %>
<h3 align="center">New User Registration</h3>
<c:if test="${param.success!=null}">
    <div style="color:green">
        User successfully registered! Please wait for account activation.
    </div>
</c:if>
<form method="post" action="${SITE_URL}/user/register/save">
    <div class="form-group">
        <label>User Name:</label>
        <input type="text" name="username" required="required" class="form-control" />
    </div>
    <div class="form-group">
        <label>Password:</label>
        <input type="password" name="password" required="required" class="form-control" />
    </div>
    <div class="form-group">
        <label>Email:</label>
        <input type="email" name="email" required="required" class="form-control" />
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit" class="btn btn-success">
        <span class="glyphicon glyphicon-user"></span> Sign Up
    </button>
</form>
<%@include file="../shared/footer.jsp" %>