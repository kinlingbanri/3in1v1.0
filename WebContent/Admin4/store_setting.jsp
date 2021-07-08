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

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="./index.jsp">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">複合式加值機<br>系統管理</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider">

                        <!-- Heading -->
            <div class="sidebar-heading">營運管理</div>
            
            <!-- Nav Item - 店家管理 Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>店家管理</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                    		<a class="collapse-item" href="./store_setting.jsp">店家配置</a>
                        <a class="collapse-item" href="./store_device.jsp">設備配置</a>
                        <a class="collapse-item" href="./store_add.jsp">新增店家</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - 設備管理 Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-cog"></i>
<!--                     <i class="fas fa-fw fa-wrench"></i> -->
                    <span>設備管理</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                    		<a class="collapse-item" href="./addvalue_setting.jsp">加值機設定</a>
                    		<a class="collapse-item" href="./addvalue_add.jsp">新增加值機</a>
                    		<a class="collapse-item" href="./device_setting.jsp">消費機設定</a>
                    		<a class="collapse-item" href="./device_add.jsp">新增消費機</a>
                    </div>
                </div>
            </li>
            
            <!-- Nav Item - 會員管理 Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseThree" aria-expanded="true" aria-controls="collapseThree">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>會員管理</span>
                </a>
                <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="./member_setting.jsp">會員設定</a>
                    </div>
                </div>
            </li>
            
                        
            <!-- Nav Item - 伺服器管理 Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFour" aria-expanded="true" aria-controls="collapseFour">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>伺服器管理</span>
                </a>
                <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                    		<a class="collapse-item" href="./server_setting.jsp">參數設定</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">營運數據</div>

            <!-- Nav Item - 店家數據 Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-table"></i>
<!--											<i class="fas fa-fw fa-folder"></i> -->
<!-- 										<i class="fas fa-fw fa-chart-area"></i> -->
										<span>店家數據</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="#">查詢報表</a>
                        <a class="collapse-item" href="#">報表輸出</a>
                    </div>
                </div>
            </li>
            
            <!-- Nav Item - 設備數據 Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSix" aria-expanded="true" aria-controls="collapseSix">
                    <i class="fas fa-fw fa-table"></i>
                    <span>設備數據</span>
                </a>
                <div id="collapseSix" class="collapse" aria-labelledby="headingSix" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="#">查詢報表</a>
                        <a class="collapse-item" href="#">報表輸出</a>
                    </div>
                </div>
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

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Admin</span>
                                <img class="img-profile rounded-circle" src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>簡介
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>登出
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid"  id="divStoreList">
<!-- 								<div class="container-fluid"  id="divStoreList" style="display:none;"> -->

                    <!-- Page Heading -->
<!--                     <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
<!--                     <p class="mb-4">點擊該列任一處，即可進入該店家的設定畫面 -->

                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">店家列表 (點擊該列任一處，即可進入該店家的設定畫面)</h6><p>
                        </div>
                        
                        <!-- Store List -->
                        <div class="card-body">
                            <div class="table-responsive">
                                <table id="dataTable" class="table table-bordered" style="font-size:14px;"width="100%" cellspacing="0">
                                    <thead>
                                        <tr style="text-align:center;">
                                            <th>店名</th>
                                            <th>一次性<br>消費點數</th>
                                            <th>連續性<br>消費點數</th>
                                            <th>優惠一<br>金額/點數</th>
                                            <th>優惠二<br>金額/點數</th>
                                            <th>優惠三<br>金額/點數</th>
                                            <th>狀態</th>
                                        </tr>
                                    </thead>
<!--                                     <tfoot> -->
<!--                                         <tr> -->
<!--                                         <tr> -->
<!--                                             <th>店名</th> -->
<!--                                             <th>一次性消費點數</th> -->
<!--                                             <th>連續性消費點數</th> -->
<!--                                             <th>優惠一:金額/費用</th> -->
<!--                                             <th>優惠二:金額/費用</th> -->
<!--                                             <th>優惠三:金額/費用</th> -->
<!--                                             <th>狀態</th> -->
<!--                                         </tr> -->
<!--                                     </tfoot> -->
                                    <tbody>
                                    
                                    
                                    <c:forEach items="${storeVOs}" var="store" varStatus="id">
																			<tr  style="text-align:center;" id="${store.sid}">
																				<th scope="row" style="text-align: center;">${store.name}</th>
																				<th>${store.single_count}點</th>
																				<th>${store.multi_count}點</th>
																				<th>${store.discount_1_money}元/${store.discount_1_point}點</th>
																				<th>${store.discount_2_money}元/${store.discount_2_point}點</th>
																				<th>${store.discount_3_money}元/${store.discount_3_point}點</th>
																				<th>
																					<c:choose>
																						<c:when test="${store.pause == 1}">停用</c:when>
																						<c:when test="${store.pause == 0}">啟用</c:when>
																						<c:otherwise></c:otherwise>
																					</c:choose>
																				</th>
															   			</c:forEach>
															   			
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- /.container-fluid -->
                
                
                



                <!-- Begin Page Content -->
