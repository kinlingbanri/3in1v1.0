<%@page import="gnu.io.SerialPort"%>
<%@page import="gnu.io.CommPortIdentifier"%>
<%@page import="java.util.Enumeration"%>
<%@page import="org.hibernate.internal.util.BytesHelper"%>
<%@page import="utils.TXRXSend"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="java.util.List"%>
<%@page import="com.mem.model.MemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String reqDID = request.getParameter("DID");
	String reqMACHID = request.getParameter("MACHID");
	System.out.println("reqDID : " + reqDID);
	System.out.println("reqMACHID : " + reqMACHID);

	if ((reqDID != null)) {
		session.setAttribute("DID", reqDID);
	}

	if ((reqMACHID != null)) {
		session.setAttribute("MACHID", reqMACHID);
	}
	String sessionDID = request.getParameter("DID");
	String sessionMACHID = request.getParameter("MACHID");
	System.out.println("session DID : " + session.getAttribute("DID"));
	System.out.println("session MACHID : " + session.getAttribute("MACHID"));

	MemService memSvc = new MemService();
	List<MemVO> list = memSvc.getAll();

%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>三合一加值系統</title>
<link rel="shortcut icon" href="#" />
<style>
body {
	margin: 0;
	color: #3a3e3c;
	background: #c8c8c8;
	font: 600 16px/18px 'Open Sans', sans-serif;
}

button {
	cursor:pointer;
}

*, :after, :before {
	box-sizing: border-box
}

.clearfix:after, .clearfix:before {
	content: '';
	display: table
}

.clearfix:after {
	clear: both;
	display: block
}

a {
	color: inherit;
	text-decoration: none
}

.login-wrap {
	width: 100%;
	margin: auto;
	max-width: 525px;
	min-height: 1080px;
	position: relative;
	background: url("./images/bg-021.jpg") no-repeat center;
	box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0
		rgba(0, 0, 0, .19);
}

.login-html {
	width: 100%;
	height: 100%;
	position: absolute;
	padding: 90px 70px 50px 70px; //
	background: rgba(40, 57, 101, .9);
}

.login-html .sign-in-htm, .login-html .sign-up-htm {
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	position: absolute;
	transform: rotateY(180deg);
	backface-visibility: hidden;
	transition: all .4s linear;
}

.login-html .sign-in, .login-html .sign-up, .login-form .group .check {
	display: none;
}

.login-html .tab, .login-form .group .label, .login-form .group .button
	{
	text-transform: uppercase;
}

.login-html .tab {
	font-size: 22px;
	margin-right: 15px;
	padding-bottom: 5px;
	margin: 0 15px 10px 0;
	display: inline-block;
	border-bottom: 2px solid transparent;
}

.login-html .sign-in:checked+.tab, .login-html .sign-up:checked+.tab {
	color: #fff;
	border-color: #1161ee;
}

.login-form {
	min-height: 345px;
	position: relative;
	perspective: 1000px;
	transform-style: preserve-3d;
}

.login-form .group {
	margin-bottom: 15px;
}

.login-form .group .label, .login-form .group .input, .login-form .group .button
	{
	width: 100%;
	color: #fff;
	display: block;
}

.login-form .group .input, .login-form .group .button {
	border: none;
	padding: 15px 20px;
	border-radius: 25px;
	background: rgba(255, 255, 255, .5);
}

.login-form .group input[data-type="password"] {
	text-security: circle;
	-webkit-text-security: circle;
}

.login-form .group .label {
	color: #aaa;
	font-size: 12px;
}

.login-form .group .button {
	background: #1161ee;
}

.login-form .group label .icon {
	width: 15px;
	height: 15px;
	border-radius: 2px;
	position: relative;
	display: inline-block;
	background: rgba(255, 255, 255, .1);
}

.login-form .group label .icon:before, .login-form .group label .icon:after
	{
	content: '';
	width: 10px;
	height: 2px;
	background: #fff;
	position: absolute;
	transition: all .2s ease-in-out 0s;
}

.login-form .group label .icon:before {
	left: 3px;
	width: 5px;
	bottom: 6px;
	transform: scale(0) rotate(0);
}

.login-form .group label .icon:after {
	top: 6px;
	right: 0;
	transform: scale(0) rotate(0);
}

.login-form .group .check:checked+label {
	color: #fff;
}

.login-form .group .check:checked+label .icon {
	background: #1161ee;
}

.login-form .group .check:checked+label .icon:before {
	transform: scale(1) rotate(45deg);
}

.login-form .group .check:checked+label .icon:after {
	transform: scale(1) rotate(-45deg);
}

