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
		int status = deviceVO.getStatus();
		if((status == 1) || (status == 2)) {

			
			if(consumptioning.equals("0")) {
				jsonObject.put("state", 1);
			}else {	
				
				int freecount = Integer.parseInt(req.getParameter("freecount"));
				int serial = Integer.parseInt(req.getParameter("serial"));
				System.out.println("freecount : "  + freecount);
				System.out.println("serial : " + serial);
				
				new DeviceService().updateConsumption(did, 0, serial, freecount);
				
				String mempointStr = req.getParameter("mempoint");
				String consumptionPointStr = req.getParameter("consumptionPoint");
				String storeInfo = req.getParameter("storeInfo");
				
				
				MemVO memVO = new MemService().getOneMem(username);
				int consumptionPoint = Integer.parseInt(consumptionPointStr);
				int mempoint = Integer.parseInt(mempointStr);
				int balance = mempoint - consumptionPoint;
				memVO.setPoint(balance);
				
				new MemService().updateMem(memVO);
				
				HistoryVO historyVO = new HistoryVO();
				Date date = new Date();	//Get now
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Timestamp ts = new Timestamp(date.getTime());
				System.out.println(formatter.format(ts));
				historyVO.setTtime(ts);
				
				historyVO.setDid(deviceVO.getDid());
				historyVO.setMaid(serial);
				historyVO.setMid(username);
				historyVO.setFreecount(freecount);
				historyVO.setPoint(consumptionPoint);
				historyVO.setEvent( storeInfo + consumptionPoint + "點" );
				new HistoryService().insertHistory(historyVO);
				
				System.out.println("insertHistory!!!");
				
				jsonObject.put("state", 3);
				jsonObject.put("balance", balance);
			}
		}else if((status == 0) || (status == 8)) {
			jsonObject.put("state", 2);
		}

		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}
}
