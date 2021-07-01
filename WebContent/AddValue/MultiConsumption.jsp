<%@page import="com.store.model.StoreVO"%>
<%@page import="com.store.model.StoreService"%>
<%@page import="com.machine.model.MachineVO"%>
<%@page import="com.machine.model.MachineService"%>
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
	
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String username = memVO.getUsername();
	MemService memService = new MemService();
	memVO = memService.getOneMem(username);
	int memPoint = memVO.getPoint();
	String DID = (String) session.getAttribute("DID");
	String number = (String)session.getAttribute("MACHID");
	
	System.out.println("Session username : " + username);
	System.out.println("Session memPoint : " + memPoint);
	System.out.println("Session DID : " + DID);
	System.out.println("Session number : " + number);

	DeviceService deviceService = new DeviceService();
	DeviceVO device = deviceService.getOneDevice(DID);
	
	MachineService machineService = new MachineService();
	MachineVO machineVO = machineService.getOneMachineNumber(number);
	int serial = machineVO.getSerial();
	
	StoreService storeService = new StoreService();
	StoreVO storeVO = storeService.getOneStore( device.getSid() );	

	String title = storeVO.getName() + " 烘衣機" + serial + "號";
	System.out.print("title : " + title);
	
	String serviceType = number.substring(0, 2);
	System.out.println("serviceType : " + serviceType);
	String headerStr = "";
	String url = "";
	if(serviceType.equals("TY")){
		headerStr = "加值服務";
	}else if(serviceType.equals("WS")){
		headerStr = "消費服務";
	}else if(serviceType.equals("DR")){
		headerStr = "消費服務";
	}

	
// 	int consumptionPoint = storeVO.getSingle_count();						//消費一次費用,從資料庫查詢
// 	int freecount = consumptionPoint /10;
// 	request.setAttribute("consumptionPoint", consumptionPoint);
	


	System.out.println("MultiConsumption.jsp");

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
<!-- <link rel="stylesheet" href="../css/bootstrap3.3.7.min.css"> -->
<link rel="stylesheet" href="../css/font-awesome.min.css">

<style>
.element::-webkit-scrollbar {
	width: 0 !important
}

.element {
	-ms-overflow-style: none;
}

.element {
	overflow: -moz-scrollbars-none;
}

#matrix {
	margin: 0 auto;
	padding: 5px;
	width: 100%;
}

#matrix tr, td {
	padding: 5px;
}

#matrix button {
	width: 100%;
		/* 		  height: 80px; */
		/* 		  width: 150px; */
		/* 		  border: none; */
		/* 		  font-family: helvetica; */
		/* 		  font-weight: bold; */
		/* 		  background-color: rgba(15, 138, 15, 0.5); */
	}
	
	#matrix button:hover {
	/* 	background-color: rgb(15, 138, 15); */
	}
	
	.main {
	  margin-left: 5px;
	}
	.counter {
	  width: 45px;  
	  border-radius: 0px !important;
	  text-align: center;
	}
	.up_count {
	  margin-bottom: 10px;  
	  margin-left: -4px;
	  border-top-left-radius: 0px;
	  border-bottom-left-radius: 0px;
	} 
	.down_count {
	  margin-bottom: 10px;  
	  margin-right: -4px;
	  border-top-right-radius: 0px;
	  border-bottom-right-radius: 0px;
	}

		/* Center the loader */
		#loader {
			position: absolute;
			left: 50%;
			top: 50%;
			z-index: 1;
			width: 120px;
			height: 120px;
			margin: -76px 0 0 -76px;
			border: 16px solid #f3f3f3;
			border-radius: 50%;
			border-top: 16px solid #3498db;
			-webkit-animation: spin 2s linear infinite;
			animation: spin 2s linear infinite;
		}
		
		@-webkit-keyframes spin {
			0%			{	-webkit-transform: rotate(0deg); }
			100%	{ -webkit-transform: rotate(360deg);	}
		}
		@ keyframes spin {
			0% 	{ transform: rotate(0deg); }
		 100% { transform:rotate(360deg); }
		}

</style>

<script src="../js/jquery-3.3.1.js"></script>
<script src="../js/bootstrap-4.0.0/bootstrap.js"></script>
<script src="../js/nicescroll.js"></script>

<script>



// $('.btn-number').click(function(e){
	
//     e.preventDefault();
    
