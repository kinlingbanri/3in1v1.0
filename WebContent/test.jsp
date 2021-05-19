<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title></title>
	<link rel="stylesheet" href="./jquery.dataTables.css">
	<link rel="stylesheet" href="./scroller.dataTables.css">
	
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/scroller/2.0.3/js/dataTables.scroller.min.js"></script>
	
	<script>
	$(document).ready(function() {
	    $('#example').DataTable( {
	        "processing": true,
	        "serverSide": true,
	        "ajax": {
	            "url": "./DeviceHistorySevlet",
	            "type": "POST",
	            "dataSrc":"historyVOs"
	        },
	        "columns": [
	            { "data": "ttime" },
	            { "data": "event" }
	        ]
	    } );
	} );
	</script>
</head>

<body>

<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>ttime</th>
                <th>event</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>ttime</th>
                <th>event</th>
            </tr>
        </tfoot>
    </table>

<script>

</script>
</body>
</html>