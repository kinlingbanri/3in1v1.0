package com.device.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeviceJDBCDAO implements DeviceDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	//String url = "jdbc:mysql://211.21.93.171:3306/threeinone?useUnicode=true&characterEncoding=utf-8";
	String url = "jdbc:mysql://211.21.93.170:3306/rm_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_ALL_STMT = 
			"SELECT did, number, coin, paper, location, refund, uid, status, add_status, error, "
			+ "100_count, 500_count, 1000_count, point, add_point, add_money, add_life_status,"
			+ "machid, freecount, freecountset, sid, mid FROM device";
	private static final String GET_ONE_STMT = 
			"SELECT did, number, coin, paper, location, refund, uid, status, add_status, error, "
			+ "100_count, 500_count, 1000_count, point, add_point, add_money, add_life_status,"
			+ "machid, freecount, freecountset, sid, mid FROM device where number = ?";
	private static final String GET_DEVICE_ADD_STATUS_STMT = 
			"SELECT add_status FROM device where number = ?";
	private static final String GET_DEVICE_STATUS_STMT = 
			"SELECT status FROM device where number = ?";
	private static final String GET_CHECK_MONEY_STMT = 
			"SELECT did, number, ADD_STATUS, 100_COUNT, 500_COUNT, 1000_COUNT"
			+ " FROM device where number = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO device (number, coin, paper, location, refund, uid, status, add_status, error,"
					+ "100_count, 500_count, 1000_count, point, add_point, machid, freecount, freecountset, "
					+ "sid, mid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE device set number=?, coin=?, paper=?, location=?, refund=?, uid=?,"
					+ "status=?, add_status=?, 100_count=?, 500_count=?, 1000_count=?, "
					+ "point=?, add_point=?, error=?, machid=?, freecount=?, freecountset=?, "
					+ "sid=?, mid=? where did = ?";
	private static final String UPDATE_STATUS_STMT = 
			"UPDATE device set status=? where number = ?";
	private static final String UPDATE_CONSUMPTION_STMT = 
			"UPDATE device set status=?, machid=?, freecount=? where number = ?";
