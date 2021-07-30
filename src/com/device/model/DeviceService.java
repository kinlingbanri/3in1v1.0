package com.device.model;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;

public class DeviceService {
	
	public DeviceDAO_interface dao;
	
	public DeviceService() {
		dao = new DeviceDAO();
	}

	public void insertDevice(DeviceVO deviceVO) {
		dao.insert(deviceVO);
	}
	
	public long insertGetDid(DeviceVO deviceVO) {
		return dao.insertGetDid(deviceVO);
	}
	
	public void updateDevice(DeviceVO deviceVO) {
		dao.update(deviceVO);
	}
	
	public void deleteDevice(DeviceVO deviceVO) {
		dao.delete(deviceVO);
	}
	
	public boolean getStatus(String number) {
		return dao.getStatus(number);
	}
	
	public DeviceVO getOneDevice(String number) {
		return dao.findByPrimaryId(number);
	}
	
	public DeviceVO getOnByDid(int did) {
		return dao.getOneByDid(did);
	}
	
	public DeviceVO getCheckMoney(String number) {
		return dao.getCheckMoney(number);
	}
	
	public List<DeviceVO> getAll(){
		return dao.getAll();
	}
	
	public List<DeviceVO> getAllBySid(int sid){
		return dao.getAllBySid(sid);
	}
	
	public DeviceVO getAddStatus(String number) {
		return dao.getAddStatus(number);
	}
	
	public void updateStatus(String number, int status) {
		dao.updateStatus(number, status);
	}
	
	public void updateConsumption(String number, int status, int serial, int freecount) {
		dao.updateConsumption(number, status, serial, freecount);
	}
	
	public void updateConsumptionVO(DeviceVO deviceVO) {
		dao.updateConsumptionVO(deviceVO);
	}
	
	public void updateAddStatus11(String number, int add_status, int point) {
		dao.updateAddStatus11(number, add_status, point);
	}
	
	public void updateAddStatus13(String number, int add_status, int add_point, int add_money) {
		dao.updateAddStatus13(number, add_status, add_point, add_money);
	}
}
