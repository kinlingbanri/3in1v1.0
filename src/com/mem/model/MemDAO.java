package com.mem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemDAO implements MemDAO_interface {
	
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
			"SELECT username, email, password, point, black, authority, verification, verificationcode, "
			+ "verificationdate, phone, add_money, add_status, add_life_status FROM mem order by username";
	private static final String GET_ONE_STMT = 
			"SELECT username, email, password, point, black, authority, verification, verificationcode," 
			+"verificationdate, phone, now_money, add_money, add_status, add_life_status FROM mem where username = ?";
	private static final String GET_ONEEMAIL_STMT = 
			"SELECT username, email, password, point, black, authority, verification, verificationcode," + 
			"verificationdate, phone, add_money, add_status FROM mem where email = ?";
	private static final String GET_ONEPHONE_STMT = 
			"SELECT username, email, password, point, black, authority, verification, verificationcode," + 
			"verificationdate, phone, add_money, add_status FROM mem where phone = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO mem (username, email, password, point, black, authority, verification, verificationcode," + 
			"verificationdate, phone, add_money, add_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE mem set email=?, password=?, point=?, black=?, authority=?, verification=?, " + 
			"verificationcode=?, verificationdate=?, phone=?, add_money=?, add_status=?, add_life_status=? where username = ?";
	private static final String UPDATE_CUECK_MONEY = 
			"UPDATE mem set now_money=?, add_money=?, add_status=? where username = ?";
	private static final String UPDATE_NOW_MONEY = 
			"UPDATE mem set now_money=? where username = ?";
	private static final String DELETE = 
			"DELETE FROM mem where username = ?";

	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getUsername());
			pstmt.setString(2, memVO.getEmail());
			pstmt.setString(3, memVO.getPassword());
			pstmt.setInt(4, memVO.getPoint());
			pstmt.setInt(5, memVO.getBlack());
			pstmt.setInt(6, memVO.getAuthority());
			pstmt.setInt(7, memVO.getVerification());
			pstmt.setString(8, memVO.getVerificationcode());
			pstmt.setTimestamp(9, memVO.getVerificationdate());
			pstmt.setString(10, memVO.getPhone());
			pstmt.setInt(11, memVO.getAdd_money());
			pstmt.setInt(12, memVO.getAdd_status());

			pstmt.executeUpdate();

			// Handle any SQL errors
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

	@Override
	public void update(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getEmail());
			pstmt.setString(2, memVO.getPassword());
			pstmt.setInt(3, memVO.getPoint());
			pstmt.setInt(4, memVO.getBlack());
			pstmt.setInt(5, memVO.getAuthority());
			pstmt.setInt(6, memVO.getVerification());
			pstmt.setString(7, memVO.getVerificationcode());
			pstmt.setTimestamp(8, memVO.getVerificationdate());
			pstmt.setString(9, memVO.getPhone());
			pstmt.setInt(10, memVO.getAdd_money());
			pstmt.setInt(11, memVO.getAdd_status());
			pstmt.setInt(12, memVO.getAdd_life_status());
			pstmt.setString(13, memVO.getUsername());		

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
	public void delete(String username) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, username);

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

	@Override
	public MemVO findByPrimaryKey(String username) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setUsername(rs.getString("username"));
				memVO.setEmail(rs.getString("email"));
				memVO.setPassword(rs.getString("password"));
				memVO.setPoint(rs.getInt("point"));
				memVO.setBlack(rs.getInt("black"));
				memVO.setAuthority(rs.getInt("authority"));
				memVO.setVerification(rs.getInt("verification"));
				memVO.setVerificationcode(rs.getString("verificationcode"));
				memVO.setVerificationdate(rs.getTimestamp("verificationdate"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setNow_money(rs.getInt("now_money"));
				memVO.setAdd_money(rs.getInt("add_money"));
				memVO.setAdd_status(rs.getInt("add_status"));
				memVO.setAdd_life_status(rs.getInt("add_life_status"));
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
		return memVO;
	}
	

	@Override
	public List<MemVO> findByEmail(String email) {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEEMAIL_STMT);			
			pstmt.setString(1, email);			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setUsername(rs.getString("username"));
				memVO.setEmail(rs.getString("email"));
				memVO.setPassword(rs.getString("password"));
				memVO.setPoint(rs.getInt("point"));
				memVO.setBlack(rs.getInt("black"));
				memVO.setAuthority(rs.getInt("authority"));
				memVO.setVerification(rs.getInt("verification"));
				memVO.setVerificationcode(rs.getString("verificationcode"));
				memVO.setVerificationdate(rs.getTimestamp("verificationdate"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setAdd_money(rs.getInt("add_money"));
				memVO.setAdd_status(rs.getInt("add_status"));
				list.add(memVO); // Store the row in the list
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

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setUsername(rs.getString("username"));
				memVO.setEmail(rs.getString("email"));
				memVO.setPassword(rs.getString("password"));
				memVO.setPoint(rs.getInt("point"));
				memVO.setBlack(rs.getInt("black"));
				memVO.setAuthority(rs.getInt("authority"));
				memVO.setVerification(rs.getInt("verification"));
				memVO.setVerificationcode(rs.getString("verificationcode"));
				memVO.setVerificationdate(rs.getTimestamp("verificationdate"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setAdd_money(rs.getInt("add_money"));
				memVO.setAdd_status(rs.getInt("add_status"));
				memVO.setAdd_life_status(rs.getInt("add_life_status"));
				list.add(memVO); // Store the row in the list
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
		return list;
	}

	@Override
	public List<MemVO> findByPhone(String phone) {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEPHONE_STMT);
			
			pstmt.setString(1, phone);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setUsername(rs.getString("username"));
				memVO.setEmail(rs.getString("email"));
				memVO.setPassword(rs.getString("password"));
				memVO.setPoint(rs.getInt("point"));
				memVO.setBlack(rs.getInt("black"));
				memVO.setAuthority(rs.getInt("authority"));
				memVO.setVerification(rs.getInt("verification"));
				memVO.setVerificationcode(rs.getString("verificationcode"));
				memVO.setVerificationdate(rs.getTimestamp("verificationdate"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setAdd_money(rs.getInt("add_money"));
				memVO.setAdd_status(rs.getInt("add_status"));
				list.add(memVO); // Store the row in the list
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

	@Override
	public void updateCheckMoney(String username, int now_money, int add_money, int add_status) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CUECK_MONEY);

			pstmt.setInt(1, now_money);
			pstmt.setInt(2, add_money);
			pstmt.setInt(3, add_status);
			pstmt.setString(4, username);		

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
	public void updateNowMoney(String username, int now_money) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_NOW_MONEY);

			pstmt.setInt(1, now_money);
			pstmt.setString(2, username);		

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


}
