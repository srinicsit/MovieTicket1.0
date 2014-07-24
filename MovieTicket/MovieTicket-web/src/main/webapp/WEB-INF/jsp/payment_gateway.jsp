<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
</style>
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/custom.css" />"
	rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery-1.10.2.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.10.4.js" />"></script>
<script type="text/javascript">
	$(function() {
		$('#submit').button();
	})
</script>

</head>
<body>
<body>
	<form:form modelAttribute="paymentForm"
		action="${pageContext.servletContext.contextPath}${'/payment'}"
		method="post">
		<form:hidden path="transactionId" />
		<h3>Welcome to Payment gatway</h3>
		<form:button id="submit">Submit</form:button>
	</form:form>
</body>
</html>