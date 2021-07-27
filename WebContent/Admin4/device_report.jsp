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
<!-- 								<div class="container-fluid"  id="divAddvalueList" style="display:none;"> -->

                    <!-- Page Heading -->
<!--                     <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
<!--                     <p class="mb-4">點擊該列任一處，即可進入該店家的設定畫面 -->

                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                           <h4 id="title" class="m-0 font-weight-bold text-primary" style="float:left;">數據報表</h6>
<!-- 								           <button id="btnNextConsumption" type="button" class="btn btn-primary" style="margin-right:12px;; float:right; display:none;">設定消費機</button> -->
                        </div>
                        
                        <div style="position: relative;">
                            		<div style="float:left;">
                            				<div class="btn-group" style="margin-top: 8px; ;">
																			  <button id="btnSelectStore" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" 
																			  					aria-haspopup="true" aria-expanded="false" style="margin: 8px 0 0 12px;">
																			  		選擇店家
																			  </button>
																			  <input type="hidden" name="inputSid" value="" id="inputSid">
																			  <div class="dropdown-menu store-menu">
																				    <c:forEach items="${storeVOs}" var="store" varStatus="id">
																				    		<a class="dropdown-item" href="#" id="s${store.sid }">${store.name }</a>
																				    </c:forEach>																				    
																			  </div>
																		</div>
                            		</div>
                            		
                            		<input type="hidden" name="excelName" value="" id="inputExcelName">
                            		<div style="float:left;">
                            				<div class="btn-group" style="margin-top: 8px; ;">
																			  <button id="btnSelectDevice" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" 
																			  					aria-haspopup="true" aria-expanded="false" style="margin: 8px 0 0 12px;">
																			  		選擇設備
																			  </button>
																			  <input type="hidden" name="inputDid" value="" id="inputDid">
																			  <input type="hidden" name="inputMid" value="" id="inputMid">
																			  <input type="hidden" name="inputDid" value="" id="inputDnumber">
																			  <input type="hidden" name="inputMid" value="" id="inputMnumber">
																			  <div class="dropdown-menu dm-menu">
																				    <c:forEach var="index" varStatus="id" begin="1" end="200" step="1">
																				    		<input type="hidden" name="inputDid" value="" id="inputDid-${index}">
																			  				<input type="hidden" name="inputMid" value="" id="inputMid-${index}">
																			  				<input type="hidden" name="inputDeviceNumber" value="" id="inputDeviceNumber-${index}">
																			  				<input type="hidden" name="inputMachineNumber" value="" id="inputMachineNumber-${index}">
																				    		<a class="dropdown-item dd-menu-a" id="dd-${index}">${index}</a>
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
                   
                        
                        <!-- addvalue report List -->
                        <div class="card-body" id="divAddvalueList">