//     fieldName = $(this).attr('data-field');
//     type      = $(this).attr('data-type');
//     var input = $("input[name='"+fieldName+"']");
//     var currentVal = parseInt(input.val());
//     if (!isNaN(currentVal)) {
//         if(type == 'minus') {
            
//             if(currentVal > input.attr('min')) {
//                 input.val(currentVal - 1).change();
//             } 
//             if(parseInt(input.val()) == input.attr('min')) {
//                 $(this).attr('disabled', true);
//             }

//         } else if(type == 'plus') {

//             if(currentVal < input.attr('max')) {
//                 input.val(currentVal + 1).change();
//             }
//             if(parseInt(input.val()) == input.attr('max')) {
//                 $(this).attr('disabled', true);
//             }

//         }
//     } else {
//         input.val(0);
//     }
// });

</script>

</head>

<body
	style="font-family: Microsoft JhengHei; background-image: url('../images/bg_bggenerator_com.png');">

	<!-- .navbar-expand-{sm|md|lg|xl}決定在哪個斷點以上就出現漢堡式選單 -->
	<!-- navbar-dark 文字顏色 .bg-dark 背景顏色 -->
	<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #0093E9; background-image: linear-gradient(340deg, #0093E9 0%, #80D0C7 100%); //background-color:#91989F  !important;">
		<!-- .navbar-brand 左上LOGO位置 -->
		<a class="navbar-brand" href="#"> <!--       <img src="../images/icons8-menu.svg" width="30" height="30" class="d-inline-block align-top" alt=""> -->
			<span class="h3 mx-1" style="font-weight: bold;"><%=headerStr %></span>
		</a>
		<!-- .navbar-toggler 漢堡式選單按鈕 -->
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<!-- .navbar-toggler-icon 漢堡式選單Icon -->
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- .collapse.navbar-collapse 用於外層中斷點群組和隱藏導覽列內容 -->
		<!-- 選單項目&漢堡式折疊選單 -->
		<div class="collapse navbar-collapse" id="navbarSupportedContent" style="margin-top: 4px;">
			<ul class="navbar-nav mr-auto">
				<!-- active表示當前頁面 -->
				<li class="nav-item active">
					<a class="nav-link" href="#" style="text-align: right; color: #FFFFFF;"><%=headerStr %>
						<span class="sr-only">(current)</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="./Record.jsp" style="text-align: right; color: #FFFFFF;">交易紀錄</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="./Modify.jsp" style="text-align: right; color: #FFFFFF;">會員資料</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="../logout.jsp" style="text-align: right; color: #FFFFFF;">登出</a>
				</li>
			</ul>
		</div>
	</nav>

	<section style="height: 1080px;">
		<div style="text-align: center;">
			<h3 id="storeInfo" style="font-weight: 900;"> <%=title %></h3>
			<p style="margin: 0 0 0 0; font-size: 20px; font-weight: 600;" id="pointMinute">
				每<%=usePoint%>點可使用<%=useMinute%>分鐘
			</p>
		</div>
		<p id="lack" style="text-align: center; margin: 20px 0 16px 0; font-size: 24px; font-weight: 900; color: red; display:none;">
			您的點數不足，請先加值
		</p>
		<div id="divInfo" style="margin:12px 0 12px 0;">
			<div style="margin: 36px 0 12px 0; text-align: center;" id="divNowPoint">
				<div>
					<p style="margin: 0 0 0 0; font-size: 20px;">現有點數</p>
					<p id="memPoint" style="font-weight: bold; color: #FF993C; margin: 0 0 0 0; font-size: 20px;"> <%=memPoint%>點</p>
				</div>
			</div>
		</div>
		
		<!-- bootstrap 3.7 only -->
