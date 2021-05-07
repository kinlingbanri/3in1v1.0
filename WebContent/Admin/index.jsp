<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->

<%@page import="com.store.model.StoreVO"%>
<%@page import="com.store.model.StoreService"%>
<%@page import="com.device.model.DeviceVO"%>
<%@page import="com.device.model.DeviceService"%>
<%@page import="com.addrecord.model.AddRecordService"%>
<%@page import="com.addrecord.model.TodayTotalVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.List"%>



<%
	AddRecordService addRecordService = new AddRecordService();
	TodayTotalVO todayTotalVO = addRecordService.getTodayTotal();
	System.out.print(todayTotalVO.getTotalMoney() + ",");
	System.out.println(todayTotalVO.getTotalPoint());
	
	
	DeviceService deviceService = new DeviceService();
	List<DeviceVO> devices = deviceService.getAll();
	for (DeviceVO device : devices) {
		System.out.print(device.getDid() + ",");
		System.out.print(device.getNumber() + ",");
		System.out.print(device.getCoin() + ",");
		System.out.print(device.getPaper() + ",");
		System.out.print(device.getLocation() + ",");
		System.out.print(device.getRefund() + ",");
		System.out.print(device.getUid() + ",");
		System.out.print(device.getStatus() + ",");
		System.out.print(device.getError() + ",");
		System.out.print(device.getMachid() + ",");
		System.out.print(device.getFreecount() + ",");
		System.out.println(device.getFreecountset());
	}
	
	request.setAttribute("devices", devices);

// 	DeviceVO device = deviceService.getOneDevice("TY00001");
// 	System.out.print(device.getDid() + ",");
// 	System.out.print(device.getNumber() + ",");
// 	System.out.print(device.getCoin() + ",");
// 	System.out.print(device.getPaper() + ",");
// 	System.out.print(device.getLocation() + ",");
// 	System.out.print(device.getRefund() + ",");
// 	System.out.print(device.getUid() + ",");
// 	System.out.print(device.getStatus() + ",");
// 	System.out.print(device.getError() + ",");
// 	System.out.print(device.getMachid() + ",");
// 	System.out.print(device.getFreecount() + ",");
// 	System.out.println(device.getFreecountset());

// 	DeviceVO deviceVO = new DeviceVO();	
// 	deviceVO.setCoin(10);
// 	deviceVO.setPaper(1);
// 	deviceVO.setLocation(new String("三峽北大店".getBytes("ISO-8859-1"), "UTF-8"));
// 	deviceVO.setRefund(0);
// 	deviceVO.setUid(2);
// 	deviceVO.setStatus(0);
// 	deviceVO.setError(0);
// 	deviceVO.setMachid(1);
// 	deviceVO.setFreecount(0);
// 	deviceVO.setFreecountset(20);	
// 	deviceVO.setNumber("TY00031");
// 	deviceVO.setDid(33);
// 	deviceService.updateDeivce(deviceVO);

// 	DeviceVO deviceVO = new DeviceVO();
// 	deviceVO.setNumber("TY00031");
// 	deviceService.deleteDevice(deviceVO);
	
%>

<!DOCTYPE HTML>
<html>
<head>
<title>三合一加值系統後台管理</title>
<link rel="shortcut icon" href="#" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Pooled Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="css/morris.css" type="text/css"/>
<!-- Graph CSS -->
<link href="css/font-awesome.css" rel="stylesheet">
<style>

body{
	font-family: Microsoft JhengHei;
}

.devicelist:hover {
	  background: #f2f3ff;
	  outline: none;
	  cursor: pointer;
}

td {
  text-align: center;
  vertical-align: middle;
}
	
.redCircle{
	width:16px;height:16px;
	border-radius:50%;
	background-color:red;
	margin: 0px auto;
}

.greenCircle{
	width:16px;height:16px;
	border-radius:50%;
	background-color:green;
	margin: 0px auto;
}

.yellowCircle{
	width:16px;height:16px;
	border-radius:50%;
	background-color:yellow;
	margin: 0px auto;
}


