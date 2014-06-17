<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Theater Details</title>
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/jquery-ui-1.10.4.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.10.2.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.10.4.js" />"></script>
</head>
<body>

	<div>
		<div></div>
		<div></div>
	</div>


	<div class="ui-dialog-content ui-widget-content">
		<p class="validateTips">All form fields are required.</p>

		<form:form action="${pageContext.servletContext.contextPath}${'/theater'}"
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