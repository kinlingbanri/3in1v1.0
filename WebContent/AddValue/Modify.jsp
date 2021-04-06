<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%
	/*****	Online *****/
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	System.out.println("Session username : " + memVO.getUsername());
	Object objectDID = session.getAttribute("DID");
	String DID = objectDID.toString();
	System.out.println("Modify.jsp session DID : " +DID );
	
	/*****	Test *****/
// 	String DID = "12323";
// 	System.out.println("Session DID : " + DID);
// 	MemService memService = new MemService();
// 	MemVO memVO = memService.getOneMem("Van007");
	
	String username = memVO.getUsername();
	String email = memVO.getEmail();
	String password = memVO.getPassword();
	
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
	<style>
			.login-wrap{
			width:100%;
			margin:auto;
			max-width:525px;
			min-height:1080px;
			position:relative;
			//background:url("../images/TongYa002.jpg") no-repeat center;
			box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
		}
		.login-html{
			width:100%;
			height:100%;
			position:absolute;
			padding:90px 70px 50px 70px;
			background:rgba(40,57,101,.9);
		}
		.login-html .sign-in-htm,
		.login-html .sign-up-htm{
			top:0;
			left:0;
			right:0;
			bottom:0;
			position:absolute;
			transform:rotateY(180deg);
			backface-visibility:hidden;
			transition:all .4s linear;
		}
		.login-html .sign-in,
		.login-html .sign-up,
		.login-form .group .check{
			display:none;
		}
		.login-html .tab,
		.login-form .group .label,
		.login-form .group .button{
			text-transform:uppercase;
		}
		.login-html .tab{
			color:#DDD;
			font-size:22px;
			margin-right:15px;
			padding-bottom:5px;
			margin:0 15px 10px 0;
			display:inline-block;
			border-bottom:2px solid transparent;
		}
		.login-html .sign-in:checked + .tab,
		.login-html .sign-up:checked + .tab{
			color:#000;
			border-color:#1161ee;
		}
		.login-form{
			min-height:345px;
			position:relative;
			perspective:1000px;
			transform-style:preserve-3d;
		}
		.login-form .group{
			margin-bottom:15px;
		}
		.login-form .group .label,
		.login-form .group .input,
		.login-form .group .button{
			width:100%;
			color:orange;
			display:block;
		}
		.login-form .group .input,
		.login-form .group .button{
			border:none;
			padding:15px 20px;
			border-radius:25px;
			background:rgba(255,255,255,.78);
		}
		.login-form .group input[data-type="password"]{
			text-security:circle;
			-webkit-text-security:circle;
		}
		.login-form .group .label{
			color:#888;
			font-size:12px;
		}
		.login-form .group .button{
			background:#1161ee;
		}
		.login-form .group label .icon{
			width:15px;
			height:15px;
			border-radius:2px;
			position:relative;
			display:inline-block;
			background:rgba(255,255,255,.1);
		}
		.login-form .group label .icon:before,
		.login-form .group label .icon:after{
			content:'';
			width:10px;
			height:2px;
			background:#fff;
			position:absolute;
			transition:all .2s ease-in-out 0s;
		}
		.login-form .group label .icon:before{
			left:3px;
			width:5px;
			bottom:6px;
			transform:scale(0) rotate(0);
		}
		.login-form .group label .icon:after{
			top:6px;
			right:0;
			transform:scale(0) rotate(0);
		}
		.login-form .group .check:checked + label{
			color:#fff;
		}
		.login-form .group .check:checked + label .icon{
			background:#1161ee;
		}
		.login-form .group .check:checked + label .icon:before{
			transform:scale(1) rotate(45deg);
		}
		.login-form .group .check:checked + label .icon:after{
			transform:scale(1) rotate(-45deg);
		}
		.login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{
			transform:rotate(0);
		}
		.login-html .sign-up:checked + .tab + .login-form .sign-up-htm{
			transform:rotate(0);
		}

		.hr{
			height:2px;
			margin:50px 0 40px 0;
			background:rgba(255,255,255,.2);
		}
		.foot-lnk{
			text-align:center;
		}
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
      <span class="h3 mx-1" style="font-weight:bold;">會員資料</span>
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
          <a class="nav-link" href="./AddValue.jsp" style="text-align: right; color: #FFFFFF;">加值服務<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./Record.jsp" style="text-align: right; color: #FFFFFF;">交易紀錄</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" style="text-align: right; color: #FFFFFF;">會員資料</a>
        </li>
				<li class="nav-item">
					<a class="nav-link" href="../logout.jsp" style="text-align: right; color: #FFFFFF;">登出</a>
