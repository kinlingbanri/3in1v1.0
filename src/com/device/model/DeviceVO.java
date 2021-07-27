package com.device.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device")
public class DeviceVO  implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "did")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int did;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "coin")
	private int coin;
	
	@Column(name = "paper")
	private int paper;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "refund")
	private int refund;
	
	@Column(name = "uid")
	private int uid;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "point")
	private int point;
	
	@Column(name = "add_status")
	private int add_status;

	@Column(name = "add_money")
	private int add_money;
	
	@Column(name = "add_point")
	private int add_point;
	
	@Column(name = "100_count")
	private int count_100;	
	
	@Column(name = "500_count")
	private int count_500;
	
	@Column(name = "1000_count")
	private int count_1000;	
	
	@Column(name = "sid")
	private int sid;
	
	@Column(name = "mid")
	private String mid;
	
	@Column(name = "error")
	private int error;
	
	@Column(name = "machid")
	private int machid;
	
	@Column(name = "freecount")
	private int freecount;
	
	@Column(name = "freecountset")
	private int freecountset;	
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public int getPaper() {
		return paper;
	}
	public void setPaper(int paper) {
		this.paper = paper;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getRefund() {
		return refund;
	}
	public void setRefund(int refund) {
		this.refund = refund;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}	
	public int getAdd_status() {
		return add_status;
	}
	public void setAdd_status(int add_status) {
		this.add_status = add_status;
	}
	public int getCount_100() {
		return count_100;
	}
	public void setCount_100(int count_100) {
		this.count_100 = count_100;
	}
	public int getCount_500() {
		return count_500;
	}
	public void setCount_500(int count_500) {
		this.count_500 = count_500;
	}
	public int getCount_1000() {
		return count_1000;
	}
	public void setCount_1000(int count_1000) {
		this.count_1000 = count_1000;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public int getMachid() {
		return machid;
	}
	public void setMachid(int machid) {
		this.machid = machid;
	}
	public int getFreecount() {
		return freecount;
	}
	public void setFreecount(int freecount) {
		this.freecount = freecount;
	}
	public int getFreecountset() {
		return freecountset;
	}
	public void setFreecountset(int freecountset) {
		this.freecountset = freecountset;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getAdd_point() {
		return add_point;
	}
	public void setAdd_point(int add_point) {
		this.add_point = add_point;
	}
	public int getAdd_money() {
		return add_money;
	}
	public void setAdd_money(int add_money) {
		this.add_money = add_money;
	}
	
}
