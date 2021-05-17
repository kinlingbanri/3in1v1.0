<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="./Admin2/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="./Admin2/vendor/bootstrap/css/dataTables.bootstrap4.min.css" rel="stylesheet">
	<link href="./Admin2/vendor/bootstrap/css/buttons.bootstrap4.min.css" rel="stylesheet">
	<link href="./Admin2/vendor/bootstrap/css/select.bootstrap4.min.css" rel="stylesheet">
	<link href="./Admin2/vendor/bootstrap/css/dataTables.dateTime.min.css" rel="stylesheet">
	<link href="./Admin2/vendor/bootstrap/css/editor.bootstrap4.min.css" rel="stylesheet">
	
	<script src="./Admin2/vendor/bootstrap/jss/jquery-3.5.1.js"></script>
	<script src="./Admin2/vendor/bootstrap/jss/popper.min.js"></script>
	<script src="./Admin2/vendor/bootstrap/jss/bootstrap.min.js"></script>
	
		<script src="./Admin2/vendor/bootstrap/jss/jquery.dataTables.min.js"></script>
	
		<script src="./Admin2/vendor/bootstrap/jss/dataTables.bootstrap4.min.js"></script>
	


	<script src="./Admin2/vendor/bootstrap/jss/dataTables.buttons.min.js"></script>
	<script src="./Admin2/vendor/bootstrap/jss/buttons.bootstrap4.min.js"></script>
	<script src="./Admin2/vendor/bootstrap/jss/dataTables.select.min.js"></script>
	<script src="./Admin2/vendor/bootstrap/jss/dataTables.dateTime.min.js"></script>
	

	
	<script src="./Admin2/vendor/bootstrap/jss/dataTables.editor.min.js"></script>
	
	<script src="./Admin2/vendor/bootstrap/jss/editor.bootstrap4.min.js"defer></script>
	
	
		<script class="init">
	


var editor; // use a global for the submit and return data rendering in the examples

$(document).ready(function() {
	editor = new $.fn.dataTable.Editor( {
		ajax: "../php/staff.php",
		table: "#example",
		fields: [ {
				label: "First name:",
				name: "first_name"
			}, {
				label: "Last name:",
				name: "last_name"
			}, {
				label: "Position:",
				name: "position"
			}, {
				label: "Office:",
				name: "office"
			}, {
				label: "Extension:",
				name: "extn"
			}, {
				label: "Start date:",
				name: "start_date",
				type: 'datetime'
			}, {
				label: "Salary:",
				name: "salary"
			}
		]
	} );

	var table = $('#example').DataTable( {
		lengthChange: false,
		ajax: "../php/staff.php",
		columns: [
			{ data: null, render: function ( data, type, row ) {
				// Combine the first and last names into a single table field
				return data.first_name+' '+data.last_name;
			} },
			{ data: "position" },
			{ data: "office" },
			{ data: "extn" },
			{ data: "start_date" },
			{ data: "salary", render: $.fn.dataTable.render.number( ',', '.', 0, '$' ) }
		],
		select: true
	} );

	// Display the buttons
	new $.fn.dataTable.Buttons( table, [
		{ extend: "create", editor: editor },
		{ extend: "edit",   editor: editor },
		{ extend: "remove", editor: editor }
	] );

	table.buttons().container()
		.appendTo( $('.col-md-6:eq(0)', table.table().container() ) );
} );



	</script>
	

</head>
<body>

				<div class="demo-html">
					<table id="example" class="table table-striped table-bordered" style="width:100%">
						<thead>
							<tr>
								<th>Name</th>
								<th>Position</th>
								<th>Office</th>
								<th>Extn.</th>
								<th>Start date</th>
								<th>Salary</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Name</th>
								<th>Position</th>
								<th>Office</th>
								<th>Extn.</th>
								<th>Start date</th>
								<th>Salary</th>
							</tr>
						</tfoot>
					</table>
				</div>

</body>
</html>