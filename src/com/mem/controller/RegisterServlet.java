package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mem.model.MemService;
import com.mem.model.MemVO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		//resp.setContentType("application/json; charset=UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		
		
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String RegisterState = req.getParameter("RegisterState");
		String registerUsername = req.getParameter("registerUsername");
		String reqDID = req.getParameter("DID");
		System.out.println("Register req DID = " + reqDID);
		String sessionDID = (String)req.getSession().getAttribute("DID");
		System.out.println("Register sessionDID = " + sessionDID);
		req.getSession().setAttribute("DID", sessionDID);
		
		MemService memService = new MemService();
		MemVO memVO = memService.getOneMem(registerUsername);
		if(memVO == null) {
			System.out.println("RegisterState : registert");
			
			if(RegisterState.equals("2")) {
				String registerEmail = req.getParameter("registerEmail");
				String registerPassword = req.getParameter("registerPassword");
				
				memService.addMem(registerUsername, registerEmail, registerPassword, 0);
				System.out.println("Register Success!!!");
			}
			
			jsonObject.put("state", "register");
		}else {

			jsonObject.put("state", "repeat");
		}
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
