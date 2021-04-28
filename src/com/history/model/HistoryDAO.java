package com.history.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.addrecord.model.AddRecordVO;

public class HistoryDAO implements HistoryDAO_interface{
	
	HistoryDAO_interface dao;
	
	private static final String GET_ALL_STMT = 
			"SELECT hid, ttime, event, ip, uid, did, refundcount, freecount, exchangecount,"
					+ "papercount FROM history";
	private static final String GET_MEMBERID_STMT = 
			"SELECT hid, ttime, event, ip, uid, did, maid, mid, refundcount, freecount, exchangecount,"
					+ "papercount FROM history where mid = ?";
	private static final String GET_ONE_STMT = 
			"select hid, ttime, event, ip, uid, did, refundcount, freecount, exchangecount,"
					+ "papercount from history where hid = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO history (ttime, event, ip, uid, did, refundcount, freecount,"
					+ "exchangecount, papercount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			// TODO Auto-generated method stub
			
		}
		@Override
		public void update(HistoryVO historyVO) {
			// TODO Auto-generated method stub
			
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
					System.out.println("test:" + rs.getString("mid"));
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

}
