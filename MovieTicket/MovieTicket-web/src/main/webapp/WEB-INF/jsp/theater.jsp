<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style type="text/css">
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

.labelCell {
	float: left;
	width: 25%;
	padding: 5px;
}

.valueCell {
	float: left;
	width: 60%;
	padding: 5px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/jquery.dataTables.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/custom.css" />"
	rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
<script src="<c:url value="/resources/js/jquery.auto.complete.js" />"></script>
<script type="text/javascript">
	$(function() {
		debugger;
		initDialog();
		$('#locations').autocomplete({
			source : $('#contextPath').val() + "/locations/partialList",
			minLength : 1,
			focus : function(event, ui) {
				$("#locations").val(ui.item.name);
				return false;
			},
			select : function(event, ui) {

				$('#locationId').val(ui.item.id)
				fillDataInTable();
				return false;
			}

		}).data("ui-autocomplete")._renderItem = function(ul, item) {

			return $("<li>").append("<a>" + item.name + "</a>").appendTo(ul);
		};

		var contextPath = $('#contextPath').val();
		//<"top"i>rt<"bottom"flp><"clear">
		var table = $("#theatersData").DataTable({
			"dom" : '<"toolbar">frtip',
			"bProcessing" : false,
			"bInfo" : false,
			"bServerSide" : false,
			searching : false,
			ordering : false,
			"bJQueryUI" : true,
			"aoColumns" : [ {
				"mData" : "name"
			}, {
				"mData" : "location.name"
			}, {
				"mData" : "id"
			}

			],
			"columnDefs" : [ {
				"targets" : [ 2 ],
				"visible" : false,
				"searchable" : false
			} ]
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
					$('#locationId').val(row.location.id);
					$('#locationName').val(row.location.name);
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
							+ "/delete";
					submitForm();

				});

		function submitForm() {
			$.ajax({
				type : "post",
				url : document.theaterForm.action,
				data : $("form").serialize(),
				success : function(data) {
					debugger;
					fillDataInTable();

				}
			})
		}

		function initDialog() {
			$("#dialog").dialog(
					{
						modal : true,
						autoOpen : false,
						width : 400,
						open : function(event, ui) {

						},
						buttons : [
								{
									text : "Ok",
									click : function() {
										$(this).dialog("close");
										// document.theaterForm.submit();
										submitForm();
										document.theaterForm.action = $(
												'#contextPath').val()
												+ "/theater";

									}
								},
								{
									text : "Cancel",
									click : function() {
										document.theaterForm.action = $(
												'#contextPath').val()
												+ "/theater";
										$(this).dialog("close");
									}
								} ]
					});
		}

		function fillDataInTable() {

			$.ajax({
				type : "GET",
				url : contextPath + "/theater/dtList/" + getLocationIdVal(),
				success : function(data) {
					debugger;
					table.clear();
					table.rows.add(data.aaData);
					table.draw();

				}

			});
		}

	});

	function getLocationIdVal() {
		debugger;
		var val = $('#locationId').val();
		if (val == undefined || val == null || val == "") {
			val = -1;
		}
		return val;
	}
</script>

</head>
<body>


	<div class="labelCell">
		<div>
			<label>Location</label>
		</div>
		<div>
			<input type="text" id="locations" />
		</div>
	</div>

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

			<form:hidden path="locationId" id="locationId" />

			<fieldset>
				<form:label path="name">Name</form:label>
				<form:input path="name" id="name"
					class="text ui-widget-content ui-corner-all" />

				<form:hidden id="theaterId" path="theaterId"></form:hidden>

			</fieldset>
		</form:form>

	</div>


</body>
</html>