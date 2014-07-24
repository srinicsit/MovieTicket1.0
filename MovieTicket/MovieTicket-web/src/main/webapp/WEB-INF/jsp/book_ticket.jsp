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
						debugger;
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
				var a = $('#days a');
				for (var i = 0; i < a.length; i++) {
					var anchor = $(a[i]);

					anchor.attr('onclick', 'javascript:loadScreens('
							+ data[i].day + ')');
					anchor.attr('day', data[i].day);
					anchor.html("<div>" + data[i].name + "</div>" + "<div>"
							+ data[i].day + "</div>")
				}

			}

		})

		function initDialog() {
			$("#dialog").dialog({
				modal : true,
				autoOpen : false,
				width : 900,
				height : 700,
				buttons : [ {
					text : "Ok",
					click : function() {
						var dialog = $(this);
						var bookedSeats = JSON.stringify(getBookedSeats());
						$('#bookingSeats').val(bookedSeats);

						submitForm();

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

		initDialog();

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
			<form:form name="bookTicketForm"
				action="${pageContext.servletContext.contextPath}${'/bookTicket'}"
				modelAttribute="bookTicketForm" method="post">
				<form:hidden path="bookingSeats" id="bookingSeats" />
				<table>
					<tr>
						<td><label>Seats</label></td>
						<td><input type="number" id="seats" value="1" max="10"
							min="1" /></td>
					</tr>
				</table>
			</form:form>

		</div>

		<div id='screenLayout' style="height: 400px; overflow: auto;">
			<table id="screenLayoutTable" cellpadding="0" cellspacing="0"
				border="0" class="display">
			</table>
		</div>
	</div>



</body>
</html>
