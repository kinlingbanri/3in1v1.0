<%@page import="com.store.model.StoreVO"%>
<%@page import="com.store.model.StoreService"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="java.util.List"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.device.model.DeviceVO"%>
<%@page import="com.device.model.DeviceService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	StoreService storeService = new StoreService();
	List<StoreVO> storeVOs = storeService.getAll();
	
	// Query All
	for (StoreVO store : storeVOs) {
		System.out.print(store.getSid() + ",");
		System.out.print(store.getName() + ",");
		System.out.print(store.getCity() + ",");
		System.out.print(store.getDistrict()+ ",");
		System.out.println(store.getPause());
	}
	
	request.setAttribute("storeVOs", storeVOs);

// 	DeviceService deviceService = new DeviceService();
// 	List<DeviceVO> devices = deviceService.getAll();
// 	for (DeviceVO device : devices) {
// 		System.out.print(device.getDid() + ",");
// 		System.out.print(device.getNumber() + ",");
// 		System.out.print(device.getCoin() + ",");
// 		System.out.print(device.getPaper() + ",");
// 		System.out.print(device.getLocation() + ",");
// 		System.out.print(device.getRefund() + ",");
// 		System.out.print(device.getUid() + ",");
// 		System.out.print(device.getStatus() + ",");
// 		System.out.print(device.getError() + ",");
// 		System.out.print(device.getMachid() + ",");
// 		System.out.print(device.getFreecount() + ",");
// 		System.out.println(device.getFreecountset());
// 	}

// 	request.setAttribute("devices", devices);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>三合一兌幣機後台管理</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="css/googlefont.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">


<link href="css/bootstrap4-toggle.css" rel="stylesheet">

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

<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/bootstrap4-toggle.js"></script>
<!-- <script src="js/jquery.qrcode.js"></script> -->
<script src="js/qrcode.js"></script>
<style>
{
box-sizing
:
 
border-box
;
}

/* Button used to open the contact form - fixed at the bottom of the page */
.open-button {
	background-color: #555;
	color: white;
	padding: 16px 20px;
	border: none;
	cursor: pointer;
	opacity: 0.8;
	position: fixed;
	bottom: 23px;
	right: 28px;
	width: 280px;
}

/* The popup form - hidden by default */
.form-popup {
	display: none;
	position: fixed;
	bottom: 0;
	right: 15px;
	border: 3px solid #f1f1f1;
	z-index: 9;
}

/* Add styles to the form container */
.form-container {
	max-width: 300px;
	padding: 10px;
	background-color: white;
}

/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	border: none;
	background: #f1f1f1;
}

/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container input[type=password]:focus
	{
	background-color: #ddd;
	outline: none;
}

/* Set a style for the submit/login button */
.form-container .btn {
	background-color: #04AA6D;
	color: white;
	padding: 16px 20px;
	border: none;
	cursor: pointer;
	width: 100%;
	margin-bottom: 10px;
	opacity: 0.8;
}

/* Add a red background color to the cancel button */
.form-container .cancel {
	background-color: red;
}

/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
	opacity: 1;
}
</style>

