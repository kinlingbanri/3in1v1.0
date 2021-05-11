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
			"SELECT did, number, coin, paper, location, refund, uid, status, error, machid,"
					+ "freecount, freecountset FROM device";
	private static final String GET_ONE_STMT = 
			"SELECT did, number, coin, paper, location, refund, uid, status, error, machid,"
					+ "freecount, freecountset FROM device where number = ?";
	private static final String GET_DEVICE_STATUS_STMT = 
			"SELECT status FROM device where number = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO device (number, coin, paper, location, refund, uid, status, error,"
					+ "machid, freecount, freecountset) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE device set number=?, coin=?, paper=?, location=?,refund=?, uid=?,"
					+ "status=?, error=?, machid=?, freecount=?, freecountset=? where did = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM device where did = ?";

	public static void main(String[] args) {
		DeviceDAO_interface dao = new DeviceJDBCDAO();
		
//		//Add
//		DeviceVO deviceVO = new DeviceVO();
//		deviceVO.setNumber("TY00031");
//		deviceVO.setCoin(0);
//		deviceVO.setPaper(0);
//		deviceVO.setLocation("RD-31");
//		deviceVO.setRefund(0);
//		deviceVO.setDid(1);
//		deviceVO.setStatus(1);
//		deviceVO.setError(0);
//		deviceVO.setMachid(0);
//		deviceVO.setFreecount(0);
//		deviceVO.setFreecountset(1);
//		dao.insert(deviceVO);
		
//		//Update
//		DeviceVO deviceVO = new DeviceVO();
//		deviceVO.setNumber("TY00031");
//		deviceVO.setCoin(1);
//		deviceVO.setPaper(1);
//		deviceVO.setLocation("RD-31");
//		deviceVO.setRefund(0);
//		deviceVO.setDid(1);
//		deviceVO.setStatus(1);
//		deviceVO.setError(0);
//		deviceVO.setMachid(0);
//		deviceVO.setFreecount(0);
//		deviceVO.setFreecountset(1);
//		deviceVO.setDid(31);
//		dao.update(deviceVO);
		
//		// Delete
//		DeviceVO deviceVO = new DeviceVO();
//		deviceVO.setDid(31);
//		dao.delete(deviceVO);
		
		//Query Device Status
		boolean status = dao.getStatus("TY00001");
		System.out.print("Device Status : " + status);
		
		
//		// Query One
//		DeviceVO deviceVO = dao.findByPrimaryId("TY00030");
//		System.out.print(deviceVO.getDid() + ",");
//		System.out.print(deviceVO.getNumber() + ",");
//		System.out.print(deviceVO.getCoin() + ",");
//		System.out.print(deviceVO.getPaper() + ",");
//		System.out.print(deviceVO.getLocation() + ",");
//		System.out.print(deviceVO.getRefund() + ",");
//		System.out.print(deviceVO.getUid() + ",");
//		System.out.print(deviceVO.getStatus() + ",");
//		System.out.print(deviceVO.getError() + ",");
//		System.out.print(deviceVO.getMachid() + ",");
//		System.out.print(deviceVO.getFreecount() + ",");
//		System.out.println(deviceVO.getFreecountset());
		
//		// Query All
//		List<DeviceVO> list = dao.getAll();
//		for (DeviceVO device : list) {
//			System.out.print(device.getDid() + ",");
//			System.out.print(device.getNumber() + ",");
//			System.out.print(device.getCoin() + ",");
//			System.out.print(device.getPaper() + ",");
//			System.out.print(device.getLocation() + ",");
//			System.out.print(device.getRefund() + ",");
//			System.out.print(device.getUid() + ",");
//			System.out.print(device.getStatus() + ",");
//			System.out.print(device.getError() + ",");
//			System.out.print(device.getMachid() + ",");
//			System.out.print(device.getFreecount() + ",");
//			System.out.println(device.getFreecountset());
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
			pstmt.setInt(8,		deviceVO.getError());
			pstmt.setInt(9,		deviceVO.getMachid());
			pstmt.setInt(10,		deviceVO.getFreecount());
			pstmt.setInt(11, 	deviceVO.getFreecountset());
			pstmt.setInt(12,		deviceVO.getDid());
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
			pstmt.setInt(8,			deviceVO.getError());
			pstmt.setInt(9,			deviceVO.getMachid());
			pstmt.setInt(10,		deviceVO.getFreecount());
			pstmt.setInt(11,		deviceVO.getFreecountset());
			pstmt.setInt(12,		deviceVO.getDid());
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
				deviceVO.setError(rs.getInt("error"));
				deviceVO.setMachid(rs.getInt("machid"));
				deviceVO.setFreecount(rs.getInt("freecount"));
				deviceVO.setFreecountset(rs.getInt("freecountset"));
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
				deviceVO.setError(rs.getInt("error"));
				deviceVO.setMachid(rs.getInt("machid"));
				deviceVO.setFreecount(rs.getInt("freecount"));
				deviceVO.setFreecountset(rs.getInt("freecountset"));

				list.add(deviceVO); // Store the row in the list
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
		return status;
	}

}