</style>

<!-- jQuery -->
<script src="js/jquery-2.1.4.min.js"></script>
<!-- //jQuery -->
<link href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet' type='text/css'/>
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<!-- lined-icons -->
<link rel="stylesheet" href="css/icon-font.min.css" type='text/css' />
<!-- //lined-icons -->
</head> 
<body>

<div class="page-container">
	<!--/content-inner-->
	<div class="left-content">
		<div class="mother-grid-inner">
			<!--header start here-->
			<div class="header-main">
				<div class="logo-w3-agile">
					<h1><a href="index.html" style="font-size:20px; font-weight:bold;">三合一加值系統</a></h1>
				</div>
				
				<div class="profile_details w3l" style="float:right;">		
					<ul>
						<li class="dropdown profile_details_drop">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
								<div class="profile_img">
<!-- 									<span class="prfil-img"> -->
<!-- 										<img src="images/in4.jpg" alt=""> -->
<!-- 									</span>  -->
									<div class="user-name">
										<p>Malorum</p>
										<span>Administrator</span>
									</div>
									<i class="fa fa-angle-down"></i>
									<i class="fa fa-angle-up"></i>
									<div class="clearfix"></div>	
								</div>	
							</a>
							<ul class="dropdown-menu drp-mnu">
								<li> <a href="#"><i class="fa fa-cog"></i> Settings</a> </li> 
								<li> <a href="#"><i class="fa fa-user"></i> Profile</a> </li> 
								<li> <a href="#"><i class="fa fa-sign-out"></i> Logout</a> </li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="clearfix"> </div>	
			</div>
<!--heder end here-->



		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="./index.jsp">Home</a>
				<i class="fa fa-angle-right"></i>
			</li>
    </ol>

		<!--four-grids here-->
		<div class="four-grids">
			<div class="col-md-3 four-grid">
				<div class="four-agileits">
					<div class="icon">
						<i class="glyphicon glyphicon-user" aria-hidden="true"></i>
					</div>
					<div class="four-text">
						<h3>今日加值金額</h3>
						<h4><%=todayTotalVO.getTotalMoney() %>元</h4>
					</div>
				</div>
			</div>
			<div class="col-md-3 four-grid">
				<div class="four-agileinfo">
					<div class="icon">
						<i class="glyphicon glyphicon-list-alt" aria-hidden="true"></i>
					</div>
					<div class="four-text">
						<h3>今日加值點數</h3>
						<h4><%=todayTotalVO.getTotalPoint() %>點</h4>
					</div>
				</div>
			</div>
			<div class="col-md-3 four-grid">
				<div class="four-w3ls">
					<div class="icon">
						<i class="glyphicon glyphicon-folder-open" aria-hidden="true"></i>
					</div>
					<div class="four-text">
						<h3>今日消費</h3>
						<h4>12,430</h4>
					</div>
				</div>
			</div>
			<div class="col-md-3 four-grid">
				<div class="four-wthree">
					<div class="icon">
						<i class="glyphicon glyphicon-briefcase" aria-hidden="true"></i>
					</div>
					<div class="four-text">
						<h3>今日兌幣</h3>
						<h4>10</h4>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<!--//four-grids here end-->
		
		<!--photoday-section-->
			<div class="col-sm-4 wthree-crd">
				<div class="card">
					<div class="card-body">
						<div class="widget widget-report-table">
							<header class="widget-header m-b-15"></header>
							<div class="row m-0 md-bg-grey-100 p-l-20 p-r-20">
								<div class="col-md-6 col-sm-6 col-xs-6 w3layouts-aug" style="width: 100%;">
									<h3 style="font-weight:bold;">設備清單(點選該列可進入設定頁面)</h3>
<!--  									<p>圖 -->
 								</div>
