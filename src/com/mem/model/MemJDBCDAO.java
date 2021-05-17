package com.mem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemJDBCDAO implements MemDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://211.21.93.170:3306/RM_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_ALL_STMT = 
			"SELECT username, email, password, point, black, authority, verification, verificationcode, "
			+ "verificationdate, phone FROM mem order by username";
	private static final String GET_ONE_STMT = 
			"SELECT username, email, password, point, black, authority, verification, verificationcode," 
			+"verificationdate, phone FROM mem where username = ?";
	private static final String GET_ONEEMAIL_STMT = 
			"SELECT username, email, password, point, black, authority, verification, verificationcode," + 
			"verificationdate, phone FROM mem where email = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO mem (username, email, password, point, black, authority, verification, verificationcode," + 
			"verificationdate, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE mem set email=?, password=?, point=?, black=?, authority=?, verification=?, " + 
			"verificationcode=?, verificationdate=?, phone=? where username = ?";
	private static final String DELETE = 
			"DELETE FROM mem where username = ?";


	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getUsername());
			pstmt.setString(2, memVO.getEmail());
			pstmt.setString(3, memVO.getPassword());
			pstmt.setInt(4, memVO.getPoint());
			pstmt.setInt(5, memVO.getBlack());
			pstmt.setInt(6, memVO.getAuthority());
			pstmt.setInt(7, memVO.getVerification());
			pstmt.setInt(8, memVO.getVerificationcode());
			pstmt.setTimestamp(9, memVO.getVerificationdate());
			pstmt.setString(10, memVO.getPhone());

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
			pstmt.setInt(4, memVO.getBlack());
			pstmt.setInt(5, memVO.getAuthority());
			pstmt.setInt(6, memVO.getVerification());
			pstmt.setInt(7, memVO.getVerificationcode());
			pstmt.setTimestamp(8, memVO.getVerificationdate());
			pstmt.setString(9, memVO.getPhone());
			pstmt.setString(10, memVO.getUsername());			

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
	public void delete(String username) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, username);

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
	public MemVO findByPrimaryKey(String username) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				memVO.setVerificationcode(rs.getInt("verificationcode"));
				memVO.setVerificationdate(rs.getTimestamp("verificationdate"));
				memVO.setPhone(rs.getString("phone"));
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				memVO.setVerificationcode(rs.getInt("verificationcode"));
				memVO.setVerificationdate(rs.getTimestamp("verificationdate"));
				memVO.setPhone(rs.getString("phone"));
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
	
	@Override
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
				memVO.setBlack(rs.getInt("black"));
				memVO.setAuthority(rs.getInt("authority"));
				memVO.setVerification(rs.getInt("verification"));
				memVO.setVerificationcode(rs.getInt("verificationcode"));
				memVO.setVerificationdate(rs.getTimestamp("verificationdate"));
				memVO.setPhone(rs.getString("phone"));
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

	public static void main(String[] args) {
		MemJDBCDAO dao = new MemJDBCDAO();
		
//		//Add
//		MemVO memVO = new MemVO();
//		memVO.setUsername("金城六");
//		memVO.setEmail("Kim@hotmail.com");
//		memVO.setPassword("123");
//		memVO.setPoint(600);
//		memVO.setBlack(0);
//		memVO.setAuthority(0);
//		memVO.setVerification(0);
//		memVO.setVerificationcode(5491);
//		
//		Date date = new Date();       
//		Timestamp nousedate = new Timestamp(date.getTime());
//		
//		memVO.setVerificationdate(nousedate);
//		memVO.setPhone("0935276906");
//		memVO.setBlack(1);
//		dao.insert(memVO);
		

		//Update
		MemVO memVO = new MemVO();
		memVO.setUsername("金城六");
		memVO.setEmail("Kim2@hotmail.com");
		memVO.setPassword("123");
		memVO.setPoint(650);
		memVO.setBlack(0);
		memVO.setAuthority(0);
		memVO.setVerification(0);
		memVO.setVerificationcode(3418);
		
		Date date = new Date();       
		Timestamp nousedate = new Timestamp(date.getTime());
		
		memVO.setVerificationdate(nousedate);
		memVO.setPhone("0935276906");
		memVO.setBlack(0);
		dao.update(memVO);
		
//		// Delete
//		dao.delete("金城六");
		
		
//		// Query One
//		MemVO memVO = dao.findByPrimaryKey("Van007");
//		System.out.print(memVO.getUsername() + ",");
//		System.out.print(memVO.getEmail() + ",");
//		System.out.print(memVO.getPassword() + ",");
//		System.out.print(memVO.getPoint());
//		System.out.print(memVO.getBlack() + ",");
//		System.out.print(memVO.getAuthority() + ",");
//		System.out.print(memVO.getVerification() + ",");
//		System.out.print(memVO.getVerificationcode() + ",");
//		System.out.print(memVO.getVerificationdate() + ",");
//		System.out.print(memVO.getPhone());
//		System.out.println();
		
		
//		// Query Email All
//		List<MemVO> list = dao.findByEmail("Van@tongya.com.tw");
//		for (MemVO mem : list) {
//			System.out.print(mem.getUsername() + ",");
//			System.out.print(mem.getEmail() + ",");
//			System.out.print(mem.getPassword() + ",");
//			System.out.print(mem.getPoint());
//			System.out.print(mem.getBlack() + ",");
//			System.out.print(mem.getAuthority() + ",");
//			System.out.print(mem.getVerification() + ",");
//			System.out.print(mem.getVerificationcode() + ",");
//			System.out.print(mem.getVerificationdate() + ",");
//			System.out.print(mem.getPhone());
//			System.out.println();
//		}
		
//		// Query All
//		List<MemVO> list = dao.getAll();
//		for (MemVO mem : list) {
//			System.out.print(mem.getUsername() + ",");
//			System.out.print(mem.getEmail() + ",");
//			System.out.print(mem.getPassword() + ",");
//			System.out.print(mem.getPoint() + ",");
//			System.out.print(mem.getBlack() + ",");
//			System.out.print(mem.getAuthority() + ",");
//			System.out.print(mem.getVerification() + ",");
//			System.out.print(mem.getVerificationcode() + ",");
//			System.out.print(mem.getVerificationdate() + ",");
//			System.out.print(mem.getPhone());
//			System.out.println();
//		}
		
	}
}
