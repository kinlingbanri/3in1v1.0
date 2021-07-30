package com.store.model;

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

import com.device.model.DeviceVO;
import com.machine.model.MachineVO;

public class StoreDAO implements StoreDAO_interface{
	
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
			"SELECT sid, name, city, district, pause, single_count, multi_count, discount_1_money, discount_1_point,"
					+ "discount_2_money, discount_2_point, discount_3_money, discount_3_point FROM store";
	private static final String GET_ONE_STMT = 
			"SELECT sid, name, city, district, pause, single_count, multi_count, discount_1_money, discount_1_point, " + 
					"discount_2_money, discount_2_point, discount_3_money, discount_3_point, machine_count " + 
					"FROM store where sid = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO store (name, city, district, pause, single_count, multi_count, discount_1_money, discount_1_point," + 
								"discount_2_money, discount_2_point, discount_3_money, discount_3_point, machine_count) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE store set name=?, city=?, district=?, pause=?, single_count=?, multi_count=?, discount_1_money=?, "
								+ "discount_1_point=?, discount_2_money=?, discount_2_point=?, discount_3_money=?, "
								+ "discount_3_point=?, machine_count=? where sid = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM store where sid = ?";
	
	private static final String GET_ADD_Statistics_STMT = 
			"SELECT storeid, SUM(POINT) add_point, SUM(money) add_money FROM addrecord WHERE STR_TO_DATE(storedatetime, '%Y-%m-%d') >= ?"
			+ " AND STR_TO_DATE(storedatetime, '%Y-%m-%d') <= ? GROUP BY storeid;";

	private static final String GET_HISTORY_Statistics_STMT = 
			"SELECT sid, SUM(POINT) point FROM history WHERE STR_TO_DATE(ttime, '%Y-%m-%d') >= ? "
			+ "AND STR_TO_DATE(ttime, '%Y-%m-%d') <= ? GROUP BY sid;";
	
	
	private static final String GET_DEVICE_ALL_BY_SID_STMT = 
			"SELECT did, number, location FROM device where sid = ?";
	private static final String GET_MACHINE_ALL_BY_SID_STMT = 
			"SELECT machid, number, name FROM machine WHERE sid = ?";
	
	private static final String GET_ADD_Statistics_By_DID_STMT = 
			"SELECT SUM(POINT) add_point, SUM(money) add_money FROM addrecord WHERE STR_TO_DATE(storedatetime, '%Y-%m-%d') >= ?"
			+ " AND STR_TO_DATE(storedatetime, '%Y-%m-%d') <= ? AND deviceid = ?;";
	private static final String GET_HISTORY_Statistics_BY_MAID_STMT = 
			"SELECT SUM(POINT) point FROM history WHERE STR_TO_DATE(ttime, '%Y-%m-%d') >= ?"
			+ "AND STR_TO_DATE(ttime, '%Y-%m-%d') <= ? AND maid = ? AND sid = ?;";
	
	
	

	@Override
	public void insert(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, storeVO.getName());
			pstmt.setString(2, storeVO.getCity());
			pstmt.setString(3, storeVO.getDistrict());
			pstmt.setInt(4, storeVO.getPause());
			pstmt.setInt(5, storeVO.getSingle_count());
			pstmt.setInt(6, storeVO.getMulti_count());
			pstmt.setInt(7, storeVO.getDiscount_1_money());
			pstmt.setInt(8, storeVO.getDiscount_1_point());
			pstmt.setInt(9, storeVO.getDiscount_2_money());
			pstmt.setInt(10, storeVO.getDiscount_2_point());
			pstmt.setInt(11, storeVO.getDiscount_3_money());
			pstmt.setInt(12, storeVO.getDiscount_3_point());
			pstmt.setInt(13, 0);
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
	public void update(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, storeVO.getName());
			pstmt.setString(2, storeVO.getCity());
			pstmt.setString(3, storeVO.getDistrict());
			pstmt.setInt(4, storeVO.getPause());
			pstmt.setInt(5, storeVO.getSingle_count());
			pstmt.setInt(6, storeVO.getMulti_count());
			pstmt.setInt(7, storeVO.getDiscount_1_money());
			pstmt.setInt(8, storeVO.getDiscount_1_point());
			pstmt.setInt(9, storeVO.getDiscount_2_money());
			pstmt.setInt(10, storeVO.getDiscount_2_point());
			pstmt.setInt(11, storeVO.getDiscount_3_money());
			pstmt.setInt(12, storeVO.getDiscount_3_point());
			pstmt.setInt(13, storeVO.getMachine_count());
			pstmt.setInt(14, storeVO.getSid());
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
	public void delete(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, storeVO.getSid());
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
	public StoreVO findByPrimaryId(int sid) {
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				storeVO = new StoreVO();
				storeVO.setSid(rs.getInt("sid"));
				storeVO.setName(rs.getString("name"));
				storeVO.setCity(rs.getString("city"));
				storeVO.setDistrict(rs.getString("district"));
				storeVO.setPause(rs.getInt("pause"));
				storeVO.setSingle_count(rs.getInt("single_count"));
				storeVO.setMulti_count(rs.getInt("multi_count"));
				storeVO.setDiscount_1_money(rs.getInt("discount_1_money"));
				storeVO.setDiscount_1_point(rs.getInt("discount_1_point"));
				storeVO.setDiscount_2_money(rs.getInt("discount_2_money"));
				storeVO.setDiscount_2_point(rs.getInt("discount_2_point"));
				storeVO.setDiscount_3_money(rs.getInt("discount_3_money"));
				storeVO.setDiscount_3_point(rs.getInt("discount_3_point"));
				storeVO.setMachine_count(rs.getInt("machine_count"));
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
		return storeVO;
	}

