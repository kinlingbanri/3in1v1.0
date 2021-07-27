package com.adminconfig.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.adminconfig.model.AdminConfigService;
import com.adminconfig.model.AdminConfigVO;

@WebServlet("/UpdateAdminConfigServlet")
public class UpdateAdminConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateAdminConfigServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("AddRecordServlet!");
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String ip = req.getParameter("ip");
		String comPort = req.getParameter("comport");
		
		AdminConfigVO adminConfigVO = new AdminConfigService().getAdminConfig();
		adminConfigVO.setIp(ip);
		adminConfigVO.setComPort(comPort);
		new AdminConfigService().updateAdminConfig(adminConfigVO);
		
		jsonObject.put("state", "1");
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
