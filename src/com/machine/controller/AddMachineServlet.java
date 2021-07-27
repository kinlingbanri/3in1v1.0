package com.machine.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.machine.model.MachineService;
import com.machine.model.MachineVO;

@WebServlet("/AddMachineServlet")
public class AddMachineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddMachineServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("AddMachineServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String name = req.getParameter("machinename");
		System.out.print("name : " + name);
		String sidStr = req.getParameter("sid");
		String didStr = req.getParameter("did");
		int sid = Integer.parseInt(sidStr);
		int did = Integer.parseInt(didStr);
		
		MachineVO newMachineVO = new MachineVO();
		newMachineVO.setName(name);
		newMachineVO.setSid(sid);
		newMachineVO.setDid(did);
		long machidLong = new MachineService().insertGetMachid(newMachineVO);
		
		int machid = Math.toIntExact(machidLong);
		MachineVO machineVO = new MachineService().getOneMachine(machid);
				
		jsonObject.put("machineVO", machineVO);
		jsonObject.put("machid", machid);
		jsonObject.put("state", 1);
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
