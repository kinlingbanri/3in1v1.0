<%@page import="com.store.model.StoreVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.store.model.StoreService"%>
<%@page import="com.device.model.DeviceService"%>
<%@page import="com.device.model.DeviceVO"%>
<%@page import="com.machine.model.MachineService"%>
<%@page import="com.machine.model.MachineVO"%>
<%@page import="com.store.model.DeviceMachineTotal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
StoreService storeService = new StoreService();
List<StoreVO> storeVOs = storeService.getAll();

request.setAttribute("storeVOs", storeVOs);

List<DeviceMachineTotal> deviceMachineTotals = new ArrayList<DeviceMachineTotal>();

DeviceService deviceService = new DeviceService();
MachineService machineService = new MachineService();

// Query All
for (StoreVO store : storeVOs) {
	int sid = store.getSid();
	String storeName = store.getName();
	List<DeviceVO> deviceVOs = deviceService.getAllBySid(sid);
	List<MachineVO> machineVOs = machineService.getAllBySid(sid);

	DeviceMachineTotal deviceMachineTotal = new DeviceMachineTotal();
	deviceMachineTotal.setSid(sid);
	deviceMachineTotal.setStoreName(storeName);
	deviceMachineTotal.setDeviceCount(deviceVOs.size());
	deviceMachineTotal.setMachineCount(machineVOs.size());
	deviceMachineTotals.add(deviceMachineTotal);

	// 	System.out.print(store.getSid() + ",");
	// 	System.out.print(store.getName() + ",");
	// 	System.out.print(store.getCity() + ",");
	// 	System.out.print(store.getDistrict()+ ",");
	// 	System.out.print(store.getPause()+ ",");
	// 	System.out.print(store.getSingle_count()+ ",");
	// 	System.out.print(store.getMulti_count()+ ",");
	// 	System.out.print(store.getDiscount_1_money()+ ",");
	// 	System.out.print(store.getDiscount_1_point()+ ",");
	// 	System.out.print(store.getDiscount_2_money()+ ",");
	// 	System.out.print(store.getDiscount_2_point()+ ",");
	// 	System.out.print(store.getDiscount_3_money()+ ",");
	// 	System.out.println(store.getDiscount_3_point());
}

request.setAttribute("deviceMachineTotals", deviceMachineTotals);



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
    	
/*     	.btn-circle { */
/*     			color:#FFF; */
/* 			    width: 30px; */
/* 			    height: 30px; */
/* 			    padding: 6px 0px; */
/* 			    border-radius: 15px; */
/* 			    text-align: center; */
/* 			    font-size: 12px; */
/* 			    line-height: 1.42857; */
/* 			} */
    	
    </style>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

		<%@ include file="./leftmenu.jsp"%>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<%@ include file="./topbar.jsp"%>

                <!-- Begin Page Content -->
                <div class="container-fluid">
<!-- 								<div class="container-fluid"  id="divStoreList" style="display:none;"> -->

                    <!-- Page Heading -->
<!--                     <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
<!--                     <p class="mb-4">點擊該列任一處，即可進入該店家的設定畫面 -->

                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h4 id="title" class="m-0 font-weight-bold text-primary" style="float:left;">店家配置 - 店家列表</h6>
                            <button id="btnBackList" type="button" class="btn btn-warning" style="margin-right:12px;; float:right; display:none;">返回店家列表</button>
                        </div>
                        
                        <!-- Store List Start -->
                        <div class="card-body" id="divStoreList">
<!--                         <div class="card-body" id=""divStoreList"" style="display:none;"> -->
                            <div class="table-responsive">
                                <table id="dataTable" class="table table-bordered table-hover" style="font-size:14px;"width="100%" cellspacing="0">
                                    <thead>
                                        <tr style="text-align:center;">
                                        		<th>#</th>
                                            <th>店名</th>
                                            <th>加值機數量</th>
                                            <th>消費機數量</th>
<!--                                             <th>狀態</th> -->
                                            <th></th>
                                        </tr>
                                    </thead>
