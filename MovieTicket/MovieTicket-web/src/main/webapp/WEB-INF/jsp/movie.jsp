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
<style type="text/css">
label,input {
	display: block;
}

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
<script type="text/javascript">
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
		var table = $("#movies").DataTable({
			"dom" : '<"toolbar">frtip',
			"bProcessing" : true,
			"bInfo" : false,
			"bServerSide" : false,
			searching : false,
			ordering : false,
			"sAjaxSource" : contextPath + "/movies/listForDt",
			"bJQueryUI" : true,
			"aoColumns" : [ {
				"mData" : "movieName"
			}, {
				"mData" : "language"
			}, {
				"mData" : "hours"
			}, {
				"mData" : "mins"
			}, {
				"mData" : "id"
			}

			],
			"columnDefs" : [ {
				"targets" : [ 4 ],
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
			debugger;
			var row = table.row('.selected').data();
			$('#movieId').val(row.id);
			$('#movieName').val(row.movieName);
			$('#language').val(row.language)

			$('#hours').val(row.hours);
			$('#mins').val(row.mins);

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
					debugger;
					fillDataInTable();

				}
			})
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
					<th>Hous</th>
					<th>Minutes</th>
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

			<fieldset>
				<form:label path="movieName">Name</form:label>
				<form:input path="movieName" id="movieName" class="text " />
				<form:label path="language">Language</form:label>
				<form:input path="language" id="language" class="text" />
				<form:label path="hours">Hours</form:label>
				<form:input path="hours" id="hours" class="text " />
				<form:label path="mins">Minutes</form:label>
				<form:input path="mins" id="mins" class="text " />


				<form:hidden id="movieId" path="movieId"></form:hidden>
			</fieldset>
		</form:form>

	</div>
</body>
</html>