<!-- 		<div class="center" style="width:150px; margin:40px auto;"> -->
<!-- 			<div class="input-group"> -->
<!-- 	          <span class="input-group-btn"> -->
<!-- 	              <button type="button" class="btn btn-danger btn-number"  data-type="minus" data-field="quant[2]"> -->
<!-- 	                <span class="glyphicon glyphicon-minus"></span> -->
<!-- 	              </button> -->
<!-- 	          </span> -->
<!-- 	          <input type="text" name="quant[2]" class="form-control input-number" value="10" min="1" max="100"> -->
<!-- 	          <span class="input-group-btn"> -->
<!-- 	              <button type="button" class="btn btn-success btn-number" data-type="plus" data-field="quant[2]"> -->
<!-- 	                  <span class="glyphicon glyphicon-plus"></span> -->
<!-- 	              </button> -->
<!-- 	          </span> -->
<!-- 	      </div> -->
<!-- 	</div> -->



		<div class="container py-4" id="divUsePoint" style="padding: 0.5rem 0 0.5rem 0 !important;">
				<p style="margin: 0 0 0 0; font-size: 20px; text-align:center;">本次消費點數</p>
		    <div class="row" style="width:240px; margin:0 auto;">
		        <div class="col-sm-3 mx-auto" style="min-width:172px;">
		            <div class="input-group">
		                <span class="input-group-prepend">
		                    <button type="button" id="btnMinus" class="btn btn-danger btn-number" data-type="minus" data-field="quant[1]">
		                        <span class="fa fa-minus" id="spanMinus"></span>
		                    </button>
		                </span>
		                <input type="text" id="inputUsePoint" name="quant[1]" class="form-control input-number" value="10" min="10" max="1000" style="text-align: center; font-weight: bold; color: #FF993C; font-size: 20px;"disabled="disabled">
		                <span class="input-group-append">
		                    <button type="button" id="btnPlus"  class="btn btn-success  btn-number" data-type="plus" data-field="quant[1]">
		                        <span class="fa fa-plus" id="spanPlus"></span>
		                    </button>
		                </span>
		            </div>
		        </div>
		    </div>
		</div>


		

		
		
		
		
		
		
		
<!-- 		<div style="margin: 28px 0 0 0; text-align: center;" id="divSelect"> -->
<!-- 			<button type="button" class="btn btn-info" id="selectBtn">選擇次數</button> -->
<!-- 		</div> -->
<!-- 		<div style="margin: 24px 0 0 0; text-align: center; width: 100%; display: none;" -->
<!-- 			id="CounterDiv"> -->
<!-- 			<table id="matrix"> -->
<!-- 				<tr> -->
<!-- 					<td><button id="btn1" class="btn btn-warning">1</button></td> -->
<!-- 					<td><button id="btn2" class="btn btn-warning">2</button></td> -->
<!-- 					<td><button id="btn3" class="btn btn-warning">3</button></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td><button id="btn4" class="btn btn-warning">4</button></td> -->
<!-- 					<td><button id="btn5" class="btn btn-warning">5</button></td> -->
<!-- 					<td><button id="btn6" class="btn btn-warning">6</button></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td><button id="btn7" class="btn btn-warning">7</button></td> -->
<!-- 					<td><button id="btn8" class="btn btn-warning">8</button></td> -->
<!-- 					<td><button id="btn9" class="btn btn-warning">9</button></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td><button id="btn10" class="btn btn-warning">10</button></td> -->
<!-- 					<td><button id="btn11" class="btn btn-warning">11</button></td> -->
<!-- 					<td><button id="btn12" class="btn btn-warning">12</button></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</div> -->

		<div style="margin: 0 0 0 0; text-align: center;" id="divService">
			<div id="needPoint">
