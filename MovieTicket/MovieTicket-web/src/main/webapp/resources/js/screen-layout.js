/**
 * 
 */
var seatsTypeColumnsInfo = {};
var seatsTypeRowsInfo = {};
var seatsTypeLabelInfo = {};

var rowNumberToCharInfo = {};

var seatsTypeAndSeatsInfo = {};

var rowsInfo = [];

var rowNamesToClsType = {};

seatsTypeLabelInfo.balcony = "Balcony";
seatsTypeLabelInfo.gold = "Gold Class";
seatsTypeLabelInfo.first = "First Class";
seatsTypeLabelInfo.silver = "Silver Class";
seatsTypeLabelInfo.bronze = "Bronze Class";

function setRowsForSeatType(seatType, noofRowsId) {

	seatsTypeRowsInfo[seatType] = $('#' + noofRowsId).val();

}
function setColsForSeatType(seatType, noofColssId) {

	seatsTypeColumnsInfo[seatType] = $('#' + noofColssId).val();

}

function changeSeat(obj) {
	debugger;
	var img = $('#' + obj);
	var clsTableId = img.attr('clsTableId');
	var status;
	if (img.css("visibility") != "hidden") {
		status = "hidden";
	} else {
		status = "visible";
	}

	var row = parseInt(img.attr('row'));
	var col = parseInt(img.attr('col'));

	var seats = parseInt($('#seats').val());

	var table = $('#' + clsTableId);
	var rows = table.find('tr');

	var selctionType = $('#seatSelctionType').val();
	if ("single" == selctionType) {
		hideOrShow(obj);
	} else if ("row" == selctionType) {
		for (var i = 0; i < rows.length; i++) {
			if (i + 1 > row && i + 1 <= (row + seats)) {
				var tds = $(rows[i]).find('td');
				updateStatus($(tds[col + 1]).find('img').attr('id'), status);
			}
		}

	} else if ("column" == selctionType) {

		var tds = $(rows[row]).find('td');
		for (var c = 0; c < tds.length; c++) {
			if (c + 1 > col && c + 1 <= (col + seats)) {
				updateStatus($(tds[c + 1]).find('img').attr('id'), status);
			}
		}

	} else if ("entireRow" == selctionType) {
		for (var i = 0; i < rows.length; i++) {
			if (i == row) {
				var tds = $(rows[i + 1]).find('td');
				for (var c = 0; c < tds.length; c++) {
					hideOrShow($(tds[c]).find('img').attr('id'));
				}

			}
		}

	} else if ("entireColumn" == selctionType) {
		for (var i = 0; i < rows.length; i++) {
			var tds = $(rows[i]).find('td');
			for (var c = 0; c < tds.length; c++) {
				if (c == col) {
					hideOrShow($(tds[c + 1]).find('img').attr('id'));
				}
			}

		}
	}

}

function getRowNames(clsType, rows) {
	var rowNames = rowNamesToClsType[clsType];
	// if (rowNames == undefined) {
	setRowNames(clsType, rows);
	// }
	rowNames = rowNamesToClsType[clsType];
	return rowNames;
}

function setRowNames(clsType, rows) {
	var rowNames;
	// rowNames = rowNamesToClsType[clsType];
	if (rowNames == undefined) {
		rowNames = [];
	}
	for (var r = 0; r < rows; r++) {
		var rowName = rowNumberToCharInfo[r + 1];

		rowNames.push(rowName);
	}
	rowNamesToClsType[clsType] = rowNames;

}

function hideOrShow(imgId) {
	if (imgId == undefined) {
		return;
	}
	var img = $('#' + imgId);
	var row = parseInt(img.attr('row'));
	var col = parseInt(img.attr('col'));
	if (img.css("visibility") != "hidden") {
		img.css("visibility", "hidden");

	} else {
		img.css("visibility", "visible");

	}
}

function updateStatus(imgId, status) {
	if (imgId == undefined) {
		return;
	}
	var img = $('#' + imgId);
	var row = parseInt(img.attr('row'));
	var col = parseInt(img.attr('col'));
	img.css("visibility", status);
	if (status == "hidden") {

	} else {

	}

}