.login-html .sign-in:checked+.tab+.sign-up+.tab+.login-form .sign-in-htm
	{
	transform: rotate(0);
}

.login-html .sign-up:checked+.tab+.login-form .sign-up-htm {
	transform: rotate(0);
}

.hr {
	height: 2px;
	margin: 50px 0 40px 0;
	background: rgba(255, 255, 255, .2);
}

.foot-lnk {
	text-align: center;
}
</style>

<script src="./js/jquery-3.3.1.js"></script>
<script src="./js/nicescroll.js"></script>
<script src="./js/sweetalert2.js"></script>
</head>

<body id="body">
	<div class="login-wrap">
		<div class="login-html">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked>
			<label for="tab-1" class="tab" style="font-size: 24px;">使用Email</label>
			<input id="tab-2" type="radio" name="tab" class="sign-up">
			<label for="tab-2" class="tab" style="font-size: 24px;">使用簡訊</label>

			<div class="login-form" style="margin-top: 16px;">
				<div class="sign-in-htm" id="signin">
					<div class="login-form" style="margin: 16px 0 0 0; min-height: 80px; float: left; width: 100%;">
						<div class="form-check-inline" style="float: left; margin-right: 24px;">
							<label class="form-check-label" style="font-size: 20px;">
								<input type="radio" class="form-check-input" name="optradio" value="1" style="width: 16px; height: 16px;" checked>帳號
							</label>
						</div>
						<div class="form-check-inline" style="float: left;">
							<label class="form-check-label" style="font-size: 20px;">
								<input type="radio" class="form-check-input" name="optradio" value="2" style="width: 16px; height: 16px;">Email
							</label>
						</div>
						<div class="group" style="margin:32px 0 36px 0;">
							<input id="inputEmail" type="text" class="input" name="username" style="margin-top: 18px; font-size: 16px;">
						</div>
						
					</div>

