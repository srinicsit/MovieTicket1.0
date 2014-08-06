<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/jquery-ui.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<style>
body {
	font: 62.5% "Trebuchet MS", sans-serif;
	margin: 50px;
}

.demoHeaders {
	margin-top: 2em;
}

#progressbar .ui-progressbar-value {
	background-color: #ccc;
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

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>

<script>
	$(function() {
		$("#progressbar").progressbar({
			value : false
		});

		function initDialog() {
			$("#progressDialog").dialog({
				autoOpen : false,
				closeOnEscape : false,
				resizable : false,
				modal : true

			});
		}
		initDialog();
		function showProgress() {
			$('#progressDialog').dialog("open");
		}
		function hideProgress() {
			$('#progressDialog').dialog("close");
		}

		$(document).ajaxSend(function() {
			// 			showProgress();
		});
		$(document).ajaxComplete(function() {
			// 			hideProgress();
		});
	});
</script>

</head>
<body>
	<div>
		<div>
			<tiles:insertAttribute name="header" />
		</div>

		<div style="width: 170px; float: left;">
			<tiles:insertAttribute name="menu" />
		</div>

		<div style="width: 600px; float: left;">
			<tiles:insertAttribute name="body" />
		</div>
		<div style="clear: both;">
			<tiles:insertAttribute name="footer" />
		</div>

		<div id="progressDialog">
			<div class="progress-label">Processing...</div>
			<div id="progressbar"></div>
		</div>


	</div>
</body>
</html>
