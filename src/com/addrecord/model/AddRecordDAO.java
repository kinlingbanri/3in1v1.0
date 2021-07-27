package com.addrecord.model;

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

import com.mem.model.MemVO;
import com.store.model.StatisticsAllVO;

public class AddRecordDAO implements AddRecordDAO_interface{
	
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
			"SELECT id, storedatetime, coin10, coin50, paper100, paper500, paper1000, "
			+ "point, errorcode, username, deviceid, devicenumber, storeid, storename, cardid FROM addrecord order by id";
	private static final String QUERY_RANGEDATE_STORENAME_STMT = 
			"SELECT id, storedatetime, paper100, paper500, paper1000, point FROM addrecord " + 
			"WHERE STR_TO_DATE(storedatetime, '%Y-%m-%d') >= ? AND " + 
			"		STR_TO_DATE(storedatetime, '%Y-%m-%d') <= ? AND " + 
			"		storename = ?";
	
	private static final String GET_TodayTotal_STMT = 
			"SELECT sum( (coin10*10) + (coin50*50) + (paper100*100) + (paper500*500) + (paper1000*1000)) totalMoney, (point) totalpoint "
			+ "from addrecord where DATE(storedatetime) = curdate();";
	
	private static final String GET_ONE_STMT = 
			"SELECT id, storedatetime, coin10, coin50, paper100, paper500, paper1000, "
					+ "point, errorcode, username, deviceid, devicenumber, storeid, storename, cardid FROM addrecord where id = ?";
	private static final String GET_BYUSERNAME_STMT = 
			"SELECT id, storedatetime, coin10, coin50, paper100, paper500, paper1000, "
					+ "point, errorcode, username, deviceid, devicenumber, storeid, storename, cardid FROM addrecord where username = ?"
					+ "order by storedatetime DESC LIMIT 30";
	private static final String GET_LIST_BY_DID_STMT = 
			"SELECT id, storedatetime, money, point, username, deviceid, devicenumber, storeid, storename " + 
			"FROM addrecord " + 
			"WHERE	STR_TO_DATE(storedatetime, '%Y-%m-%d') >= ? AND " + 
			"			STR_TO_DATE(storedatetime, '%Y-%m-%d') <= ? AND " + 
			"			deviceid = ? " + 
			"order by storedatetime DESC;";
	private static final String GET_AFTER30_STMT = 
			"SELECT ADDRECORD.STOREDATETIME, ADDRECORD.POINT, ADDRECORD.DEVICENUMBER, STORE.NAME, STORE.CITY FROM ADDRECORD " + 
			"					INNER JOIN STORE ON ADDRECORD.STOREID = STORE.SID AND ADDRECORD.USERNAME = ? " + 
			"					order by STOREDATETIME DESC LIMIT 30";
	private static final String INSERT_STMT = 
			"INSERT INTO addrecord (storedatetime, coin10, coin50, paper100, paper500, paper1000, "
			+ "money, point,errorcode, username, deviceid, devicenumber, storeid, storename, cardid, machid)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE addrecord set storedatetime=?, coin10=?, coin50=?, paper100=?, paper500=?,"
			+ " paper1000=?, money=?, point=?, errorcode=?, username=?, deviceid=?,  devicenumber = ?,"
			+ "storeid=?, storename=?, cardid=?, machid=? where id = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM addrecord where id = ?";

