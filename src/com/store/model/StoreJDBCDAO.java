package com.store.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.device.model.DeviceVO;
import com.machine.model.MachineVO;

public class StoreJDBCDAO implements StoreDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://211.21.93.170:3306/rm_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_ALL_STMT = 
			"SELECT sid, name, city, district, pause, single_count, multi_count, discount_1_money, discount_1_point,"
					+ "discount_2_money, discount_2_point, discount_3_money, discount_3_point FROM store";
	private static final String GET_ONE_STMT = 
			"SELECT sid, name, city, district, pause, single_count, multi_count, discount_1_money, discount_1_point," + 
			"					+ discount_2_money, discount_2_point, discount_3_money, discount_3_point FROM store where sid = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO store (name, city, district, pause, single_count, multi_count, discount_1_money, discount_1_point," + 
								"discount_2_money, discount_2_point, discount_3_money, discount_3_point) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE store set name=?, city=?, district=?, pause=?, single_count=?, multi_count=?, discount_1_money=?, "
								+ "discount_1_point=?, discount_2_money=?, discount_2_point=?, discount_3_money=?, "
								+ "discount_3_point=? where sid = ?";
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

	public static void main(String[] args) {
		StoreJDBCDAO dao = new StoreJDBCDAO();
		
		
		//Add
//		StoreVO storeVO = new StoreVO();
//		storeVO.setName("三峽恩主公店");
//		storeVO.setCity("新北市");
//		storeVO.setDistrict("三峽區");
//		storeVO.setPause(1);					//1預設為啟用
//		storeVO.setSingle_count(100);
//		storeVO.setMulti_count(10);
//		storeVO.setDiscount_1_money(200);
//		storeVO.setDiscount_1_point(250);
//		storeVO.setDiscount_2_money(500);
//		storeVO.setDiscount_2_point(700);
//		storeVO.setDiscount_3_money(1000);
//		storeVO.setDiscount_3_point(1300);		
//		dao.insert(storeVO);
		
		//Update
//		StoreVO storeVO = new StoreVO();
//		storeVO.setSid(11);
//		storeVO.setName("三峽恩主公店");
//		storeVO.setCity("新北市");
//		storeVO.setDistrict("三峽區");
//		storeVO.setPause(1);					//1預設為啟用
//		storeVO.setSingle_count(100);
//		storeVO.setMulti_count(10);
//		storeVO.setDiscount_1_money(200);
//		storeVO.setDiscount_1_point(250);
//		storeVO.setDiscount_2_money(500);
//		storeVO.setDiscount_2_point(700);
//		storeVO.setDiscount_3_money(1000);
//		storeVO.setDiscount_3_point(1300);
//		dao.update(storeVO);

		
		// Delete
//		StoreVO storeVO = new StoreVO();
//		storeVO.setSid(11);
//		dao.delete(storeVO);


		// Query One
//		StoreVO store = dao.findByPrimaryId(6);
//		System.out.print(store.getSid() + ",");
//		System.out.print(store.getName() + ",");
//		System.out.print(store.getCity() + ",");
//		System.out.print(store.getDistrict()+ ",");
//		System.out.print(store.getPause()+ ",");
//		System.out.print(store.getSingle_count()+ ",");
//		System.out.print(store.getMulti_count()+ ",");
//		System.out.print(store.getDiscount_1_money()+ ",");
//		System.out.print(store.getDiscount_1_point()+ ",");
//		System.out.print(store.getDiscount_2_money()+ ",");
//		System.out.print(store.getDiscount_2_point()+ ",");
//		System.out.print(store.getDiscount_3_money()+ ",");
//		System.out.println(store.getDiscount_3_point());
		
//		// Query All
//		List<StoreVO> list = dao.getAll();
//		for (StoreVO store : list) {
//			System.out.print(store.getSid() + ",");
//			System.out.print(store.getName() + ",");
//			System.out.print(store.getCity() + ",");
//			System.out.print(store.getDistrict()+ ",");
//			System.out.print(store.getPause()+ ",");
//			System.out.print(store.getSingle_count()+ ",");
//			System.out.print(store.getMulti_count()+ ",");
//			System.out.print(store.getDiscount_1_money()+ ",");
//			System.out.print(store.getDiscount_1_point()+ ",");
//			System.out.print(store.getDiscount_2_money()+ ",");
//			System.out.print(store.getDiscount_2_point()+ ",");
//			System.out.print(store.getDiscount_3_money()+ ",");
//			System.out.println(store.getDiscount_3_point());
//		}
		
//		List<StatisticsAllVO> list = dao.getAllStatistics("2021-06-11", "2021-06-30");
//		for (StatisticsAllVO statisticsAllVO : list) {
//			System.out.print(statisticsAllVO.getSid() + ",");
//			System.out.print(statisticsAllVO.getName() + ",");
//			System.out.print(statisticsAllVO.getAdd_money() + ",");
//			System.out.print(statisticsAllVO.getAdd_point() + ",");
//			System.out.println(statisticsAllVO.getConsumption_point());
//		}
		
		List<StatisticsAllVO> list = dao.getAllStatisticsBySid(1, "2020-06-11", "2021-07-15");
		for (StatisticsAllVO statisticsAllVO : list) {
			System.out.print(statisticsAllVO.getSid() + ",");
			System.out.print(statisticsAllVO.getName() + ",");
			System.out.print(statisticsAllVO.getNumber() + ",");
			System.out.print(statisticsAllVO.getAdd_money() + ",");
			System.out.print(statisticsAllVO.getAdd_point() + ",");
			System.out.println(statisticsAllVO.getConsumption_point());
		}
	}

	@Override
	public void insert(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			pstmt.setInt(13, storeVO.getSid());
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
	public void delete(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, storeVO.getSid());
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
	public StoreVO findByPrimaryId(int sid) {
		StoreVO storeVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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
