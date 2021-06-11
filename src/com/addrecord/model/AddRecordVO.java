package com.addrecord.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addrecord")
public class AddRecordVO implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;	

	public AddRecordVO() {
		super();
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "storedatetime")
	private Timestamp storedatetime;
	
	@Column(name = "coin10")
	private int coin10;

	@Column(name = "coin50")
	private int coin50;
	
	@Column(name = "paper100")
	private int paper100;
	
	@Column(name = "paper500")
	private int paper500;
	
	@Column(name = "paper1000")
	private int paper1000;
	
	@Column(name = "point")
	private int point;
	
	@Column(name = "errorcode")
	private int errorcode;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "deviceid")
	private int deviceid;
	
	@Column(name = "devicenumber")
	private String deviceNumber;

	@Column(name = "storeid")
	private int storeid;
	
	@Column(name = "cardid")
	private String cardid;
	
	@Column(name = "storename")
	private String storename;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "location")
	private String location;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getStoredatetime() {
		return storedatetime;
	}

	public void setStoredatetime(Timestamp storedatetime) {
		this.storedatetime = storedatetime;
	}

	public int getCoin10() {
		return coin10;
	}

	public void setCoin10(int coin10) {
		this.coin10 = coin10;
	}

	public int getCoin50() {
		return coin50;
	}

	public void setCoin50(int coin50) {
		this.coin50 = coin50;
	}

	public int getPaper100() {
		return paper100;
	}

	public void setPaper100(int paper100) {
		this.paper100 = paper100;
	}

	public int getPaper500() {
		return paper500;
	}

	public void setPaper500(int paper500) {
		this.paper500 = paper500;
	}

	public int getPaper1000() {
		return paper1000;
	}

	public void setPaper1000(int paper1000) {
		this.paper1000 = paper1000;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public int getStoreid() {
		return storeid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
