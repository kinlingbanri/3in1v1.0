package com.machine.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MachineVO {
	
	@Id
	@Column(name = "machid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int machid;

	@Column(name = "type")
	private String type;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "sid")
	private int sid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "serial")
	private int serial;

	public int getMachid() {
		return machid;
	}

	public void setMachid(int machid) {
		this.machid = machid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}
	
}
