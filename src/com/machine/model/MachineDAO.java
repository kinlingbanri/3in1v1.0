package com.machine.model;

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

public class MachineDAO implements MachineDAO_interface{
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
			"SELECT machid, type, number, sid, name, serial FROM machine";
	private static final String GET_ONE_STMT = 
			"SELECT machid, type, number, sid, name, serial FROM machine where machid = ?";
	private static final String GET_ONE_NUMBER_STMT = 
			"SELECT machid, type, number, sid, name, serial FROM machine where number = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO machine (type, number, sid, name, serial) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE machine set type=?, number=?, sid=?, name=?, serial=? where machid = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM machine where machid = ?";
	private static final String AUTO_INCREMENT_STMT = 
			"SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES "
			+ "WHERE TABLE_SCHEMA = 'rm_58' AND TABLE_NAME = 'machine'";	

	@Override
	public void insert(MachineVO machineVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, machineVO.getType());
			pstmt.setString(2, machineVO.getNumber());
			pstmt.setInt(3, machineVO.getSid());
			pstmt.setString(4, machineVO.getName());
			pstmt.setInt(5, machineVO.getSerial());
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
	public void update(MachineVO machineVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, machineVO.getType());
			pstmt.setString(2, machineVO.getNumber());
			pstmt.setInt(3, machineVO.getSid());
			pstmt.setString(4, machineVO.getName());
			pstmt.setInt(5, machineVO.getSerial());
			pstmt.setInt(6, machineVO.getMachid());
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
	public void delete(MachineVO machineVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, machineVO.getMachid());
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
	public MachineVO findByPrimaryId(int machid) {
		MachineVO machineVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, machid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// machineVO 也稱為 Domain objects
				machineVO = new MachineVO();
				machineVO.setMachid(rs.getInt("machid"));				
				machineVO.setType(rs.getString("type"));
				machineVO.setNumber(rs.getString("number"));
				machineVO.setSid(rs.getInt("sid"));
				machineVO.setName(rs.getString("name"));
				machineVO.setSerial(rs.getInt("serial"));
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
		return machineVO;
	}

	@Override
	public List<MachineVO> getAll() {
		List<MachineVO> list = new ArrayList<MachineVO>();
		MachineVO machineVO = null;	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// machineVO 也稱為 Domain objects
				machineVO = new MachineVO();
				machineVO.setMachid(rs.getInt("machid"));				
				machineVO.setType(rs.getString("type"));
				machineVO.setNumber(rs.getString("number"));
				machineVO.setSid(rs.getInt("sid"));
				machineVO.setName(rs.getString("name"));
				machineVO.setSerial(rs.getInt("serial"));

				list.add(machineVO); // Store the row in the list
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
	public MachineVO findByPrimaryNumber(String number) {
		MachineVO machineVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_NUMBER_STMT);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// machineVO 也稱為 Domain objects
				machineVO = new MachineVO();
				machineVO.setMachid(rs.getInt("machid"));				
				machineVO.setType(rs.getString("type"));
				machineVO.setNumber(rs.getString("number"));
				machineVO.setSid(rs.getInt("sid"));
				machineVO.setName(rs.getString("name"));
				machineVO.setSerial(rs.getInt("serial"));
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
		return machineVO;
	}

}
