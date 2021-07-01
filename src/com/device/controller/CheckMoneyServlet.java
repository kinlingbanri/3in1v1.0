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
import com.store.model.StoreService;
import com.store.model.StoreVO;

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
		String sidStr = req.getParameter("sid");
		int sid = Integer.parseInt(sidStr);
		System.out.println("CheckMoneyServlet number : " + did);
		System.out.println("CheckMoneyServlet store id : " + sid);
		
		DeviceVO addStatusVO = new DeviceService().getAddStatus(did);
		int addStatus = addStatusVO.getAdd_status();
		
		//if(addStatus.getAdd_status() == 14) {
		if((addStatus >= 10) && (addStatus <= 19)) {
			DeviceVO deviceVO = new DeviceService().getCheckMoney(did);
			int count_100 = deviceVO.getCount_100();
			int count_500 = deviceVO.getCount_500();
			int count_1000 = deviceVO.getCount_1000();
			int totalMoney = (count_100 * 100) + (count_500 * 500) + (count_1000 * 1000);
			
			System.out.print(deviceVO.getDid() + ",");
			System.out.print(deviceVO.getNumber() + ",");
			System.out.print(deviceVO.getAdd_status() + ",");
			System.out.print(count_100 + ",");
			System.out.print(count_500 + ",");
			System.out.println(totalMoney);
			StoreService storeService = new StoreService();
			StoreVO storeVO = storeService.getOneStore(sid);
			
			int totalPoint = 0;
			if(totalMoney >= storeVO.getDiscount_3_money()) {
				totalPoint = totalMoney + ( storeVO.getDiscount_3_point() - storeVO.getDiscount_3_money());
			}else if(totalMoney >= storeVO.getDiscount_2_money()) {
				totalPoint = totalMoney + ( storeVO.getDiscount_2_point() - storeVO.getDiscount_2_money());
			}else if(totalMoney >= storeVO.getDiscount_1_money()) {
				totalPoint = totalMoney + ( storeVO.getDiscount_1_point() - storeVO.getDiscount_1_money());
			}else {
				totalPoint = totalMoney;
			}
			System.out.println("totalPoint : " + totalPoint);
			
			if(addStatus == 14) {
				new DeviceService().updateAddStatus13(did, 13, totalPoint);
			}
			
			

			jsonObject.put("add_status", deviceVO.getAdd_status());
			jsonObject.put("count_100", count_100);
			jsonObject.put("count_500", count_500);
			jsonObject.put("count_1000", count_1000);
			jsonObject.put("totalMoney", totalMoney);
			jsonObject.put("totalPoint", totalPoint);
			jsonObject.put("state", 1);
			
		}else {
			jsonObject.put("state", 2);
		}
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		
	}

}
