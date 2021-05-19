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

import com.history.model.DeviceJsonObject;
import com.history.model.HistoryService;
import com.history.model.HistoryVO;

import javassist.expr.NewArray;

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
		
		String did = "TY0001";
		//String did = req.getParameter("num");
		System.out.println("CheckDeviceStatusServlet number : " + did);
		
		
		
		String deviceNumber = "TY00001";
		String location = "RD-1";
//		int did = 1;
		int totalCount = 502310;
		
		DeviceJsonObject deviceJsonObject = new DeviceJsonObject();
		deviceJsonObject.setRecordsTotal(502310);
		deviceJsonObject.setRecordsFiltered(30);
		
		List<HistoryVO> data = new HistoryService().getAllByDid(1, 1, 100);
		
		deviceJsonObject.setHistoryVOs(data);
		
		try {
		      jsonObject = new JSONObject(deviceJsonObject);
		}catch (JSONException err){
			throw new RuntimeException("Couldn't load database driver. "
					+ err.getMessage());
		}
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
