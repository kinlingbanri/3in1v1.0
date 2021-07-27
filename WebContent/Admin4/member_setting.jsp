<%@page import="com.machine.model.MachineService"%>
<%@page import="com.machine.model.MachineVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.store.model.DeviceMachineTotal"%>
<%@page import="java.util.Map"%>
<%@page import="com.device.model.DeviceService"%>
<%@page import="com.device.model.DeviceVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.store.model.StoreVO"%>
<%@page import="com.store.model.StoreService"%>

<%@page import="com.mem.model.MemVO"%>
<%@page import="java.util.List"%>
<%@page import="com.mem.model.MemService"%>
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

	MemService memSvc = new MemService();
	List<MemVO> memberVOs = memSvc.getAll();

	for (MemVO mem : memberVOs) {
		System.out.print(mem.getUsername() + ",");
		System.out.print(mem.getEmail() + ",");
		System.out.print(mem.getPassword() + ",");
		System.out.print(mem.getPoint());
		System.out.print(mem.getBlack() + ",");
		System.out.print(mem.getAuthority() + ",");
		System.out.print(mem.getVerification() + ",");
		System.out.print(mem.getVerificationcode() + ",");
		System.out.print(mem.getVerificationdate() + ",");
		System.out.print(mem.getPhone());
		System.out.println();
	}

	request.setAttribute("memberVOs", memberVOs);

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

<!-- loader -->
<link href="./css/loader.css" rel="stylesheet">
    
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
					<!-- 								<div class="container-fluid"  id="divMemberList" style="display:none;"> -->

					<!-- Page Heading -->
					<!--                     <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
					<!--                     <p class="mb-4">點擊該列任一處，即可進入該店家的設定畫面 -->


					<div class="card shadow mb-4">
						<div class="card-header py-3" style="padding: 0 12px 0 12px;">
							<h4 id="title" class="m-0 font-weight-bold text-primary"
								style="float: left; padding: 6px 0 0 0;">會員清單</h4>
								<p id="subTitle" style="color:orange; margin: 6px 0 0 100px;">(如預修改員會資料，請點選該會員任一欄位)</p>
							<button id="btnBackList" type="button" class="btn btn-warning"
								style="margin-right: 12px;; float: right; display: none;">返回會員清單</button>
						</div>

						<!-- Store List -->
						<div class="card-body" id="divMemberList">
<!-- 						<div class="card-body" id=""divMemberList"" style="display:none;"> -->
							<div class="table-responsive">
								<table id="dataTable" class="table table-bordered" style="font-size: 14px;" width="100%" cellspacing="0">
									<thead>
										<tr style="text-align: center;">
											<th>#</th>
											<th>帳號</th>
											<th>手機號碼</th>
											<th>Email</th>
											<th>點數</th>
											<th>密碼</th>
<!-- 											<th>狀態</th> -->
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${memberVOs}" var="member" varStatus="id">
											<tr style="text-align: center;" id="${id.index + 1}">
												<th scope="row" style="text-align: center;">${id.index + 1}</th>
												<th id="us${id.index + 1}">${member.username}</th>
												<th id="ph${id.index + 1}">${member.phone}</th>
												<th id="em${id.index + 1}">${member.email}</th>
												<th id="po${id.index + 1}">${member.point}</th>
												<th id="pa${id.index + 1}">${member.password}</th>
