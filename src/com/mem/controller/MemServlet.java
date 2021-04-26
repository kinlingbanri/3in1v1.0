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
		//resp.setContentType("text/html;charset=UTF-8");
		//resp.setContentType("text/plain");
		resp.setContentType("application/json");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String DID = req.getParameter("DID");
		String MAID = req.getParameter("MAID");

		System.out.println("memservlet req DID : " + DID);
		System.out.println("memservlet req MAID : " + MAID);
		
		String sessionDID = req.getSession().getAttribute("DID").toString();
		String sessionMAID = req.getSession().getAttribute("MAID").toString();
		System.out.println("memservlet session DID : " + sessionDID);
		System.out.println("memservlet session MAID : " + sessionMAID);
		
		String machineStr = sessionMAID.substring(0, 3);
		System.out.println("machineStr : " + machineStr);
		
		MemService memService = new MemService();
		MemVO memVO = memService.getOneMem(username);
		
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		if(memVO == null) {
			jsonObject.put("state", "2");
		}else {
			if(password.equals(memVO.getPassword())) {
				jsonObject.put("state", "1");
				jsonObject.put("type", machineStr);
				req.getSession().setAttribute("memVO", memVO);
			}else {
				jsonObject.put("state", "3");
			}
		}

		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}
}
