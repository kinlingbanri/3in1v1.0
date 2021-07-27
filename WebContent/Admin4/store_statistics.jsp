<%@page import="com.store.model.StoreVO"%>
<%@page import="java.util.List"%>
<%@page import="com.store.model.StoreService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
StoreService storeService = new StoreService();
List<StoreVO> storeVOs = storeService.getAll();

request.setAttribute("storeVOs", storeVOs);


// Query All
for (StoreVO store : storeVOs) {
	System.out.print(store.getSid() + ",");
	System.out.print(store.getName() + ",");
	System.out.print(store.getCity() + ",");
	System.out.print(store.getDistrict()+ ",");
	System.out.print(store.getPause()+ ",");
	System.out.print(store.getSingle_count()+ ",");
	System.out.print(store.getMulti_count()+ ",");
	System.out.print(store.getDiscount_1_money()+ ",");
	System.out.print(store.getDiscount_1_point()+ ",");
	System.out.print(store.getDiscount_2_money()+ ",");
	System.out.print(store.getDiscount_2_point()+ ",");
	System.out.print(store.getDiscount_3_money()+ ",");
	System.out.println(store.getDiscount_3_point());
}



%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>複合式加值機後台管理</title>

    <!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="./css/fonts.googleapis.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    
    <!-- Bootstrap Switch Button bootstrap4-toggle@3.6.1 -->
    <link href="./css/bootstrap4-toggle.min.css" rel="stylesheet">
    
    <!-- lightpick -->
    <link href="./css/lightpick.css" rel="stylesheet">
    
    <!-- loader -->
    <link href="./css/loader.css" rel="stylesheet">
    
    <style>
    	.configBlock{
    		padding-top:6px;
    		padding-bottom:6px;
    		background-color:#00BCD4;
    		color:#FFF;
    		padding: 0;
    		margin: 6px;
    		text-align:center;
    	}
    	
    	.configInput{
    		width:70%;
    		margin:0 auto;
    		text-align: center;
    		height: 2rem;
    	}
    </style>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

				<%@ include file="./leftmenu.jsp" %>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

								<%@ include file="./topbar.jsp" %>

                <!-- Begin Page Content -->
                <div class="container-fluid">
<!-- 								<div class="container-fluid"  id="divStoreList" style="display:none;"> -->

                    <!-- Page Heading -->
<!--                     <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
<!--                     <p class="mb-4">點擊該列任一處，即可進入該店家的設定畫面 -->

                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                           <h4 id="title" class="m-0 font-weight-bold text-primary" style="float:left;">統計資料</h6>
								           <button id="btnNextConsumption" type="button" class="btn btn-primary" style="margin-right:12px;; float:right; display:none;">設定消費機</button>
                        </div>
                        
                        <div style="position: relative;">
                            		<div style="float:left;">
                            				<div class="btn-group" style="margin-top: 8px; ;">
																			  <button id="btnSelectStore" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin: 8px 0 0 12px;">
																			  		全部店家
																			  </button>
																			  <input type="hidden" name="inputSid" value="all" id="inputSid">
																			  <div class="dropdown-menu store-menu">
																			  		<a class="dropdown-item" href="#" id="sall">全部店家</a>
																				    <c:forEach items="${storeVOs}" var="store" varStatus="id">
																				    		<a class="dropdown-item" href="#" id="s${store.sid }">${store.name }</a>
																				    </c:forEach>																				    
																			  </div>
																		</div>
                            		</div>
                            		<div>
                            				<img id="imgCalendar" src="./img/calendar.png" width="32px;" height="32px;" style="position: absolute; margin: 18px 0 0 19px;">
<!--                             				<input id="datepicker" value="按此選擇起迄日期" type="text" style="float:left; height:38px; width:232px; margin:16px 0 0 16px; padding-left:8px; text-align: right; background-color: #3BB8F2; border-style: none; color: #FFF; border-radius: 6px;"/> -->
																				<input id="datepicker" value="按此選擇起迄日期" type="text" style="float:left; height:38px; width:240px; margin:16px 0 0 16px; padding-left:36px; text-align: center; border-radius: 6px; background-color: transparent;"/>
<!--                             				<i class="fa fa-calendar" aria-hidden="true"></i> -->
                            		</div>
                            		
<!--                             		<button id="btnExcel" type="button" class="btn btn-success" style="margin: 16px 12px 0 0; float:right;">輸出Excel</button> -->
                            		<button id="btnInquire" type="button" class="btn btn-info" style="margin: 16px 12px 0 0; float:right;">查詢</button>
                        </div>
                        
                        <!-- Divider -->
            						<hr class="sidebar-divider">
                   
                        
                        <!-- Store List -->
                        <div class="card-body" id="divStoreList">
