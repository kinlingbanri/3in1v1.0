package com.history.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

@WebServlet("/HistoryRecordServlet")
public class HistoryRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HistoryRecordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("HistoryRecordServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String username = req.getParameter("username");
		String did = req.getParameter("did");
		int machid = Integer.parseInt(req.getParameter("machid"));
		int point =Integer.parseInt(req.getParameter("point"));
		int freecount = Integer.parseInt(req.getParameter("freecount"));
		String number = req.getParameter("number");
		req.getSession().setAttribute("DID", did);
		req.getSession().setAttribute("MAID", number);
		
		System.out.println("username : " + username);
		System.out.println("did : " + did);
		System.out.println("machid : " + machid);
		System.out.println("consumptionPoint : " + point);
		System.out.println("freecount : " + freecount);
		System.out.println("number : " + number);
		
		DeviceVO deviceVO = new DeviceService().getOneDevice(did);
		HistoryVO historyVO = new HistoryVO();
		
		Date date = new Date();	//Get now
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp ts = new Timestamp(date.getTime());
		System.out.println("date : " + formatter.format(ts));
		
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null || "".equals(ip)) {
			ip = req.getRemoteAddr();
		}
		System.out.println("ip : " + ip);		
		
		historyVO.setTtime(ts);
		historyVO.setIp(ip);
		
		historyVO.setDid(deviceVO.getDid());
		historyVO.setLocation(deviceVO.getLocation());
		
		historyVO.setMid(username);
		historyVO.setMaid(machid);
		historyVO.setFreecount(freecount);
		historyVO.setPoint(point);
		
		historyVO.setUid(-1);
		historyVO.setRefundcount(0);
		historyVO.setExchangecount(0);
		historyVO.setPapercount(0);
		
		String event = "";
		String numberType = number.substring(0, 3);		
		System.out.println("numberType : " + numberType);
		if(numberType.equals("NOT")) {
			event = "加值" + point + "點";
		}else if(numberType.equals("WAH")) {
			event = "洗衣" + point + "點";
		}else if(numberType.equals("DRY")) {
			event = "烘衣" + point + "點";
		}
		historyVO.setEvent(event);
		
		new HistoryService().insertHistory(historyVO);
		
		jsonObject.put("status", "1");
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}
}
