package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

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
import utils.Random4;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		//resp.setContentType("text/html; charset=UTF-8");		
		
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String RegisterState = req.getParameter("RegisterState");
		String registerUsername = req.getParameter("registerUsername");
		String reqDID = req.getParameter("DID");
		System.out.println("Register req DID = " + reqDID);
		String reqMACHID = req.getParameter("MACHID");
		System.out.println("Register req MACHID = " + reqMACHID);
		
		String sessionDID = (String)req.getSession().getAttribute("DID");
		System.out.println("Register sessionDID = " + sessionDID);
		req.getSession().setAttribute("DID", sessionDID);
		
		String sessionMACHID = (String)req.getSession().getAttribute("MACHID");
		System.out.println("Register sessionMACHID = " + sessionMACHID);
		req.getSession().setAttribute("MACHID", sessionMACHID);
		
		System.out.println("RegisterState = " + RegisterState);
		
		MemService memService = new MemService();
		MemVO memVO = memService.getOneMem(registerUsername);
		if(memVO == null) {
			System.out.println("RegisterState : registert");
			
			if(RegisterState.equals("2")) {
				String newCode = Random4.getRandomCharArray();
				
				MemVO newMemVO = new MemVO();
				String registerEmail = req.getParameter("registerEmail");
				String registerPassword = req.getParameter("registerPassword");
				String registerPhone = req.getParameter("registerPhone");
				newMemVO.setUsername(registerUsername);
				newMemVO.setEmail(registerEmail);
				newMemVO.setPassword(registerPassword);
				newMemVO.setPhone(registerPhone);
				newMemVO.setPoint(0);
				newMemVO.setAuthority(0);
				newMemVO.setBlack(0);
				newMemVO.setVerification(1);			//預設從1開始記算,錯誤加1,累記至3當天就不再發送
				newMemVO.setVerificationcode(newCode);
				Date current = new Date();
				Timestamp nowDate = new Timestamp(current.getTime());
				newMemVO.setVerificationdate(nowDate);
				memService.insertMem(newMemVO);
				
				// 這裡要放傳送簡訊的程式碼				
				ServletContext application=getServletConfig().getServletContext();
				String jarpath = application.getRealPath("/WEB-INF/lib/RXTX_Demo.jar");
				System.out.println("jarpath : " + jarpath);

				String comPortNum = "COM8";
				String commandStr = "cmd /c java -jar " + jarpath + " " +  comPortNum + " 您的驗證碼為:" + newCode;
				//Runtime.getRuntime().exec( "cmd /c java -jar C:\\Users\\USER\\eclipse-workspace\\3in1\\WebContent\\WEB-INF\\lib\\RXTX_Demo.jar COM8 OOOOKKKK" );
				Runtime.getRuntime().exec(commandStr);
				
				EmailUtil.sendEmail(registerEmail, "van@tongya.com.tw",
						"mail.tongya.com.tw", "驗證碼", "您的驗證碼為 : " + newCode);
				
				//memService.addMem(registerUsername, registerEmail, registerPhone, registerPassword, 0);
				System.out.println("Register Success!!!");
			}
			
			jsonObject.put("state", "register");
			jsonObject.put("username", registerUsername);
		}else {

			jsonObject.put("state", "repeat");
		}
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
