<%@page import="com.history.model.HistoryVO"%>
<%@page import="com.history.model.HistoryService"%>
<%@page import="com.device.model.DeviceVO"%>
<%@page import="java.util.List"%>
<%@page import="com.device.model.DeviceService"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//online
 	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String username = memVO.getUsername();
	MemService memService = new MemService();
	memVO = memService.getOneMem(username);
	int memPoint = memVO.getPoint();
	//memPoint = 90;
	String DID = (String)session.getAttribute("DID");
	String MACHID = (String)session.getAttribute("MAID");
	int machineNumber =  Integer.parseInt(MACHID.substring(3, 8));
	System.out.println("Session username : " + username);
	System.out.println("Session memPoint : " + memPoint);
	System.out.println("Session DID : " + DID);
	System.out.println("Session MAID : " + MACHID);
	System.out.println("Session machineNumber : " + machineNumber);
	System.out.println("SingleConsumption.jsp");	
	
	int consumptionPoint = 100;						//消費一次費用,從資料庫查詢
	request.setAttribute("consumptionPoint", consumptionPoint);
	
// 	HistoryService historyService = new HistoryService();
// 	List<HistoryVO> historys = historyService.getByMemberId("陳啟展");
// 	for (HistoryVO history : historys) {
// 		System.out.print(history.getHid() + ",");
// 		System.out.print(history.getTtime() + ",");
// 		System.out.print(history.getEvent() + ",");
// 		System.out.print(history.getIp() + ",");
// 		System.out.print(history.getUid() + ",");
// 		System.out.print(history.getDid() + ",");
// 		System.out.print(history.getMaid() + ",");
// 		System.out.print(history.getMid() + ",");
// 		System.out.print(history.getRefundcount() + ",");
// 		System.out.print(history.getFreecount() + ",");
// 		System.out.print(history.getExchangecount() + ",");
// 		System.out.println(history.getPapercount());
// 	}
	
		DeviceService deviceService = new DeviceService();
		DeviceVO device = deviceService.getOneDevice(DID);
		System.out.print(device.getDid() + ",");
		System.out.print(device.getNumber() + ",");
		System.out.print(device.getCoin() + ",");
		System.out.print(device.getPaper() + ",");
		System.out.print(device.getLocation() + ",");
		System.out.print(device.getRefund() + ",");
		System.out.print(device.getUid() + ",");
		System.out.print(device.getMaid() + ",");
		System.out.print(device.getMid() + ",");
		System.out.print(device.getStatus() + ",");
		System.out.print(device.getError() + ",");
		System.out.print(device.getMachid() + ",");
		System.out.print(device.getFreecount() + ",");
		System.out.print(device.getFreecountset() + ",");
		System.out.println(device.getLocation());
		
		String machineName = device.getLocation() + " 洗衣機" + Integer.toString(machineNumber) + "號";
		System.out.print("machineName : " + machineName);
	
	//Test
// 	MemService memService = new MemService();
// 	MemVO memVO = memService.getOneMem("Van007");
// 	session.setAttribute("memVO", memVO);
// 	String DID = "TY00010";	
// 	int point = 100;						//消費一次費用
// 	session.setAttribute("DID", DID);
// 	request.setAttribute("point", point);
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
          <a class="nav-link" href="./Record-S.jsp" style="text-align: right; color: #FFFFFF;">交易紀錄</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./Modify-S.jsp" style="text-align: right; color: #FFFFFF;">會員資料</a>
        </li>
				<li class="nav-item">
					<a class="nav-link" href="../logout.jsp" style="text-align: right; color: #FFFFFF;">登出</a>
        </li>
      </ul>
    </div>
  </nav>
  
  <section style="height: 1080px;">
		<div style="text-align: center;">
			<h2 id="storeInfo" style="font-weight: 900;">
				<%=machineName %>
			</h2>
			<p style="margin: 0 0 0 0; font-size:20px; font-weight: 600;">本次消費需<%=consumptionPoint %>點</p>
		</div>
		<div style="margin: 7% 0 0 0; text-align:center;">
			<div id="divInfo">
				<p style="margin: 0 0 0 0; font-size:20px;">現有點數</p>
				<p style="font-weight: bold; color: #FF993C; margin: 0 0 0 0; font-size: 20px; margin-bottom: 36px;" id="memPoint"><%=memPoint %>點</p>
				
				<p style="font-weight: bold; color: #FF993C; margin: 0 0 0 0; font-size: 20px; margin-bottom: 36px;" id="consumptionPoint"></p>
			</div>
			<p style="margin: 0 0 32px 0; font-size:24px; font-weight: bold; color:red;" id="lack">您的餘額不足，請先加值</p>
			<div id="divSuccess">
				<p style="margin: 0 0 0 0; font-size:24px; font-weight: bold;">本次消費<%=consumptionPoint %>點</p>
				<p style="margin: 0 0 0 0; font-size:24px; font-weight: bold; color:red;" id="balance"></p>
			</div>
			<div id="timerDiv" style="margin: 0 0 12px 0;">
				<p style="margin: 0 0 0 0; font-size:16px;">請按確認交易，手動完成服務</p>
				<p style="margin: 0 0 0 0; font-size:16px;">或於時間結束後由系統自動完成服務</p>
				<p id="timer" style="color:red; font-weight:bold; font-size:18px;">30秒</p>
			</div>
		  <input type="hidden" name="DID" value="<%=DID %>" id="inputDIid">
		  <input type="hidden" name="MACHID" value="2" id="inputMachid">
		  <input type="hidden" name="username" value="<%=username %>" id="inputUsername">
		  <input type="hidden" name="status" value="0" id="inputStatus">
		  <input type="hidden" name="memPoint" value="<%=memPoint %>" id="inputMemPoint">
		  <input type="hidden" name="consumptionPoint" value="<%=consumptionPoint %>" id="inputConsumptionPoint">
		  <input type="hidden" name="freecount" value="10" id="inputFreecount">
		  <input type="hidden" name="number" value="<%=MACHID %>" id="inputNumber">
			<button type="submit" id="confirmBtn" class="btn btn-success" style="font-weight:bold; margin-right:12px;">確認交易</button>
			<button type="submit" id="logoutBtn" class="btn btn-warning" style="font-weight:bold; margin-left:12px;">取消交易</button>
		</div>
		<div style="text-align: center; margin-top:24px;" id="successDiv">
			<button class="btn btn-success" style="font-weight:bold; margin-left:12px;" id="successBtn">完成交易</button>
		</div>
  </section><!-- End Section -->  
  
  
  <script>