<!--                         <div class="card-body" id=""divStoreList"" style="display:none;"> -->
                            <div class="table-responsive">
                                <table id="dataTable" class="table table-bordered" style="font-size:14px;"width="100%" cellspacing="0">
                                    <thead>
                                        <tr style="text-align:center;">
                                        		<th>#</th>
                                            <th>名稱</th>
                                            <th>儲值金額</th>
                                            <th>儲值點數</th>
                                            <th>消費點數</th>
                                        </tr>
                                    </thead>
<!--                                     <tfoot> -->
<!--                                         <tr> -->
<!--                                         		<th>#</th> -->
<!--                                             <th>店名</th> -->
<!--                                             <th>儲值金額</th> -->
<!--                                             <th>儲值點數</th> -->
<!--                                             <th>消費點數</th> -->
<!--                                         </tr> -->
<!--                                     </tfoot> -->
                                    <tbody>
                                    
                                    
<%--                                     <c:forEach items="${storeVOs}" var="store" varStatus="id"> --%>
<%-- 																			<tr  style="text-align:center;" id="${store.sid}"> --%>
<%-- 																				<th scope="row" style="text-align: center;">${id.index + 1}</th> --%>
<%-- 																				<th>${store.name}</th> --%>
<!-- 																				<th>52800元</th> -->
<!-- 																				<th>60400點</th> -->
<!-- 																				<th>48000點</th> -->
																				
<%-- 															   			</c:forEach> --%>

                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- /.container-fluid -->





            </div>
            <!-- End of Main Content -->


        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">True</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>
    
    	<!-- Loader Div -->
			<div style="position: fixed; top: 50%; left: 50%; z-index: 99;">
				<div id="loader" style="display: none;"></div>
			</div>
			<!-- End Loader Div -->

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
    
    <!-- datatable export plugin -->
    <script src="./js/dataTables.buttons.min.js"></script>
    <script src="./js/jszip.min.js"></script>
    <script src="./js/pdfmake.min.js"></script>
    <script src="./js/vfs_fonts.js"></script>
    <script src="./js/buttons.html5.min.js"></script>
    <script src="./js/buttons.print.min.js"></script>
    

    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>

		<!-- Bootstrap Switch Button bootstrap4-toggle@3.6.1 -->
		<script src="./js/bootstrap4-toggle.min.js"></script>
		
		<!-- lightpick -->
		<script src="./js/momnet-2.22.2.min.js"></script>		
		
		<!-- lightpick -->
		<script src="./js/lightpick.js"></script>
		
		<script>
		    //var picker = new Lightpick({ field: document.getElementById('datepicker') });

		    var picker = new Lightpick({
		        field: document.getElementById('datepicker'),
		        singleDate: false,
		        format:"YYYY-MM-DD",
		        separator:" / ",
		        onSelect: function(start, end){
		            var str = '';
		            str += start ? start.format('Do MMMM YYYY') + ' to ' : '';
		            str += end ? end.format('Do MMMM YYYY') : '...';
		            document.getElementById('datepicker').innerHTML = str;
		        }
		    });
		</script>

		<script>

		$(document).ready(function(){
			$("#datepicker").val("按此選擇起迄日期");
			
			var table = $('#dataTable').DataTable();
		     
		    $('#dataTable tbody').on('click', 'tr', function () {
// 		    		var id = table.row( this ).id();
// 			    	$("#inputSid").val(id);
			    	
			    	
// 		        var data = table.row( this ).data();
// 		        //alert( 'You clicked on '+ data[0] +'\'s row' );
// 		        $("#textStoreName").text(data[0]);
// 		        $("#textSinglePoint").text( "目前消費點數:" + data[1]);
// 		        $("#textMultiPoint").text("目前消費點數:" + data[2]);
// 		        var str = data[3].split("/");
		        
// 		        $("#textDiscount1Dallas").text("目前設定金額:" + str[0]);
// 		        $("#textDiscount1Point").text("目前設定點數:" + str[1]);
// 		        var str = data[4].split("/");
// 		        $("#textDiscount2Dallas").text("目前設定金額:" + str[0]);
// 		        $("#textDiscount2Point").text("目前設定點數:" + str[1]);
// 		        var str = data[5].split("/");
// 		        $("#textDiscount3Dallas").text("目前設定金額:" + str[0]);
// 		        $("#textDiscount3Point").text("目前設定點數:" + str[1]);
// 		        var pause = data[6];
		        
// 		        if(pause =="啟用"){
// 		        	$('#cbStoreState').bootstrapToggle('on')
// 				    }else if(pause == "停用"){
// 				    	$('#cbStoreState').bootstrapToggle('off')
// 					  }
		    } );


		    $("#imgCalendar").click(function(){
			    //alert("imgCalendar");
		    	picker.hide();
			  });
		});
			
			//新增datatable row
