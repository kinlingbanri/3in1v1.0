package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mem.model.MemService;
import com.mem.model.MemVO;

import utils.Random4;

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

		if(verification == 10) {										//已驗證
			jsonObject.put("state", "3");
			req.getSession().setAttribute("memVO", memVO);
		}else if((isSameDay == true ) && (verification == 4) ) {	//同一天,且驗證3次以上失敗,當天不再提供驗證
			jsonObject.put("state", "1");
		}else{	
			if(verificationCode.equals(code)) {
				memVO.setVerification(10);
				jsonObject.put("state", "3");
				Date date = new Date();
				Timestamp nowDate = new Timestamp(date.getTime());
				memVO.setVerificationdate(nowDate);
				new MemService().updateMem(memVO);
				memVO = new MemService().getOneMem(username);
				req.getSession().setAttribute("memVO", memVO);
			}else {
				if((verification == 4) || (verification == 1)) {
					memVO.setVerification(2);
				}else if(verification == 2) {
					memVO.setVerification(3);
				}else if(verification == 3) {
					memVO.setVerification(4);
				}
				memVO.setVerificationcode(Random4.getRandomCharArray());
				Date date = new Date();
				Timestamp nowDate = new Timestamp(date.getTime());
				memVO.setVerificationdate(nowDate);
				new MemService().updateMem(memVO);
				jsonObject.put("state", "2");
			}
			
		}
		


		jsonObject.put("type", machineStr);
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
