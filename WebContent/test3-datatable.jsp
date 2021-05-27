<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title></title>
<!-- 	<link rel="stylesheet" href="./jquery.dataTables.css"> -->
<!-- 	<link rel="stylesheet" href="./scroller.dataTables.css"> -->
	
<!-- 	<script src="https://code.jquery.com/jquery-3.5.1.js"></script> -->
<!-- 	<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script> -->
<!-- 	<script src="https://cdn.datatables.net/scroller/2.0.3/js/dataTables.scroller.min.js"></script> -->
	
	  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <style>
  #custom-handle {
    width: 3em;
    height: 1.6em;
    top: 50%;
    margin-top: -.8em;
    text-align: center;
    line-height: 1.6em;
  }
  </style>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    var handle = $( "#custom-handle" );
    $( "#slider" ).slider({
      create: function() {
        handle.text( $( this ).slider( "value" ) );
      },
      slide: function( event, ui ) {
        handle.text( ui.value );
      }
    });
  } );
	
	
	
	
	<script>
	var tableObj = null;
	
	$(document).ready(function() {

// 		//20210520 version
//		//第一次拉scroller時,資料無法呈現,第二次之後才沒問題
//		//拉到最後項目時,最後幾筆要很久才會出現,而且會一直動態往上移,可能是持續增加新筆數?
// 		$('#example').DataTable( {
// 	        serverSide: true,
// 	        ordering: false,
// 	        searching: false,
// 	        "ajax": {
// 	            "url": "./DeviceHistorySevlet",
// 	            "type": "POST",
// 	            "dataSrc":"historyVOs"
// 	        },
// 	        "columns": [
// 	            { "data": "ttime" },
// 	            { "data": "event" }
// 	        ],
// 	        scrollY: 600,
// 	        deferRender:    false,
// 	        scroller: {
// 	            loadingIndicator: true,
// 	            serverWait: 500
// 	        },
// 	    } );
		

// 		//20210519 version
// 		//越後面的查詢越久,甚至到10幾秒以上
// 		tableObj = $('#example').DataTable( {
// 	        "processing": true,
// 	        "serverSide": true,
// 	        "searching": false,
// 	        "deferLoading": 689000,
// 	        'retrieve': true,
// 	        "ajax": {
// 	            "url": "./DeviceHistorySevlet",
// 	            "type": "POST",
// 	            "dataSrc":"historyVOs"
// 	        },
// 	        "columns": [
// 	            { "data": "ttime" },
// 	            { "data": "event" }
// 	        ]
// 	    } );

// 		$('#example').on('page.dt', function(e, settings, len){
// 			console.log(tableObj.page.info());
// 			//console.log(tableObj.api());
// 			console.log( 'New page length: '+ (parseInt(tableObj.page(), 10) +1) );
// 			tableObj.ajax.reload( null, false );
// 		});

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