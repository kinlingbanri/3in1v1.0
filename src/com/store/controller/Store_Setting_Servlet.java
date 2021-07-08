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

@WebServlet("/Store_Setting_Servlet")
public class Store_Setting_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Store_Setting_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Store_Setting_Servlet");
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		res.setContentType("application/json");
		JSONObject jsonObject = new JSONObject();
		
		String sidStr = req.getParameter("sid");
		int sid = Integer.parseInt(sidStr);
		String coulumName = req.getParameter("coulumName");
		String arg1Str = req.getParameter("totalArgs");
		//int arg1 = Integer.parseInt(arg1Str);
		String arg2Str = req.getParameter("totalArgs");
		//int arg2 = Integer.parseInt(arg2Str);
		
		StoreService storeService = new StoreService();
		StoreVO storeVO = storeService.getOneStore(sid);
		if(coulumName.equals("name")) {
			storeVO.setName(arg1Str);
		}else if(coulumName.equals("single")) {
			int arg1 = Integer.parseInt(arg1Str);
			storeVO.setSingle_count(arg1);
		}else if(coulumName.equals("multi")) {
			int arg1 = Integer.parseInt(arg1Str);
			storeVO.setMulti_count(arg1);
		}else if(coulumName.equals("discount1")) {
			int arg1 = Integer.parseInt(arg1Str);
			int arg2 = Integer.parseInt(arg2Str);
			storeVO.setDiscount_1_money(arg1);
			storeVO.setDiscount_1_point(arg2);
		}else if(coulumName.equals("discount2")) {
			int arg1 = Integer.parseInt(arg1Str);
			int arg2 = Integer.parseInt(arg2Str);
			storeVO.setDiscount_2_money(arg1);
			storeVO.setDiscount_2_point(arg2);
		}else if(coulumName.equals("discount3")) {
			int arg1 = Integer.parseInt(arg1Str);
			int arg2 = Integer.parseInt(arg2Str);
			storeVO.setDiscount_3_money(arg1);
			storeVO.setDiscount_3_point(arg2);
		}else if(coulumName.equals("pause")) {
			int arg1 = Integer.parseInt(arg1Str);
			storeVO.setPause(arg1);
		}
		storeService.updateStore(storeVO);
		

		

		
		jsonObject.put("state", "0");

		out.write(jsonObject.toString());
		out.flush();
		out.close();	
		
	}

}