<script>
$(function() {




	
// 	$('input[type="checkbox"]').checkboxpicker({
// 	// OPTIONS
//   });
});

		
</script>

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a class="sidebar-brand d-flex align-items-center justify-content-center" href="./index.jsp">
				<div class="sidebar-brand-text mx-3">三合一後台管理系統</div>
			</a>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item">
				<a class="nav-link collapsed" href="#" data-toggle="collapse"
							data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
					<i class="fas fa-fw fa-folder"></i>
					<span>設備</span>
				</a>
				<div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="./devices.jsp">設備管理</a>						
						<!--                         <h6 class="collapse-header">Login Screens:</h6> -->						
						<!--                         <a class="collapse-item" href="register.html">Register</a> -->
						<!--                         <a class="collapse-item" href="forgot-password.html">Forgot Password</a> -->
						<!--                         <div class="collapse-divider"></div> -->
						<!--                         <h6 class="collapse-header">Other Pages:</h6> -->
						<!--                         <a class="collapse-item" href="404.html">404 Page</a> -->
						<!--                         <a class="collapse-item" href="blank.html">Blank Page</a> -->
					</div>
				</div>
			</li>
			<!-- End Nav Item - Pages Collapse Menu -->

			<!-- Nav Item - Stores -->
			<li class="nav-item">
				<a class="nav-link" href="./members.jsp">
					<i class="fas fa-fw fa-table"></i> <span>店家</span>
				</a>
			</li>

			<!-- Divider -->
			<!--             <hr class="sidebar-divider d-none d-md-block"> -->

			<!--             Sidebar Toggler (Sidebar) -->
			<!--             <div class="text-center d-none d-md-inline"> -->
			<!--                 <button class="rounded-circle border-0" id="sidebarToggle"></button> -->
			<!--             </div> -->

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow">
							<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
										data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<span	class="mr-2 d-none d-lg-inline text-gray-600 small">Admin</span>
								<!--                                 <img class="img-profile rounded-circle" src="img/undraw_profile.svg"> -->
							</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									Settings
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
									Activity Log
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">店家清單</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered display" id="dataTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th style="text-align:center;">店名</th>
											<th style="text-align:center;">啟用 / 停用</th>
											<th style="text-align:center;">QR code</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th style="text-align:center;">店名</th>
											<th style="text-align:center;">啟用 / 停用</th>
											<th style="text-align:center;">QR code</th>
										</tr>
									</tfoot>
									<tbody>

										<c:forEach items="${storeVOs}" var="store" varStatus="id">
											<tr id="tr${id.count}">
												<td style="text-align:center;" class="tdName" id="thnm${store.sid}">${store.name }</td>
												<td style="text-align:center;">
												<c:choose>
													<c:when test="${(store.pause == 1)}">															
														<input type="checkbox" class="cbPause" data-toggle="toggle" data-on="啟用" data-off="停用" data-onstyle="success" data-offstyle="secondary"  id="thps${store.sid}" checked>
													</c:when>
													<c:when test="${store.pause == 0}">
														<input type="checkbox" class="cbPause" data-toggle="toggle" data-on="啟用" data-off="停用" data-onstyle="success" data-offstyle="secondary"  id="thps${store.sid}">
													</c:when>
												</c:choose>
												</td>												
												<td style="text-align:center;"><button class="btn btn-info btnQr" id="btnQr${store.sid}">QR CODE</button></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						
						<!-- Name Modal -->
						<div class="modal fade" id="modalName" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
						  <div class="modal-dialog modal-dialog-centered" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title">店家名稱</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body" style="margin:0px auto;">
										<h3 id="storeName" style="text-align:center; color:#FF8800;">Test</h3>
										<input type="text" class="form-control" style="text-align:center;" placeholder="請輸入新的店名" id="inputNewStoreName">
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
						        <button type="button" class="btn btn-primary" id="btnNameChange">更新</button>
						      </div>
						    </div>
						  </div>
						</div>
						<!-- End Name Modal -->
						
						<!-- QR code Modal -->
						<div class="modal fade" id="modalQr" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
						  <div class="modal-dialog modal-dialog-centered" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id=""ModalQr"Title">QR code</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body" style="margin:0px auto;">
<!-- 						        <div id="divQr"> -->
<!-- 						        	<img id="imgQr" src="#" hidden > -->
<!-- 						        </div> -->

<!-- 											<input id="text" type="text" value="http://jindo.dev.naver.com/collie" style="width:80%" /><br /> -->
<div id="qrcode" style="width:100px; height:100px; margin-top:15px;"></div>

<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode"), {
	width : 100,
	height : 100
});

