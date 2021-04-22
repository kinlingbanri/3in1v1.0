package com.addrecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mem.model.MemVO;

public class AddRecordJDBCDAO implements AddRecordDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/threeinone";
	String userid = "root";
	String passwd = "34182958";
	
	private static final String GET_ALL_STMT = 
			"SELECT id, storedatetime, coin10, coin50, paper100, paper500, paper1000, "
			+ "point, errorcode, username, deviceid, storeid, cardid FROM addrecord order by id";
	
	private static final String GET_TodayTotal_STMT = 
			"SELECT sum( (coin10*10) + (coin50*50) + (paper100*100) + (paper500*500) + (paper1000*1000)) totalMoney, (point) totalpoint "
			+ "from addrecord where DATE(storedatetime) = curdate();";
	
	private static final String GET_ONE_STMT = 
			"SELECT id, storedatetime, coin10, coin50, paper100, paper500, paper1000, "
					+ "point, errorcode, username, deviceid, storeid, cardid FROM addrecord where id = ?";
	private static final String GET_BYUSERNAME_STMT = 
			"SELECT id, storedatetime, coin10, coin50, paper100, paper500, paper1000, "
					+ "point, errorcode, username, deviceid, storeid, cardid FROM addrecord where username = ?"
					+ "order by storedatetime DESC LIMIT 30";
	private static final String GET_AFTER30_STMT = 
			"SELECT ADDRECORD.STOREDATETIME, ADDRECORD.POINT, STORE.STORENAME, STORE.CITY FROM ADDRECORD " + 
			"					INNER JOIN STORE ON ADDRECORD.STOREID = STORE.ID AND ADDRECORD.USERNAME = ? " + 
			"					order by STOREDATETIME DESC LIMIT 30";
	private static final String INSERT_STMT = 
			"INSERT INTO addrecord (storedatetime, coin10, coin50, paper100, paper500, paper1000, "
			+ "point, errorcode, username, deviceid, storeid, cardid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE addrecord set storedatetime=?, coin10=?, coin50=?, paper100=?, paper500=?,"
			+ " paper1000=?, point=?, errorcode=?, username=?, deviceid=?, storeid=?, cardid=? where id = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM addrecord where id = ?";

	public static void main(String[] args)  {
		
		AddRecordJDBCDAO dao = new AddRecordJDBCDAO();
		
//		//Add
//		AddRecordVO addRecordVO = new AddRecordVO();
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		try {
//			ts = Timestamp.valueOf("2021-04-06 10:11:28");  
//			
//		} catch (Exception e) {  
//			e.printStackTrace();  
//		} 
//		addRecordVO.setStoredatetime(ts);
//		addRecordVO.setCoin10(10);
//		addRecordVO.setCoin50(10);
//		addRecordVO.setPaper100(10);
//		addRecordVO.setPaper500(10);
//		addRecordVO.setPaper1000(10);
//		addRecordVO.setPoint(1760);
//		addRecordVO.setErrorcode(0);
//		addRecordVO.setUsername("Kim");
//		addRecordVO.setDeviceid(1);
//		addRecordVO.setStoreid(1);
//		addRecordVO.setCardid("");
//		dao.insert(addRecordVO);
//		System.out.println("OK");
		
		

//		//Update
//		AddRecordVO addRecordVO = new AddRecordVO();
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		try {
//			ts = Timestamp.valueOf("2021-04-06 11:21:30");  
//			
//		} catch (Exception e) {  
//			e.printStackTrace();  
//		} 
//		addRecordVO.setStoredatetime(ts);
//		addRecordVO.setCoin10(100);
//		addRecordVO.setCoin50(50);
//		addRecordVO.setPaper100(10);
//		addRecordVO.setPaper500(10);
//		addRecordVO.setPaper1000(10);
//		addRecordVO.setPoint(1760);
//		addRecordVO.setErrorcode(0);
//		addRecordVO.setUsername("Kim");
//		addRecordVO.setDeviceid(1);
//		addRecordVO.setStoreid(1);
//		addRecordVO.setCardid("");
//		addRecordVO.setId(31);
//		dao.update(addRecordVO);
//		System.out.println("OK");

		
//		// Delete
//		AddRecordVO addRecordVO = new AddRecordVO();
//		addRecordVO.setId(31);
//		dao.delete(addRecordVO);
//		System.out.println("OK");
		
		
//		// Query One
//		AddRecordVO addRecordVO = dao.findByPrimaryId(1);
//		System.out.print(addRecordVO.getId() + ",");
//		System.out.print(addRecordVO.getStoredatetime() + ",");
//		System.out.print(addRecordVO.getCoin10() + ",");
//		System.out.print(addRecordVO.getCoin50() + ",");
//		System.out.print(addRecordVO.getPaper100() + ",");
//		System.out.print(addRecordVO.getPaper500() + ",");
//		System.out.print(addRecordVO.getPaper1000() + ",");
//		System.out.print(addRecordVO.getPoint() + ",");
//		System.out.print(addRecordVO.getErrorcode() + ",");
//		System.out.print(addRecordVO.getUsername() + ",");
//		System.out.print(addRecordVO.getDeviceid() + ",");
//		System.out.print(addRecordVO.getStoreid() + ",");
//		System.out.println(addRecordVO.getCardid());
		
		
//		// Query After 30 record
//		MemVO memVO = new MemVO();
//		memVO.setUsername("Kim");
//		List<AddRecordVO> list = dao.getAfter30(memVO);
//		for (AddRecordVO addRecord : list) {
//			System.out.print(addRecord.getStoredatetime() + ",");
//			System.out.print(addRecord.getPoint() + ",");
//			System.out.print(addRecord.getStorename() + ",");
//			System.out.println(addRecord.getCity());
//		}
		
		
		// Query Today Total value
		TodayTotalVO todayTotalVO = dao.getTodayAddValue();
		System.out.print(todayTotalVO.getTotalMoney() + ",");
		System.out.println(todayTotalVO.getTotalPoint());
		
//		// Query By Username
//		MemVO memVO = new MemVO();
//		memVO.setUsername("Kim");
//		List<AddRecordVO> list = dao.getListByUsername(memVO);
//		for (AddRecordVO addRecord : list) {
//			System.out.print(addRecord.getId() + ",");
//			System.out.print(addRecord.getStoredatetime() + ",");
//			System.out.print(addRecord.getCoin10() + ",");
//			System.out.print(addRecord.getCoin50() + ",");
//			System.out.print(addRecord.getPaper100() + ",");
//			System.out.print(addRecord.getPaper500() + ",");
//			System.out.print(addRecord.getPaper1000() + ",");
//			System.out.print(addRecord.getPoint() + ",");
//			System.out.print(addRecord.getErrorcode() + ",");
//			System.out.print(addRecord.getUsername() + ",");
//			System.out.print(addRecord.getDeviceid() + ",");
//			System.out.print(addRecord.getStoreid() + ",");
//			System.out.println(addRecord.getCardid());
//		}	

		
//		// Query All
//		List<AddRecordVO> list = dao.getAll();
//		for (AddRecordVO addRecord : list) {
//			System.out.print(addRecord.getId() + ",");
//			System.out.print(addRecord.getStoredatetime() + ",");
//			System.out.print(addRecord.getCoin10() + ",");
//			System.out.print(addRecord.getCoin50() + ",");
//			System.out.print(addRecord.getPaper100() + ",");
//			System.out.print(addRecord.getPaper500() + ",");
//			System.out.print(addRecord.getPaper1000() + ",");
//			System.out.print(addRecord.getPoint() + ",");
//			System.out.print(addRecord.getErrorcode() + ",");
//			System.out.print(addRecord.getUsername() + ",");
//			System.out.print(addRecord.getDeviceid() + ",");
//			System.out.print(addRecord.getStoreid() + ",");
//			System.out.println(addRecord.getCardid());
//		}		

	}

	@Override
	public void insert(AddRecordVO addRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setTimestamp(1, addRecordVO.getStoredatetime());
			pstmt.setInt(2, addRecordVO.getCoin10());
			pstmt.setInt(3, addRecordVO.getCoin50());
			pstmt.setInt(4, addRecordVO.getPaper100());
			pstmt.setInt(5, addRecordVO.getPaper500());
			pstmt.setInt(6, addRecordVO.getPaper1000());
			pstmt.setInt(7, addRecordVO.getPoint());
			pstmt.setInt(8, addRecordVO.getErrorcode());
			pstmt.setString(9, addRecordVO.getUsername());
			pstmt.setInt(10, addRecordVO.getDeviceid());
			pstmt.setInt(11, addRecordVO.getStoreid());
			pstmt.setString(12, addRecordVO.getCardid());

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
	public void update(AddRecordVO addRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setTimestamp(1, addRecordVO.getStoredatetime());
			pstmt.setInt(2, addRecordVO.getCoin10());
			pstmt.setInt(3, addRecordVO.getCoin50());
			pstmt.setInt(4, addRecordVO.getPaper100());
			pstmt.setInt(5, addRecordVO.getPaper500());
			pstmt.setInt(6, addRecordVO.getPaper1000());
			pstmt.setInt(7, addRecordVO.getPoint());
			pstmt.setInt(8, addRecordVO.getErrorcode());
			pstmt.setString(9, addRecordVO.getUsername());
			pstmt.setInt(10, addRecordVO.getDeviceid());
			pstmt.setInt(11, addRecordVO.getStoreid());
			pstmt.setString(12, addRecordVO.getCardid());
			pstmt.setInt(13, addRecordVO.getId());

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
	public void delete(AddRecordVO addRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, addRecordVO.getId());
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
	public AddRecordVO findByPrimaryId(int id) {
		AddRecordVO addRecordVO = null;
		
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
				// addRecordVO 也稱為 Domain objects
				addRecordVO = new AddRecordVO();
				addRecordVO.setId(rs.getInt("id"));
				addRecordVO.setStoredatetime(rs.getTimestamp("storedatetime"));
				addRecordVO.setCoin10(rs.getInt("coin10"));
				addRecordVO.setCoin50(rs.getInt("coin50"));
				addRecordVO.setPaper100(rs.getInt("paper100"));
				addRecordVO.setPaper500(rs.getInt("paper500"));
				addRecordVO.setPaper1000(rs.getInt("paper1000"));
				addRecordVO.setPoint(rs.getInt("point"));
				addRecordVO.setErrorcode(rs.getInt("errorcode"));
				addRecordVO.setUsername(rs.getString("username"));
				addRecordVO.setDeviceid(rs.getInt("deviceid"));
				addRecordVO.setStoreid(rs.getInt("storeid"));
				addRecordVO.setCardid(rs.getString("cardid"));
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
		return addRecordVO;
	}

	@Override
	public List<AddRecordVO> getAll() {
		List<AddRecordVO> list = new ArrayList<AddRecordVO>();
		AddRecordVO addRecordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				addRecordVO = new AddRecordVO();
				addRecordVO.setId(rs.getInt("id"));
				addRecordVO.setStoredatetime(rs.getTimestamp("storedatetime"));
				addRecordVO.setCoin10(rs.getInt("coin10"));
				addRecordVO.setCoin50(rs.getInt("coin50"));
				addRecordVO.setPaper100(rs.getInt("paper100"));
				addRecordVO.setPaper500(rs.getInt("paper500"));
				addRecordVO.setPaper1000(rs.getInt("paper1000"));
				addRecordVO.setPoint(rs.getInt("point"));
				addRecordVO.setErrorcode(rs.getInt("errorcode"));
				addRecordVO.setUsername(rs.getString("username"));
				addRecordVO.setDeviceid(rs.getInt("deviceid"));
				addRecordVO.setStoreid(rs.getInt("storeid"));
				addRecordVO.setCardid(rs.getString("cardid"));

				list.add(addRecordVO); // Store the row in the list
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
	public List<AddRecordVO> getListByUsername(MemVO memVO) {
		List<AddRecordVO> list = new ArrayList<AddRecordVO>();
		AddRecordVO addRecordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BYUSERNAME_STMT);
			pstmt.setString(1, memVO.getUsername());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				addRecordVO = new AddRecordVO();
				addRecordVO.setId(rs.getInt("id"));
				addRecordVO.setStoredatetime(rs.getTimestamp("storedatetime"));
				addRecordVO.setCoin10(rs.getInt("coin10"));
				addRecordVO.setCoin50(rs.getInt("coin50"));
				addRecordVO.setPaper100(rs.getInt("paper100"));
				addRecordVO.setPaper500(rs.getInt("paper500"));
				addRecordVO.setPaper1000(rs.getInt("paper1000"));
				addRecordVO.setPoint(rs.getInt("point"));
				addRecordVO.setErrorcode(rs.getInt("errorcode"));
				addRecordVO.setUsername(rs.getString("username"));
				addRecordVO.setDeviceid(rs.getInt("deviceid"));
				addRecordVO.setStoreid(rs.getInt("storeid"));
				addRecordVO.setCardid(rs.getString("cardid"));

				list.add(addRecordVO); // Store the row in the list
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
	public List<AddRecordVO> getAfter30(MemVO memVO) {
		List<AddRecordVO> list = new ArrayList<AddRecordVO>();
		AddRecordVO addRecordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_AFTER30_STMT);
			pstmt.setString(1, memVO.getUsername());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				addRecordVO = new AddRecordVO();
				addRecordVO.setStoredatetime(rs.getTimestamp("storedatetime"));
				addRecordVO.setPoint(rs.getInt("point"));
				addRecordVO.setStorename(rs.getString("storename"));
				addRecordVO.setCity(rs.getString("city"));

				list.add(addRecordVO); // Store the row in the list
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
	public TodayTotalVO getTodayAddValue() {
		TodayTotalVO todayTotalVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_TodayTotal_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				todayTotalVO = new TodayTotalVO();
				todayTotalVO.setTotalMoney(rs.getInt("totalmoney"));
				todayTotalVO.setTotalPoint(rs.getInt("totalpoint"));
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
		return todayTotalVO;
	}	
	
}
