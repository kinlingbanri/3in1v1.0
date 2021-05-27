package com.device.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.device.model.DeviceService;
import com.device.model.DeviceVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.history.model.DeviceJsonObject;
import com.history.model.HistoryService;
import com.history.model.HistoryVO;

@WebServlet("/DeviceHistorySevlet")
public class DeviceHistorySevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeviceHistorySevlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("DeviceHistorySevlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject =null;
		
		String didStr = req.getParameter("did");
		int did = Integer.parseInt(didStr);
		String valueStr = req.getParameter("value");
		int index = Integer.parseInt(valueStr);
		int total = 100;
		
		DeviceJsonObject deviceJsonObject = new DeviceJsonObject();
		List<DeviceJsonObject> deviceJsonObjects = new HistoryService().getAllByDid(did, index, total);
		int len = new HistoryService().getCount(did);
		deviceJsonObjects.get(0).setLen(len);

//		deviceJsonObject.setLen(len);
		System.out.println("deviceJsonObject : " + deviceJsonObject);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = null;
		try {
		      jsonObject = new JSONObject(deviceJsonObjects);
		      
		      json = gson.toJson(deviceJsonObjects); // converts to json
		}catch (JSONException err){
			throw new RuntimeException("Couldn't load database driver. "
					+ err.getMessage());
		}
		
		
		
//		//String pagelength = req.getParameter("pagelength");
//		String drawStr = req.getParameter("draw");
//		int draw = Integer.parseInt(drawStr);
//		String startStr = req.getParameter("start");
//		int start = Integer.parseInt(startStr);
//		String lengthStr = req.getParameter("length");
//		int length = Integer.parseInt(lengthStr);
//		Object index = req.getParameter("order[0][column]");
//		Object name = req.getParameter(("['columns'][" + index + "]['data']"));
//		Object sort = req.getParameter("order[0][dir]");
//		//Object value = req.getParameter("value");
//		
//		System.out.println("draw : " + draw);
//		System.out.println("start : " + start);
//		System.out.println("length : " + length);
//		System.out.println("index : " + index);
//		System.out.println("name : " + name);
//		System.out.println("order : " + sort);
//		//System.out.println("value : " + value);
//		
//		PrintWriter out = res.getWriter();
//		JSONObject jsonObject =null;
//		
//		String number = "TY00001";
//		//String did = req.getParameter("num");
//		System.out.println("CheckDeviceStatusServlet number : " + number);
//
//		String location = "RD-1";
//		
//		DeviceVO deviceVO = new DeviceService().getOneDevice(number);
//		int did = deviceVO.getDid();
//		System.out.println("CheckDeviceStatusServlet did : " + did);
//		int count = new HistoryService().getCount(did);
//		
//		DeviceJsonObject deviceJsonObject = new DeviceJsonObject();
//		deviceJsonObject.setDraw(draw);
//		deviceJsonObject.setRecordsTotal(count);
//		deviceJsonObject.setRecordsFiltered(count);
//		
//		List<HistoryVO> data = new HistoryService().getAllByDid(did, start, length);
//		
//		deviceJsonObject.setHistoryVOs(data);
//		
//		try {
//		      jsonObject = new JSONObject(deviceJsonObject);
//		}catch (JSONException err){
//			throw new RuntimeException("Couldn't load database driver. "
//					+ err.getMessage());
//		}

		
		out.write(json.toString());
		out.flush();
		out.close();
	}

}
