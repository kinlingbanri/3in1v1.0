package com.saleprice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalePriceJDBC implements SalePrice_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://211.21.93.170:3306/rm_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_ALL_STMT = 
			"SELECT id, dallas, point FROM saleprice";
	private static final String GET_ONE_STMT = 
			"SELECT id, dallas, point FROM saleprice where id = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO saleprice (dallas, point) VALUES (?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE saleprice set dallas=?, point=? where id = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM saleprice where id = ?";

	public static void main(String[] args) {
		SalePriceJDBC dao = new SalePriceJDBC();		
		
		//Add
//		SalePriceVO salePriceVO = new SalePriceVO();
//		salePriceVO.setDallas(1000);
//		salePriceVO.setPoint(1300);
//		dao.insert(salePriceVO);
		
		//Update
//		SalePriceVO salePriceVO = new SalePriceVO();
//		salePriceVO.setId(1);
//		salePriceVO.setDallas(200);
//		salePriceVO.setPoint(300);
//		dao.update(salePriceVO);
		
//		// Delete
//		SalePriceVO salePriceVO = new SalePriceVO();
//		salePriceVO.setId(2);
//		dao.delete(storeVO);

//		// Query One
//		SalePriceVO salePriceVO = dao.findByPrimaryId(1);
//		System.out.print(salePriceVO.getId() + ",");
//		System.out.print(salePriceVO.getDallas() + ",");
//		System.out.println(salePriceVO.getPoint());
		
		// Query All
		List<SalePriceVO> list = dao.getAll();
		for (SalePriceVO salePriceVO1 : list) {
			System.out.print(salePriceVO1.getId() + ",");
			System.out.print(salePriceVO1.getDallas() + ",");
			System.out.println(salePriceVO1.getPoint());
		}

	}

	@Override
	public void insert(SalePriceVO salePriceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, salePriceVO.getDallas());
			pstmt.setInt(2, salePriceVO.getPoint());
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
	public void update(SalePriceVO salePriceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, salePriceVO.getDallas());
			pstmt.setInt(2, salePriceVO.getPoint());
			pstmt.setInt(3, salePriceVO.getId());
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
	public void delete(SalePriceVO salePriceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, salePriceVO.getId());
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
	public SalePriceVO findByPrimaryId(int id) {
		SalePriceVO salePriceVO= null;
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
				salePriceVO = new SalePriceVO();
				salePriceVO.setId(rs.getInt("id"));
				salePriceVO.setDallas(rs.getInt("dallas"));
				salePriceVO.setPoint(rs.getInt("point"));
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
		return salePriceVO;
	}

	@Override
	public List<SalePriceVO> getAll() {
		List<SalePriceVO> list = new ArrayList<SalePriceVO>();
		SalePriceVO salePriceVO = null;
		
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
				salePriceVO = new SalePriceVO();
				salePriceVO.setId(rs.getInt("id"));
				salePriceVO.setDallas(rs.getInt("dallas"));
				salePriceVO.setPoint(rs.getInt("point"));
				list.add(salePriceVO); // Store the row in the list
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