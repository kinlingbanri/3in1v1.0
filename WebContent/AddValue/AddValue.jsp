<%@page import="com.mem.model.MemService"%>
<%@page import="com.store.model.StoreVO"%>
<%@page import="com.store.model.StoreService"%>
<%@page import="com.device.model.DeviceVO"%>
<%@page import="com.device.model.DeviceService"%>
<%@page import="com.device.model.DeviceDAO"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.List"%>
<%@page import="com.saleprice.model.SalePriceVO"%>
<%@page import="com.saleprice.model.SalepriceService"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	Object DIDObject = session.getAttribute("DID");
	String did = (String)DIDObject;
	System.out.println("AddValue.jspSession did : " + did.toString());
	Object MACHIDObject = session.getAttribute("MACHID");
	String machid = (String)MACHIDObject;
	System.out.println("AddValue.jspSession machid : " + machid);
	
	MemVO memVO = (MemVO) session.getAttribute("memVO");

	
	if(memVO == null){
		System.out.println("Session memVO Null ");
		RequestDispatcher rd = request.getRequestDispatcher("../logout.jsp");
		rd.forward(request,response);
	}else{		
		System.out.println("Session username : " + memVO.getUsername());
		String username = memVO.getUsername();
		int point = memVO.getPoint();
		
		int add_money = memVO.getAdd_money();
		System.out.println("add_money " + add_money);
		
		MemService memService = new MemService();
		memService.updateNowMoney(username, 0);
		
		// Get Device Object
		DeviceService deviceService = new DeviceService();
		DeviceVO deviceVO = deviceService.getOneDevice(did);
		deviceService.updateAddStatus11(did, 11, point);
		String localName = deviceVO.getLocation();
		
		int sid = deviceVO.getSid();
		System.out.println("AddValue.jspSession sid : " + sid);
		
		StoreService storeService = new StoreService();
		StoreVO storeVO = storeService.getOneStore(sid);
		
		String title = storeVO.getName() + deviceVO.getLocation();
		
		request.setAttribute("storeVO", storeVO);
		
		request.setAttribute("memVO", memVO);

		request.setAttribute("localName", localName);
		request.setAttribute("did", did);
		request.setAttribute("machid", machid);
		request.setAttribute("sid", sid);
		request.setAttribute("username", username);
		request.setAttribute("point", point);
		request.setAttribute("title", title);
	}
	
	String serviceType = machid.substring(0, 2);
	System.out.println("serviceType : " + serviceType);
	String headerStr = "";
	String url = "";
	if(serviceType.equals("TY")){
		headerStr = "加值服務";
		url = "./AddValue.jsp";
	}else if(serviceType.equals("WS")){
		headerStr = "消費服務";
		url = "./SingleConsumption.jsp";
	}else if(serviceType.equals("DR")){
		headerStr = "消費服務";
		url = "./MultiConsumption.jsp";
	}
	
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">	
  <title>加值系統</title>

	<link rel="shortcut icon" href="#" />
	<link rel="stylesheet" href="../css/bootstrap.css">
	<style>
		.element::-webkit-scrollbar { width: 0 !important }
		.element { -ms-overflow-style: none; }
		.element { overflow: -moz-scrollbars-none; }
	</style>
	
	<script src="../js/jquery-3.3.1.js"></script>
	<script src="../js/bootstrap-4.0.0/bootstrap.js"></script>
	<script src="../js/nicescroll.js"></script>
	<script src="../js/sweetalert2.js"></script>
</head>

<body style="font-family: Microsoft JhengHei; background-image: url('../images/bg_bggenerator_com.png');">
  
  <!-- .navbar-expand-{sm|md|lg|xl}決定在哪個斷點以上就出現漢堡式選單 -->
  <!-- navbar-dark 文字顏色 .bg-dark 背景顏色 -->
  <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #0093E9; background-image: linear-gradient(340deg, #0093E9 0%, #80D0C7 100%); //background-color:#91989F  !important;">
    <!-- .navbar-brand 左上LOGO位置 -->
    <a class="navbar-brand" href="#">
