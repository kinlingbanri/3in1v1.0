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
import com.device.model.DeviceVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;


@WebServlet("/FreeCountServlet")
public class FreeCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FreeCountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("FreeCountServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String username = req.getParameter("username");
		String did = req.getParameter("did");
		int machid = Integer.parseInt(req.getParameter("machid"));
		int status = Integer.parseInt(req.getParameter("status"));
		String mempoint = req.getParameter("mempoint");
		String consumptionPoint = req.getParameter("consumptionPoint");
		int freecount = Integer.parseInt(req.getParameter("freecount"));
		String number = req.getParameter("number");
		req.getSession().setAttribute("DID", did);
		req.getSession().setAttribute("MAID", number);
		
		System.out.println("username : " + username);
		System.out.println("did : " + did);
		System.out.println("machid : " + machid);
		System.out.println("status : " + status);
		System.out.println("mempoint : " + mempoint);
		System.out.println("consumptionPoint : " + consumptionPoint);
		System.out.println("freecount : " + freecount);
		System.out.println("number : " + number);
		
		DeviceVO deviceVO = new DeviceService().getOneDevice(did);
		deviceVO.setMachid(machid);
		deviceVO.setFreecount(freecount);
		deviceVO.setStatus(status);
		new DeviceService().updateDevice(deviceVO);
		
		MemVO memVO = new MemService().getOneMem(username);		
		int balance = Integer.parseInt(mempoint) - Integer.parseInt(consumptionPoint);
		memVO.setPoint(balance);
		System.out.println("username : " + memVO.getUsername());
		System.out.println("email : " + memVO.getEmail());
		System.out.println("pwd : " + memVO.getPassword());
		System.out.println("point:" + memVO.getPoint());
		
		new MemService().updateMem(memVO);
		
		jsonObject.put("state", "1");
		jsonObject.put("balance", balance);
		

		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
