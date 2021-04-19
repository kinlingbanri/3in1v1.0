<%@page import="com.store.model.StoreService"%>
<%@page import="com.addrecord.model.AddRecordVO"%>
<%@page import="java.util.List"%>
<%@page import="com.addrecord.model.AddRecordService"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    
<%
	/*****	Online *****/
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	System.out.println("Session username : " + memVO.getUsername());
	Object objectDID = session.getAttribute("DID");
	String DID = objectDID.toString();
	System.out.println("AddRecord.jsp session DID : " +DID );
	
	/*****	Test *****/
// 	String DID = "12323";
// 	System.out.println("Session DID : " + DID);
// 	MemService memService = new MemService();
// 	MemVO memVO = memService.getOneMem("Kim");
	
	String username = memVO.getUsername();
	String email = memVO.getEmail();
	String password = memVO.getPassword();
	int point = memVO.getPoint();
	
	// Query By Username
	AddRecordService addRecordService = new AddRecordService();
	List<AddRecordVO> addRecords = addRecordService.getAfter30(memVO);
	System.out.println("addRecords : " + addRecords.size());
	for (AddRecordVO addRecord : addRecords) {
		System.out.print(addRecord.getStoredatetime() + ",");
		System.out.print(addRecord.getPoint() + ",");
		System.out.print(addRecord.getCity() + ",");
		System.out.println(addRecord.getStorename());
	}	
	System.out.println("Record.jsp !");
	
	request.setAttribute("addRecords",addRecords);
	
%>

<jsp:useBean id="stroeservice" class="com.store.model.StoreService"/>

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
      <span class="h3 mx-1" style="font-weight:bold;">交易紀錄</span>
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
          <a class="nav-link" href="#" style="text-align: right; color: #FFFFFF;">交易紀錄</a>
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
		<input type="hidden" name="DID" value="<%=DID %>" id="DID">
  	<input type="hidden" name="memVOUsername" value="<%=username %>" id="memVOUsername">
  	<input type="hidden" name="memVOEmail" value="<%=email %>" id="memVOEmail">
  	<input type="hidden" name="memVOPassword" value="<%=password %>" id="memVOPassword">

		<div style="margin:6px 0 8px 0;">
			<p style="float:left; margin: 2px 0 0 10px; font-weight:bold; font-size:20px; color:#ffa500;">現有點數 <%=point %>點</p>
			<button class="btn btn-outline-warning" style="float:right; margin:0 4px 4px 0; font-weight:bold;" id="returnBtn">返回</button>
		</div>

		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col" style="text-align: center;">#</th>
		      <th scope="col" style="width: 28%;">日期</th>
		      <th scope="col" style="text-align: center;">交易點數</th>
		      <th scope="col">設備位置</th>
				</tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${addRecords}" var="addRecord" varStatus="id">
					<tr>
						<th scope="row" style="text-align: center;">${id.count}</th>
			      <td><fmt:formatDate value="${addRecord.storedatetime }" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			      <td style="text-align: center;">${addRecord.point }</td>
			      <td>${addRecord.city }${addRecord.storename }</td>
			    </tr>
	   		</c:forEach>
		  </tbody>
		</table>
  </section><!-- End Section -->  
  
  <script>
  
		//隠藏右側scrollbar
		$("#body").niceScroll({
		  cursorcolor: "#0026BF",
		  cursorborder: "1px solid #30BAFF", 
		  autohidemode: "hidden",
		  cursorwidth: "10px"
		});
		
		$("#returnBtn").click(function(){
			window.location.href = "./AddValue.jsp";
		});
		
		var memVOUsername = $("#memVOUsername").val();
		var memVOEmail = $("#memVOEmail").val();
		var memVOPassword = $("#memVOPassword").val();
		console.log("Record.jsp : " + memVOUsername + ";" + memVOEmail + ";" + memVOPassword);

  </script>
</body>
</html>