var qrcode = document.getElementById('qrcode');
var killone = qrcode.childNodes[0];
killone.parentNode.removeChild(killone);
var killtwo = qrcode.childNodes[0];
killtwo.parentNode.removeChild(killtwo);

//qrcode.makeCode("http://211.21.93.171:8080/3in1/index.jsp?DID=TY00001&MAID=DRY00001");

// function makeCode () {		
	//var elText = document.getElementById("text");
	
// 	if (!elText.value) {
// 		alert("Input a text");
// 		elText.focus();
// 		return;
// 	}
	
	
// }

// makeCode();
</script>

						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
						        <button type="button" class="btn btn-primary" id="btnQrDownload">下載</button>
						      </div>
						    </div>
						  </div>
						</div>
						<!-- End QR code Modal -->						
						
						
				</div>
				<!-- End DataTales Example -->
			</div>
			<!-- End Page Content -->

			<input type="hidden" value="" id="hiddendid">
			<!-- Modify Location Form -->
			<div class="form-popup" id="locationForm">
				<div class="form-container" style="background-color: #BFFFDF;">
					<h2 id="locationID"></h2>
					<h5 id="locationNow"></h5>
					<div>
						<label style="width: 32%; float: left; margin-top: 7px;"><b>欲更新店名</b></label>
						<input style="width: 68%; float: left; height: 24px;" type="text"
							name="location" id="inputlocation">
					</div>
					<button type="submit" class="btn"
						style="height: 48px; padding: 8px;" id="updateLocationBtn">更新</button>
					<button type="submit" class="btn cancel"
						style="height: 48px; padding: 8px;" onclick="closeAllForm()">取消</button>
				</div>
			</div>
			<!-- End Modify Location Form -->

			<!-- Modify Drop out coin Form -->
			<div class="form-popup" id="dropoutForm">
				<div class="form-container" style="background-color: #BFFFDF;">
					<h2 id="dropoutID"></h2>
					<h5 id="dropoutINow"></h5>
					<div>
						<label style="width: 32%; float: left; margin-top: 7px;"><b>欲退幣數量</b></label>
						<input style="width: 68%; float: left; height: 24px;" type="text"
							name="dropout" id="inputdropout">
					</div>
					<button type="submit" class="btn"
						style="height: 48px; padding: 8px;" id="updateDropoutBtn">退幣</button>
					<button type="submit" class="btn cancel"
						style="height: 48px; padding: 8px;" onclick="closeAllForm()">取消</button>
				</div>
			</div>
			<!-- End Modify Drop out coin Form -->

			<!-- Modify coin set Form -->
			<div class="form-popup" id="coinsetForm">
				<div class="form-container" style="background-color: #BFFFDF;">
					<h2 id="coinsetID"></h2>
					<h5 id="coinsetNow"></h5>
					<div>
						<label style="width: 32%; float: left; margin-top: 7px;"><b>欲設定儲幣</b></label>
						<input style="width: 68%; float: left; height: 24px;" type="text"
							name="coinset" id="inputcoinset">
					</div>
					<button type="submit" class="btn"
						style="height: 48px; padding: 8px;" id="updateCoinsetBtn">設定</button>
					<button type="submit" class="btn cancel"
						style="height: 48px; padding: 8px;" onclick="closeAllForm()">取消</button>
				</div>
			</div>
			<!-- End Modify coin paper Form -->

			<!-- Modify clean paper Form -->
			<div class="form-popup" id="cleanpaperForm">
				<div class="form-container" style="background-color: #BFFFDF;">
					<h2 id="cleanpaperID"></h2>
					<h5 id="cleanpaperNow"></h5>
					<div>
						<label style="width: 100%; float: left; margin-top: 7px;"><b>確定清空?</b></label>
					</div>
					<button type="submit" class="btn"
						style="height: 48px; padding: 8px;" id="updateCleanpaperBtn">清空</button>
					<button type="submit" class="btn cancel"
						style="height: 48px; padding: 8px;" onclick="closeAllForm()">取消</button>
				</div>
			</div>
			<!-- End Modify clean paper Form -->

			<!-- Modify clean coin Form -->
			<div class="form-popup" id="cleancoinForm">
				<div class="form-container" style="background-color: #BFFFDF;">
					<h2 id="cleancoinID"></h2>
					<h5 id="cleancoinNow"></h5>
					<div>
						<label style="width: 100%; float: left; margin-top: 7px;"><b>確定清空?</b></label>
					</div>
					<button type="submit" class="btn"
						style="height: 48px; padding: 8px;" id="updateCleancoinBtn">清空</button>
					<button type="submit" class="btn cancel"
						style="height: 48px; padding: 8px;" onclick="closeAllForm()">取消</button>
				</div>
			</div>
			<!-- End Modify clean coin Form -->

			<!-- Modify free count Form -->
			<div class="form-popup" id="freecountForm">
				<div class="form-container" style="background-color: #BFFFDF;">
					<h2 id="freecountID"></h2>
					<h5 id="freecountNow">
						<b>免費洗衣或烘衣</b>
					</h5>
					<label style="width: 100%; float: left; margin-top: 7px;">輸入機械編號(0
						- 63)</label> <input style="width: 100%; float: left; height: 24px;"
						type="text" name="coinset" id="inputdevicenumber"> <label
						style="width: 100%; float: left; margin-top: 7px;">選擇枚數(1
						- 30)</label> <input style="width: 100%; float: left; height: 24px;"
						type="text" name="coinset" id="inputfreeset">
					<button type="submit" class="btn"
						style="height: 48px; padding: 8px;" id="updateFreecountBtn">執行</button>
					<button type="submit" class="btn cancel"
						style="height: 48px; padding: 8px;" onclick="closeAllForm()">取消</button>
				</div>
			</div>
			<!-- End Modify free count Form -->


			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

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
						<span aria-hidden="true">Ã</span>
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



	<script>
	var id = 0;

	var qrcode = new QRCode(document.getElementById("qrcode"), {
		width : 100,
		height : 100
	});
	
	$(document).ready(function() {
	});

	function change(type, id, value){
		$.ajax({
			type : 'POST', //GET or POST
			url : "../StoreServlet", //請求的頁面
			cache : false, //防止抓到快取的回應
			data : {
				type : type,
				id : id,
				value : value
			},
			success : function(jsonObject) { //當請求成功後此事件會被呼叫
				var state = jsonObject.state;
				console.log("state : " + state);
				if (state == "1") {

					if(type == "name"){
						$("#thnm" + id).text(value);
						$("#storeName").text(value);						
					}else if(type == "pause"){

					}

				}
			},
			error : function(e) {
				console.log("e: " + e);
			}
		});
	}


	//var canvasDownload;
	$(".btnQr").click(function(){
		var str = 'http://211.21.93.171:8080/3in1/index.jsp?DID=' + "TY00001" + "&MAID=" + "DRY00001";
		console.log("qr code str : " + str);
		qrcode.makeCode(str);
// 		$('#qrcode canvas').hide();
 		//$('#qrcode').qrcode({ render : "canvas", width: 64, height: 64, text:str });
		$('#modalQr').modal('show');
// 		var canvas = $("#divQr canvas")[0];
// 		canvasDownload = canvas;
// 		var img = canvas.toDataURL("image/jpeg");			
// 		$('#divQr').html('<img src="'+ img +'" />');
// 		var idStr = this.id;
// 		var tempId =idStr.substring(4, idStr.length);
// 		id = parseInt(tempId, 10);
// 		console.log(this.id);


		
	});

	$(".cbPause").change(function() {
		//$('#console-event').html('Toggle: ' + $(this).prop('checked'))

		var idStr = this.id;
		id = idStr.substring(4, idStr.length);
		var isChecked = this.checked;
		console.log(isChecked);
		if(isChecked == true){
			change("pause", id, 1);
		}else{
			change("pause", id, 0);
		}
		console.log(this.id);
	});
	
	$(".tdName").click(function(){
		$("#storeName").text( $('#'+this.id).text());			
		$('#modalName').modal('show');
		var idStr = this.id;
		var tempId =idStr.substring(4, idStr.length);
		id = parseInt(tempId, 10);
		console.log(this.id);
	});

	$("#inputNewStoreName").click(function() {
		$("#inputNewStoreName").val('').css('color', '#6e707e');
	});
	
	$("#btnNameChange").click(function(){
		var value = $("#inputNewStoreName").val();
		if((value == null) || (value == "")){
			$("#inputNewStoreName").val("不可為空白").css('color', '#ff412b');
		}else{
			change("name", id, value);
		}
		console.log(id + ";" + value);
	});

	//#ff412b