<!--                         <div class="card-body" id=""divAddvalueList"" style="display:none;"> -->
                            <div class="table-responsive">
                                <table id="dataTable" class="table table-bordered" style="font-size:14px;"width="100%" cellspacing="0">
                                    <thead>
                                        <tr style="text-align:center;">
                                        		<th>#</th>
                                        		<th>帳號</th>
                                            <th>日期</th>
                                            <th>儲值金額</th>
                                            <th>儲值點數</th>
                                            <th>消費金額</th>
                                        </tr>
                                    </thead>
                                    <tbody>

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
			$('.dd-menu-a').hide();
			$("#btnSelectDevice").attr('disabled', true);
			
			$("#datepicker").val("按此選擇起迄日期");
			
			//var table = $('#dataTable').DataTable();
			//var dataTableConsumption = $('#dataTableConsumption').DataTable();

			table.column(2).visible(true);
			table.column(3).visible(true);
			table.column(4).visible(true);
			
		  $("#imgCalendar").click(function(){
			  //alert("imgCalendar");
		  	picker.hide();
			});
		});

		var table = $('#dataTable').dataTable({
					//"lengthMenu":"每頁 _MENU_ 筆",
				  "language": {					  
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
					  },
					  "pageLength": 50,
					  dom : 'Bfrtip',
					  buttons:[
								//'copy', 'csv', 'excel', 'pdf', 'print'
								{
		                extend: 'excelHtml5',
		                title: '統計資料',
		                text: '匯出Excel',
		                className : 'btn btn-success',
		                init : function(dt, node, config){
		                	$("#btnInquire").click(function(){
		                		config.title = $("#inputExcelName").val();
		                	});
		                	
// 		                	$("#inputExcelName").on('change', function() {
// 		                        config.title = this.value;
// 		                    });
			              }
		            },
// 		            {
// 		                extend: 'pdfHtml5',
// 		                title: '統計資料'
// 		            }
						],
						"autoWidth": true,
						"columns":[
							{ "width": "8%" },
							{ "width": "19%" },
							{ "width": "28%" },
							{ "width": "15%" },
							{ "width": "15%" },
							{ "width": "15%" },
						]
					  
			});



		    

		  $(".modify").click(function(){
			  $("#title").text("樹林站前店 - 設定加值機");
				$("#divAddvalueList").hide();
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
			  $("#divAddvalueList").show();
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

		  function getDeviceObject(sid) {

				$.ajax({
					type : 'POST', //GET or POST
					url : "../DeviceMachineListServlet", //請求的頁面
					cache : false, //防止抓到快取的回應
					data : {
						sid : sid
					},
					success : function(jsonObject) { //當請求成功後此事件會被呼叫
						console.log("jsonObject : " + jsonObject);
						var size = jsonObject.size;
						console.log("size : " + size);

						if(size > 0){
							$("#btnSelectDevice").attr('disabled', false);
							
							var deviceMachineListVOs = jsonObject["deviceMachineListVOs"];
							
							for(var i = 0; i < size; i++){
								var index = i + 1;
								var dm = deviceMachineListVOs[i];	

								var number = dm.machineNumber;
								var type = number.substring(0, 2);

								if(type == 'TY'){
									$("#dd-" + index).text(dm.deviceName);
								}else if( (type == 'WS') || (type == 'DR')){
									$("#dd-" + index).text(dm.machineName);
								}
								
								$("#inputDid-" + index).val(dm.did);
								$("#inputMid-" + index).val(dm.machid);
								$("#inputDeviceNumber-" + index).val(dm.deviceNumber);
								$("#inputMachineNumber-" + index).val(dm.machineNumber);
								
								$("#dd-" + index).show();
							}
							
						}else if(size == 0){
							$("#btnSelectDevice").attr('disabled', true);
							alert("無任何設備，請至店家管理 -> 店家配置進行新增和設定");
						}
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

			$(".store-menu a").click(function(){
// 					console.log(this.text);
// 					console.log(this.id);

					$('.dd-menu-a').hide();
					$('#inputMid').val('');
					$("#btnSelectDevice").text( '選擇設備');
					var idStr = this.id;
					console.log(".store-menu a" + this.id);
					$("#btnSelectStore").text( this.text );
					$("#inputSid").val( idStr.substring(1, idStr.length) );
					var sid = $("#inputSid").val();
					console.log( sid );
					getDeviceObject(sid);
			});

			$(".dm-menu a").click(function(){
				$("#btnSelectDevice").text( this.text );
				var idStr = this.id;
				var index = idStr.substring(3, idStr.length);
				$("#inputDid").val( $("#inputDid-" + index).val()  );
				$("#inputMid").val( $("#inputMid-" + index).val()  );
				$("#inputDnumber").val( $("#inputDeviceNumber-" + index).val()  );
				$("#inputMnumber").val( $("#inputMachineNumber-" + index).val()  );
				var fileName = $("#btnSelectStore").text() + " - " + $("#btnSelectDevice").text();
				$("#inputExcelName").val( fileName );
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
					var number = $("#inputDnumber").val();
					var type = number.substring(0, 2);
					console.log("type : " + type);
				 
					var sid = $("#inputSid").val();
					var did = $("#inputDid").val();
					var mid = $("#inputMid").val();
					var dNumber = $("#inputDnumber").val();
					var mNumber = $("#inputMnumber").val();
					
					var str = $("#datepicker").val();

					var dateArray = str.split(' / ');
					console.log(dateArray[0]);
					console.log(dateArray[1]);
				 
				 $.ajax({
						type : 'POST', //GET or POST
						url : "../DeviceReportServlet", //請求的頁面
						cache : false, //防止抓到快取的回應
						data : {
							sid : sid,
							did : did,
							mid : mid,
							dNumber : dNumber,
							mNumber : mNumber,
							startDate : dateArray[0],
							endDate : dateArray[1]
						},
						success : function(jsonObject) { //當請求成功後此事件會被呼叫
							document.getElementById("loader").style.display = "none";
							console.log("jsonObject : " + jsonObject);

							

 							var type = mNumber.substring(0, 2);
 							if(type == "TY"){
 								var addRecordVOs = jsonObject["addRecordVOs"];
 	 							console.log("addRecordVOs:" + addRecordVOs);

 	 							var count = addRecordVOs.length;
 	 							console.log("count:" + count);

 	 							for(var i = 0; i < count; i++){
 	 								var addRecordVO = addRecordVOs[i];

 	 								//去除小數點
 	 								var time = addRecordVO.storedatetime.split(".")[0];

 	 								var rowNode = table.row.add( [ 
 	 	 																		i+1,
 	 	 																		addRecordVO.username,
 	 	 																		time,
 	 	 																		addRecordVO.money,
 	 	 																		addRecordVO.point,
 	 	 																		'-'
 	 	 																] ).draw().node();
 	 								
 	 								$( rowNode ).css('text-align','center');
 	 							}
 	 							table.column(2).visible(true);
 	 							table.column(3).visible(true);
 	 							table.column(4).visible(false);

 	 						}else if( (type == "WS") || (type == "DR") ){
 	 							var historyVOs = jsonObject["historyVOs"];
 	 							console.log("historyVOs:" + historyVOs);

 	 							var count = historyVOs.length;
 	 							console.log("count:" + count);

 	 							for(var i = 0; i < count; i++){
 	 								var historyVO = historyVOs[i];

 	 								//去除小數點
 	 								var time = historyVO.ttime.split(".")[0];

 	 								var rowNode = table.row.add( [ 
 	 	 																		i+1,
 	 	 																		historyVO.mid,
 	 	 																		time,
 	 	 																		'-',
 	 	 																		'-',
 	 	 																		historyVO.point
 	 	 																] ).draw().node();
 	 								$( rowNode ).css('text-align','center');
 	 							}
 	 							table.column(2).visible(false);
 	 							table.column(3).visible(false);
 	 							table.column(4).visible(true);
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
				 var alertStr = "";
				 var str = $("#datepicker").val();
				 var dateArray = str.split(' / ');
				 var sid = $("#inputSid").val();
				 var mid = $("#inputMid").val();
				 console.log("dateArray[1]" +  dateArray[1]);
				 if((str == "按此選擇起迄日期") || (dateArray[1] == "...")){
					 alertStr += "請選擇起迄日期!\n";
				 }
				 if(sid == ''){
					 alertStr += "請選擇店家!\n";
				 }
				 if(mid == ''){
					 alertStr += "請選擇設備!";
				 }

				 if(alertStr == ""){
					inquire();
				 }else{
					 alert(alertStr);
				 }
			 });

			 

		</script>
</body>

</html>