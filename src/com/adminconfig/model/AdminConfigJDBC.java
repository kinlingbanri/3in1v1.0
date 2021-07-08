package com.adminconfig.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminConfigJDBC implements AdminConfigDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://211.21.93.170:3306/rm_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_STMT = "SELECT id, ip, comport FROM adminconfig";

	public static void main(String[] args) {
		AdminConfigDAO_interface dao = new AdminConfigJDBC();
		
		AdminConfigVO adminConfigVO = dao.getAdminConfig();
		System.out.print(adminConfigVO.getId() + ",");
		System.out.print(adminConfigVO.getIp() + ",");
		System.out.println(adminConfigVO.getComPort());
	}

	@Override
	public AdminConfigVO getAdminConfig() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminConfigVO adminConfigVO = new AdminConfigVO();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminConfigVO.setId(rs.getInt("id"));
				adminConfigVO.setIp(rs.getString("ip"));
				adminConfigVO.setComPort(rs.getString("comport"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return adminConfigVO;
	}

}