<!--  								<div class="col-md-6 col-sm-6 col-xs-6 "> -->
<!--  									<h2 class="text-right c-teal f-300 m-t-20">$21,235</h2> -->
<!--  								</div> -->
							</div>
							<div class="widget-body p-15">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>ID</th>
											<th>設備名稱</th>
											<th style="text-align: center;">狀態</th>
										</tr>
									</thead>
									<tbody>										
										<c:forEach items="${devices}" var="device" varStatus="id">
											<tr  scope="row" style="text-align: center;" class="devicelist">
												<td>${device.number}</td>
												<td>${device.location}</td>												
												<c:choose>
												    <c:when test="${(device.error == 0) and (device.paper < 280)}">
												       <td><div class="greenCircle"></div></td>
												    </c:when>
												    <c:when test="${device.error > 0}">
												       	<td><div class="redCircle"></div></td>
												    </c:when>
												    <c:otherwise>
												       <td><div class="yellowCircle"></div></td>
												    </c:otherwise>
												</c:choose>
									    </tr>
							   		</c:forEach>
									</tbody>
								</table>    
							</div>
						</div>
					</div>
				</div>
			</div>
      <!-- photoday-section end -->

			<!-- /script-for sticky-nav -->
			<script>
			$(document).ready(function() {
				var navoffeset=$(".header-main").offset().top;
				$(window).scroll(function(){
					var scrollpos=$(window).scrollTop(); 
					if(scrollpos >=navoffeset){
						$(".header-main").addClass("fixed");
					}else{
						$(".header-main").removeClass("fixed");
					}
				});			 
			});
			</script>
			<!-- /script-for sticky-nav end -->
			
			<!-- inner block start here -->
			<div class="inner-block"></div>
			<!--inner block end here-->

			<!--COPY rights start here-->
			<div class="copyrights">
	 			<p>Â© 2016 Pooled. All Rights Reserved | Design by  <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>
			</div>	
			<!--COPY rights end here-->

		</div>
	</div>
  
  <!--//content-inner-->
	<!--/sidebar-menu-->
	<div class="sidebar-menu">
		<header class="logo1">
			<a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> 
		</header>
	<div style="border-top:1px ridge rgba(255, 255, 255, 0.15)"></div>
  	<div class="menu">
			<ul id="menu" >
				<li><a href="index.html"><i class="fa fa-tachometer"></i>
				<span>Dashboard</span>
				<div class="clearfix"></div></a></li>
				<li id="menu-academico" ><a href="inbox.html"><i class="fas fa-user-friends"></i><span>會員管理</span><div class="clearfix"></div></a></li>
			<li><a href="gallery.html"><i class="fa fa-picture-o" aria-hidden="true"></i><span>設備管理</span><div class="clearfix"></div></a></li>
			<li id="menu-academico" ><a href="charts.html"><i class="fa fa-bar-chart"></i><span>Charts</span><div class="clearfix"></div></a></li>
			 <li id="menu-academico" ><a href="#"><i class="fa fa-list-ul" aria-hidden="true"></i><span> Short Codes</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
				   <ul id="menu-academico-sub" >
				   <li id="menu-academico-avaliacoes" ><a href="icons.html">Icons</a></li>
					<li id="menu-academico-avaliacoes" ><a href="typography.html">Typography</a></li>
					<li id="menu-academico-avaliacoes" ><a href="faq.html">Faq</a></li>
				  </ul>
				</li>
			<li id="menu-academico" ><a href="errorpage.html"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i><span>Error Page</span><div class="clearfix"></div></a></li>
			  <li id="menu-academico" >
			  		<a href="#">
			  			<link rel="icon" href="./images/user.png" type="image/png" sizes="24x24">
				  		<span> UI Components</span>
				  		<span class="fa fa-angle-right" style="float: right"></span>
				  		<div class="clearfix"></div>
			  		</a>
				   <ul id="menu-academico-sub" >
				   <li id="menu-academico-avaliacoes" ><a href="button.html">Buttons</a></li>
					<li id="menu-academico-avaliacoes" ><a href="grid.html">Grids</a></li>
				  </ul>
				</li>
			 <li><a href="tabels.html"><i class="fa fa-table"></i>  <span>Tables</span><div class="clearfix"></div></a></li>
			<li><a href="maps.html"><i class="fa fa-map-marker" aria-hidden="true"></i>  <span>Maps</span><div class="clearfix"></div></a></li>
	        <li id="menu-academico" ><a href="#"><i class="fa fa-file-text-o"></i>  <span>Pages</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
				 <ul id="menu-academico-sub" >
					<li id="menu-academico-boletim" ><a href="calendar.html">Calendar</a></li>
					<li id="menu-academico-avaliacoes" ><a href="signin.html">Sign In</a></li>
					<li id="menu-academico-avaliacoes" ><a href="signup.html">Sign Up</a></li>
				  </ul>
			 </li>
			<li><a href="#"><i class="fa fa-check-square-o nav_icon"></i><span>Forms</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
			  <ul>
				<li><a href="input.html"> Input</a></li>
				<li><a href="validation.html">Validation</a></li>
			</ul>
			</li>
		  </ul>
		</div>
	  </div>
	  <div class="clearfix"></div>		
	</div>
		<script>
		var toggle = true;
					
		$(".sidebar-icon").click(function() {                
		  if (toggle)
		  {
			$(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
			$("#menu span").css({"position":"absolute"});
		  }
		  else
		  {
			$(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
			setTimeout(function() {
			  $("#menu span").css({"position":"relative"});
			}, 400);
		  }
						
						toggle = !toggle;
		});
		</script>
<!--js -->

<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
<!-- /Bootstrap Core JavaScript -->	   
<!-- morris JavaScript -->	
<script src="js/raphael-min.js"></script>
<script src="js/morris.js"></script>
<script>
	$(document).ready(function() {
		//BOX BUTTON SHOW AND CLOSE
	   jQuery('.small-graph-box').hover(function() {
		  jQuery(this).find('.box-button').fadeIn('fast');
	   }, function() {
		  jQuery(this).find('.box-button').fadeOut('fast');
	   });
	   jQuery('.small-graph-box .box-close').click(function() {
		  jQuery(this).closest('.small-graph-box').fadeOut(200);
		  return false;
	   });
	   
	    //CHARTS
	    function gd(year, day, month) {
			return new Date(year, month - 1, day).getTime();
		}
		
		graphArea2 = Morris.Area({
			element: 'hero-area',
			padding: 10,
        behaveLikeLine: true,
        gridEnabled: false,
        gridLineColor: '#dddddd',
        axes: true,
        resize: true,
        smooth:true,
        pointSize: 0,
        lineWidth: 0,
        fillOpacity:0.85,
			data: [
				{period: '2014 Q1', iphone: 2668, ipad: null, itouch: 2649},
				{period: '2014 Q2', iphone: 15780, ipad: 13799, itouch: 12051},
				{period: '2014 Q3', iphone: 12920, ipad: 10975, itouch: 9910},
				{period: '2014 Q4', iphone: 8770, ipad: 6600, itouch: 6695},
				{period: '2015 Q1', iphone: 10820, ipad: 10924, itouch: 12300},
				{period: '2015 Q2', iphone: 9680, ipad: 9010, itouch: 7891},
				{period: '2015 Q3', iphone: 4830, ipad: 3805, itouch: 1598},
				{period: '2015 Q4', iphone: 15083, ipad: 8977, itouch: 5185},
				{period: '2016 Q1', iphone: 10697, ipad: 4470, itouch: 2038},
				{period: '2016 Q2', iphone: 8442, ipad: 5723, itouch: 1801}
			],
			lineColors:['#ff4a43','#a2d200','#22beef'],
			xkey: 'period',
            redraw: true,
            ykeys: ['iphone', 'ipad', 'itouch'],
            labels: ['All Visitors', 'Returning Visitors', 'Unique Visitors'],
			pointSize: 2,
			hideHover: 'auto',
			resize: true
		});
		
	   
	});
	</script>
</body>
</html>