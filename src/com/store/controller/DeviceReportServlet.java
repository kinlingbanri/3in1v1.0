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

import com.addrecord.model.AddRecordService;
import com.addrecord.model.AddRecordVO;
import com.history.model.HistoryService;
import com.history.model.HistoryVO;
import com.store.model.DeviceMachineListVO;
import com.store.model.StatisticsAllVO;
import com.store.model.StoreService;

@WebServlet("/DeviceReportServlet")
public class DeviceReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeviceReportServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("DeviceReportServlet");
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		res.setContentType("application/json");
		JSONObject jsonObject = new JSONObject();
		
		String sidStr = req.getParameter("sid");
		String didStr = req.getParameter("did");
		String midStr = req.getParameter("mid");
		String dNumber = req.getParameter("dNumber");
		String mNumber = req.getParameter("mNumber");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		System.out.println("mNumber : "  + mNumber);
		System.out.println("start date : "  + startDate + "; end date : " + endDate);
		
		String type = mNumber.substring(0, 2);
		List<DeviceMachineListVO> deviceMachineListVOs = new ArrayList<DeviceMachineListVO>();
		
		
		if(type.equals("TY")) {
			int did = Integer.parseInt(didStr);
			List<AddRecordVO> addRecordVOs = new AddRecordService().getListByDid(startDate, endDate, did);
			
			//DeviceMachineListVO deviceMachineListVO = new DeviceMachineListVO();
			
			jsonObject.put("addRecordVOs", addRecordVOs);
		}else if( (type.equals("WS")) || (type.equals("DR")) ) {
			int maid = Integer.parseInt(midStr);
			List<HistoryVO> historyVOs = new HistoryService().getListByMaid(startDate, endDate, maid);
			jsonObject.put("historyVOs", historyVOs);
		}
		
//		StoreService storeService = new StoreService();
//		//List<StatisticsAllVO> statisticsAllVOs = new ArrayList<StatisticsAllVO>();
//		if(sidStr.equals("all")) {
//			System.out.println("all");
//			List<StatisticsAllVO> statisticsAllVOs = storeService.getAllStatistics(startDate, endDate);
//			
//			for (StatisticsAllVO vo : statisticsAllVOs) {
//				System.out.print(vo.getSid() + ",");
//				System.out.print(vo.getName() + ",");
//				System.out.print(vo.getAdd_money() + ",");
//				System.out.print(vo.getAdd_point() + ",");
//				System.out.println(vo.getConsumption_point());
//			}
//			
//			jsonObject.put("statisticsAllVOs", statisticsAllVOs);
//			
//		}else {
//			int sid = Integer.parseInt(sidStr);
//			System.out.println("sidStr : " + sidStr);
//			List<StatisticsAllVO> statisticsAllVOs = storeService.getAllStatisticsBySId(sid, startDate, endDate);
//			
//			for (StatisticsAllVO vo : statisticsAllVOs) {
//				System.out.print(vo.getSid() + ",");
//				System.out.print(vo.getName() + ",");
//				System.out.print(vo.getNumber() + ",");
//				System.out.print(vo.getAdd_money() + ",");
//				System.out.print(vo.getAdd_point() + ",");
//				System.out.println(vo.getConsumption_point());
//			}
//			
//			jsonObject.put("statisticsAllVOs", statisticsAllVOs);
//		}
		
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
