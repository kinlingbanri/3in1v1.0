package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.store.model.StatisticsAllVO;
import com.store.model.StoreService;

@WebServlet("/StoreStatisticsServlet")
public class StoreStatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StoreStatisticsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("StoreStatisticsServlet");
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		res.setContentType("application/json");
		JSONObject jsonObject = new JSONObject();
		
		String sidStr = req.getParameter("sid");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		System.out.println("start date : "  + startDate + "; end date : " + endDate);
		
		StoreService storeService = new StoreService();
		//List<StatisticsAllVO> statisticsAllVOs = new ArrayList<StatisticsAllVO>();
		if(sidStr.equals("all")) {
			System.out.println("all");
			List<StatisticsAllVO> statisticsAllVOs = storeService.getAllStatistics(startDate, endDate);
			
			for (StatisticsAllVO vo : statisticsAllVOs) {
				System.out.print(vo.getSid() + ",");
				System.out.print(vo.getName() + ",");
				System.out.print(vo.getAdd_money() + ",");
				System.out.print(vo.getAdd_point() + ",");
				System.out.println(vo.getConsumption_point());
			}
			
			jsonObject.put("statisticsAllVOs", statisticsAllVOs);
			
		}else {
			int sid = Integer.parseInt(sidStr);
			System.out.println("sidStr : " + sidStr);
			List<StatisticsAllVO> statisticsAllVOs = storeService.getAllStatisticsBySId(sid, startDate, endDate);
			
			for (StatisticsAllVO vo : statisticsAllVOs) {
				System.out.print(vo.getSid() + ",");
				System.out.print(vo.getName() + ",");
				System.out.print(vo.getNumber() + ",");
				System.out.print(vo.getAdd_money() + ",");
				System.out.print(vo.getAdd_point() + ",");
				System.out.println(vo.getConsumption_point());
			}
			
			jsonObject.put("statisticsAllVOs", statisticsAllVOs);
		}
		
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
