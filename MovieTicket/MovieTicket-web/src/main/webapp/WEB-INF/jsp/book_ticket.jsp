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
<script src="<c:url value="/resources/js/book_ticket.js" />"></script>

<script type="text/javascript">
	$(function() {
		var movies;
		loadSeatTypes();
		loadRowNumberToCharInfo();
		$('#screensInfo').tooltip({
			items : "[title]",
			track : true,
			content : function() {
				var element = $(this);

				if (element.is("[title]")) {

					var obj = jQuery.parseJSON(element.attr("title"));
					// 					var obj = JSON.parse(element.attr("title"));
					var table = $('<table cellpadding="10" cellspacing="10">')
					for (var i = 0; i < obj.length; i++) {
						var tr = $('<tr>');

						var availableTd = $('<td>');
						if (obj[i].count > 0) {
							availableTd.text("Available");
						} else {
							availableTd.text("Not Available");
						}

						var costTd = $('<td>');
						costTd.text("Rs " + obj[i].cost)

						var clsTd = $('<td>');
						clsTd.text(seatsTypeLabelInfo[obj[i].classTypeId]);

						tr.append(clsTd);
						tr.append($('<td>').html('&nbsp; &nbsp; &nbsp;'));

						tr.append(availableTd);
						tr.append($('<td>').html('&nbsp;&nbsp; &nbsp;'));

						tr.append(costTd);
						table.append(tr);
					}

					return table.html();
				}

			}
		});

		var activeTab;
		$("#days").tabs(
				{
					activate : function(event, ui) {
						var tabInx = ui.newTab.index();
						activeTab = ui.newTab;

						var anchor = $(activeTab.find('a')[0]);
						if (tabInx == 0) {
							$('#date').html(
									$(anchor).attr('dayName') + ",&nbsp;"
											+ $(anchor).attr('weekAndDay'));
						} else {
							$('#date').html($(anchor).attr('weekAndDay'));
						}

					}
				});
		$.ajax({
			type : "GET",
			url : $('#contextPath').val() + "/movies/list",
			success : function(data) {
				movies = data;
				addOptions(data, 'movie');
				$('#movie').combobox(
						{
							select : function(event, ui) {
								debugger;
								setMovieInfo();
								if (activeTab == undefined) {
									var anchors = $('#days a');
									loadScreens($(anchors[0]).attr('date'));
								} else {
									loadScreens($(activeTab.find('a')[0]).attr(
											'date'));
								}

							}
						});
			}
		})
		$.ajax({
			type : "GET",
			url : $('#contextPath').val() + "/bookTicket/days",
			success : function(data) {
				var a = $('#days a');
				for (var i = 0; i < a.length; i++) {
					var anchor = $(a[i]);

					anchor.attr('onclick', 'javascript:loadScreens('
							+ data[i].date + ')');
					anchor.attr('day', data[i].day);
					anchor.attr('dayName', data[i].name);
					anchor.attr('weekAndDay', data[i].weekAndDay);
					anchor.attr('date', data[i].date);
					anchor.html("<div>" + data[i].name + "</div>" + "<div>"
							+ data[i].day + "</div>")
					if (i == 0) {

						$('#date').html(
								$(anchor).attr('dayName') + ",&nbsp;"
										+ $(anchor).attr('weekAndDay'));
					}
				}

			}

		})

		function setMovieInfo() {

			var movieId = $('#movie').val();
			for (var i = 0; i < movies.length; i++) {
				if (movies[i].id == movieId) {

					$('#movieName').text(movies[i].movieName);
					$('#os_movieName').text(movies[i].movieName);

					$('#language').text(movies[i].language.name);
					$('#os_language').text(movies[i].language.name);

					$('#certificate').text(movies[i].certificate.code);
					$('#os_certificate').text(movies[i].certificate.code)

					var description = movies[i].certificate.description;
					if (description) {
						$('#description').text(description);
						$('#os_description').text(description);
					} else {
						$('#description').text("");
						$('#os_description').text("");
					}
					break;
				}
			}
		}

		function initDialog() {
			$("#dialog").dialog({
				modal : true,
				autoOpen : false,
				width : 900,
				height : 700,
				buttons : [ {
					text : "Ok",
					click : function() {
						// 						var dialog = $(this);
						// 						var bookedSeats = JSON.stringify(getBookedSeats());
						// 						$('#bookingSeats').val(bookedSeats);
						// 						submitForm();

						debugger;
						var dialog = $(this);
						dialog.dialog("close");
						var bookedSeats = getBookedSeats();
						var seatsData = "";
						var noBookedSeats = bookedSeats.seatsStatus.length;
						for (var i = 0; i < noBookedSeats; i++) {
							var bookedSeat = bookedSeats.seatsStatus[i];
							var seat = $('#' + bookedSeat.id);
							var r = seat.attr('row');
							var c = seat.attr('col');
							var rowName = rowNumberToCharInfo[parseInt(r) + 1];
							seatsData = seatsData + rowName + c + ",";
						}
						var iHandlingChrgs = 33.71 * noBookedSeats;
						$('#seatInfo').text(seatsData);
						$('#internetHandlingFees').text(iHandlingChrgs);
						$("#orderSummaryDialog").dialog("open");

					}
				}, {
					text : "Cancel",
					click : function() {
						resetForm();
						$(this).dialog("close");
					}
				} ]
			});
		}

		function initOrderSummaryDialog() {
			$("#orderSummaryDialog").dialog({
				modal : true,
				autoOpen : false,
				width : 450,
				height : 320,
				buttons : [ {
					text : "Ok",
					click : function() {

						// 						var dialog = $(this);
						// 						var bookedSeats = JSON.stringify(getBookedSeats());
						// 						$('#bookingSeats').val(bookedSeats);
						// 						submitForm();

					}
				}, {
					text : "Cancel",
					click : function() {
						// 						resetForm();
						$(this).dialog("close");
					}
				} ]
			});
		}

		initDialog();
		initOrderSummaryDialog();

	})

	function submitForm() {
		document.bookTicketForm.action = document.bookTicketForm.action
				+ "/save";
		document.bookTicketForm.submit();
	}

	function resetForm() {
		document.bookTicketForm.action = $('#contextPath').val()
				+ "/bookTicket";
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
		<div>
			<h3>
				<span id="movieName"></span>&nbsp;(<span id="language">&nbsp;
				</span>)&nbsp;(<span id="certificate"> </span>) <span style="color: red"
					id="description"> |</span>
			</h3>

		</div>

		<div>
			<h3>
				<span id="date"></span>,<span id="time"> </span>|<span
					id="theaterAddress"></span>
			</h3>
		</div>
		<div>
			<hr />
		</div>

		<div>
			<form:form name="bookTicketForm"
				action="${pageContext.servletContext.contextPath}${'/bookTicket'}"
				modelAttribute="bookTicketForm" method="post">
				<form:hidden path="bookingSeats" id="bookingSeats" />
				<table>
					<tr>
						<td><label>Quantity</label></td>
						<td><input type="number" id="seats" value="1" max="10"
							min="1" /></td>
						<td>&nbsp;</td>
						<td>Select Your Seats</td>
						<td>Total : Rs <span id="seatsPrice"> 0</span></td>
					</tr>
				</table>
			</form:form>

		</div>
		<fieldset>
			<div id='screenLayout' style="height: 400px; overflow: auto;">

				<table id="screenLayoutTable" cellpadding="0" cellspacing="0"
					border="0" class="display">
				</table>

			</div>
		</fieldset>
	</div>

	<div id="orderSummaryDialog" title="Order Summary">

		<div>

			<h3>
				<span id="os_movieName"></span>&nbsp;(<span id="os_language">&nbsp;
				</span>)&nbsp;(<span id="os_certificate"> </span>) <span style="color: red"
					id="os_description"> |</span>
			</h3>



		</div>

		<fieldset>
			<legend>
				<h3>Order Summary</h3>
			</legend>


			<div>
				<div class="labelCell">
					<label>Tickets(s)</label>
				</div>

				<div class="valueCell">
					<span id="os_seatsPrice"></span>
				</div>
			</div>
			<div>
				<div class="labelCell">
					<label>Seat Info</label>
				</div>

				<div class="valueCell">
					<span id="seatInfo"></span>
				</div>
			</div>

			<div>
				<div class="labelCell">
					<label>+ Internet handling fees</label>
				</div>

				<div class="valueCell">
					<span id="internetHandlingFees">33.71</span>
				</div>
			</div>

		</fieldset>
		<div>
			<div>
				<ui>
				<li>Tickets mandatory for guests above 3 years of age.</li>
				<li>An Internet handling fee (per ticket) will be levied. Check
					the same before completing the transaction.</li>
				</ui>
			</div>
		</div>
	</div>


</body>
</html>
