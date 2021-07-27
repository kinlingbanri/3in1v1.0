package com.machine.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation;

import com.store.model.StoreVO;
import com.sun.mail.handlers.message_rfc822;

public class MachineJDBCDAO implements MachineDAO_interface{
	MachineDAO_interface dao;
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://211.21.93.170:3306/rm_58?useUnicode=true&characterEncoding=utf-8";
	String userid = "van";
	String passwd = "34182958";
	
	private static final String GET_ALL_STMT = 
			"SELECT machid, type, number, sid, name, serial FROM machine";
	private static final String GET_ALL_BY_SID_STMT = 
			"SELECT machid, type, number, sid, name, serial, single_point, multi_point, point FROM machine WHERE sid = ?";
	private static final String GET_ALL_BY_DID_STMT = 
			"SELECT machid, type, number, sid, name, serial, single_point, multi_point, point, did FROM machine WHERE did = ?";
	private static final String GET_ONE_STMT = 
			"SELECT machid, type, number, sid, name, serial FROM machine where machid = ?";
	private static final String GET_ONE_NUMBER_STMT = 
			"SELECT machid, type, number, sid, name, serial FROM machine where number = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO machine (type, number, sid, name, serial) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE machine set type=?, number=?, sid=?, name=?, serial=?, single_point=?, multi_point=?, point=? where machid = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM machine where machid = ?";
	private static final String AUTO_INCREMENT_STMT = 
			"SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES "
			+ "WHERE TABLE_SCHEMA = 'rm_58' AND TABLE_NAME = 'machine'";

	public static void main(String[] args) {
		MachineJDBCDAO dao = new MachineJDBCDAO();
		
		MachineVO machineVO = new MachineVO();
		machineVO.setName("板橋府中店");
		long id = dao.insertGetMachid(machineVO);
		System.out.println("id : " + id);
		
		//Insert
//		String type = "洗衣";
//		String typeStr = "";
//		if(type.equals("洗衣")) {
//			typeStr = "WS";
//		}else if(type.equals("烘衣")) {
//			typeStr = "DR";
//		}
//		MachineVO machineVO = new MachineVO();
//		machineVO.setType(type);
//		int count = dao.checkAutoIncrement();
//		String numberStr =  typeStr + String.format("%05d", count);
//		machineVO.setNumber(numberStr);
//		machineVO.setSid(8);
//		machineVO.setName("國際牌PS-WASH01001");
//		machineVO.setSerial(1);
//		dao.insert(machineVO);
		
		// Update
//		MachineVO machineVO = new MachineVO();
//		machineVO.setMachid(12);
//		machineVO.setType("洗衣");
//		int count = dao.checkAutoIncrement();
//		machineVO.setNumber("WS00012");
//		machineVO.setSid(8);
//		machineVO.setName("國際牌PS-WASH01001");
//		machineVO.setSerial(8);
//		dao.update(machineVO);
		
		//Delete
//		MachineVO machineVO = new MachineVO();
//		machineVO.setMachid(12);
//		dao.delete(machineVO);
		
		// Query one by id
//		MachineVO machine = dao.findByPrimaryId(1);
//		System.out.print(machine.getMachid() + ",");
//		System.out.print(machine.getType() + ",");
//		System.out.print(machine.getNumber() + ",");
//		System.out.print(machine.getSid()+ ",");
//		System.out.print(machine.getName()+ ",");
//		System.out.println(machine.getSerial());
		
		// Query one by number
//		MachineVO machine = dao.findByPrimaryNumber("WS00001");
//		System.out.print(machine.getMachid() + ",");
//		System.out.print(machine.getType() + ",");
//		System.out.print(machine.getNumber() + ",");
//		System.out.print(machine.getSid()+ ",");
//		System.out.print(machine.getName()+ ",");
//		System.out.println(machine.getSerial());

		
		// Query All By Sid
//		List<MachineVO> list = dao.getAllBySid(1);
//		for (MachineVO machine : list) {
//			System.out.print(machine.getMachid() + ",");
//			System.out.print(machine.getType() + ",");
//			System.out.print(machine.getNumber() + ",");
//			System.out.print(machine.getSid()+ ",");
//			System.out.print(machine.getName()+ ",");
//			System.out.print(machine.getSerial()+ ",");
//			System.out.print(machine.getSingle_point() + ",");
//			System.out.print(machine.getMulti_point());
//		System.out.print(machine.getPoint());
//			System.out.println();
//		}
		
		// Query All
//		List<MachineVO> list = dao.getAll();
//		for (MachineVO machine : list) {
//			System.out.print(machine.getMachid() + ",");
//			System.out.print(machine.getType() + ",");
//			System.out.print(machine.getNumber() + ",");
//			System.out.print(machine.getSid()+ ",");
//			System.out.print(machine.getName()+ ",");
//			System.out.println(machine.getSerial());
//		}
	}

