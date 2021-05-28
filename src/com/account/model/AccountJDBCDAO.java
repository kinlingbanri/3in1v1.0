package com.account.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountJDBCDAO implements AccountDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	//String url = "jdbc:mysql://211.21.93.171:3306/threeinone?useUnicode=true&characterEncoding=utf-8";
	String url = "jdbc:mysql://211.21.93.170:3306/rm_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_ALL_STMT = 
			"SELECT uid, user, password FROM account";
	private static final String GET_ONE_STMT = 
			"SELECT uid, user, password FROM account where uid = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO account (user, password) VALUES (?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE account set user=?, password=? where uid = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM account where uid = ?";
	
	public static void main(String[] args) {
		AccountDAO_interface dao = new AccountJDBCDAO();
		
//		//Add
//		AccountVO accountVO = new AccountVO();
//		accountVO.setUser("Oliver");
//		accountVO.setPassword("123");
//		dao.insert(accountVO);
		
//		//Update
//		AccountVO accountVO = new AccountVO();
//		accountVO.setUser("Oliver");
//		accountVO.setPassword("1234");
//		accountVO.setUid(4);
//		dao.update(accountVO);
		
//		// Delete
//		AccountVO accountVO = new AccountVO();
//		accountVO.setUid(4);
//		dao.delete(accountVO);
		
//		// Query One
//		AccountVO accountVO = dao.findByPrimaryId(1);
//		System.out.print(accountVO.getUid() + ",");
//		System.out.print(accountVO.getUser() + ",");
//		System.out.println(accountVO.getPassword());
		
		// Query All
		List<AccountVO> list = dao.getAll();
		for (AccountVO accountVO : list) {
			System.out.print(accountVO.getUid() + ",");
			System.out.print(accountVO.getUser() + ",");
			System.out.println(accountVO.getPassword());
		}

	}

	@Override
	public void insert(AccountVO accountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, accountVO.getUser());
			pstmt.setString(2, accountVO.getPassword());
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
	public void update(AccountVO accountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, accountVO.getUser());
			pstmt.setString(2, accountVO.getPassword());
			pstmt.setInt(3, accountVO.getUid());

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
	public void delete(AccountVO accountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, accountVO.getUid());
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
	public AccountVO findByPrimaryId(int uid) {
		AccountVO accountVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				accountVO = new AccountVO();
				accountVO.setUid(rs.getInt("uid"));
				accountVO.setUser(rs.getString("user"));
				accountVO.setPassword(rs.getString("password"));
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
		return accountVO;
	}

	@Override
	public List<AccountVO> getAll() {
		List<AccountVO> list = new ArrayList<AccountVO>();
		AccountVO accountVO = null;
		
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
				accountVO = new AccountVO();
				accountVO.setUid(rs.getInt("uid"));
				accountVO.setUser(rs.getString("user"));
				accountVO.setPassword(rs.getString("password"));
				list.add(accountVO); // Store the row in the list
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
