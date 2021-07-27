package com.store.model;

import javax.persistence.Column;

public class StatisticsAllVO {
	
	@Column(name = "sid")
	private int sid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "add_money")
	private int add_money;
	
	@Column(name = "add_point")
	private int add_point;
	
	@Column(name = "consumption_point")
	private int consumption_point;

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getAdd_money() {
		return add_money;
	}

	public void setAdd_money(int add_money) {
		this.add_money = add_money;
	}

	public int getAdd_point() {
		return add_point;
	}

	public void setAdd_point(int add_point) {
		this.add_point = add_point;
	}

	public int getConsumption_point() {
		return consumption_point;
	}

	public void setConsumption_point(int consumption_point) {
		this.consumption_point = consumption_point;
	}
}
