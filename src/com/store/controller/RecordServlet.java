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
import com.device.model.DeviceService;
import com.device.model.DeviceVO;
import com.history.model.HistoryService;
import com.history.model.HistoryVO;
import com.machine.model.MachineService;
import com.machine.model.MachineVO;
import com.record.model.RecordVO;
import com.record.model.TransactionRecordService;
import com.store.model.DeviceMachineListVO;

@WebServlet("/RecordServlet")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RecordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("RecordServlet");
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		res.setContentType("application/json");
		JSONObject jsonObject = new JSONObject();
		
		String username = req.getParameter("username");
		System.out.println("username : "  + username);
		
		List<RecordVO> recordVOs = new TransactionRecordService().getRecords30(username);

		for(RecordVO recordVO : recordVOs) {
			int type = recordVO.getType();
			int id = recordVO.getId();
			if(type == 1) {
				DeviceVO deviceVO = new DeviceService().getOnByDid(id);
				recordVO.setName(deviceVO.getLocation());
			}else if(type == 2) {
				MachineVO machineVO = new MachineService().getOneMachine(id);
				recordVO.setName(machineVO.getName());
			}
		}
	
		jsonObject.put("recordVOs", recordVOs);
		jsonObject.put("state", 1);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
