<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style type="text/css">
label,input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/jquery.dataTables.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
<script type="text/javascript">
	$(function() {
		debugger;
		initDialog();
		var contextPath = $('#contextPath').val();
		//<"top"i>rt<"bottom"flp><"clear">
		var table = $("#theatersData").DataTable({
			"dom" : '<"toolbar">frtip',
			"bProcessing" : true,
			"bInfo" : false,
			"bServerSide" : true,
			searching : false,
			ordering : false,
			"sAjaxSource" : "" + contextPath + "/theater/listForDt",
			"bJQueryUI" : true,
			"aoColumns" : [ {
				"mData" : "name"
			}, {
				"mData" : "location"
			}, {
				"mData" : "id"
			}

			],
			"columnDefs" : [ {
				"targets" : [ 2 ],
				"visible" : false,
				"searchable" : false
			}]
		});
		var toolBarIcons = 'Theater Details <ul id="icons" class="ui-widget ui-helper-clearfix"><li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-plus"><span class="ui-icon ui-icon-plus"></span></li>'
				+ '<li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-pencil"><span class="ui-icon ui-icon-pencil"></span></li>'
				+ '<li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-trash"><span class="ui-icon ui-icon-trash"></span></li></ul>'
		$("div.toolbar").html(toolBarIcons);

		$('#theatersData tbody').on('click', 'tr', function() {

			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('.ui-icon-pencil').click(
				function() {
					debugger;
					var row = table.row('.selected').data();
					$('#name').val(row.name);
					$('#theaterId').val(row.id);
					document.theaterForm.action = document.theaterForm.action
							+ "/update"
					$("#dialog").dialog("open");

				});
		$('.ui-icon-plus').click(function() {
			$("#dialog").dialog("open");
			event.preventDefault();
		});

		$('.ui-icon-trash').click(
				function() {
					debugger;
					var rowId = table.row('.selected').data().id;
					$('#theaterId').val(rowId);
					document.theaterForm.action = document.theaterForm.action
							+ "/delete"
					document.theaterForm.submit();
				});

		function initDialog() {
			$("#dialog").dialog({
				modal : true,
				autoOpen : false,
				width : 400,
				buttons : [ {
					text : "Ok",
					click : function() {
						$(this).dialog("close");
						document.theaterForm.submit();
					}
				}, {
					text : "Cancel",
					click : function() {
						$(this).dialog("close");
					}
				} ]
			});
		}

	});
</script>

</head>
<body>




	<div>
		<table id="theatersData" class="cell-border">
			<thead>
				<tr>
					<th>Name</th>
					<th>Location</th>
					<th>Id</th>
				</tr>
			</thead>


		</table>
	</div>

	<div id="dialog" title="Theater Details">

		<p class="validateTips">All form fields are required.</p>

		<form:form id="theaterFormId" name="theaterForm"
			action="${pageContext.servletContext.contextPath}${'/theater'}"
			modelAttribute="theaterForm" method="post">

			<fieldset>
				<form:label path="name">Name</form:label>
				<form:input path="name" id="name"
					class="text ui-widget-content ui-corner-all" />

				<form:label path="location">Location</form:label>
				<form:input path="location" disabled="true"
					class="text ui-widget-content ui-corner-all" />

				<form:hidden id="theaterId" path="theaterId"></form:hidden>



			</fieldset>
		</form:form>
		<input type="hidden" id="contextPath"
			value="${pageContext.servletContext.contextPath}">

	</div>


</body>
</html>