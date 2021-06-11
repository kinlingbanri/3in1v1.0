package com.addrecord.controller;

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

import com.addrecord.model.AddRecordService;
import com.addrecord.model.AddRecordVO;
import com.device.model.DeviceService;
import com.device.model.DeviceVO;
import com.history.model.HistoryService;
import com.history.model.HistoryVO;

@WebServlet("/AddRecordServlet")
public class AddRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddRecordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("AddRecordServlet!");
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String username = req.getParameter("username");
		String did = req.getParameter("did");
		int count_100 = Integer.parseInt(req.getParameter("count_100"));
		int count_500 =Integer.parseInt(req.getParameter("count_500"));
		int count_1000 = Integer.parseInt(req.getParameter("count_1000"));
		int totalPoint = Integer.parseInt(req.getParameter("totalPoint"));
		
		System.out.println("username : " + username);
		System.out.println("did : " + did);
		System.out.println("count_100 : " + count_100);
		System.out.println("count_500 : " + count_500);
		System.out.println("count_1000 : " + count_1000);
		System.out.println("totalPoint : " + totalPoint);
		
		DeviceVO deviceVO = new DeviceService().getOneDevice(did);
		
		Date date = new Date();	//Get now
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp ts = new Timestamp(date.getTime());
		System.out.println("date : " + formatter.format(ts));
		
		AddRecordVO addRecordVO = new AddRecordVO();
		addRecordVO.setStoredatetime(ts);
		addRecordVO.setPaper100(count_100);
		addRecordVO.setPaper500(count_500);
		addRecordVO.setPaper1000(count_1000);
		addRecordVO.setPoint(totalPoint);
		addRecordVO.setUsername(username);
		addRecordVO.setDeviceid(deviceVO.getDid());
		addRecordVO.setDeviceNumber(deviceVO.getNumber());
		
		new AddRecordService().insertRecord(addRecordVO);	
		
		deviceVO.setAdd_status(19);
		new DeviceService().updateDevice(deviceVO);
		
		jsonObject.put("state", "19");
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