<!-- 					<div class="group" style="margin-bottom: 32px;"> -->
<!-- 						<div id="divEmailUN"> -->
<!-- 							<label for="user" class="label" style="font-size: 24px;width: 48px;height: 48px;margin: 66px 0 0 16px;">帳號</label> -->
<!-- 							<input id="inputEmailUN" type="text" class="input" name="username" style="margin-top: 6px; font-size: 16px;"> -->
<!-- 						</div> -->
<!-- 						<div style="margin-top:16px;  display:none;" id="divEmailEM" > -->
<!-- 							<label for="user" class="label" style="font-size: 20px; width: 72px; margin: 0 0 0 16px;">Email</label> -->
<!-- 							<input id="inputEmailEM" type="text" class="input" name="email" style="margin-top: 6px; font-size: 16px;"> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<input type="hidden" name="DID" 	value="<%=request.getAttribute("DID")%>" id="DID">
					<input type="hidden" name="action" value="getOne_For_Display">
					<div style="height: 48px; color:red; font-size:20px; display:none;" id="emailWarn">處理中...</div>
					<div class="group" style="margin: 180px 0 0 0;">
						<button class="button" id="sendEmailBtn" style="font-size: 16px; font-weight: bold; width: 45%; float: left;" type="button">送出</button>
						<button class="button" id="CancelBtn1" style="font-size: 16px; font-weight: bold; width: 45%; float: right; background-color: #D26900;">返回</button>
						<!-- 						<button class="button" id="testBtn" style="font-size:16px; font-weight:bold;">Test</button> -->
					</div>
					<div class="hr" style="margin: 280px 0 40px 0;"></div>
					<div class="foot-lnk">
						<a href="./index.jsp?DID=<%= sessionDID%>&MACHID=<%= sessionMACHID%>">已有帳號?</a>
					</div>
				</div>
				
				
				

				<div class="sign-up-htm" id="signup">
					<div class="login-form" style="margin: 16px 0 0 0; min-height: 80px; float: left; width: 100%;">
						<div class="form-check-inline" style="float: left; margin-right: 24px;">
							<label class="form-check-label" style="font-size: 20px;">
								<input type="radio" class="form-check-input" name="optradio" value="3" style="width: 16px; height: 16px;">帳號
							</label>
						</div>
						<div class="form-check-inline" style="float: left;">
							<label class="form-check-label" style="font-size: 20px;">
								<input type="radio" class="form-check-input" name="optradio" value="4" style="width: 16px; height: 16px;">手機號碼
							</label>
						</div>
						<div class="group" style="margin:32px 0 36px 0;">
							<input id="inputPhone" type="text" class="input" name="phone" style="margin-top: 18px; font-size: 16px;">
						</div>
					</div>

					<div style="height: 48px; color:red; font-size:20px; display:none;" id="phoneWarn">處理中...</div>
					<div class="group" style="margin: 180px 0 0 0;">
						<button class="button" id="sendPhoneBtn" style="font-size: 16px; font-weight: bold; width: 45%; float: left;" type="button">送出</button>
						<button class="button" id="CancelBtn2" style="font-size: 16px; font-weight: bold; width: 45%; float: right; background-color: #D26900;">返回</button>
					</div>
					<div class="hr" style="margin: 280px 0 40px 0;"></div>
					<div class="foot-lnk">
						<a href="./index.jsp?<%= sessionDID%>&<%= sessionMACHID%>">已有帳號?</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		var DID = $("#DID").val();

		//初始化各元素
		$(function() {
			if ($('#tab-1').is(":checked")) {
				$("#signin").show();
				$("#signup").hide();
			} else if ($('#tab-2').is(":checked")) {
				$("#signin").hide();
				$("#signup").show();
			}
			$("#inputEmail").val("").css('color', '#aaa');
			$("#inputPhone").val("").css('color', '#aaa');
		});

		var typeValue = 0; 
		$(document).on('change', 'input:radio[name="optradio"]', function (event) {
			var value = $("[name='optradio']:checked").val();
			$("#inputEmail").val("").css('color', '#aaa');
			$("#inputPhone").val("").css('color', '#aaa');			
			if(value == 1){
				typeValue = 1;	
			}else if(value == 2){
				typeValue = 2;	
			}else if(value == 3){
				typeValue = 3;
			}else if(value == 4){
				typeValue = 4;	
			}
	  });


		$("#tab-1").click(function() {
			$("#signin").show('slow');
			$("#signup").hide('slow');
			$('input[name="optradio"]').eq(0).prop('checked',true);
		});

		$("#tab-2").click(function() {
			$("#signin").hide('slow');
			$("#signup").show('slow');
			$('input[name="optradio"]').eq(2).prop('checked',true);
		});

		//Hide page right scrollbar
		$("#body").niceScroll({
			cursorcolor : "#0026BF",
			cursorborder : "1px solid #30BAFF",
			autohidemode : "hidden",
			cursorwidth : "10px"
		});

		function ajaxSend(method, type, str) {

			if (method == "email") {
				$("#emailWarn").text("處理中...").show();
			} else if (method == "phone") {
				$("#phoneWarn").text("處理中...").show();
			}

			$.ajax({
				type : 'POST', //GET or POST
				url : "SendEmailServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : { //要傳送到頁面的參數
					method : method,
					type : type,
					str : str
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					
					var validateState = jsonObject.state;
					if (validateState == "send") {
						console.log("jsonObject.state : " + jsonObject.state);
						if (method == "email") {
							$("#emailWarn").text("密碼已送出，3秒後自動跳至登入頁面!").css( "color", "red");
						} else if (method == "phone") {
							$("#phoneWarn").text("密碼已送出，3秒後自動跳至登入頁面!").css( "color", "red");
						}

						setTimeout(function() {
							window.location.href = "./logout.jsp";
						}, 3000);

// 						swal.fire({
// 						    title: '密碼已送出！',
// 						    text: '3秒後自動關閉!',
// 						    timer: 3000
// 						}).then(
// 						    function () {
// 						    	// handling the promise rejection
// 						    	window.location.href = "./index.jsp";
// 						    },		    	
// 						    function (dismiss) {
// 						        if (dismiss === 'timer') {
// 						            console.log('I was closed by the timer')
// 						        }
// 						    }
// 							)
						 
					} else if (validateState == "nouser") {
						if (method == "email") {
							$("#inputEmail").val("無此帳號!").css('color', 'red');
							$("#emailWarn").hide();
						}else if (method == "phone") {
							$("#inputPhone").val("無此帳號!").css('color', 'red');
							$("#phoneWarn").hide();
						}
						$("#emailWarn").hide();
					} else if (validateState == "noemail") {
						$("#inputEmail").val("無此Email!").css('color', 'red');
						$("#emailWarn").hide();
					} else if (validateState == "nophone") {
						$("#inputPhone").val("無此手機號碼!").css('color', 'red');
						$("#phoneWarn").hide();
					}
				},
				error : function(e) {
					console.log("e: " + e);
				}
			});
		}

		//輸入前清空文字和恢復樣式
		document.getElementById('inputEmail').onfocus = function() {
			if (this.style.color == "red") {
				this.value = "";
				this.style.color = "#AAAAAA";
			}
		}
		document.getElementById('inputPhone').onfocus = function() {
			if (this.style.color == "red") {
				this.value = "";
				this.style.color = "#AAAAAA";
			}
		}

