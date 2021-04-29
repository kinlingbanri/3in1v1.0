package com.store.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreJDBCDAO implements StoreDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://211.21.93.170:3306/rm_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_ALL_STMT = 
			"SELECT id, name, city, district FROM store order by city";
	private static final String GET_ONE_STMT = 
			"SELECT id, name, city, district FROM store where id = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO store (name, city, district) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE store set name=?, city=?, district=? where id = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM store where id = ?";

	public static void main(String[] args) {
		StoreJDBCDAO dao = new StoreJDBCDAO();
		
		
//		//Add
//		StoreVO storeVO = new StoreVO();
//		storeVO.setName("龍華店");
//		storeVO.setCity("桃園市");
//		storeVO.setDistrict("龜山區");
//		dao.insert(storeVO);
		
//		//Update
//		StoreVO storeVO = new StoreVO();
//		storeVO.setID(2);
//		storeVO.setName("龍華店");
//		storeVO.setCity("桃園市");
//		storeVO.setDistrict("龜山區");
//		dao.update(storeVO);

		
//		// Delete
//		StoreVO storeVO = new StoreVO();
//		storeVO.setID(3);
//		dao.delete(storeVO);


//		// Query One
//		StoreVO storeVO = dao.findByPrimaryId(1);
//		System.out.print(storeVO.getID() + ",");
//		System.out.print(storeVO.getName() + ",");
//		System.out.print(storeVO.getCity() + ",");
//		System.out.println(storeVO.getDistrict());
		
		// Query All
		List<StoreVO> list = dao.getAll();
		for (StoreVO store : list) {
			System.out.print(store.getID() + ",");
			System.out.print(store.getName() + ",");
			System.out.print(store.getCity() + ",");
			System.out.println(store.getDistrict());
		}
	}

	@Override
	public void insert(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, storeVO.getName());
			pstmt.setString(2, storeVO.getCity());
			pstmt.setString(3, storeVO.getDistrict());

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

	@Override
	public void update(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, storeVO.getName());
			pstmt.setString(2, storeVO.getCity());
			pstmt.setString(3, storeVO.getDistrict());
			pstmt.setInt(4, storeVO.getID());

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

	@Override
	public void delete(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, storeVO.getID());
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

	@Override
	public StoreVO findByPrimaryId(int id) {
		StoreVO storeVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				storeVO = new StoreVO();
				storeVO.setID(rs.getInt("id"));
				storeVO.setName(rs.getString("name"));
				storeVO.setCity(rs.getString("city"));
				storeVO.setDistrict(rs.getString("district"));
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				storeVO = new StoreVO();
				storeVO.setID(rs.getInt("id"));
				storeVO.setName(rs.getString("name"));
				storeVO.setCity(rs.getString("city"));
				storeVO.setDistrict(rs.getString("district"));
				list.add(storeVO); // Store the row in the list
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
