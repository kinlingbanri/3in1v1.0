<%@page import="com.machine.model.MachineService"%>
<%@page import="com.machine.model.MachineVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.store.model.DeviceMachineTotal"%>
<%@page import="java.util.Map"%>
<%@page import="com.device.model.DeviceService"%>
<%@page import="com.device.model.DeviceVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.store.model.StoreVO"%>
<%@page import="java.util.List"%>
<%@page import="com.store.model.StoreService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		System.out.print(sid + ",");
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

	int addValueQty = 0;
	String addValueDisplay1 = "block";
	String addValueDisplay2 = "block";
%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>複合式加值機後台管理</title>

<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link href="./css/fonts.googleapis.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Bootstrap Switch Button bootstrap4-toggle@3.6.1 -->
<link href="./css/bootstrap4-toggle.min.css" rel="stylesheet">

<style>
.configBlock {
	padding-top: 6px;
	padding-bottom: 6px;
	background-color: #00BCD4;
	color: #FFF;
	padding: 0;
	margin: 6px;
	text-align: center;
}

.configInput {
	width: 70%;
	margin: 0 auto;
	text-align: center;
	height: 2rem;
}
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
						<div class="card-header py-3" style="padding: 0 12px 0 12px;">
							<h4 id="title" class="m-0 font-weight-bold text-primary"
								style="float: left; padding: 6px 0 0 0;">店家配置 - 店家列表</h4>
							<button id="btnAddStore" class="btn btn-success" data-toggle="modal" data-target="#StoreNameModal"
										style="float: right;">
								<i class="fa fa-plus"></i> 新增店家
							</button>
							<button id="btnNextConsumption" type="button"
								class="btn btn-primary"
								style="margin-right: 12px;; float: right; display: none;">設定消費機</button>
							<button id="btnBackAddvalue" type="button"
								class="btn btn-primary"
								style="margin-right: 12px;; float: right; display: none;">設定加值機</button>
							<button id="btnBackList" type="button" class="btn btn-warning"
								style="margin-right: 12px;; float: right; display: none;">返回店家列表</button>

						</div>



						<!-- Store List -->
						<div class="card-body" id="divStoreList">
							<!--                         <div class="card-body" id=""divStoreList"" style="display:none;"> -->
							<div class="table-responsive">
								<table id="dataTable" class="table table-bordered" style="font-size: 14px;" width="100%" cellspacing="0">
									<thead>
										<tr style="text-align: center;">
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
									<!--                                             <th>加值機數量</th>
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
													<button id="btnSid${deviceMachineTotal.sid}" type="button" class="btn btn-warning modify">設備配置</button>
												</th>
										</c:forEach>
									</tbody>

								</table>
							</div>
						</div>

						<input type="hidden" name="sid" value="0" id="inputSid">

						<!-- Store addvalue setting -->
						
						<!--                         <div class="container" id="divAddvalueSetting"> -->
						<div class="container" id="divAddvalueSetting"
							style="display: none;">
							<input type="hidden" name="addvalueQty" value="0" id="inputAddvalueQty">
							<div class="row">
								<div style="width: 100%;">
									<!--                             				<h4 style="float:left; margin-top: 12px;">樹林站前店加值機</h4> -->

									<button id="btnAddAddvalue" class="btn btn-success" data-toggle="modal"
													data-target="#addvalueModal" style="float: left; margin: 6px 0 6px 0;">
										<i class="fa fa-plus"></i>新增加值機
									</button>
									<button id="btnUpdateAddvalue" class="btn btn-info"
													style="float: left; margin: 6px 0 6px 0; display: none;">
										<i class="fa fa-edit"></i>修改參數
									</button>
								</div>

								<!-- addvalue table 1 start -->
								<input type="hidden" name="did1" value="0" id="inputDid1">
								<div id="divDataTableAddvalue1" class="table-responsive" style="display: none;">
									<table id="dataTableAddvalue1" class="table" style="background: skyblue;">
										<thead>
											<tr style="text-align: center;">
												<td colspan="4"><input type="text"
													class="form-control configInput" id="inputAddName1"
													value="樹林站前加值機"
													style="font-size: 18px; color: orange; font-weight: 900;">
												</td>
											</tr>
											<tr style="text-align: center;">
												<th>#</th>
												<th>儲值金額</th>
												<th>兌換點數</th>
											</tr>
										</thead>
										<tbody>
											<tr style="text-align: center;">
												<th style="padding: 1rem;">優惠方案一</th>
												<th><input type="text" class="form-control configInput"
													id="inputAdd1_discount1_money" value="0"></th>
												<th><input type="text" class="form-control configInput"
													id="inputAdd1_discount1_point" value="0"></th>
											</tr>
											<tr style="text-align: center;">
												<th style="padding: 1rem;">優惠方案二</th>
												<th><input type="text" class="form-control configInput"
													id="inputAdd1_discount2_money" value="0"></th>
												<th><input type="text" class="form-control configInput"
													id="inputAdd1_discount2_point" value="0"></th>
											</tr>
											<tr style="text-align: center;">
												<th style="padding: 1rem;">優惠方案三</th>
												<th><input type="text" class="form-control configInput" id="inputAdd1_discount3_money" value="0"></th>
												<th><input type="text" class="form-control configInput"
													id="inputAdd1_discount3_point" value="0"></th>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- addvalue table 1 end -->


								<!-- addvalue table 2 start -->
								<input type="hidden" name="did2" value="0" id="inputDid2">
								<div id="divDataTableAddvalue2" class="table-responsive" style="display: none;">
									<table id="dataTableAddvalue2" class="table" style="background:#afeeee;";>
										<thead>
											<tr style="text-align: center;">
												<td colspan="4"><input type="text"
													class="form-control configInput" id="inputAddName2"
													value="桃園迴龍加值機"
													style="font-size: 18px; color: orange; font-weight: 900;">
												</td>
											</tr>
											<tr style="text-align: center;">
												<th>#</th>
												<th>儲值金額</th>
												<th>兌換點數</th>
											</tr>
										</thead>
										<tbody>
											<tr style="text-align: center;">
												<th style="padding: 1rem;">優惠方案一</th>
												<th><input type="text" class="form-control configInput"
													id="inputAdd2_discount1_money" value="0"></th>
												<th><input type="text" class="form-control configInput"
													id="inputAdd2_discount1_point" value="0"></th>
											</tr>
											<tr style="text-align: center;">
												<th style="padding: 1rem;">優惠方案二</th>
												<th><input type="text" class="form-control configInput"
													id="inputAdd2_discount2_money" value="0"></th>
												<th><input type="text" class="form-control configInput"
													id="inputAdd2_discount2_point" value="0"></th>
											</tr>
											<tr style="text-align: center;">
												<th style="padding: 1rem;">優惠方案三</th>
												<th><input type="text" class="form-control configInput"
													id="inputAdd2_discount3_money" value="0"></th>
												<th><input type="text" class="form-control configInput"
													id="inputAdd2_discount3_point" value="0"></th>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- addvalue table 2 end -->




							</div>
						</div>

						<!-- Store constumtion setting -->
						<!--                         <div class="container" id="divConsumptionSetting"> -->
						<div class="container" id="divConsumptionSetting" style="display: none;">
							<div style="width: 100%;">
								<!--				<h4 style="float:left; margin-top: 12px;">樹林站前店消費機</h4> -->
								<button id="btnAddConsumption" class="btn btn-success" data-toggle="modal"
												data-target="#consumptionModal" style="float: left; margin: 6px 0 6px 0;">
									<i class="fa fa-plus"></i>新增消費機
								</button>
								<button id="btnUpdateConsumption" class="btn btn-info" style="float: left; margin: 6px 0 6px 8px; display: none;">
									<i class="fa fa-edit"></i>修改參數
								</button>
							</div>

							<table id="dataTableConsumption" class="table" style="font-size: 14px; margin-top: 16px;" width="100%;" cellspacing="0">
								<thead>
									<tr style="text-align: center;">
										<th>#</th>
										<th>名稱</th>
										<th>服務類型</th>
										<th>消費基礎點數</th>
										<!--                                             <th>設備ID</th> -->
										<!--                                             <th>狀態</th> -->
										<!--                                         		<th></th> -->
									</tr>
								</thead>
								<!--                                     <tfoot> -->
								<!-- 																						<th>#</th> -->
								<!-- 																						<th>名稱</th> -->
								<!--                                            	<th>免費服務次數</th> -->
								<!-- 																						<th>設備ID</th> -->
								<!-- 																						<th>狀態</th> -->
								<!--                                         		<th></th> -->

								<!--                                     </tfoot> -->
								<tbody>
								<input type='hidden' value="" id="inputIndex" />
								<c:forEach var="count"  varStatus="id" begin ="1" end="200" step="1">
									<tr style="text-align: center; display:none;" id="trCon${count}">
										<th style="padding-top: 1.5rem;"> ${count}
											<input type='hidden' value="" id="inputMid${count}" />
										</th>
										<th style="padding-top: 1rem;">
											<input type="text" class="form-control configInput" id="inputConName${count}" value="${count}"  style="width: 240px;">
										</th>
										<th style="">
											<div class="btn-group" style="">
												<button id="btnConType${count}" type="button" class="btn btn-info dropdown-toggle btn-type" data-toggle="dropdown"
																aria-haspopup="true" aria-expanded="false" style="">服務類型</button>
												<div class="dropdown-menu type-menu">
													<a class="dropdown-item" href="#">連續性消費</a>
													<a class="dropdown-item" href="#">一次性消費</a>
												</div>
											</div>
										</th>
										<th style="padding-top: 1rem;">
											<input type="text" class="form-control configInput" id="inputConPoint${count}" value="10" style="">
										</th>
									</tr>
								</c:forEach>
								

									
								</tbody>
							</table>
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
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Add Stroe Modal-->
	<div id="StoreNameModal" class="modal fade"tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">新增店家 - 設定名稱</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="recipient-name" class="col-form-label">名稱:</label>
							<input type="text" class="form-control" id="inputAddStoreName">
					</div>
