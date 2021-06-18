<%@page import="com.store.model.StoreService"%>
<%@page import="com.store.model.StoreVO"%>
<%@page import="com.history.model.DeviceJsonObject"%>
<%@page import="com.history.model.HistoryService"%>
<%@page import="com.history.model.HistoryVO"%>
<%@page import="java.util.List"%>
<%@page import="com.mem.model.MemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// 	String deviceNumber = "TY00001";
// 	String location = "RD-1";
	int did = 1;

	HistoryService historyService = new HistoryService();
	int totalCount = historyService.getCount(did);
	System.out.println("totalCount : " + totalCount);
	int pageTotal = totalCount / 100;
	int lastPage = pageTotal * 100;
	System.out.println("pageTatal : " + pageTotal);

// 	List<DeviceJsonObject> deviceJsonObjects = historyService.getAllByDid(did, 1, 100);
// 	request.setAttribute("deviceJsonObjects", deviceJsonObjects);
	
	StoreService storeService = new StoreService();
	List<StoreVO> storeVOs = storeService.getAll();
	request.setAttribute("storeVOs", storeVOs);

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

<title>三合一兌幣機後台管理</title>

<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link href="css/googlefont.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.css" rel="stylesheet">

<link rel="stylesheet" href="./css/jquery-ui.css">

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>

<!-- Date range picker -->
<script src="js/moment.min.js"></script>
<script src="js/daterangepicker.js"></script>
<link rel="stylesheet" href="./css/daterangepicker.css">

<style>
{
	box-sizing:border-box;
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

/* Center the loader */
#loader {
	position: absolute;
	left: 50%;
	top: 50%;
	z-index: 1;
	width: 120px;
	height: 120px;
	margin: -76px 0 0 -76px;
	border: 16px solid #f3f3f3;
	border-radius: 50%;
	border-top: 16px solid #3498db;
	-webkit-animation: spin 2s linear infinite;
	animation: spin 2s linear infinite;
}

@-webkit-keyframes spin { 0% {
	-webkit-transform: rotate(0deg);
}

100%
{
-webkit-transform:rotate(360deg);
}
}
@
keyframes spin { 0% {
	transform: rotate(0deg);
}

100%{
transform:rotate(360deg);
}
}

/* Add animation to "page content" */
.animate-bottom {
	position: relative;
	-webkit-animation-name: animatebottom;
	-webkit-animation-duration: 1s;
	animation-name: animatebottom;
	animation-duration: 1s
}

@-webkit-keyframes animatebottom {from { bottom:-100px;
	opacity: 0
}

to {
	bottom: 0px;
	opacity: 1
}

}
@keyframes animatebottom {from { bottom:-100px;
	opacity: 0
}

to {
	bottom: 0;
	opacity: 1
}

}
#myDiv {
	display: none;
	text-align: center;
}

  .col-sm-4 {
    flex: 0 0 25%;
    max-width: 25%;
  }
</style>

<!-- date range picker -->
<style>
/* 外框 */
.daterangepicker{
	background: #BBE6E4  none;
}

.drp-calendar{
 background: #90EE90 none;
}

.cancelBtn{
	background: #F0F6F6 none;
}


</style>

