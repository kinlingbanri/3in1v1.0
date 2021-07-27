<%@page import="com.adminconfig.model.AdminConfigService"%>
<%@page import="com.addrecord.model.AddRecordService"%>
<%@page import="com.adminconfig.model.AdminConfigVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
AdminConfigVO adminConfigVO = new AdminConfigService().getAdminConfig();
request.setAttribute("adminConfigVO", adminConfigVO);


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
				<div class="container-fluid" id="divServerConfig"">
					<div class="card shadow mb-4">
					
						<div class="card-header py-3">
							<h4 class="m-0 font-weight-bold text-primary" style="float: left;">伺服器設定</h4>
						</div>

						<div style="width: 100%;">
							<button id="btnUpdate" class="btn btn-info" style="float: left; margin: 6px 0 6px 6px;">
								<i class="fa fa-edit"></i>修改參數
							</button>
						</div>


						<!-- addvalue table 1 start -->
						<div id="divConfig" class="table-responsive">
							<table id="dataTable1" class="table">
								<thead>
									<tr style="text-align: center;">
										<th>#</th>
										<th>目前參數</th>
										<th>預更新參數</th>
									</tr>
								</thead>
								<tbody>
									<tr style="text-align: center;">
										<th style="padding: 1rem;">IP</th>
										<th id="textIp"style="padding-top: 16px; font-weight: 600;">${adminConfigVO.ip}</th>
										<th>
											<input id="inputIp" type="text" class="form-control configInput"
															value="${adminConfigVO.ip}">
										</th>
									</tr>
									<tr style="text-align: center;">
										<th style="padding: 1rem;">COM PORT</th>
										<th id="textComport" style="padding-top: 16px; font-weight: 600;">${adminConfigVO.comPort}</th>
										<th>
											<input id="inputComport" type="text" class="form-control configInput"
															value="${adminConfigVO.comPort}">
										</th>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- addvalue table 1 end -->

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
	$("#btnUpdate").click(function(){
		var ip = $("#inputIp").val();
		var comport = $("#inputComport").val();
		
		$.ajax({
			type : 'POST', //GET or POST
			url : "../UpdateAdminConfigServlet", //請求的頁面
			cache : false, //防止抓到快取的回應
			data : {
				ip : ip,
				comport : comport
			},
			success : function(jsonObject) { //當請求成功後此事件會被呼叫
				console.log("jsonObject : " + jsonObject);
				var state = jsonObject.state;
				console.log("state : " + state);
				if(state == 1){
					$("#textIp").text(ip);
					$("#textComport").text(comport);
					alert("參數更新成功!");
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
	</script>
</body>

</html>