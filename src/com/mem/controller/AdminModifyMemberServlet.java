package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mem.model.MemService;
import com.mem.model.MemVO;

@WebServlet("/AdminModifyMemberServlet")
public class AdminModifyMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminModifyMemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("AdminModifyMemberServlet!!!");
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String pointStr = req.getParameter("point");
		int point = Integer.parseInt(pointStr);
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String blackStr = req.getParameter("black");
		int black = Integer.parseInt(blackStr);
		
		System.out.println("username : " + username);
		System.out.println("email : " + email);
		System.out.println("point : " + point);
		System.out.println("phone : " + phone);
		System.out.println("password : " + password);
		System.out.println("black : " + black);
		
		
		MemService memService = new MemService();
		MemVO memVO = memService.getOneMem(username);
		
		memVO.setEmail(email);
		memVO.setPoint(point);
		memVO.setPhone(phone);
		memVO.setPassword(password);
		memVO.setBlack(black);
		
		System.out.println(memVO.getEmail());
		System.out.println(memVO.getPoint());
		System.out.println(memVO.getPhone());
		System.out.println(memVO.getPassword());
		System.out.println(memVO.getBlack());

		
		memService.updateMem(memVO);
		
		
		jsonObject.put("state", "ok");
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