<!--       <img src="../images/icons8-menu.svg" width="30" height="30" class="d-inline-block align-top" alt=""> -->
      <span class="h3 mx-1" style="font-weight:bold;">加值服務</span>
    </a>
    <!-- .navbar-toggler 漢堡式選單按鈕 -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <!-- .navbar-toggler-icon 漢堡式選單Icon -->
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <!-- .collapse.navbar-collapse 用於外層中斷點群組和隱藏導覽列內容 -->
    <!-- 選單項目&漢堡式折疊選單 -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent" style="margin-top:4px;">
      <ul class="navbar-nav mr-auto">
        <!-- active表示當前頁面 -->
        <li class="nav-item active">
          <a class="nav-link" href="#" style="text-align: right; color: #FFFFFF;"><%=headerStr %><span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./Record.jsp" style="text-align: right; color: #FFFFFF;">交易紀錄</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./Modify.jsp" style="text-align: right; color: #FFFFFF;">會員資料</a>
        </li>
				<li class="nav-item">
					<a class="nav-link" href="../logout.jsp" style="text-align: right; color: #FFFFFF;">登出</a>
<!-- 					<button id="menuLogoutBtn" class="nav-link" -->
<!-- 						style="float:right; color: #FFFFFF; padding-top: 6px]; border: none; -->
<!-- 	 						background: none;">登出</button> -->
        </li>
      </ul>
    </div>
  </nav>
  
  <section style="height: 1080px;">
		<div style="text-align: center; margin:12px 0 24px 0;">
			<h3 style="color:#777;">${title}	</h3>
			<h5 style="color:orange; font-weight: bold;" id="h5NowPoint">現有點數: ${point}點</h5>
		</div>
		
		<!-- Price mapping -->
		<div style="text-align:center; width:100%;" id="divPrice">
			<div style="position: relative; z-index: 20; width: 320px; margin: 0px auto;">
				<img style="z-index: 5; position:relative; height:35px; width:210px;" src="../images/price-11.png.png">
				<h5 style="z-index: 10; position:absolute; color:#888; top:4px; left:82px; float:left;">${ storeVO.getDiscount_1_money() }元</h5>
				<h5 style="z-index: 10; position:absolute; color:#FFF; top:4px; right:80px; float:right;">${ storeVO.getDiscount_1_point() }點</h5>
			</div>
			<div style="position: relative; z-index: 20; width: 320px; margin: 0px auto;">
				<img style="z-index: 5; position:relative; height:35px; width:210px;" src="../images/price-22.png.png">
				<h5 style="z-index: 10; position:absolute; color:#888; top:4px; left:82px; float:left;">${ storeVO.getDiscount_2_money() }元</h5>
				<h5 style="z-index: 10; position:absolute; color:#FFF; top:4px; right:80px; float:right;">${ storeVO.getDiscount_2_point() }點</h5>
			</div>			
			<div style="position: relative; z-index: 20; width: 320px; margin: 0px auto;">
				<img style="z-index: 5; position:relative; height:35px; width:210px;" src="../images/price-33.png.png">
				<h5 style="z-index: 10; position:absolute; color:#888; top:4px; left:82px; float:left;">${ storeVO.getDiscount_3_money() }元</h5>
				<h5 style="z-index: 10; position:absolute; color:#FFF; top:4px; right:80px; float:right;">${ storeVO.getDiscount_3_point() }點</h5>
			</div>
		</div>	
		<!-- End Price mapping -->
		
		<!--  -->
		<div style="margin: 7% 0 0 0; text-align:center; width:320px; height:124px; margin:0px auto;" id="divNowPrice">
			<div style="float:left; position:relative; margin:24px;">
				<img style="z-index: 5; position:relative; height:124px; width:105px;" src="../images/price-444.png">
				<div style="z-index: 10; position:absolute; top:24px; width: 100%;">
						<h4 style="color:#F6C936;font-weight: 900;" id="totalMoney">0</h4>
				</div>
				<h5 style="z-index: 10; position:absolute; color:#EEE; top:89px; right:13px; float:right;">投入金額</h5>
			</div>
			<div style="float:right; position:relative;">
				<img style="z-index: 5; position:relative; height:124px; width:105px; margin:24px;" src="../images/price-555.png">
				
				<div style="z-index: 10; position:absolute;top:48px; width: 100%;;">
						<h4 style="color:#FF993C; font-weight: 900;" id="totalPoint">0</h4>
				</div>
				<h5 style="z-index: 10; position:absolute; color:#EEE; top:114px; right:36px; float:right;">加值點數</h5>
			</div>
		</div>		
		<!-- End -->
		
		
		<input type="hidden" name="did" value="${did }" id="inputDid">
		<input type="hidden" name="machid" value="${machid }" id="inputMACHid">
		<input type="hidden" name="sid" value="${sid }" id="inputSid">
		<input type="hidden" name="username" value="${username }" id="inputUsername">		
		<input type="hidden" name="count_100" value="" id="inputCount_100">
		<input type="hidden" name="count_500" value="" id="inputCount_500">
		<input type="hidden" name="count_1000" value="" id="inputCount_1000">
		<input type="hidden" name="totalPoint" value="" id="inputTotalPoint">
		
		<div style="margin: 7% 0 0 0; text-align:center; width:100%;">
		
