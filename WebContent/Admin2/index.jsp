<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="com.account.model.AccountVO"%>
<%@page import="com.account.model.AccountService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.device.model.DeviceVO"%>
<%@page import="com.device.model.DeviceService"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	DeviceService deviceService = new DeviceService();
	List<DeviceVO> devices = deviceService.getAll();
	for (DeviceVO device : devices) {
		System.out.print(device.getDid() + ",");
		System.out.print(device.getNumber() + ",");
		System.out.print(device.getCoin() + ",");
		System.out.print(device.getPaper() + ",");
		System.out.print(device.getLocation() + ",");
		System.out.print(device.getRefund() + ",");
		System.out.print(device.getUid() + ",");
		System.out.print(device.getStatus() + ",");
		System.out.print(device.getError() + ",");
		System.out.print(device.getMachid() + ",");
		System.out.print(device.getFreecount() + ",");
		System.out.println(device.getFreecountset());
	}

	request.setAttribute("devices", devices);
	
	Map<Integer, String> map = new HashMap<Integer, String>();

	MemService memSvc = new MemService();
	List<MemVO> memberVOs = memSvc.getAll();
	
	for (MemVO mem : memberVOs) {
		//nameList.add(mem.getUsername());
		
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
	
	AccountService accountService = new AccountService();
	List<AccountVO> accountVOs = accountService.getAll();
	for(AccountVO account : accountVOs){
		map.put( Integer.valueOf(account.getUid()), account.getUser());
	}
	
	for(Iterator<Map.Entry<Integer, String>> entries = map.entrySet().iterator(); entries.hasNext(); ){
		Map.Entry<Integer, String> entry = entries.next();
		System.out.println(entry.getKey() + ":" + entry.getValue());
	}
	
	request.setAttribute("memberVOs", memberVOs);	
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

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    
    <style>
				body{
					font-family: Microsoft JhengHei;
				}			
				.devicelist:hover {
					  background: #f2f3ff;
					  outline: none;
					  cursor: pointer;
				}			
				td {
				  text-align: center;
				  vertical-align: middle;
				}				
				.redCircle{
					width:16px;height:16px;
					border-radius:50%;
					background-color:red;
					margin: 0px auto;
				}			
				.greenCircle{
					width:16px;height:16px;
					border-radius:50%;
					background-color:green;
					margin: 0px auto;
				}			
				.yellowCircle{
					width:16px;height:16px;
					border-radius:50%;
					background-color:yellow;
					margin: 0px auto;
				}
				
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
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>設備</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
<!--                         <h6 class="collapse-header">Login Screens:</h6> -->
                        <a class="collapse-item" href="./devices.jsp">設備管理</a>
<!--                         <a class="collapse-item" href="register.html">Register</a> -->
<!--                         <a class="collapse-item" href="forgot-password.html">Forgot Password</a> -->
<!--                         <div class="collapse-divider"></div> -->
<!--                         <h6 class="collapse-header">Other Pages:</h6> -->
<!--                         <a class="collapse-item" href="404.html">404 Page</a> -->
<!--                         <a class="collapse-item" href="blank.html">Blank Page</a> -->
                    </div>
                </div>
            </li>
						
						<!-- Nav Item - Members -->
            <li class="nav-item">
                <a class="nav-link" href="./members.jsp">
                    <i class="fas fa-fw fa-table"></i>
                    <span>會員</span>
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
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Admin</span>
<!--                                 <img class="img-profile rounded-circle" src="img/undraw_profile.svg"> -->
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

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                收益 (當月)</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>
                                        </div>
<!--                                         <div class="col-auto"> -->
<!--                                             <i class="fas fa-calendar fa-2x text-gray-300"></i> -->
<!--                                         </div> -->
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                 收益 (年度)</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">$215,000</div>
                                        </div>
<!--                                         <div class="col-auto"> -->
<!--                                             <i class="fas fa-dollar-sign fa-2x text-gray-300"></i> -->
<!--                                         </div> -->
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">加值 (當月)
                                            </div>
                                            <div class="row no-gutters align-items-center">
                                                <div class="col-auto">
                                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">$72,000</div>
                                                </div>
                                                <div class="col">
                                                    <div class="progress progress-sm mr-2">
                                                        <div class="progress-bar bg-info" role="progressbar"
                                                            style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                                            aria-valuemax="100"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
<!--                                         <div class="col-auto"> -->
<!--                                             <i class="fas fa-clipboard-list fa-2x text-gray-300"></i> -->
<!--                                         </div> -->
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Pending Requests Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-warning shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                加值 (當月)</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">$560,000</div>
                                        </div>
<!--                                         <div class="col-auto"> -->
<!--                                             <i class="fas fa-comments fa-2x text-gray-300"></i> -->
<!--                                         </div> -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Content Column -->
                        <div class="col-lg-6 mb-4">

                            <!-- Project Card Example -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">設備狀態</h6>
                                </div>
                                <div class="card-body">
																		<table class="table table-bordered">
																			<thead>
																				<tr>
																					<th style="text-align: center;">ID</th>
																					<th style="text-align: center;">設備名稱</th>
																					<th style="text-align: center;">狀態</th>
																				</tr>
																			</thead>
																			<tbody>										
																				<c:forEach items="${devices}" var="device" varStatus="id">
																					<tr  scope="row" style="text-align: center;" class="devicelist">
																						<td>${device.number}</td>
																						<td>${device.location}</td>												
																						<c:choose>
																						    <c:when test="${(device.error == 0) and (device.paper < 280)}">
																						       <td><div class="greenCircle"></div></td>
																						    </c:when>
																						    <c:when test="${device.error > 0}">
																						       	<td><div class="redCircle"></div></td>
																						    </c:when>
																						    <c:otherwise>
																						       <td><div class="yellowCircle"></div></td>
																						    </c:otherwise>
																						</c:choose>
																			    </tr>
																	   		</c:forEach>
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

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
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

</body>

</html>