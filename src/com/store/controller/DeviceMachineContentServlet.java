package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import com.store.model.DeviceMachineContent;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/DeviceMachineContentServlet")
public class DeviceMachineContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeviceMachineContentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("DeviceMachineContentServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String sidStr = req.getParameter("sid");
		int sid = Integer.parseInt(sidStr);
		
		List<DeviceMachineContent> deviceMachineContents = new ArrayList<DeviceMachineContent>();
		
		StoreVO storeVO = new StoreService().getOneStore(sid);
		//jsonObject.put("storename", storeVO.getName());
		
		List<DeviceVO> deviceVOs = new DeviceService().getAllBySid(sid);
		System.out.print("SID : " + sid);
		System.out.print("deviceVOs count : " + deviceVOs.size());
		
//		for(DeviceVO deviceVO: deviceVOs) {
//			DeviceMachineContent deviceMachineContent = new DeviceMachineContent();
//			deviceMachineContent.setDid(deviceVO.getDid());
//			deviceMachineContent.setMachid(deviceVO.getDid());
//			deviceMachineContent.setName(deviceVO.getLocation());
//			System.out.println("getLocation : " + deviceVO.getLocation());
//			deviceMachineContent.setDeviceNumber(deviceVO.getNumber());
//			deviceMachineContent.setMachineNumber(deviceVO.getNumber());
//			deviceMachineContents.add(deviceMachineContent);
//			//jsonObject.put("deviceMachineContent", deviceMachineContent);
//		}
		
//		for(DeviceVO deviceVO: deviceVOs) {
//			int did = deviceVO.getDid();
//			List<MachineVO> machineVOs = new MachineService().getAllByDid(did);
//			for(MachineVO machineVO : machineVOs) {
//				DeviceMachineContent deviceMachineContent = new DeviceMachineContent();
//				deviceMachineContent.setDid(did);
//				deviceMachineContent.setMachid(machineVO.getMachid());
//				deviceMachineContent.setName(machineVO.getName());
//				System.out.println("machineVO.getName() : " + machineVO.getName());
//				deviceMachineContent.setDeviceNumber(deviceVO.getNumber());
//				deviceMachineContent.setMachineNumber(machineVO.getNumber());
//				deviceMachineContents.add(deviceMachineContent);
//			}
//		}
		
		List<MachineVO> machineVOs = new MachineService().getAllBySid(sid);
		System.out.println("machineVOs count : " + machineVOs.size());
		for(MachineVO machineVO : machineVOs) {
			DeviceMachineContent deviceMachineContent = new DeviceMachineContent();
			int did = machineVO.getDid();
			System.out.println("newDId : " + did);
			DeviceVO deviceVO1 = null;
			deviceVO1 = new DeviceService().getOnByDid(did);
			System.out.println("new deviceVO1 : " + deviceVO1.getDid());
			deviceMachineContent.setDid(did);
			deviceMachineContent.setMachid(machineVO.getMachid());
			deviceMachineContent.setName(machineVO.getName());
			System.out.println("machineVO.getName() : " + machineVO.getName());
			deviceMachineContent.setDeviceNumber(deviceVO1.getNumber());
			deviceMachineContent.setMachineNumber(machineVO.getNumber());
			deviceMachineContents.add(deviceMachineContent);
		}
		
		
		jsonObject.put("storename", storeVO.getName());
		jsonObject.put("deviceMachineContents", deviceMachineContents);
		jsonObject.put("size", deviceMachineContents.size());
		
		
		
		
		
//		DeviceService deviceService = new DeviceService();
//		List<DeviceVO> deviceVOs = deviceService.getAllBySid(sid);
//		int size = deviceVOs.size();
//		
//		
//		if(size >= 1) {
//			jsonObject.put("did_1", deviceVOs.get(0).getDid());
//			jsonObject.put("deviceName_1", deviceVOs.get(0).getLocation());
//			System.out.println("did_1 : " +  deviceVOs.get(0).getDid());
//			System.out.println("Name_1 : " +  deviceVOs.get(0).getLocation());
//		}
//		if(size >= 2) {
//			jsonObject.put("did_2", deviceVOs.get(1).getDid());
//			jsonObject.put("deviceName_2", deviceVOs.get(1).getLocation());
//			System.out.println("deviceName_2 : " +  deviceVOs.get(1).getLocation());
//		}
//
//		jsonObject.put("discount_1_money", storeVO.getDiscount_1_money());
//		jsonObject.put("discount_1_point", storeVO.getDiscount_1_point());
//		jsonObject.put("discount_2_money", storeVO.getDiscount_2_money());
//		jsonObject.put("discount_2_point", storeVO.getDiscount_2_point());
//		jsonObject.put("discount_3_money", storeVO.getDiscount_3_money());
//		jsonObject.put("discount_3_point", storeVO.getDiscount_3_point());
//		jsonObject.put("storename", storeVO.getName());
//		jsonObject.put("size", size);
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
