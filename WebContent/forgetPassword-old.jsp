<%@page import="com.mem.model.MemVO"%>
<%@page import="java.util.List"%>
<%@page import="com.mem.model.MemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
		String checkSessionDID = session.getAttribute("DID").toString();
		System.out.println("checkSessionDID : " + checkSessionDID);
		
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
		body{
			margin:0;
			color:#6a6f8c;
			background:#c8c8c8;
			font:600 16px/18px 'Open Sans',sans-serif;
		}
		*,:after,:before{box-sizing:border-box}
		.clearfix:after,.clearfix:before{content:'';display:table}
		.clearfix:after{clear:both;display:block}
		a{color:inherit;text-decoration:none}

		.login-wrap{
			width:100%;
			margin:auto;
			max-width:525px;
			min-height:1080px;
			position:relative;
			background:url("./images/TongYa002.jpg") no-repeat center;
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
			font-size:22px;
			margin-right:15px;
			padding-bottom:5px;
			margin:0 15px 10px 0;
			display:inline-block;
			border-bottom:2px solid transparent;
		}
		.login-html .sign-in:checked + .tab,
		.login-html .sign-up:checked + .tab{
			color:#fff;
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
			color:#fff;
			display:block;
		}
		.login-form .group .input,
		.login-form .group .button{
			border:none;
			padding:15px 20px;
			border-radius:25px;
			background:rgba(255,255,255,.1);
		}
		.login-form .group input[data-type="password"]{
			text-security:circle;
			-webkit-text-security:circle;
		}
		.login-form .group .label{
			color:#aaa;
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
	
	<script src="./js/jquery-3.3.1.js"></script>
	<script src="./js/nicescroll.js"></script>
<!-- 	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script> -->
	<script src="./js/sweetalert2.js"></script>
</head>

<body id="body">
	<div class="login-wrap">		
		<div class="login-html">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab" style="font-size: 28px; border-style: none; margin-bottom: 24px;">忘記密碼</label>
			<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab" style="font-size: 28px;"></label>
			<div class="login-form" style="margin-top: 16px;">
				<div class="sign-in-htm">
					<div class="group">
						<label for="user" class="label" style="font-size: 20px; ">帳號</label>
						<input id="username" type="text" class="input" name="username" style="margin-top: 6px; font-size: 16px;">
					</div>
					<div style="margin: 16px 0 24px 0;font-size: 24px;color: red;">或</div>
					<div class="group">
						<label for="pass" class="label" style="font-size: 20px;">Email</label>
						<input id="email" type="text" class="input" name="email" style="margin-top: 6px; font-size: 16px;">
					</div>
					<input type="hidden" name="DID" value="<%=request.getAttribute("DID") %>" id="DID">
					<div class="group" style="margin-top: 48px;">
						<button class="button" id="sendPasswordBtn" style="font-size:16px; font-weight:bold; width:45%; float:left;">確認</button>
						<button class="button" id="CancelBtn" style="font-size:16px; font-weight:bold; width:45%; float:right; background-color: #D26900;">取消</button>
					</div>
				</div>
			</div>		
		</div>
	</div>
	<script>
	
		var DID = $("#DID").val();
	
		//初始化各元素
		$(function(){
			$("#username").val("").css('color', '#aaa');
			$("#email").val("").css('color', '#aaa');;
		});
		
		//隠藏右側scrollbar
		$("#body").niceScroll({
        cursorcolor: "#0026BF",
        cursorborder: "1px solid #30BAFF", 
        autohidemode: "hidden",
        cursorwidth: "10px"
    });
		
		//驗證EAMIL格式
		function emailValidate(email){
			var state = 0;
			var mailformat = /^[^\[\]\(\)\\<>:;,@.]+[^\[\]\(\)\\<>:;,@]*@[a-z0-9A-Z]+(([.]?[a-z0-9A-Z]+)*[-]*)*[.]([a-z0-9A-Z]+[-]*)+$/g;
			if(email == null || email == ""){
				$("#email").val("Email不能為空白!").css('color', 'red').show();
				state = 1;
			}else if(!email.match(mailformat)){
				$("#email").val("Email格式錯誤!").css('color', 'red').show();
				state = 1;
			}
			return state;
		}
		
		//用於登入確認帳號或Email是否為空白
		function inputValidate(username, email){
			var state = 0;

			if((username == null || username == "") && (email == null || email == "")){
				$("#username").val("帳號或密碼請擇一填寫").css('color', 'red');
				$("#email").val("帳號或密碼請擇一填寫").css('color', 'red');
				state = 1;
			}else{
				if(email == null || email == ""){
					state = emailValidate(email);
				}
			}
			
			return state;
		}


		
		//輸入前清空文字和恢復樣式
		document.getElementById('username').onfocus = function() {
			if(this.style.color == "red"){
				this.value = "";
				$("#email").val("");
				this.style.color = "#DDDDDD";
			}
		}
		
		document.getElementById('email').onfocus = function() {
			if(this.style.color == "red"){
				this.value = "";
				$("#username").val("");
				this.style.color = "#DDDDDD";
			}
		}
		
		//先行驗證email格式
		document.getElementById('email').onblur = function() {
			emailValidate();
		}
		
		

		$("#CancelBtn").click(function(){
			window.location.href = "./index.jsp";
		});

		$("#sendPasswordBtn").click(function(){
			var username = $("#username").val();
			var email = $("#email").val();			
			var validateState = inputValidate(username, email);
			console.log("validateState : " + validateState);
		});
		
		

		
		
		
		
		
		
		
		
		
		



		
		$("#loginBtn").click(function(){
			var state = loginValidate();
			
			if(state == 0){
				var username = $("#username").val();
				var password = $("#password").val();
				var DID = $("#DID").val();
				
				$.ajax({
				    type: 'POST',                     //GET or POST
				    url: "mem/mem.do",            //請求的頁面
				    cache: false,                    //防止抓到快取的回應
				    data: {      											//要傳送到頁面的參數
				    	username : username,
							password : password,
							DID : DID
				    },
				    success: function (jsonObject) {         //當請求成功後此事件會被呼叫
				    	console.log("jsonObject.state : "+ jsonObject.state);
				    	var validateState = jsonObject.state;
				    	if(validateState == "1"){
								window.location.href = "./AddValue/AddValue.jsp";
							}else if(validateState == "2"){
	 							$("#username").val("無此帳號!").css('color', 'red');
		 					}else if(validateState == "3"){
	 							$("#password").hide();
	 							$("#texLogintPwd").val("密碼錯誤!").css('color', 'red').show();
	 							document.getElementById('checkboxPwd').checked = true;
	 						}
				    },
				    error: function(e){
				    	console.log("e: " + e);
				    },            //當請求失敗後此事件會被呼叫
				    statusCode: {                     //狀態碼處理
				      404: function() {
				        //alert("page not found");
				      }
				    }
				});
			}
		});

		
		

		
		//輸入前清空文字和恢復樣式
		document.getElementById('username').onfocus = function() {
			if(this.style.color == "red"){
				this.value = "";
				this.style.color = "#DDDDDD";
			}
		}
		document.getElementById('email').onfocus = function() {
			if(this.style.color == "red"){
				this.value = "";
				this.style.color = "#DDDDDD";
			}
		}
		
		//如果先前驗證沒過,清除警示文字和恢復樣式
// 		document.getElementById('username').onblur = function() {
// 			var username = $("#username").val();
			
// 			if(username == null || username == ""){
// 				$("#username").val("帳號不能為空白!").css('color', 'red');
// 			}else if(username == "此帳號已註冊!"){
				
// 			}else {

// 				var RegisterState = 1;
				
// 				$.ajax({
// 				    type: 'POST',                     //GET or POST
// 				    url: "./RegisterServlet",            //請求的頁面
// 				    cache: false,                     //防止抓到快取的回應
// 				    data: {
// 				    	RegisterState:RegisterState,
// 				    	registerUsername:registerUsername,
// 				    	DID : DID
// 				    },
// 				    success: function (jsonObject) {         //當請求成功後此事件會被呼叫
// 				    	var state = jsonObject.state;
// 							console.log("state : " + state);
// 				    	if(state == "repeat"){
// 				    		$("#registerUsername").val("此帳號已註冊!").css('color', 'red');
// 				    	}else if(state == "register"){
// 				    		//alert("註冊成功!");
// 				    		//window.location.href = "../index.jsp";
// 				    	}			    	
// 				    },
// 				    error: function(e){
// 				    	console.log("e: " + e);
// 				    },            //當請求失敗後此事件會被呼叫
// 				    statusCode: {                     //狀態碼處理
// 				      404: function() {
// 				        alert("page not found");
// 				      }
// 				    }
// 				});
// 			}			
// 		}
		
		

		
// 		//輸入前清空文字和恢復樣式
// 		document.getElementById('username').onfocus = function() {
// 			if(this.style.color == "red"){
// 				this.value = "";
// 				this.style.color = "#DDDDDD";
// 			}
// 		}
		
		$("#registerBtn").click(function(){
			var state = registerValidate();
			console.log("register state : " + state);
			if(state == 0){
				var registerUsername = $("#registerUsername").val();
				var registerEmail = $("#registerEmail").val();
				var registerPassword = $("#registerPassword").val();
				var DID = $("#DID").val();
				
				var registerUsername = $("#registerUsername").val();
				
				if(registerUsername == null || registerUsername == ""){
					$("#registerUsername").val("帳號不能為空白!").css('color', 'red');
				}else if(registerUsername == "此帳號已註冊!"){
					
				}else {

					var RegisterState = 2;
					
					$.ajax({
					    type: 'POST',                     //GET or POST
					    url: "./RegisterServlet",            //請求的頁面
					    cache: false,                     //防止抓到快取的回應
					    data: {
					    	RegisterState:RegisterState,
					    	registerUsername:registerUsername,
					    	registerEmail:registerEmail,
					    	registerPassword:registerPassword,
					    	DID : DID
					    },
					    success: function (jsonObject) {         //當請求成功後此事件會被呼叫
					    	var state = jsonObject.state;
								console.log("state : " + state);
					    	if(state == "repeat"){
					    		$("#registerUsername").val("此帳號已註冊!").css('color', 'red');
					    	}else if(state == "register"){
					    		console.log("註冊成功!");
					    		swal.fire({
					    		    title: '註冊成功！',
					    		    text: '3秒後自動關閉!',
					    		    timer: 3000
					    		}).then(
					    		    function () {
					    		    	// handling the promise rejection
					    		    	window.location.href = "../3in1/index.jsp";
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
			}
		});
		
		
		
// 		$("#CancelBtn").click(function(){
// 			swal.fire({
// 			    title: '已將密碼傳送至Email！',
// 			    text: '3秒後自動關閉!',
// 			    timer: 3000
// 			}).then(
// 			    function () {
// 			    	// handling the promise rejection
// 			    	window.location.href = "./index.jsp";
// 			    },		    	
// 			    function (dismiss) {
// 			        if (dismiss === 'timer') {
// 			            //console.log('I was closed by the timer');
// 			        }
// 			    }
// 				)
// 		});

	</script>
</body>