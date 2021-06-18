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
	String url = "jdbc:mysql://211.21.93.170:3306/rm_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_ALL_STMT = 
			"SELECT id, storedatetime, coin10, coin50, paper100, paper500, paper1000, "
			+ "point, errorcode, username, deviceid, devicenumber, storeid, cardid FROM addrecord order by id";
	private static final String QUERY_RANGEDATE_STORENAME_STMT = 
			"SELECT id, storedatetime, paper100, paper500, paper1000, point FROM addrecord " + 
			"WHERE STR_TO_DATE(storedatetime, '%Y-%m-%d') >= ? AND " + 
			"		STR_TO_DATE(storedatetime, '%Y-%m-%d') <= ? AND " + 
			"		storename = ?";
	
	private static final String GET_TodayTotal_STMT = 
			"SELECT sum( (coin10*10) + (coin50*50) + (paper100*100) + (paper500*500) + (paper1000*1000)) totalMoney, (point) totalpoint "
			+ "from addrecord where DATE(storedatetime) = curdate();";
	
	private static final String GET_ONE_STMT = 
			"SELECT id, storedatetime, coin10, coin50, paper100, paper500, paper1000, "
					+ "point, errorcode, username, deviceid, devicenumber, storeid, cardid FROM addrecord where id = ?";
	private static final String GET_BYUSERNAME_STMT = 
			"SELECT id, storedatetime, coin10, coin50, paper100, paper500, paper1000, "
					+ "point, errorcode, username, deviceid, devicenumber, storeid, cardid FROM addrecord where username = ?"
					+ "order by storedatetime DESC LIMIT 30";
	private static final String GET_AFTER30_STMT = 
			"SELECT ADDRECORD.STOREDATETIME, ADDRECORD.POINT, ADDRECORD.DEVICENUMBER, STORE.NAME, STORE.CITY FROM ADDRECORD " + 
			"					INNER JOIN STORE ON ADDRECORD.STOREID = STORE.SID AND ADDRECORD.USERNAME = ? " + 
			"					order by STOREDATETIME DESC LIMIT 30";
	private static final String INSERT_STMT = 
			"INSERT INTO addrecord (storedatetime, coin10, coin50, paper100, paper500, paper1000, "
			+ "point, errorcode, username, deviceid, devicenumber, storeid, cardid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE addrecord set storedatetime=?, coin10=?, coin50=?, paper100=?, paper500=?,"
			+ " paper1000=?, point=?, errorcode=?, username=?, deviceid=?,  devicenumber = ?, storeid=?, cardid=? where id = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM addrecord where id = ?";

	public static void main(String[] args)  {
		
		AddRecordJDBCDAO dao = new AddRecordJDBCDAO();
		
		//Add
//		AddRecordVO addRecordVO = new AddRecordVO();
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		try {
//			ts = Timestamp.valueOf("2021-04-11 10:33:28");  
//			
//		} catch (Exception e) {  
//			e.printStackTrace();  
//		} 
//		addRecordVO.setStoredatetime(ts);
//		addRecordVO.setCoin10(0);
//		addRecordVO.setCoin50(0);
//		addRecordVO.setPaper100(2);
//		addRecordVO.setPaper500(1);
//		addRecordVO.setPaper1000(1);
//		addRecordVO.setPoint(2300);
//		addRecordVO.setErrorcode(0);
//		addRecordVO.setUsername("林阿金");
//		addRecordVO.setDeviceid(1);
//		addRecordVO.setDeviceNumber("TY00001");
//		addRecordVO.setStoreid(1);
//		addRecordVO.setCardid("");
//		dao.insert(addRecordVO);		

		//Update
//		AddRecordVO addRecordVO = new AddRecordVO();
//
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		try {
//			ts = Timestamp.valueOf("2021-06-11 10:37:00");  
//			
//		} catch (Exception e) {  
//			e.printStackTrace();  
//		} 
//		addRecordVO.setStoredatetime(ts);
//		addRecordVO.setCoin10(0);
//		addRecordVO.setCoin50(0);
//		addRecordVO.setPaper100(1);
//		addRecordVO.setPaper500(1);
//		addRecordVO.setPaper1000(1);
//		addRecordVO.setPoint(2300);
//		addRecordVO.setErrorcode(0);
//		addRecordVO.setUsername("林阿金");
//		addRecordVO.setDeviceid(1);
//		addRecordVO.setDeviceNumber("TY00001");
//		addRecordVO.setStoreid(1);
//		addRecordVO.setCardid("");
//		addRecordVO.setId(38);
//		dao.update(addRecordVO);		

		
		// Delete
//		AddRecordVO addRecordVO = new AddRecordVO();
//		addRecordVO.setId(38);
//		dao.delete(addRecordVO);
		
		// Query One
//		AddRecordVO addRecordVO = dao.findByPrimaryId(36);
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
//		System.out.print(addRecordVO.getDeviceNumber() + ",");
//		System.out.print(addRecordVO.getStoreid() + ",");
//		System.out.println(addRecordVO.getCardid());
		
		
		// Query After 30 record
//		MemVO memVO = new MemVO();
//		memVO.setUsername("Kim");
//		List<AddRecordVO> list = dao.getAfter30(memVO);
//		for (AddRecordVO addRecord : list) {
//			System.out.print(addRecord.getStoredatetime() + ",");
//			System.out.print(addRecord.getPoint() + ",");
//			System.out.print(addRecord.getDeviceNumber() + ",");
//			System.out.print(addRecord.getStorename() + ",");
//			System.out.println(addRecord.getCity());
//		}
		
		
		// Query Today Total value
//		TodayTotalVO todayTotalVO = dao.getTodayAddValue();
//		System.out.print(todayTotalVO.getTotalMoney() + ",");
//		System.out.println(todayTotalVO.getTotalPoint());
		
		// Query By Username
//		MemVO memVO = new MemVO();
//		memVO.setUsername("林阿金");
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
//			System.out.print(addRecord.getDeviceNumber() + ",");
//			System.out.print(addRecord.getStoreid() + ",");
//			System.out.println(addRecord.getCardid());
//		}	

		
//		 Query queryRangeDateAndStoreName
		List<AddRecordVO> list = dao.queryRangeDateAndStoreName("2021-03-15", "2021-06-15", "三峽復興店");
		
		for (AddRecordVO addRecord : list) {
			int totalMoney = 0;
			System.out.print(addRecord.getId() + ",");
			System.out.print(addRecord.getStoredatetime() + ",");
			System.out.print(addRecord.getPaper100() + ",");
			totalMoney += addRecord.getPaper100() * 100;
			System.out.print(addRecord.getPaper500() + ",");
			totalMoney += addRecord.getPaper500() * 500;
			System.out.print(addRecord.getPaper1000() + ",");
			totalMoney += addRecord.getPaper1000() * 1000;
			System.out.println(addRecord.getPoint());
			System.out.println("加值金額: " + totalMoney + ", 儲值點數: " + addRecord.getPoint());
		}
		
		
//		 Query All
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
//			System.out.print(addRecord.getDeviceNumber() + ",");
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
			pstmt.setString(11, addRecordVO.getDeviceNumber());
			pstmt.setInt(12, addRecordVO.getStoreid());
			pstmt.setString(13, addRecordVO.getCardid());

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
			pstmt.setString(11, addRecordVO.getDeviceNumber());
			pstmt.setInt(12, addRecordVO.getStoreid());
			pstmt.setString(13, addRecordVO.getCardid());
			pstmt.setInt(14, addRecordVO.getId());

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
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
				addRecordVO.setDeviceNumber(rs.getString("deviceNumber"));
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
				addRecordVO.setDeviceNumber(rs.getString("deviceNumber"));
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
				addRecordVO.setDeviceNumber(rs.getString("deviceNumber"));
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
				addRecordVO.setDeviceNumber(rs.getString("deviceNumber"));
				addRecordVO.setStorename(rs.getString("name"));
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

	@Override
	public List<AddRecordVO> queryRangeDateAndStoreName(String startDate, String endDate, String storeName) {
		List<AddRecordVO> list = new ArrayList<AddRecordVO>();
		AddRecordVO addRecordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(QUERY_RANGEDATE_STORENAME_STMT);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setString(3, storeName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				addRecordVO = new AddRecordVO();
				addRecordVO.setId(rs.getInt("id"));
				addRecordVO.setStoredatetime(rs.getTimestamp("storedatetime"));
				addRecordVO.setPaper100(rs.getInt("paper100"));
				addRecordVO.setPaper500(rs.getInt("paper500"));
				addRecordVO.setPaper1000(rs.getInt("paper1000"));
				addRecordVO.setPoint(rs.getInt("point"));

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
	
}
