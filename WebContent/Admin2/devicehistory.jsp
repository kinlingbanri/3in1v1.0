<%@page import="com.history.model.HistoryService"%>
<%@page import="com.history.model.HistoryVO"%>
<%@page import="java.util.List"%>
<%@page import="com.mem.model.MemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String deviceNumber = "TY00001";
String location = "RD-1";
int did = 1;

HistoryService historyService = new HistoryService();
int totalCount = historyService.getCount(did);
int lastCount = 0;
if(totalCount > 50){
	lastCount = totalCount - 50;
}else{
	lastCount = totalCount;
}

// List<HistoryVO> historyVOs = historyService.getAllByDid(did, lastCount, 50);
// request.setAttribute("historyVOs", historyVOs);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>三合一對幣機後台管理系統</title>

    <!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="css/googlefont.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="vendor/datatables/scroller.dataTables.css" rel="stylesheet">
    
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.scroller.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>
    
    <script src="js/jquery.validate.js"></script>
    <script src="js/additional-methods.js"></script>
    
    <style>
			{box-sizing: border-box;}
			
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
			.form-container input[type=text]:focus, .form-container input[type=password]:focus {
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
			  margin-bottom:10px;
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

    </script>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item">
                <a class="nav-link" href="index.html">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Interface
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Components</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Custom Components:</h6>
                        <a class="collapse-item" href="buttons.html">Buttons</a>
                        <a class="collapse-item" href="cards.html">Cards</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Utilities</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Custom Utilities:</h6>
                        <a class="collapse-item" href="utilities-color.html">Colors</a>
                        <a class="collapse-item" href="utilities-border.html">Borders</a>
                        <a class="collapse-item" href="utilities-animation.html">Animations</a>
                        <a class="collapse-item" href="utilities-other.html">Other</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Addons
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Pages</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Login Screens:</h6>
                        <a class="collapse-item" href="login.html">Login</a>
                        <a class="collapse-item" href="register.html">Register</a>
                        <a class="collapse-item" href="forgot-password.html">Forgot Password</a>
                        <div class="collapse-divider"></div>
                        <h6 class="collapse-header">Other Pages:</h6>
                        <a class="collapse-item" href="404.html">404 Page</a>
                        <a class="collapse-item" href="blank.html">Blank Page</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="charts.html">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Charts</span></a>
            </li>

            <!-- Nav Item - Tables -->
            <li class="nav-item active">
                <a class="nav-link" href="./members.jsp">
                    <i class="fas fa-fw fa-table"></i>
                    <span>會員</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>

                    <!-- Topbar Search -->
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerts Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter">7</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Message Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_1.svg"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div class="font-weight-bold">
                                        <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                            problem I've been having.</div>
                                        <div class="small text-gray-500">Emily Fowler Â· 58m</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_2.svg"
                                            alt="...">
                                        <div class="status-indicator"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">I have the photos that you ordered last month, how
                                            would you like them sent to you?</div>
                                        <div class="small text-gray-500">Jae Chun Â· 1d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_3.svg"
                                            alt="...">
                                        <div class="status-indicator bg-warning"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Last month's report looks great, I am very happy with
                                            the progress so far, keep up the good work!</div>
                                        <div class="small text-gray-500">Morgan Alvarez Â· 2d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                            told me that people say this to all dogs, even if they aren't good...</div>
                                        <div class="small text-gray-500">Chicken the Dog Â· 2w</div>
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">會員清單</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered display" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>時間</th>
                                            <th>訊息</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>時間</th>
                                            <th>訊息</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    
                                    
                                    
<%--                                     		<c:url var="url" value="/AdminModifyMemberServlet" />  --%>
<%-- 																				<c:forEach items="${memberVOs}" var="member" varStatus="id"> --%>
<%-- 																					<tr id="tr${id.count}"> --%>
<%-- 																						<td id="thun${id.count}">${member.username }</td> --%>
<%-- 																						<td id="them${id.count}">${member.email }</td> --%>
<%-- 																						<td id="thpo${id.count}">${member.point }</td> --%>
<%-- 																						<td id="thph${id.count}">${member.phone }</td> --%>
<%-- 																						<td id="thpw${id.count}">${member.password }</td> --%>
<%-- 																						<td id="thbl${id.count}">${member.black }</td> --%>
<%-- 																						<td id="thau${id.count}">${member.authority }</td> --%>
<!-- 																					</tr> -->
<%-- 																				</c:forEach> --%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
            </div>
            
            <div class="form-popup" id="myForm" >
            	<div  class="form-container" style="background-color: #BFFFDF;">
            		<h1 id="username"></h1>            		
            		<div>
            				<label for="email" style="width:24%; float:left; margin-top: 7px;"><b>Email</b></label>
						    		<input  style="width:76%; float:left; height: 24px;" type="text"name="email" id="inputemail">
            		</div>
								<div>
            				<label for="point" style="width:24%; float:left; margin-top: 7px;"><b>點數</b></label>
						    		<input  style="width:76%; float:left; height: 24px;" type="text" name="point" id="inputpoint">
            		</div>
								<div>
            				<label for="phone" style="width:24%; float:left; margin-top: 7px;"><b>電話</b></label>
						    		<input  style="width:76%; float:left; height: 24px;" type="text" name="phone" id="inputphone">
            		</div>
            		<div>
            				<label for="password" style="width:24%; float:left; margin-top: 7px;"><b>密碼</b></label>
						    		<input style="width:76%; float:left; height: 24px;" type="text" name="password" id="inputpassword">
            		</div>
            		<div>
            				<label for="password" style="width:24%; float:left; margin-top: 7px;"><b>權限</b></label>
										<select class="browser-default custom-select" style="width:76%;">
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
						    <button type="submit" class="btn cancel"  style="height: 48px; padding: 8px;" onclick="closeForm()">取消</button>
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
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
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

	    $(document).ready(function() {

	        $('#dataTable').DataTable( {
	            'processing': true,											//載入時在畫面會出現processing的訊息
	            'serverSide': true,												//啟動server side模式,避免一次載入大量資料(50000)
	            'cache':false,
	            'serverMethod': 'post',
	            //'retrieve': true,
	            'ajax': {
	                "url": '../DeviceHistorySevlet',
	                type: 'POST',
	                dataSrc:'historyVOs',
	                success:(data) => {
										console.log("data : " + data);
			            }
	            },
	             "columns":[
										{"data":"ttime"},
										{"data":"event"}
								]

	            
//	             //"deferLoading": 0,										//預先用ajax載入
//	             order: [[0, "desc"]],											//預設排序資料行(由0算起)
//	             orderMulti : false,
//	             "columns":[
//	 							{"data":"ttime"},
//	 							{"data":"event"}
//	             ],
//	             ajax: {
//	                 method:"post",
//	                 url: "../DeviceHistorySevlet", 
//	                 data: function (d)
//	                 {
//	                 	console.log("d : " + d);
//	                     //d.MyTitle = $("input[name=MyTitle]").val();
//	                     //d.MyMoney = $("input[name=MyMoney]").val();
//	                 }
//	             }
	            
	        } );






	        
	    } );

		function openForm() {
				document.getElementById("myForm").style.display = "block";
		}

		function closeForm() {
				document.getElementById("myForm").style.display = "none";
		}



		$("#updateBtn").click(function(){

			var checked = $('#cbblack').is(":checked");
			var blackstate = 0;
			if(checked == true){
				blackstate = 1;
			}
			
			$.ajax({
				  type: 'POST',                    					//GET or POST
				  url: "../AdminModifyMemberServlet",		//請求的頁面
				  cache: false,                     				//防止抓到快取的回應
				  data: {
						username:$("#username").text(),
						email:$("#inputemail").val(),
						point:$("#inputpoint").val(),
						phone:$("#inputphone").val(),
						password:$("#inputpassword").val(),
						black:blackstate
				  },
				  success: function (jsonObject) {         //當請求成功後此事件會被呼叫
				  	var state = jsonObject.state;
						console.log("state : " + state);
						if(state == "ok"){

							var num = $("#hiddenNumber").val();
							$("#them" + num).text( $("#inputemail").val() );
							$("#thpo" + num).text( $("#inputpoint").val() );
							$("#thph" + num).text( $("#inputphone").val() );
							$("#thpw" + num).text( $("#inputpassword").val() ) ;
							$("#thbl" + num).text( blackstate ) ;
							closeForm();
						}    	
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
				
		});

		
		</script>
</body>

</html>