	@Override
	public void insert(MachineVO machineVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, machineVO.getType());
			pstmt.setString(2, machineVO.getNumber());
			pstmt.setInt(3, machineVO.getSid());
			pstmt.setString(4, machineVO.getName());
			pstmt.setInt(5, machineVO.getSerial());
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
	public void update(MachineVO machineVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, machineVO.getType());
			pstmt.setString(2, machineVO.getNumber());
			pstmt.setInt(3, machineVO.getSid());
			pstmt.setString(4, machineVO.getName());
			pstmt.setInt(5, machineVO.getSerial());
			pstmt.setInt(6,  machineVO.getSingle_point());
			pstmt.setInt(7, machineVO.getMulti_point());
			pstmt.setInt(8, machineVO.getPoint());
			pstmt.setInt(9, machineVO.getMachid());
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
	public void delete(MachineVO machineVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, machineVO.getMachid());
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
	public MachineVO findByPrimaryId(int machid) {
		MachineVO machineVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, machid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				machineVO = new MachineVO();
				machineVO.setMachid(rs.getInt("machid"));
				machineVO.setType(rs.getString("type"));
				machineVO.setNumber(rs.getString("number"));
				machineVO.setSid(rs.getInt("sid"));
				machineVO.setName(rs.getString("name"));
				machineVO.setSerial(rs.getInt("serial"));
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
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
		return count;
	}

	@Override
	public MachineVO findByPrimaryNumber(String number) {
		MachineVO machineVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_NUMBER_STMT);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				machineVO = new MachineVO();
				machineVO.setMachid(rs.getInt("machid"));
				machineVO.setType(rs.getString("type"));
				machineVO.setNumber(rs.getString("number"));
				machineVO.setSid(rs.getInt("sid"));
				machineVO.setName(rs.getString("name"));
				machineVO.setSerial(rs.getInt("serial"));
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
		return machineVO;
	}

	@Override
	public List<MachineVO> getAllBySid(int sid) {
		List<MachineVO> list = new ArrayList<MachineVO>();
		MachineVO machineVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_SID_STMT);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				machineVO = new MachineVO();
				machineVO.setMachid(rs.getInt("machid"));
				machineVO.setType(rs.getString("type"));
				machineVO.setNumber(rs.getString("number"));
				machineVO.setSid(rs.getInt("sid"));
				machineVO.setName(rs.getString("name"));
				machineVO.setSerial(rs.getInt("serial"));
				machineVO.setSingle_point(rs.getInt("single_point"));
				machineVO.setMulti_point(rs.getInt("multi_point"));
				machineVO.setPoint(rs.getInt("point"));
				list.add(machineVO); // Store the row in the list
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
	public long insertGetMachid(MachineVO machineVO) {
		long id = -1;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, machineVO.getType());
			pstmt.setString(2, machineVO.getNumber());
			pstmt.setInt(3, machineVO.getSid());
			pstmt.setString(4, machineVO.getName());
			pstmt.setInt(5, machineVO.getSerial());
			pstmt.executeUpdate();
			
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			while (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
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
		
		return id;
	}

	@Override
	public List<MachineVO> getAllByDid(int did) {
		List<MachineVO> list = new ArrayList<MachineVO>();
		MachineVO machineVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_DID_STMT);
			pstmt.setInt(1, did);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				machineVO = new MachineVO();
				machineVO.setMachid(rs.getInt("machid"));
				machineVO.setType(rs.getString("type"));
				machineVO.setNumber(rs.getString("number"));
				machineVO.setSid(rs.getInt("sid"));
				machineVO.setName(rs.getString("name"));
				machineVO.setSerial(rs.getInt("serial"));
				machineVO.setSingle_point(rs.getInt("single_point"));
				machineVO.setMulti_point(rs.getInt("multi_point"));
				machineVO.setPoint(rs.getInt("point"));
				machineVO.setDid(rs.getInt("did"));
				list.add(machineVO); // Store the row in the list
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

}