<!--                                     <tfoot> -->
<!--                                         <tr> -->
<!--                                             <th>店名</th> -->
<!-- 																						 <th>加值機數量</th> -->
<!--                                             <th>消費機數量</th>
<!--                                             <th>狀態</th> -->
<!-- 																						<th></th> -->
<!--                                         </tr> -->
<!--                                     </tfoot> -->
                                    <tbody>
																		<c:forEach items="${deviceMachineTotals}" var="deviceMachineTotal" varStatus="id">
																			<tr style="text-align: center;" id="${deviceMachineTotal.sid}">
																				<th scope="row" style="text-align: center;" id="storeNO${deviceMachineTotal.sid}">${id.index + 1}</th>
																				<th id="storeName${deviceMachineTotal.sid}">${deviceMachineTotal.storeName}</th>
																				<th id="storeAddvalueCount${deviceMachineTotal.sid}">${deviceMachineTotal.deviceCount}</th>
																				<th id="storeConsumptionCount${deviceMachineTotal.sid}">${deviceMachineTotal.machineCount}</th>
																				<th style="padding: 3px 0 0 0;">
																					<button id="btnSid${deviceMachineTotal.sid}" type="button" class="btn btn-warning qrcode">列印設定</button>
																				</th>
																		</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- Store List End -->
                        
                        
                        <input type="hidden" name="sid" value="0" id="inputSid">
												<!-- QR code  -->
<!--                         <div id="divQrcodeList" class="container" style="background-color:#FFF7FB; padding-bottom:20px;"> -->
                        <div id="divQrcodeList" class="container" style="background-color:#FFF7FB; padding-bottom:20px; display:none;">
<!--                             <div style="margin:18px 0 6px 12px;"> -->
<!--                             		<h4 style="font-weight:900; float:left; margin-top:8px;">加值機</h4> -->
<!--                             </div> -->
                            <div>
		                            <table class="table table-hover" style="text-align: center;">
																	  <thead>
																	    <tr>
																	    	<th scope="col" style="padding: 12px 0 18px 0;">#</th>
																	      <th scope="col" style="padding: 12px 0 18px 0;">名稱</th>
																	      <th scope="col" style="padding: 12px 0 18px 0;">QR code</th>
<!-- 																	      <th scope="col"> -->
<!-- 																	      		<button type="button" class="btn btn-primary">批次下載</button> -->
<!-- 																	      </th> -->
																	    </tr>
																	  </thead>
																	  <tbody>
																		<input type='hidden' value="" id="inputIndex" />
																		<c:forEach var="count"  varStatus="id" begin ="1" end="200" step="1">
																			<tr id="trCon${count}">
																				<input type='hidden' value="" id="inputDid${count}" />
																				<input type='hidden' value="" id="inputDeviceNumber${count}" />
																				<input type='hidden' value="" id="inputMid${count}" />
																				<input type='hidden' value="" id="inputMachineNumber${count}" />
																				<td  style="padding: 1.2rem 0 0 0;">${id.index}</td>
																				<td style="padding: 1.2rem 0 0 0;" id="tdName${id.index}"></td>
																				<td>
																					<button id="btnQRcode${id.index}" class="btn btn-primary btn-qrcode" data-toggle="modal" data-target="#oneQRcodeModal">
																						<i class="fa fa-download"></i> 下載QR code
																					</button>
																				</td>
<!-- 																				<td> -->
<!-- 																					<input id="cbSelectDevice type="checkbox" class="form-check-input" value="" style="height:20px; width:20px; margin: 9px 0px 0 -0.625rem;"> -->
<!-- 																				</td> -->
																			</tr>
																		</c:forEach>
																	  </tbody>
																</table>
                            </div>
                        </div>
                        
                        
                        
                        
                
                
                





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
    
    <!-- QR code Modal-->
    <div class="modal fade" id="oneQRcodeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="oneQRcodeModalLabel">加值機1號</h5>
<!--                     <button class="close" type="button" data-dismiss="modal" aria-label="Close"> -->
<!--                         <span aria-hidden="true">True</span> -->
<!--                     </button> -->
                </div>
                <div class="modal-body" style="text-align: center;">
                	<img id="imgQRcode" with="240" heigh="240"
                				src="data:image/png;base64, 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">
								</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
                    <button id="btnQRcoceDownload" class="btn btn-primary" type="button">下載</button>
