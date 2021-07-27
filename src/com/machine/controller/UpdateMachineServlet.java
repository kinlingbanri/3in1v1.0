package com.machine.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import com.machine.model.MachineService;
import com.machine.model.MachineVO;

@WebServlet("/UpdateMachineServlet")
public class UpdateMachineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateMachineServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("UpdateDeviceServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();

//		var machineObj = {
//				"mid" : mid,
//				"name" : name,
//				"type" : type,
//				"point" : point
//			};

		String machineList = req.getParameter("machineList");
		System.out.println("machineList : " + machineList);
		
		JSONArray jsonArray = new JSONArray(machineList);
		int length = jsonArray.length();
		System.out.println("jsonArray length : " + jsonArray.length());
		
		for(int i = 0; i < length; i++) {
			JSONObject machineJsonObject = jsonArray.getJSONObject(i);
			
			int mid = machineJsonObject.getInt("mid");
			System.out.println("mid : " + mid);
			String name = machineJsonObject.getString("name");
			System.out.println("name : " + name);
			String type = machineJsonObject.getString("type");
			
			int point = machineJsonObject.getInt("point");
			System.out.println("point : " + point);
			
			
			
			MachineVO machineVO = new MachineService().getOneMachine(mid);
			machineVO.setMachid(mid);
			machineVO.setName(name);
			
			System.out.println("type : " + type);
			String number = null;
			if(type.equals("一次性消費")) {
				number = "WS" + String.format("%05d", mid);
				System.out.println("type : " + type);
			}else if(type.equals("連續性消費")) {
				number = "DR" + String.format("%05d", mid);
				System.out.println("type : " + type);
			}
			System.out.println("number : " + number);
			
			machineVO.setNumber(number);;
			machineVO.setType(type);
			machineVO.setPoint(point);
			new MachineService().updateMachine(machineVO);

			jsonObject.put("state", 1);
		}
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
