<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Management</title>
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/jquery.dataTables.css" />"
	rel="stylesheet" />
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/custom.css" />"
	rel="stylesheet" />
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
</style>
<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
<script src="<c:url value="/resources/js/jquery.auto.complete.js" />"></script>

<script type="text/javascript">
	$(function() {
		var contextPath = $('#contextPath').val();
		$('#allMovies').combobox({

			select : function(event, ui) {
				debugger;
				$('#movieId').val(ui.item.value);
				loadMovieScreenDetails()

			}
		});

		initDialog();

		var table = $("#movieScreen").DataTable({
			"dom" : '<"toolbar">frtip',
			"bProcessing" : true,
			"bInfo" : false,
			"bServerSide" : false,
			searching : false,
			ordering : false,
			// 					"sAjaxSource" : contextPath + "/movieAssign/listForDt/"
			// 							+ $('#movieId').val(),
			"bJQueryUI" : true,
			"aoColumns" : [ {
				"mData" : "screen.theater.name"
			}, {
				"mData" : "screen.name"
			}, {
				"mData" : "screen.theater.location.name"
			}, {
				"mData" : "showDate",
				"mRender" : function(data, type, row) {
					if (data) {
						debugger;
						return getDate(data);
					}
				}
			}, {
				"mData" : "showDate",
				"mRender" : function(data, type, row) {
					if (data) {

						return getHours(data) + ":" + getMins(data);
					}
				}
			}, {
				"mData" : "id"
			}

			],
			"columnDefs" : [ {
				"targets" : [ 5 ],
				"visible" : false,
				"searchable" : false
			} ]
		});
		var toolBarIcons = 'Movie Details <ul id="icons" class="ui-widget ui-helper-clearfix"><li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-plus"><span class="ui-icon ui-icon-plus"></span></li>'
				+ '<li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-pencil"><span class="ui-icon ui-icon-pencil"></span></li>'
				+ '<li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-trash"><span class="ui-icon ui-icon-trash"></span></li></ul>'
		$("div.toolbar").html(toolBarIcons);

		$('#movieScreen tbody').on('click', 'tr', function() {

			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('.ui-icon-pencil')
				.click(
						function() {
							debugger;
							$("#dialog").dialog("open");
							var row = table.row('.selected').data();

							$('#locationId')
									.val(row.screen.theater.location.id);
							createLocation('locations',
									row.screen.theater.location.id);

							$('#movieAssignId').val(row.id);

							$('#theaterId').val(row.screen.theater.id);
							createTheater('theaterName', row.screen.theater.id);

							$('#showDate').val(getDate(row.showDate));

							$('#screenId').val(row.screen.id);
							createScreen('screenName', row.screen.id);

							$('#locations').val(
									row.screen.theater.location.name);

							$('#theaterName').val(row.screen.theater.name);
							$('#screenName').val(row.screen.name);
							$('#hours').val(getHours(row.showDate));
							$('#mins').val(getMins(row.showDate));

							document.movieAssignForm.action = document.movieAssignForm.action
									+ "/update";

						});
		$('.ui-icon-plus')
				.click(
						function() {

							document.movieAssignForm.action = document.movieAssignForm.action
									+ "/save";
							$("#dialog").dialog("open");

						});

		$('.ui-icon-trash').click(
				function() {
					debugger;
					var row = table.row('.selected').data();

					$('#movieAssignId').val(row.id);
					$('#showDate').val(getDate(row.showDate));

					document.movieAssignForm.action = $('#contextPath').val()
							+ "/movieAssign/delete";
					submitForm();

				});

		function createLocation(locationId, selLocId) {
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

				setAutocompletCurrentValue('#' + locationId, selLocId);
			});
		}

		function createTheater(theaterId, selTheaterVal) {
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
						createScreen('screenName');
					}
				});
				setAutocompletCurrentValue('#' + theaterId, selTheaterVal);

			});

		}
		function createScreen(screenId, selScreenVal) {
			$
					.get($('#contextPath').val() + "/screen/list/"
							+ getTheaterIdVal(),
							function(data) {
								var screenSelect = $('#' + screenId);
								clearOptions(screenId);
								for (var i = 0; i < data.length; i++) {
									var option = $("<option>");
									option.attr('value', data[i].id);
									option.text(data[i].name);
									screenSelect.append(option);
								}
								screenSelect.combobox({
									select : function(event, ui) {
										debugger;
										$('#screenId').val($(this).val());

									}
								});
								setAutocompletCurrentValue('#' + screenId,
										selScreenVal);
							});

		}

		function setAutocompletCurrentValue(id, value) {
			if (id != undefined && value != undefined) {
				$(id).val(value);
				var textToShow = $(id).find(":selected").text();
				$(id).parent().find("span").find("input").val(textToShow);
			}
		}

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

		function loadMovieScreenDetails() {
			var contextPath = $('#contextPath').val();
			$.ajax({
				type : "GET",
				url : contextPath + "/movieAssign/listForDt/"
						+ $('#movieId').val(),
				success : function(data) {
					debugger;
					table.clear();
					table.rows.add(data.aaData);
					table.draw();
				}
			})
		}

		function setDefault(defValue) {
			debugger;
			$('#location option').each(function() {
				if (($(this).attr('value')) === defValue) {
					$(this).attr('selected', 'selected');
				}
			});
		}

		$('#showDate').datepicker({
			minDate : -0,
			maxDate : "+2M +10D"
		});

		function submitForm() {
			debugger;
			$.ajax({
				type : "post",
				url : document.movieAssignForm.action,
				data : $("form").serialize(),
				success : function(data) {
					debugger;
					loadMovieScreenDetails();

				}
			})
		}

		function initDialog() {
			$("#dialog").dialog(
					{
						modal : true,
						autoOpen : false,
						width : 400,
						buttons : [
								{
									text : "Ok",
									click : function() {
										$(this).dialog("close");
										submitForm();
										document.movieAssignForm.action = $(
												'#contextPath').val()
												+ '/movieAssign';
									}
								},
								{
									text : "Cancel",
									click : function() {
										$(this).dialog("close");
										document.movieAssignForm.action = $(
												'#contextPath').val()
												+ '/movieAssign';
									}
								} ]
					});
		}

		function createHoursOption(id) {

			var hoursSelect = $('#' + id);
			for (var i = 0; i < 24; i++) {
				var option = $("<option>");
				option.attr('value', i);
				if (i < 10) {
					option.text("0" + i);
				} else {
					option.text(i);
				}
				hoursSelect.append(option);
			}
		}
		function createMinsOption(id) {
			var minsSelect = $('#' + id);
			for (var i = 0; i < 60; i++) {
				var option = $("<option>");
				option.attr('value', i);
				if (i < 10) {
					option.text("0" + i);
				} else {
					option.text(i);
				}
				minsSelect.append(option);
			}
		}
		createHoursOption('hours');
		createMinsOption('mins');
		// 		$('#hours').selectmenu();
		// 		$('#mins').selectmenu();
		createLocation('locations');

		$('#screenName').combobox({
			select : function(event, ui) {
				$('#screenId').val($(this).val());

			}
		});

		$('#theaterName').combobox({
			select : function(event, ui) {
				$('#theaterId').val($(this).val());
				createScreen('screenName');
			}
		});

		function clearOptions(selectId) {
			$('#' + selectId + '').find('option').remove().end().append(
					'<option value="">Select</option>');
		}

	});

	function addOptions(data, componentId) {
		debugger;
		for ( var i in data) {
			var theater = data[i];
			var option = $("<option>");
			option.val(theater.id);
			option.text(theater.name);

			$('#' + componentId).append(option);
		}
	}

	function getDate(data) {
		var d = new Date(data)
		var curr_date = d.getDate();
		var curr_month = d.getMonth();
		var curr_year = d.getFullYear();
		var dateString = (curr_month + 1 + "/" + curr_date + "/" + curr_year);
		return dateString;
	}
	function getHours(data) {
		var d = new Date(data);
		return d.getHours();
	}
	function getMins(data) {
		var d = new Date(data)
		return d.getMinutes();
	}
