package com.history.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HistoryDAO implements HistoryDAO_interface{
	
	HistoryDAO_interface dao;
	
	private static final String GET_COUNT_STMT = 
			"SELECT COUNT(*) FROM history WHERE did = ?";
	private static final String GET_ALL_DID_STMT = 
			"SELECT hid, ttime, event, ip, uid, did, maid, mid, point, location FROM history where did = ?  ORDER BY history.TTIME DESC LIMIT ?, ?;";
	private static final String GET_ALL_STMT = 
			"SELECT hid, ttime, event, ip, uid, did, maid, mid, refundcount, freecount, exchangecount,"
					+ "papercount, location FROM history";
	private static final String GET_MEMBERID_STMT = 
			"SELECT hid, ttime, event, ip, uid, did, maid, mid, refundcount, freecount, exchangecount,"
					+ "papercount FROM history where mid = ?";
	private static final String GET_30_STMT = 
			"SELECT TTIME, MID, POINT, LOCATION FROM history "
			+ "WHERE MID = ? AND FREECOUNT > 0 AND POINT > 0 ORDER BY TTIME DESC LIMIT 30;";
	private static final String GET_ONE_STMT = 
			"select hid, ttime, event, ip, uid, did, refundcount, freecount, exchangecount,"
					+ "papercount from history where hid = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO history (ttime, event, ip, uid, did, maid, mid, refundcount, freecount,"
					+ "point, exchangecount, papercount, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE history set ttime=?, event=?, ip=?, uid=?, did=?, refundcount=?,"
					+ "freecount=?, exchangecount=?, papercount=? where hid = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM history where hid = ?";
	
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
		@Override
		public void insert(HistoryVO historyVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
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
			ResultSet rs = null;

			try {
				con = ds.getConnection();
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
		}
		@Override
		public void delete(HistoryVO historyVO) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public HistoryVO findByPrimaryId(int hid) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public List<HistoryVO> getAll() {
			List<HistoryVO> list = new ArrayList<HistoryVO>();
			HistoryVO historyVO = null;	
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// addRecordVO 也稱為 Domain objects
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
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_MEMBERID_STMT);
				pstmt.setString(1, mid);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// addRecordVO 也稱為 Domain objects
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
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public List<DeviceJsonObject> getAllByDid(int did, int index, int total){
			List<DeviceJsonObject> list = new ArrayList<DeviceJsonObject>();
			DeviceJsonObject deviceJsonObject = null;		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
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
					list.add(deviceJsonObject);
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
		public int getCount(int did) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_COUNT_STMT);
				pstmt.setInt(1, did);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					count = rs.getInt(1);
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
			return count;
		}

}
