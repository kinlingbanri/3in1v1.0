package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mem.model.MemService;
import com.mem.model.MemVO;

import utils.EmailUtil;
import utils.SerialPortMessage;

@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SendEmailServlet");
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String method = req.getParameter("method");
		String type = req.getParameter("type");
		String str = req.getParameter("str");
		
		MemService memService = new MemService();
		
		if(type.equals("email")) {
			List<MemVO> memVOs = memService.getMemEmail(str);
			
			if(memVOs.size() == 0) {
				System.out.println("noemail");
				jsonObject.put("state", "noemail");
			}else {
				
				for (MemVO memVO : memVOs) {
					String pwd = memVO.getPassword();
					EmailUtil.sendEmail(memVO.getEmail(), "van@tongya.com.tw",
							"mail.tongya.com.tw", "密碼", "您的密碼為:" + pwd);
				}				
				System.out.println("Send email");	
				jsonObject.put("state", "send");
			}
		}else if(type.equals("phone")) {
			List<MemVO> memVOs = memService.getMemPhone(str);
			
			if(memVOs.size() == 0) {
				System.out.println("nophone");
				jsonObject.put("state", "nophone");
			}else {
				for (MemVO memVO : memVOs) {
					String pwd = memVO.getPassword();
					String phone = memVO.getPhone();
					
					// 這裡要放傳送簡訊的程式碼	
					SerialPortMessage serialPortMessage = new SerialPortMessage();
					serialPortMessage.SendMessage(phone, ("password:" + pwd)); 
					
					/*
					ServletContext application=getServletConfig().getServletContext();
					String jarpath = application.getRealPath("/WEB-INF/lib/RXTX_Demo.jar");
					System.out.println("jarpath : " + jarpath);

					String comPortNum = "COM8";
					String commandStr = "cmd /c java -jar " + jarpath + " " +  comPortNum + " 您的驗證碼為:" + pwd;
					//Runtime.getRuntime().exec( "cmd /c java -jar C:\\Users\\USER\\eclipse-workspace\\3in1\\WebContent\\WEB-INF\\lib\\RXTX_Demo.jar COM8 OOOOKKKK" );
					Runtime.getRuntime().exec(commandStr);
					*/
					

				}				
				System.out.println("Send phone");	
				jsonObject.put("state", "send");
			}
		}else if(type.equals("username")) {
			MemVO memVO = memService.getOneMem(str);
			
			if(memVO == null) {
				System.out.println("nouser");
				jsonObject.put("state", "nouser");
			}else {
				String pwd = memVO.getPassword();
				if(method.equals("email")) {
					EmailUtil.sendEmail(memVO.getEmail(), "van@tongya.com.tw",
							"mail.tongya.com.tw", "密碼", "您的密碼為:" + pwd);
					System.out.println("Send email");	
					jsonObject.put("state", "send");
				}else if(method.equals("phone")) {
					String phone = memVO.getPhone();
					
					// 這裡要放傳送簡訊的程式碼	
					SerialPortMessage serialPortMessage = new SerialPortMessage();
					serialPortMessage.SendMessage(phone, ("password:" + pwd)); 
					
					/*
					ServletContext application=getServletConfig().getServletContext();
					String jarpath = application.getRealPath("/WEB-INF/lib/RXTX_Demo.jar");
					System.out.println("jarpath : " + jarpath);

					String comPortNum = "COM8";
					String commandStr = "cmd /c java -jar " + jarpath + " " +  comPortNum + " 您的驗證碼為:" + pwd;
					//Runtime.getRuntime().exec( "cmd /c java -jar C:\\Users\\USER\\eclipse-workspace\\3in1\\WebContent\\WEB-INF\\lib\\RXTX_Demo.jar COM8 OOOOKKKK" );
					Runtime.getRuntime().exec(commandStr);
					
//					String frontStr = "powershell C:\\Users\\USER\\eclipse-workspace\\3in1\\lib\\ComPortDemo.ps1 ";					
//					String middleStr = "您的密碼為:";
//					String command = frontStr + phone + middleStr + pwd;
//					System.out.println("command : " + command);
//					Runtime.getRuntime().exec(command);
					*/
					System.out.println("Send phone");	
					jsonObject.put("state", "send");
				}
			}
		}
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
