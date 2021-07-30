package com.device.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.device.model.DeviceService;
import com.device.model.DeviceVO;
import com.history.model.HistoryService;
import com.history.model.HistoryVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		String consumptioning = req.getParameter("consumptioning");
		
		DeviceVO deviceVO = new DeviceService().getOneDevice(did);		
		int freecount = Integer.parseInt(req.getParameter("freecount"));
		int serial = Integer.parseInt(req.getParameter("serial"));
		System.out.println("freecount : "  + freecount);
		System.out.println("serial : " + serial);
		
		switch(serial) {
		    case 0:
		    	deviceVO.setMach_00(freecount); break;
		    case 1:
		    	deviceVO.setMach_01(freecount); break;
		    case 2:
		    	deviceVO.setMach_02(freecount); break;
		    case 3:
		    	deviceVO.setMach_03(freecount); break;
		    case 4:
		    	deviceVO.setMach_04(freecount); break;
		    case 5:
		    	deviceVO.setMach_05(freecount); break;
		    case 6:
		    	deviceVO.setMach_06(freecount); break;
		    case 7:
		    	deviceVO.setMach_07(freecount); break;
		    case 8:
		    	deviceVO.setMach_08(freecount); break;
		    case 9:
		    	deviceVO.setMach_09(freecount); break;
		    case 10:
		    	deviceVO.setMach_10(freecount); break;
		    case 11:
		    	deviceVO.setMach_11(freecount); break;
		    case 12:
		    	deviceVO.setMach_12(freecount); break;
		    case 13:
		    	deviceVO.setMach_13(freecount); break;
		    case 14:
		    	deviceVO.setMach_14(freecount); break;
		    case 15:
		    	deviceVO.setMach_15(freecount); break;
		    case 16:
		    	deviceVO.setMach_16(freecount); break;
		    case 17:
		    	deviceVO.setMach_17(freecount); break;
		    case 18:
		    	deviceVO.setMach_18(freecount); break;
		    case 19:
		    	deviceVO.setMach_19(freecount); break;
		    case 20:
		    	deviceVO.setMach_20(freecount); break;
		    case 21:
		    	deviceVO.setMach_21(freecount); break;
		    case 22:
		    	deviceVO.setMach_22(freecount); break;
		    case 23:
		    	deviceVO.setMach_23(freecount); break;
		    case 24:
		    	deviceVO.setMach_24(freecount); break;
		    case 25:
		    	deviceVO.setMach_25(freecount); break;
		    case 26:
		    	deviceVO.setMach_26(freecount); break;
		    case 27:
		    	deviceVO.setMach_27(freecount); break;
		    case 28:
		    	deviceVO.setMach_28(freecount); break;
		    case 29:
		    	deviceVO.setMach_29(freecount); break;
		    	
		    default:
		        break;
		}
		
		new DeviceService().updateConsumptionVO(deviceVO);
		
		
		String mempointStr = req.getParameter("mempoint");
		String consumptionPointStr = req.getParameter("consumptionPoint");
		String storeInfo = req.getParameter("storeInfo");		
		
		MemVO memVO = new MemService().getOneMem(username);
		int consumptionPoint = Integer.parseInt(consumptionPointStr);
		int mempoint = Integer.parseInt(mempointStr);
		int balance = mempoint - consumptionPoint;
		memVO.setPoint(balance);		
		new MemService().updateMem(memVO);				
		
		int sid = deviceVO.getSid();
		System.out.println("sid : " + sid);
		StoreService storeService = new StoreService();
		StoreVO storeVO = storeService.getOneStore(sid);
		String storename = storeVO.getName();
		System.out.println("FreeCountServlet store name : " + storename);

		jsonObject.put("state", 3);
		jsonObject.put("balance", balance);
		
		
		//20210729 Start
//		int status = deviceVO.getStatus();
//		if((status == 1) || (status == 2)) {
//			
//			if(consumptioning.equals("0")) {
//				jsonObject.put("state", 1);
//			}else {	
//				
//				int freecount = Integer.parseInt(req.getParameter("freecount"));
//				int serial = Integer.parseInt(req.getParameter("serial"));
//				System.out.println("freecount : "  + freecount);
//				System.out.println("serial : " + serial);
//				
//				new DeviceService().updateConsumption(did, 0, serial, freecount);
//				
//				String mempointStr = req.getParameter("mempoint");
//				String consumptionPointStr = req.getParameter("consumptionPoint");
//				String storeInfo = req.getParameter("storeInfo");
//				
//				
//				MemVO memVO = new MemService().getOneMem(username);
//				int consumptionPoint = Integer.parseInt(consumptionPointStr);
//				int mempoint = Integer.parseInt(mempointStr);
//				int balance = mempoint - consumptionPoint;
//				memVO.setPoint(balance);
//				
//				new MemService().updateMem(memVO);				
//				
//				int sid = deviceVO.getSid();
//				System.out.println("sid : " + sid);
//				StoreService storeService = new StoreService();
//				StoreVO storeVO = storeService.getOneStore(sid);
//				String storename = storeVO.getName();
//				System.out.println("AddRecordServlet store name : " + storename);
//				/*
//				HistoryVO historyVO = new HistoryVO();
//				Date date = new Date();	//Get now
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				Timestamp ts = new Timestamp(date.getTime());
//				System.out.println(formatter.format(ts));
//				historyVO.setTtime(ts);
//				
//				historyVO.setDid(deviceVO.getDid());
//				historyVO.setMaid(serial);
//				historyVO.setMid(username);
//				historyVO.setFreecount(freecount);
//				historyVO.setPoint(consumptionPoint);				
//				historyVO.setEvent( storeInfo + consumptionPoint + "點" );
//				historyVO.setStorename(storename);
//				new HistoryService().insertHistory(historyVO);
//				
//				System.out.println("insertHistory!!!");
//				*/
//				jsonObject.put("state", 3);
//				jsonObject.put("balance", balance);
//			}
//		}else if((status == 0) || (status == 8)) {
//			jsonObject.put("state", 2);
//		}
		//20210729 End

		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}
}