<%-- 												<c:choose> --%>
<%-- 												   <c:when test="${member.black == 0}"> --%>
<%-- 												   		<th id="bl${id.index + 1}">啟用</th> --%>
<%-- 												   </c:when> --%>
<%-- 												   <c:otherwise> --%>
<%-- 												   		<th id="bl${id.index + 1}">停用</th> --%>
<%-- 												   </c:otherwise>												  		 --%>
<%-- 												</c:choose> --%>
										</c:forEach>
									</tbody>

								</table>
							</div>
						</div>
						

						<input type="hidden" name="sid" value="0" id="inputIndex">
						<!-- member setting -->
						<div class="container" id="divMemberSetting" style="display:none;">
							<div class="row">
							
								<div style="width: 100%;">
									<button id="btnUpdateMeber" class="btn btn-info" style="float:left; margin:6px 0 6px 0;">
										<i class="fa fa-edit">修改參數</i>
									</button>
								</div>

								<!-- addvalue table 1 start -->
								<div id="divDataTableAMember" class="table-responsive">
									<table id="dataTableMember" class="table" style="background: skyblue;";>
										<thead>
											<tr style="text-align: center;">
												<td colspan="4"><h3 style="margin-bottom: 0px; color:#FFD700; font-weight:550;" id="textUsername">金城舞</h3></td>
											</tr>
											<tr style="text-align: center;">
												<th>#</th>
												<th>原先參數</th>
												<th>預設定參數</th>
											</tr>
										</thead>
										<tbody>
											<tr style="text-align: center;">
												<th style="padding: 1rem;">手機號碼</th>
												<th id="textPhone" style="color:#EEE; padding-top: 1rem; font-weight:600;"></th>
												<th><input id="inputPhone" type="text" class="form-control configInput" style="background-color:#EEE;"></th>
												
											</tr>
											<tr style="text-align: center;">
												<th style="padding: 1rem;">Email</th>
												<th id="textEmail" style="color:#EEE; padding-top: 1rem; font-weight:600;"></th>
												<th><input id="inputEmail" type="text" class="form-control configInput"></th>
											</tr>
											<tr style="text-align: center;">
												<th style="padding: 1rem;">點數</th>
												<th id="textPoint" style="color:#EEE; padding-top: 1rem; font-weight:600;"></th>
												<th><input id="inputPoint" type="text" class="form-control configInput"></th>
											</tr>
											<tr style="text-align: center;">
												<th style="padding: 1rem;">密碼</th>
												<th id="textPassword" style="color:#EEE; padding-top: 1rem; font-weight:600;"></th>
												<th><input id="inputPassword" type="text" class="form-control configInput"></th>
											</tr>
