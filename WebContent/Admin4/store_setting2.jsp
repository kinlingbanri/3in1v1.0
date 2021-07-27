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

				
				<%@ include file="./leftmenu.jsp" %>
        

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <%@ include file="./topbar.jsp" %>

                <!-- Begin Page Content -->
                <div class="container-fluid">
<!-- 								<div class="container-fluid"  id="divStoreList" style="display:none;"> -->

                    <!-- Page Heading -->
<!--                     <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
<!--                     <p class="mb-4">點擊該列任一處，即可進入該店家的設定畫面 -->

                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                           <h4 id="title" class="m-0 font-weight-bold text-primary" style="float:left;">店家配置 - 店家列表</h6>
								           <button id="btnNextConsumption" type="button" class="btn btn-primary" style="margin-right:12px;; float:right; display:none;">設定消費機</button>
								           <button id="btnBackAddvalue" type="button" class="btn btn-primary" style="margin-right:12px;; float:right; display:none;">設定加值機</button>
								           <button id="btnBackList" type="button" class="btn btn-warning" style="margin-right:12px;; float:right; display:none;">返回店家列表</button>
								           
								           <button id="btnUpdateAddvalue" type="button" class="btn btn-success" style="margin-right:12px;; float:right; display:none;">更新加值機</button>								           
								           <button id="btnUpdtateConsumption" type="button" class="btn btn-success" style="margin-right:12px;; float:right; display:none;">更新消費機</button>
								           
                        </div>
                        
                   
                        
                        <!-- Store List -->
                        <div class="card-body" id="divStoreList">
<!--                         <div class="card-body" id=""divStoreList"" style="display:none;"> -->
                            <div class="table-responsive">
                                <table id="dataTable" class="table table-bordered" style="font-size:14px;"width="100%" cellspacing="0">
                                    <thead>
                                        <tr style="text-align:center;">
                                        		<th>#</th>
                                            <th>店名</th>
                                            <th>消費機數量</th>
<!--                                             <th>狀態</th> -->
                                            <th></th>
                                        </tr>
                                    </thead>
<!--                                     <tfoot> -->
<!--                                         <tr> -->
<!--                                             <th>店名</th> -->
<!--                                             <th>消費機數量</th>
<!--                                             <th>狀態</th> -->
<!-- 																						<th></th> -->
<!--                                         </tr> -->
<!--                                     </tfoot> -->
                                    <tbody>
                                    
                                    
                                    <c:forEach items="${storeVOs}" var="store" varStatus="id">
																			<tr  style="text-align:center;" id="${store.sid}">
																				<th scope="row" style="text-align: center;">${id.index + 1}</th>
																				<th>${store.name}</th>
																				<th>7台</th>
<!-- 																				<th> -->
<%-- 																					<c:choose> --%>
<%-- 																						<c:when test="${store.pause == 1}">停用</c:when> --%>
<%-- 																						<c:when test="${store.pause == 0}">啟用</c:when> --%>
<%-- 																						<c:otherwise></c:otherwise> --%>
<%-- 																					</c:choose> --%>
<!-- 																				</th> -->
																				<th style="padding: 3px 0 0 0;">
																   				<button id="btnSid" type="button" class="btn btn-warning modify">修改</button>
																   			</th>
															   			</c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                        
                        
                        
                        

                        <!-- Store addvalue setting -->
<!--                         <div class="container" id="divAddvalueSetting"> -->
                        <div class="container" id="divAddvalueSetting" style="display:none;">
                            <div class="row">
                            		<div style="width:100%;">
                            				<h4 style="float:left; margin-top: 12px;">樹林站前店加值機</h4>
                            				<div class="btn-group" style="margin-top: 8px; float:right;">
																			  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin-bottom:12px;">
																			  		按此新增加值機
																			  </button>
																			  <div class="dropdown-menu">
																				    <a class="dropdown-item" href="#">加值機3號</a>
																				    <a class="dropdown-item" href="#">加值機4號</a>
																				    <a class="dropdown-item" href="#">加值機5號</a>
																			  </div>
																		</div>
                            		</div>

																<table id="dataTableAddvalue" class="table table-bordered" style="font-size:14px;" width="100%" cellspacing="0">
                                    <thead>
                                        <tr style="text-align:center;">
                                        		<th><button id="btnSid" type="button" class="btn btn-danger">移除</button></th>
                                        		<th>儲值金額</th>
                                            <th>兌換點數</th>
                                        </tr>
                                    </thead>
