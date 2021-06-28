<%@page import="com.mem.model.MemVO"%>
<%@page import="java.util.List"%>
<%@page import="com.mem.model.MemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String DID = request.getParameter("DID");
	String MACHID = request.getParameter("MACHID");
	System.out.println("index.jsp reqDID : " + DID);
	System.out.println("index.jsp reqMACHID : " + MACHID);

	String sessionDID = (String) session.getAttribute("DID");
	String sessionMACHID = (String) session.getAttribute("MACHID");

	System.out.println("index.jsp start sessionDID : " + sessionDID);
	System.out.println("index.jsp start sessionMACHID : " + sessionMACHID);

	if ((DID != null) && (!DID.equals(""))) {
		session.setAttribute("DID", DID);
	}
	if ((MACHID != null) && (!MACHID.equals(""))) {
		session.setAttribute("MACHID", MACHID);
	}
	System.out.println("index.jsp session DID : " + session.getAttribute("DID"));
	System.out.println("index.jsp session MACHID : " + session.getAttribute("MACHID"));

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

* {
	touch-action: pan-y;
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


<!-- 	<script src="./js/jquery-3.3.1.js"></script> -->

<script src="https://code.jquery.com/jquery-2.2.4.js"></script>

<script src="./js/nicescroll.js"></script>
<script src="./js/core.js"></script>
<script src="./js/sweetalert2.js"></script>
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script> -->

</head>

<body id="body">
	<div class="login-wrap">
		<div class="login-html">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked>
			<label id="tab-11" for="tab-1" class="tab" style="font-size: 28px;">登入</label>
			<input	id="tab-2" type="radio" name="tab" class="sign-up">
			<label	id="tab-22"	for="tab-2" class="tab" style="font-size: 28px;">註冊</label>
			<div class="login-form" style="margin-top: 16px;">

				<!-- Login form -->
				<div class="sign-in-htm" id="signin">
					<div class="group">
						<label for="user" class="label" style="font-size: 20px;">帳號</label>
						<input id="username" type="text" class="input" name="username" style="margin-top: 6px; font-size: 16px;">
					</div>
					<div class="group">
						<label for="pass" class="label" style="font-size: 20px;">密碼</label>
						<input id="password" type="password" class="input" data-type="password" name="password"
							style="margin-top: 6px; font-size: 16px;">
						<input id="texLogintPwd" type="text" class="input" name="texLogintPwd"
							style="margin-top: 6px; font-size: 16px;">
					</div>
					<div class="group" style="margin-bottom: 25px;">
						<label for="check"> <input type="checkbox" name="showPwd" id="checkboxPwd">
							顯示密碼
						</label>
					</div>
					<input type="hidden" name="DID" value="<%=DID%>" id="DID">
					<input type="hidden" name="MACHID" value="<%=MACHID%>" id="MACHID">
					<input type="hidden" name="username" value="" id="inputHiddenUsername">
					<div class="group">
						<button class="button" id="loginBtn" style="font-size: 16px; font-weight: bold;">登入</button>
					</div>
					<div class="hr"></div>
					<div class="foot-lnk">
						<a href="./forgetPassword.jsp">忘記密碼?</a>
					</div>
				</div>
				<!-- End Login form -->

				<!-- Register form -->
				<div class="sign-up-htm" id="signup">
					<div class="group" id="signupSuccess" style="text-align: center; font-size: 24px; font-weight: bold; color: #AAAAAA; margin-top: 24px;">
						<p>註冊成功！</p>
						<p style=" line-height: 30px;">請稍後，將進入驗證頁面</p>
					</div>
					<div id="signupForm">
						<div class="group">
							<label for="user" class="label" style="font-size: 20px;">帳號</label>
							<input id="registerUsername" type="text" class="input"
								style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group">
							<label for="email" class="label" style="font-size: 20px;">Email</label>
							<input id="registerEmail" type="email" class="input"
								style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group">
							<label for="email" class="label" style="font-size: 20px;">再輸入一次Email</label>
							<input id="checkRegisterEmail" type="email" class="input"
								style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group">
							<label for="phone" class="label" style="font-size: 20px;">手機號碼</label>
							<input id="registerPhone" type="tel" class="input"
								style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group">
							<label for="phone" class="label" style="font-size: 20px;">再輸入一次號碼</label>
							<input id="checkRegisterPhone" type="tel" class="input"
								style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group">
							<label for="pass" class="label" style="font-size: 20px;">密碼</label>
							<input id="registerPassword" type="text" class="input"
								style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group">
							<label for="pass" class="label" style="font-size: 20px;">再輸入一次密碼</label>
							<input id="checkRegisterPassword" type="text" class="input"
								style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group" style="margin-top: 24px;">
							<button class="button" id="registerBtn"
								style="font-size: 16px; font-weight: bold;">註冊</button>
						</div>
						<div class="hr"></div>
						<div class="foot-lnk">
							<label for="tab-1">已有帳號?</label>
						</div>
					</div>
				</div>
				<!-- End Register form -->
				
				<!-- Verification form -->
				<div class="verification-htm" id="verificationWindow" style="display:none;">
					<div class="group" id="verificationWindow1" style="display:none;">
						<label for="over" class="label" style="font-size: 20px;text-align:center; color:black;">已超過一天3封簡訊上限</label>
						<label for="over" class="label" style="font-size: 20px;text-align:center; color:black;">請明日再驗證</label>
					</div>
					<div class="group" id="verificationWindow2" style="display:none;">
						<label for="over" class="label" style="font-size: 20px;text-align:center; color:red;">驗證碼錯誤</label>
						<label for="over" class="label" style="font-size: 20px;text-align:center; color:red;">已寄送新驗證碼，請重新輸入</label>
					</div>
					<div class="group" id="verificationWindow3" style="display:none;">
						<label for="over" class="label" style="font-size: 20px;text-align:center; color:#AAAAAA;">驗證成功!</label>
						<label for="over" class="label" style="font-size: 20px;text-align:center; color:#AAAAAA; line-height: 28px;">請稍後，將進入登入頁面</label>
					</div>
					<div class="group" id="verificationWindow4" style="text-align:center;">
						<label for="user" class="label" style="font-size: 20px;">輸入4位數的驗證碼</label>
						<input id="verificationcode" type="text" class="input" style="margin-top: 16px; font-size: 16px;  color:#AAAAAA">
					</div>
					<div class="group" id="verificationWindow5">
						<button class="button" id="verificationBtn" style="font-size: 16px; font-weight: bold; margin-top: 32px;">驗證</button>
					</div>
				</div>
				<!-- End Verification form -->

			</div>
			<!-- End login-form -->
			
		</div>
		<!-- End login-html -->
		
	</div>
	
	<!-- Loader Div -->
	<div style="position: fixed; top: 50%; left: 50%; z-index: 99;">
		<div id="loader" style="display: none;"></div>
	</div>
	<!-- End Loader Div -->

	<!-- 	<button class="button" id="testBtn" style="font-size:16px; font-weight:bold;">Test</button> -->

	<script>

	/*************************** 初始化和function ***************************/

	console.log("Into index.jsp!!!");	
	var DID = $("#DID").val();
	var MACHID = $("#MACHID").val();
	console.log("DID=" + DID + "; MACHID=" + MACHID);

	//隠藏右側scrollbar
	$("#body").niceScroll({
		cursorcolor : "#0026BF",
		cursorborder : "1px solid #30BAFF",
		autohidemode : "hidden",
		cursorwidth : "10px"
	});
	
		//初始化各元素
	$(function() {
		if ($('#tab-1').is(":checked")) {
			$("#signin").show();
			$("#signup").hide();
		} else if ($('#tab-2').is(":checked")) {
			$("#signin").hide();
			$("#signup").show();
		}

		$("#texLogintPwd").hide();
		document.getElementById('checkboxPwd').checked = false;
		$("#username").val("").css('color', '#aaa');
		$("#password").val("").css('color', '#aaa');
		$("#texLogintPwd").val("").css('color', '#aaa');

		$("#registerUsername").val("").css('color', '#aaa');
		$("#registerEmail").val("").css('color', '#aaa');
		$("#checkRegisterEmail").val("").css('color', '#aaa');
		$("#registerPhone").val("").css('color', '#aaa');
		$("#checkRegisterPhone").val("").css('color', '#aaa');
		$("#registerPassword").val("").css('color', '#aaa');
		$("#checkRegisterPassword").val("").css('color', '#aaa');

		$("#signupSuccess").hide();
		$("#signupForm").show();
	});

	//倒數計時,時間到自動登出
	function myTimer() {
		if (count == 0) {
			clearInterval(myTimerVar);
			window.location.href = "../3in1/index.jsp?DID=" + DID + "&MACHID=" + MACHID;
		} else {
			count = count - 1;
			console.log("count : " + count);
		}
	}	
	
	
	/*************************** End 初始化 ***************************/
	
	/*************************** 驗證 ***************************/	
	
	function myTimerToVarification() {
		if (count == 0) {
			clearInterval(myTimerVar);
			openValidateDiv();
		} else {
			count = count - 1;
			console.log("count : " + count);
		}
	}
	
	//啟用認證視窗
	function openValidateDiv(){
		document.getElementById("tab-1").style.display = "none";
		document.getElementById("tab-11").style.display = "none";
		document.getElementById("tab-2").style.display = "none";
		document.getElementById("tab-22").style.display = "none";
		document.getElementById("signin").style.display = "none";
		document.getElementById("signup").style.display = "none";
		$("#verificationWindow").show();
	}

	//用於登入確認帳號和密碼是否為空白,再提交後台驗證
	function loginValidate() {
		var state = 0;
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == null || username == "") {
			$("#username").val("帳號不能為空白!").css('color', 'red');
			state = 1;
		}
		if (password == null || password == "") {
			$("#password").hide();
			$("#texLogintPwd").val("密碼不能為空白!").css('color', 'red').show();
			document.getElementById('checkboxPwd').checked = true;
			state = 1;
		}
		return state;
	}

	//驗證EAMIL格式
	function emailValidate() {
		var registerEmail = $("#registerEmail").val();
		var mailformat = /^[^\[\]\(\)\\<>:;,@.]+[^\[\]\(\)\\<>:;,@]*@[a-z0-9A-Z]+(([.]?[a-z0-9A-Z]+)*[-]*)*[.]([a-z0-9A-Z]+[-]*)+$/g;
		if (registerEmail == null || registerEmail == "") {
			$("#registerEmail").val("Email不能為空白!").css('color', 'red')
					.show();
		} else if (!registerEmail.match(mailformat)) {
			$("#registerEmail").val("Email格式錯誤!").css('color', 'red')
					.show();
		}
	}

	//驗證手機格式
	function phoneValidate() {
		var state = 0;
		var registerPhone = $("#registerPhone").val();
		var phoneformat = /^(09)[0-9]{8}$/;
		if (registerPhone == null || registerPhone == "") {
			$("#registerPhone").val("手機號碼不能為空白!").css('color', 'red')
					.show();
			state = 1;
		} else if (!registerPhone.match(phoneformat)) {
			$("#registerPhone").val("手機號碼格式錯誤!").css('color', 'red').show();
			state = 1;
		}
		return state;
	}

	//驗證確認手機格式
	function checkPhoneValidate() {
		var state = 0;
		var registerPhone = $("#registerPhone").val();
		var checkRegisterPhone = $("#checkRegisterPhone").val();
		var phoneformat = /^(09)[0-9]{8}$/;
		if (checkRegisterPhone == null || checkRegisterPhone == "") {
			$("#checkRegisterPhone").val("手機號碼不能為空白!").css('color', 'red')
					.show();
			state = 1;
		} else if (!checkRegisterPhone.match(phoneformat)) {
			$("#checkRegisterPhone").val("手機號碼格式錯誤!").css('color', 'red')
					.show();
			state = 1;
		} else if (registerPhone != checkRegisterPhone) {
			$("#checkRegisterPhone").val("手機號碼不一致!").css('color', 'red')
					.show();
			state = 1;
		}
		return state;
	}


	//用於註冊確認帳號和密碼和EMAIL是否為空白,再提交後台
	function registerValidate() {
		var state = 0;
		var emailState = 0;
		var passwordState = 0;
		var registerUsername = $("#registerUsername").val();
		var registerEmail = $("#registerEmail").val();
		var checkRegisterEmail = $("#checkRegisterEmail").val();
		var registerPassword = $("#registerPassword").val();
		var checkRegisterPassword = $("#checkRegisterPassword").val();
		if (registerUsername == null || registerUsername == "") {
			$("#registerUsername").val("帳號不能為空白!").css('color', 'red');
			state = 1;
		}
		if (registerEmail == null || registerEmail == "") {
			$("#registerEmail").val("Email不能為空白!").css('color', 'red')
					.show();
			state = 1;
			emailState = 1;
		}
		if (checkRegisterEmail == null || checkRegisterEmail == "") {
			$("#checkRegisterEmail").val("Email不能為空白!").css('color', 'red')
					.show();
			state = 1;
			emailState = 1;
		}
		state = phoneValidate();
		state = checkPhoneValidate()
		if (registerPassword == null || registerPassword == "") {
			$("#registerPassword").val("密碼不能為空白!").css('color', 'red')
					.show();
			state = 1;
			passwordState = 1;
		}
		if (checkRegisterPassword == null || checkRegisterPassword == "") {
			$("#checkRegisterPassword").val("密碼不能為空白!").css('color', 'red')
					.show();
			state = 1;
			passwordState = 1;
		}
		if (emailState == 0) {
			emailValidate();
		}
		if (emailState == 0) {
			if ($("#registerEmail").val() != $("#checkRegisterEmail").val()) {
				$("#checkRegisterEmail").val("Email不一致!").css('color',
						'red').show();
				state = 1;
			}
		}
		if (passwordState == 0) {
			if ($("#registerPassword").val() != $("#checkRegisterPassword")
					.val()) {
				$("#checkRegisterPassword").val("密碼不一致!").css('color',
						'red').show();
				state = 1;
			}
		}

		return state;
	}

	/*************************** END 驗證 ***************************/



	/*************************** 登入 ***************************/

	//輸入前清空文字和恢復樣式
	document.getElementById('username').onfocus = function() {
		if (this.style.color == "red") {
			this.value = "";
			this.style.color = "#AAAAAA";
		}
	}
	document.getElementById('texLogintPwd').onfocus = function() {
		if (this.style.color == "red") {
			this.value = "";
			this.style.color = "#AAAAAA";
		}
	}

	//如果先前驗證沒過,清除警示文字和恢復樣式
	document.getElementById('password').onblur = function() {
		document.getElementById('texLogintPwd').value = this.value;
		console.log("password : " + password.value + " ; texLogintPwd : "
				+ texLogintPwd.value);
	}

	//如果先前驗證沒過,清除警示文字和恢復樣式
	document.getElementById('texLogintPwd').onblur = function() {
		document.getElementById('password').value = this.value;
		console.log("password : " + password.value + " ; texLogintPwd : "
				+ texLogintPwd.value);
	}

	//是否切換顯示密碼
	document.getElementById('checkboxPwd').onclick = function() {
		var password = document.getElementById("password");
		var texLogintPwd = document.getElementById("texLogintPwd");
		var checked = this.checked;
		if (checked == true) {
			texLogintPwd.style.display = "block";
			password.style.display = "none";
			texLogintPwd.value = password.value;
		} else if (checked == false) {
			texLogintPwd.style.display = "none";
			password.style.display = "block";
			password.value = texLogintPwd.value;
		}
		console.log("password : " + password.value + " ; texLogintPwd : " + texLogintPwd.value);
	};

	$("#loginBtn").click(function() {
		document.getElementById("loader").style.display = "block";
		var state = loginValidate();

		if (state == 0) {
			var username = $("#username").val();
			var password = $("#password").val();
			var DID = $("#DID").val();
			var MACHID = $("#MACHID").val();

			$.ajax({
				type : 'POST',				//GET or POST
				url : "mem/mem.do",		//請求的頁面
				cache : false, 				//防止抓到快取的回應
				data : { 							//要傳送到頁面的參數
					username : username,
					password : password,
					DID : DID,
					MACHID : MACHID
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					document.getElementById("loader").style.display = "none";
					console.log("jsonObject.username : " + jsonObject.username);
					
					$("#inputHiddenUsername").val(jsonObject.username);
					console.log("jsonObject.state : " + jsonObject.state);
					
					var validateState = jsonObject.state;
					if (validateState == "1") {
						console.log("Login success!");
						var type = jsonObject.type;
						console.log("type length : "	+ type.length);
						
						if (type == "TY") {
							window.location.href = "./AddValue/AddValue.jsp";
							//setTimeout(function(){ window.location.href = "./AddValue/AddValue.jsp"; }, 3000);
						} else if (type == "DR") {
							window.location.href = "./AddValue/MultiConsumption.jsp";
							//setTimeout(function(){ window.location.href = "./AddValue/MultiConsumption.jsp"; }, 3000);
						} else if (type == "WS") {
							window.location.href = "./AddValue/SingleConsumption.jsp";
							//setTimeout(function(){ window.location.href = "./AddValue/SingleConsumption.jsp"; }, 3000);
						}
						
					} else if (validateState == "2") {
						$("#username") .val("無此帳號!").css('color', 'red');
					} else if (validateState == "3") {
						$("#password").hide();
						$("#texLogintPwd").val("密碼錯誤!").css('color', 'red').show();
						document.getElementById('checkboxPwd').checked = true;
					}else if (validateState == "4") {
						openValidateDiv();
						//測試用
						$("#verificationcode").val( jsonObject.code);
					}
				},
				error : function(e) {
					console.log("e: " + e);
				}, //當請求失敗後此事件會被呼叫
				statusCode : { //狀態碼處理
					404 : function() {
						//alert("page not found");
					}
				}
			});
		}
	});

	/*************************** 註冊 ***************************/

	function checkVarificationCode(){
		var state = 0;
		var verificationcode = $("#verificationcode").val();
		var codeformat = /[0-9]{4}$/;			
		if (verificationcode == null || verificationcode == "") {
			$("#verificationcode").val("驗證碼不能為空白!").css('color', 'red');
			state = 1;
		}else if (verificationcode.length != 4) {
			$("#verificationcode").val("驗證碼長度錯誤!請輸入4位數字").css('color', 'red').show();
			state = 1;
		}else if (!verificationcode.match(codeformat)) {
			$("#verificationcode").val("驗證碼格式錯誤!請輸入4位1至9的號碼").css('color', 'red').show();
			state = 1;
		}
		return state;
	}
	
	document.getElementById('verificationcode').onfocus = function() {
		if (this.style.color == "red") {
			this.value = "";
			this.style.color = "#AAAAAA";
		}
	}

	//輸入前清空文字和恢復樣式
	document.getElementById('registerUsername').onfocus = function() {
		if (this.style.color == "red") {
			this.value = "";
			this.style.color = "#AAAAAA";
		}
	}
	document.getElementById('registerEmail').onfocus = function() {
		if (this.style.color == "red") {
			this.value = "";
			this.style.color = "#AAAAAA";
		}
	}
	document.getElementById('checkRegisterEmail').onfocus = function() {
		if (this.style.color == "red") {
			this.value = "";
			this.style.color = "#AAAAAA";
		}
	}
	document.getElementById('registerPhone').onfocus = function() {
		if (this.style.color == "red") {
			this.value = "";
			this.style.color = "#AAAAAA";
		}
	}
	document.getElementById('checkRegisterPhone').onfocus = function() {
		if (this.style.color == "red") {
			this.value = "";
			this.style.color = "#AAAAAA";
		}
	}
	document.getElementById('registerPassword').onfocus = function() {
		if (this.style.color == "red") {
			this.value = "";
			this.style.color = "#AAAAAA";
		}
	}
	document.getElementById('checkRegisterPassword').onfocus = function() {
		if (this.style.color == "red") {
			this.value = "";
			this.style.color = "#AAAAAA";
		}
	}

	//先行驗證email格式
	document.getElementById('registerEmail').onblur = function() {
		emailValidate();
	}
	//先行驗證phone格式
	document.getElementById('registerPhone').onblur = function() {
		phoneValidate();
	}
	//先行驗證check phone格式
	document.getElementById('checkRegisterPhone').onblur = function() {
		checkPhoneValidate();
	}

	//如果先前驗證沒過,清除警示文字和恢復樣式
	document.getElementById('registerUsername').onblur = function() {
		var registerUsername = $("#registerUsername").val();

		if (registerUsername == null || registerUsername == "") {
			$("#registerUsername").val("帳號不能為空白!").css('color', 'red');
		} else if (registerUsername == "此帳號已註冊!") {

		} else {
			var RegisterState = 1;

			$.ajax({
				type : 'POST', //GET or POST
				url : "./RegisterServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : {
					RegisterState : RegisterState,
					registerUsername : registerUsername,
					DID : DID,
					MACHID : MACHID
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫
					var state = jsonObject.state;
					console.log("state : " + state);
					if (state == "repeat") {
						$("#registerUsername").val("此帳號已註冊!").css('color',
								'red');
					} else if (state == "register") {
						//alert("註冊成功!");
						//window.location.href = "../index.jsp";
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
		}
	}

	$("#tab-1").click(function() {
		$("#signup").hide('slow');
		$("#signin").show('slow');
	});

	$("#tab-2").click(function() {
		$("#signup").show('slow');
		$("#signin").hide('slow');
	});

	$("#registerBtn").click(function() {
		var state = registerValidate();
		console.log("register state : " + state);
		if (state == 0) {
			var registerUsername = $("#registerUsername").val();
			var registerEmail = $("#registerEmail").val();
			var registerPhone = $("#registerPhone").val();
			var registerPassword = $("#registerPassword").val();
			var DID = $("#DID").val();
			var MACHID = $("#MACHID").val();

			var registerUsername = $("#registerUsername").val();

			if (registerUsername == null || registerUsername == "") {
				$("#registerUsername").val("帳號不能為空白!").css( 'color', 'red');
			} else if (registerUsername == "此帳號已註冊!") {

			} else {
				var RegisterState = 2;
				document.getElementById("loader").style.display = "block";
				
				$.ajax({
					type : 'POST',							//GET or POST
					url : "./RegisterServlet",	//請求的頁面
					cache : false,							//防止抓到快取的回應
					data : {
						RegisterState : RegisterState,
						registerUsername : registerUsername,
						registerEmail : registerEmail,
						registerPhone : registerPhone,
						registerPassword : registerPassword,
						DID : DID,
						MACHID : MACHID
					},
					success : function(jsonObject) { //當請求成功後此事件會被呼叫
						document.getElementById("loader").style.display = "none";
						var state = jsonObject.state;
						console.log("state : " + state);
						if (state == "repeat") {
							$("#registerUsername").val("此帳號已註冊!").css('color', 'red');
						} else if (state == "register") {
							console.log("註冊成功!");
							$(window).scrollTop(0);
							$("#inputHiddenUsername").val(jsonObject.username);
							$("#signupForm").hide();
							$("#signupSuccess").show();
							$("#tab-11").hide();
							$("#tab-22").hide();
							//count = 3;														
							//myTimerVar = setInterval(function() {	myTimerToVarification(); }, 800);
							
							setTimeout(function(){ window.location.replace("../logout.jsp"); }, 3000);

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
			}
		}
	});

	$("#verificationBtn").click(function() {
		document.getElementById("loader").style.display = "block";
		var state = checkVarificationCode();
		console.log("verificationBtn state : " + state);
		if (state == 0) {
			var verificationcode = $("#verificationcode").val();
			var inputHiddenUsername = $("#inputHiddenUsername").val();
			var DID = $("#DID").val();
			var MACHID = $("#MACHID").val();
			console.log("verificationcode : " + verificationcode);
			console.log("inputHiddenUsername : " + inputHiddenUsername);
			console.log("DID : " + DID);
			console.log("MACHID : " + MACHID);
			$.ajax({
				type : 'POST', //GET or POST
				url : "./VarificationServlet", //請求的頁面
				cache : false, //防止抓到快取的回應
				data : { //要傳送到頁面的參數
					code : verificationcode,
					username : inputHiddenUsername,
					DID : DID,
					MACHID : MACHID
				},
				success : function(jsonObject) { //當請求成功後此事件會被呼叫

					document.getElementById("loader").style.display = "none";
					
					$("#verificationcode").val( jsonObject.testCode);
					console.log("jsonObject.testCode : " + jsonObject.testCode);
					console.log("jsonObject.name : " + jsonObject.name);
					var validateState = jsonObject.state;
					//var validateState = jsonObject.state;
					if (validateState == "3") {

						$("#verificationWindow1").hide();
						$("#verificationWindow2").hide();
						$("#verificationWindow3").show();
						$("#verificationWindow4").hide();
						$("#verificationWindow5").hide();

						var type = jsonObject.type;
						console.log("type" + type);
						if (type == "TY") {
							//window.location.href = "./AddValue/AddValue.jsp";
							setTimeout(function(){ window.location.href = "./AddValue/AddValue.jsp"; }, 3000);
						} else if (type == "DR") {
							//window.location.href = "./AddValue/MultiConsumption.jsp";
							setTimeout(function(){ window.location.href = "./AddValue/MultiConsumption.jsp"; }, 3000);
						} else if (type == "WS") {
							//window.location.href = "./AddValue/SingleConsumption.jsp";
							setTimeout(function(){ window.location.href = "./AddValue/SingleConsumption.jsp"; }, 3000);
						}
					}else if (validateState == "1") {
						$("#verificationcode").val( jsonObject.testCode);
						$("#verificationWindow2").hide();
						$("#verificationWindow3").hide();
						$("#verificationWindow1").show();
					}else if (validateState == "2") {
						$("#verificationcode").val( jsonObject.testCode);
						$("#verificationWindow1").hide();
						$("#verificationWindow3").hide();
						$("#verificationWindow2").show();
					}
				},
				error : function(e) {
					console.log("e: " + e);
				}
			});
		}
	});

	</script>
</body>
</html>