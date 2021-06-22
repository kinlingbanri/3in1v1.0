package com.device.model;

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

public class DeviceDAO implements DeviceDAO_interface{

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
			"SELECT did, number, coin, paper, location, refund, uid, status, add_status, error, "
			+ "100_count, 500_count, 1000_count, point, add_point, machid, freecount, freecountset, "
			+ "sid, mid FROM device";
	private static final String GET_ONE_STMT = 
			"SELECT did, number, coin, paper, location, refund, uid, status, add_status, error, "
			+ "100_count, 500_count, 1000_count, point, add_point, machid, freecount, freecountset, "
			+ "sid, mid FROM device where number = ?";
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
	private static final String UPDATE_ADD_STATUS_11_STMT = 
			"UPDATE device set add_status=?, point=? where number = ?";
	private static final String UPDATE_ADD_STATUS_13_STMT = 
			"UPDATE device set add_status=?, add_point=? where number = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM device where did = ?";
	private static final String AUTO_INCREMENT_STMT = "SELECT `AUTO_INCREMENT`" + 
			"FROM  INFORMATION_SCHEMA.TABLES" + 
			"WHERE TABLE_SCHEMA = 'rm_58'" + 
			"AND   TABLE_NAME   = 'device'";
	
	@Override
	public void insert(DeviceVO deviceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
	public void update(DeviceVO deviceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, deviceVO.getDid());
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
	public DeviceVO findByPrimaryId(String number) {
		DeviceVO deviceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int deviceStatus = rs.getInt("status");
				if(deviceStatus == 1) {
					status = true;
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
		return status;
	}

	@Override
	public DeviceVO getCheckMoney(String number) {
		DeviceVO deviceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DEVICE_ADD_STATUS_STMT);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deviceVO 也稱為 Domain objects
				deviceVO = new DeviceVO();
				deviceVO.setAdd_status(rs.getInt("add_status"));
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
		return deviceVO;
	}

	@Override
	public void updateStatus(String number, int status) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);
			
			pstmt.setInt(1, 		status);
			pstmt.setString(2, 	number);
			pstmt.executeUpdate();

			// Handle any driver errors
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ADD_STATUS_11_STMT);
			pstmt.setInt(1, add_status);
			pstmt.setInt(2, point);
			pstmt.setString(3, number);
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void updateAddStatus13(String number, int add_status, int add_point) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ADD_STATUS_13_STMT);
			pstmt.setInt(1, add_status);
			pstmt.setInt(2, add_point);
			pstmt.setString(3, number);
			pstmt.executeUpdate();

			// Handle any driver errors
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(AUTO_INCREMENT_STMT);		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("auto_increment");
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
		return count;
	}

	@Override
	public void updateConsumption(String number, int status, int serial, int freecount) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CONSUMPTION_STMT);
			
			pstmt.setInt(1, 		status);
			pstmt.setInt(2, 		serial);
			pstmt.setInt(3, 		freecount);			
			pstmt.setString(4, 	number);
			pstmt.executeUpdate();

			// Handle any driver errors
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
