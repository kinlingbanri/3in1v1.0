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

import utils.HibernateUtil;

@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String username = req.getParameter("username");
		String type = req.getParameter("type");
		String value = req.getParameter("value");
		String DID = req.getParameter("DID");
		String MACHID = req.getParameter("MACHID");
		req.getSession().setAttribute("DID", DID);
		req.getSession().setAttribute("MACHID", MACHID);
		
		System.out.println("username : " + username);
		System.out.println("type : " + type);
		System.out.println("value : " + value);
		System.out.println("DID : " + DID);
		System.out.println("MACHID : " + MACHID);
		
		jsonObject.put("state", "no");
		
		MemService memService = new MemService();
		MemVO memVO = memService.getOneMem(username);		
		
		System.out.println("memVO.getUsername() : " + memVO.getUsername());	
		if(memVO != null) {
			System.out.println("ModifyServlet!");
			
			if(type.equals("password")) {
				memVO.setPassword(value);				
				System.out.println("Modify Password Success!!!");
			}else if(type.equals("phone")) {
				memVO.setPhone(value);
				System.out.println("Modify Phone Success!!!");
			}else if(type.equals("email")) {
				memVO.setEmail(value);
				System.out.println("Modify Email Success!!!");
			}
			new MemService().updateMem(memVO);
			jsonObject.put("state", "ok");
		}
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