<!-- 			<p style="margin: 0 0 0 0; font-size:16px;">已投入金額</p> -->
<!-- 			<p style="font-weight: bold; color: #FF993C; margin: 0 0 0 0; font-size: 20px;" id="addMoney">200元</p> -->
<!-- 			<p style="margin: 6px 0 0 0;">加值點數為</p> -->
<!-- 			<p style="font-weight: bold; color: #FF993C; font-size: 20px;" id="addPoint">220點</p> -->
			<div id="divInfo">
<!-- 				<p style="margin: 0 0 0 0; font-size:16px;">系統將於以下時間結束後自動完成加值</p> -->
				<p id="timer" style="color:red; font-weight:bold; font-size:18px; margin:0;">系統將於30秒後自動完成加值</p>
				<p style="margin: 0 0 16px 0; font-size:16px;">或請按完成，手動完成加值</p>
<!-- 				<p id="timer" style="color:red; font-weight:bold; font-size:18px;">30秒</p> -->
				<button class="btn btn-outline-success" style="font-weight:bold;" id="btnAdd" disabled>確認儲值</button>
				<button class="btn btn-warning" style="font-weight: bold; margin-left: 12px;" id="logoutBtn">取消交易</button>
			</div>

			<div id="divSuccess" style="display:none;">
				<p style="font-size:28px; font-weight: 900; color: blue; margin: 24px 0 12px 0;">加值成功</p>
				<p style="font-size:20px; font-weight: 700; color: orange; margin: 0 0 0 0;" id="successDallas">本次加值0元</p>
				<p style="font-size:20px; font-weight: 700; color: orange; margin: 0 0 0 0;" id="successPoint">儲值點數0點</p>
				<p style="font-size:20px; font-weight: 700; color: orange; margin: 0 0 0 0; dispaly:none;" id="successNowPoint">加值後您現在的點數為0點</p>
				<p style="font-size:24px; font-weight: 700; color: red; margin: 48px 0 0 0;" id="successInfo">3秒後自動跳回至登入畫面</p>
			</div>
			
		</div>
  </section>
  <!-- End Section -->  
  
  
  <script>
  
		//隠藏右側scrollbar
		$("#body").niceScroll({
		  cursorcolor: "#0026BF",
		  cursorborder: "1px solid #30BAFF", 
		  autohidemode: "hidden",
		  cursorwidth: "10px"
		});

		var count = 30;
		var myTimerVar= setInterval(function(){ myTimer()}, 1000);
		console.log("Into AddValue.jsp");
		
		function myTimer(){
			if(count == 0){
				clearInterval(myTimerVar);
				var totalPoint = $("#inputTotalPoint").val();
				if(totalPoint > 0){
					addValue();
				}else{
					window.location.replace("../logout.jsp");
					//window.location.href = "../logout.jsp";
				}
				
			}else{
				count = count - 1;
				checkMoney();
				var countStr = "系統將於" + count + "秒後自動完成加值";
				document.getElementById("timer").innerText = countStr;
			}
		}
		
		function resetTimer(){
			count = 30;
// 			var countStr = count + "秒";
			var countStr = "系統將於" + count + "秒後自動完成加值";
			document.getElementById("timer").innerText = countStr;
			clearInterval(myTimerVar);
			myTimerVar= setInterval(function(){ myTimer()}, 1000);
		}

		var totalMoneyTemp = 0;
		function moneyToPoint(totalMoney, totalPoint ){

			$("#totalMoney").text(totalMoney);
			$("#totalPoint").text(totalPoint);
			$("#inputTotalPoint").val(totalPoint);
			$("#successDallas").text( "本次加值" + totalMoney + "元");
			$("#successPoint").text( "本次儲值" + totalPoint + "點");

			if(totalPoint > 0){
				$('.navbar-toggler').attr('disabled', true);
				$("#btnAdd").attr("disabled", false);
				$('#logoutBtn').attr('disabled', true);
				
			}else if(totalPoint <= 0){
				$('.navbar-toggler').attr('disabled', false);
				$("#btnAdd").attr("disabled", true);
				$('#logoutBtn').attr('disabled', false);
			}

			if( totalMoneyTemp != totalPoint ){
				totalMoneyTemp = totalPoint;
				resetTimer();
			}
		}
		
		function checkMoney(){
			var did = $("#inputDid").val();
			var sid = $("#inputSid").val();
			var username = $("#inputUsername").val();
			console.log("check Money DID : " + did);
			console.log("check Money SID : " + sid);
			$.ajax({
				type : 'POST', //GET or POST
				url : "../CheckMoneyServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : { //要傳送到頁面的參數
					did : did,
					sid : sid,
					username:username
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫

					if(jsonObject.state == 2){

					}else if(jsonObject.state == 1){
						moneyToPoint(jsonObject.totalMoney, jsonObject.totalPoint );
					}
					
				},
				error : function(e) {
					console.log("e: " + e);
				}
			});
		}

		function addValue(){
			console.log("Add Value Function!");
			var did = $("#inputDid").val();
			var sid = $("#inputSid").val();
			var username = $("#inputUsername").val();
			console.log("did : " + did);
			console.log("sid : " + sid);
			
			$.ajax({
				type : 'POST', //GET or POST
				url : "../AddRecordServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : { //要傳送到頁面的參數
					did : did,
					sid : sid,
					username : username
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					console.log("jsonObject.state : " + jsonObject.state);
					if(jsonObject.state == 19){
						clearInterval(myTimerVar);
						//show add success, and jump to login page
						$("#divPrice").hide();
						$("#divNowPrice").hide();
						$("#divInfo").hide();
						$("#divSuccess").show();
						$("#successNowPoint").text( "加值後您現在的點數為 " + jsonObject.nowPoint + " 點");
						$("#successNowPoint").show();
						setTimeout(function(){ window.location.replace("../logout.jsp"); }, 3000);
						//setTimeout(function(){ window.location.href = "../logout.jsp"; }, 3000);
					}
				},
				error : function(e) {
					console.log("e: " + e);
				}
			});
		}
		
		$("#btnAdd").click(function(){
			checkMoney();
			var totalPoint = $("#inputTotalPoint").val();
			console.log("btnAdd totalPoint :" + totalPoint);
			if(totalPoint > 0){
				addValue();
			}			
		});

		document.getElementById('logoutBtn').onclick = function() {
			clearInterval(myTimerVar);
			window.location.href = "../logout.jsp";
		}

  </script>
</body>
</html>