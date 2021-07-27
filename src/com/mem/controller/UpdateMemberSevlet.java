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

@WebServlet("/UpdateMemberSevlet")
public class UpdateMemberSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateMemberSevlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		PrintWriter out = res.getWriter();
		JSONObject jsonObject = new JSONObject();

		String username = req.getParameter("username");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String pointStr = req.getParameter("point");
		int point = Integer.parseInt(pointStr);
		String password = req.getParameter("password");
		String blackStr = req.getParameter("black");
		int black = 0;
		if(blackStr.equals("啟用")) {
			black = 1;
		}else if(blackStr.equals("停用")) {
			black = 0;
		}
		
		MemVO memVO = new MemService().getOneMem(username);		
		
		if(memVO != null) {
			memVO.setPhone(phone);
			memVO.setEmail(email);
			memVO.setPoint(point);
			memVO.setPassword(password);
			memVO.setBlack(black);
			new MemService().updateMem(memVO);
			jsonObject.put("state", "1");
		}else {
			jsonObject.put("state", "2");
		}
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