//110, 112,126 = #6e707e
	

	//QR code download
	$("#btnQrDownload").click(function(){
		//下載的圖檔無法讀取
		var a = document.createElement("a"); //Create <a>
		a.href = $("#qrcode img").attr('src'); //Image Base64 Goes here
		a.download = "QRcode.png"; //File name Here
		a.click(); //Downloaded file

		//window.open("<img src='"+ canvasDownload.toDataURL('image/png') +"'/>")

// 			var a = document.createElement("a"); //Create <a>
// 			a.setAttribute('download', 'QrCode.png');
// 			var dataURL = canvasDownload.toDataURL('image/png');
// 			var url = dataURL.replace(/^data:image\/png/,'data:application/octet-stream');
// 			a.setAttribute('href', url);
// 	 		a.click(); //Downloaded file
	});



	



		// 		$(document).ready(function() {
		// 		    var table = $('#dataTable').DataTable();

		// 		    $('#dataTable tbody').on('click', 'tr', function (event) {
		// 					var id = $(this).attr('id');
		// 			    var num =  id.substring(2, id.length);
		// 			    $("#hiddenNumber").val(num);

		// 					$("#username").text( $("#thun" + num).text() ) ;
		// 					$("#inputemail").val( $("#them" + num).text() );
		// 					$("#inputpoint").val( $("#thpo" + num).text() );
		// 					$("#inputphone").val( $("#thph" + num).text() );
		// 					$("#inputpassword").val( $("#thpw" + num).text() );

		// 					var blackVal = $("#thbl" + num).text();
		// 					if(blackVal == 0){
		// 						$("#cbblack").prop('checked', false);
		// 					}else if(blackVal == 1){
		// 						$("#cbblack").prop('checked', true);
		// 					}

		// 					document.getElementById("locationForm").style.display = "block";
		// 		    });
		// 		});

