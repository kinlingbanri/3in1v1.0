package com.mem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemJDBCClass {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/threeinone";
	String userid = "root";
	String passwd = "34182958";
	
	private static final String GET_ALL_STMT = 
			"SELECT username, email, password, point FROM mem order by username";
	private static final String GET_ONE_STMT = 
			"SELECT username, email, password, point FROM mem where username = ?";
	private static final String GET_ONEEMAIL_STMT = 
			"SELECT username, email, password, point FROM mem where email = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO mem (username, email, password, point) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE mem set email=?, password=?, point=? where username = ?";
	private static final String DELETE = 
			"DELETE FROM mem where username = ?";
	
	
	
	
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getEmail());
			pstmt.setString(2, memVO.getPassword());
			pstmt.setInt(3, memVO.getPoint());
			pstmt.setString(4, memVO.getUsername());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setUsername(rs.getString("username"));
				memVO.setEmail(rs.getString("email"));
				memVO.setPassword(rs.getString("password"));
				memVO.setPoint(rs.getInt("point"));
				list.add(memVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}
}