function setRowNumberToCharInfo() {
	rowNumberToCharInfo[1] = 'A';
	rowNumberToCharInfo[2] = 'B';
	rowNumberToCharInfo[3] = 'C';
	rowNumberToCharInfo[4] = 'D';

	rowNumberToCharInfo[5] = 'E';
	rowNumberToCharInfo[6] = 'F';
	rowNumberToCharInfo[7] = 'G';
	rowNumberToCharInfo[8] = 'H';

	rowNumberToCharInfo[9] = 'I';
	rowNumberToCharInfo[10] = 'J';
	rowNumberToCharInfo[11] = 'K';
	rowNumberToCharInfo[12] = 'L';

	rowNumberToCharInfo[13] = 'M';
	rowNumberToCharInfo[14] = 'N';
	rowNumberToCharInfo[15] = 'O';
	rowNumberToCharInfo[16] = 'P';

	rowNumberToCharInfo[17] = 'Q';
	rowNumberToCharInfo[18] = 'R';
	rowNumberToCharInfo[19] = 'S';
	rowNumberToCharInfo[20] = 'T';

	rowNumberToCharInfo[21] = 'U';
	rowNumberToCharInfo[22] = 'V';
	rowNumberToCharInfo[23] = 'W';
	rowNumberToCharInfo[24] = 'X';

	rowNumberToCharInfo[25] = 'Y';
	rowNumberToCharInfo[26] = 'Z';

}

function getRowsForClsType(clsType, totalRows, totalCols, tableId) {
	var trs = [];
	var rowNames = getRowNames(clsType, totalRows);
	for (var r = 0; r < totalRows; r++) {
		var row = $('<tr>');
		var rowNameTd = $('<td>');
		var rowName = rowNames[r];
		rowNameTd.text(rowName);
		row.append(rowNameTd);
		for (var c = 0; c < totalCols; c++) {
			var id = clsType + "_" + r + "_" + c;

			var seat = $('<td>');
			var div = $('<div>');
			var ahref = $('<a>');

			ahref.attr('href', 'javascript:;');
			div.attr('onclick', 'changeSeat("' + id + '")');

			var img = $('<img >');
			img.attr('src', 'resources/img/1_1.png');
			img.attr('row', r);
			img.attr('col', c);
			img.attr('id', id);
			img.attr('clsType', clsType);
			img.attr('clsTableId', tableId);
			img.attr('rowName', rowName);
			img.css("visibility", "visible");

			ahref.append(img);
			div.append(ahref);
			seat.append(div);

			row.append(seat);
		}
		trs.push(row);
	}
	return trs;
}

function getClsType(clsTypes, clsTypeName) {

	for (var i = 0; i < clsTypes.length; i++) {
		if (clsTypes[i].seatClsName == clsTypeName) {
			return clsTypes[i]
		}
	}
}
function getRow(rowsList, rowNum) {
	for (var i = 0; i < rowsList.length; i++) {
		if (rowsList[i].rowNum == rowNum) {
			return rowsList[i]
		}
	}
}

function getAllSeatsInfo() {
	var imgs = $('#screenLayoutTable').find('img');
	debugger;
	var allSeats = [];
	var clsTypeInfo;
	var rowInfo;
	for (var i = 0; i < imgs.length; i++) {

		var img = $(imgs[i]);
		var status = img.css("visibility");

		var row = img.attr('row');
		var col = img.attr('col');
		var clsType = img.attr('clsType');
		var rowName = img.attr('rowName');

		clsTypeInfo = getClsType(allSeats, clsType);
		if (clsTypeInfo == undefined) {
			clsTypeInfo = {};
			clsTypeInfo.seatClsName = clsType;
			clsTypeInfo.totalRows = seatsTypeRowsInfo[clsType];
			clsTypeInfo.totalCols = seatsTypeColumnsInfo[clsType];
			clsTypeInfo.rowsList = [];
			allSeats.push(clsTypeInfo);
		}

		// rowInfo = clsTypeInfo[rowName];// rowsList
		rowInfo = getRow(clsTypeInfo.rowsList, row)

		if (rowInfo == undefined) {
			rowInfo = {};
			rowInfo.rowName = rowName;
			rowInfo.rowNum = row;
			rowInfo.seats = [];
			clsTypeInfo.rowsList.push(rowInfo);
		}

		var seatInfo = {};

		seatInfo.colNum = col;
		if (status == 'visible') {
			seatInfo.status = 'show';
		} else {
			seatInfo.status = 'hide';
		}

		rowInfo.seats.push(seatInfo);

	}
	debugger;
	var screen = {};
	screen.seatClassTypes=allSeats;
	return JSON.stringify(screen);
}