<!-- 											<tr style="text-align: center;"> -->
<!-- 												<th style="padding: 1rem;">狀態</th> -->
<!-- 												<th id="textBlack" style="color:#EEE; padding-top: 1rem; font-weight:600;"></th> -->
<!-- 												<th>												 -->
<!-- 													<div class="btn-group" style=""> -->
<!-- 														<button id="btnBlack" type="button" class="btn btn-info dropdown-toggle btn-type" data-toggle="dropdown" -->
<!-- 																		aria-haspopup="true" aria-expanded="false">停用</button> -->
<!-- 														<div class="dropdown-menu type-menu"> -->
<!-- 															<a class="dropdown-item" href="#">啟用</a> -->
<!-- 															<a class="dropdown-item" href="#">停用</a> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</th> -->
<!-- 											</tr> -->
										</tbody>
									</table>
								</div>
								<!-- addvalue table 1 end -->
								
								<div class="table-responsive" style="margin-top:12px;">
									<table id="recordTable"  class="table table-bordered" >
										<thead>
											<tr style="text-align: center;">
												<td colspan="7" style="font-size:1.5rem;">交易記錄</td>
											</tr>
									    <tr style="text-align: center;">
									      <th scope="col" style="width:12%;">#</th>
									      <th scope="col" style="width:20%;">日期</th>
									      <th scope="col" style="width:16%;">店名</th>
									      <th scope="col" style="width:13%;">設備名稱</th>
									      <th scope="col" style="width:13%;">儲值金額</th>
									      <th scope="col" style="width:13%;">加值點數</th>
									      <th scope="col" style="width:13%;">消費點數</th>									      
											</tr>
									  </thead>
									  <tbody>
										</tbody>
									</table>
								</div>

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
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>


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

	<!-- Page level custom scripts -->
	<script src="js/demo/datatables-demo.js"></script>

	<!-- Bootstrap Switch Button bootstrap4-toggle@3.6.1 -->
	<script src="./js/bootstrap4-toggle.min.js"></script>

	<script>
	var table;
	var recordTable;
	
		$(document).ready(function() {
			table = $('#dataTable').DataTable();
			recordTable = $('#recordTable').DataTable();

			$('#dataTable tbody').on('click', 'tr', function() {
				document.getElementById("loader").style.display = "block";
				var id = $(this).attr('id');
		    $("#inputIndex").val(id);
		    var username = $("#us" + id).text();
				var phone = $("#ph" + id).text();
				var email = $("#em" + id).text();
				var point = $("#po" + id).text();
				var password = $("#pa" + id).text();
				var black = $("#bl" + id).text();

				$("#textUsername").text( username );
				$("#textPhone").text( phone );
				$("#textEmail").text( email );
				$("#textPoint").text( point );
				$("#textPassword").text( password );
				$("#textBlack").text( black );
				$("#inputPhone").val( phone );
				$("#inputEmail").val( email );
				$("#inputPoint").val( point );
				$("#inputPassword").val( password );
				$("#btnBlack").text( black );

				$("#title").text("會員設定");				
				document.getElementById("divMemberList").style.display = "none";
				document.getElementById("subTitle").style.display = "none";				
				document.getElementById("divMemberSetting").style.display = "block";
				document.getElementById("btnBackList").style.display = "block";



				$.ajax({
					type : 'POST', //GET or POST
					url : "../RecordServlet", //請求的頁面
					dataType : 'json',
					cache : false, //防止抓到快取的回應
					data : {
						username : username
					},
					success : function(jsonObject) { //當請求成功後此事件會被呼叫
						console.log("jsonObject : " + jsonObject);
						var state = jsonObject.state;

						if(state == 1){

							var recordVOs = jsonObject["recordVOs"];
	 						console.log("recordVOs:" + recordVOs);

	 						var count = recordVOs.length;
	 						console.log("count:" + count);
							
	 						for(var i = 0; i < count; i++){
	 								var recordVO = recordVOs[i];
	 								var time = recordVO.recordDate;
									var storename = recordVO.storeName;
									var name = recordVO.name;
									var money = recordVO.money;
									var point = recordVO.point;
									var type = recordVO.type;

									if(type == 1){
										var rowNode = recordTable.row.add( [ 
																					i+1, time, storename, name, money, point, '-' 
																			] ).draw().node();
									}else if(type ==2 ){
										var rowNode = recordTable.row.add( [ 
																					i+1, time, storename, name, '-', '-', point 
																			] ).draw().node();
									}

									$( rowNode ).css('text-align','center');
									
	 							}

	 						document.getElementById("loader").style.display = "none";
							
						}else if(state == 2){
							console.log("Update Error!");
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
		});

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

		$('#recordTable').dataTable({
			"lengthChange": false,
			"paginate" : false,
			"info" : false,
			"searching" : false,
			"language": {
			      "emptyTable": "無資料"
			    }
		});

		$(".type-menu a").click(function(){
			var str = this.text;
			$("#btnBlack").text( this.text );
		});

		$("#btnUpdateMeber").click(function(){
			  var username = $("#textUsername").text();
				var phone = $("#inputPhone").val();
				var email = $("#inputEmail").val();
				var point = $("#inputPoint").val();
				var password = $("#inputPassword").val();
				var black = $("#btnBlack").text();
				
				$.ajax({
					type : 'POST', //GET or POST
					url : "../UpdateMemberSevlet", //請求的頁面
					dataType : 'json',
					cache : false, //防止抓到快取的回應
					data : {
						username : username,
						phone : phone,
						email : email,
						point : point,
						password : password,
						black : black
					},
					success : function(jsonObject) { //當請求成功後此事件會被呼叫
						console.log("jsonObject : " + jsonObject);
						var state = jsonObject.state;

						if(state == 1){
							var index = $("#inputIndex").val();

							$("#ph" + index).text(phone);
							$("#em" + index).text(email);
							$("#po" + index).text(point);
							$("#pa" + index).text(password);
							$("#bl" + index).text(black);
							
							alert("參數已更新!");
						}else if(state == 2){
							console.log("Update Error!");
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

		$("#btnBackList").click(function() {
			$("#title").text("會員清單");
			$("#divMemberSetting").hide();
			$("#btnBackList").hide();
			$("#divMemberList").show();
			$("#subTitle").show();
			$('#recordTable td:nth-child(1),th:nth-child(5)').show();
			$('#recordTable td:nth-child(1),th:nth-child(6)').show();
			$('#recordTable td:nth-child(1),th:nth-child(7)').show();
			//$('#dataTable > tr > td').remove();
			$("#recordTable tr td").remove();
		});

	</script>
</body>

</html>