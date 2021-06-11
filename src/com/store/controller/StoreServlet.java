package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/StoreServlet")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StoreServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		String type = req.getParameter("type");
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);
		String valueStr = req.getParameter("value");
		System.out.println("memservlet req type : " + type);
		System.out.println("memservlet req id : " + id);
		System.out.println("memservlet req valueStr : " + valueStr);
		
		StoreService storeService = new StoreService();
		StoreVO storeVO = storeService.getOneStore(id);
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		if(type.equals("name")) {
			storeVO.setName(valueStr);
			storeService.updateStore(storeVO);
			jsonObject.put("state", "1");
		}else if(type.equals("pause")) {
			int value = Integer.parseInt(valueStr);
			storeVO.setPause(value);
			storeService.updateStore(storeVO);
			jsonObject.put("state", "1");
		}else {
			jsonObject.put("state", "0");
		}
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();		

	}

}