<script>

    $( function() {
			//$("#divDataTable").show();
        
        $( "#slider-vertical" ).slider({
          range: "min",
          min: 1,
          max: <%=pageTotal%>,
          value: 1,
          slide: function( event, ui ) {
            $( "#amount" ).val( ui.value );            
          },
          stop:function(event, ui){
        	  document.getElementById("loader").style.display = "block";
        	  $("#amount").prop('disabled', true);
              
        	  $( "#slider-vertical" ).slider({ disabled: "true" });
        	  $("#slider-vertical .ui-slider-handle").unbind('keydown');
        	  
            var value = $( "#slider-vertical" ).slider( "value" );
        	  $( "#amount" ).val( value );
        	  $("#dataTable td").parent().remove();
        	  
		  			$.ajax({
		  			  type: 'POST',                     //GET or POST
		  			  url: "../DeviceHistorySevlet",            //請求的頁面
		  			  cache: false,                     //防止抓到快取的回應
		  			  data: {
			  			  did:<%=did%>,
								value:(value*100)
		  			  },
		  			  success: function (jsonObject) {         //當請求成功後此事件會被呼叫
		  					document.getElementById("loader").style.display = "none";
// 									alert("len : " + jsonObject.len);
									console.log(jsonObject[0].len);
				  				for(var k in jsonObject){
					  					if(k == 0){
					  						console.log(jsonObject);
							  			}
				  				    $("#dataTable").find('tbody').append($('<tr>')
				  																						.append($('<td>')
				  																							.text(jsonObject[k].ttime)
				  																						)
				  																						.append($('</td>'))
				  																						.append($('<td>')
				  																							.text(jsonObject[k].event)
				  																						)
				  																						.append($('</td>'))
				  																					).append($('</tr>'))	
				  				}
				  				
									$( "#slider-vertical" ).slider( "enable" );
									$("#amount").prop('disabled', false);
									var newPageCount = (jsonObject[0].len - 1) / 100;
									$( "#slider-vertical" ).slider("option", "max", newPageCount);
		  			  },
		  			  error: function(e){
		  			  	console.log("e: " + e);
		  			  },            //當請求失敗後此事件會被呼叫
		  			  statusCode: {                     //狀態碼處理
		  			    404: function() {
		  			      alert("page not found");
		  			    }
		  			  }
		  			});
          }
        });        
      });

    </script>
    
    <script>
    Date.prototype.yyyymmdd = function() {
    	  var mm = this.getMonth() + 1; // getMonth() is zero-based
    	  var dd = this.getDate();

    	  return [this.getFullYear(),
    	          (mm>9 ? '' : '0') + mm,
    	          (dd>9 ? '' : '0') + dd
    	         ].join('-');
    	};

    	var date = new Date();
    	date.yyyymmdd();
    	console.log("Today : " + date.yyyymmdd());
    </script>
    
<script>

$(function() {
	$("#report").click(function(){
		$("#reportPages").slideToggle(150);
	});
	$("#setting").click(function(){
		$("#settingPages").slideToggle(150);
	});
});

</script>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a class="sidebar-brand d-flex align-items-center justify-content-center" href="./index.jsp">
				<div class="sidebar-brand-text mx-3">三合一後台管理系統</div>
			</a>

			<!-- Nav Item - Pages Collapse Menu -->
