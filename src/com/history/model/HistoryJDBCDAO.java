package com.history.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryJDBCDAO implements HistoryDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://192.168.100.72:3306/rm_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_COUNT_STMT = 
			"SELECT COUNT(*) FROM history WHERE did = ?";
	private static final String GET_ALL_DID_STMT = 
			"SELECT hid, ttime, event, ip, uid, did, maid, mid, point, location FROM history where did = ?  ORDER BY TTIME DESC LIMIT ?, ?;";
	private static final String GET_ALL_STMT = 
			"SELECT hid, ttime, event, ip, uid, did, maid, mid, refundcount, freecount, exchangecount,"
					+ "papercount FROM history";
	private static final String GET_MEMBERID_STMT = 
			"SELECT hid, ttime, event, ip, uid, did, maid, mid, refundcount, freecount, exchangecount,"
					+ "papercount FROM history where mid = ?";
	private static final String GET_ONE_STMT = 
			"select hid, ttime, event, ip, uid, did, maid, mid, refundcount, freecount, exchangecount,"
					+ "papercount from history where hid = ?";
	private static final String GET_30_STMT = 
			"SELECT TTIME, MID, POINT, LOCATION FROM history "
			+ "WHERE MID = ? AND FREECOUNT > 0 AND POINT > 0 ORDER BY TTIME DESC LIMIT 30;";
	private static final String INSERT_STMT = 
			"INSERT INTO history (ttime, event, ip, uid, did, maid, mid, refundcount, freecount,"
					+ "point, exchangecount, papercount, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE history set ttime=?, event=?, ip=?, uid=?, did=?, maid=?, mid=?, refundcount=?,"
					+ "freecount=?, exchangecount=?, papercount=? where hid = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM history where hid = ?";
	
	public static void main(String[] args) {
		
		HistoryDAO_interface dao = new HistoryJDBCDAO();

//		//Add
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
//		dao.insert(historyVO);
		
//		//Update
//		HistoryVO historyVO = new HistoryVO();
//		Date date = new Date();	//Get now
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Timestamp ts = new Timestamp(date.getTime());
//		System.out.println(formatter.format(ts));
//		historyVO.setHid(1552967);
//		historyVO.setTtime(ts);
//		historyVO.setEvent("");
//		historyVO.setIp("192.168.100.165");
//		historyVO.setUid(0);
//		historyVO.setDid(8);
//		historyVO.setMaid(1);
//		historyVO.setMid("小邰");
//		historyVO.setRefundcount(0);
//		historyVO.setFreecount(0);
//		historyVO.setExchangecount(10);
//		historyVO.setPapercount(1);
//		dao.update(historyVO);
		
//		// Delete
//		HistoryVO historyVO = new HistoryVO();
//		historyVO.setHid(537483);
//		dao.delete(historyVO);
		
//		//Query one
//		HistoryVO historyVO = dao.findByPrimaryId(2010555);
//		System.out.print(historyVO.getHid() + ",");
//		System.out.print(historyVO.getTtime() + ",");
//		System.out.print(historyVO.getEvent() + ",");
//		System.out.print(historyVO.getIp() + ",");
//		System.out.print(historyVO.getUid() + ",");
//		System.out.print(historyVO.getDid() + ",");
//		System.out.print(historyVO.getRefundcount() + ",");
//		System.out.print(historyVO.getFreecount() + ",");
//		System.out.print(historyVO.getExchangecount() + ",");
//		System.out.println(historyVO.getPapercount());
		
//		// Query By MemberID
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
//			System.out.print(history.getExchangecount() + ",");
//			System.out.println(history.getPapercount());
//		}
		
//		// Query 30
//		List<HistoryVO> list = dao.getByMemberId("陳啟展");
//		for (HistoryVO history : list) {
//			System.out.print(history.getTtime() + ",");
//			System.out.print(history.getMid() + ",");
//			System.out.print(history.getPoint() + ",");
//			System.out.println(history.getLocation());
//		}
		
//		// Query All
//		List<HistoryVO> list = dao.getAll();
//		for (HistoryVO history : list) {
//			System.out.print(history.getHid() + ",");
//			System.out.print(history.getTtime() + ",");
//			System.out.print(history.getEvent() + ",");
//			System.out.print(history.getIp() + ",");
//			System.out.print(history.getUid() + ",");
//			System.out.print(history.getDid() + ",");
//			System.out.print(history.getRefundcount() + ",");
//			System.out.print(history.getFreecount() + ",");
//			System.out.print(history.getExchangecount() + ",");
//			System.out.println(history.getPapercount());
//		}
		
		int count = dao.getCount(1);
		System.out.println("count : " + count);
		
		int lastCount = count - 50;
		
		// Query By DID
		List<HistoryVO> list = dao.getAllByDid(1, lastCount, 50);
		for (HistoryVO history : list) {
			System.out.print(history.getTtime() + ",");
			System.out.print(history.getEvent() + ",");
			System.out.print(history.getPoint() + ",");
			System.out.println(history.getLocation());
		}
		


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
			pstmt.setInt(10, historyVO.getExchangecount());
			pstmt.setInt(11, historyVO.getPapercount());
			pstmt.setInt(12, historyVO.getHid());

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
				historyVO.setExchangecount(rs.getInt("exchangecount"));
				historyVO.setPapercount(rs.getInt("papercount"));
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
				historyVO.setExchangecount(rs.getInt("exchangecount"));
				historyVO.setPapercount(rs.getInt("papercount"));

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
				historyVO.setExchangecount(rs.getInt("exchangecount"));
				historyVO.setPapercount(rs.getInt("papercount"));
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
	public List<HistoryVO> getAllByDid(int did, int index, int total){
		List<HistoryVO> list = new ArrayList<HistoryVO>();
		HistoryVO historyVO = null;		
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
				historyVO = new HistoryVO();
				historyVO.setTtime(rs.getTimestamp("ttime"));
				historyVO.setEvent(rs.getString("event"));
				historyVO.setPoint(rs.getInt("point"));
				historyVO.setLocation(rs.getString("location"));
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

}
