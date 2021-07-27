package com.store.model;

public class DeviceMachineContent {

	String name;
	int did;	
	String deviceNumber;
	int machid;
	String machineNumber;

	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public int getMachid() {
		return machid;
	}
	public void setMachid(int machid) {
		this.machid = machid;
	}
	public String getMachineNumber() {
		return machineNumber;
	}
	public void setMachineNumber(String machineNumber) {
		this.machineNumber = machineNumber;
	}
}