<!-- 				<p style="margin: 0 0 0 0; font-size: 20px;">本次服務需要</p> -->
<!-- 				<p style="font-weight: bold; color: #FF993C; margin: 0 0 0 0; font-size: 20px; margin-bottom: 10px;" -->
<!-- 					id="consumptionPoint">0點</p> -->
				<p style="margin: 6px 0 0 0; font-size: 20px;">可使用</p>
				<p style="font-weight: bold; color: #FF993C; font-size: 20px;" id="minute"><%=useMinute %>分鐘</p>
			</div>


			<div id="divSuccess" style="display: none;">
				<p style="margin: 0 0 0 0; font-size: 24px; font-weight: bold;" id="nowConsumption"></p>
				<p style="margin: 12px 0 0 0; font-size: 24px; font-weight: bold; color: red;" id="balance"></p>
			</div>

			<div id="timerDiv" style="margin: 0 0 12px 0;">
				<p style="margin: 0 0 0 0; font-size: 16px;">請按確認交易，手動完成服務</p>
				<p style="margin: 0 0 0 0; font-size: 16px;">或於時間結束後由系統自動完成服務</p>
				<p id="timer" style="color: red; font-weight: bold; font-size: 18px;">30秒</p>
			</div>


		  
		  <input type="hidden" name="DID" value="<%=DID %>" id="inputDIid">
		  <input type="hidden" name="MACHID" value="2" id="inputMachid">
		  <input type="hidden" name="username" value="<%=username %>" id="inputUsername">
		  <input type="hidden" name="serial" value="<%= serial %>" id="inputSerial">
		  <input type="hidden" name="memPoint" value="<%=memPoint %>" id="inputMemPoint">
		  <input type="hidden" name="consumptionPoint" value="10" id="inputConsumptionPoint">
		  <input type="hidden" name="freecount" value="1" id="inputFreecount">
		  <input type="hidden" name="number" value="<%=number %>" id="inputNumber">
		  
		  
		  
		  
		  
			
			<div>
				<button type="submit" class="btn btn-success" style="font-weight: bold; margin-right: 12px;" id="confirmBtn">確認交易</button>
				<button class="btn btn-warning" style="font-weight: bold; margin-left: 12px;" id="logoutBtn">取消交易</button>
			</div>
		</div>
		<div style="text-align: center; margin-top: 24px; display: none;" id="successDiv">
			<button class="btn btn-success" style="font-weight: bold; margin-left: 12px;" id="successBtn">完成交易</button>
		</div>
	</section>
	<!-- End Section -->
	
	
	<div style="position: fixed; top: 50%; left: 50%; z-index: 99;">
		<div id="loader" style="display: none;"></div>
	</div>


	<script>
		//初始化各元素
		var count = 30;
		var myTimerVar= setInterval(function(){ myTimer()}, 1000);
		
		//隠藏右側scrollbar
		$("#body").niceScroll({
			cursorcolor : "#0026BF",
			cursorborder : "1px solid #30BAFF",
			autohidemode : "hidden",
			cursorwidth : "10px"
		});
		
		$(function() {

// 			$('button').click(function(e){
// 		        var button_classes, value = +$('.counter').val();
// 		        button_classes = $(e.currentTarget).prop('class');        
// 		        if(button_classes.indexOf('up_count') !== -1){
// 		            value = (value) + 1;            
// 		        } else {
// 		            value = (value) - 1;            
// 		        }
// 		        value = value < 0 ? 0 : value;
// 		        $('.counter').val(value);
// 		    });  
// 		    $('.counter').click(function(){
// 		        $(this).focus().select();
// 		    });

			//$("#inputUsePoint").val(0);
			
			
			//停止使用確認交易按鍵
			//$("#confirmBtn").attr('disabled', true);

			
			if (	<%=memPoint%> < 10) {
				$("#lack").show();
				$("#divSelect").hide();
				$("#needPoint").hide();
				$("#CounterDiv").hide();
				$("#divSuccess").hide();
				$("#timerDiv").hide();
				$("#confirmBtn").hide();
				$("#divUsePoint").hide();

				document.getElementById('logoutBtn').style.marginLeft = "0px";
			} else {
				$("#lack").hide();
			}
		});



		$('.btn-number').click(function(event){
			var point =  parseInt( $("#inputUsePoint").val(), 10);
			
			var usePointState = 1;
			var ID = event.target.id;
			console.log("ID:" + ID);
			if((ID == "btnMinus") || (ID == "spanMinus")){
				if(point >= 20){
					point = point - 10;
					$("#confirmBtn").attr('disabled', false);
				}else if(point < 10){
					usePointState = 0;
					$("#confirmBtn").attr('disabled', true);
				}
				console.log("point:" + point);
				if(point == 0){
					console.log("pointpoint:" + point);
					$("#confirmBtn").attr('disabled', true);
				}
			}else if((ID == "btnPlus") || (ID == "spanPlus")){
				
				if( point < <%=memPoint%>){
					point = point + 10;
				}

<%-- 				if( point >= <%=memPoint%>){ --%>
// 					$("#confirmBtn").attr('disabled', true);
// 				}else{
// 					$("#confirmBtn").attr('disabled', false);
// 				}
				
			}

			if(usePointState == 1){
	 			$("#inputUsePoint").val( point );
	 			var pointStr = point + "點";
	 			var count = point / 10;
	 			console.log("count:" + count);
	 			$("#inputFreecount").val(count);
	 			$("#inputConsumptionPoint").val(point);
	 			$("#consumptionPoint").text(pointStr);
				var minuteStr = (count * <%=useMinute%>) + "分鐘";
	 			$("#minute").text(minuteStr);
				console.log("useMinute:" + <%=useMinute%>);
	 			console.log("minuteStr:" + minuteStr);

	 			var memPointStr = $("#memPoint").text();
	 			var memPoint = memPointStr.substring(0, memPointStr.length - 1);

	 			var balance = memPoint - point;
	 			console.log("balance:" +balance);

// 	 			if (balance < 0) {
// 	 				$("#lack").show();
// 	 				$("#confirmBtn").attr("disabled", true);
// 	 			} else {
// 	 				$("#lack").hide();
// 	 				$("#confirmBtn").attr("disabled", false);
// 	 			}

	 			resetTimer();

			}

		});




		

// 		$("#btnMinus").click(function(){
// 			var point =  parseInt( $("#inputUsePoint").val(), 10);
// 			console.log(point);
// 			if(point >= 10){				
// 				point = point - 10;
				
// 				$("#inputUsePoint").val( point );				
// 				var pointStr = point + "點";
// 				var count = point / 10;
// 				console.log("count:" +count);
// 				$("#inputFreecount").val(count);
// 				$("#inputConsumptionPoint").val(point);
// 				$("#consumptionPoint").text(pointStr);
<%-- 				var minuteStr = (count * <%=useMinute%>) + "分鐘"; --%>
// 				$("#minute").text(minuteStr);
<%-- 				console.log("useMinute:" + <%=useMinute%>); --%>
// 				console.log("minuteStr:" + minuteStr);

// 				var memPointStr = $("#memPoint").text();
// 				var memPoint = memPointStr.substring(0, memPointStr.length - 1);

// 				var balance = memPoint - point;
// 				console.log("balance:" +balance);

// 				if (balance < 0) {
// 					$("#lack").show();
// 					$("#confirmBtn").attr("disabled", true);
// 				} else {
// 					$("#lack").hide();
// 					$("#confirmBtn").attr("disabled", false);
// 				}

// 				resetTimer();
// 			}else{

// 			}
			
			
// 		});

// 		$("#btnPlus").click(function(){
// 			var point =  parseInt( $("#inputUsePoint").val(), 10);
// 			console.log(point);
// 			point = point + 10;
// 			console.log("ADD:" + point);
			
// 			$("#inputUsePoint").val( point );
// 			var pointStr = point + "點";
// 			var count = point / 10;
// 			console.log("count:" + count);
// 			$("#inputFreecount").val(count);
// 			$("#inputConsumptionPoint").val(point);
// 			$("#consumptionPoint").text(pointStr);
<%-- 			var minuteStr = (count * <%=useMinute%>) + "分鐘"; --%>
// 			$("#minute").text(minuteStr);
<%-- 			console.log("useMinute:" + <%=useMinute%>); --%>
// 			console.log("minuteStr:" + minuteStr);

// 			var memPointStr = $("#memPoint").text();
// 			var memPoint = memPointStr.substring(0, memPointStr.length - 1);

// 			var balance = memPoint - point;
// 			console.log("balance:" +balance);

// 			if (balance < 0) {
// 				$("#lack").show();
// 				$("#confirmBtn").attr("disabled", true);
// 			} else {
// 				$("#lack").hide();
// 				$("#confirmBtn").attr("disabled", false);
// 			}

// 			resetTimer();
// 		});


		





		
		

		function myTimer() {
			if (count == 0) {
				clearInterval(myTimerVar);
				window.location.href = "../logout.jsp";
			} else {
				count = count - 1;
				console.log("count : " + count);
				var countStr = count + "秒";
				document.getElementById("timer").innerText = countStr;
			}
		}

		function resetTimer() {
			count = 30;
			var countStr = count + "秒";
			document.getElementById("timer").innerText = countStr;
			clearInterval(myTimerVar);
			myTimerVar = setInterval(function() {
				myTimer()
			}, 1000);
		}

		function resetStatusTimer() {
			clearInterval(myCheckStatusTimerVar);
		}


		var consumpTimerVar;
		var consumptioning = 0;
		function Consumption(username, did, machid, freecount, mempoint, serial, consumptionPoint, number, storeInfo){
			$.ajax({
				type : 'POST', //GET or POST
				url : "../FreeCountServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : {
					username : username,
					  did : did,
					  machid : machid,
						freecount : freecount,
						mempoint : mempoint,
						serial : serial,
						consumptionPoint : consumptionPoint,
						number : number,
						consumptioning : consumptioning,
						storeInfo : storeInfo
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫

					state = jsonObject.state;
					console.log("consumption state : " + state);
					if(state == 3){
						//consumptioning = 0;
						clearInterval(consumpTimerVar);
						
						$("#pSinglePoint").hide();
						$("#divInfo").hide();
						$("#timerDiv").hide();
						$("#confirmBtn").hide();
						$("#logoutBtn").hide();
						document.getElementById("loader").style.display = "none";

						var balance =  jsonObject.balance;
						$("#balance").text( "您的餘額為: " + balance + "點" );
						$("#divSuccess").show();

						console.log("consumption End!");
						setTimeout(function(){ window.location.replace("../logout.jsp"); }, 3000);
						
				  }else if(state == 1){
					  consumptioning = 1;
				  }




					
// 					var state = jsonObject.state;
// 					var balance = jsonObject.balance;
// 					if (state == 1) {
// 						$("#divInfo").hide();
// 						$("#pointMinute").hide();
// 						$("#selectBtn").hide();
// 						$("#needPoint").hide();
// 						$("#lack").hide();
// 						$("#divUsePoint").hide();
// 						$("#divInfo").hide();
// 						$("#timerDiv").hide();
// 						$("#confirmBtn").hide();
// 						$("#logoutBtn").hide();
// 						$("#divSuccess").show();

// 						$("#nowConsumption").text(
// 								'本次消費 ' + $("#inputConsumptionPoint").val()	+ '點');

// 						$("#balance").text('您的餘額為 ' + balance + '點');

// 						count = 3;
// 						myTimerVar= setInterval(function(){ myTimer()}, 1000);
// 					}
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

		function CheckStatus(did) {
			var deviceStatus = 0;
			$.ajax({
				type : 'POST', //GET or POST
				url : "../CheckDeviceStatusServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				async : false, //停止非同步
				data : {
					did : did
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					var status = jsonObject.status;
					if (status == 1) {
						deviceStatus = 1;
						console.log("CheckStatus : " + deviceStatus);
					}
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
			return deviceStatus;
		}

		var historyRecordState = 0;
		function historyRecord(username, did, machid, freecount, consumptionPoint, number) {
			$.ajax({
				type : 'POST', //POST
				url : "../HistoryRecordServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				async : false, //停止非同步
				data : {
					username : username,
					did : did,
					machid : machid,
					point : consumptionPoint,
					freecount : freecount,
					number : number
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					var status = jsonObject.status;
					if (status == 1) {
						historyRecordState = 1;
					}
					console.log("status : " + status);
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



		$("#selectBtn").click(function() {
			$("#CounterDiv").slideToggle();
		});

		$(".btn-warning").click(function() {
			$("#CounterDiv").slideToggle();
			var count = this.innerHTML;
			var point = count *	<%=usePoint%>;
			var pointStr = point + "點";
			$("#inputFreecount").val(count);
			$("#inputConsumptionPoint").val(point);
			$("#consumptionPoint").text(pointStr);
			var minuteStr = (count * <%=useMinute%>) + "分鐘";
			$("#minute").text(minuteStr);

			var memPointStr = $("#memPoint").text();
			var memPoint = memPointStr.substring(0, memPointStr.length - 1);

			var balance = memPoint - point;
			console.log(balance);

			if (balance < 0) {
				$("#lack").show();
				$("#confirmBtn").attr("disabled", true);
			} else {
				$("#lack").hide();
				$("#confirmBtn").attr("disabled", false);
			}

			resetTimer();
		});

		document.getElementById('logoutBtn').onclick = function() {
			document.getElementById("minute").style.display = "none";
			clearInterval(myTimerVar);
			window.location.href = "../logout.jsp";
		}

		document.getElementById('successBtn').onclick = function() {
			window.location.href = "../logout.jsp";
		}

		$("#confirmBtn").click(function() {
			clearInterval(myTimerVar);
			
			var did =  $("#inputDIid").val();
			var machid =  $("#inputMachid").val();
			var username =  $("#inputUsername").val();
			var serial =  $("#inputSerial").val();
			var mempoint =  $("#inputMemPoint").val();
			var consumptionPoint =  $("#inputConsumptionPoint").val();
			var freecount =  $("#inputFreecount").val();
			var number =  $("#inputNumber").val();
			var storeInfo =  $("#storeInfo").text();

			console.log(did + ";" + machid + ";"+ username + ";"+ serial + ";"+ mempoint + ";" +consumptionPoint + ";"+ freecount + ";" + number + ";");

			document.getElementById("loader").style.display = "block";

			consumpTimerVar = setInterval(function(){
				Consumption(username, did, machid, freecount, mempoint, serial, consumptionPoint, number, storeInfo)
			}, 1000);

// 					var tempState = 0;
// 					delay('a', 0).then(function(v) {
// 						console.log(v[0], v[1]); // 顯示 a 0
// 						var status = CheckStatus(did);
// 						if (status == 1) {
// 							$("#successDiv").show();
// 						}
// 						console.log("status  1: " + status);
// 						return delay('b', 100); // 延遲一秒之後，告訴後面的函示顯示 b 1000
// 					}).then(
// 							function(v) {
// 								console.log(v[0], v[1]); // 顯示 b 1000
// 								var status = CheckStatus(did);
// 								if (status == 1) {
// 									tempState = 1;
// 									$("#successDiv").show();
// 								}
// 								historyRecord(username, did, machid, freecount,
// 										consumptionPoint, number);

// 								console.log("tempState 2: " + tempState);
// 								return delay('c', 1000); // 延遲兩秒之後，告訴後面的函示顯示 c 1000
// 							}).then(function(v) {
// 						console.log(v[0], v[1]); // 顯示 c 1000
// 						var status = CheckStatus(did);
// 						if (status == 1) {
// 							$("#successDiv").show();
// 						}
// 						console.log("status3 : " + status);
// 						return delay('d', 1000);
// 					}).then(function(v) {
// 						console.log(v[0], v[1]);
// 						var status = CheckStatus(did);
// 						if (status == 1) {
// 							$("#successDiv").show();
// 						}
// 						console.log("status 4: " + status);
// 						return delay('e', 1000);
// 					}).then(function(v) {
// 						console.log(v[0], v[1]);
// 						var status = CheckStatus(did);
// 						if (status == 1) {
// 							$("#successDiv").show();
// 						}
// 						console.log("status 5: " + status);
// 						return delay('f', 1000);
// 					}).then(function(v) {
// 						console.log(v[0], v[1]);
// 						var status = CheckStatus(did);
// 						if (status == 1) {
// 							$("#successDiv").show();
// 						}
// 						console.log("status : " + status);
// 						console.log("tempState : " + tempState);
// 						return delay('g', 1000);
// 					}).then(function(v) {
// 						console.log(v[0], v[1]);
// 						var status = CheckStatus(did);
// 						if (status == 1) {
// 							$("#successDiv").show();
// 						}
// 						console.log("status : " + status);
// 						return delay('h', 1000);
// 					}).then(function(v) {
// 						console.log(v[0], v[1]);
// 						var status = CheckStatus(did);
// 						if (status == 1) {
// 							$("#successDiv").show();
// 						}
// 						console.log("status : " + status);
// 						return delay('i', 1000);
// 					}).then(function(v) {
// 						console.log(v[0], v[1]);
// 						var status = CheckStatus(did);
// 						if (status == 1) {
// 							$("#successDiv").show();
// 						}
// 						console.log("status : " + status);
// 						return delay('i', 1000);
// 					}).then(function(v) {
// 						console.log(v[0], v[1]);
// 						var status = CheckStatus(did);
// 						if (status == 1) {
// 							$("#successDiv").show();
// 						}
// 						console.log("status : " + status);
// 						return delay('i', 1000);
// 					}).then(function(v) {
// 						console.log(v[0], v[1]);
// 						var status = CheckStatus(did);
// 						console.log("status : " + status);
// 						console.log("tempState : " + tempState);
// 						if (status == 1) {
// 							$("#successDiv").show();
// 						}
// 						console.log("status : " + status);
// 						if ((status == 0) && (historyRecordState == 1)) {
// 							window.location.href = "../logout.jsp";
// 						}
// 						return delay('i', 1000);
// 					});



				});

// 		var delay = function(r, s) {
// 			return new Promise(function(resolve, reject) {
// 				setTimeout(function() {
// 					resolve([ r, s ]);
// 				}, s);
// 			});
// 		};

		// 		document.getElementById('menuLogoutBtn').onclick = function(){
		// 			window.location.href = "../logout.jsp";
		// 		}
	</script>
</body>
</html>