<!-- 					<button id="menuLogoutBtn" class="nav-link" -->
<!-- 						style="float:right; color: #FFFFFF; padding-top: 6px]; border: none; -->
<!-- 						background: none;">登出</button> -->
        </li>
       </ul>
    </div>
  </nav>
  
  <section style="height: 1080px;">
		<input type="hidden" name="DID" value="<%=DID %>" id="DID">
  	<input type="hidden" name="memVOUsername" value="<%=username %>" id="memVOUsername">
  	<input type="hidden" name="memVOEmail" value="<%=email %>" id="memVOEmail">
  	<input type="hidden" name="memVOPassword" value="<%=password %>" id="memVOPassword">

		<div class="login-wrap" style="box-shadow: none;">
			<div class="login-html" style="background: none;">
				<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab" style="font-size: 20px; font-weight:bold;">修改密碼</label>
				<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab" style="font-size: 20px; font-weight:bold;">修改Email</label>
				<div class="login-form" style="margin-top: 16px;">
					<!-- Login form -->
					<div class="sign-in-htm">
						<div class="group">
							<label for="user" class="label" style="font-size: 18px; ">原密碼</label>
							<input id="password" type="text" class="input" name="password" style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group">
							<label for="pass" class="label" style="font-size: 18px;">新密碼</label>
							<input id="newPassword" type="text" class="input" name="newPassword" style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group">
							<label for="pass" class="label" style="font-size: 18px;">再輸一次新密碼</label>
							<input id="checkNewPassword" type="text" class="input" name="checkNewPassword" style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group" style="margin-top: 48px;">
							<button class="button" id="switchPasswordBtn" style="font-size:16px; font-weight:bold; color:#fff; width:45%; float:left;">確認</button>
							<button class="button" id="backBtn1" style="font-size:16px; font-weight:bold; color:#fff; width:45%; float:right; background-color: #D26900;">返回</button>
