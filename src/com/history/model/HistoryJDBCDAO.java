package com.history.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryJDBCDAO implements HistoryDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://211.21.93.170:3306/rm_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_COUNT_STMT = 
			"SELECT COUNT(*) FROM history WHERE did = ?";
	private static final String GET_ALL_DID_STMT = 
			"SELECT hid, ttime, event, ip, uid, did, maid, mid, point, location, sid, storename FROM history where did = ?  ORDER BY TTIME DESC LIMIT ?, ?;";
	private static final String GET_ALL_STMT = 
			"SELECT hid, ttime, event, ip, uid, did, maid, mid, refundcount, freecount, exchangecount,"
					+ "papercount, location, sid, storename FROM history";
	private static final String GET_MEMBERID_STMT = 
			"SELECT hid, ttime, event, ip, uid, did, maid, mid, refundcount, freecount, point, exchangecount,"
					+ "papercount, sid, storename FROM history where mid = ?";
	private static final String GET_LIST_BY_MAID_STMT = 
			"SELECT hid, ttime, event, did, maid, mid, point, sid " + 
			"FROM history " + 
			"WHERE STR_TO_DATE(ttime, '%Y-%m-%d') >= ? AND " + 
			"		  STR_TO_DATE(ttime, '%Y-%m-%d') <= ? AND " +
			"		  POINT > 0 AND MAID = ?  " +
			"ORDER BY ttime DESC";

	private static final String GET_ONE_STMT = 
			"select hid, ttime, event, ip, uid, did, maid, mid, refundcount, freecount, exchangecount,"
					+ "papercount, point, sid, storename from history where hid = ?";
	private static final String GET_30_STMT = 
			"SELECT TTIME, MID, POINT, LOCATION, sid, storename FROM history "
			+ "WHERE MID = ? AND FREECOUNT > 0 AND POINT > 0 ORDER BY TTIME DESC LIMIT 30;";
	private static final String INSERT_STMT = 
			"INSERT INTO history (ttime, event, ip, uid, did, maid, mid, refundcount, freecount,"
					+ "point, exchangecount, papercount, location, sid, storename) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE history set ttime=?, event=?, ip=?, uid=?, did=?, maid=?, mid=?, refundcount=?,"
					+ "freecount=?, point=?, exchangecount=?, papercount=?, location=?, sid=?, storename=? where hid = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM history where hid = ?";
	
	public static void main(String[] args) {
		
		HistoryDAO_interface dao = new HistoryJDBCDAO();

		//Add
//		HistoryVO historyVO = new HistoryVO();
//		Date date = new Date();	//Get now
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Timestamp ts = new Timestamp(date.getTime());
//		System.out.println(formatter.format(ts));
//		historyVO.setTtime(ts);
//		historyVO.setEvent("烘衣60分鐘");
//		historyVO.setIp("192.168.100.165");
//		historyVO.setUid(0);
//		historyVO.setDid(1);
//		historyVO.setMaid(2);
//		historyVO.setMid("陳啟展");
//		historyVO.setRefundcount(0);
//		historyVO.setFreecount(10);
//		historyVO.setPoint(100);
//		historyVO.setExchangecount(0);
//		historyVO.setPapercount(0);
//		historyVO.setLocation("樹林站前");
//		historyVO.setSid(12);
//		historyVO.setStorename("三峽恩主公店");
//		dao.insert(historyVO);
		
		//Update
//		HistoryVO historyVO = new HistoryVO();
//		Date date = new Date();	//Get now
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Timestamp ts = new Timestamp(date.getTime());
//		System.out.println(formatter.format(ts));
//		historyVO.setHid(3482383);
//		historyVO.setTtime(ts);
//		historyVO.setEvent("");
//		historyVO.setIp("192.168.100.165");
//		historyVO.setUid(0);
//		historyVO.setDid(8);
//		historyVO.setMaid(1);
//		historyVO.setMid("陳啟展");
//		historyVO.setRefundcount(0);
//		historyVO.setFreecount(0);
//		historyVO.setPoint(90);
//		historyVO.setExchangecount(10);
//		historyVO.setPapercount(1);
//		historyVO.setLocation("樹林站後");
//		historyVO.setSid(12);
//		historyVO.setStorename("三峽恩主公店");
//		dao.update(historyVO);
		
//		// Delete
//		HistoryVO historyVO = new HistoryVO();
//		historyVO.setHid(537483);
//		dao.delete(historyVO);
		
		//Query one
//		HistoryVO history = dao.findByPrimaryId(3482383);
//		System.out.print(history.getHid() + ",");
//		System.out.print(history.getTtime() + ",");
//		System.out.print(history.getEvent() + ",");
//		System.out.print(history.getIp() + ",");
//		System.out.print(history.getUid() + ",");
//		System.out.print(history.getDid() + ",");
//		System.out.print(history.getMaid() + ",");
//		System.out.print(history.getMid() + ",");
//		System.out.print(history.getRefundcount() + ",");
//		System.out.print(history.getFreecount() + ",");
//		System.out.print(history.getPoint() + ",");
//		System.out.print(history.getExchangecount() + ",");
//		System.out.print(history.getPapercount() + ",");
//		System.out.print(history.getLocation() + ",");
//		System.out.print(history.getSid() + ",");
//		System.out.println(history.getStorename());
		
		// Query By MemberID
//		List<HistoryVO> list = dao.getByMemberId("陳啟展");
//		for (HistoryVO history : list) {
//			System.out.print(history.getHid() + ",");
//			System.out.print(history.getTtime() + ",");
//			System.out.print(history.getEvent() + ",");
//			System.out.print(history.getIp() + ",");
//			System.out.print(history.getUid() + ",");
//			System.out.print(history.getDid() + ",");
//			System.out.print(history.getMaid() + ",");
//			System.out.print(history.getMid() + ",");
//			System.out.print(history.getRefundcount() + ",");
//			System.out.print(history.getFreecount() + ",");
//			System.out.print(history.getPoint() + ",");
//			System.out.print(history.getExchangecount() + ",");
//			System.out.print(history.getPapercount() + ",");
//			System.out.print(history.getLocation() + ",");
//			System.out.print(history.getSid() + ",");
//			System.out.println(history.getStorename());
//		}

		
		// Query By DID
		String startDate = "2021-06-01";
		String endDate = "2021-07-01";
		List<HistoryVO> list = dao.getListByMaid(startDate, endDate, 2);
		for (HistoryVO history : list) {
			System.out.print(history.getHid() + ",");
			System.out.print(history.getTtime() + ",");
			System.out.print(history.getEvent() + ",");
			System.out.print(history.getDid() + ",");
			System.out.print(history.getMaid() + ",");
			System.out.print(history.getMid() + ",");
			System.out.print(history.getPoint() + ",");
			System.out.print(history.getSid() + ",");
			System.out.println();
		}
		
		
//		// Query 30
//		List<HistoryVO> list = dao.getByMemberId("陳啟展");
//		for (HistoryVO history : list) {
//			System.out.print(history.getTtime() + ",");
//			System.out.print(history.getMid() + ",");
//			System.out.print(history.getPoint() + ",");
//			System.out.print(history.getLocation() + ",");
//			System.out.println(history.getStorename());
//		}
		
		// Query All
//		List<HistoryVO> list = dao.getAll();
//		for (HistoryVO history : list) {
//			System.out.print(history.getHid() + ",");
//			System.out.print(history.getTtime() + ",");
//			System.out.print(history.getEvent() + ",");
//			System.out.print(history.getIp() + ",");
//			System.out.print(history.getUid() + ",");
//			System.out.print(history.getDid() + ",");
//			System.out.print(history.getMaid() + ",");
//			System.out.print(history.getMid() + ",");
//			System.out.print(history.getRefundcount() + ",");
//			System.out.print(history.getFreecount() + ",");
//			System.out.print(history.getPoint() + ",");
//			System.out.print(history.getExchangecount() + ",");
//			System.out.println(history.getPapercount());
//			System.out.print(history.getSid() + ",");
//			System.out.print(history.getStorename() + ",");
//		}

		
		// Query By DID
//		int count = dao.getCount(1);
//		System.out.println("count : " + count);		
//		int lastCount = count - 50;		
//		List<DeviceJsonObject> list = dao.getAllByDid(1, lastCount, 50);
//		for (DeviceJsonObject deviceJsonObject : list) {
//			System.out.print(deviceJsonObject.getTtime() + ",");
//			System.out.print(deviceJsonObject.getEvent() + ",");
//		}

	}

	@Override
	public void insert(HistoryVO historyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setTimestamp(1, historyVO.getTtime());
			pstmt.setString(2, historyVO.getEvent());
			pstmt.setString(3, historyVO.getIp());
			pstmt.setInt(4, historyVO.getUid());
			pstmt.setInt(5, historyVO.getDid());
			pstmt.setInt(6, historyVO.getMaid());
			pstmt.setString(7, historyVO.getMid());
			pstmt.setInt(8, historyVO.getRefundcount());
			pstmt.setInt(9, historyVO.getFreecount());
			pstmt.setInt(10, historyVO.getPoint());
			pstmt.setInt(11, historyVO.getExchangecount());
			pstmt.setInt(12, historyVO.getPapercount());
			pstmt.setString(13, historyVO.getLocation());
			pstmt.setInt(14, historyVO.getSid());
			pstmt.setString(15, historyVO.getStorename());

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
	public void update(HistoryVO historyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setTimestamp(1, historyVO.getTtime());
			pstmt.setString(2, historyVO.getEvent());
			pstmt.setString(3, historyVO.getIp());
			pstmt.setInt(4, historyVO.getUid());
			pstmt.setInt(5, historyVO.getDid());
			pstmt.setInt(6, historyVO.getMaid());
			pstmt.setString(7, historyVO.getMid());
			pstmt.setInt(8, historyVO.getRefundcount());
			pstmt.setInt(9, historyVO.getFreecount());
			pstmt.setInt(10, historyVO.getPoint());
			pstmt.setInt(11, historyVO.getExchangecount());
			pstmt.setInt(12, historyVO.getPapercount());
			pstmt.setString(13, historyVO.getLocation());
			pstmt.setInt(14, historyVO.getSid());
			pstmt.setString(15, historyVO.getStorename());
			pstmt.setInt(16, historyVO.getHid());

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
	public void delete(HistoryVO historyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, historyVO.getHid());
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
	public HistoryVO findByPrimaryId(int hid) {
		HistoryVO historyVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, hid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// historyVO 也稱為 Domain objects
				historyVO = new HistoryVO();
				historyVO.setHid(rs.getInt("hid"));
				historyVO.setTtime(rs.getTimestamp("ttime"));
				historyVO.setIp(rs.getString("ip"));
				historyVO.setUid(rs.getInt("uid"));
				historyVO.setDid(rs.getInt("did"));
				historyVO.setMaid(rs.getInt("maid"));
				historyVO.setMid(rs.getString("mid"));
				historyVO.setRefundcount(rs.getInt("refundcount"));
				historyVO.setFreecount(rs.getInt("freecount"));
				historyVO.setPoint(rs.getInt("point"));
				historyVO.setExchangecount(rs.getInt("exchangecount"));
				historyVO.setPapercount(rs.getInt("papercount"));
				historyVO.setSid(rs.getInt("sid"));
				historyVO.setStorename(rs.getString("storename"));
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
		return historyVO;
	}

	@Override
	public List<HistoryVO> getAll() {
		List<HistoryVO> list = new ArrayList<HistoryVO>();
		HistoryVO historyVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// historyVO 也稱為 Domain objects
				historyVO = new HistoryVO();
				historyVO.setHid(rs.getInt("HID"));
				historyVO.setTtime(rs.getTimestamp("ttime"));
				historyVO.setIp(rs.getString("ip"));
				historyVO.setUid(rs.getInt("uid"));
				historyVO.setDid(rs.getInt("did"));
				historyVO.setMaid(rs.getInt("maid"));
				historyVO.setMid(rs.getString("mid"));
				historyVO.setRefundcount(rs.getInt("refundcount"));
				historyVO.setFreecount(rs.getInt("freecount"));
				historyVO.setPoint(rs.getInt("point"));
				historyVO.setExchangecount(rs.getInt("exchangecount"));
				historyVO.setPapercount(rs.getInt("papercount"));
				historyVO.setLocation(rs.getString("location"));
				historyVO.setSid(rs.getInt("sid"));
				historyVO.setStorename(rs.getString("storename"));

				list.add(historyVO); // Store the row in the list
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
	public List<HistoryVO> getByMemberId(String mid) {
		List<HistoryVO> list = new ArrayList<HistoryVO>();
		HistoryVO historyVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEMBERID_STMT);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// historyVO 也稱為 Domain objects
				historyVO = new HistoryVO();
				historyVO.setHid(rs.getInt("hid"));
				historyVO.setTtime(rs.getTimestamp("ttime"));
				historyVO.setIp(rs.getString("ip"));
				historyVO.setUid(rs.getInt("uid"));
				historyVO.setDid(rs.getInt("did"));
				historyVO.setMaid(rs.getInt("maid"));
				historyVO.setMid(rs.getString("mid"));
				historyVO.setRefundcount(rs.getInt("refundcount"));
				historyVO.setFreecount(rs.getInt("freecount"));
				historyVO.setPoint(rs.getInt("point"));
				historyVO.setExchangecount(rs.getInt("exchangecount"));
				historyVO.setPapercount(rs.getInt("papercount"));
				historyVO.setSid(rs.getInt("sid"));
				historyVO.setStorename(rs.getString("storename"));
				list.add(historyVO);
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
	public List<HistoryVO> get30(String mid) {
		List<HistoryVO> list = new ArrayList<HistoryVO>();
		HistoryVO historyVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_30_STMT);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// historyVO 也稱為 Domain objects
				historyVO = new HistoryVO();
				historyVO.setTtime(rs.getTimestamp("ttime"));
				historyVO.setMid(rs.getString("mid"));
				historyVO.setPoint(rs.getInt("point"));
				historyVO.setLocation(rs.getString("location"));
				historyVO.setSid(rs.getInt("sid"));
				historyVO.setStorename(rs.getString("storename"));
				list.add(historyVO);
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
	public List<DeviceJsonObject> getAllByDid(int did, int index, int total){
		List<DeviceJsonObject> list = new ArrayList<DeviceJsonObject>();
		DeviceJsonObject deviceJsonObject = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_DID_STMT);
			pstmt.setInt(1, did);
			pstmt.setInt(2, index);
			pstmt.setInt(3, total);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// historyVO 也稱為 Domain objects
				deviceJsonObject = new DeviceJsonObject();
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String dateStr = null;
				try {
					dateStr = sdf.format( rs.getTimestamp("ttime") );
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				deviceJsonObject.setTtime(dateStr);
				deviceJsonObject.setEvent(rs.getString("event"));
				deviceJsonObject.setStorename(rs.getString("storename"));
				list.add(deviceJsonObject);
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
	public int getCount(int did) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_COUNT_STMT);
			pstmt.setInt(1, did);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

	@Override
	public List<HistoryVO> getListByMaid(String startDate, String endDate, int maid) {
		List<HistoryVO> list = new ArrayList<HistoryVO>();
		HistoryVO historyVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LIST_BY_MAID_STMT);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setInt(3, maid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// historyVO 也稱為 Domain objects
				historyVO = new HistoryVO();
				historyVO.setHid(rs.getInt("hid"));
				historyVO.setTtime(rs.getTimestamp("ttime"));
				historyVO.setDid(rs.getInt("did"));
				historyVO.setMaid(rs.getInt("maid"));
				historyVO.setMid(rs.getString("mid"));
				historyVO.setPoint(rs.getInt("point"));
				historyVO.setSid(rs.getInt("sid"));
				list.add(historyVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

}
