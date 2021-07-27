package com.record.model;

import java.util.Date;

public class TransactionRecordVO {

	private Date recordTimeStamp;
	private int point;
	private String location;
	private String Storename;
	private String type;	//交易類型:消費,加值
	
	public Date getRecordTimeStamp() {
		return recordTimeStamp;
	}
	public void setRecordTimeStamp(Date recordTimeStamp) {
		this.recordTimeStamp = recordTimeStamp;
	}
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStorename() {
		return Storename;
	}
	public void setStorename(String storename) {
		Storename = storename;
	}	
}