<!-- 							<button class="button" id="testBtn" style="font-size:16px; font-weight:bold;">Test</button> -->
						</div>
					</div>
					
					<div class="sign-up-htm">
						<div class="group">
							<label for="user" class="label" style="font-size: 20px;">原Email</label>
							<input id="email" type="text" class="input" style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group">
							<label for="pass" class="label" style="font-size: 20px;">新Email</label>
							<input id="newEmail" type="text" class="input" style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group">
							<label for="pass" class="label" style="font-size: 20px;">再輸入一次新Email</label>
							<input id="checkNewEmail" type="text" class="input" style="margin-top: 6px; font-size: 16px;">
						</div>
						<div class="group" style="margin-top: 48px;">
							<button class="button" id="switchEmailBtn" style="font-size:16px; font-weight:bold; color:#fff; width:45%; float:left;">確認</button>
							<button class="button" id="backBtn2" style="font-size:16px; font-weight:bold; color:#fff; width:45%; float:right; background-color: #D26900;">返回</button>
						</div>
					</div>				

			</div>
		</div>		
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
			$("#email").val("").css('color', '#aaa');
			$("#newEmail").val("").css('color', '#aaa');
			$("#checkNewEmail").val("").css('color', '#aaa');
			$("#password").val("").css('color', '#aaa');
			$("#newPassword").val("").css('color', '#aaa');
			$("#checkNewPassword").val("").css('color', '#aaa');
		});
		
		var memVOUsername = $("#memVOUsername").val();
		var memVOEmail = $("#memVOEmail").val();
		var memVOPassword = $("#memVOPassword").val();
		console.log("Modify.jsp : " + memVOUsername + ";" + memVOEmail + ";" + memVOPassword);

		
		//驗證EAMIL格式
		function emailValidate(){
			var state = 0;
			var newEmail = $("#newEmail").val();
			var mailformat = /^[^\[\]\(\)\\<>:;,@.]+[^\[\]\(\)\\<>:;,@]*@[a-z0-9A-Z]+(([.]?[a-z0-9A-Z]+)*[-]*)*[.]([a-z0-9A-Z]+[-]*)+$/g;
			if(!newEmail.match(mailformat)){
				$("#newEmail").val("Email格式錯誤!").css('color', 'red').show();
				state = 1;
			}
			return state;
		}	

		function validatePassword(){
			var state = 0;
			var password = document.getElementById('password').value;
			var newPassword = document.getElementById('newPassword').value;
			var checkNewPassword = document.getElementById('checkNewPassword').value;
			
			if((password == null) || (password == "")){
				$("#password").val("不能為空白!").css('color', 'red').show();
				state = 1;
			}
			if((newPassword == null) || (newPassword == "")){
				$("#newPassword").val("不能為空白!").css('color', 'red').show();
				state = 1;
			}
			if((checkNewPassword == null) || (checkNewPassword == "")){
				$("#checkNewPassword").val("不能為空白!").css('color', 'red').show();
				state = 1;
			}
			
			if(state == 0){
				if(password == "不能為空白!"){
					state = 1;
				}else{
					if(memVOPassword != password){
						$("#password").val("和原密碼不一致!").css('color', 'red').show();
						state = 1;
					}
				}
				
				if(newPassword == "不能為空白!"){
					state = 1;
				}
				
				if(checkNewPassword == "不能為空白!"){
					state = 1;
				}else{
					if(newPassword != checkNewPassword){
						$("#checkNewPassword").val("和新密碼不一致!").css('color', 'red').show();
						state = 1;
					}
				}
			}
			return state;
		}
		
		function validateEmail(){
			var state = 0;
			var email = document.getElementById('email').value;
			var newEmail = document.getElementById('newEmail').value;
			var checkNewEmail = document.getElementById('checkNewEmail').value;
			
			if((email == null) || (email == "")){
				$("#email").val("不能為空白!").css('color', 'red').show();
				state = 1;
			}
			if((newEmail == null) || (newEmail == "")){
				$("#newEmail").val("不能為空白!").css('color', 'red').show();
				state = 1;
			}
			if((checkNewEmail == null) || (checkNewEmail == "")){
				$("#checkNewEmail").val("不能為空白!").css('color', 'red').show();
				state = 1;
			}
			
			if(state == 0){
				if(email == "不能為空白!"){
					state = 1;
				}else{
					if(memVOEmail != email){
						$("#email").val("和原Email不一致!").css('color', 'red').show();
						state = 1;
					}
				}
				
				if(newEmail == "不能為空白!"){
					state = 1;
				}else{
					state = emailValidate();
				}
				
				if(checkNewPassword == "不能為空白!"){
					state = 1;
				}else{
					if(newEmail != checkNewEmail){
						$("#checkNewEmail").val("和新Email不一致!").css('color', 'red').show();
						state = 1;
					}
				}
			}
			return state;
		}
		
		function ModifyContent(username, type, value){
			var DID = document.getElementById('DID').value;
			console.log("Modify Ajax : " + username + ";" + type + ";" + value + ";" + DID);
			$.ajax({
				  type: 'POST',                    //GET or POST
				  url: "../ModifyServlet",            //請求的頁面
				  cache: false,                   //防止抓到快取的回應
				  data: {
					  username : username,
					  type : type,
					  value : value,
					  DID : DID
				  },
				  success: function (jsonObject) {         //當請求成功後此事件會被呼叫
				  	var state = jsonObject.state;
						console.log("state : " + state);
				  	if(state == "ok"){
				  		console.log("修改成功!");
				  		swal.fire({
				  		    title: '修改成功！',
				  		    text: '3秒後自動關閉!',
				  		    timer: 3000
				  		}).then(
				  		    function () {
				  		    	// handling the promise rejection
				  		    	window.location.href = "../index.jsp";
				  		    },		    	
				  		    function (dismiss) {
				  		        if (dismiss === 'timer') {
				  		            console.log('I was closed by the timer')
				  		        }
				  		    }
				  			)
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
		
		/***** 輸入前清空文字和恢復樣式 *****/
		document.getElementById('password').onfocus = function() {
			if(this.style.color == "red"){
				this.value = "";
				this.style.color = "orange";
			}
		}
		
		document.getElementById('newPassword').onfocus = function() {
			if(this.style.color == "red"){
				this.value = "";
				this.style.color = "orange";
			}
		}
		
		document.getElementById('checkNewPassword').onfocus = function() {
			if(this.style.color == "red"){
				this.value = "";
				this.style.color = "orange";
			}
		}

		document.getElementById('email').onfocus = function() {
			if(this.style.color == "red"){
				this.value = "";
				this.style.color = "orange";
			}
		}
		
		document.getElementById('newEmail').onfocus = function() {
			if(this.style.color == "red"){
				this.value = "";
				this.style.color = "orange";
			}
		}
		
		document.getElementById('checkNewEmail').onfocus = function() {
			if(this.style.color == "red"){
				this.value = "";
				this.style.color = "orange";
			}
		}
		
		
		
		$("#switchPasswordBtn").click(function(){
			var state = validatePassword();
			console.log("Password state : " + state);
			if(state == 0){
				var newPassword = document.getElementById('newPassword').value;
				ModifyContent(memVOUsername, "password", newPassword);
			}			
		});
		
		$("#switchEmailBtn").click(function(){
			var state = validateEmail();
			console.log("Email state : " + state);
			if(state == 0){
				var newEmail = document.getElementById('newEmail').value;
				ModifyContent(memVOUsername, "email", newEmail);
			}
		});
		
		$("#backBtn1").click(function(){
			window.location.href = "./AddValue.jsp";
		});

		$("#backBtn2").click(function(){
			window.location.href = "./AddValue.jsp";
		});

		
		$("#testBtn").click(function(){
			console.log(username + ";" + email + ";" + password);
		});
 
  </script>
</body>
</html>