// 		function closeAllForm() {
// 			document.getElementById("locationForm").style.display = "none";
// 			document.getElementById("dropoutForm").style.display = "none";
// 			document.getElementById("coinsetForm").style.display = "none";
// 			document.getElementById("cleanpaperForm").style.display = "none";
// 			document.getElementById("cleancoinForm").style.display = "none";
// 			document.getElementById("freecountForm").style.display = "none";
// 		}

// 		$(document).ready(function() {
// 							var table = $('#dataTable').DataTable();

// 							$('#dataTable tbody tr')
// 									.on(
// 											'click',
// 											'td',
// 											function(event) {
// 												var id = $(this).attr('id');
// 												var num = id.substring(4,
// 														id.length);
// 												var funStr = id.substring(2, 4);
// 												$("#hiddendid").val(num);
// 												console.log("id : " + id);
// 												console.log("num : " + num);
// 												console.log("funStr : "
// 														+ funStr);
// 												console
// 														.log("hiddendid : "
// 																+ $(
// 																		"#hiddendid")
// 																		.val());

// 												closeAllForm();

// 												if (funStr == "lc") {
// 													$('#locationID').text(
// 															$('#thnb' + num)
// 																	.text());
// 													$('#locationNow')
// 															.text(
// 																	"目前店名 : "
// 																			+ $(
// 																					this)
// 																					.text());
// 													document
// 															.getElementById("locationForm").style.display = "block";
// 												} else if (funStr == "ci") {
// 													$('#dropoutID').text(
// 															$('#thnb' + num)
// 																	.text());
// 													$('#dropoutINow')
// 															.text(
// 																	"目前儲幣 : "
// 																			+ $(
// 																					this)
// 																					.text());
// 													document
// 															.getElementById("dropoutForm").style.display = "block";
// 												} else if (funStr == "sv") {
// 													$('#coinsetID').text(
// 															$('#thnb' + num)
// 																	.text());
// 													$('#coinsetNow')
// 															.text(
// 																	"目前儲幣 : "
// 																			+ $(
// 																					this)
// 																					.text());
// 													document
// 															.getElementById("coinsetForm").style.display = "block";
// 												} else if (funStr == "pp") {
// 													$('#cleanpaperID').text(
// 															$('#thnb' + num)
// 																	.text());
// 													$('#cleanpaperNow')
// 															.text(
// 																	"目前紙鈔 : "
// 																			+ $(
// 																					this)
// 																					.text());
// 													document
// 															.getElementById("cleanpaperForm").style.display = "block";
// 												} else if (funStr == "cc") {
// 													$('#cleancoinID').text(
// 															$('#thnb' + num)
// 																	.text());
// 													$('#cleancoinNow')
// 															.text(
// 																	"目前儲幣 : "
// 																			+ $(
// 																					"#thci"
// 																							+ num)
// 																					.text());
// 													document
// 															.getElementById("cleancoinForm").style.display = "block";
// 												} else if (funStr == "fc") {
// 													$('#freecountID').text(
// 															$('#thnb' + num)
// 																	.text());
// 													document
// 															.getElementById("freecountForm").style.display = "block";
// 												} else if (funStr == "rc") {

