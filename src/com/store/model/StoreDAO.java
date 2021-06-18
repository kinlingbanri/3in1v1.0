package com.store.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StoreDAO implements StoreDAO_interface{
	
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
	
	private static final String GET_ALL_STMT = 
			"SELECT sid, name, city, district, pause, single_count, multi_count, discount_1_money, discount_1_point,"
					+ "discount_2_money, discount_2_point, discount_3_money, discount_3_point FROM store";
	private static final String GET_ONE_STMT = 
			"SELECT sid, name, city, district, pause, single_count, multi_count, discount_1_money, discount_1_point," + 
			"					+ discount_2_money, discount_2_point, discount_3_money, discount_3_point FROM store where sid = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO store (name, city, district, pause, single_count, multi_count, discount_1_money, discount_1_point," + 
								"discount_2_money, discount_2_point, discount_3_money, discount_3_point) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE store set name=?, city=?, district=?, pause=?, single_count=?, multi_count=?, discount_1_money=?, "
								+ "discount_1_point=?, discount_2_money=?, discount_2_point=?, discount_3_money=?, "
								+ "discount_3_point=? where sid = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM store where sid = ?";

	@Override
	public void insert(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, storeVO.getName());
			pstmt.setString(2, storeVO.getCity());
			pstmt.setString(3, storeVO.getDistrict());
			pstmt.setInt(4, storeVO.getPause());
			pstmt.setInt(5, storeVO.getSingle_count());
			pstmt.setInt(6, storeVO.getMulti_count());
			pstmt.setInt(7, storeVO.getDiscount_1_money());
			pstmt.setInt(8, storeVO.getDiscount_1_point());
			pstmt.setInt(9, storeVO.getDiscount_2_money());
			pstmt.setInt(10, storeVO.getDiscount_2_point());
			pstmt.setInt(11, storeVO.getDiscount_3_money());
			pstmt.setInt(12, storeVO.getDiscount_3_point());
			pstmt.executeUpdate();

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

	@Override
	public void update(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, storeVO.getName());
			pstmt.setString(2, storeVO.getCity());
			pstmt.setString(3, storeVO.getDistrict());
			pstmt.setInt(4, storeVO.getPause());
			pstmt.setInt(5, storeVO.getSingle_count());
			pstmt.setInt(6, storeVO.getMulti_count());
			pstmt.setInt(7, storeVO.getDiscount_1_money());
			pstmt.setInt(8, storeVO.getDiscount_1_point());
			pstmt.setInt(9, storeVO.getDiscount_2_money());
			pstmt.setInt(10, storeVO.getDiscount_2_point());
			pstmt.setInt(11, storeVO.getDiscount_3_money());
			pstmt.setInt(12, storeVO.getDiscount_3_point());
			pstmt.setInt(13, storeVO.getSid());
			pstmt.executeUpdate();

			// Handle any driver errors
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

	@Override
	public void delete(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, storeVO.getSid());
			pstmt.executeUpdate();

			// Handle any driver errors
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

	@Override
	public StoreVO findByPrimaryId(int sid) {
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				storeVO = new StoreVO();
				storeVO.setSid(rs.getInt("sid"));
				storeVO.setName(rs.getString("name"));
				storeVO.setCity(rs.getString("city"));
				storeVO.setDistrict(rs.getString("district"));
				storeVO.setPause(rs.getInt("pause"));
				storeVO.setSingle_count(rs.getInt("single_count"));
				storeVO.setMulti_count(rs.getInt("multi_count"));
				storeVO.setDiscount_1_money(rs.getInt("discount_1_money"));
				storeVO.setDiscount_1_point(rs.getInt("discount_1_point"));
				storeVO.setDiscount_2_money(rs.getInt("discount_2_money"));
				storeVO.setDiscount_2_point(rs.getInt("discount_2_point"));
				storeVO.setDiscount_3_money(rs.getInt("discount_3_money"));
				storeVO.setDiscount_3_point(rs.getInt("discount_3_point"));
			}

			// Handle any driver errors
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
		return storeVO;
	}

	@Override
	public List<StoreVO> getAll() {
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				storeVO = new StoreVO();
				storeVO.setSid(rs.getInt("sid"));
				storeVO.setName(rs.getString("name"));
				storeVO.setCity(rs.getString("city"));
				storeVO.setDistrict(rs.getString("district"));
				storeVO.setPause(rs.getInt("pause"));
				storeVO.setSingle_count(rs.getInt("single_count"));
				storeVO.setMulti_count(rs.getInt("multi_count"));
				storeVO.setDiscount_1_money(rs.getInt("discount_1_money"));
				storeVO.setDiscount_1_point(rs.getInt("discount_1_point"));
				storeVO.setDiscount_2_money(rs.getInt("discount_2_money"));
				storeVO.setDiscount_2_point(rs.getInt("discount_2_point"));
				storeVO.setDiscount_3_money(rs.getInt("discount_3_money"));
				storeVO.setDiscount_3_point(rs.getInt("discount_3_point"));
				list.add(storeVO); // Store the row in the list
			}

			// Handle any driver errors
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
