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
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	System.out.println("Session username : " + memVO.getUsername());
	Object DIDObject = session.getAttribute("DID");
	String did = (String)DIDObject;
	System.out.println("AddValue.jspSession did : " + did.toString());
	
	SalepriceService salepriceService = new SalepriceService();
	
	// Query All
	List<SalePriceVO> salePriceVOs = salepriceService.getAll();

	Collections.sort(salePriceVOs, new Comparator<SalePriceVO>() {
		public int compare(SalePriceVO o1, SalePriceVO o2) {
			return o1.getDallas() - o2.getDallas();
		}
	});
	
	// Get Device Object
	DeviceService deviceService = new DeviceService();
	DeviceVO deviceVO = deviceService.getOneDevice(did);
	String localName = deviceVO.getLocation();
	
	request.setAttribute("salePriceVOs", salePriceVOs);
	request.setAttribute("localName", localName);
	request.setAttribute("did", did);
	
	
// 	for (SalePriceVO salePriceVO1 : salePriceVOs) {
// 		System.out.print(salePriceVO1.getId() + ",");
// 		System.out.print(salePriceVO1.getDallas() + ",");
// 		System.out.println(salePriceVO1.getPoint());
// 	}

// 		Query check money
// 		DeviceVO deviceVO1 = deviceService.getCheckMoney("TY00001");
// 		System.out.print(deviceVO1.getDid() + ",");
// 		System.out.print(deviceVO1.getNumber() + ",");
// 		System.out.print(deviceVO1.getCoin() + ",");
// 		System.out.print(deviceVO1.getPaper() + ",");
// 		System.out.print(deviceVO1.getLocation() + ",");
// 		System.out.print(deviceVO1.getRefund() + ",");
// 		System.out.print(deviceVO1.getUid() + ",");
// 		System.out.print(deviceVO1.getStatus() + ",");
// 		System.out.print(deviceVO1.getError() + ",");
// 		System.out.print(deviceVO1.getMachid() + ",");
// 		System.out.print(deviceVO1.getFreecount() + ",");
// 		System.out.print(deviceVO1.getFreecountset() + ",");
// 		System.out.print(deviceVO1.getMaid() + ",");
// 		System.out.print(deviceVO1.getMid() + ",");
// 		System.out.print(deviceVO1.getAdd_status() + ",");
// 		System.out.print(deviceVO1.getCount_100() + ",");
// 		System.out.print(deviceVO1.getCount_500() + ",");
// 		System.out.println(deviceVO1.getCount_1000());
	
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
          <a class="nav-link" href="#" style="text-align: right; color: #FFFFFF;">加值服務<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./Record-A.jsp" style="text-align: right; color: #FFFFFF;">交易紀錄</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./Modify-A.jsp" style="text-align: right; color: #FFFFFF;">會員資料</a>
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
			<h2 style="color:#777;">${localName}
<!-- 				中山店加值機1號 -->

			</h2>
<!-- 			<h4 style="font-size:20px; color:RED;font-weight: bold;"> -->
<!-- 				優惠價 -->
<!-- 			</h4> -->
		</div>
		
		<!-- Price mapping -->
		<div style="text-align:center; width:100%;">
			<div style="position: relative; z-index: 20; width: 320px; margin: 0px auto;">
				<img style="z-index: 5; position:relative; height:35px; width:210px;" src="../images/price-11.png.png">
				<h5 style="z-index: 10; position:absolute; color:#888; top:4px; left:82px; float:left;">${salePriceVOs.get(0).getDallas()}元</h5>
				<h5 style="z-index: 10; position:absolute; color:#FFF; top:4px; right:80px; float:right;">${salePriceVOs.get(0).getPoint()}點</h5>
			</div>
			<div style="position: relative; z-index: 20; width: 320px; margin: 0px auto;">
				<img style="z-index: 5; position:relative; height:35px; width:210px;" src="../images/price-22.png.png">
				<h5 style="z-index: 10; position:absolute; color:#888; top:4px; left:82px; float:left;">${salePriceVOs.get(1).getDallas()}元</h5>
				<h5 style="z-index: 10; position:absolute; color:#FFF; top:4px; right:80px; float:right;">${salePriceVOs.get(1).getPoint()}點</h5>
			</div>			
			<div style="position: relative; z-index: 20; width: 320px; margin: 0px auto;">
				<img style="z-index: 5; position:relative; height:35px; width:210px;" src="../images/price-33.png.png">
				<h5 style="z-index: 10; position:absolute; color:#888; top:4px; left:82px; float:left;">${salePriceVOs.get(2).getDallas()}元</h5>
				<h5 style="z-index: 10; position:absolute; color:#FFF; top:4px; right:80px; float:right;">${salePriceVOs.get(2).getPoint()}點</h5>
			</div>
		</div>	
		<!-- End Price mapping -->
		
		<!-- 舊優惠對照表 -->
