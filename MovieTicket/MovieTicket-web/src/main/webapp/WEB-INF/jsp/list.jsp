<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<title>jQuery UI Example Page</title>


<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/jquery-ui-1.10.4.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.10.2.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.10.4.js" />"></script>
<style>
body {
	font: 62.5% "Trebuchet MS", sans-serif;
	margin: 50px;
}

.demoHeaders {
	margin-top: 2em;
}

#dialog-link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog-link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
}

#icons {
	margin: 0;
	padding: 0;
}

#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

#icons span.ui-icon {
	float: left;
	margin: 0 4px;
}

.fakewindowcontain .ui-widget-overlay {
	position: absolute;
}
</style>

<script>
	$(function() {

		$("#tabs").tabs();

	});
</script>

</head>
<body>
	<!-- Tabs -->
	<h2 class="demoHeaders">Tabs</h2>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">First</a></li>
			<li><a href="#tabs-2">Second</a></li>
			<li><a href="#tabs-3">Third</a></li>
		</ul>
		<div id="tabs-1">Lorem ipsum dolor sit amet, consectetur
			adipisicing elit, sed do eiusmod tempor incididunt ut labore et
			dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
			exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</div>
		<div id="tabs-2">Phasellus mattis tincidunt nibh. Cras orci
			urna, blandit id, pretium vel, aliquet ornare, felis. Maecenas
			scelerisque sem non nisl. Fusce sed lorem in enim dictum bibendum.</div>
		<div id="tabs-3">Nam dui erat, auctor a, dignissim quis,
			sollicitudin eu, felis. Pellentesque nisi urna, interdum eget,
			sagittis et, consequat vestibulum, lacus. Mauris porttitor
			ullamcorper augue.</div>
	</div>

</body>
</html>