package com.device.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.language.bm.Languages.LanguageSet;
import org.json.JSONException;
import org.json.JSONObject;

import com.addrecord.model.AddRecordService;
import com.addrecord.model.AddRecordVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.history.model.DeviceJsonObject;
import com.history.model.HistoryService;

import javassist.expr.NewArray;

@WebServlet("/DeviceQueryHistorySevlet")
public class DeviceQueryHistorySevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeviceQueryHistorySevlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("DeviceQueryHistorySevlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String dateRangeStr = req.getParameter("dateRange");
		String[] dateRangeStrArray = dateRangeStr.split("~");
		String startDate = dateRangeStrArray[0].trim();
		String endDate = dateRangeStrArray[1].trim();
		
		String selectASStr = req.getParameter("selectAS");
		selectASStr = selectASStr.trim();
		String storeName = req.getParameter("storeName");

		System.out.println("startDate: " + startDate);
		System.out.println("endDate: " + endDate);
		System.out.println("selectASStr: " + selectASStr);
		System.out.println("storeName: " + storeName);
		
		String json = null;
		if(selectASStr.equals("加值")) {
			List<AddRecordVO> addRecordVOs = new AddRecordService().queryRangeDateStoreName(startDate, endDate, storeName);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			jsonObject.put("addRecordVOs", addRecordVOs);
			//jsonObject.put("len", addRecordVOs.size());
//			System.out.println("len: " + addRecordVOs.size());
			try {
			      //json = gson.toJson(addRecordVOs); // converts to json
			      
			}catch (JSONException err){
				throw new RuntimeException("Couldn't load database driver. " + err.getMessage());
			}
			
		}else if(selectASStr.equals("消費")) {
			
		}
		




		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
