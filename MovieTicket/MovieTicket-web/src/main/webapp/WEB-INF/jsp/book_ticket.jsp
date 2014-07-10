<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
</style>
<link
	href="<c:url value="/resources/theme/ui-light-ness/css/ui-lightness/custom.css" />"
	rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery-1.10.2.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.10.4.js" />"></script>
<script src="<c:url value="/resources/js/jquery.auto.complete.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>

<script type="text/javascript">
	$(function() {
		$('#screensInfo').tooltip({
			items : "[title]",
			content : function() {
				var element = $(this);

				if (element.is("[title]")) {
					return element.attr("title");
				}

			}
		});

		var activeTab;
		$("#days").tabs({
			activate : function(event, ui) {
				debugger;
				activeTab = ui.newTab;

			}
		});
		$.ajax({
			type : "GET",
			url : $('#contextPath').val() + "/movies/list",
			success : function(data) {
				addOptions(data, 'movie');
				$('#movie').combobox({
					select : function(event, ui) {

						if (activeTab == undefined) {
							var anchors = $('#days a');
							loadScreens($(anchors[0]).attr('day'));
						} else {
							loadScreens($(activeTab.find('a')[0]).attr('day'));
						}

					}
				});
			}
		})
		$.ajax({
			type : "GET",
			url : $('#contextPath').val() + "/bookTicket/days",
			success : function(data) {
				debugger;
				var a = $('#days a');
				for ( var i in a) {
					var anchor = $(a[i]);
					anchor.attr('onclick', 'javascript:loadScreens('
							+ data[i].day + ')');
					anchor.attr('day', data[i].day);
					anchor.html("<div>" + data[i].name + "</div>" + "<div>"
							+ data[i].day + "</div>")
				}

			}

		})

		function addOptions(data, componentId) {
			debugger;
			for ( var i in data) {
				var theater = data[i];
				var option = $("<option>");
				option.val(theater.id);
				option.text(theater.movieName);

				$('#' + componentId).append(option);
			}
		}

	})

	function loadScreens(day) {
		var d = new Date()
		var curr_date = day;
		var curr_month = d.getMonth();
		var curr_year = d.getFullYear();
		var dateString = (curr_month + 1 + "/" + curr_date + "/" + curr_year);
		d = new Date(dateString);

		$.ajax({
			type : "GET",
			url : $('#contextPath').val() + "/bookTicket/showList/"
					+ $('#movie').val() + "/" + d,
			success : function(data) {
				var screensInfo = $('#screensInfo');
				var screensInfoTable = $("#screensInfoTable");
				screensInfoTable.remove();
				var newTable = $('<table>');
				newTable.attr('id', 'screensInfoTable');

				var screens = data.aaData;
				debugger;

				for ( var i in screens) {
					var tr = $('<tr>');
					var screenTd = $('<td>');
					screenTd.text(screens[i].screen.theater.name);
					tr.append(screenTd);
					var timings = screens[i].timingsAndAvailableInfo;
					for ( var t in timings) {
						var timingTd = $('<td>');
						var a = $('<a>');
						a.attr('href', '#');
						a.attr('onclick', 'javascript:loadSeats('
								+ timings[t].movieScreenId + ')');

						var jsonStr = JSON
								.stringify(timings[t].seatsStatusCounts);
						a.attr('title', 'Available : ' + jsonStr);
						a.text(timings[t].time)
						timingTd.append(a);
						tr.append(timingTd);
					}
					newTable.append(tr);
				}

				screensInfo.append(newTable);
				$("#screensInfoTable").DataTable();

			}

		})

	}

	function loadSeats(movieScreenId) {
		$.ajax({
			type : "GET",
			url : $('#contextPath').val() + "/bookTicket/screenSeats/"
					+ movieScreenId,
			success : function(data) {

			}
		})
	}
	
	function loadScreenDetails(screenId) {
		var contextPath = $('#contextPath').val();
		$.get(contextPath + "/screen/" + "/clsTypes/" + screenId, function(data) {
			var screenData = data;
			for (i in screenData) {
				var clsType = screenData[i];

				seatsTypeRowsInfo[clsType.seatClsName] = clsType.rows;
				seatsTypeColumnsInfo[clsType.seatClsName] = clsType.cols;
				seatsTypeIdInfo[clsType.seatClsName] = clsType.id;

				for (r in screenData[i].rowsList) {

					var row = clsType.rowsList[r];
					for (c in row.seats) {

						var seat = row.seats[c];

						var seatData = {};
						seatData.status = seat.status;
						seatData.id = seat.id;

						var seatId = clsType.seatClsName + SEPERATOR + row.rowNum
								+ SEPERATOR + seat.colNum;

						seatsInfo[seatId] = seatData;
					}
				}
			}
			debugger;
			onSeatTypeChange();
			document.screenForm.action = document.screenForm.action + "/update"
			$("#dialog").dialog("open");
		});

	function getRowsForClsType(clsType, totalRows, totalCols, tableId) {
		var trs = [];
		var rowNames = getRowNames(clsType, totalRows);
		for (var r = 0; r < totalRows; r++) {
			var row = $('<tr>');
			var rowNameTd = $('<td>');
			var rowName = rowNames[r];
			rowNameTd.text(rowName);
			row.append(rowNameTd);
			var contextPath = $('#contextPath').val();
			for (var c = 0; c < totalCols; c++) {
				var id = clsType + "_" + r + "_" + c;

				var seat = $('<td>');
				var div = $('<div>');
				var ahref = $('<a>');

				ahref.attr('href', 'javascript:;');
				div.attr('onclick', 'changeSeat("' + id + '")');

				var img = $('<img >');
				img.attr('src', contextPath + '/resources/img/1_1.png');
				img.attr('row', r);
				img.attr('col', c);
				img.attr('id', id);
				img.attr('clsType', clsType);
				img.attr('clsTableId', tableId);
				img.attr('rowName', rowName);

				var seatId = clsType + SEPERATOR + r + SEPERATOR + c;
				var seatData = seatsInfo[seatId];
				if (seatData != undefined && seatData != null) {
					if (seatData.status != "hide") {
						img.css("visibility", "visible");
					} else {
						// hidden
						img.css("visibility", "hidden");
					}
				} else {
					img.css("visibility", "visible");
				}

				ahref.append(img);
				div.append(ahref);
				seat.append(div);

				row.append(seat);
			}
			trs.push(row);
		}
		return trs;
	}
</script>

</head>

<body>
	<form:form modelAttribute="bookTicketForm">
		<div style="width: 900px;">

			<fieldset>
				<legend>Book Ticket</legend>
				<div class="labelCell">
					<div class="labelCell">
						<form:label path="movie">Movie</form:label>
					</div>

					<div class="valueCell">
						<form:select path="movie" id="movie">
							<form:option value=""></form:option>
						</form:select>
					</div>
				</div>
				<div id="days" class="tabHeader">
					<ul>
						<li><a href="#screensInfo">Tab1</a></li>
						<li><a href="#screensInfo">Tab2</a></li>
						<li><a href="#screensInfo">Tab3</a></li>
						<li><a href="#screensInfo">Tab4</a></li>
						<li><a href="#screensInfo">Tab5</a></li>
					</ul>
					<div id="screensInfo">
						<table id="screensInfoTable" class="display">
							<thead>
								<tr>
									<td>select a movie</td>
								</tr>
							</thead>
						</table>
					</div>

				</div>

			</fieldset>

		</div>
	</form:form>

	<div id="dialog" title="Seat Layout">
		<div id='screenLayout' style="height: 400px; overflow: auto;">
			<table id="screenLayoutTable" cellpadding="0" cellspacing="0"
				border="0" class="display">
			</table>
		</div>
	</div>



</body>
</html>
