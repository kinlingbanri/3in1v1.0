<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
    
<%
	Object object = session.getAttribute("DID");
	if(object != null){
		String DID = object.toString();
		System.out.println("Logout.jsp session DID : " +DID );

		//清除session資料
		session.invalidate();
		
		request.getSession().setAttribute("DID", DID);
		//跳躍到登入畫面
		String returnURL = "0;url=index.jsp?DID=" + DID;
		response.setHeader("refresh", returnURL);
	}


	
	
// 	session.invalidate();
// 	request.getSession();
// 	session.setAttribute("DID", DID);
// 	String url = "index.jsp?DID=" + DID;
// 	response.sendRedirect(url);
%>
    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">	
<title>三合一加值系統</title>
<link rel="shortcut icon" href="#" />
</head>
<body>

</body>
</html>