//   $(function() {
<%-- 		if (	<%=memPoint%> < 10) { --%>
// 			$("#lack").show();
// 			$("#divSelect").hide();
// 			$("#needPoint").hide();
// 			$("#CounterDiv").hide();
// 			$("#divSuccess").hide();
// 			$("#timerDiv").hide();
// 			$("#confirmBtn").hide();
// 		} else {
// 			$("#lack").hide();
// 		}

		//初始化各元素
		$(function(){
			var memPointStr = $("#memPoint").text();
			memPointStr = memPointStr.substring(0, memPointStr.length-1);
			var memPointValue = parseInt(memPointStr, 10);
			var consumptionPoint = $("#inputConsumptionPoint").val();
			consumptionPoint =parseInt(consumptionPoint, 10);

			$("#divSuccess").hide();
			$("#successInfo").hide();
			$("#logoutBtn").show();
			$("#successDiv").hide();

			if(memPointValue < consumptionPoint){
				$("#lack").show();
				$("#timerDiv").hide();
				$("#confirmBtn").hide();
			}else{
				$("#lack").hide();
				$("#timerDiv").show();
				$("#confirmBtn").show();
				
				//var myTimerVar= setInterval(function(){ myTimer()}, 1000);
			}			
		});
  
		//隠藏右側scrollbar
		$("#body").niceScroll({
		  cursorcolor: "#0026BF",
		  cursorborder: "1px solid #30BAFF", 
		  autohidemode: "hidden",
		  cursorwidth: "10px"
		});

		
		
		var DID = $("#DID").val();
		var count = 30;
		
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

		function resetStatusTimer(){
			clearInterval(myCheckStatusTimerVar);	
		}


		function Consumption(username, did, machid, freecount, mempoint, status, consumptionPoint, number){
			
			$.ajax({
				  type: 'POST',                     //GET or POST
				  url: "../FreeCountServlet",       //請求的頁面
				  cache: false,                    //防止抓到快取的回應
				  async:false,													//停止非同步
				  data: {
					  username : username,
					  did : did,
					  machid : machid,
				  	freecount : freecount,
				  	mempoint : mempoint,
				  	status : status,
				  	consumptionPoint : consumptionPoint,
				  	number : number
				  },
				  success: function (jsonObject) {         //當請求成功後此事件會被呼叫
				  	var state = jsonObject.state;
				  	var balance =  jsonObject.balance;
				  	if(state == 1){
					  	$("#balance").text('您的餘額為 ' + balance + '點');
				  		$("#lack").hide();
							$("#timerDiv").hide();
							$("#logoutBtn").hide();
							$("#confirmBtn").hide();
							$("#successInfo").show();
							$("#divInfo").hide();
							$("#divSuccess").show();
							
							//count = 3;
							//myTimerVar= setInterval(function(){ myTimer()}, 1000);
					  }
				  },
				  error: function(e){
				  	console.log("e: " + e);
				  },            //當請求失敗後此事件會被呼叫
				  statusCode: {                     //狀態碼處理
				    404: function() {
				      alert("page not found");
				    }
				  }
				});
		}

		
		function CheckStatus(did){
			var deviceStatus = 0;
				$.ajax({
					  type: 'POST',                     		//GET or POST
					  url: "../CheckDeviceStatusServlet",  //請求的頁面
					  cache: false,                    		//防止抓到快取的回應
					  async:false,													//停止非同步
					  data: {
						  did : did
					  },
					  success: function (jsonObject) {         //當請求成功後此事件會被呼叫
					  	var status = jsonObject.status;
					  	if(status == 1){
					  		deviceStatus = 1;
					  		console.log("CheckStatus : " + deviceStatus);
						  }
					  },
					  error: function(e){
					  	console.log("e: " + e);
					  },            //當請求失敗後此事件會被呼叫
					  statusCode: {                     //狀態碼處理
					    404: function() {
					      alert("page not found");
					    }
					  }
					});
			return deviceStatus;
		}
		
		function historyRecord(username, did, machid, freecount, consumptionPoint, number){
			$.ajax({
				  type: 'POST',                     		//POST
				  url: "../HistoryRecordServlet",  			//請求的頁面
				  cache: false,                    		//防止抓到快取的回應
				  async:false,													//停止非同步
				  data: {
					  username : username,
					  did : did,
					  machid : machid,
					  point : consumptionPoint,
					  freecount : freecount,
					  number : number
				  },
				  success: function (jsonObject) {         //當請求成功後此事件會被呼叫
				  	var status = jsonObject.status;
			  		console.log("status : " + status);
				  },
				  error: function(e){
				  	console.log("e: " + e);
				  },            //當請求失敗後此事件會被呼叫
				  statusCode: {                     //狀態碼處理
				    404: function() {
				      alert("page not found");
				    }
				  }
			});
		}
		


		document.getElementById('logoutBtn').onclick = function(){
			window.location.href = "../logout.jsp";
		}

		document.getElementById('successBtn').onclick = function(){
			window.location.href = "../logout.jsp";
		}

		$("#confirmBtn").click(function(){
			var did =  $("#inputDIid").val();
			var machid =  $("#inputMachid").val();
			var username =  $("#inputUsername").val();
			var status =  $("#inputStatus").val();
			var mempoint =  $("#inputMemPoint").val();
			var consumptionPoint =  $("#inputConsumptionPoint").val();
			var freecount =  $("#inputFreecount").val();
			var number =  $("#inputNumber").val();

			console.log(did + ";" + machid + ";"+ username + ";"+ status + ";"+ mempoint + ";" +consumptionPoint + ";"+ freecount + ";" + number + ";");
			
			Consumption(username, did, machid, freecount, mempoint, status, consumptionPoint, number);

			delay('a',0).then(function(v){
				  console.log(v[0],v[1]);   // 顯示 a 0
				   var status = CheckStatus(did);
				   console.log("status : " + status);
				  return delay('b',100);   // 延遲一秒之後，告訴後面的函示顯示 b 1000
				}).then(function(v){
				  console.log(v[0],v[1]);   // 顯示 b 1000
				  var status = CheckStatus(did);
				  historyRecord(username, did, machid, freecount, consumptionPoint, number);
				  console.log("status : " + status);
				  return delay('c',1000);   // 延遲兩秒之後，告訴後面的函示顯示 c 1000
				}).then(function(v){
					  console.log(v[0],v[1]);   // 顯示 c 1000
					  var status = CheckStatus(did);
					  if(status == 1){
						  $("#successDiv").show();
						}
					  console.log("status : " + status);
					  return delay('d',1000);
				}).then(function(v){
					  console.log(v[0],v[1]);
					  var status = CheckStatus(did);
					  if(status == 1){
						  $("#successDiv").show();
						}
					  console.log("status : " + status);
					  return delay('e',1000);
				}).then(function(v){
					  console.log(v[0],v[1]);
					  var status = CheckStatus(did);
					  if(status == 1){
						  $("#successDiv").show();
						}
					  console.log("status : " + status);
					  return delay('f',1000);
				}).then(function(v){
					  console.log(v[0],v[1]);
					  var status = CheckStatus(did);
					  if(status == 1){
						  $("#successDiv").show();
						}
					  console.log("status : " + status);
					  return delay('g',1000);
				}).then(function(v){
					  console.log(v[0],v[1]);
					  var status = CheckStatus(did);
					  if(status == 1){
						  $("#successDiv").show();
						}
					  console.log("status : " + status);
					  return delay('h',1000);
				}).then(function(v){
					  console.log(v[0],v[1]);
					  var status = CheckStatus(did);
					  if(status == 1){
						  $("#successDiv").show();
						}
					  console.log("status : " + status);
					  return delay('i',1000);
				}).then(function(v){
					  console.log(v[0],v[1]);
					  var status = CheckStatus(did);
					  if(status == 1){
						  $("#successDiv").show();
						}
					  console.log("status : " + status);
					  return delay('i',1000);
				}).then(function(v){
					  console.log(v[0],v[1]);
					  var status = CheckStatus(did);
					  if(status == 1){
						  $("#successDiv").show();
						}
					  console.log("status : " + status);
					  if(status == 1){
						  //historyRecord(username, did, machid, freecount, consumptionPoint, number);
						}
					  return delay('i',1000);
				}).then(function(v){
					  console.log(v[0],v[1]);
					  var status = CheckStatus(did);
					  if(status == 1){
						  $("#successDiv").show();
						}
					  console.log("status : " + status);
					  //window.location.href = "../logout.jsp";
					  return delay('i',1000);
				});			
 		});
 		
		var delay = function(r,s){
			return new Promise(function(resolve,reject){
				setTimeout(function(){
					resolve([r,s]);
				},s); 
			});
		};
 
  </script>
</body>
</html>