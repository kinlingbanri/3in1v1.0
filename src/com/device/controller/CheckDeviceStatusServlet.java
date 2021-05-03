package com.device.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.device.model.DeviceService;

@WebServlet("/CheckDeviceStatusServlet")
public class CheckDeviceStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckDeviceStatusServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("CheckDeviceStatusServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String did = req.getParameter("did");
		System.out.println("CheckDeviceStatusServlet number : " + did);
		boolean status = new DeviceService().getStatus(did);
		System.out.println("CheckDeviceStatusServlet status : " + status);
		if(status == true) {
			jsonObject.put("status", "1");
		}else {
			jsonObject.put("status", "2");
		}

		out.write(jsonObject.toString());
		out.flush();
		out.close();
		
	}

}