<!--                 <div class="container-fluid" id="divStoreConfig""> -->
                <div class="container-fluid" id="divStoreConfig" style="display:none;">

                    <!-- Page Heading -->
<!--                     <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
<!--                     <p class="mb-4">點擊該列任一處，即可進入該店家的設定畫面 -->
                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h4 class="m-0 font-weight-bold text-primary" style="float:left;">設定店家</h4>
                            <button id="btnBack" type="button" class="btn btn-warning" style="margin-right:12px;; float:right;">回到店家列表</button>
                            <input type="hidden" name="SID" value="" id="inputSid">
                        </div>
                        
                         <!-- Store Config -->
                        <div class="container">
                            <div class="row">
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">店名</h4>
														      	<h6 id="textStoreName"></h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>預更換店名</h6>
            												<input type="text" class="form-control configInput" id="inputStoreName">
            												<button type="button" class="btn btn-success" style="margin:12px 0 6px 0;" id="btnName">確認</button>
														    </div>
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">一次性消費點數</h4>
														      	<h6 id="textSinglePoint">目前消費點數:</h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>預更換消費點數</h6>
            												<input type="text" class="form-control configInput" id="inputSinglePoint">
            												<button type="button" class="btn btn-success" style="margin:12px 0 6px 0;" id="btnSingle">確認</button>
														    </div>
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">連續性消費點數</h4>
														      	<h6 id="textMultiPoint">目前消費點數:</h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>預更換消費點數</h6>
            												<input type="text" class="form-control configInput" id="inputMultiPoint">
            												<button type="button" class="btn btn-success" style="margin:12px 0 6px 0;" id="btnMulti">確認</button>
														    </div>
														</div>

                            <div class="row">
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">優惠方案一</h4>
														      	<h6 id="textDiscount1Dallas">目前設定金額:</h6>
														      	<h6 id="textDiscount1Point">目前設定點數:</h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>預設定金額</h6>            												
            												<input type="text" class="form-control configInput" id="inputDiscount1Dallas">
            												<h6 style="margin: 0.5rem;">預設定點數</h6>            												
            												<input type="text" class="form-control configInput" id="inputDiscount1Point">
            												<button type="button" class="btn btn-success" style="margin:12px 0 6px 0;" id="btnDiscount1">確認</button>
														    </div>
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">優惠方案二</h4>
														      	<h6 id="textDiscount2Dallas">目前設定金額:</h6>
														      	<h6 id="textDiscount2Point">目前設定點數:</h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>預設定金額</h6>            												
            												<input type="text" class="form-control configInput" id="inputDiscount2Dallas">
            												<h6 style="margin: 0.5rem;">預設定點數</h6>            												
            												<input type="text" class="form-control configInput" id="inputDiscount2Point">
            												<button type="button" class="btn btn-success" style="margin:12px 0 6px 0;" id="btnDiscount2">確認</button>
														    </div>
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">優惠方案三</h4>
														      	<h6 id="textDiscount3Dallas">目前設定金額:</h6>
														      	<h6 id="textDiscount3Point">目前設定點數:</h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>預設定金額</h6>            												
            												<input type="text" class="form-control configInput" id="inputDiscount3Dallas">
            												<h6 style="margin: 0.5rem;">預設定點數</h6>            												
            												<input type="text" class="form-control configInput" id="inputDiscount3Point">
            												<button type="button" class="btn btn-success" style="margin:12px 0 6px 0;" id="btnDiscount3">確認</button>
														    </div>
														</div>
														
														
														<div class="row" style="width: 33.6%; margin: 6px -.75rem 12px -.75rem;">
														    <div class="col-md4 configBlock" style="margin: 0 0 0 0.4rem; width: 100%;">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">店家狀態</h4>
																		<input id="cbStoreState" type="checkbox" data-toggle="toggle" data-onstyle="success" data-on="啟用" data-off="停用"  style="margin-bottom:6px;" checked>
																		<style>.toggle{margin-bottom:6px;}</style>
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

		$(document).ready(function(){
			var table = $('#dataTable').DataTable();
		     
		    $('#dataTable tbody').on('click', 'tr', function () {
		    		var id = table.row( this ).id();
			    	$("#inputSid").val(id);
			    	
			    	
		        var data = table.row( this ).data();
		        //alert( 'You clicked on '+ data[0] +'\'s row' );
		        $("#textStoreName").text(data[0]);
		        $("#textSinglePoint").text( "目前消費點數:" + data[1]);
		        $("#textMultiPoint").text("目前消費點數:" + data[2]);
		        var str = data[3].split("/");
		        
		        $("#textDiscount1Dallas").text("目前設定金額:" + str[0]);
		        $("#textDiscount1Point").text("目前設定點數:" + str[1]);
		        var str = data[4].split("/");
		        $("#textDiscount2Dallas").text("目前設定金額:" + str[0]);
		        $("#textDiscount2Point").text("目前設定點數:" + str[1]);
		        var str = data[5].split("/");
		        $("#textDiscount3Dallas").text("目前設定金額:" + str[0]);
		        $("#textDiscount3Point").text("目前設定點數:" + str[1]);
		        var pause = data[6];
		        
		        if(pause =="啟用"){
		        	$('#cbStoreState').bootstrapToggle('on')
				    }else if(pause == "停用"){
				    	$('#cbStoreState').bootstrapToggle('off')
					  }
		    } );
		});
			
			//新增datatable row
			function addDatatble(){
				var table = $('#dataTable').DataTable();
				var rowNode = table.row.add( [ 'Tiger Nixon', 'System Architect', 'Edinburgh', 54, '2011/04/25', '$320,800' ] ).draw().node();
				$( rowNode ) .css( 'color', 'red' ).animate( { color: 'black' } );
			}

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


		  $('#dataTable tbody').on('click', 'tr', function (event) {

			  /*
				var id = $(this).attr('id');
		    var num =  id.substring(2, id.length);
		    $("#hiddenNumber").val(num);
	    	
				$("#username").text( $("#thun" + num).text() ) ;
				$("#inputemail").val( $("#them" + num).text() );
				$("#inputpoint").val( $("#thpo" + num).text() );
				$("#inputphone").val( $("#thph" + num).text() );
				$("#inputpassword").val( $("#thpw" + num).text() );

				var blackVal = $("#thbl" + num).text();
				if(blackVal == 0){
					$("#cbblack").prop('checked', false);
				}else if(blackVal == 1){
					$("#cbblack").prop('checked', true);
				}
				*/

			  document.getElementById("divStoreList").style.display = "none";
				document.getElementById("divStoreConfig").style.display = "block";
	    });

		  $("#btnBack").click(function(){
			  document.getElementById("divStoreList").style.display = "block";
				document.getElementById("divStoreConfig").style.display = "none";
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

			$("#btnName").click(function() {
				var name = $("#inputStoreName").val();
				updateStore("name", name, "");
			});

			$("#btnSingle").click(function() {
				var single = $("#inputSinglePoint").val();
				updateStore("single", single, "");
			});

			$("#btnMulti").click(function() {
				var multi = $("#inputMultiPoint").val();
				updateStore("multi", multi, "");
			});

			$("#btnDiscount1").click(function() {
				var discount1Money = $("#inputDiscount1Dallas").val();
				var discount1Point = $("#inputDiscount1Point").val();
				updateStore("discount1", discount1Money, discount1Point);
			});

			$("#btnDiscount2").click(function() {
				var discount2Money = $("#inputDiscount2Dallas").val();
				var discount2Point = $("#inputDiscount2Point").val();
				updateStore("discount2", discount2Money, discount2Point);
			});

			$("#btnDiscount3").click(function() {
				var discount3Money = $("#inputDiscount3Dallas").val();
				var discount3Point = $("#inputDiscount3Point").val();
				updateStore("discount3", discount3Money, discount3Point);
			});

			

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

		</script>
</body>

</html>