// 		//輸入完成後離開input去做驗證
// 		document.getElementById('inputEmail').onblur= function() {
// 			if(this.value == ""){
// 				this.value = "不能為空白";
// 				this.style.color = "red";
// 			}
// 		}

// 		document.getElementById('inputPhone').onblur= function() {
// 			if(this.value == ""){
// 				this.value = "不能為空白";
// 				this.style.color = "red";
// 			}
// 		}
		

		$("#CancelBtn1").click(function() {
			window.location.href = "./index.jsp?DID=<%= sessionDID%>&MACHID=<%= sessionMACHID%>";
		});
		$("#CancelBtn2").click(function() {
			window.location.href = "./index.jsp?DID=<%= sessionDID%>&MACHID=<%= sessionMACHID%>";
		});

		$("#sendEmailBtn").click(function() {
			var value = $("[name='optradio']:checked").val();
			var varificationState = 0;
			var mailformat = /^[^\[\]\(\)\\<>:;,@.]+[^\[\]\(\)\\<>:;,@]*@[a-z0-9A-Z]+(([.]?[a-z0-9A-Z]+)*[-]*)*[.]([a-z0-9A-Z]+[-]*)+$/g;
			console.log("value : " + value);

			var emailStr = document.getElementById('inputEmail').value;
			if((emailStr == "") || (emailStr == null) || (emailStr == "不能為空白!") ){
				$("#inputEmail").val("不能為空白!").css('color', 'red').show();
			}else if ((emailStr == "無此帳號!")) {
				$("#inputEmail").val("無此帳號!").css('color', 'red').show();
	 		}else if ((emailStr == "無此Email!")) {
				$("#inputEmail").val("無此Email!").css('color', 'red').show();
	 		}else if (emailStr == "Email格式錯誤!") {
				$("#inputEmail").val("Email格式錯誤!").css('color', 'red').show();
			}else if (value == 2) {
				if(!emailStr.match(mailformat)){
					$("#inputEmail").val("Email格式錯誤!").css('color', 'red').show();
				}else{
					varificationState = 1;
				}
			} else{
	 			varificationState = 1;
		 	}
		 	
	 		if(varificationState == 1){
		 		var method = "email";
		 		var type = "";
				if(value == 1){
					type = "username";
					console.log("method:" + method + " ; type:" + type + " ; emailStr:" +emailStr);
				}else if(value == 2){
					type = "email";
					console.log("method:" + method + " ; type:" + type + " ; emailStr:" +emailStr);
				}
				
				ajaxSend(method, type, emailStr);
				
		 	}

			
// 			var username = $("#inputEmail").val();
// 			if (username == null || username == "") {
// 				$("#username").val("帳號不能為空白!").css('color', 'red');
// 			} else if (username == "帳號不能為空白!") {
// 				console.log("帳號不能為空白!");
// 			} else {
// 				ajaxSend("email", string);
// 			}

		});

		$("#sendPhoneBtn").click(function() {
			var value = $("[name='optradio']:checked").val();
			var varificationState = 0;
			var mobileformat = /^(09)[0-9]{8}$/;
			console.log("value : " + value);

			var phoneStr = document.getElementById('inputPhone').value;
			if((phoneStr == "") || (phoneStr == null) || (phoneStr == "不能為空白!") ){
				$("#inputPhone").val("不能為空白!").css('color', 'red').show();
			}else if ((phoneStr == "無此帳號!")) {
				$("#inputPhone").val("無此帳號!").css('color', 'red').show();
	 		}else if ((phoneStr == "無此手機號碼!")) {
				$("#inputPhone").val("無此手機號碼!").css('color', 'red').show();
	 		}else if (phoneStr == "手機格式錯誤!") {
				$("#inputPhone").val("手機格式錯誤!").css('color', 'red').show();
			}else if(value == 4){
				if (!phoneStr.match(mobileformat)) {
					$("#inputPhone").val("手機格式錯誤!").css('color', 'red').show();
				}else{
					varificationState = 1;
				}
			}else{
	 			varificationState = 1;
		 	}
		 	
	 		if(varificationState == 1){
		 		var method = "phone";
		 		var type = "";
				if(value == 3){
					type = "username";
					console.log("method:" + method + " ; type:" + type + " ; phoneStr:" +phoneStr);
				}else if(value == 4){
					type = "phone";
					console.log("method:" + method + " ; type:" + type + " ; phoneStr:" +phoneStr);
				}
				
				ajaxSend(method, type, phoneStr);
				
		 	}
		});
	</script>
</body>