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
	
	@Column(name = "maid")
	private int maid;
	
	@Column(name = "mid")
	private String mid;
	
	@Column(name = "status")
	private int status;
	
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
	public int getMaid() {
		return maid;
	}
	public void setMaid(int maid) {
		this.maid = maid;
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
}
