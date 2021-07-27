package com.adminconfig.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdminConfigDAO implements AdminConfigDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/rm_58");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_STMT = "SELECT id, ip, comport, url FROM adminconfig";
	private static final String UPDATE_STMT = "UPDATE adminconfig set ip=?, comport=? where id = ?";

	@Override
	public AdminConfigVO getAdminConfig() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminConfigVO adminConfigVO = new AdminConfigVO();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminConfigVO.setId(rs.getInt("id"));
				adminConfigVO.setIp(rs.getString("ip"));
				adminConfigVO.setComPort(rs.getString("comport"));
				adminConfigVO.setUrl(rs.getString("url"));
			}

			// Handle any driver errors
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

	@Override
	public void updateAdminConfig(AdminConfigVO adminConfigVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, adminConfigVO.getIp());
			pstmt.setString(2, adminConfigVO.getComPort());
			pstmt.setInt(3, adminConfigVO.getId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}

}