// 			function addDatatble(){
// 				var table = $('#dataTable').DataTable();
// 				var rowNode = table.row.add( [ 'Tiger Nixon', 'System Architect', 'Edinburgh', 54, '2011/04/25', '$320,800' ] ).draw().node();
// 				$( rowNode ) .css( 'color', 'red' ).animate( { color: 'black' } );
// 			}

			$('#dataTable').dataTable({
					//"lengthMenu":"每頁 _MENU_ 筆",
				  "language": {
					  "pageLength": 50,
					  	"lengthMenu" : "每頁 _MENU_ 筆",
					    "search" 			: "關鍵字搜尋:",
					    "info"					: "顯示 _START_ 到 _END_ 共 _TOTAL_ 筆",
					    "infoEmpty"		: "顯示 0 到 0 共 0 筆",
					    "emptyTable" : "無資料",
					    "zeroRecords": " ",
					    "paginate": {
					        "first"			: "第一頁",
					        "last"			: "最後一頁",
					        "next"			: "下一頁",
					        "previous"	: "上一頁"
					    }
					  }
			  ,
					  dom : 'Bfrtip'
,
					  buttons:[
								//'copy', 'csv', 'excel', 'pdf', 'print'
								{
		                extend: 'excelHtml5',
		                title: '統計資料',
		                text: '匯出Excel',
		                className : 'btn btn-success'
		            },
// 		            {
// 		                extend: 'pdfHtml5',
// 		                title: '統計資料'
// 		            }
						]
					  
			});




		    

		  $(".modify").click(function(){
			  $("#title").text("樹林站前店 - 設定加值機");
				$("#divStoreList").hide();
				$("#divAddvalueSetting").show();
				$("#btnNextConsumption").show();
				$("#btnUpdateAddvalue").show();
				$("#btnBackList").show();
			});

		  $("#btnNextConsumption").click(function(){
			  $("#title").text("樹林站前店 - 設定消費機");
			  $("#divAddvalueSetting").hide();
			  $("#divConsumptionSetting").show();
				$("#btnUpdtateConsumption").show();
				$("#btnBackAddvalue").show();			  
			  $("#btnNextConsumption").hide();
				$("#btnUpdateAddvalue").hide();
				$("#btnBackList").hide();
			});

		  $("#btnUpdateAddvalue").click(function(){
			  alert("btnComplete!");
			});

		  $("#btnBackList").click(function(){
			  $("#title").text("店家配置");
			  $("#divAddvalueSetting").hide();
			  $("#divStoreList").show();
			  $("#btnNextConsumption").hide();
				$("#btnUpdateAddvalue").hide();
				$("#btnBackList").hide();
			});

		  $("#btnUpdtateConsumprio").click(function(){
			  alert("btnComplete!");
			});

		  $("#btnBackAddvalue").click(function(){
			  $("#title").text("樹林站前店 - 設定加值機");
			  $("#divConsumptionSetting").hide();
			  $("#divAddvalueSetting").show();
				$("#btnUpdtateConsumption").hide();
				$("#btnBackAddvalue").hide();
				$("#btnNextConsumption").show();
				$("#btnUpdateAddvalue").show();
				$("#btnBackList").show();
			});

			$(".store-menu a").click(function(){
// 					console.log(this.text);
// 					console.log(this.id);
					var idStr = this.id;
					$("#btnSelectStore").text( this.text );
					$("#inputSid").val( idStr.substring(1, idStr.length) );
					var sid = $("#inputSid").val();
					console.log( sid );

			});

			//第1個參數:store id
			//第2個參數:type(傳要改變的欄位名稱))
			//第2個參數:總共要傳幾個有效參數
			//第3個以後的參數:依第1個參數設定相同數量的參數值,超過的部份以""替代
			function updateStore(coulumName, arg1, arg2 ){
				var sid = $("#inputSid").val();
				$.ajax({
					type : 'POST', //GET or POST
					url : "../Store_Setting_Servlet", //請求的頁面
					cache : false, //防止抓到快取的回應
					data : {
						sid : sid,
						coulumName : coulumName,
						arg1 : arg1,
						arg2 : arg2
					},
					success : function(jsonObject) { //當請求成功後此事件會被呼叫
						
					},
					error : function(e) {
						console.log("e: " + e);
					}, //當請求失敗後此事件會被呼叫
					statusCode : { //狀態碼處理
						404 : function() {
							alert("page not found");
						}
					}
				});
			}


			function updateRowColor(){

			}

			$('#cbStoreState').change(function() {
				var pause = document.getElementById('cbStoreState').checked;
				
				if(pause == true){
					console.log("pause", pause);
					updateStore("pause", 1, "");
				}else if(pause == false){
					console.log("pause", pause);
					updateStore("pause", 0, "");
				}
				//updateStore("pause", state, "");
			 });

			 function inquire(){
				 document.getElementById("loader").style.display = "block";
					var table = $('#dataTable').DataTable();
					table.clear().draw();
				 
					var sid = $("#inputSid").val();
					var str = $("#datepicker").val();
					var dateArray = str.split(' / ');
					console.log(dateArray[0]);
					console.log(dateArray[1]);
				 
				 $.ajax({
						type : 'POST', //GET or POST
						url : "../StoreStatisticsServlet", //請求的頁面
						cache : false, //防止抓到快取的回應
						data : {
							sid : sid,
							startDate : dateArray[0],
							endDate : dateArray[1]
						},
						success : function(jsonObject) { //當請求成功後此事件會被呼叫
							document.getElementById("loader").style.display = "none";
							console.log("jsonObject : " + jsonObject);


							var statisticsAllVOs = jsonObject["statisticsAllVOs"];
							console.log("statisticsAllVOs:" + statisticsAllVOs);
							
							var count = statisticsAllVOs.length;

// 							$("#dataTable").find('thead')
// 							.append($('<tr>')
// 									.append($('<td>')
// 										.text("時間")
// 									)
// 									.append($('</td>'))
// 									.append($('<td>')
// 										.text("加值金額")
// 									)
// 									.append($('</td>'))
// 									.append($('<td>')
// 										.text("儲值點數")
// 									)
// 									.append($('</td>'))
// 						).append($('</tr>')

// 						);

							for(var i = 0; i < count; i++){
								//var table = $('#dataTable').DataTable();
								var statistics = statisticsAllVOs[i];
								var money;
								var add_point;
								var consumption_point;
								
								console.log("Number : " + statistics.number);

								if(sid == "all"){
									var rowNode = table.row.add( [ i+1, statistics.name, statistics.add_money, statistics.add_point,
																		statistics.consumption_point ] ).draw().node();
								}else{
									var str = (statistics.number).substring(0, 2);
									console.log("str : " + str);
									if(str == "TY"){
										money = statistics.add_money;
										add_point = statistics.add_point;
										consumption_point = '-';
									}else{
										money = '-';
										add_point = '-';
										consumption_point = statistics.consumption_point ;
									}
									var rowNode = table.row.add( [ i+1, statistics.name, money, add_point, consumption_point ] ).draw().node();

									var temtable = document.getElementById("dataTable");
									var rows = temtable.rows;
									var rowsLength = temtable.rows.length;

									for(j = 1; j < rowsLength; j++){
										console.log("j : " + j + ";" + temtable.rows[j].cells[4].innerHTML);
										var tmpStr = temtable.rows[j].cells[4].innerHTML;
										if(tmpStr == "-"){
											rows[j].style.backgroundColor = '#b0e0e6';
										}else{
											rows[j].style.backgroundColor = '#ffc0cb';
										}
									}

								}
								$( rowNode ).css('text-align','center');


							}
						},
						error : function(e) {
							document.getElementById("loader").style.display = "none";
							console.log("e: " + e);
						}, //當請求失敗後此事件會被呼叫
						statusCode : { //狀態碼處理
							404 : function() {
								document.getElementById("loader").style.display = "none";
								alert("page not found");
							}
						}
					});
					
			 }


			 $("#btnInquire").click(function(){
				 var str = $("#datepicker").val();
				 var dateArray = str.split(' / ');
				 console.log("dateArray[1]" +  dateArray[1]);
				 if((str == "按此選擇起迄日期") || (dateArray[1] == "...")){
						alert("請選擇起迄日期!");
				 }else{
					 inquire();
				 };
			 });

			 

		</script>
</body>

</html>