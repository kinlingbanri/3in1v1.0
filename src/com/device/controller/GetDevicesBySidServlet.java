package com.device.controller;

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
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/GetDevicesBySidServlet")
public class GetDevicesBySidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetDevicesBySidServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("GetDevicesBySidServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String sidStr = req.getParameter("sid");
		int sid = Integer.parseInt(sidStr);
		
		StoreVO storeVO = new StoreService().getOneStore(sid);
		
		DeviceService deviceService = new DeviceService();
		List<DeviceVO> deviceVOs = deviceService.getAllBySid(sid);
		int size = deviceVOs.size();
		
		
		if(size >= 1) {
			jsonObject.put("did_1", deviceVOs.get(0).getDid());
			jsonObject.put("deviceName_1", deviceVOs.get(0).getLocation());
			System.out.println("did_1 : " +  deviceVOs.get(0).getDid());
			System.out.println("Name_1 : " +  deviceVOs.get(0).getLocation());
		}
		if(size >= 2) {
			jsonObject.put("did_2", deviceVOs.get(1).getDid());
			jsonObject.put("deviceName_2", deviceVOs.get(1).getLocation());
			System.out.println("deviceName_2 : " +  deviceVOs.get(1).getLocation());
		}

		jsonObject.put("discount_1_money", storeVO.getDiscount_1_money());
		jsonObject.put("discount_1_point", storeVO.getDiscount_1_point());
		jsonObject.put("discount_2_money", storeVO.getDiscount_2_money());
		jsonObject.put("discount_2_point", storeVO.getDiscount_2_point());
		jsonObject.put("discount_3_money", storeVO.getDiscount_3_money());
		jsonObject.put("discount_3_point", storeVO.getDiscount_3_point());
		jsonObject.put("storename", storeVO.getName());
		jsonObject.put("size", size);
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
