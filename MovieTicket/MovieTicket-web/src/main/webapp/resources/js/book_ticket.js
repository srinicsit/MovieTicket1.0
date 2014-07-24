var seatStatusInfo = {};

var seatsTypeLabelInfo = {};

seatsTypeLabelInfo.balcony = "Balcony";
seatsTypeLabelInfo.gold = "Gold Class";
seatsTypeLabelInfo.first = "First Class";
seatsTypeLabelInfo.silver = "Silver Class";
seatsTypeLabelInfo.bronze = "Bronze Class";

var AVAILABLE_STATUS = "AVAILABLE";

var NOT_AVAILABLE_STATUS = "NOT_AVAILABLE";

var BLOCKED_STATUS = "BLOCKED";

var BOOKING_STATUS = "bookingStatus";

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
			if (screens && screens.length > 0) {
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
								+ timings[t].movieScreenId + ','
								+ screens[i].screen.id + ')');

						var jsonStr = JSON
								.stringify(timings[t].seatsStatusCounts);
						a.attr('title', 'Available : ' + jsonStr);
						a.text(timings[t].time)
						timingTd.append(a);
						tr.append(timingTd);
					}
					newTable.append(tr);
				}
			} else {

			}

			screensInfo.append(newTable);
			// $("#screensInfoTable").DataTable();

		}

	})

}

function loadSeats(movieScreenId, screenId) {

	$.ajax({
		type : "GET",
		url : $('#contextPath').val() + "/bookTicket/screenSeats/"
				+ movieScreenId,
		success : function(data) {
			if (data && data.length > 0) {
				for ( var i in data) {
					var seatsStatus = data[i];
					var seatData = {};
					seatData.availableStatus = seatsStatus.seatStatus;
					seatData.seatsStatusId = seatsStatus.id;
					seatStatusInfo[seatsStatus.seat.id] = seatData;
				}
				loadScreenDetails(screenId);
			}

		}
	})
}

function loadScreenDetails(screenId) {
	var contextPath = $('#contextPath').val();
	$.get(contextPath + "/screen/" + "clsTypes/" + screenId, function(data) {
		var screenData = data;
		for (i in screenData) {
			var clsType = screenData[i];

			var totalRows = screenData[i].rowsList.length;
			var totalCols = screenData[i].rowsList[0].seats.length;

			for (r in screenData[i].rowsList) {

				var row = clsType.rowsList[r];
				for (c in row.seats) {

					var seat = row.seats[c];

					var seatData = seatStatusInfo[seat.id];
					if (!seatData) {
						seatData = {};
						seatData.status = seat.status;
						seatStatusInfo[seat.id] = seatData;
					} else {
						seatData.status = seat.status;
						seatStatusInfo[seat.id] = seatData;
					}

					seatStatusInfo[seat.id] = seatData;
				}
			}

		}

	});
}

function loadScreenDetails(screenId) {
	var contextPath = $('#contextPath').val();
	$.get(contextPath + "/screen/" + "clsTypes/" + screenId, function(data) {
		var screenData = data;
		debugger;

		var screenLayoutDiv = $('#screenLayout');
		var screenLayoutTable = $('#screenLayoutTable');
		screenLayoutTable.remove();

		var table = $('<table>');
		table.attr('id', 'screenLayoutTable');
		table.attr('class', 'cell-border');

		for (i in screenData) {
			var clsType = screenData[i];

			var clsHeaderTr = $('<tr>');
			var clsHeaderTd = $('<td>');
			clsHeaderTd.html('<b>' + seatsTypeLabelInfo[clsType.seatClsName]
					+ '</b>');
			clsHeaderTr.append(clsHeaderTd);

			var clsTr = $('<tr>');
			var clsTd = $('<td>');

			var clsTable = $('<table>');

			for (r in screenData[i].rowsList) {

				var row = clsType.rowsList[r];
				var tr = $('<tr>');
				for (c in row.seats) {

					var seat = row.seats[c];

					var seatData = seatStatusInfo[seat.id];
					if (!seatData) {
						seatData = {};
						seatData.status = seat.status;
						seatStatusInfo[seat.id] = seatData;
					} else {
						seatData.status = seat.status;
						seatStatusInfo[seat.id] = seatData;
					}
					var td = $('<td>');

					var div = $('<div>');
					var ahref = $('<a>');

					ahref.attr('href', 'javascript:;');

					var img = $('<img >');
					img.attr('src', contextPath + '/resources/img/1_1.png');

					img.attr('id', seatData.seatsStatusId);
					img.attr('seatId', seatData.id);
					img.attr(BOOKING_STATUS, AVAILABLE_STATUS);

					ahref.append(img);
					div.append(ahref);
					td.append(div);
					if (seatData.availableStatus
							&& seatData.availableStatus == AVAILABLE_STATUS) {
						seatStatusInfo[seat.id] = seatData;
						div.attr('onclick', 'changeSeat("'
								+ seatData.seatsStatusId + '")');
					} else {
						delete seatStatusInfo[seat.id];
					}

					if (seatData != undefined && seatData != null) {
						if (seatData.status == "show") {
							img.css("visibility", "visible");

							if (seatData.availableStatus != AVAILABLE_STATUS) {
								img.attr('src', contextPath
										+ '/resources/img/1_2.png');
							}
							
						} else if (seatData.status == "hide") {
							// hidden
							img.css("visibility", "hidden");
						}
					}

					tr.append(td);
				}
				clsTable.append(tr);
			}
			debugger;
			clsTd.append(clsTable);
			clsTr.append(clsTd);

			table.append(clsHeaderTr);
			table.append(clsTr);
		}

		screenLayoutDiv.append(table);

		$("#dialog").dialog("open");
	});
}

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

function changeSeat(obj) {

	var seats = parseInt($('#seats').val());
	var contextPath = $('#contextPath').val();
	for ( var i in seatStatusInfo) {
		if (seatStatusInfo[i].seatsStatusId == obj) {
			seatStatusInfo[i].availableStatus = BLOCKED_STATUS;
			var img = $('#' + seatStatusInfo[i].seatsStatusId);
			img.attr('src', contextPath + '/resources/img/1_2.png');
		} else {
			seatStatusInfo[i].availableStatus = AVAILABLE_STATUS;
			var img = $('#' + seatStatusInfo[i].seatsStatusId);
			img.attr('src', contextPath + '/resources/img/1_1.png');
		}
	}

}
function getNumberOfSeats() {
	var counter = 0;
	for ( var i in seatStatusInfo) {
		if (seatStatusInfo[i].availableStatus == BLOCKED_STATUS) {
			counter++;
		}
	}
}

function getBookedSeats() {
	var bookedSeats = [];
	for ( var i in seatStatusInfo) {
		if (seatStatusInfo[i].availableStatus == BLOCKED_STATUS) {
			var seatStatus = {};
			seatStatus.id = seatStatusInfo[i].seatsStatusId;
			bookedSeats.push(seatStatus);
		}
	}
	var allBookedSeats = {};
	allBookedSeats.seatsStatus = bookedSeats;
	return allBookedSeats;
}
