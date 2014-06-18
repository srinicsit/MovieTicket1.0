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
		<div>
			<div>
				<button id="rerun" type="button">Admin</button>
				<button id="select">Select an action</button>
			</div>
			<ul>
				<li><a href="${pageContext.servletContext.contextPath}${'/theater'}">Theater Details</a></li>
				<li><a href="#">Screen Details</a></li>
				<li><a href="#">Movie Details</a></li>
			</ul>
		</div>


	</form>
</body>
</html>