$(function() {
	// generate row names

	$('#seats').attr('disabled', true);

	setRowNumberToCharInfo();
	function generateTable() {
		debugger;

		var screenLayoutDiv = $('#screenLayout');
		var screenLayoutTable = $('#screenLayoutTable');
		screenLayoutTable.remove();

		var table = $('<table>');
		table.attr('id', 'screenLayoutTable');
		table.attr('class', 'cell-border');

		var seatsTypes = $('#seatsType').val();
		for (var i = 0; i < seatsTypes.length; i++) {
			var tr = $('<tr>');
			var td = $('<td>');

			var rows = parseInt($('#rows').val());
			var columns = parseInt($('#columns').val());

			var clsType = seatsTypes[i];

			var clsTable = $('<table>');
			var clsTableId = clsType + 'Table';
			clsTable.attr('id', clsTableId);

			var row = $('<tr>');
			var clsTypeTd = $('<td>');
			clsTypeTd.attr('colspan', columns);
			clsTypeTd.html("<b>" + seatsTypeLabelInfo[seatsTypes[i]] + "</b>");
			row.append(clsTypeTd);
			clsTable.append(row);

			if (seatsTypeColumnsInfo[clsType] != undefined) {
				columns = seatsTypeColumnsInfo[clsType];
			}

			if (seatsTypeRowsInfo[clsType] != undefined) {
				rows = seatsTypeRowsInfo[clsType];
			}

			var trs = getRowsForClsType(clsType, rows, columns, clsTableId);
			for (var r = 0; r < trs.length; r++) {
				clsTable.append(trs[r]);
			}
			td.append(clsTable);
			tr.append(td);
			table.append(tr);
		}

		screenLayoutDiv.append(table);
		// table.dataTable();
	}

	$('#layout').click(function() {
		generateTable();

	});

	$('#seatSelctionType').change(function() {
		var selctionType = $('#seatSelctionType').val();
		if ("single" == selctionType) {
			$('#seats').attr('disabled', true);
		} else if ("row" == selctionType) {
			$('#seats').attr('disabled', false);
		} else if ("column" == selctionType) {
			$('#seats').attr('disabled', false);
		} else if ("entireRow" == selctionType) {
			$('#seats').attr('disabled', true);
		} else if ("entireColumn" == selctionType) {
			$('#seats').attr('disabled', true);
		}
	});

	$('#submit').click(function() {

		$('#jsonData').val(getAllSeatsInfo());
	});

	$('#seatsType')
			.change(
					function() {
						// seatsTypsAndRowsInfo
						var newSeatsTypsAndRowsInfo = {};
						var values = $('#seatsType').val();
						$('#seatsTypeTable').remove();

						var seatsTypeTable = $('<table>');
						seatsTypeTable.attr('id', 'seatsTypeTable');
						$('#seatsTypeTableInfo').append(seatsTypeTable);

						for (var i = 0; i < values.length; i++) {
							var row = $('<tr>');

							var tdSeatType = $('<td>');
							tdSeatType.text(seatsTypeLabelInfo[values[i]]);

							var tdSeatTypeRows = $('<td>');
							var id = values[i] + "_" + "Rows";

							var tdCols = $('<td>');
							var colsId = values[i] + "_" + "Cols";

							var rowsInput = $('<input type="number" max="26" min="1">');
							$(rowsInput).attr('id', id);
							newSeatsTypsAndRowsInfo[values[i]] = seatsTypeRowsInfo[values[i]];
							$(rowsInput).val(seatsTypeRowsInfo[values[i]]);
							$(rowsInput).attr(
									"onblur",
									'javascript:setRowsForSeatType("'
											+ values[i] + '","' + id + '")');
							var label = $('<label>');
							$(label).text("Rows");

							// tdSeatTypeRows.append($(label));
							tdSeatTypeRows.append(rowsInput);

							var colsInput = $('<input type="number" max="100" min="1">');
							colsInput.attr('id', colsId);
							colsInput.val(seatsTypeAndSeatsInfo[values[i]]);
							colsInput
									.attr("onblur",
											'javascript:setColsForSeatType("'
													+ values[i] + '","'
													+ colsId + '")');
							var colsLabel = $('<label>');
							$(colsLabel).text("Columns");

							// tdCols.append(colsLabel);
							tdCols.append(colsInput);

							row.append(tdSeatType);
							row.append(tdSeatTypeRows);
							row.append(tdCols);
							seatsTypeTable.append(row);
						}
						seatsTypeRowsInfo = newSeatsTypsAndRowsInfo;

					});

});