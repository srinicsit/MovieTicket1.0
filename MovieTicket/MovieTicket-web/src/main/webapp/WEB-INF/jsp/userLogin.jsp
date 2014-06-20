<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
label,input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}


</style>
<script src="<c:url value="/resources/js/jquery-1.10.2.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.10.4.js" />"></script>
<script type="text/javascript">
	$(function() {
		debugger;
		$("#login").button();
	});
</script>

</head>
<body>

	<div  style="width: 400px;">


		<fieldset>
			<legend>Login</legend>
			<form:form
				action="${pageContext.servletContext.contextPath}${'/userLogin'}"
				modelAttribute="userLoginForm" method="post">

				<p class="validateTips">All form fields are required.</p>
				<form:label path="userId">UserID</form:label>
				<form:input path="userId"
					class="text ui-widget-content ui-corner-all" />

				<form:label path="pwd">Password</form:label>
				<form:password path="pwd"
					class="text ui-widget-content ui-corner-all" />


				<form:button id="login">Submit</form:button>


			</form:form>
		</fieldset>
	</div>



</body>
</html>