	@Override
	public void insert(AddRecordVO addRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setTimestamp(1, addRecordVO.getStoredatetime());
			pstmt.setInt(2, addRecordVO.getCoin10());
			pstmt.setInt(3, addRecordVO.getCoin50());
			pstmt.setInt(4, addRecordVO.getPaper100());
			pstmt.setInt(5, addRecordVO.getPaper500());
			pstmt.setInt(6, addRecordVO.getPaper1000());
			pstmt.setInt(7, addRecordVO.getMoney());
			pstmt.setInt(8, addRecordVO.getPoint());
			pstmt.setInt(9, addRecordVO.getErrorcode());
			pstmt.setString(10, addRecordVO.getUsername());
			pstmt.setInt(11, addRecordVO.getDeviceid());
			pstmt.setString(12, addRecordVO.getDeviceNumber());
			pstmt.setInt(13, addRecordVO.getStoreid());
			pstmt.setString(14, addRecordVO.getStorename());
			pstmt.setString(15, addRecordVO.getCardid());
			pstmt.setInt(16, addRecordVO.getMachid());

			pstmt.executeUpdate();

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
	public void update(AddRecordVO addRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setTimestamp(1, addRecordVO.getStoredatetime());
			pstmt.setInt(2, addRecordVO.getCoin10());
			pstmt.setInt(3, addRecordVO.getCoin50());
			pstmt.setInt(4, addRecordVO.getPaper100());
			pstmt.setInt(5, addRecordVO.getPaper500());
			pstmt.setInt(6, addRecordVO.getPaper1000());
			pstmt.setInt(7, addRecordVO.getMoney());
			pstmt.setInt(8, addRecordVO.getPoint());
			pstmt.setInt(9, addRecordVO.getErrorcode());
			pstmt.setString(10, addRecordVO.getUsername());
			pstmt.setInt(11, addRecordVO.getDeviceid());
			pstmt.setString(12, addRecordVO.getDeviceNumber());
			pstmt.setInt(13, addRecordVO.getStoreid());
			pstmt.setString(14, addRecordVO.getStorename());
			pstmt.setString(15, addRecordVO.getCardid());
			pstmt.setInt(17, addRecordVO.getMachid());
			pstmt.setInt(167, addRecordVO.getId());

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
	public void delete(AddRecordVO addRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, addRecordVO.getId());

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
	public AddRecordVO findByPrimaryId(int id) {
		AddRecordVO addRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				addRecordVO = new AddRecordVO();
				addRecordVO.setId(rs.getInt("id"));
				addRecordVO.setStoredatetime(rs.getTimestamp("storedatetime"));
				addRecordVO.setCoin10(rs.getInt("coin10"));
				addRecordVO.setCoin50(rs.getInt("coin50"));
				addRecordVO.setPaper100(rs.getInt("paper100"));
				addRecordVO.setPaper500(rs.getInt("paper500"));
				addRecordVO.setPaper1000(rs.getInt("paper1000"));
				addRecordVO.setPoint(rs.getInt("point"));
				addRecordVO.setErrorcode(rs.getInt("errorcode"));
				addRecordVO.setUsername(rs.getString("username"));
				addRecordVO.setDeviceid(rs.getInt("deviceid"));
				addRecordVO.setDeviceNumber(rs.getString("deviceNumber"));
				addRecordVO.setStoreid(rs.getInt("storeid"));
				addRecordVO.setStorename(rs.getString("storename"));
				addRecordVO.setCardid(rs.getString("cardid"));
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
		return addRecordVO;
	}

	@Override
	public List<AddRecordVO> getAll() {
		List<AddRecordVO> list = new ArrayList<AddRecordVO>();
		AddRecordVO addRecordVO = null;	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				addRecordVO = new AddRecordVO();
				addRecordVO.setId(rs.getInt("id"));
				addRecordVO.setStoredatetime(rs.getTimestamp("storedatetime"));
				addRecordVO.setCoin10(rs.getInt("coin10"));
				addRecordVO.setCoin50(rs.getInt("coin50"));
				addRecordVO.setPaper100(rs.getInt("paper100"));
				addRecordVO.setPaper500(rs.getInt("paper500"));
				addRecordVO.setPaper1000(rs.getInt("paper1000"));
				addRecordVO.setPoint(rs.getInt("point"));
				addRecordVO.setErrorcode(rs.getInt("errorcode"));
				addRecordVO.setUsername(rs.getString("username"));
				addRecordVO.setDeviceid(rs.getInt("deviceid"));
				addRecordVO.setDeviceNumber(rs.getString("deviceNumber"));
				addRecordVO.setStoreid(rs.getInt("storeid"));
				addRecordVO.setStorename(rs.getString("storename"));
				addRecordVO.setCardid(rs.getString("cardid"));

				list.add(addRecordVO); // Store the row in the list
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
	public List<AddRecordVO> getListByUsername(MemVO memVO) {
		List<AddRecordVO> list = new ArrayList<AddRecordVO>();
		AddRecordVO addRecordVO = null;	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYUSERNAME_STMT);
			pstmt.setString(1, memVO.getUsername());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				addRecordVO = new AddRecordVO();
				addRecordVO.setId(rs.getInt("id"));
				addRecordVO.setStoredatetime(rs.getTimestamp("storedatetime"));
				addRecordVO.setCoin10(rs.getInt("coin10"));
				addRecordVO.setCoin50(rs.getInt("coin50"));
				addRecordVO.setPaper100(rs.getInt("paper100"));
				addRecordVO.setPaper500(rs.getInt("paper500"));
				addRecordVO.setPaper1000(rs.getInt("paper1000"));
				addRecordVO.setPoint(rs.getInt("point"));
				addRecordVO.setErrorcode(rs.getInt("errorcode"));
				addRecordVO.setUsername(rs.getString("username"));
				addRecordVO.setDeviceid(rs.getInt("deviceid"));
				addRecordVO.setDeviceNumber(rs.getString("deviceNumber"));
				addRecordVO.setStoreid(rs.getInt("storeid"));
				addRecordVO.setStorename(rs.getString("storename"));
				addRecordVO.setCardid(rs.getString("cardid"));

				list.add(addRecordVO); // Store the row in the list
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
	public List<AddRecordVO> getAfter30(MemVO memVO) {
		List<AddRecordVO> list = new ArrayList<AddRecordVO>();
		AddRecordVO addRecordVO = null;	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_AFTER30_STMT);
			System.out.println(memVO.getUsername());
			pstmt.setString(1, memVO.getUsername());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				addRecordVO = new AddRecordVO();
				addRecordVO.setStoredatetime(rs.getTimestamp("storedatetime"));
				addRecordVO.setPoint(rs.getInt("point"));
				addRecordVO.setDeviceNumber(rs.getString("deviceNumber"));
				addRecordVO.setStorename(rs.getString("name"));
				addRecordVO.setCity(rs.getString("city"));

				list.add(addRecordVO); // Store the row in the list
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
	public TodayTotalVO getTodayAddValue() {
		TodayTotalVO todayTotalVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TodayTotal_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				todayTotalVO = new TodayTotalVO();
				todayTotalVO.setTotalMoney(rs.getInt("totalmoney"));
				todayTotalVO.setTotalPoint(rs.getInt("totalpoint"));
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
		return todayTotalVO;
	}

	@Override
	public List<AddRecordVO> queryRangeDateAndStoreName(String startDate, String endDate, String storeName) {
		List<AddRecordVO> list = new ArrayList<AddRecordVO>();
		AddRecordVO addRecordVO = null;	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(QUERY_RANGEDATE_STORENAME_STMT);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setString(3, storeName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				addRecordVO = new AddRecordVO();
				addRecordVO.setId(rs.getInt("id"));
				addRecordVO.setStoredatetime(rs.getTimestamp("storedatetime"));
				addRecordVO.setPaper100(rs.getInt("paper100"));
				addRecordVO.setPaper500(rs.getInt("paper500"));
				addRecordVO.setPaper1000(rs.getInt("paper1000"));
				addRecordVO.setPoint(rs.getInt("point"));

				list.add(addRecordVO); // Store the row in the list
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

	@Override
	public List<StatisticsAllVO> getAddStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddRecordVO> getListByDid(String startDate, String endDate, int did) {
		List<AddRecordVO> list = new ArrayList<AddRecordVO>();
		AddRecordVO addRecordVO = null;	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_BY_DID_STMT);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setInt(3, did);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// addRecordVO 也稱為 Domain objects
				addRecordVO = new AddRecordVO();
				addRecordVO.setId(rs.getInt("id"));
				addRecordVO.setStoredatetime(rs.getTimestamp("storedatetime"));
				addRecordVO.setMoney(rs.getInt("money"));
				addRecordVO.setPoint(rs.getInt("point"));
				addRecordVO.setUsername(rs.getString("username"));
				addRecordVO.setDeviceid(rs.getInt("deviceid"));
				addRecordVO.setDeviceNumber(rs.getString("deviceNumber"));
				addRecordVO.setStoreid(rs.getInt("storeid"));
				addRecordVO.setStorename(rs.getString("storename"));


				list.add(addRecordVO); // Store the row in the list
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
