package com.device.model;

import java.util.List;

public interface DeviceDAO_interface {
	public void insert(DeviceVO deviceVO);
	public void update(DeviceVO deviceVO);
	public void delete(DeviceVO deviceVO);
	public boolean getStatus(String number);
	public DeviceVO getCheckMoney(String number);
	public DeviceVO findByPrimaryId(String number);
	public List<DeviceVO> getAll();
	
	public DeviceVO getAddStatus(String number);
	public void updateStatus(String number, int status);
	public void updateConsumption(String number, int status, int serial, int freecount);
	public void updateAddStatus11(String number, int add_status, int point);
	public void updateAddStatus13(String number, int add_status, int add_point, int add_money, int add_life_status);
	public int checkAutoIncrement();
}
