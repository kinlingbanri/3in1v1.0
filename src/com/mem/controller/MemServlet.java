package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.adminconfig.model.AdminConfigService;
import com.device.model.DeviceService;
import com.device.model.DeviceVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

import utils.EmailUtil;
import utils.Random4;
import utils.SerialPortMessage;

@WebServlet(name = "MemServlet", urlPatterns = {"/MemServlet"})
public class MemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		//resp.setContentType("text/html;charset=UTF-8");
		//resp.setContentType("text/plain");
		resp.setContentType("application/json");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String DID = req.getParameter("DID");
		String MACHID = req.getParameter("MACHID");

		System.out.println("memservlet req username : " + username);
		System.out.println("memservlet req password : " + password);
		System.out.println("memservlet req DID : " + DID);
		System.out.println("memservlet req MACHID : " + MACHID);
		
		String sessionDID = req.getSession().getAttribute("DID").toString();
		String sessionMACHID = req.getSession().getAttribute("MACHID").toString();
		System.out.println("memservlet session DID : " + sessionDID);
		System.out.println("memservlet sessionMACHID : " + sessionMACHID);
		
		String machineStr = sessionMACHID.substring(0, 2);
		System.out.println("machineStr : " + machineStr);
		
		MemService memService = new MemService();
		MemVO memVO = memService.getOneMem(username);
		System.out.println("memVO");
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		String testCode = "";
		
		if(memVO == null) {
			jsonObject.put("state", "2");
			System.out.println("null : ");
		}else  if( !password.equals(memVO.getPassword())){
			jsonObject.put("state", "3");
			System.out.println("state : " + 3);
		}else if((password.equals(memVO.getPassword()) && (memVO.getVerification() == 10))) {
			
			String type = MACHID.substring(0, 2);
			
			System.out.println("type : " + type);
			
			if(type.equals("TY")) {
				System.out.println(" MemServlet Update Add_life_status!");
				DeviceService deviceService = new DeviceService();
				DeviceVO deviceVO = deviceService.getOneDevice(DID);
				deviceVO.setMid(username);
				deviceService.updateDevice(deviceVO);
				
				
				memVO.setAdd_life_status(1);
				new MemService().updateMem(memVO);
			}
			
			req.getSession().setAttribute("memVO", memVO);
			
			jsonObject.put("state", "1");
			jsonObject.put("type", machineStr);			
			
			System.out.println("verification OK");
		}else if((password.equals(memVO.getPassword()) && (memVO.getVerification() != 10))) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date current = new Date();
			String currentStr = sdf.format(current);
			int verification = memVO.getVerification();
			Date verificationDate = memVO.getVerificationdate();
			String verificationDateStr = sdf.format(verificationDate);
			
			boolean isSameDay = false;
			if(currentStr.equals(verificationDateStr)) {
				isSameDay = true;
				System.out.println("Equals!!");	
			}
			
			if((isSameDay == true ) && (verification > 3) ) {
				
			}else {
				if(isSameDay == false) {
					memVO.setVerification(1);
				}
				String newCode = Random4.getRandomCharArray();
				//測試用
				testCode = newCode;
				
				memVO.setVerificationcode(newCode);
				Timestamp nowDate = new Timestamp(current.getTime());
				memVO.setVerificationdate(nowDate);
				System.out.println("testCode : " + testCode);
				memService.updateMem(memVO);
				System.out.println(" updateMem");
				// 這裡要放傳送簡訊的程式碼
				AdminConfigService adminConfigService = new AdminConfigService();
				String comport = adminConfigService.getAdminConfig().getComPort();
				SerialPortMessage serialPortMessage = new SerialPortMessage();
				serialPortMessage.SendMessage(comport, memVO.getPhone(), ("code:" + newCode));
				
				/*
				ServletContext application=getServletConfig().getServletContext();
				String jarpath = application.getRealPath("/WEB-INF/lib/RXTX_Demo.jar");
				System.out.println("jarpath : " + jarpath);
				
				//以下會出現錯誤:CreateProcess錯誤= 193，％1不是有效的Win32應用程式
				//ProcessBuilder pb = new ProcessBuilder(jarpath, "-jar", "COM8 test");		
				//ProcessBuilder pb = new ProcessBuilder("C:\\Users\\USER\\eclipse-workspace\\3in1\\WebContent\\WEB-INF\\lib\\RXTX_Demo.jar", "-jar", "COM8 test");
				//pb.start();				
				
				//成功
				String comPortNum = "COM8";
				String commandStr = "cmd /c java -jar " + jarpath + " " +  comPortNum + " 您的驗證碼為:" + newCode;
				//Runtime.getRuntime().exec( "cmd /c java -jar C:\\Users\\USER\\eclipse-workspace\\3in1\\WebContent\\WEB-INF\\lib\\RXTX_Demo.jar COM8 OOOOKKKK" );
				Runtime.getRuntime().exec(commandStr);
				*/
				
				EmailUtil.sendEmail(memVO.getEmail(), "van@tongya.com.tw",
						"mail.tongya.com.tw", "驗證碼", "您的驗證碼為 : " + newCode);
			}
			jsonObject.put("state", "4");
		}
		System.out.println("code2 : " + testCode);
		jsonObject.put("username", username);
		jsonObject.put("testCode", testCode);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		System.out.println("MemServlet End ");
	}
}