<!-- 			<li class="nav-item"> -->
<!-- 				<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" -->
<!-- 									aria-expanded="true" aria-controls="collapsePages"> -->
<!-- 					<i class="fas fa-fw fa-folder"></i> -->
<!-- 					<span>營運管理</span> -->
<!-- 				</a> -->
<!-- 				<div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar"> -->
<!-- 					<div class="bg-white py-2 collapse-inner rounded"> -->
<!-- 						<a class="collapse-item" href="./devices.jsp">店家管理</a> -->
<!-- 						<a class="collapse-item" href="./devices.jsp">設備管理</a> -->
<!-- 						                        <h6 class="collapse-header">Login Screens:</h6> -->
<!-- 						                        <a class="collapse-item" href="register.html">Register</a> -->
<!-- 						                        <a class="collapse-item" href="forgot-password.html">Forgot Password</a> -->
<!-- 						                        <div class="collapse-divider"></div> -->
<!-- 						                        <h6 class="collapse-header">Other Pages:</h6> -->
<!-- 						                        <a class="collapse-item" href="404.html">404 Page</a> -->
<!-- 						                        <a class="collapse-item" href="blank.html">Blank Page</a> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</li> -->
			<!-- End Nav Item - Pages Collapse Menu -->
			
			
			
			
			<!-- Nav Report - Pages Collapse Menu -->
			<li class="nav-item">
				<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="reportPages"
									aria-expanded="true" aria-controls="reportPages" id="report">
					<i class="fas fa-fw fa-folder"></i>
					<span>營運報表</span>
				</a>
				<div id="reportPages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="#">店家資訊</a>
						<a class="collapse-item" href="./devicehistory.jsp">設備資訊</a>
					</div>
				</div>
			</li>
			<!-- End Nav Report - Pages Collapse Menu -->

			<!-- Nav Item - Members -->
			<li class="nav-item"><a class="nav-link" href="./members.jsp">
					<i class="fas fa-fw fa-table"></i>
					<span>會員</span>
			</a></li>

			
			<!-- Nav Setting - Pages Collapse Menu -->
			<li class="nav-item">
				<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="reportPages"
									aria-expanded="true" aria-controls="reportPages" id="setting">
					<i class="fas fa-fw fa-folder"></i>
					<span>參數設定</span>
				</a>
				<div id="settingPages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="#">優惠設定</a>
						<a class="collapse-item" href="#">簡訊機設定</a>
					</div>
				</div>
			</li>
			<!-- End Nav Setting - Pages Collapse Menu -->
			
			
			
			
			



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
								<span class="mr-2 d-none d-lg-inline text-gray-600 small" style="display: inline !important;">Admin</span>
								<!--                                 <img class="img-profile rounded-circle" src="img/undraw_profile.svg"> -->
							</a>
							<!-- Dropdown - User Information -->
							<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
										aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#">
									<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a>
								<a class="dropdown-item" href="#">
									<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>Settings
								</a>
								<a class="dropdown-item" href="#">
									<i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>Activity Log
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
									<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>Logout
								</a>
							</div></li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<div style="position: fixed; top: 50%; left: 50%; z-index: 99;">
					<div id="loader" style="display: none;"></div>
				</div>

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary" style="float: left;">設備記錄</h6>
							<button class="btn btn-success" type="button" id="btnOutExcel" style="float: right;">輸出excel</button>
						</div>
						<button class="btn btn-success" type="button" style="width:96%; margin:8px 0 0 2%;" id="btnSetQuery">設定查詢條件</button>
						<div id="divSearch" style="margin: 12px;">
							<div style="background-color: #DFFFDF; height: 76px;">
								<div style="width:100%; float:left;">
									<h5 style="float:left;">請選擇日期:</h5>
								</div>
								<input class="default form-control" type="text" id="inputDateRangePicker" style="width: 226px; float:left;" />
								<span class="fa fa-calendar" style="font-size: 28px; margin: 6px; cursor:pointer;"></span>
							</div>
							<div style="margin: 24px 0 24px 0; background-color: #F1E1FF;">
								<div style="width:100%; float:left;">
									<h5 style="float:left;">請選擇加值或消費:</h5>
								</div>
								<div class="btn-group">
								  <button id="btnAS" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    加值
								  </button>
								  <div class="dropdown-menu" id="dropdownList">
								    <a class="dropdown-item" href="#">加值</a>
								    <a class="dropdown-item" href="#">消費</a>
								  </div>
								</div>
							</div>
							<div style="background-color: #FFE6D9;">
								<div style="width:100%; float:left;">
									<h5 style="float:left;">請選擇店家:</h5>
									<h5 style="float:left;" id="selectST">${storeVOs.get(0).getName()}</h5>
								</div>
								<button class="btn btn-info" type="button" id="btnSelectStore">選擇店家</button>
								<div class="container" id="divStoreList" style="display:none;">
							    <div class="row">
							        <c:forEach items="${storeVOs}" var="store" varStatus="id">
													<div class="col-sm-4 py-2">
									            <div class="card h-100 border-primary" style="text-align: center; cursor:pointer;">
									                <div class="card-body">
									                    <h3 class="card-title" style="font-size: 1.2rem; font-weight: 560;">${store.name}</h3>
									                </div>
									            </div>
									        </div>
											</c:forEach>
							        
							        

							        
							        
							    </div>
							    <!--  End Row -->
							</div>

								
								
							</div>
						</div>
						<button class="btn btn-success" type="button" style="width:96%; margin:8px 0 8px 2%;" id="btnQuery">查詢</button>

						<!-- Div Data Table -->
						<div style="width: 96%; margin-left: 2%; display:none;" id="divDataTable">
							<div style="float: left;">
								<p style="float: left;">
									<label for="amount">總筆數:</label>
								</p>
								<p style="float: left;">
									<label for="amount" id="totalCount"><%=totalCount%></label>
								</p>
							</div>
							<div style="float: right;">
								<p>
									<label for="amount">當前頁數(每頁100筆):</label>
										<input type="text" id="amount" style="border: 0; color: #f6931f; font-weight: bold; width: 120px;"
										oninput="value=value.replace(/[^\d]/g,'')">
								</p>
							</div>

							<div id="slider-vertical" style="margin-top: 40px;"></div>

							<div class="card-body">
								<div class="table-responsive">
									<table class='table table-striped table-bordered display' width='100%' cellspacing='0' style="width: 99.4%; margin: 2px;" id='dataTable'>
										<thead>
											<tr>
