<%@page import="com.mem.model.MemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	System.out.println("Session username : " + memVO.getUsername());
	Object DID = session.getAttribute("DID");
	System.out.println("AddValue.jspSession DID : " + DID.toString());
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
  <nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#91989F  !important;">
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
		<div style="text-align: center;">
			<h2>
				中山店加值機1號
			</h2>
			<h4 style="font-size:20px; color:RED;font-weight: bold;">
				優惠價
			</h4>
		</div>
		<div>
			<table style="width:100%; border:none;">
				<tbody>
					<tr>
						<td style="text-align: right;">200元</td>
						<td style="text-align: center;">-&gt;&nbsp;</td>
						<td style="text-align: left; color:#FF993C;">220點</td>
					</tr>
					<tr>
						<td style="text-align: right;">500元</td>
						<td style="text-align: center;">-&gt;&nbsp;<br></td>
						<td style="text-align: left; color:#FF993C;">600點<br></td>
					</tr>
					<tr>
						<td style="text-align: right;">1000元</td>
						<td style="text-align: center;">-&gt;&nbsp;</td>
						<td style="text-align: left; color:#FF993C;">1300點</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="margin: 7% 0 0 0; text-align:center;">
			<p style="margin: 0 0 0 0; font-size:16px;">已投入金額</p>
			<p style="font-weight: bold; color: #FF993C; margin: 0 0 0 0; font-size: 20px;" id="addMoney">200元</p>
			<p style="margin: 6px 0 0 0;">加值點數為</p>
			<p style="font-weight: bold; color: #FF993C; font-size: 20px;" id="addPoint">220點</p>
			<p style="margin: 0 0 0 0; font-size:16px;">系統將於以下時間結束後自動完成加值</p>
			<p style="margin: 0 0 0 0; font-size:16px;">或請按完成，手動完成加值</p>
			<p id="timer" style="color:red; font-weight:bold; font-size:18px;">30秒</p>
			<button class="btn btn-outline-success" style="font-weight:bold;" id="logoutBtn">
				確認
			</button>
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
				
			}else{
				count = count - 1;
				console.log("count : " + count);
				var countStr = count + "秒";
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

		document.getElementById('logoutBtn').onclick = function(){
			window.location.href = "../logout.jsp";
		}
		
		
		
		
		

		
		
// 		document.getElementById('menuLogoutBtn').onclick = function(){
// 			window.location.href = "../logout.jsp";
// 		}
 
  </script>
</body>
</html>