<!--                     <a class="btn btn-primary">下載</a> -->
                </div>
            </div>
        </div>
    </div>
    

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

    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>

		<!-- Bootstrap Switch Button bootstrap4-toggle@3.6.1 -->
		<script src="./js/bootstrap4-toggle.min.js"></script>

		<script>

		$(document).ready(function() {
			for(var i = 1; i <= 200; i++){
				$("#trCon"+i).hide();
			}
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
					  	"lengthMenu" : "每頁 _MENU_ 筆",
					    "search" 			: "關鍵字搜尋:",
					    "info"					: "顯示 _START_ 到 _END_ 共 _TOTAL_ 筆",
					    "paginate": {
					        "first"			: "第一頁",
					        "last"			: "最後一頁",
					        "next"			: "下一頁",
					        "previous"	: "上一頁"
					    }
					  }
			});


			function getDeviceObject(sid) {

				$.ajax({
					type : 'POST', //GET or POST
					url : "../DeviceMachineContentServlet", //請求的頁面
					cache : false, //防止抓到快取的回應
					data : {
						sid : sid
					},
					success : function(jsonObject) { //當請求成功後此事件會被呼叫
						console.log("jsonObject : " + jsonObject);
						var size = jsonObject.size;
						var name = jsonObject.name;
						console.log("size : " + size);
						$("#title").text(jsonObject.storename + " - 下載QR code");
						var deviceMachineContents = jsonObject["deviceMachineContents"];
						
							for(var i = 0; i < size; i++){
								var index = i + 1;
								$("#trCon" + index).show();
								
								var dm = deviceMachineContents[i];								
								$("#tdName" + index).text(dm.name);
								$("#inputDid" + index).val(dm.did);
								$("#inputDeviceNumber" + index).val(dm.deviceNumber);
								console.log("dm.devicenumber : " + dm.deviceNumber);
								$("#inputMid" + index).val(dm.machid);
								$("#inputMachineNumber" + index).val(dm.machineNumber);
								console.log("dm.machinenumber : " + dm.machineNumber);
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

			function getQRcode(count, deviceNumber, machineNumber, list){
				$.ajax({
					type : 'POST', //GET or POST
					url : "../QRcodeServlet", //請求的頁面
					cache : false, //防止抓到快取的回應
					data : {
						count : count,
						deviceNumber : deviceNumber,
						machineNumber : machineNumber,
						size : 240,
						list : list
					},
					success : function(jsonObject) { //當請求成功後此事件會被呼叫
						console.log("jsonObject : " + jsonObject);
						if(count == 'one'){
							$("#oneQRcodeModal").show();
							console.log("base64Str" + jsonObject.base64Str);
							$("#imgQRcode").attr("src", jsonObject.base64Str );
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
			

			  $(".qrcode").click(function(){
				  for(var i = 1; i <= 200; i++){
						$("#trCon"+i).hide();
					}
				  //$("#title").text("樹林站前店 - 列印QR Code");
				  $("#btnBackList").show();
					$("#divStoreList").hide();
					$("#divQrcodeList").show();
					console.log("qrcode id " + this.id);
					var sidStr = this.id;
					var sid = sidStr.substring(6, sidStr.length);
					$("#inputSid").val(sid);
					getDeviceObject(sid);
				});

			  $("#btnBackList").click(function(){
				  $("#title").text("店家列表");
				  $("#btnBackList").hide();
					$("#divStoreList").show();
					$("#divQrcodeList").hide();
				});

			$(".btn-qrcode").click(function(){
				var indexStr = this.id;
				console.log("indexStr : " + indexStr);
				var index = indexStr.substring(9, indexStr.length);
				console.log("index : " + index);

				var title = $("#title").text();
				var storeName = title.split(" - ");
				
				$("#oneQRcodeModalLabel").text( storeName[0] + " - " + $("#tdName" + index).text() );

				var deviceNumber = $("#inputDeviceNumber" + index).val();
				var machineNumber = $("#inputMachineNumber" + index).val();
				console.log("deviceNumber : " + deviceNumber);
				console.log("machineNumber : " + machineNumber);
				
				getQRcode('one', deviceNumber, machineNumber);
			});

			$("#btnQRcoceDownload").click(function(){
				var a = document.createElement("a"); //Create <a>
				a.href = $("#imgQRcode").attr('src');
			  //a.href = "data:image/png;base64," + ImageBase64; //Image Base64 Goes here
			  var name = $("#oneQRcodeModalLabel").text();
			  a.download = name + ".png"; //File name Here
			  a.click(); //Downloaded file
			});

		</script>
</body>

</html>