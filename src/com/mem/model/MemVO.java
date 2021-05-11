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
	
	@Column(name = "verification")
	private Integer verification;
	
	@Column(name = "verificationcode")
	private Integer verificationcode;
	
	@Column(name = "verificationdate")
	private Timestamp verificationdate;
	
	@Column(name = "phone")
	private String phone;
	
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

	public Integer getVerificationcode() {
		return verificationcode;
	}

	public void setVerificationcode(Integer verificationcode) {
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
	
}
