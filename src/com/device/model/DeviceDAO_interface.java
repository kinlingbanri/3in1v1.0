package com.device.model;

import java.util.List;

public interface DeviceDAO_interface {
	public void insert(DeviceVO deviceVO);
	public void update(DeviceVO deviceVO);
	public void delete(DeviceVO deviceVO);
	public DeviceVO findByPrimaryId(String number);
	public List<DeviceVO> getAll();
}