//	private static final String UPDATE_ADD_STATUS_11_STMT = 
//			"UPDATE device set add_status=?, point=? where number = ?";
	private static final String UPDATE_ADD_STATUS_11_STMT = 
			"UPDATE device set add_status=?, point=?, 100_count=?, 500_count=?, 1000_count=?, add_point=? where number = ?";
	private static final String UPDATE_ADD_STATUS_13_STMT = 
			"UPDATE device set add_status=?, add_point=?, add_money=?, add_life_status=? where number = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM device where did = ?";
	private static final String AUTO_INCREMENT_STMT = "SELECT `AUTO_INCREMENT` "
			+ "FROM  INFORMATION_SCHEMA.TABLES "
			+ "WHERE TABLE_SCHEMA = 'rm_58' AND TABLE_NAME = 'device';";

	public static void main(String[] args) {
		DeviceDAO_interface dao = new DeviceJDBCDAO();
		

		
		//Add
//		DeviceVO deviceVO = new DeviceVO();		
//		int count = dao.checkAutoIncrement();
//		String numberStr =  "TY" + String.format("%05d", count);
//		System.out.print("numberStr : " + numberStr);
//		deviceVO.setNumber(numberStr);
//		deviceVO.setCoin(0);
//		deviceVO.setPaper(0);
//		deviceVO.setLocation("三峽大同店");
//		deviceVO.setRefund(0);
//		deviceVO.setUid(1);
//		deviceVO.setStatus(0);
//		deviceVO.setAdd_status(0);
//		deviceVO.setCount_100(0);
//		deviceVO.setCount_500(0);
//		deviceVO.setCount_1000(0);
//		deviceVO.setPoint(0);
//		deviceVO.setAdd_point(0);
//		deviceVO.setError(0);
//		deviceVO.setMachid(0);
//		deviceVO.setFreecount(0);
//		deviceVO.setFreecountset(1);
//		deviceVO.setSid(1);
//		deviceVO.setMid("");
//		dao.insert(deviceVO);
		
		//Update
//		DeviceVO deviceVO = new DeviceVO();
//		deviceVO.setNumber("TY00032");
//		deviceVO.setCoin(0);
//		deviceVO.setPaper(0);
//		deviceVO.setLocation("三峽大埔店");
//		deviceVO.setRefund(0);
//		deviceVO.setUid(1);
//		deviceVO.setStatus(11);
//		deviceVO.setAdd_status(0);
//		deviceVO.setCount_100(0);
//		deviceVO.setCount_500(0);
//		deviceVO.setCount_1000(0);
//		deviceVO.setPoint(0);
//		deviceVO.setAdd_point(0);
//		deviceVO.setError(0);
//		deviceVO.setMachid(0);
//		deviceVO.setFreecount(0);
//		deviceVO.setFreecountset(0);
//		deviceVO.setSid(6);
//		deviceVO.setMid("");
//		deviceVO.setDid(31);
//		dao.update(deviceVO);
		
		//Update
//		dao.updateStatus("TY00031", 15);
		
//		dao.updateAddStatus11("TY00031", 11, 1200);
		
//		dao.updateAddStatus13("TY00031", 11, 1600);
		
		//Update Consumption
		dao.updateConsumption("TY00001", 0, 8, 10);
		
//		// Delete
//		DeviceVO deviceVO = new DeviceVO();
//		deviceVO.setDid(31);
//		dao.delete(deviceVO);
		
		//Query Device Status
//		boolean status = dao.getStatus("TY00001");
//		System.out.print("Device Status : " + status);
		
		//Query Device Status
//		DeviceVO deviceVO = dao.getAddStatus("TY00003");
//		System.out.print("Add Status : " + deviceVO.getAdd_status());
		
		// Query check money
//		DeviceVO deviceVO = dao.getCheckMoney("TY00001");
//		System.out.print(deviceVO.getDid() + ",");
//		System.out.print(deviceVO.getNumber() + ",");
//		System.out.print(deviceVO.getAdd_status() + ",");
//		System.out.print(deviceVO.getCount_100() + ",");
//		System.out.print(deviceVO.getCount_500() + ",");
//		System.out.println(deviceVO.getCount_1000());
		
		// Query One
//		DeviceVO deviceVO = dao.findByPrimaryId("TY00001");
//		System.out.print(deviceVO.getDid() + ",");
//		System.out.print(deviceVO.getNumber() + ",");
//		System.out.print(deviceVO.getCoin() + ",");
//		System.out.print(deviceVO.getPaper() + ",");
//		System.out.print(deviceVO.getLocation() + ",");
//		System.out.print(deviceVO.getRefund() + ",");
//		System.out.print(deviceVO.getUid() + ",");
//		System.out.print(deviceVO.getStatus() + ",");
//		System.out.print(deviceVO.getAdd_status() + ",");		
//		System.out.print(deviceVO.getCount_100() + ",");
//		System.out.print(deviceVO.getCount_500() + ",");
//		System.out.print(deviceVO.getCount_1000() + ",");
//		System.out.print(deviceVO.getPoint() + ",");
//		System.out.print(deviceVO.getAdd_point() + ",");		
//		System.out.print(deviceVO.getError() + ",");
//		System.out.print(deviceVO.getMachid() + ",");
//		System.out.print(deviceVO.getFreecount() + ",");
//		System.out.print(deviceVO.getFreecountset() + ",");
//		System.out.print(deviceVO.getSid() + ",");
//		System.out.println(deviceVO.getMid());
		
		// Query All
//		List<DeviceVO> list = dao.getAll();
//		for (DeviceVO deviceVO : list) {
//			System.out.print(deviceVO.getDid() + ",");
//			System.out.print(deviceVO.getNumber() + ",");
//			System.out.print(deviceVO.getCoin() + ",");
//			System.out.print(deviceVO.getPaper() + ",");
//			System.out.print(deviceVO.getLocation() + ",");
//			System.out.print(deviceVO.getRefund() + ",");
//			System.out.print(deviceVO.getUid() + ",");
//			System.out.print(deviceVO.getStatus() + ",");
//			System.out.print(deviceVO.getAdd_status() + ",");		
//			System.out.print(deviceVO.getCount_100() + ",");
//			System.out.print(deviceVO.getCount_500() + ",");
//			System.out.print(deviceVO.getCount_1000() + ",");
//			System.out.print(deviceVO.getPoint() + ",");
//			System.out.print(deviceVO.getAdd_point() + ",");		
//			System.out.print(deviceVO.getError() + ",");
//			System.out.print(deviceVO.getMachid() + ",");
//			System.out.print(deviceVO.getFreecount() + ",");
//			System.out.print(deviceVO.getFreecountset() + ",");
//			System.out.print(deviceVO.getSid() + ",");
//			System.out.println(deviceVO.getMid());
//		}
	}

	@Override
	public void insert(DeviceVO deviceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, 	deviceVO.getNumber());
			pstmt.setInt(2, 		deviceVO.getCoin());
			pstmt.setInt(3, 		deviceVO.getPaper());
			pstmt.setString(4, 	deviceVO.getLocation());
			pstmt.setInt(5, 		deviceVO.getRefund());
			pstmt.setInt(6, 		deviceVO.getUid());
			pstmt.setInt(7, 		deviceVO.getStatus());
			pstmt.setInt(8,		deviceVO.getAdd_status());
			pstmt.setInt(9,		deviceVO.getCount_100());
			pstmt.setInt(10,		deviceVO.getCount_500());
			pstmt.setInt(11, 	deviceVO.getCount_1000());
			pstmt.setInt(12,		deviceVO.getPoint());
			pstmt.setInt(13, 	deviceVO.getAdd_point());
			pstmt.setInt(14,		deviceVO.getError());
			pstmt.setInt(15,		deviceVO.getMachid());
			pstmt.setInt(16,		deviceVO.getFreecount());
			pstmt.setInt(17, 	deviceVO.getFreecountset());
			pstmt.setInt(18,		deviceVO.getSid());
			pstmt.setString(19,	deviceVO.getMid());
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
	public void update(DeviceVO deviceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, 	deviceVO.getNumber());
			pstmt.setInt(2, 		deviceVO.getCoin());
			pstmt.setInt(3, 		deviceVO.getPaper());
			pstmt.setString(4, 	deviceVO.getLocation());
			pstmt.setInt(5, 		deviceVO.getRefund());
			pstmt.setInt(6, 		deviceVO.getUid());
			pstmt.setInt(7, 		deviceVO.getStatus());
			pstmt.setInt(8,		deviceVO.getAdd_status());
			pstmt.setInt(9,		deviceVO.getCount_100());
			pstmt.setInt(10,		deviceVO.getCount_500());
			pstmt.setInt(11, 	deviceVO.getCount_1000());
			pstmt.setInt(12,		deviceVO.getPoint());
			pstmt.setInt(13, 	deviceVO.getAdd_point());
			pstmt.setInt(14,		deviceVO.getError());
			pstmt.setInt(15,		deviceVO.getMachid());
			pstmt.setInt(16,		deviceVO.getFreecount());
			pstmt.setInt(17, 	deviceVO.getFreecountset());
			pstmt.setInt(18,		deviceVO.getSid());
			pstmt.setString(19,	deviceVO.getMid());
			pstmt.setInt(20,		deviceVO.getDid());
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
	public void delete(DeviceVO deviceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, deviceVO.getDid());
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
	public DeviceVO findByPrimaryId(String number) {
		DeviceVO deviceVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);			
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deviceVO 也稱為 Domain objects
				deviceVO = new DeviceVO();
				deviceVO.setDid(rs.getInt("DID"));
				deviceVO.setNumber(rs.getString("number"));
				deviceVO.setCoin(rs.getInt("coin"));
				deviceVO.setPaper(rs.getInt("paper"));
				deviceVO.setLocation(rs.getString("location"));
				deviceVO.setRefund(rs.getInt("refund"));
				deviceVO.setUid(rs.getInt("uid"));
				deviceVO.setStatus(rs.getInt("status"));
				deviceVO.setAdd_status(rs.getInt("add_status"));
				deviceVO.setCount_100(rs.getInt("100_count"));
				deviceVO.setCount_500(rs.getInt("500_count"));
				deviceVO.setCount_1000(rs.getInt("1000_count"));
				deviceVO.setPoint(rs.getInt("point"));
				deviceVO.setAdd_point(rs.getInt("add_point"));
				deviceVO.setError(rs.getInt("error"));
				deviceVO.setMachid(rs.getInt("machid"));
				deviceVO.setFreecount(rs.getInt("freecount"));
				deviceVO.setFreecountset(rs.getInt("freecountset"));
				deviceVO.setSid(rs.getInt("sid"));
				deviceVO.setMid(rs.getString("mid"));
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
		return deviceVO;
	}

	@Override
	public List<DeviceVO> getAll() {
		List<DeviceVO> list = new ArrayList<DeviceVO>();
		DeviceVO deviceVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deviceVO 也稱為 Domain objects
				deviceVO = new DeviceVO();
				deviceVO.setDid(rs.getInt("DID"));
				deviceVO.setNumber(rs.getString("number"));
				deviceVO.setCoin(rs.getInt("coin"));
				deviceVO.setPaper(rs.getInt("paper"));
				deviceVO.setLocation(rs.getString("location"));
				deviceVO.setRefund(rs.getInt("refund"));
				deviceVO.setUid(rs.getInt("uid"));
				deviceVO.setStatus(rs.getInt("status"));
				deviceVO.setAdd_status(rs.getInt("add_status"));
				deviceVO.setCount_100(rs.getInt("100_count"));
				deviceVO.setCount_500(rs.getInt("500_count"));
				deviceVO.setCount_1000(rs.getInt("1000_count"));
				deviceVO.setPoint(rs.getInt("point"));
				deviceVO.setAdd_point(rs.getInt("add_point"));
				deviceVO.setError(rs.getInt("error"));
				deviceVO.setMachid(rs.getInt("machid"));
				deviceVO.setFreecount(rs.getInt("freecount"));
				deviceVO.setFreecountset(rs.getInt("freecountset"));
				deviceVO.setSid(rs.getInt("sid"));
				deviceVO.setMid(rs.getString("mid"));

				list.add(deviceVO); // Store the row in the list
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

	@Override
	public boolean getStatus(String number) {
		boolean status = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_DEVICE_STATUS_STMT);			
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int deviceStatus = rs.getInt("status");
				if(deviceStatus == 1) {
					status = true;
				}
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
		return status;
	}

	@Override
	public DeviceVO getCheckMoney(String number) {
		DeviceVO deviceVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CHECK_MONEY_STMT);			
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deviceVO 也稱為 Domain objects
				deviceVO = new DeviceVO();
				deviceVO.setDid(rs.getInt("DID"));
				deviceVO.setNumber(rs.getString("number"));
				deviceVO.setAdd_status(rs.getInt("ADD_STATUS"));
				deviceVO.setCount_100(rs.getInt("100_COUNT"));
				deviceVO.setCount_500(rs.getInt("500_COUNT"));
				deviceVO.setCount_1000(rs.getInt("1000_COUNT"));
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
		return deviceVO;
	}

	@Override
	public DeviceVO getAddStatus(String number) {
		DeviceVO deviceVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_DEVICE_ADD_STATUS_STMT);			
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deviceVO 也稱為 Domain objects
				deviceVO = new DeviceVO();
				deviceVO.setAdd_status(rs.getInt("add_status"));
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
		return deviceVO;
	}

	@Override
	public void updateStatus(String number, int status) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);
			
			pstmt.setInt(1, 		status);
			pstmt.setString(2, 	number);
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
	public void updateAddStatus11(String number, int add_status, int point) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ADD_STATUS_11_STMT);
			pstmt.setInt(1, add_status);
			pstmt.setInt(2, point);			
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 	0);
			pstmt.setInt(6, 	0);
			pstmt.setString(7, number);
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
	public void updateAddStatus13(String number, int add_status, int add_point, int add_money, int add_life_status) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ADD_STATUS_13_STMT);
			pstmt.setInt(1, add_status);
			pstmt.setInt(2, add_point);
			pstmt.setInt(3, add_money);
			pstmt.setInt(4, add_life_status);
			pstmt.setString(5, number);
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
	public int checkAutoIncrement() {
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(AUTO_INCREMENT_STMT);		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("auto_increment");
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
		return count;
	}

	@Override
	public void updateConsumption(String number, int status, int serial, int freecount) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_CONSUMPTION_STMT);
			
			pstmt.setInt(1, 		status);
			pstmt.setInt(2, 		serial);
			pstmt.setInt(3, 		freecount);			
			pstmt.setString(4, 	number);
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
}
