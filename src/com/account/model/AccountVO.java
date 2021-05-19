package com.account.model;

import java.security.KeyStore.PrivateKeyEntry;

import javax.lang.model.type.PrimitiveType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AccountVO {
	
	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;
	
	@Column(name = "user")
	private String user;
	
	@Column(name = "password")
	private String password;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
