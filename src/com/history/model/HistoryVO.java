package com.history.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class HistoryVO  implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "hid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hid;
	
	@Column(name = "ttime")
	private Timestamp ttime;
	
	@Column(name = "event")
	private String event;
	
	@Column(name = "ip")
	private String ip;
	
	@Column(name = "uid")
	private int uid;
	
	@Column(name = "did")
	private int did;
	
	@Column(name = "maid")
	private int maid;
	
	@Column(name = "mid")
	private String mid;
	
	@Column(name = "refundcount")
	private int refundcount;
	
	@Column(name = "freecount")
	private int freecount;
	
	@Column(name = "exchangecount")
	private int exchangecount;
	
	@Column(name = "papercount")
	private int papercount;
	

	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public Timestamp getTtime() {
		return ttime;
	}
	public void setTtime(Timestamp ttime) {
		this.ttime = ttime;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public int getRefundcount() {
		return refundcount;
	}
	public void setRefundcount(int refundcount) {
		this.refundcount = refundcount;
	}
	public int getFreecount() {
		return freecount;
	}
	public void setFreecount(int freecount) {
		this.freecount = freecount;
	}
	public int getExchangecount() {
		return exchangecount;
	}
	public void setExchangecount(int exchangecount) {
		this.exchangecount = exchangecount;
	}
	public int getPapercount() {
		return papercount;
	}
	public void setPapercount(int papercount){
		this.papercount = papercount;
	}

}
