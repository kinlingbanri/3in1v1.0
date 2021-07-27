package com.device.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.device.model.DeviceService;
import com.device.model.DeviceVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;


@WebServlet("/UpdateDeviceServlet")
public class UpdateDeviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateDeviceServlet() {
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
		
		String sidStr = req.getParameter("sid");
		int sid = Integer.parseInt(sidStr);
		System.out.println("sid : " + sid);

		String deviceList = req.getParameter("deviceList");
		System.out.println("deviceList : " + deviceList);
		
		JSONArray jsonArray = new JSONArray(deviceList);
		int length = jsonArray.length();
		System.out.println("jsonArray length : " + jsonArray.length());
		
		for(int i = 0; i < length; i++) {
			JSONObject devoceJsonObject = jsonArray.getJSONObject(i);
			
			int did = devoceJsonObject.getInt("did");
			System.out.println("did : " + did);
			String devicename = devoceJsonObject.getString("devicename");
			System.out.println("devicename : " + devicename);
			int money1 = devoceJsonObject.getInt("discount1_money");
			System.out.println("money1 : " + money1);
			int money2 = devoceJsonObject.getInt("discount2_money");
			System.out.println("money2 : " + money2);
			int money3 = devoceJsonObject.getInt("discount3_money");
			System.out.println("money3 : " + money3);
			int point1 = devoceJsonObject.getInt("discount1_point");
			System.out.println("point1 : " + point1);
			int point2 = devoceJsonObject.getInt("discount2_point");
			System.out.println("point2 : " + point2);
			int point3 = devoceJsonObject.getInt("discount3_point");
			System.out.println("point3 : " + point3);

			DeviceVO deviceVO = new DeviceService().getOnByDid(did);
			deviceVO.setLocation(devicename);
			new DeviceService().updateDevice(deviceVO);
			
			StoreVO storeVO = new StoreService().getOneStore(sid);
			storeVO.setDiscount_1_money(money1);
			storeVO.setDiscount_1_point(point1);
			storeVO.setDiscount_2_money(money2);
			storeVO.setDiscount_2_point(point2);
			storeVO.setDiscount_3_money(money3);
			storeVO.setDiscount_3_point(point3);
			new StoreService().updateStore(storeVO);
			
			jsonObject.put("state", 1);
		}
		
		
		
		
//		List<MachineVO> machineVOs = new MachineService().getAllBySid(sid);
//		StoreVO storeVO = new StoreService().getOneStore(sid);
//		
//		jsonObject.put("size", machineVOs.size());
//		jsonObject.put("machineVOs", machineVOs);
//		jsonObject.put("storename", storeVO.getName());
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