<!--                                     <tfoot> -->
<!--                                         		<th><button id="btnSid" type="button" class="btn btn-btn-danger">移除</button></th> -->
<!--                                         		<th>儲值金額</th> -->
<!--                                             <th>兌換點數</th> -->
<!--                                     </tfoot> -->
                                    <tbody>
                                        <tr  style="text-align:center;">
                                        		<th style="padding: 1rem;">優惠方案一</th>
                                        		<th><input type="text" class="form-control configInput" id="c-1" value="100"></th>
                                            <th><input type="text" class="form-control configInput" id="c-1" value="120"></th>
                                        </tr>
                                        <tr  style="text-align:center;">
                                        		<th style="padding: 1rem;">優惠方案二</th>
                                        		<th><input type="text" class="form-control configInput" id="c-1" value="500"></th>
                                            <th><input type="text" class="form-control configInput" id="c-1" value="650"></th>
                                        </tr>
                                        <tr  style="text-align:center;">
                                        		<th style="padding: 1rem;">優惠方案三</th>
                                        		<th><input type="text" class="form-control configInput" id="c-1" value="1000"></th>
                                            <th><input type="text" class="form-control configInput" id="c-1" value="1400"></th>
                                        </tr>
                                    </tbody>
                                </table>
                            
														    
														</div>
                        </div>

                        <!-- Store constumtion setting -->
<!--                         <div class="container" id="divConsumptionSetting"> -->
                        <div class="container" id="divConsumptionSetting" style="display:none;">
                        
                        
                                <div style="width:100%;">
                            				<h4 style="float:left; margin-top: 12px;">樹林站前店消費機</h4>
                            				<div class="btn-group" style="margin-top: 8px; float:right;">
																			  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin-bottom:12px;">
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
   
																
																
																
																
                                <table id="dataTableConsumption" class="table table-bordered" style="font-size:14px;" width="100%" cellspacing="0">
                                    <thead>
                                        <tr style="text-align:center;">
                                        		<th>#</th>
                                            <th>名稱</th>
                                            <th>免費服務次數</th>
<!--                                             <th>設備ID</th> -->
<!--                                             <th>狀態</th> -->
<!--                                         		<th></th> -->
                                        </tr>
                                    </thead>
<!--                                     <tfoot> -->
<!-- 																						<th>#</th> -->
<!-- 																						<th>名稱</th> -->
<!--                                            	<th>免費服務次數</th> -->
<!-- 																						<th>設備ID</th> -->
<!-- 																						<th>狀態</th> -->
<!--                                         		<th></th> -->

<!--                                     </tfoot> -->
                                    <tbody>
                                        <tr  style="text-align:center;">
                                        		<th style="padding: 1rem;">1</th>
                                            <th style="padding: 1rem;">洗衣機1號</th>
                                            <th style="padding: 0.5rem;">
                                            		<input type="text" class="form-control configInput" id="c-1" value="10">
                                            </th>
<!--                                             <th>1</th> -->
<!--                                             <th>正常</th> -->
                                        		<th style="padding: 0.5rem;">
                                        				<button id="btnSid" type="button" class="btn btn-danger">移除</button>
                                        		</th>
                                        </tr>
                                        <tr style="text-align:center;">
                                        		<th style="padding: 1rem;">2</th>
                                            <th style="padding: 1rem;">洗衣機2號</th>
                                            <th style="padding: 0.5rem;">
                                            		<input type="text" class="form-control configInput" id="c-1" value="10">
                                            </th>
<!--                                             <th style="padding: 1rem;">1</th> -->
<!--                                             <th>正常</th> -->
                                        		<th style="padding: 0.5rem;">
                                        				<button id="btnSid" type="button" class="btn btn-danger">移除</button>
                                        		</th>
                                        </tr>
                                        <tr  style="text-align:center;">
                                        		<th style="padding: 1rem;">3</th>
                                            <th style="padding: 1rem;">烘衣機1號</th>
                                            <th style="padding: 0.5rem;">
                                            		<input type="text" class="form-control configInput" id="c-1" value="10">
                                            </th>
