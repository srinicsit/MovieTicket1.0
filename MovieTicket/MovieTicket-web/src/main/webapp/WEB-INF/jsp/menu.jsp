<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<style>
.ui-menu {
	position: absolute;
	width: 100px;
}
</style>

<script>
	$(function() {

		// 		$("#tabs").tabs();
		// 		$("#radioset").buttonset();

		$("#rerun").button().click(function() {

		}).next().button({
			text : false,
			icons : {
				primary : "ui-icon-triangle-1-s"
			}
		}).click(function() {
			var menu = $(this).parent().next().show().position({
				my : "left top",
				at : "left bottom",
				of : this
			});
			$(document).one("click", function() {
				menu.hide();
			});
			return false;
		}).parent().buttonset().next().hide().menu();

	});
</script>

</head>
<body>
	<form style="margin-top: 1em;">

		<c:if test="${sessionScope.user != null}">
			<div>
				<div>
					<button id="rerun" type="button">Admin</button>
					<button id="select">Select an action</button>
				</div>
				<ul>
					<li><a
						href="${pageContext.servletContext.contextPath}${'/theater'}">Theater
							Details</a></li>
					<li><a
						href="${pageContext.servletContext.contextPath}${'/screen'}">Screen
							Details</a></li>
					<li><a href="#">Movie Details</a></li>


					<li><a
						href="${pageContext.servletContext.contextPath}${'/userLogin?logout=logout'}">Logout</a></li>



				</ul>
			</div>
		</c:if>

	</form>
</body>
</html>