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
    </style>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

 
				<%@ include file="./leftmenu.jsp" %>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

 								<%@ include file="./topbar.jsp" %>

                <!-- Begin Page Content -->
                <div class="container-fluid"  id="divMemberList">
<!-- 								<div class="container-fluid"  id="divMemberList" style="display:none;"> -->

                    <!-- Page Heading -->
<!--                     <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
<!--                     <p class="mb-4">點擊該列任一處，即可進入該店家的設定畫面 -->

                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">會員列表 (點擊該列任一處，即可進入該會員的設定畫面)</h6><p>
                        </div>
                        
                        <!-- Store List -->
                        <div class="card-body">
                            <div class="table-responsive">
                                <table id="dataTable" class="table table-bordered" style="font-size:14px;"width="100%" cellspacing="0">
                                    <thead>
                                        <tr style="text-align:center;">
                                            <th>帳號</th>
                                            <th>手機號碼</th>
                                            <th>密碼</th>
                                            <th>Email</th>
                                            <th>點數</th>
                                            <th>黑名單</th>
                                        </tr>
                                    </thead>
<!--                                     <tfoot> -->
<!--                                         <tr> -->
<!--                                             <th>帳號</th> -->
<!--                                             <th>電話號碼</th> -->
<!--                                             <th>密碼</th> -->
<!--                                             <th>Email</th> -->
<!--                                             <th>點數</th> -->
<!--                                             <th>黑名單</th> -->
<!--                                         </tr> -->
<!--                                     </tfoot> -->
                                    <tbody>
                                        <tr  style="text-align:center;">
                                            <th>Kim</th>
                                            <th>0935276906</th>
                                            <th>1234</th>
                                            <th>Kim2@hotmail.com</th>
                                            <th>1300</th>
                                            <th>否</th>
                                        </tr>
                                        <tr style="text-align:center;">
                                            <th>金城五</th>
                                            <th>0935276906</th>
                                            <th>234</th>
                                            <th>Kim2@hotmail.com</th>
                                            <th>2460</th>
                                            <th>否</th>
                                        </tr>
                                        <tr style="text-align:center;">
                                            <th>Van007</th>
                                            <th>0935276906</th>
                                            <th>123</th>
                                            <th>Van@tongya.com.tw</th>
                                            <th>1300</th>
                                            <th>是</th>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- /.container-fluid -->
                
                
                



                <!-- Begin Page Content -->
<!--                 <div class="container-fluid" id="divMemberConfig""> -->
                <div class="container-fluid" id="divMemberConfig" style="display:none;">

                    <!-- Page Heading -->
<!--                     <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
<!--                     <p class="mb-4">點擊該列任一處，即可進入該店家的設定畫面 -->
                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h4 class="m-0 font-weight-bold text-primary" style="float:left;">Kim</h4>
                            <button id="btnBack" type="button" class="btn btn-warning" style="margin-right:12px;; float:right;">回到店家列表</button>
                        </div>
                        
                         <!-- Store Config -->
                        <div class="container">
                            <div class="row">
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">帳號</h4>
														      	<h6 id="textMemberName">Kim</h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>新帳號</h6>
            												<input type="text" class="form-control configInput" id="inputMemberName">
            												<button type="button" class="btn btn-success" style="margin:12px 0 6px 0;">確認</button>
														    </div>
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">手機號碼</h4>
														      	<h6 id="textMemberPhone">目前號碼:</h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>新手機號碼</h6>
            												<input type="text" class="form-control configInput" id="inputMemberPhone">
            												<button type="button" class="btn btn-success" style="margin:12px 0 6px 0;">確認</button>
														    </div>
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">密碼</h4>
														      	<h6 id="textMemberPwd">目前密碼:</h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>新密碼</h6>
            												<input type="text" class="form-control configInput" id="inputMemberPwd">
            												<button type="button" class="btn btn-success" style="margin:12px 0 6px 0;">確認</button>
														    </div>
														</div>

                            <div class="row">
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">Email</h4>
														      	<h6 id="textMemberEmail">目前Email:</h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>新Email</h6>            												
            												<input type="text" class="form-control configInput" id="inputMemberEmail">
            												<button type="button" class="btn btn-success" style="margin:12px 0 6px 0;">確認</button>
														    </div>
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">點數</h4>
														      	<h6 id="textMemberPoint">目前點數:</h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>新點數</h6>
            												<input type="text" class="form-control configInput" id="inputMemberPoint">
            												<button type="button" class="btn btn-success" style="margin:12px 0 6px 0;">確認</button>
														    </div>
														    <div class="col-md configBlock">
														      	<h4 style="font-weight:900; margin-top: 6px; color:#FFCC22;">黑名單</h4>
														      	<h6 id="textMemberBlack">目前狀態:</h6>
            												<hr class="sidebar-divider d-none d-md-block" style="width:90%; color:#FFF;">
            												<h6>新狀態</h6>     
            												<input id="cbMemberBlack" type="checkbox" data-toggle="toggle" data-onstyle="success" data-on="啟用" data-off="停用"  style="margin-bottom:6px;" checked>
																		<style>.toggle{margin-bottom:6px;  min-width: 5rem;}</style>
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

		$(function() {
			$(".toggle").css({ "min-width": "5rem"});
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

			  document.getElementById("divMemberList").style.display = "none";
				document.getElementById("divMemberConfig").style.display = "block";
	    });

		  $("#btnBack").click(function(){
			  document.getElementById("divMemberList").style.display = "block";
				document.getElementById("divMemberConfig").style.display = "none";
			});

		</script>
</body>

</html>