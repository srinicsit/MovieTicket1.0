<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	<div class="ui-dialog-content ui-widget-content">
		<p class="validateTips">All form fields are required.</p>

		<form:form
			action="${pageContext.servletContext.contextPath}${'/screen'}"
			modelAttribute="theaterForm" method="post">
			<fieldset>

				<form:label path="name">Name</form:label>
				<form:input path="name" class="text ui-widget-content ui-corner-all" />

				<form:label path="location">Location</form:label>
				<form:input path="location" readonly="true"
					class="text ui-widget-content ui-corner-all" />
				<form:button>Submit</form:button>


			</fieldset>
		</form:form>
	</div>



</body>
</html>