package com.mem.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

import com.mem.model.MemService;
import com.mem.model.MemVO;

import utils.Random4;
import utils.TXRXSend;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

@WebServlet("/VarificationServlet")
public class VarificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VarificationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("VarificationServlet");
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
				
		String username = req.getParameter("username");
		String code = req.getParameter("code");
		String DID = req.getParameter("DID");
		String MAID = req.getParameter("MAID");

		System.out.println("memservlet req username : " + username);
		System.out.println("memservlet req code : " + code);
		System.out.println("memservlet req DID : " + DID);
		System.out.println("memservlet req MAID : " + MAID);
		
		String sessionDID = req.getSession().getAttribute("DID").toString();
		String sessionMAID = req.getSession().getAttribute("MAID").toString();
		System.out.println("memservlet session DID : " + sessionDID);
		System.out.println("memservlet session MAID : " + sessionMAID);
		
		String machineStr = sessionMAID.substring(0, 3);
		System.out.println("machineStr : " + machineStr);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date current = new Date();
		String currentStr = sdf.format(current);
		
		MemVO memVO = new MemService().getOneMem(username);
		String verificationCode = memVO.getVerificationcode();
		int verification = memVO.getVerification();
		Date verificationDate = memVO.getVerificationdate();
		String verificationDateStr = sdf.format(verificationDate);
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		boolean isSameDay = false;
		if(currentStr.equals(verificationDateStr)) {
			isSameDay = true;
			System.out.println("Equals!!");	
		}

		//測試用
		String testCode = "";
		if(verification == 10) {										//已驗證
			jsonObject.put("state", "3");
			req.getSession().setAttribute("memVO", memVO);
		}else if((isSameDay == true ) && (verification == 4) ) {	//同一天,且驗證3次以上失敗,當天不再提供驗證
			jsonObject.put("state", "1");
		}else if((isSameDay == true ) && (verification == 3) && !(verificationCode.equals(code))) {	//同一天,且驗證3次以上失敗,當天不再提供驗證
			memVO.setVerification(4);
			new MemService().updateMem(memVO);
			jsonObject.put("state", "1");
		}else {
			if(verificationCode.equals(code)) {							//驗證通過
				memVO.setVerification(10);
				jsonObject.put("state", "3");
				Date date = new Date();
				Timestamp nowDate = new Timestamp(date.getTime());
				memVO.setVerificationdate(nowDate);
				new MemService().updateMem(memVO);
				memVO = new MemService().getOneMem(username);
				req.getSession().setAttribute("memVO", memVO);
			}else {				
				if(isSameDay == false){
					memVO.setVerification(2);
				}else {
					if(verification == 1) {
						memVO.setVerification(2);
					}else if(verification == 2) {
						memVO.setVerification(3);
					}else if(verification == 3) {
						memVO.setVerification(4);
					}
				}
				
				String newCode = Random4.getRandomCharArray();
				testCode = newCode;
				memVO.setVerificationcode(newCode);
				Date date = new Date();
				Timestamp nowDate = new Timestamp(date.getTime());
				memVO.setVerificationdate(nowDate);
				new MemService().updateMem(memVO);
				// 這裡要放傳送簡訊的程式碼
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
				
				jsonObject.put("state", "2");
			}
		}

		jsonObject.put("type", machineStr);
		jsonObject.put("testCode", testCode);
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
