<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	<div class="ui-dialog-content ui-widget-content">
		<h3>Login</h3>
		<p class="validateTips">All form fields are required.</p>

		<form:form
			action="${pageContext.servletContext.contextPath}${'/userLogin'}"
			modelAttribute="userLoginForm" method="post">
			<fieldset>
				<form:label path="userId">UserID</form:label>
				<form:input path="userId"
					class="text ui-widget-content ui-corner-all" />

				<form:label path="pwd">Password</form:label>
				<form:password path="pwd"
					class="text ui-widget-content ui-corner-all" />


				<form:button>Submit</form:button>

			</fieldset>
		</form:form>
	</div>



</body>
</html>