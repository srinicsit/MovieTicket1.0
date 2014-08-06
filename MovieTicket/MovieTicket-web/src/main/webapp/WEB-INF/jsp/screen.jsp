<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/custom.css" />"
	rel="stylesheet" />
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/jquery.dataTables.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
<script src="<c:url value="/resources/js/screen-layout.js" />"></script>
<script src="<c:url value="/resources/js/jquery.auto.complete.js" />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
input.text {
	/* margin-bottom: 12px;
	width: 95%;
	padding: .4em; */
	
}
</style>

<script type="text/javascript">
	$(function() {
		var contextPath = $('#contextPath').val();
		loadSeatTypes();
		$('#submit').button();
		$('#layout').button();

		$('#rows').spinner({
			min : 1,
			max : 26
		});
		$('#columns').spinner({
			min : 1,
			max : 50
		});

		initDialog();

		var table = $("#screensData").DataTable({
			"dom" : '<"toolbar">frtip',
			"bProcessing" : true,
			"bInfo" : false,
			"bServerSide" : false,
			searching : false,
			ordering : false,
			// 			"sAjaxSource" : contextPath + "/screen/" + getTheaterIdVal(),
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
			debugger;
			var row = table.row('.selected').data();
			$('#name').val(row.name);
			var screenId = row.id;
			$('#screenId').val(screenId);

			loadScreenDetails(screenId);

			event.preventDefault();

		});
		$('.ui-icon-plus').click(function() {

			$("#dialog").dialog("open");
			event.preventDefault();
		});

		$('.ui-icon-trash').click(function() {
			debugger;
			var rowId = table.row('.selected').data().id;
			$('#screenId').val(rowId);
			document.screenForm.action = document.screenForm.action + "/delete"
			submitForm();

		});

		createLocation('locations');
		//createLocation('#locationName');
		createTheater("theaterName");

		function getTheaterIdVal() {
			debugger;
			var val = $('#theaterId').val();
			if (val == undefined || val == null || val == "") {
				val = -1;
			}
			return val;
		}

		function getLocationIdVal() {
			debugger;
			var val = $('#locationId').val();
			if (val == undefined || val == null || val == "") {
				val = -1;
			}
			return val;
		}

		function submitForm() {
			$.ajax({
				type : "post",
				url : document.screenForm.action,
				data : $("form").serialize(),
				success : function(data) {
					debugger;
					fillDataInTable();

				}
			})
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

		function createLocation(locationId) {
			var locationSelect = $('#' + locationId);
			$.get($('#contextPath').val() + "/locations/fullList", function(
					data) {
				clearOptions(locationId);
				for (var i = 0; i < data.length; i++) {
					var option = $("<option>");

					option.attr('value', data[i].id);
					option.text(data[i].name);
					locationSelect.append(option);
				}

				locationSelect.combobox({
					select : function(event, ui) {
						$('#locationId').val($(this).val());
						createTheater('theaterName');
					}
				});

			});
		}
		function createTheater(theaterId) {
			$.get($('#contextPath').val() + "/theater/fullList/"
					+ getLocationIdVal(), function(data) {
				debugger;
				clearOptions(theaterId);
				var theaterSelect = $('#' + theaterId);
				for (var i = 0; i < data.length; i++) {
					var option = $("<option>");
					option.attr('value', data[i].id);
					option.text(data[i].name);
					theaterSelect.append(option);
				}
				theaterSelect.combobox({
					select : function(event, ui) {
						$('#theaterId').val($(this).val());
						fillDataInTable();
					}
				});

			});

		}
		function clearOptions(selectId) {
			$('#' + selectId + '').find('option').remove().end().append(
					'<option value="">Select</option>');
		}

		function initDialog() {
			$("#dialog").dialog(
					{
						modal : true,
						autoOpen : false,
						width : 900,
						height : 700,
						buttons : [
								{
									text : "Ok",
									click : function() {
										$(this).dialog("close");
										var allSeatsInfo = getAllSeatsInfo();
										$('#seatsInfo').val(allSeatsInfo);
										setRemovedClsTypes();
										submitForm();
										document.screenForm.action = $(
												'#contextPath').val()
												+ "/screen";
									}
								},
								{
									text : "Cancel",
									click : function() {
										document.screenForm.action = $(
												'#contextPath').val()
												+ "/screen";
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


		<div class="labelCell">
			<label>Location</label>
		</div>

		<div class="valueCell">
			<select id="locations">
				<option value=""></option>
			</select>
		</div>
		<input id="locationId" type="hidden">

		<div class="labelCell">
			<label for="theaterName">Theater</label>
		</div>
		<div class="valueCell">
			<select id="theaterName"><option value=""></option></select>
		</div>



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


	</div>



	<div id="dialog" title="Screen Details">


		<div>
			<form:form name="screenForm"
				action="${pageContext.servletContext.contextPath}${'/screen'}"
				modelAttribute="screenForm" method="post">
				<form:hidden path="theaterId" id="theaterId" />
				<form:hidden path="screenId" id="screenId" />

				<form:hidden path="updateSeatClsTypes" id="updateSeatClsTypes" />
				<form:hidden path="removedSeatCls" id="removedSeatCls" />

				<table>
					<tr>
						<td><form:label path="name">Screen Name</form:label></td>
						<td><form:input path="name" class="text " />
						<td><table>
								<tr>
									<td><form:label path="rows">Rows</form:label></td>
									<td><form:input path="rows" class="text" value="5" /></td>
								</tr>
							</table></td>


						<td><form:label path="columns">Columns</form:label></td>
						<td><form:input path="columns" class="text" value="5" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><label>Seats</label>
						<td><input type="number" id="seats" value="1" max="10"
							min="1" /></td>

						<td>
							<table>
								<tr>
									<td><label>Selection Type</label></td>
									<td><select id="seatSelctionType">
											<option value="single">Single</option>
											<option value="row">Row</option>
											<option value="column">Column</option>
											<option value="entireRow">Entire Row</option>
											<option value="entireColumn">Entire Column</option>
									</select></td>
								</tr>
							</table>
						</td>

					</tr>
				</table>
				<table>
					<tr>
						<td>Seats Type</td>
						<td><select name="seatsType" id="seatsType" multiple>

						</select></td>

						<td><div id='seatsTypeTableInfo'
								style="height: 100px; overflow: auto; width: 400px">
								<table id="seatsTypeTable">
								</table>
							</div></td>
						<td><button type="button" id="layout">Show layout</button></td>
					</tr>

				</table>




				<div id='screenLayout' style="height: 400px; overflow: auto;">
					<table id="screenLayoutTable" cellpadding="0" cellspacing="0"
						border="0" class="display">
					</table>
				</div>
				<!-- 				<div>
					<button id="submit" type="button">Sumbit</button>
					<textarea rows="10" cols="50" id="jsonData"></textarea>
				</div> -->
				<form:hidden path="seatsInfo" id="seatsInfo" />
			</form:form>
		</div>
		<input type="hidden" id="contextPath"
			value="${pageContext.servletContext.contextPath}">

	</div>


</body>
</html>