<!-- 		<div> -->
<!-- 			<table style="width:100%; border:none;"> -->
<!-- 				<tbody> -->
<!-- 					<tr> -->
<%-- 						<td style="text-align: right;">${salePriceVOs.get(0).getDallas()}元</td> --%>
<!-- 						<td style="text-align: center;">-&gt;&nbsp;</td> -->
<%-- 						<td style="text-align: left; color:#FF993C;">${salePriceVOs.get(0).getPoint()}點</td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<%-- 						<td style="text-align: right;">${salePriceVOs.get(1).getDallas()}元</td> --%>
<!-- 						<td style="text-align: center;">-&gt;&nbsp;<br></td> -->
<%-- 						<td style="text-align: left; color:#FF993C;">${salePriceVOs.get(1).getPoint()}點<br></td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<%-- 						<td style="text-align: right;">${salePriceVOs.get(2).getDallas()}元</td> --%>
<!-- 						<td style="text-align: center;">-&gt;&nbsp;</td> -->
<%-- 						<td style="text-align: left; color:#FF993C;">${salePriceVOs.get(2).getPoint()}點</td> --%>
<!-- 					</tr> -->
<!-- 				</tbody> -->
<!-- 			</table>			 -->
<!-- 		</div> -->
		<!-- End  舊優惠對照表 -->
		
		<!--  -->
		<div style="margin: 7% 0 0 0; text-align:center; width:320px; height:124px; margin:0px auto;">
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
		
		
		<input type="hidden" name="did" value="<%=did %>" id="inputDid">
		
		<div style="margin: 7% 0 0 0; text-align:center; width:100%;">
		