<!--                                             <th>1</th> -->
<!--                                             <th>正常</th> -->
                                        		<th style="padding: 0.5rem;">
                                        				<button id="btnSid" type="button" class="btn btn-danger">移除</button>
                                        		</th>
                                        </tr>
                                        <tr style="text-align:center;">
                                        		<th style="padding: 1rem;">4</th>
                                            <th style="padding: 1rem;">烘衣機2號</th>
                                            <th style="padding: 0.5rem;">
                                            		<input type="text" class="form-control configInput" id="c-1" value="10">
                                            </th>
<!--                                             <th>1</th> -->
<!--                                             <th>正常</th> -->
                                        		<th style="padding: 0.5rem;">
                                        				<button id="btnSid" type="button" class="btn btn-danger">移除</button>
                                        		</th>
                                        </tr>
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
// 		    		var id = table.row( this ).id();
// 			    	$("#inputSid").val(id);
			    	
			    	
// 		        var data = table.row( this ).data();
// 		        //alert( 'You clicked on '+ data[0] +'\'s row' );
// 		        $("#textStoreName").text(data[0]);
// 		        $("#textSinglePoint").text( "目前消費點數:" + data[1]);
// 		        $("#textMultiPoint").text("目前消費點數:" + data[2]);
// 		        var str = data[3].split("/");
		        
// 		        $("#textDiscount1Dallas").text("目前設定金額:" + str[0]);
// 		        $("#textDiscount1Point").text("目前設定點數:" + str[1]);
// 		        var str = data[4].split("/");
// 		        $("#textDiscount2Dallas").text("目前設定金額:" + str[0]);
// 		        $("#textDiscount2Point").text("目前設定點數:" + str[1]);
// 		        var str = data[5].split("/");
// 		        $("#textDiscount3Dallas").text("目前設定金額:" + str[0]);
// 		        $("#textDiscount3Point").text("目前設定點數:" + str[1]);
// 		        var pause = data[6];
		        
// 		        if(pause =="啟用"){
// 		        	$('#cbStoreState').bootstrapToggle('on')
// 				    }else if(pause == "停用"){
// 				    	$('#cbStoreState').bootstrapToggle('off')
// 					  }
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




		    

		  $(".modify").click(function(){
			  $("#title").text("樹林站前店 - 設定加值機");
				$("#divStoreList").hide();				
				$("#divAddvalueSetting").show();
				$("#btnBackList").show();
				$("#btnNextConsumption").show();				
				
				//$("#btnUpdateAddvalue").show();
			});

		  $("#btnNextConsumption").click(function(){
			  $("#title").text("樹林站前店 - 設定消費機");
			  $("#divAddvalueSetting").hide();
			  $("#divConsumptionSetting").show();

			  $("#btnBackAddvalue").show();
			  $("#btnNextConsumption").hide();
			  
				//$("#btnUpdtateConsumption").show();
				//$("#btnUpdateAddvalue").hide();				
			});

		  $("#btnUpdateAddvalue").click(function(){
			  alert("btnComplete!");
			});

		  $("#btnBackList").click(function(){
			  $("#title").text("店家配置");
			  $("#divAddvalueSetting").hide();
			  $("#divConsumptionSetting").hide();
			  $("#divStoreList").show();
			  
			  $("#btnNextConsumption").hide();
				$("#btnBackAddvalue").hide();
				$("#btnBackList").hide();
			});

		  $("#btnUpdtateConsumprio").click(function(){
			  alert("btnComplete!");
			});

		  $("#btnBackAddvalue").click(function(){
			  $("#title").text("樹林站前店 - 設定加值機");
			  $("#divConsumptionSetting").hide();
			  $("#divAddvalueSetting").show();

			  $("#btnBackAddvalue").hide();
				$("#btnUpdtateConsumption").hide();
				$("#btnNextConsumption").show();
				
				//$("#btnBackAddvalue").hide();				
				//$("#btnUpdateAddvalue").show();
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