<!-- 												<th></th> -->
<!-- 												<th></th> -->
											</tr>
										</thead>
										<tbody>
<%-- 											<c:url var="url" value="/AdminModifyMemberServlet" /> --%>
<%-- 											<c:forEach items="${deviceJsonObjects}" var="devicejson" varStatus="id"> --%>
<%-- 												<tr id="tr${id.count}"> --%>
<%-- 													<td>${devicejson.ttime }</td> --%>
<%-- 													<td>${devicejson.event }</td> --%>
<!-- 												</tr> -->
<%-- 											</c:forEach> --%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!--End Div Data Table -->

					</div>
					<!-- /.container-fluid -->
				</div>

				<div class="form-popup" id="myForm">
					<div class="form-container" style="background-color: #BFFFDF;">
						<h1 id="username"></h1>
						<div>
							<label for="email" style="width: 24%; float: left; margin-top: 7px;"><b>Email</b></label>
							<input style="width: 76%; float: left; height: 24px;" type="text" name="email" id="inputemail">
						</div>
						<div>
							<label for="point" style="width: 24%; float: left; margin-top: 7px;"><b>點數</b></label>
							<input style="width: 76%; float: left; height: 24px;" type="text" name="point" id="inputpoint">
						</div>
						<div>
							<label for="phone" style="width: 24%; float: left; margin-top: 7px;"><b>電話</b></label>
							<input style="width: 76%; float: left; height: 24px;" type="text" name="phone" id="inputphone">
						</div>
						<div>
							<label for="password" style="width: 24%; float: left; margin-top: 7px;"><b>密碼</b></label>
							<input style="width: 76%; float: left; height: 24px;" type="text" name="password" id="inputpassword">
						</div>
						<div>
							<label for="password" style="width: 24%; float: left; margin-top: 7px;"><b>權限</b></label>
							<select class="browser-default custom-select" style="width: 76%;">
								<option selected>Open this select menu</option>
								<option value="1">One</option>
								<option value="2">Two</option>
								<option value="3">Three</option>
							</select>
						</div>
						<div>
							<div class="form-check" style="margin: 12px 0 20px;">
								<input class="form-check-input" type="checkbox" value="" id="cbblack">
								<label class="form-check-label" for="flexCheckDefault">黑名單</label>
							</div>
						</div>
						<button type="submit" class="btn" style="height: 48px; padding: 8px;" id="updateBtn">更新</button>
						<button type="submit" class="btn cancel" style="height: 48px; padding: 8px;" onclick="closeForm()">取消</button>
						<input type="hidden" value="" id="hiddenNumber">
					</div>
				</div>
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
		<a class="scroll-to-top rounded" href="#page-top">
			<i class="fas fa-angle-up"></i>
		</a>

		<!-- Logout Modal-->
		<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Ready toLeave?</h5>
						<button class="close" type="button" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">Ã</span>
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

		<script>

		  // Toggle the side navigation
		  $("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
		    $("body").toggleClass("sidebar-toggled");
		    $(".sidebar").toggleClass("toggled");
		    if ($(".sidebar").hasClass("toggled")) {
		      $('.sidebar .collapse').collapse('hide');
		    };
		  });
		
		$("#inputDateRangePicker").daterangepicker({
			"alwaysShowCalendars": true,
			opens: "right",
			startDate: date.yyyymmdd(),
			endDate: date.yyyymmdd(),
// 			ranges: {
// 				"今天": [moment(), moment()],
// 				"過去 7 天": [moment().subtract(6, "days"), moment()],
// 				"本月": [moment().startOf("month"), moment().endOf("month")],
// 				"上個月": [moment().subtract(1, "month").startOf("month"), moment().subtract(1, "month").endOf("month")]
// 			},
			locale: {
				format: "YYYY-MM-DD",
				separator: " ~ ",
				applyLabel: "確定",
				cancelLabel: "清除",
				fromLabel: "開始日期",
				toLabel: "結束日期",
				customRangeLabel: "自訂日期區間",
				daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
				monthNames: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
				firstDay: 1
			}
		});

		function updateTable(value){
				document.getElementById("loader").style.display = "block";
				
      	$( "#slider-vertical" ).slider({ disabled: "true" });
      	$("#slider-vertical .ui-slider-handle").unbind('keydown');
       	  
      	  $("#dataTable td").parent().remove();
      	  
		  			$.ajax({
		  			  type: 'POST',                     //GET or POST
		  			  url: "../DeviceHistorySevlet",            //請求的頁面
		  			  cache: false,                     //防止抓到快取的回應
		  			  data: {
			  			  did:<%=did%>,
								value:(value*100)
		  			  },
		  			  success: function (jsonObject) {         //當請求成功後此事件會被呼叫
		  					document.getElementById("loader").style.display = "none";
//									alert("len : " + jsonObject.len);
									console.log(jsonObject[0].len);
				  				for(var k in jsonObject){
					  					if(k == 0){
					  						console.log(jsonObject);
							  			}
				  				    $("#dataTable").find('tbody').append($('<tr>')
				  																						.append($('<td>')
				  																							.text(jsonObject[k].ttime)
				  																						)
				  																						.append($('</td>'))
				  																						.append($('<td>')
				  																							.text(jsonObject[k].event)
				  																						)
				  																						.append($('</td>'))
				  																					).append($('</tr>'))	
				  				}
				  				
									$( "#slider-vertical" ).slider( "enable" );
									$("#amount").prop('disabled', false);
									$("#slider-vertical").slider("value", value);
									var newPageCount = (jsonObject[0].len - 1) / 100;
									$( "#slider-vertical" ).slider("option", "max", newPageCount);
		  			  },
		  			  error: function(e){
		  			  	console.log("e: " + e);
		  			  },            //當請求失敗後此事件會被呼叫
		  			  statusCode: {                     //狀態碼處理
		  			    404: function() {
		  			      alert("page not found");
		  			    }
		  			  }
		  			});

		}

		function query(){
			var dateRange = $("#inputDateRangePicker").val();
			var selectAS = $("#btnAS").text();
			var storeName = $("#selectST").text();

			console.log( "dateRange : " + dateRange + "; selectAS :  " + selectAS + "; storeName : " + storeName);
			
  			$.ajax({
	  			  type: 'post',                     //GET or POST
	  				dataType : "json",
	  			  url: "../DeviceQueryHistorySevlet",            //請求的頁面
	  			  cache: false,                     //防止抓到快取的回應
	  			  data: {
	  					dateRange : dateRange,
	  					selectAS : selectAS,
	  					storeName : storeName
	  			  },
	  			  success: function (jsonObject) {         //當請求成功後此事件會被呼叫
	  					console.log( jsonObject);
							var addRecordVOs = jsonObject["addRecordVOs"];
							console.log("addRecordVOs:" + addRecordVOs);
							
							var count = addRecordVOs.length;

							$("#dataTable").find('thead')
									.append($('<tr>')
											.append($('<td>')
												.text("時間")
											)
											.append($('</td>'))
											.append($('<td>')
												.text("加值金額")
											)
											.append($('</td>'))
											.append($('<td>')
												.text("儲值點數")
											)
											.append($('</td>'))
								).append($('</tr>')

								);

							
							for(var i = 0; i < count; i++){
								var addRecord = addRecordVOs[i];

								console.log(addRecord);
								var totalMoney = (addRecord["paper100"] * 100) +
																		(addRecord["paper500"] * 500) +
																		(addRecord["paper1000"] * 1000);
								var eventStr = "加值金額" +  totalMoney + "元," +
																"儲值點數" + addRecord.point + "點";
								console.log("eventStr:" + eventStr);
								console.log("storedatetime:" + addRecord["storedatetime"].split(' ')[0]);
			  				    $("#dataTable").find('tbody')
			  				    .append($('<tr>')
												.append($('<td>')
													.text(addRecord["storedatetime"].split(' ')[0])
												)
												.append($('</td>'))
												.append($('<td>')
													.text(totalMoney)
												)
												.append($('</td>'))
												.append($('<td>')
													.text(addRecord.point)
												)
												.append($('</td>'))
										).append($('</tr>'));
							}

							$("#dataTable thead").css('text-align','center');
							$("#dataTable tbody").css('text-align','center');
			  				
								$( "#slider-vertical" ).slider( "enable" );
								$("#amount").prop('disabled', false);
								$("#totalCount").text(count);
								$("#slider-vertical").slider("value", 1);
								var newPageCount = (count / 100) + 1;
								$( "#slider-vertical" ).slider("option", "max", newPageCount);
	  			  },
	  			  error: function(e){
	  			  	console.log("e: " + e);
	  			  },            //當請求失敗後此事件會被呼叫
	  			  statusCode: {                     //狀態碼處理
	  			    404: function() {
	  			      alert("page not found");
	  			    }
	  			  }
	  			});
		}

		$(document).ready(function() {
			$( "#amount" ).val(1);
		});

		$("#btnSetQuery").on('click', function(){
			$("#divSearch").show();
			$("#divDataTable").hide();
			$("#divDataTable thead tr").empty();
			$("#divDataTable tbody tr").empty();
			$("#btnQuery").attr("disabled", false);
		});

		$("#btnQuery").on('click', function(){
			$("#divSearch").hide();
			query();
			$("#divDataTable").show();
			$("#btnQuery").attr("disabled", true);
		});
		
		$('.fa-calendar').click(function() {
		    $("#inputDateRangePicker").focus();
		  });

		$("#dropdownList a").on('click', function(){
			$("#btnAS").text($(this).text());			
		});		
		
		$("#btnSelectStore").click(function(){
			$("#divStoreList").toggle();
		});

		$(".card-title").click(function(e){
			$("#selectST").text($(this).text());
			$("#divStoreList").hide();
		});

		$("#amount").blur(function(){
			//alert("mouseup!");
	    var value = $( "#amount" ).val();
	    console.log("value : " + value);
			if((value > <%=pageTotal%>) ||(value < 1)){
				alert("請輸入大於0或小於等於<%=pageTotal%>的數值");
				} else {
					updateTable(value);
				}
			});

			$("#btnOutExcel").click(function() {
				var table = document.getElementById('dataTable');
				var data = [];
				for (var i = 1, row; row = table.rows[i]; i++) {
					var col = row.cells;
					var jsonObj = {
						'時間' : col[0].innerHTML,
						'訊息' : col[1].innerHTML
					}
					data.push(jsonObj);
				}

				var obj = {};
				var titleObj = {};
				titleObj['ttime'] = '時間';
				titleObj['event'] = '內容';
				obj['title'] = titleObj;
				obj['data'] = data;
				console.log(JSON.stringify(obj));

				$.ajax({
					type : 'POST', //GET or POST
					url : "../HistoryOutExcelSevlet", //請求的頁面
					cache : false, //防止抓到快取的回應
					data : {
						obj : JSON.stringify(obj)
					},
					success : function(jsonObject) { //當請求成功後此事件會被呼叫
						var state = jsonObject.state;
						console.log("state : " + state);
						if (state == 1) {
							console.log("儲存成功!");
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