	@Override
	public List<StoreVO> getAll() {
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				storeVO = new StoreVO();
				storeVO.setSid(rs.getInt("sid"));
				storeVO.setName(rs.getString("name"));
				storeVO.setCity(rs.getString("city"));
				storeVO.setDistrict(rs.getString("district"));
				storeVO.setPause(rs.getInt("pause"));
				storeVO.setSingle_count(rs.getInt("single_count"));
				storeVO.setMulti_count(rs.getInt("multi_count"));
				storeVO.setDiscount_1_money(rs.getInt("discount_1_money"));
				storeVO.setDiscount_1_point(rs.getInt("discount_1_point"));
				storeVO.setDiscount_2_money(rs.getInt("discount_2_money"));
				storeVO.setDiscount_2_point(rs.getInt("discount_2_point"));
				storeVO.setDiscount_3_money(rs.getInt("discount_3_money"));
				storeVO.setDiscount_3_point(rs.getInt("discount_3_point"));
				list.add(storeVO); // Store the row in the list
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
	public List<StatisticsAllVO> getAllStatistics(String startDate, String endDate) {
		List<StatisticsAllVO> list = new ArrayList<StatisticsAllVO>();
		StatisticsAllVO statisticsAllVO = null;
		
		List<StatisticsAllVO> add_list = new ArrayList<StatisticsAllVO>();		
		StatisticsAllVO addVO = null;
		
		List<StatisticsAllVO> consumption_list = new ArrayList<StatisticsAllVO>();		
		StatisticsAllVO consumptionVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				statisticsAllVO = new StatisticsAllVO();
				statisticsAllVO.setSid(rs.getInt("sid"));
				statisticsAllVO.setName(rs.getString("name"));
				list.add(statisticsAllVO); // Store the row in the list
			}
			
			pstmt = con.prepareStatement(GET_ADD_Statistics_STMT);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				addVO = new StatisticsAllVO();
				addVO.setSid(rs.getInt("storeid"));
				addVO.setAdd_money(rs.getInt("add_money"));
				addVO.setAdd_point(rs.getInt("add_point"));
				add_list.add(addVO); // Store the row in the list
			}
			
			for (StatisticsAllVO vo1 : add_list) {				
				for (StatisticsAllVO vo2 : list) {
					if(vo1.getSid() == vo2.getSid()) {
						vo2.setAdd_money(vo1.getAdd_money());
						vo2.setAdd_point(vo1.getAdd_point());
					}
				}
			}
			
			pstmt = con.prepareStatement(GET_HISTORY_Statistics_STMT);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				consumptionVO = new StatisticsAllVO();
				consumptionVO.setSid(rs.getInt("sid"));
				consumptionVO.setConsumption_point(rs.getInt("point"));
				consumption_list.add(consumptionVO); // Store the row in the list
			}
			
			for (StatisticsAllVO vo1 : consumption_list) {				
				for (StatisticsAllVO vo2 : list) {
					if(vo1.getSid() == vo2.getSid()) {
						vo2.setConsumption_point(vo1.getConsumption_point());
					}
				}
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
	public List<StatisticsAllVO> getAllStatisticsBySid(int sid, String startDate, String endDate) {
		List<StatisticsAllVO> list = new ArrayList<StatisticsAllVO>();
		StatisticsAllVO statisticsAllVO = null;
		
		List<StatisticsAllVO> add_list = new ArrayList<StatisticsAllVO>();		
		StatisticsAllVO addVO = null;
		
		List<StatisticsAllVO> consumption_list = new ArrayList<StatisticsAllVO>();		
		StatisticsAllVO consumptionVO = null;
		
		List<DeviceVO> deviceVOs = new ArrayList<DeviceVO>();
		DeviceVO deviceVO = null;
		
		List<MachineVO> machineVOs = new ArrayList<MachineVO>();
		MachineVO machineVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DEVICE_ALL_BY_SID_STMT);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				deviceVO = new DeviceVO();
				deviceVO.setDid(rs.getInt("did"));
				deviceVO.setNumber(rs.getString("number"));
				deviceVO.setLocation(rs.getString("location"));
				deviceVOs.add(deviceVO); // Store the row in the list
			}
			
			pstmt = con.prepareStatement(GET_ADD_Statistics_By_DID_STMT);			
			for(DeviceVO device : deviceVOs) {
				
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
				pstmt.setInt(3, device.getDid());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					statisticsAllVO = new StatisticsAllVO();
					statisticsAllVO.setName(device.getLocation());
					statisticsAllVO.setNumber(device.getNumber());
					statisticsAllVO.setAdd_money(rs.getInt("add_money"));
					statisticsAllVO.setAdd_point(rs.getInt("add_point"));
					list.add(statisticsAllVO);					
				}
		    }
			
			pstmt = con.prepareStatement(GET_MACHINE_ALL_BY_SID_STMT);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				machineVO = new MachineVO();
				machineVO.setMachid(rs.getInt("machid"));
				machineVO.setNumber(rs.getString("number"));
				machineVO.setName(rs.getString("name"));
				machineVOs.add(machineVO); // Store the row in the list
			}

			pstmt = con.prepareStatement(GET_HISTORY_Statistics_BY_MAID_STMT);	
			for(MachineVO machine : machineVOs) {
				
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
				pstmt.setInt(3, machine.getMachid());
				pstmt.setInt(4, sid);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					statisticsAllVO = new StatisticsAllVO();
					statisticsAllVO.setName(machine.getName());
					statisticsAllVO.setNumber(machine.getNumber());
					statisticsAllVO.setConsumption_point(rs.getInt("point"));
					list.add(statisticsAllVO);					
				}
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

}
