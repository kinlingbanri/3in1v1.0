<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Tables</title>

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
                        <a class="collapse-item" href="./member_setting.jsp.jsp">會員設定</a>
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
                    		<a class="collapse-item" href="./server_setting">參數設定</a>
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
<!--                 <div class="container-fluid"  id="divStoreList"> -->
								<div class="container-fluid"  id="divStoreList" style="display:none;">

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
                                            <th>所屬加值機</th>
                                            <th>所屬消費機</th>
                                        </tr>
                                    </thead>
<!--                                     <tfoot> -->
<!--                                         <tr> -->
<!--                                         <tr> -->
<!--                                             <th>店名</th> -->
<!--                                             <th>加值機名稱</th> -->
<!--                                             <th>消費機名稱</th> -->
<!--                                         </tr> -->
<!--                                     </tfoot> -->
                                    <tbody>
                                    		<tr style="text-align:center;">
                                            <th>樹林站前店</th>
                                            <th>加值機1號; 加值機2號;</th>
                                            <th>
                                            		<p style="margin:0px;">
                                            			洗衣機1號; 洗衣機2號;
                                            		</p>
                                            		<p style="margin:0px;">
                                            			烘衣機1號; 烘衣機2號; 烘衣機3號;
                                            		</p>
                                            </th>
                                        </tr>
                                        <tr style="text-align:center;">
                                            <th>樹林站後店</th>
                                            <th>加值機1號; 加值機2號;</th>
                                            <th>
                                            		<p style="margin:0px;">
                                            			洗衣機1號; 洗衣機2號; 洗衣機3號; 洗衣機4號;
                                            		</p>
                                            		<p style="margin:0px;">
                                            			烘衣機1號; 烘衣機2號; 烘衣機3號; 烘衣機4號; 烘衣機5號;
                                            		</p>
                                            </th>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- /.container-fluid -->
                
                
                



                <!-- Begin Page Store Add -->
                <div class="container-fluid" id="divStoreAdd">
<!--                 <div class="container-fluid" id="divStoreAdd" style="display:none;"> -->

                    <!-- Page Heading -->
<!--                     <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
<!--                     <p class="mb-4">點擊該列任一處，即可進入該店家的設定畫面 -->
                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h4 class="m-0 font-weight-bold text-primary" style="float:left;">樹林站前店</h4>
                            <button id="btnBack" type="button" class="btn btn-warning" style="margin-right:12px;; float:right;">回到店家列表</button>
                        </div>
                        
                        <!-- AddValue Machine add -->
                        <div class="container" style="background-color:#FFF7FB; padding-bottom:20px;">
                            <div style="margin:18px 0 6px 12px;">
                            		<h4 style="font-weight:900; float:left; margin-top:8px;">加值機</h4>
