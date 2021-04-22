<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// 	MemVO memVO = (MemVO) session.getAttribute("memVO");
// 	System.out.println("Session username : " + memVO.getUsername());
// 	Object DID = session.getAttribute("DID");
// 	System.out.println("Session DID : " + DID.toString());
// 	System.out.println("MultiConsumption.jsp");
	
	//Test
	MemService memService = new MemService();
	MemVO memVO = memService.getOneMem("Van007");
	session.setAttribute("memVO", memVO);
	String DID = "TY00010";
	session.setAttribute("DID", DID);
	int usePoint = 10;
	int useMinute = 6;
	
	
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
		
		#matrix {
		  margin: 0 auto;
		  padding: 5px;
		  width: 100%;
		}
		
		#matrix tr, td {
		  padding: 5px;
		}
		
 		#matrix button {
 			width:100%;
/* 		  height: 80px; */
/* 		  width: 150px; */
/* 		  border: none; */
/* 		  font-family: helvetica; */
/* 		  font-weight: bold; */
/* 		  background-color: rgba(15, 138, 15, 0.5); */
 		}
		
		#matrix button:hover {
			background-color: rgb(15, 138, 15);
		}
		
	</style>
	
	<script src="../js/jquery-3.3.1.js"></script>
	<script src="../js/bootstrap-4.0.0/bootstrap.js"></script>
	<script src="../js/nicescroll.js"></script>
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
      <span class="h3 mx-1" style="font-weight:bold;">消費服務</span>
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
          <a class="nav-link" href="#" style="text-align: right; color: #FFFFFF;">消費服務<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./Record-M.jsp" style="text-align: right; color: #FFFFFF;">交易紀錄</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./Modify-M.jsp" style="text-align: right; color: #FFFFFF;">會員資料</a>
        </li>
				<li class="nav-item">
					<a class="nav-link" href="../logout.jsp" style="text-align: right; color: #FFFFFF;">登出</a>
        </li>
      </ul>
    </div>
  </nav>
  
  <section style="height: 1080px;">
		<div style="text-align: center;">
			<h2 id="storeInfo">
				中山店烘衣機1號
			</h2>
		</div>
		<div>
			<div style="margin: 7% 0 0 0; text-align:center;">
				<p style="margin: 0 0 0 0; font-size:20px;" id="pointMinute">每<%=usePoint %>點可使用<%=useMinute %>分鐘</p>
			</div>
		</div>
		<div style="margin: 7% 0 0 0; text-align:center;">
			<button type="button" class="btn btn-info" id="selectBtn">選擇次數</button>
		</div>
		<div style="margin: 7% 0 0 0; text-align:center; width:100%;" id="CounterDiv">
			<table id="matrix">
		    <tr>
		      <td><button id="btn1" class="btn btn-warning">1</button></td>
		      <td><button id="btn2" class="btn btn-warning">2</button></td>
		      <td><button id="btn3" class="btn btn-warning">3</button></td>
		    </tr>
		    <tr>
		      <td><button id="btn4" class="btn btn-warning">4</button></td>
		      <td><button id="btn5" class="btn btn-warning">5</button></td>
		      <td><button id="btn6" class="btn btn-warning">6</button></td>
		    </tr>
		    <tr>
		      <td><button id="btn7" class="btn btn-warning">7</button></td>
		      <td><button id="btn8" class="btn btn-warning">8</button></td>
		      <td><button id="btn9" class="btn btn-warning">9</button></td>
		    </tr>
		    <tr>
		      <td><button id="btn10" class="btn btn-warning">10</button></td>
		      <td><button id="btn11" class="btn btn-warning">11</button></td>
		      <td><button id="btn12" class="btn btn-warning">12</button></td>
		    </tr>
		  </table>
		</div>
		<div style="margin: 7% 0 0 0; text-align:center;">
			<p style="margin: 0 0 0 0; font-size:16px;">本次服務將扣除</p>
			<p style="font-weight: bold; color: #FF993C; margin: 0 0 0 0; font-size: 20px;" id="point">0點</p>
			<p style="margin: 6px 0 0 0;">可使用</p>
			<p style="font-weight: bold; color: #FF993C; font-size: 20px;" id="minute">0分鐘</p>
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
		
		//初始化各元素
		$(function(){
			$("#CounterDiv").hide();
		});
		
		$("#selectBtn").click(function(){
			$("#CounterDiv").slideToggle();
		});
		
		$(".btn-warning").click(function(){
			$("#CounterDiv").slideToggle();
			var count = this.innerHTML;
			var pointStr = (count * <%=usePoint %>) + "點";
			
			$("#point").text(pointStr);
			var minuteStr = (count * <%=useMinute %>) + "分鐘";
			$("#minute").text(minuteStr);
			
			resetTimer();
		});
		
		
		
		

		var count = 30;
		var myTimerVar= setInterval(function(){ myTimer()}, 1000);
		
		function myTimer(){
			if(count == 0){
				clearInterval(myTimerVar);
				window.location.href = "../logout.jsp";
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