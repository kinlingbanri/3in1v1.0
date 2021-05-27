package com.history.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.ImmutableEntityUpdateQueryHandlingMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import javassist.expr.NewArray;
import utils.POIUtil;


@WebServlet("/HistoryOutExcelSevlet")
public class HistoryOutExcelSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public HistoryOutExcelSevlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("HistoryOutExcelSevlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String obj = req.getParameter("obj");
		System.out.println("obj : " + obj);
		JSONObject jsonObj = new JSONObject(obj);
		
		new POIUtil().createExcel(jsonObj);
		

		//Object object = obj.getJ
		

		
//		POIUtil poiUtil = new POIUtil();
//		poiUtil.createExcel();
		
		jsonObject.put("state", "1");
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
