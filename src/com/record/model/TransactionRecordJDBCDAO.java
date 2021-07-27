package com.record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.addrecord.model.AddRecordVO;
import com.device.model.DeviceService;
import com.device.model.DeviceVO;
import com.history.model.HistoryVO;
import com.machine.model.MachineService;
import com.machine.model.MachineVO;

public class TransactionRecordJDBCDAO implements TransactionRecordDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://211.21.93.170:3306/rm_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_30_ADDRECORD_STMT = 
			"SELECT STOREDATETIME, POINT, STORENAME  FROM addrecord WHERE USERNAME = ? ORDER BY STOREDATETIME DESC LIMIT 30";
	private static final String GET_30_HISTORY_STMT = 
			"SELECT TTIME, POINT, STORENAME  FROM history WHERE MID = ? AND FREECOUNT > 0 AND POINT > 0 ORDER BY TTIME DESC LIMIT 30";
	
	private static final String GET_ADDRECORD_30_STMT = 
			"SELECT STOREDATETIME, POINT, DEVICEID, STOREID, STORENAME, MONEY FROM addrecord WHERE USERNAME = ? ORDER BY STOREDATETIME DESC LIMIT 30";
	private static final String GET_HISTORY_30_STMT = 
			"SELECT TTIME, DID, MAID, POINT, SID, STORENAME  FROM history WHERE MID = ? AND POINT > 0 ORDER BY TTIME DESC LIMIT 30";

	public static void main(String[] args) {
		TransactionRecordDAO_interface dao = new TransactionRecordJDBCDAO();
		
//		List<TransactionRecordVO> list = dao.get30Record("陳啟展");
//		for(TransactionRecordVO transactionRecordVO : list) {
//			System.out.print(transactionRecordVO.getRecordTimeStamp() + ",");
//			System.out.print(transactionRecordVO.getType() + ",");
//			System.out.print(transactionRecordVO.getPoint() + ",");
//			System.out.println(transactionRecordVO.getStorename());
//		}

		
		List<RecordVO> list = dao.getRecord30("陳啟展");
		for(RecordVO recordVO : list) {
			System.out.print(recordVO.getMid() + ",");
			System.out.print(recordVO.getSid() + ",");
			System.out.print(recordVO.getId() + ",");
			System.out.print(recordVO.getStoreName() + ",");
			System.out.print(recordVO.getRecordDate() + ",");
			System.out.print(recordVO.getMoney() + ",");
			System.out.print(recordVO.getPoint() + ",");
			System.out.print(recordVO.getType() + ",");
			
//			int type = recordVO.getType();
//			if(type == 1) {
//				DeviceVO deviceVO = new DeviceService().getOnByDid(recordVO.getId());
//				String name = deviceVO.getLocation();
//				System.out.print(name + ",");
//			}
//			else
//			if(type == 2) {
//				MachineVO machineVO = new MachineService().getOneMachine(recordVO.getId());
//				String name = machineVO.getName();
//				System.out.print(name + ",");				
//			}
			System.out.println();
		}
	}

	@Override
	public List<TransactionRecordVO> get30Record(String username) {
		List<TransactionRecordVO> list = new ArrayList<TransactionRecordVO>();
		List<TransactionRecordVO> transactionRecordVOs = new ArrayList<TransactionRecordVO>();
		List<HistoryVO> historyVOs = new ArrayList<HistoryVO>();
		List<AddRecordVO> addRecordVOs = new ArrayList<AddRecordVO>();
		TransactionRecordVO transactionRecordVO = null;
		HistoryVO historyVO = null;
		AddRecordVO addRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
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
			
			
//			for(TransactionRecordVO vo : transactionRecordVOs) {
//				System.out.print(vo.getRecordTimeStamp() + ",");
//				System.out.print(vo.getType() + ",");
//				System.out.print(vo.getPoint() + ",");
//				System.out.println(vo.getLocation());
//			}
			
			

			transactionRecordVOs.sort(Comparator.comparing(TransactionRecordVO::getRecordTimeStamp));
			int toSkip = Math.max(transactionRecordVOs.size() - 30, 0);
						
			//list = transactionRecordVOs.stream().skip(toSkip).collect(Collectors.toCollection(ArrayList::new));
			
			
			
			list = transactionRecordVOs.stream().skip(toSkip).sorted(Comparator.comparing(TransactionRecordVO::getRecordTimeStamp).reversed()).collect(Collectors.toList());
			//transactionRecordVOs = transactionRecordVOs.stream().sorted(Comparator.comparing(TransactionRecordVO::getRecordTimeStamp).reversed()).collect(Collectors.toList());
			
			//Collections.sort(list, Collections.reverseOrder());
			
			
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

	@Override
	public List<RecordVO> getRecord30(String username) {
		List<RecordVO> list = new ArrayList<RecordVO>();
		List<RecordVO> recordVOs = new ArrayList<RecordVO>();
		RecordVO recordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
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
