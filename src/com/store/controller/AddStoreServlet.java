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

@WebServlet("/AddStoreServlet")
public class AddStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddStoreServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("AddStoreServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String storename = req.getParameter("storename");
		System.out.print("storename : " + storename);
		
		StoreVO storeVO = new StoreVO();
		storeVO.setName(storename);
		new StoreService().insertStore(storeVO);
		
		jsonObject.put("state", 1);
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
