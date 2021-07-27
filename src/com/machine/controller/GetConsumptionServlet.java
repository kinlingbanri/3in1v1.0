package com.machine.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.machine.model.MachineService;
import com.machine.model.MachineVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/GetConsumptionServlet")
public class GetConsumptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetConsumptionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("GetConsumptionServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String sidStr = req.getParameter("sid");
		int sid = Integer.parseInt(sidStr);
		System.out.print("sid : " + sid);
		
		List<MachineVO> machineVOs = new MachineService().getAllBySid(sid);
		StoreVO storeVO = new StoreService().getOneStore(sid);
		
		jsonObject.put("size", machineVOs.size());
		jsonObject.put("machineVOs", machineVOs);
		jsonObject.put("storename", storeVO.getName());
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
