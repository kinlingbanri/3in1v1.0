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
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

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
		String sidStr = req.getParameter("sid");
		int sid = Integer.parseInt(sidStr);
		System.out.println("username : " + username);
		System.out.println("CheckMoneyServlet number : " + did);
		System.out.println("CheckMoneyServlet store id : " + sid);
		
		
		DeviceVO deviceVO = new DeviceService().getCheckMoney(did);
		int count_100 = deviceVO.getCount_100();
		int count_500 = deviceVO.getCount_500();
		int count_1000 = deviceVO.getCount_1000();
		int totalMoney = (count_100 * 100) + (count_500 * 500) + (count_1000 * 1000);
		
		StoreService storeService = new StoreService();
		StoreVO storeVO = storeService.getOneStore(sid);
		
		int totalPoint = 0;
		if(totalMoney >= storeVO.getDiscount_3_money()) {
			totalPoint = totalMoney + ( storeVO.getDiscount_3_point() - storeVO.getDiscount_3_money());
		}else if(totalMoney >= storeVO.getDiscount_2_money()) {
			totalPoint = totalMoney + ( storeVO.getDiscount_2_point() - storeVO.getDiscount_2_money());
		}else if(totalMoney >= storeVO.getDiscount_1_money()) {
			totalPoint = totalMoney + ( storeVO.getDiscount_1_point() - storeVO.getDiscount_1_money());
		}
		System.out.println("totalPoint : " + totalPoint);		

		
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
		addRecordVO.setStoreid(sid);
		addRecordVO.setStorename(storeVO.getName());
		new AddRecordService().insertRecord(addRecordVO);
		
		//完成交易,更新Device狀態
		//19 : 完成交易
		//totalPoint : 更新device add_point的數值 
		new DeviceService().updateAddStatus13(did, 19, totalPoint);
		
		MemService memService = new MemService();
		MemVO memVO = memService.getOneMem(username);
		int nowPoint = memVO.getPoint() + totalPoint;
		memVO.setPoint(nowPoint);
		memService.updateMem(memVO);
		
		jsonObject.put("state", "19");
		jsonObject.put("nowPoint", nowPoint);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