<!--                             		<button type="button" class="btn btn-default btn-circle" style="background-color:#0B0;"> -->
<!--                             				<i class="fa fa-plus"></i> -->
<!--                             		</button> -->
                            		<div class="btn-group" style="float:left; margin-left:18px;">
																	  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
																	  		按此新增加值機
																	  </button>
																	  <div class="dropdown-menu">
																		    <a class="dropdown-item" href="#">加值機3號</a>
																		    <a class="dropdown-item" href="#">加值機4號</a>
																		    <a class="dropdown-item" href="#">加值機5號</a>
																	  </div>
																</div>
                            </div>
                            <div>
		                            <table class="table table-hover" style="text-align: center;">
																	  <thead>
																	    <tr>																	      
																	      <th scope="col" style="padding: 12px 0 18px 0;">名稱</th>
																	      <th scope="col" style="padding: 12px 0 18px 0;">QR code</th>
																	      <th scope="col">
																	      		<button type="button" class="btn btn-primary">批次下載QR code</button>
																	      </th>
																	      <th scope="col"></th>
																	    </tr>
																	  </thead>
																	  <tbody>
																	    <tr>
																	      <td style="padding: 1.2rem 0 0 0;">加值機1號</td>
																	      <td><button type="button" class="btn btn-primary">下載QR code</button></td>
																	      <td>
																	      		<input type="checkbox" class="form-check-input" value="" style="height:20px; width:20px; margin: 9px 0px 0 -0.625rem;">
																	      </td>
																	      <td><button type="button" class="btn btn-danger" style="margin-right:12px;">刪除</button></td>
																	    </tr>
																	    <tr>
																	      <td style="padding: 1.2rem 0 0 0;">加值機2號</td>
																	      <td><button type="button" class="btn btn-primary">下載QR code</button></td>
																	      <td>
																	      		<input type="checkbox" class="form-check-input" value="" style="height:20px; width:20px; margin: 9px 0px 0 -0.625rem;">
																	      </td>
																	      <td><button type="button" class="btn btn-danger" style="margin-right:12px;">刪除</button></td>
																	    </tr>
																	  </tbody>
																</table>
                            </div>
                        </div>
                        
                        
                      	<!-- Consumption Machine add -->
                        <div class="container" style="background-color:#ECF5FF;">
                            <div style="margin:18px 0 6px 12px;">
                            		<h4 style="font-weight:900; float:left; margin-top:8px;">消費機</h4>
                            		<div class="btn-group" style="float:left; margin-left:18px;">
																	  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
																	  		按此新增消費機
																	  </button>
																	  <div class="dropdown-menu">
																		    <a class="dropdown-item" href="#">洗衣機3號</a>
																		    <a class="dropdown-item" href="#">洗衣機4號</a>
																		    <a class="dropdown-item" href="#">洗衣機5號</a>
																		    <a class="dropdown-item" href="#">烘衣機4號</a>
																		    <a class="dropdown-item" href="#">烘衣機5號</a>
																		    <a class="dropdown-item" href="#">烘衣機6號</a>
																	  </div>
																</div>
                            </div>
                            <div>
		                            <table class="table table-hover" style="text-align: center;">
																	  <thead>
																	    <tr>																	      
																	      <th scope="col" style="padding: 12px 0 18px 0;">名稱</th>
																	      <th scope="col" style="padding: 12px 0 18px 0;">QR code</th>
																	      <th scope="col">
																	      		<button type="button" class="btn btn-primary">批次下載QR code</button>
																	      </th>
																	      <th scope="col"></th>
																	    </tr>
																	  </thead>
																	  <tbody>
																	    <tr>
																	      <td style="padding: 1.2rem 0 0 0;">洗衣機1號</td>
																	      <td><button type="button" class="btn btn-primary">下載QR code</button></td>
																	      <td>
																	      		<input type="checkbox" class="form-check-input" value="" style="height:20px; width:20px; margin: 9px 0px 0 -0.625rem;">
																	      </td>
																	      <td><button type="button" class="btn btn-danger" style="margin-right:12px;">刪除</button></td>
																	    </tr>
																	    <tr>
																	      <td style="padding: 1.2rem 0 0 0;">洗衣機2號</td>
																	      <td><button type="button" class="btn btn-primary">下載QR code</button></td>
																	      <td>
																	      		<input type="checkbox" class="form-check-input" value="" style="height:20px; width:20px; margin: 9px 0px 0 -0.625rem;">
																	      </td>
																	      <td><button type="button" class="btn btn-danger" style="margin-right:12px;">刪除</button></td>
																	    </tr>
																	    <tr>
																	      <td style="padding: 1.2rem 0 0 0;">烘衣機1號</td>
																	      <td><button type="button" class="btn btn-primary">下載QR code</button></td>
																	      <td>
																	      		<input type="checkbox" class="form-check-input" value="" style="height:20px; width:20px; margin: 9px 0px 0 -0.625rem;">
																	      </td>
																	      <td><button type="button" class="btn btn-danger" style="margin-right:12px;">刪除</button></td>
																	    </tr>
																	    <tr>
																	      <td style="padding: 1.2rem 0 0 0;">烘衣機2號</td>
																	      <td><button type="button" class="btn btn-primary">下載QR code</button></td>
																	      <td>
																	      		<input type="checkbox" class="form-check-input" value="" style="height:20px; width:20px; margin: 9px 0px 0 -0.625rem;">
																	      </td>
																	      <td><button type="button" class="btn btn-danger" style="margin-right:12px;">刪除</button></td>
																	    </tr>	
																	    <tr>
																	      <td style="padding: 1.2rem 0 0 0;">烘衣機3號</td>
																	      <td><button type="button" class="btn btn-primary">下載QR code</button></td>
																	      <td>
																	      		<input type="checkbox" class="form-check-input" value="" style="height:20px; width:20px; margin: 9px 0px 0 -0.625rem;">
																	      </td>
																	      <td><button type="button" class="btn btn-danger" style="margin-right:12px;">刪除</button></td>
																	    </tr>
																	    
																	    
																	  </tbody>
																</table>
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
				document.getElementById("divStoreAdd").style.display = "block";
	    });

		  $("#btnBack").click(function(){
			  document.getElementById("divStoreList").style.display = "block";
				document.getElementById("divStoreAdd").style.display = "none";
			});

		</script>
</body>

</html>