<!-- 			<p style="margin: 0 0 0 0; font-size:16px;">已投入金額</p> -->
<!-- 			<p style="font-weight: bold; color: #FF993C; margin: 0 0 0 0; font-size: 20px;" id="addMoney">200元</p> -->
<!-- 			<p style="margin: 6px 0 0 0;">加值點數為</p> -->
<!-- 			<p style="font-weight: bold; color: #FF993C; font-size: 20px;" id="addPoint">220點</p> -->
			<p style="margin: 0 0 0 0; font-size:16px;">系統將於以下時間結束後自動完成加值</p>
			<p style="margin: 0 0 0 0; font-size:16px;">或請按完成，手動完成加值</p>
			<p id="timer" style="color:red; font-weight:bold; font-size:18px;">30秒</p>
			<button class="btn btn-outline-success" style="font-weight:bold;" id="btnAdd">確認</button>
		</div>
  </section><!-- End Section -->  
  
  
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
				
				/*
				var totalPoint = 520;				
				swal.fire({
				    title: '加值成功！',
				    text: '本次加值' + $("#addMoney").val() + '；' + $("#addPoint").val() + '；' + 
				    					'剩餘點數' + totalPoint +  '；' + '3秒後自動關閉!',
				    timer: 3000
				}).then(
				    function () {
				    	// handling the promise rejection
				    	window.location.href = "../logout.jsp";
				    },		    	
				    function (dismiss) {
				        if (dismiss === 'timer') {
				            console.log('I was closed by the timer')
				        }
				    }
					)
					*/
					
				window.location.href = "../logout.jsp";
				
			}else{
				count = count - 1;
				console.log("count : " + count);
				var countStr = count + "秒";
				checkMoney();
				document.getElementById("timer").innerText = countStr;
			}
		}
		
		function resetTimer(){
			count = 30;
			var countStr = count + "秒";
			document.getElementById("timer").innerText = countStr;
			clearInterval(myTimerVar);
			myTimerVar= setInterval(function(){ myTimer()}, 1000);
		}

		var totalMoneyTemp = 0;
		function moneyToPoint(count100, count500, count1000 ){
			var totalPoint = 0;
			var totalMoney = (count100*100) + (count500*500) + (count1000*1000);
			console.log("totalMoney : " + totalMoney);

// 			if(totalMoney > ${salePriceVOs.get(2).getDallas()){
// 				totalPoint = totalMoney + ( ${salePriceVOs.get(2).getPoint() - ${salePriceVOs.get(2).getDallas() );
// 			}else if(totalMoney > ${salePriceVOs.get(1).getDallas()){
// 				totalPoint = totalMoney + ( ${salePriceVOs.get(1).getPoint() - ${salePriceVOs.get(1).getDallas() );
// 			}else if(totalMoney > ${salePriceVOs.get(0).getDallas()){
// 				totalPoint = totalMoney + ( ${salePriceVOs.get(0).getPoint() - ${salePriceVOs.get(0).getDallas() );
// 			}

			price3 = parseInt(totalMoney / ${salePriceVOs.get(2).getDallas()});
			console.log("price3 : " + price3);
			console.log("Dallas : " + ${salePriceVOs.get(2).getDallas()});
			totalPoint += price3 * ${salePriceVOs.get(2).getPoint()};
			console.log("totalPoint : " + totalPoint);
			var tmp2Value = totalMoney - (${salePriceVOs.get(2).getDallas()}*price3);
			console.log("tmp2Value : " + tmp2Value);
			price2 = parseInt(tmp2Value / ${salePriceVOs.get(1).getDallas()});
			console.log("price2 : " + price2);
			totalPoint += price2 * ${salePriceVOs.get(1).getPoint()};
			console.log("totalPoint : " + totalPoint);
			var tmp1Value = tmp2Value - (${salePriceVOs.get(1).getDallas()}*price2);
			console.log("tmp1Value : " + tmp1Value);
			price1 = parseInt(tmp1Value / ${salePriceVOs.get(0).getDallas()});
			console.log("price1 : " + price1);
			totalPoint += price1 * ${salePriceVOs.get(0).getPoint()};
			console.log("totalPoint : " + totalPoint);
			var tmpValue = tmp1Value - (${salePriceVOs.get(0).getDallas()}*price1);
			console.log("tmpValue : " + tmpValue);
			totalPoint = totalPoint + tmpValue;
			console.log("totalPoint : " + totalPoint);

			$("#totalMoney").text(totalMoney);
			$("#totalPoint").text(totalPoint);

			if( totalMoneyTemp != totalPoint ){
				totalMoneyTemp = totalPoint;
				resetTimer();
			}
		}

		
		function checkMoney(){
			var did = $("#inputDid").val();
			console.log("check Money DID : " + did);
			$.ajax({
				type : 'POST', //GET or POST
				url : "../CheckMoneyServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : { //要傳送到頁面的參數
					did : did
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					console.log("jsonObject : " + jsonObject);
					count100 = jsonObject.count_100;
					count500 = jsonObject.count_500;
					count1000 = jsonObject.count_1000;
					console.log("jsonObject.add_status : " + jsonObject.add_status);
					moneyToPoint(count100, count500, count1000 );
					
					//$("#verificationcode").val( jsonObject.testCode);
				},
				error : function(e) {
					console.log("e: " + e);
				}
			});
		}
		
		$("#btnAdd").click(function(){
			checkMoney();
		});
		

		
		
// 		document.getElementById('menuLogoutBtn').onclick = function(){
// 			window.location.href = "../logout.jsp";
// 		}
 
  </script>
</body>
</html>