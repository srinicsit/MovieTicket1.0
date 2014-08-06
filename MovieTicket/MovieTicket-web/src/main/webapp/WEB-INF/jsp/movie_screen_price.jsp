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
fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

select {
	width: 150px;
}

.custom-combobox {
	position: relative;
	display: inline-block;
}

.custom-combobox-toggle {
	position: absolute;
	top: 0;
	bottom: 0;
	margin-left: -1px;
	padding: 0;
	/* support: IE7 */
	*height: 1.7em;
	*top: 0.1em;
}

.custom-combobox-input {
	margin: 0;
	padding: 0.3em;
}

.overflow {
	height: 200px;
}
</style>
<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
<script src="<c:url value="/resources/js/jquery.auto.complete.js" />"></script>
<script src="<c:url value="/resources/js/jquery.auto.complete.js" />"></script>

<script type="text/javascript">
	var seatTypesMap = {};
	$(function() {

		var contextPath = $('#contextPath').val();
		$.get(contextPath + "/utils/allSeatTypes", function(data) {
			for (var i = 0; i < data.length; i++) {
				seatTypesMap[data[i].id] = data[i].name;
			}
		});

		initDialog();
		$('#seatClsName').selectmenu();
		$('#movie').selectmenu();
		//.selectmenu("menuWidget").addClass("overflow")
		$('#showTime').selectmenu();

		var table = $("#movieScreenPriceTbl").DataTable({
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
				"mData" : "seatType.id",
				"mRender" : function(data, type, row) {
					if (data) {
						return seatTypesMap[data];
					}
				}
			}, {
				"mData" : "ticketCost"
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
		var toolBarIcons = 'Movie Screen Cost Details <ul id="icons" class="ui-widget ui-helper-clearfix"><li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-plus"><span class="ui-icon ui-icon-plus"></span></li>'
				+ '<li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-pencil"><span class="ui-icon ui-icon-pencil"></span></li>'
				+ '<li class="ui-state-default ui-corner-all ui-state-hover" title=".ui-icon-trash"><span class="ui-icon ui-icon-trash"></span></li></ul>'
		$("div.toolbar").html(toolBarIcons);

		$('#movieScreenPriceTbl tbody').on('click', 'tr', function() {

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
							document.movieScreenPriceForm.action = document.movieScreenPriceForm.action
									+ "/update";
							var row = table.row('.selected').data();
							$('#movieScreenPriceId').val(row.id);
							$('#seatClsName').val(row.seatType.id);
							$('#price').val(row.ticketCost);
							seatsType.selectmenu("refresh");
							$("#dialog").dialog("open");

						});
		$('.ui-icon-plus')
				.click(
						function() {
							$("#dialog").dialog("open");
							document.movieScreenPriceForm.action = document.movieScreenPriceForm.action
									+ "/save";
						});

		$('.ui-icon-trash')
				.click(
						function() {
							document.movieScreenPriceForm.action = document.movieScreenPriceForm.action
									+ "/delete";
							var row = table.row('.selected').data();
							$('#movieScreenPriceId').val(row.id);
							submitForm();
						});

		function createLocation(locationId) {
			var locationSelect = $('#' + locationId);
			$.get($('#contextPath').val() + "/locations/fullList", function(
					data) {

				for (var i = 0; i < data.length; i++) {
					var option = $("<option>");
					option.attr('value', data[i].id);
					option.text(data[i].name);
					locationSelect.append(option);
				}
			});
		}

		function createTheater(theaterId) {
			$.get($('#contextPath').val() + "/theater/fullList/"
					+ getLocationIdVal(), function(data) {

				var theaterSelect = $('#' + theaterId);
				for (var i = 0; i < data.length; i++) {
					var option = $("<option>");
					option.attr('value', data[i].id);
					option.text(data[i].name);
					theaterSelect.append(option);
				}

			});

		}

		function createScreen(screenId) {

			$.get(
					$('#contextPath').val() + "/screen/list/"
							+ getTheaterIdVal(), function(data) {
						var screenSelect = $('#' + screenId);
						for (var i = 0; i < data.length; i++) {
							var option = $("<option>");
							option.attr('value', data[i].id);
							option.text(data[i].name);
							screenSelect.append(option);
						}
					});

		}

		function loadScreenClassTypes() {
			var url = $('#contextPath').val() + "/screen/clsTypeIds/"
					+ getScreenIdVal();
			$.get(url, function(data) {
				debugger;
				clearSeatClsTypes();
				var seatsType = $('#seatClsName');
				for (var i = 0; i < data.length; i++) {
					var seatTypeId = data[i].seatType.id;
					var option = $('<option>');
					option.attr('value', data[i].seatType.id);
					option.text(data[i].seatType.name);
					seatsType.append(option);
				}
			});
		}
		function clearOptions(id) {
			$('#' + id).find('option').remove().end().append(
					'<option value="">Select</option>');
		}
		function clearSeatClsTypes() {
			clearOptions('seatClsName');
		}
		function createMovieShowTimes() {
			var url = $('#contextPath').val() + "/movieAssign/moviesList/"
					+ getScreenIdVal();
			clearMovies();
			clearShowTimes();
			var movieScreens;
			var movieTimings = {};
			$.get(url, function(data) {
				movieScreens = data;

				var selectMovie = $('#movie');

				for (var i = 0; i < data.length; i++) {

					var movieDates = movieTimings[data[i].movie.id];
					if (movieDates == undefined) {
						movieDates = [];
						var movieOption = $('<option>');
						movieOption.attr('value', data[i].movie.id);
						movieOption.text(data[i].movie.movieName);
						selectMovie.append(movieOption);
					}
					movieDates.push(data[i]);
					movieTimings[data[i].movie.id] = movieDates;

				}
				$('#movie').selectmenu("refresh");
			});

			function clearMovies() {
				$('#movie').find('option').remove().end().append(
						'<option value="">Select</option>');
			}

			function clearShowTimes() {
				$('#showTime').find('optgroup').remove();
				$('#showTime').find('option').remove().end().append(
						'<option value="">Select</option>');
			}

			$('#movie')
					.selectmenu(
							{
								change : function() {
									clearShowTimes();
									var movieId = $(this).val();
									var movieDates = movieTimings[movieId];
									var selectShowTime = $('#showTime');

									var dateTimeMap = {};

									for ( var i in movieDates) {
										var date = getDate(movieDates[i].showDate);
										var times = dateTimeMap[date];
										if (times == undefined) {
											times = []
										}
										var timesInfo = {};
										timesInfo.id = movieDates[i].id;
										timesInfo.time = getHours(movieDates[i].showDate)
												+ ":"
												+ getMins(movieDates[i].showDate);

										times.push(timesInfo);
										dateTimeMap[date] = times;
									}

									for ( var i in dateTimeMap) {
										var optgroup = $('<optgroup>');
										optgroup.attr('label', i);

										var times = dateTimeMap[i];
										for ( var t in times) {
											var option = $('<option>');
											option.attr('value', times[t].id);
											option.text(times[t].time);
											optgroup.append(option);
										}

										selectShowTime.append(optgroup);
									}
									selectShowTime.selectmenu("refresh");
								}
							});

			$('#showTime').selectmenu({
				change : function() {
					debugger;
					$('#movieScreenId').val($(this).val());
					fillDataInTable();
				}
			});

		}
		$('#locations').combobox({
			select : function(event, ui) {
				$('#locationId').val($(this).val());
				createTheater('theaterName');
			}
		});

		$('#theaterName').combobox({
			select : function(event, ui) {
				$('#theaterId').val($(this).val());
				createScreen('screenName');
			}
		});

		$('#screenName').combobox({
			select : function(event, ui) {
				$('#screenId').val($(this).val());
				loadScreenClassTypes();
				createMovieShowTimes();
			}
		});

		createLocation('locations');

		//createLocation('#locationName');
		// 		createTheater("theaterName");

		function getScreenIdVal() {

			var val = $('#screenId').val();
			if (val == undefined || val == null || val == "") {
				val = -1;
			}
			return val;
		}

		function getTheaterIdVal() {

			var val = $('#theaterId').val();
			if (val == undefined || val == null || val == "") {
				val = -1;
			}
			return val;
		}

		function getLocationIdVal() {

			var val = $('#locationId').val();
			if (val == undefined || val == null || val == "") {
				val = -1;
			}
			return val;
		}
		function getMovieScreenIdVal() {
			var val = $('#movieScreenId').val();
			if (val == undefined || val == null || val == "") {
				val = -1;
			}
			return val;
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
		function getFullDate(data) {

			return getDate(data) + getHours(data) + ":" + getMins(data);
		}
		function submitForm() {
			$.ajax({
				type : "post",
				url : document.movieScreenPriceForm.action,
				data : $("form").serialize(),
				success : function(data) {
					fillDataInTable();
					$('#dialog').dialog("close");

				}
			})
		}
		function fillDataInTable() {

			$.ajax({
				type : "GET",
				url : contextPath + "/movieScreenPrice/"
						+ getMovieScreenIdVal(),
				success : function(data) {
					table.clear();
					table.rows.add(data.aaData);
					table.draw();

				}

			});
		}
		function initDialog() {
			$("#dialog")
					.dialog(
							{
								modal : true,
								autoOpen : false,
								width : 400,
								height : 250,
								buttons : [
										{
											text : "Ok",
											click : function() {

												submitForm();
												document.movieScreenPriceForm.action = $(
														'#contextPath').val()
														+ "/movieScreenPrice";
											}
										},
										{
											text : "Cancel",
											click : function() {
												document.movieScreenPriceForm.action = $(
														'#contextPath').val()
														+ "/movieScreenPrice";
												$(this).dialog("close");
											}
										} ]
							});
		}
	});
</script>
</head>
<body>
	<fieldset>
		<div>
			<div class="labelCell">
				<label for="locations">Location</label>
			</div>
			<div class="valueCell">
				<select id="locations">
					<option value=""></option>
				</select> <input id="locationId" type="hidden">
			</div>
		</div>
		<div>
			<div class="labelCell">
				<label for="theaterName">Theater</label>
			</div>
			<div class="valueCell">
				<select id="theaterName"><option value=""></option></select> <input
					type="hidden" id="theaterId">
			</div>
		</div>

		<div>
			<div class="labelCell">
				<label for="screenName">Screen</label>
			</div>
			<div class="valueCell">
				<select id="screenName"><option value=""></option>
				</select><input type="hidden" id="screenId">
			</div>
		</div>

		<div>
			<div class="labelCell">
				<label for="movie">Movie</label>
			</div>
			<div class="valueCell">
				<select id="movie">
					<option value=""></option>
				</select>
			</div>
		</div>

		<div>
			<div class="labelCell">
				<label for="showTime">Show Time</label>
			</div>
			<div class="valueCell">
				<select id="showTime">
					<option value=""></option>
				</select>
			</div>
		</div>


		<div>
			<table id="movieScreenPriceTbl" class="display">
				<thead>
					<tr>
						<th>Seat Class</th>
						<th>Price</th>
						<th>Id</th>
					</tr>
				</thead>
			</table>
		</div>

		<div id="dialog" title="Movie Screen Cost Details">
			<div>
				<form:form name="movieScreenPriceForm"
					action="${pageContext.servletContext.contextPath}${'/movieScreenPrice'}"
					modelAttribute="movieScreenPriceForm" method="post">
					<form:hidden path="movieScreenId" id="movieScreenId" />
					<form:hidden path="movieScreenPriceId" id="movieScreenPriceId"></form:hidden>

					<div>
						<div class="labelCell">
							<label for="seatClsName">Seat Class</label>
						</div>
						<div class="valueCell">
							<form:select id="seatClsName" path="seatClsName">
								<option value="">Select</option>
							</form:select>
						</div>
					</div>

					<div>
						<div class="labelCell">
							<label for="price">Price</label>
						</div>
						<div class="valueCell">
							<form:input type="text" id="price" path="price" />
						</div>
					</div>


				</form:form>

			</div>
		</div>




	</fieldset>
</body>
</html>