</script>


</head>
<body>
	<div>
		<div class="valueCell">
			<label> Movie</label>

			<form:select path="allMovies" id="allMovies">
				<form:option value=""></form:option>
				<form:options items="${allMovies}" itemLabel="movieName"
					itemValue="id" />
			</form:select>
		</div>
	</div>

	<div>
		<table id="movieScreen" class="display">
			<thead>
				<tr>
					<th>Theater</th>
					<th>Screen</th>
					<th>Location</th>
					<th>Show Date</th>
					<th>Show Time</th>
					<th>Id</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="dialog" title="Assign Movie Details">

		<p class="validateTips">All form fields are required.</p>

		<input type="hidden" id="theaterId" /> <input id="locationId"
			type="hidden">

		<form:form modelAttribute="movieAssignForm" name="movieAssignForm"
			action="${pageContext.servletContext.contextPath}${'/movieAssign'}"
			method="post">

			<fieldset>
				<form:hidden path="movieId" id="movieId" value="-1" />
				<form:hidden path="movieAssignId" id="movieAssignId" />

				<div>
					<div class="labelCell">
						<label>Location</label>
					</div>

					<div class="valueCell">
						<select id="locations">
							<option value=""></option>
						</select>
					</div>
				</div>


				<div>
					<div class="labelCell">
						<label for="theaterName">Theater</label>
					</div>
					<div class="valueCell">
						<select id="theaterName"><option value=""></option></select>
					</div>
				</div>
				<div>
					<div class="labelCell">
						<label for="screenName">Screen</label>
					</div>
					<div class="valueCell">
						<select id="screenName"><option value=""></option>
						</select>
						<form:hidden path="screenId" id="screenId"></form:hidden>
					</div>
				</div>


				<div>
					<div class="labelCell">
						<form:label path="showDate">Show Date</form:label>
					</div>
					<div class="valueCell">
						<form:input path="showDate" id="showDate" />
					</div>

				</div>

				<div>
					<div class="labelCell">
						<form:label path="hours">Show Time</form:label>
					</div>
					<div class="valueCell">
						<form:select path="hours" id="hours">
							<form:option value="">
								Hours
							</form:option>
						</form:select>
						:
						<form:select path="mins" id="mins">
							<form:option value="">Minutes</form:option>
						</form:select>
					</div>
				</div>
			</fieldset>
		</form:form>

	</div>

</body>

</html>

