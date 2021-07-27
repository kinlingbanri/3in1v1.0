package com.mem.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mem")
public class MemVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "email")
	private String email;
	
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "point")
	private Integer point;
	
	@Column(name = "black")
	private Integer black;
	
	@Column(name = "authority")
	private Integer authority;
	
	//預設為1,一天驗證最多3次,每次失敗加1
	//同天累積至4時,就不再提供驗證
	//不同天時,先設定為1,並更新為新日期
	//驗證過設定為10
	@Column(name = "verification")
	private Integer verification;
	
	@Column(name = "verificationcode")
	private String verificationcode;
	
	@Column(name = "verificationdate")
	private Timestamp verificationdate;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "now_money")
	private Integer now_money;
	
	@Column(name = "add_money")
	private Integer add_money;
	
	@Column(name = "add_status")
	private Integer add_status;
	
	@Column(name = "add_life_status")
	private Integer add_life_status;
	
	public MemVO() {
		super();
	}

	public MemVO(String email, String username, String password, Integer point) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.point = point;
	}
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getBlack() {
		return black;
	}

	public void setBlack(Integer black) {
		this.black = black;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public Integer getVerification() {
		return verification;
	}

	public void setVerification(Integer verification) {
		this.verification = verification;
	}

	public String getVerificationcode() {
		return verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}

	public Timestamp getVerificationdate() {
		return verificationdate;
	}

	public void setVerificationdate(Timestamp verificationdate) {
		this.verificationdate = verificationdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getNow_money() {
		return now_money;
	}

	public void setNow_money(int now_money) {
		this.now_money = now_money;
	}

	public Integer getAdd_money() {
		return add_money;
	}

	public void setAdd_money(Integer add_money) {
		this.add_money = add_money;
	}

	public Integer getAdd_status() {
		return add_status;
	}

	public void setAdd_status(Integer add_status) {
		this.add_status = add_status;
	}

	public Integer getAdd_life_status() {
		return add_life_status;
	}

	public void setAdd_life_status(Integer add_life_status) {
		this.add_life_status = add_life_status;
	}
	
}
