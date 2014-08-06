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
.overflow {
	height: 100px;
}
</style>
<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
<script type="text/javascript">
	var languageTypes = {};
	var certificateTypes = {};
	$(function() {
		var contextPath = $('#contextPath').val();

		/* $('#hours').spinner({
			min : 0,
			max : 23
		});
		$('#mins').spinner({
			min : 0,
			max : 59
		}); */
		initDialog();
		$('#releaseDate').datepicker();
		var table = $("#movies").DataTable({
			"dom" : '<"toolbar">frtip',
			"bProcessing" : true,
			"bInfo" : false,
			"bServerSide" : false,
			searching : false,
			ordering : false,
			"bJQueryUI" : true,
			"aoColumns" : [ {
				"mData" : "movieName"
			}, {
				"mData" : "language",
				"mRender" : function(data, type, row) {

					if (data) {
						return languageTypes[data.id];
					}
				}
			}, {
				"mData" : "releaseDate",
				"mRender" : function(data, type, row) {
					if (data) {
						return getDate(data);
					}
				}
			}, {
				"mData" : "hours",
				"mRender" : function(data, type, row) {

					if (data) {
						return data + ":" + row.mins;
					}
				}
			}, {
				"mData" : "certificate",
				"mRender" : function(data, type, row) {
					debugger;
					if (data) {
						return certificateTypes[data.id];
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

		$('#movies tbody').on('click', 'tr', function() {

			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('.ui-icon-pencil').click(function() {
			var row = table.row('.selected').data();
			$('#movieId').val(row.id);
			$('#movieName').val(row.movieName);
			$('#language').val(row.language.id);
			$('#certificateId').val(row.certificate.id);

			$('#releaseDate').val(getDate(row.releaseDate));

			$('#hours').val(row.hours);
			$('#mins').val(row.mins);

			$('#language').selectmenu("refresh");
			$('#hours').selectmenu("refresh");
			$('#mins').selectmenu("refresh");
			$('#certificateId').selectmenu("refresh");

			document.movieForm.action = document.movieForm.action + "/update";
			$("#dialog").dialog("open");

		});
		$('.ui-icon-plus').click(function() {
			document.movieForm.action = document.movieForm.action + "/add";
			$("#dialog").dialog("open");
		});

		$('.ui-icon-trash').click(function() {
			debugger;
			var rowId = table.row('.selected').data().id;
			$('#movieId').val(rowId);
			document.movieForm.action = document.movieForm.action + "/delete";
			submitForm();

		});

		function submitForm() {
			$.ajax({
				type : "post",
				url : document.movieForm.action,
				data : $("form").serialize(),
				success : function(data) {
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
						height : 350,
						buttons : [
								{
									text : "Ok",
									click : function() {
										$(this).dialog("close");
										submitForm();
										document.movieForm.action = $(
												'#contextPath').val()
												+ "/movies";
									}
								},
								{
									text : "Cancel",
									click : function() {
										document.movieForm.action = $(
												'#contextPath').val()
												+ "/movies";
										$(this).dialog("close");
									}
								} ]
					});
		}

		var langagesType = $('#language');
		$.get(contextPath + "/utils/allLanguages", function(data) {
			for (var i = 0; i < data.length; i++) {

				var option = $('<option>');
				option.attr('value', data[i].id);
				option.text(data[i].name);
				langagesType.append(option);

				languageTypes[data[i].id] = data[i].name;
			}
			langagesType.selectmenu("refresh");
			getAllCertificates();
		});

		function getAllCertificates() {
			var certificatesType = $('#certificateId');
			$.get(contextPath + "/utils/allCertificates", function(data) {
				for (var i = 0; i < data.length; i++) {

					var option = $('<option>');
					option.attr('value', data[i].id);
					option.text(data[i].code);
					certificatesType.append(option);

					certificateTypes[data[i].id] = data[i].code;
				}
				certificatesType.selectmenu("refresh");
				fillDataInTable();
			});
		}

		function fillDataInTable() {

			$.ajax({
				type : "GET",
				url : $('#contextPath').val() + "/movies/listForDt",
				success : function(data) {
					debugger;
					table.clear();
					table.rows.add(data.aaData);
					table.draw();

				}

			});
		}
		function getDate(data) {
			var d = new Date(data)
			var curr_date = d.getDate();
			var curr_month = d.getMonth();
			var curr_year = d.getFullYear();
			var dateString = (curr_month + 1 + "/" + curr_date + "/" + curr_year);
			return dateString;
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
		$('#hours').selectmenu().selectmenu("menuWidget").addClass("overflow");
		$('#mins').selectmenu().selectmenu("menuWidget").addClass("overflow");
		$('#language').selectmenu();
		$('#certificateId').selectmenu();
	});
</script>
</head>
<body>
	<div>
		<table id="movies" class="display">
			<thead>
				<tr>
					<th>Name</th>
					<th>Language</th>
					<th>Release Date</th>
					<th>Duration</th>
					<th>Certificate</th>
					<th>Id</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="dialog" title="Movie Details">

		<p class="validateTips">All form fields are required.</p>

		<form:form id="movieFormId" name="movieForm"
			action="${pageContext.servletContext.contextPath}${'/movies'}"
			modelAttribute="movieForm" method="post">

			<div>
				<div class="labelCell">
					<form:label path="movieName">Name</form:label>
				</div>
				<div class="valueCell">
					<form:input path="movieName" id="movieName" class="text " />
				</div>
			</div>

			<div>
				<div class="labelCell">
					<form:label path="language">Language</form:label>
				</div>
				<div class="valueCell">
					<form:select path="language" id="language" class="text"
						cssStyle="width:110px;">
						<option value="">Select</option>
					</form:select>
				</div>
			</div>

			<div>
				<div class="labelCell">
					<form:label path="certificateId">Certificate</form:label>
				</div>
				<div class="valueCell">
					<form:select path="certificateId" id="certificateId" class="text"
						cssStyle="width:110px;">
						<option value="">Select</option>
					</form:select>
				</div>
			</div>

			<div>
				<div class="labelCell">
					<form:label path="releaseDate">Release Date</form:label>
				</div>
				<div class="valueCell">
					<form:input path="releaseDate" id="releaseDate" class="text" />
				</div>
			</div>

			<div>
				<div class="labelCell">
					<form:label path="hours">Duration</form:label>
				</div>
				<div class="valueCell">
					<form:select path="hours" id="hours" cssStyle="width:80px;">
						<option value="">Hours</option>
					</form:select>
					&nbsp;
					<form:select path="mins" id="mins" cssStyle="width:80px;">
						<option value="">Minutes</option>
					</form:select>
				</div>
			</div>


			<form:hidden id="movieId" path="movieId"></form:hidden>
		</form:form>

	</div>
</body>
</html>