// 													$
// 															.ajax({
// 																type : 'POST', //GET or POST
// 																url : "../DeviceHistorySevlet", //請求的頁面
// 																cache : false, //防止抓到快取的回應
// 																data : {
// 																	num : num
// 																},
// 																success : function(
// 																		jsonObject) { //當請求成功後此事件會被呼叫
// 																	console
// 																			.log("jsonObject : "
// 																					+ jsonObject);
// 																},
// 																error : function(
// 																		e) {
// 																	console
// 																			.log("e: "
// 																					+ e);
// 																}, //當請求失敗後此事件會被呼叫
// 																statusCode : { //狀態碼處理
// 																	404 : function() {
// 																		alert("page not found");
// 																	}
// 																}
// 															});
// 												}

// 											});
// 						});

		// 		function openForm() {
		// 				document.getElementById("locationForm").style.display = "block";
		// 		}

		$("#updateBtn").click(function() {

			var checked = $('#cbblack').is(":checked");
			var blackstate = 0;
			if (checked == true) {
				blackstate = 1;
			}

			$.ajax({
				type : 'POST', //GET or POST
				url : "../AdminModifyMemberServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : {
					username : $("#username").text(),
					email : $("#inputemail").val(),
					point : $("#inputpoint").val(),
					phone : $("#inputphone").val(),
					password : $("#inputpassword").val(),
					black : blackstate
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					var state = jsonObject.state;
					console.log("state : " + state);
					if (state == "ok") {

						var num = $("#hiddenNumber").val();
						$("#them" + num).text($("#inputemail").val());
						$("#thpo" + num).text($("#inputpoint").val());
						$("#thph" + num).text($("#inputphone").val());
						$("#thpw" + num).text($("#inputpassword").val());
						$("#thbl" + num).text(blackstate);
						closeForm();
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