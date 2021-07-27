package com.record.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TransactionRecordDAO implements TransactionRecordDAO_interface{
	
	TransactionRecordDAO_interface dao;
	
	private static final String GET_30_ADDRECORD_STMT = 
			"SELECT STOREDATETIME, POINT, STORENAME  FROM addrecord WHERE USERNAME = ? ORDER BY STOREDATETIME DESC LIMIT 30";
	private static final String GET_30_HISTORY_STMT = 
			"SELECT TTIME, POINT, STORENAME  FROM history WHERE MID = ? AND FREECOUNT > 0 AND POINT > 0 ORDER BY TTIME DESC LIMIT 30";

	private static final String GET_ADDRECORD_30_STMT = 
			"SELECT STOREDATETIME, POINT, DEVICEID, STOREID, STORENAME, MONEY FROM addrecord WHERE USERNAME = ? ORDER BY STOREDATETIME DESC LIMIT 30";
	private static final String GET_HISTORY_30_STMT = 
			"SELECT TTIME, DID, MAID, POINT, SID, STORENAME  FROM history WHERE MID = ? AND POINT > 0 ORDER BY TTIME DESC LIMIT 30";

	
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
	public List<TransactionRecordVO> get30Record(String username) {
		List<TransactionRecordVO> list = new ArrayList<TransactionRecordVO>();
		List<TransactionRecordVO> transactionRecordVOs = new ArrayList<TransactionRecordVO>();
		TransactionRecordVO transactionRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_30_HISTORY_STMT);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();			
			
			while (rs.next()) {
				transactionRecordVO = new TransactionRecordVO();
				transactionRecordVO.setRecordTimeStamp(rs.getTimestamp("ttime"));
				transactionRecordVO.setPoint(rs.getInt("point"));
				transactionRecordVO.setStorename(rs.getString("storename"));
				transactionRecordVO.setType("消費");
				transactionRecordVOs.add(transactionRecordVO);
			}
			
			pstmt.close();
			rs.close();
			
			pstmt = con.prepareStatement(GET_30_ADDRECORD_STMT);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				transactionRecordVO = new TransactionRecordVO();
				transactionRecordVO.setRecordTimeStamp(rs.getTimestamp("storedatetime"));
				transactionRecordVO.setPoint(rs.getInt("point"));
				transactionRecordVO.setStorename(rs.getString("storename"));
				transactionRecordVO.setType("加值");
				transactionRecordVOs.add(transactionRecordVO);
			}

			transactionRecordVOs.sort(Comparator.comparing(TransactionRecordVO::getRecordTimeStamp));
			int toSkip = Math.max(transactionRecordVOs.size() - 30, 0);
			list = transactionRecordVOs.stream().skip(toSkip).sorted(Comparator.comparing(TransactionRecordVO::getRecordTimeStamp).reversed()).collect(Collectors.toList());
		
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
	public List<RecordVO> getRecord30(String username) {
		List<RecordVO> list = new ArrayList<RecordVO>();
		List<RecordVO> recordVOs = new ArrayList<RecordVO>();
		RecordVO recordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_HISTORY_30_STMT);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();			
			
			while (rs.next()) {
				recordVO = new RecordVO();
				recordVO.setMid(username);
				recordVO.setSid(rs.getInt("sid"));
				recordVO.setId(rs.getInt("maid"));
				recordVO.setStoreName(rs.getString("storename"));
				recordVO.setRecordDate(rs.getDate("ttime"));
				recordVO.setMoney(-1);
				recordVO.setPoint(rs.getInt("point"));
				recordVO.setType(2);
				recordVOs.add(recordVO);
			}
			
			pstmt.close();
			rs.close();
			
			pstmt = con.prepareStatement(GET_ADDRECORD_30_STMT);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				recordVO = new RecordVO();
				recordVO.setMid(username);
				recordVO.setSid(rs.getInt("storeid"));
				recordVO.setId(rs.getInt("deviceid"));
				recordVO.setStoreName(rs.getString("storename"));
				recordVO.setRecordDate(rs.getDate("storedatetime"));
				recordVO.setMoney(rs.getInt("money"));
				recordVO.setPoint(rs.getInt("point"));
				recordVO.setType(1);
				recordVOs.add(recordVO);
			}

			recordVOs.sort(Comparator.comparing(RecordVO::getRecordDate));
			int toSkip = Math.max(recordVOs.size() - 30, 0);			
			list = recordVOs.stream().skip(toSkip).sorted(Comparator.comparing(RecordVO::getRecordDate).reversed()).collect(Collectors.toList());

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

}
