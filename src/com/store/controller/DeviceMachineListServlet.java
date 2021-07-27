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
import com.store.model.DeviceMachineListVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/DeviceMachineListServlet")
public class DeviceMachineListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeviceMachineListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("DeviceMachineListServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String sidStr = req.getParameter("sid");
		int sid = Integer.parseInt(sidStr);
		
		//List<DeviceMachineContent> deviceMachineContents = new ArrayList<DeviceMachineContent>();
		List<DeviceMachineListVO> deviceMachineListVOs = new ArrayList<DeviceMachineListVO>();

		
		List<DeviceVO> deviceVOs = new DeviceService().getAllBySid(sid);
		
		for(DeviceVO deviceVO: deviceVOs) {
			DeviceMachineListVO deviceMachineList = new DeviceMachineListVO();
			deviceMachineList.setDid(deviceVO.getDid());
			deviceMachineList.setDeviceName(deviceVO.getLocation());
			deviceMachineList.setDeviceNumber(deviceVO.getNumber());
			deviceMachineList.setMachid(deviceVO.getDid());
			deviceMachineList.setMachineName(deviceVO.getLocation());
			deviceMachineList.setMachineNumber(deviceVO.getNumber());
			deviceMachineListVOs.add(deviceMachineList);
			//jsonObject.put("deviceMachineContent", deviceMachineContent);
		}
		
		for(DeviceVO deviceVO: deviceVOs) {
			int did = deviceVO.getDid();
			List<MachineVO> machineVOs = new MachineService().getAllByDid(did);
			for(MachineVO machineVO : machineVOs) {
				DeviceMachineListVO deviceMachineList = new DeviceMachineListVO();
				deviceMachineList.setDid(deviceVO.getDid());
				deviceMachineList.setDeviceName(deviceVO.getLocation());
				deviceMachineList.setDeviceNumber(deviceVO.getNumber());
				deviceMachineList.setMachid(machineVO.getMachid());
				deviceMachineList.setMachineName(machineVO.getName());
				deviceMachineList.setMachineNumber(machineVO.getNumber());
				deviceMachineListVOs.add(deviceMachineList);
			}
		}

		jsonObject.put("deviceMachineListVOs", deviceMachineListVOs);
		jsonObject.put("size", deviceMachineListVOs.size());
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
