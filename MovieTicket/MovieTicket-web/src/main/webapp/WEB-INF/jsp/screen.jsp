<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/jquery.dataTables.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
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

.ui-autocomplete {
	max-height: 100px;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: hidden;
}
</style>

<script type="text/javascript">
	$(function() {
		var contextPath = $('#contextPath').val();
		$('#submit').button();
		initDialog();

		var table = $("#screensData").DataTable({
			"dom" : '<"toolbar">frtip',
			"bProcessing" : true,
			"bInfo" : false,
			"bServerSide" : false,
			searching : false,
			ordering : false,
			"sAjaxSource" : contextPath + "/screen/" + getTheaterIdVal(),
			"bJQueryUI" : true,
			"aoColumns" : [ {
				"mData" : "name"
			}, {
				"mData" : "id"
			}

			],
			"columnDefs" : [ {
				"targets" : [ 1 ],
				"visible" : false,
				"searchable" : false
			} ]
		});
		var toolBarIcons = 'Screen Details <ul id="icons" class="ui-widget ui-helper-clearfix"><li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-plus"><span class="ui-icon ui-icon-plus"></span></li>'
				+ '<li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-pencil"><span class="ui-icon ui-icon-pencil"></span></li>'
				+ '<li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-trash"><span class="ui-icon ui-icon-trash"></span></li></ul>'
		$("div.toolbar").html(toolBarIcons);

		$('#screensData tbody').on('click', 'tr', function() {

			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('.ui-icon-pencil').click(function() {
			var row = table.row('.selected').data();
			$('#name').val(row.name);
			$('#screenId').val(row.id);
			document.screenForm.action = document.screenForm.action + "/update"
			$("#dialog").dialog("open");
			event.preventDefault();

		});
		$('.ui-icon-plus').click(function() {

			$("#dialog").dialog("open");
			event.preventDefault();
		});

		$('.ui-icon-trash').click(function() {
			var rowId = table.row('.selected').data().id;
			$('#screenId').val(rowId);
			document.screenForm.action = document.screenForm.action + "/delete"
			document.screenForm.submit();

		});

		$("#theaterName").autocomplete(
				{
					source : $('#contextPath').val() + "/theater/list/"
							+ $("#location").val(),
					minLength : 1,
					focus : function(event, ui) {
						$("#theaterName").val(ui.item.name);

						return false;
					},
					change : function(event, ui) {

					},
					select : function(event, ui) {
						$("#theaterName").val(ui.item.name);
						$('#theaterId').val(ui.item.id)
						$('#hiddenTheaterName').val(ui.item.name)
						fillDataInTable();
						return false;
					}
				}).data("ui-autocomplete")._renderItem = function(ul, item) {

			return $("<li>").append("<a>" + item.name + "</a>").appendTo(ul);
		};

		function getTheaterIdVal() {
			debugger;
			var val = $('#theaterId').val();
			if (val == undefined || val == null||val=="") {
				val = -1;
			}
			return val;
		}

		function fillDataInTable() {

			$.ajax({
				type : "GET",
				url : contextPath + "/screen/" + getTheaterIdVal(),
				success : function(data) {
					debugger;
					table.clear();
					table.rows.add(data.aaData);
					table.draw();

				}

			});
		}

		function initDialog() {
			$("#dialog").dialog({
				modal : true,
				autoOpen : false,
				width : 400,
				buttons : [ {
					text : "Ok",
					click : function() {
						$(this).dialog("close");
						document.screenForm.submit();

					}
				}, {
					text : "Cancel",
					click : function() {
						$(this).dialog("close");
					}
				} ]
			});
		}

	})
</script>
</head>
<body>

	<div>
		<form:form modelAttribute="screenForm" method="post">
			<fieldset>
				<form:label path="location">Location</form:label>
				<form:input path="location" disabled="true" id="location"
					class="text ui-widget-content ui-corner-all" />

				<form:label path="theaterName">Theater</form:label>
				<form:input path="theaterName" id="theaterName" class="text " />


			</fieldset>
			<div>
				<table id="screensData" class="display">
					<thead>
						<tr>
							<th>Name</th>
							<th>Id</th>
						</tr>
					</thead>
				</table>
			</div>
		</form:form>
		<input type="hidden" id="contextPath"
			value="${pageContext.servletContext.contextPath}">
	</div>

	<div id="dialog" title="Screen Details">

		<p class="validateTips">All form fields are required.</p>
		<div>
			<form:form name="screenForm"
				action="${pageContext.servletContext.contextPath}${'/screen'}"
				modelAttribute="screenForm" method="post">
				<form:hidden path="theaterId" id="theaterId" />
				<form:hidden path="screenId" id="screenId" />
				<form:hidden path="theaterName" id="hiddenTheaterName" />

				<form:label path="name">Screen Name</form:label>
				<form:input path="name" class="text " />

			</form:form>
		</div>
		<input type="hidden" id="contextPath"
			value="${pageContext.servletContext.contextPath}">

	</div>


</body>
</html>