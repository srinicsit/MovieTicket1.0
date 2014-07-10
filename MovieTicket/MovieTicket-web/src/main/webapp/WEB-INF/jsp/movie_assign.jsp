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
		$('#hours').spinner({
			min : 0,
			max : 23
		});
		$('#mins').spinner({
			min : 0,
			max : 59
		});
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
				"mData" : "showHours"
			}, {
				"mData" : "showMins"
			}, {
				"mData" : "id"
			}

			],
			"columnDefs" : [ {
				"targets" : [ 6 ],
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
							$('#movieAssignId').val(row.id);
							$('#theaterId').val(row.screen.theater.id);
							$('#showDate').val(getDate(row.showDate));
							$('#screenId').val(row.screen.id);

							$('#locations').val(
									row.screen.theater.location.name);

							$('#theaterName').val(row.screen.theater.name);
							$('#screenName').val(row.screen.name);
							$('#hours').val(row.showHours);
							$('#mins').val(row.showMins);

							document.movieAssignForm.action = document.movieAssignForm.action
									+ "/update";
							createLocation('locations');

						});
		$('.ui-icon-plus')
				.click(
						function() {

							document.movieAssignForm.action = document.movieAssignForm.action
									+ "/save";
							$("#dialog").dialog("open");

						});

		$('.ui-icon-trash')
				.click(
						function() {
							debugger;
							var row = table.row('.selected').data();

							$('#movieAssignId').val(row.id);
							$('#showDate').val(getDate(row.showDate));

							document.movieAssignForm.action = document.movieAssignForm.action
									+ "/delete";
							submitForm();

						});

		function createLocation(locationId) {

			$('#' + locationId).autocomplete({
				source : $('#contextPath').val() + "/locations/partialList",
				minLength : 1,
				focus : function(event, ui) {
					$('#' + locationId).val(ui.item.name);
					return false;
				},
				select : function(event, ui) {
					debugger;
					$('#locationId').val(ui.item.id)
					createTheater("theaterName");
					return false;
				}

			}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li>").append("<a>" + item.name + "</a>")
						.appendTo(ul);
			};
		}

		function createTheater(theaterId) {
			$('#' + theaterId).autocomplete(
					{
						source : $('#contextPath').val() + "/theater/list/"
								+ getLocationIdVal(),
						minLength : 1,
						focus : function(event, ui) {
							$("#" + theaterId).val(ui.item.name);

							return false;
						},
						change : function(event, ui) {

						},
						select : function(event, ui) {
							$("#" + theaterId).val(ui.item.name);
							$('#theaterId').val(ui.item.id)
							createScreen("screenName");

							return false;
						}
					}).data("ui-autocomplete")._renderItem = function(ul, item) {

				return $("<li>").append("<a>" + item.name + "</a>")
						.appendTo(ul);
			};
		}
		function createScreen(screenId) {
			$('#' + screenId).autocomplete(
					{
						source : $('#contextPath').val() + "/screen/list/"
								+ getTheaterIdVal(),
						minLength : 1,
						focus : function(event, ui) {
							$("#" + screenId).val(ui.item.name);

							return false;
						},
						change : function(event, ui) {

						},
						select : function(event, ui) {
							$("#" + screenId).val(ui.item.name);
							$('#screenId').val(ui.item.id)

							return false;
						}
					}).data("ui-autocomplete")._renderItem = function(ul, item) {

				return $("<li>").append("<a>" + item.name + "</a>")
						.appendTo(ul);
			};
		}

		createLocation('locations');

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
					<th>Show Hours</th>
					<th>Show Minutes</th>
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
				<form:hidden path="screenId" id="screenId" />


				<div>
					<div class="labelCell">
						<label>Location</label>
					</div>
					<div class="valueCell">
						<input type="text" id="locations" />
					</div>
				</div>
				<div>
					<div class="labelCell">
						<label for="theaterName">Theater</label>
					</div>
					<div class="valueCell">
						<input type="text" id="theaterName" />
					</div>
				</div>
				<div>
					<div class="labelCell">
						<label for="screenName">Screen</label>
					</div>
					<div class="valueCell">
						<input type="text" id="screenName" />
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
						<form:label path="hours">Hours</form:label>
					</div>
					<div class="valueCell">
						<form:input path="hours" id="hours" />
					</div>
				</div>

				<div>
					<div class="labelCell">
						<form:label path="mins">Minutes</form:label>
					</div>
					<div class="valueCell">
						<form:input path="mins" id="mins" />
					</div>
				</div>
			</fieldset>
		</form:form>

	</div>

</body>

</html>

