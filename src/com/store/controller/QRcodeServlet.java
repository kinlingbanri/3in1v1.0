package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import com.adminconfig.model.AdminConfigService;
import com.adminconfig.model.AdminConfigVO;
import com.mysql.cj.util.Util;
import com.store.model.StoreService;

@WebServlet("/QRcodeServlet")
public class QRcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QRcodeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		System.out.println("UpdateDeviceServlet!!!");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String count = req.getParameter("count");
		String deviceNumber = req.getParameter("deviceNumber");
		String machineNumber = req.getParameter("machineNumber");
		
		if(count.equals("one")) {
			AdminConfigVO adminConfigVO = new AdminConfigService().getAdminConfig();
//			String urlStr = adminConfigVO.getUrl();
			String ipStr = adminConfigVO.getIp();
			System.out.println("ipStr : " + ipStr);
//			String url = urlStr + "?DID=" + deviceNumber + "&MACHID=" + machineNumber;
			String url = "http://" + ipStr + ":8080/3in1/index.jsp?DID=" + deviceNumber + 
							"&MACHID=" + machineNumber;
			System.out.println("url : " + url);
			String base64Str;
			String sizeStr = req.getParameter("size");
			int size = Integer.parseInt(sizeStr);
			try {
				base64Str = new utils.QRcodeUtil().UrlToBase64(url, size);
				jsonObject.put("base64Str", base64Str);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			jsonObject.put("state", 1);
		}
		
		
		
		
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
