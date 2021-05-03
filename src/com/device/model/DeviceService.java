package com.device.model;

import java.util.List;

import com.history.model.HistoryVO;

public class DeviceService {
	
	public DeviceDAO_interface dao;
	
	public DeviceService() {
		dao = new DeviceDAO();
	}

	public void insertDevice(DeviceVO deviceVO) {
		dao.insert(deviceVO);
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
	
	public List<DeviceVO> getAll(){
		return dao.getAll();
	}
}