<!-- 					<div id="" style="color: red;">新增成功!<br>視窗關閉後請進行設定</div> -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
					<button id="btnAddStoreOK" type="button" class="btn btn-primary">新增店家</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Add Addvalue Modal-->
	<div  class="modal fade" id="addvalueModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">新增加值機 - 設定名稱</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="recipient-name" class="col-form-label">名稱:</label>
							<input type="text" class="form-control" id="inputAddDeviceName">
					</div>
<!-- 					<div id="" style="color: red;">新增成功!<br>視窗關閉後請進行設定</div> -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
					<button id="btnAddDeviceOK" type="button" class="btn btn-primary">新增加值機</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Consumption Modal-->
	<div class="modal fade" id="consumptionModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">新增消費機 - 設定名稱</h5>
					<button type="button" class="close" data-dismiss="modal" 	aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="recipient-name" class="col-form-label">名稱:</label>
							<input type="text" class="form-control" id="inputAddMachineName">
					</div>
<!-- 					<div id="" style="color: red;">新增成功!<br>視窗關閉後請進行設定</div> -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
					<button id="btnAddMachineOK" type="button" class="btn btn-primary">新增消費機</button>
				</div>
			</div>
		</div>
	</div>



	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">True</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
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
			var table = $('#dataTable').DataTable();

			$('#dataTable tbody').on('click', 'tr', function() {
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
			"language" : {
				"lengthMenu" : "每頁 _MENU_ 筆",
				"search" : "關鍵字搜尋:",
				"info" : "顯示 _START_ 到 _END_ 共 _TOTAL_ 筆",
				"paginate" : {
					"first" : "第一頁",
					"last" : "最後一頁",
					"next" : "下一頁",
					"previous" : "上一頁"
				}
			}
		});



		function setAddValue(size, jsonObject) {
			if (size >= 1) {
				$("#inputDid1").val(jsonObject.did_1);
				$("#inputAddName1").val(jsonObject.deviceName_1);
				console.log("jsonObject.deviceName_1 :" + jsonObject.deviceName_1);
				$("#inputAdd1_discount1_money").val(
						jsonObject.discount_1_money);
				$("#inputAdd1_discount1_point").val(
						jsonObject.discount_1_point);
				$("#inputAdd1_discount2_money").val(
						jsonObject.discount_2_money);
				$("#inputAdd1_discount2_point").val(
						jsonObject.discount_2_point);
				$("#inputAdd1_discount3_money").val(
						jsonObject.discount_3_money);
				$("#inputAdd1_discount3_point").val(
						jsonObject.discount_3_point);
			}

			if (size >= 2) {
				$("#inputDid2").val(jsonObject.did_2);
				$("#inputAddName2").val(jsonObject.deviceName_2);
				console.log("jsonObject.deviceName_2 :"
						+ jsonObject.deviceName_2);
				$("#inputAdd2_discount1_money").val(
						jsonObject.discount_1_money);
				$("#inputAdd2_discount1_point").val(
						jsonObject.discount_1_point);
				$("#inputAdd2_discount2_money").val(
						jsonObject.discount_2_money);
				$("#inputAdd2_discount2_point").val(
						jsonObject.discount_2_point);
				$("#inputAdd2_discount3_money").val(
						jsonObject.discount_3_money);
				$("#inputAdd2_discount3_point").val(
						jsonObject.discount_3_point);
			}

		}

		function getAddObject(sid) {
			// 				var addtable1 = $('#dataTableAddvalue1').DataTable({
			// 															searching: false, 
			// 															paging: false, 
			// 															info: false, 
			// 															"language": {
			// 															    "emptyTable" : "無資料"
			// 															  }
			// 				});
			// 				addtable1.clear().draw();
			// 				var addtable2 = $('#dataTableAddvalue2').DataTable();
			// 				addtable2.clear().draw();

			

			

			$.ajax({
				type : 'POST', //GET or POST
				url : "../GetDevicesBySidServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : {
					sid : sid
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					console.log("jsonObject : " + jsonObject);
					var size = jsonObject.size;
					console.log("size : " + size);
					$("#title").text(jsonObject.storename + " - 設定加值機");
					switch (size) {
					case 0:
						$("#btnAddAddvalue").show();
						$("#btnUpdateAddvalue").hide();
						break;
					case 1:
						$("#btnAddAddvalue").hide();
						$("#btnUpdateAddvalue").show();
						$("#divDataTableAddvalue1").show();
						setAddValue(size, jsonObject);
						break;
					case 2:
						$("#btnAddAddvalue").hide();
						$("#btnUpdateAddvalue").show();
						$("#divDataTableAddvalue1").show();
						$("#divDataTableAddvalue2").show();
						setAddValue(size, jsonObject);
						break;
					default:
						break;
					}

					// 						var statisticsAllVOs = jsonObject["statisticsAllVOs"];
					// 						console.log("statisticsAllVOs:" + statisticsAllVOs);
					// 						var count = statisticsAllVOs.length;
					// 						for(var i = 0; i < count; i++){
					// 							var statistics = statisticsAllVOs[i];
					// 							var rowNode = table.row.add( [ i+1, statistics.name, statistics.add_money, statistics.add_point, statistics.consumption_point ] ).draw().node();
					// 							$( rowNode ).css('text-align','center');
					// 						}

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

		function getConsumptionObject(sid) {
			for(var i = 1; i <= 200; i++){
				$("#trCon"+ i).hide();
			}
			
			console.log("getConsumptionObject sid : " + sid);
			$.ajax({
				type : 'POST', //GET or POST
				url : "../GetConsumptionServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : {
					sid : sid
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					console.log("jsonObject : " + jsonObject);
					var size = jsonObject.size;
					console.log("size : " + size);

					$("#title").text(jsonObject.storename + " - 設定消費機");

					if(size > 0){
						$("#btnUpdateConsumption").show();
					}

					var machineVOs = jsonObject["machineVOs"];
					console.log("machineVOs:" + machineVOs);
					var count = machineVOs.length;
					console.log("count:" + count);


					for(var i = 0; i < count; i++){
						var machine = machineVOs[i];
						var tmpCount = i + 1;
						$("#trCon"+tmpCount).show();
						console.log("machine name : " + machine.name);
						console.log("machine point : " + machine.point);
						$("#inputConName"+ tmpCount).val(machine.name);
						$("#inputMid"+ tmpCount).val(machine.machid);
						var type = machine.type;
						
						console.log("machine single_point : " + machine.single_point);
						console.log("machine multi_point : " + machine.multi_point);
						if(type == "一次性消費"){
							console.log("machine type : " + machine.type);
							//$("#inputConPoint" + tmpCount).val(machine.single_point);
							$("#btnConType" + tmpCount).text("一次性消費");
						}else if(type == "連續性消費"){
							console.log("machine type : " + machine.type);
							//$("#inputConPoint" + tmpCount).val(machine.multi_point);
							$("#btnConType" + tmpCount).text("連續性消費");
						}
						$("#inputConPoint" + tmpCount).val(machine.point);
						
						
						

						
							
						
						
					}
// 					switch (size) {
// 					case 0:
// 						$("#btnAddAddvalue").show();
// 						break;
// 					case 1:
// 						$("#btnAddAddvalue").hide();
// 						$("#btnUpdateAddvalue").show();
// 						$("#divDataTableAddvalue1").show();
// 						setAddValue(size, jsonObject);
// 						break;
// 					case 2:
// 						$("#btnAddAddvalue").hide();
// 						$("#btnUpdateAddvalue").show();
// 						$("#divDataTableAddvalue1").show();
// 						$("#divDataTableAddvalue2").show();
// 						setAddValue(size, jsonObject);
// 						break;
// 					default:
// 						break;
// 					}
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


		$(".type-menu a").click(function(){
				console.log(this.text);
				console.log(this.id);
			var idStr = this.id;
// 			$("#btnSelectStore").text( this.text );
// 			$("#inputSid").val( idStr.substring(1, idStr.length) );
// 			var sid = $("#inputSid").val();
// 			console.log( sid );

	});


		$("#btnAddStoreOK").click(function(){
			var storename = $("#inputAddStoreName").val();
			$.ajax({
				type : 'POST', //GET or POST
				url : "../AddStoreServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : {
					storename : storename
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					var state = jsonObject.state;
					console.log("state : " + state);					
					if(state == 1){
						location.reload();
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


			
// 			var table = document.getElementById("dataTable");
// 			var count = table.tBodies[0].rows.length;
// 			count = count + 1;
// 			var storeName = "板橋站前店2";

//  			var datatable = $('#dataTable').DataTable();
// 			var rowNode = datatable.row.add([
// 				"<td scope='row' style='text-align: center;''>" + count + "</td>",
// 				storeName,
// 				0,
// 				0, 
// 				"<button id='btnSid" + 100 + "' type='button' class='btn btn-warning modify'>修改</button>"]).draw().node();
// $('td:eq(4)', rowNode).attr('id', "testId100");

// 			$('#modal').modal('toggle');


			



			

			
			
			//$( rowNode ) .css( 'color', 'red' );

			
// 			<c:forEach items="${deviceMachineTotals}" var="deviceMachineTotal" varStatus="id">
// 			<tr style="text-align: center;" id="${deviceMachineTotal.sid}">
// 				<th scope="row" style="text-align: center;">${id.index + 1}</th>
// 				<th>${deviceMachineTotal.storeName}</th>
// 				<th>${deviceMachineTotal.deviceCount}</th>
// 				<th>${deviceMachineTotal.machineCount}</th>
// 				<th style="padding: 3px 0 0 0;">
// 					<button id="btnSid${deviceMachineTotal.sid}" type="button" class="btn btn-warning modify">修改</button>
// 				</th>
// 		</c:forEach>
		});

		$(".modify").click(function() {
			
			$("#divStoreList").hide();
			$("#divDataTableAddvalue1").hide();
			$("#divDataTableAddvalue2").hide();
			$("#divAddvalueSetting").show();
			$("#btnAddStore").hide();
			$("#btnBackList").show();
			$("#btnNextConsumption").show();
			console.log("modify id " + this.id);
			$("#btnUpdateAddvalue").hide();
			var sidStr = this.id;
			var sid = sidStr.substring(6, sidStr.length);
			$("#inputSid").val(sid);
			getAddObject(sid);
		});

		$("#btnNextConsumption").click(function() {
			
			$("#divAddvalueSetting").hide();
			$("#divConsumptionSetting").show();

			$("#btnBackAddvalue").show();
			$("#btnNextConsumption").hide();

			var sid = $("#inputSid").val();
			
			getConsumptionObject(sid);
			//$("#btnUpdtateConsumption").show();
			//$("#btnUpdateAddvalue").hide();
		});

		$("#btnUpdateAddvalue").click(function() {
			var sid = $("#inputSid").val();
			console.log("sid : " + sid);
			var addvalueCountStr = $("#storeAddvalueCount"+sid).text();
			console.log("addvalueCountStr : " + addvalueCountStr);
			var addvalueCount  = parseInt(addvalueCountStr, 10);
			console.log("addvalueCount : " + addvalueCount);

			var deviceArray = [];

			if(addvalueCount >= 1){
				console.log("Step : " + 1);
				var did = $('#inputDid1').val();
				var devicename = $('#inputAddName1').val();
				var discount1_money = $('#inputAdd1_discount1_money').val(  );
				var discount1_point = $('#inputAdd1_discount1_point').val(  );
				var discount2_money = $('#inputAdd1_discount2_money').val(  );
				var discount2_point = $('#inputAdd1_discount2_point').val(  );
				var discount3_money = $('#inputAdd1_discount3_money').val(  );
				var discount3_point = $('#inputAdd1_discount3_point').val(  );
				
				var deviceObj = {
						"did" : did,
						"devicename" : devicename,
						"discount1_money" : discount1_money,
						"discount1_point" : discount1_point,
						"discount2_money" : discount2_money,
						"discount2_point" : discount2_point,
						"discount3_money" : discount3_money,
						"discount3_point" : discount3_point
					};
				JSON.stringify(deviceObj);
				console.log("deviceObj : " + deviceObj);
				deviceArray.push(deviceObj);
				console.log("deviceArray : " + deviceArray);
			}

			if(addvalueCount >= 2){
				console.log("Step : " + 2);
				var did = $('#inputDid2').val();
				var devicename = $('#inputAddName2').val();
				var discount1_money = $('#inputAdd2_discount1_money').val(  );
				var discount1_point = $('#inputAdd2_discount1_point').val(  );
				var discount2_money = $('#inputAdd2_discount2_money').val(  );
				var discount2_point = $('#inputAdd2_discount2_point').val(  );
				var discount3_money = $('#inputAdd2_discount3_money').val(  );
				var discount3_point = $('#inputAdd2_discount3_point').val(  );

				var deviceObj = {
						"did" : did,
						"devicename" : devicename,
						"discount1_money" : discount1_money,
						"discount1_point" : discount1_point,
						"discount2_money" : discount2_money,
						"discount2_point" : discount2_point,
						"discount3_money" : discount3_money,
						"discount3_point" : discount3_point
					};
					JSON.stringify(deviceObj);
					deviceArray.push(deviceObj);
			}

			console.log("deviceArray : " + deviceArray);
			console.log("JSON.stringify(deviceArray); : " + JSON.stringify(deviceArray));
			var deviceList = JSON.stringify(deviceArray);

			$.ajax({
				type : 'POST', //GET or POST
				url : "../UpdateDeviceServlet", //請求的頁面
				dataType: 'json',
				cache : false, //防止抓到快取的回應
				data : {
					sid : sid,
					"deviceList" : deviceList
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					console.log("jsonObject : " + jsonObject);
					alert("參數已更新!");
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
		});

		$("#btnBackList").click(function() {
			$("#title").text("店家配置");
			$("#divAddvalueSetting").hide();
			$("#divConsumptionSetting").hide();
			$("#btnUpdateAddvalue").hide();
			$("#btnUpdateConsumption").hide();
			$("#divStoreList").show();
			$("#divDataTableAddvalue2").hide();
			$("#btnAddStore").show();
			$("#btnNextConsumption").hide();
			$("#btnBackAddvalue").hide();
			$("#btnBackList").hide();
			
		});

		$("#btnUpdateConsumption").click(function() {
			var sid = $("#inputSid").val();
			var consumptionCountStr = $("#storeConsumptionCount"+sid).text();
			console.log("consumptionCountStr : " + consumptionCountStr);
			var consumptionCount  = parseInt(consumptionCountStr, 10);
			console.log("consumptionCount : " + consumptionCount);

			var machineArray = [];

			for(var i = 1; i <= consumptionCount; i++){
				var mid = $("#inputMid" + i).val();
				var name = $("#inputConName" + i).val();
				var type = $("#btnConType" + i).text();
				var point = $("#inputConPoint" + i).val();
				console.log( mid + " : " + name  + " : " + type + " ; " + point);

				var machineObj = {
						"mid" : mid,
						"name" : name,
						"type" : type,
						"point" : point
					};

				JSON.stringify(machineObj);
				console.log("machineObj : " + machineObj);
				machineArray.push(machineObj);
				console.log("machineArray : " + machineArray);

			}

			console.log("machineArray : " + machineArray);
			console.log("JSON.stringify(machineArray); : " + JSON.stringify(machineArray));
			var machineList = JSON.stringify(machineArray);

			$.ajax({
				type : 'POST', //GET or POST
				url : "../UpdateMachineServlet", //請求的頁面
				dataType: 'json',
				cache : false, //防止抓到快取的回應
				data : {
					"machineList" : machineList
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					
					console.log("jsonObject : " + jsonObject);
					var state = jsonObject.state;
					if(state == 1){
						
						alert("參數已更新!");
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


			

			
		});

		$("#btnBackAddvalue").click(function() {
			$("#title").text("樹林站前店 - 設定加值機");
			$("#divConsumptionSetting").hide();
			$("#divAddvalueSetting").show();

			$("#btnBackAddvalue").hide();
			$("#btnUpdtateConsumption").hide();
			$("#btnNextConsumption").show();

			//$("#btnBackAddvalue").hide();				
			//$("#btnUpdateAddvalue").show();
		});

		// Insert Device Object
		$("#btnAddDeviceOK").click(function() {

			var devicename = $("#inputAddDeviceName").val();
			var sid = $("#inputSid").val();
			
			$.ajax({
				type : 'POST', //GET or POST
				url : "../AddDeviceServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : {
					devicename : devicename,
					sid : sid
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					var state = jsonObject.state;
					console.log("state : " + state);	

					if(state == 1){
						$("#btnAddAddvalue").hide();
						$('#addvalueModal').modal('toggle');
						var addvalueCountStr = $("#storeAddvalueCount"+sid).text();
						console.log("addvalueCountStr : " + addvalueCountStr);
						var addvalueCount  = parseInt(addvalueCountStr, 10);
						if(addvalueCount == 0){
							$("#storeAddvalueCount"+sid).text(addvalueCount + 1);
							var index = addvalueCount + 1;
							$('#inputDid1').val(jsonObject.did);
							$('#inputAddName1').val(jsonObject.location);
							$('#inputAdd1_discount1_money').val( jsonObject.discount_1_money );
							$('#inputAdd1_discount1_point').val( jsonObject.discount_1_point );
							$('#inputAdd1_discount2_money').val( jsonObject.discount_2_money );
							$('#inputAdd1_discount2_point').val( jsonObject.discount_2_point );
							$('#inputAdd1_discount3_money').val( jsonObject.discount_3_money );
							$('#inputAdd1_discount3_point').val(jsonObject.discount_3_point  );

							$("#divDataTableAddvalue1").show();
							$("#btnUpdateAddvalue").show();
							
						}
						else if(addvalueCount == 1){

						}

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

			

// 			var sid = 1;

// 			var state = 1;

// 			if(state == 1){
// 				$("#addvalueModal").modal('toggle');
// 				$("#btnAddAddvalue").hide();
// 				$("#btnUpdateAddvalue").show();
// 				$("#dataTableAddvalue1").show();
// 			}
			
			
		});



		// Insert Machine Object
		$("#btnAddMachineOK").click(function() {

			var machinename = $("#inputAddMachineName").val();
			var sid = $("#inputSid").val();
			var did = $("#inputDid1").val();
			console.log("machinename", machinename);
			console.log("sid", sid);
			
			$.ajax({
				type : 'POST', //GET or POST
				url : "../AddMachineServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : {
					machinename : machinename,
					sid : sid,
					did : did
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					var state = jsonObject.state;
					console.log("state : " + state);	

					if(state == 1){
						$('#consumptionModal').modal('toggle');
						var consumptionCountStr = $("#storeConsumptionCount"+sid).text();
						console.log("consumptionCountStr : " + consumptionCountStr);
						var consumptionCount  = parseInt(consumptionCountStr, 10);
						consumptionCount = consumptionCount + 1;
						console.log("consumptionCount : " + consumptionCount);
						$("#trCon"+consumptionCount).show();
						$("#inputIndex").val(consumptionCount);
						$('#inputConName'+consumptionCount).val(machinename);
						
						$("#storeConsumptionCount"+sid).text(consumptionCount);
						var  machid = jsonObject.machid;
						$("#inputMid" + consumptionCount).val( machid );
						$('#btnUpdateConsumption').show();
						console.log("btnUpdateConsumption show !" );
						
// 						$("#addvalueModal").modal('toggle');
// 						$("#btnAddAddvalue").hide();
// 						$("#btnUpdateAddvalue").show();
// 						$("#dataTableAddvalue1").show();
					}else if(state == 2){
						$('#consumptionModal').modal('toggle');
						alert("已超過店家最多30台限制，無法新增！");
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




			
			
		});

		$("#btnAddConsumption").click(function() {
			//$("#btnUpdateConsumption").show();
		});

		$(".btn-type").click(function(){
			console.log(this.id);
			var idStr = this.id;
			var id = idStr.substring(10, idStr.length);
			$("#inputIndex").val(id);
			console.log("id : " + id);
		});


		$(".type-menu a").click(function(){
			var index = $("#inputIndex").val();
			//alert(this.text);
			$("#btnConType"+index).text( this.text );
//				console.log(this.text);
//				console.log(this.id);
// 			var idStr = this.id;
// 			$("#btnSelectStore").text( this.text );
// 			$("#inputSid").val( idStr.substring(1, idStr.length) );
// 			var sid = $("#inputSid").val();
// 			console.log( sid );

	});


		

		//第1個參數:store id
		//第2個參數:type(傳要改變的欄位名稱))
		//第2個參數:總共要傳幾個有效參數
		//第3個以後的參數:依第1個參數設定相同數量的參數值,超過的部份以""替代
		function updateStore(coulumName, arg1, arg2) {
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

		$('#cbStoreState').change(function() {
			var pause = document.getElementById('cbStoreState').checked;

			if (pause == true) {
				console.log("pause", pause);
				updateStore("pause", 1, "");
			} else if (pause == false) {
				console.log("pause", pause);
				updateStore("pause", 0, "");
			}
			//updateStore("pause", state, "");
		});
	</script>
</body>

</html>