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

@WebServlet("/CheckMoneyServlet")
public class CheckMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckMoneyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("CheckMoneyServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String did = req.getParameter("did");
		System.out.println("CheckMoneyServlet number : " + did);
		
		DeviceVO deviceVO = new DeviceService().getCheckMoney(did);
		System.out.print(deviceVO.getDid() + ",");
		System.out.print(deviceVO.getNumber() + ",");
		System.out.print(deviceVO.getAdd_status() + ",");
		System.out.print(deviceVO.getCount_100() + ",");
		System.out.print(deviceVO.getCount_500() + ",");
		System.out.println(deviceVO.getCount_1000());

		jsonObject.put("add_status", deviceVO.getAdd_status());
		jsonObject.put("count_100", deviceVO.getCount_100());
		jsonObject.put("count_500", deviceVO.getCount_500());
		jsonObject.put("count_1000", deviceVO.getCount_1000());

		out.write(jsonObject.toString());
		out.flush();
		out.close();
		
	}

}
