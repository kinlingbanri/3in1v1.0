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
		String username = req.getParameter("username");
		int sid = Integer.parseInt(sidStr);
		System.out.println("CheckMoneyServlet number : " + did);
		System.out.println("CheckMoneyServlet store id : " + sid);
		System.out.println("CheckMoneyServlet username : " + username);
		
		DeviceVO addStatusVO = new DeviceService().getAddStatus(did);
		int addStatus = addStatusVO.getAdd_status();
		
		MemService memService = new MemService();
		MemVO memVO = memService.getOneMem(username);
		
		int add_life_status = memVO.getAdd_life_status();
		System.out.println("add_life_status : " + add_life_status);
		
		
		if(add_life_status == 2) {
			jsonObject.put("state", 2);
		}else if((addStatus >= 10) && (addStatus <= 19)) {			
			int new_add_money = memVO.getAdd_money();
			System.out.println("new_add_money : " + new_add_money);
			
			StoreService storeService = new StoreService();
			StoreVO storeVO = storeService.getOneStore(sid);
			
			int totalPoint = 0;
			if(new_add_money >= storeVO.getDiscount_3_money()) {
				totalPoint = new_add_money + ( storeVO.getDiscount_3_point() - storeVO.getDiscount_3_money());
			}else if(new_add_money >= storeVO.getDiscount_2_money()) {
				totalPoint = new_add_money + ( storeVO.getDiscount_2_point() - storeVO.getDiscount_2_money());
			}else if(new_add_money >= storeVO.getDiscount_1_money()) {
				totalPoint = new_add_money + ( storeVO.getDiscount_1_point() - storeVO.getDiscount_1_money());
			}else {
				totalPoint = new_add_money;
			}
			System.out.println("totalPoint : " + totalPoint);
			
			if(addStatus == 14) {
				new DeviceService().updateAddStatus13(did, 13, totalPoint, new_add_money);
			}else {
				new DeviceService().updateAddStatus13(did, addStatus, totalPoint, new_add_money);
			}
			memVO.setAdd_life_status(1);
			memService.updateMem(memVO);
			
			
			jsonObject.put("totalMoney", new_add_money);
			jsonObject.put("totalPoint", totalPoint);
			jsonObject.put("state", 1);
			
			
			
			//20210708版本 Start
			/*
			int count_100 = deviceVO.getCount_100();
			int count_500 = deviceVO.getCount_500();
			int count_1000 = deviceVO.getCount_1000();
			int totalMoney = (count_100 * 100) + (count_500 * 500) + (count_1000 * 1000);
			
			MemService memService = new MemService();
			MemVO memVO = memService.getOneMem(username);
			
			int add_money = memVO.getAdd_money();
			System.out.println("CheckMoneyServlet add_money : " + add_money);
			int now_money = memVO.getNow_money();
			System.out.println("CheckMoneyServlet now_money : " + now_money);
			
			int new_add_money = totalMoney - now_money + add_money;
			
			MemVO updateMemVO = new MemVO();
			updateMemVO.setUsername(username);
			updateMemVO.setAdd_money(new_add_money);
			updateMemVO.setNow_money(totalMoney);
			updateMemVO.setAdd_status(addStatus);
			memService.updateCheckMoney(username, totalMoney, new_add_money, addStatus);
			
			System.out.print(deviceVO.getDid() + ",");
			System.out.print(deviceVO.getNumber() + ",");
			System.out.print(deviceVO.getAdd_status() + ",");
			System.out.print(count_100 + ",");
			System.out.print(count_500 + ",");
			System.out.println(totalMoney);
			StoreService storeService = new StoreService();
			StoreVO storeVO = storeService.getOneStore(sid);
			//20210708版本 End
			
			int totalPoint = 0;
			if(new_add_money >= storeVO.getDiscount_3_money()) {
				totalPoint = new_add_money + ( storeVO.getDiscount_3_point() - storeVO.getDiscount_3_money());
			}else if(new_add_money >= storeVO.getDiscount_2_money()) {
				totalPoint = new_add_money + ( storeVO.getDiscount_2_point() - storeVO.getDiscount_2_money());
			}else if(new_add_money >= storeVO.getDiscount_1_money()) {
				totalPoint = new_add_money + ( storeVO.getDiscount_1_point() - storeVO.getDiscount_1_money());
			}else {
				totalPoint = new_add_money;
			}
			System.out.println("totalPoint : " + totalPoint);
			
			
			new DeviceService().updateAddStatus13(did, 13, totalPoint, new_add_money, 1);
			
			
			jsonObject.put("add_status", deviceVO.getAdd_status());
			jsonObject.put("count_100", count_100);
			jsonObject.put("count_500", count_500);
			jsonObject.put("count_1000", count_1000);
			jsonObject.put("totalMoney", new_add_money);
			jsonObject.put("totalPoint", totalPoint);
			jsonObject.put("state", 1);
			*/
			//20210708版本 End
		}else {
			
		}
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		
	}

}
