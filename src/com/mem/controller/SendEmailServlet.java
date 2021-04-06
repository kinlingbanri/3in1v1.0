package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mem.model.MemService;
import com.mem.model.MemVO;

import utils.EmailUtil;

@WebServlet("/SendEmail")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String type = req.getParameter("type");
		String value = req.getParameter("value");
		
		MemService memService = new MemService();
		
		if(type.equals("name")) {			
			MemVO memVO = memService.getOneMem(value);
			
			if(memVO == null) {
				System.out.println("nouser");
				jsonObject.put("state", "nouser");
			}else {
				EmailUtil.sendEmail(memVO.getEmail(), "van@tongya.com.tw",
						"mail.tongya.com.tw", "密碼", "您的密碼為:" + memVO.getPassword());
				System.out.println("Send email");	
				jsonObject.put("state", "send");
			}
		}else if(type.equals("email")) {
			List<MemVO> memVOs = memService.getMemEmail(value);
			
			if(memVOs.size() == 0) {
				System.out.println("noemail");
				jsonObject.put("state", "noemail");
			}else {
				for (MemVO memVO2 : memVOs) {
					EmailUtil.sendEmail(memVO2.getEmail(), "van@tongya.com.tw",
							"mail.tongya.com.tw", "密碼", "您的密碼為:" + memVO2.getPassword());
				}				
				System.out.println("Send email");	
				jsonObject.put("state", "send");
			}
		}
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
