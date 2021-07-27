<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="com.account.model.AccountVO"%>
<%@page import="com.account.model.AccountService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.device.model.DeviceVO"%>
<%@page import="com.device.model.DeviceService"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
for (AccountVO account : accountVOs) {
	map.put(Integer.valueOf(account.getUid()), account.getUser());
}

for (Iterator<Map.Entry<Integer, String>> entries = map.entrySet().iterator(); entries.hasNext();) {
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

.navbar{
margin-bottom: 0;
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
<!-- 						<div class="card-header py-3" style="padding: 0 12px 0 12px;"> -->
<!-- 							<h4 id="title" class="m-0 font-weight-bold text-primary" -->
<!-- 								style="float: left; padding: 6px 0 0 0;">會員清單</h4> -->
<!-- 								<p id="subTitle" style="color:orange; margin: 6px 0 0 100px;">(如預修改員會資料，請點選該會員任一欄位)</p> -->
<!-- 							<button id="btnBackList" type="button" class="btn btn-warning" -->
<!-- 								style="margin-right: 12px;; float: right; display: none;">返回會員清單</button> -->
<!-- 						</div> -->



								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">設備狀態</h6>
								</div>
								<div class="card-body">
									<table id="dataTable" class="table table-bordered">
										<thead>
											<tr>
												<th style="text-align: center;">#</th>
<!-- 												<th style="text-align: center;">ID</th> -->
												<th style="text-align: center;">設備名稱</th>
												<th style="text-align: center;">狀 態</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${devices}" var="device" varStatus="id">
												<tr scope="row" style="text-align: center;" class="devicelist">
													<td>${id.index + 1}</td>
<%-- 													<td>${device.number}</td> --%>
													<td>${device.location}</td>
													<c:choose>
														<c:when test="${(device.error == 0) and (device.paper < 280)}">
															<td style="color:#009100; font-weight:600;">正 常</td>
<!-- 															<td><div class="greenCircle"></div>	</td> -->

														</c:when>
														<c:when test="${device.error > 0}">
															<td style="color:#EAC100; font-weight:600;">異 常</td>
<!-- 															<td><div class="redCircle"></div></td> -->
														</c:when>
														<c:otherwise>
															<td style="color:#CE0000;  font-weight:600;">故 障</td>
<!-- 															<td><div class="yellowCircle"></div></td> -->
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<div>
														
													</div>
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

	</script>
</body>

</html>