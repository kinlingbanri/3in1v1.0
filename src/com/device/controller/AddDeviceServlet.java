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
import com.machine.model.MachineService;
import com.machine.model.MachineVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/AddDeviceServlet")
public class AddDeviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddDeviceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("AddMachineServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String name = req.getParameter("devicename");
		System.out.println("name : " + name);
		String sidStr = req.getParameter("sid");
		int sid = Integer.parseInt(sidStr);
		
		DeviceVO deviceVO = new DeviceVO();
		deviceVO.setSid(sid);
		deviceVO.setLocation(name);
		long didLong = new DeviceService().insertGetDid(deviceVO);
		int did = Math.toIntExact(didLong);
		String numberStr = String.format("%05d", did);
		String number = "TY" + numberStr;
		System.out.println("sid : " + sid);
		System.out.println("Device did : " + did);
		System.out.println("number : " + number);

		DeviceVO device = new DeviceService().getOnByDid(did);
		device.setNumber(number);
		System.out.println("updateDeviceVO did : " + device.getDid());
		new DeviceService().updateDevice(device);
		DeviceVO newDeviceVO = new DeviceService().getOnByDid(did);
		
		
		StoreVO storeVO = new StoreService().getOneStore(sid);
	
//		MachineVO newMachineVO = new MachineVO();
//		newMachineVO.setName(name);
//		newMachineVO.setSid(sid);
//		long machidLong = new MachineService().insertGetMachid(newMachineVO);
//		
//		int machid = Math.toIntExact(machidLong);
//		MachineVO machineVO = new MachineService().getOneMachine(machid);
		
		
		
		jsonObject.put("discount_1_money", storeVO.getDiscount_1_money());
		jsonObject.put("discount_1_point", storeVO.getDiscount_1_point());
		jsonObject.put("discount_2_money", storeVO.getDiscount_2_money());
		jsonObject.put("discount_2_point", storeVO.getDiscount_2_point());
		jsonObject.put("discount_3_money", storeVO.getDiscount_3_money());
		jsonObject.put("discount_3_point", storeVO.getDiscount_3_point());
		jsonObject.put("location", newDeviceVO.getLocation());
		jsonObject.put("did", did);
		jsonObject.put("state", 1);
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
