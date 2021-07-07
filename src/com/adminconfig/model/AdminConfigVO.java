package com.adminconfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "adminconfig")
public class AdminConfigVO {
	
	@Column(name = "ip")
	private String ip;
	
	@Column(name = "comPort")
	private String comPort;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getComPort() {
		return comPort;
	}

	public void setComPort(String comPort) {
		this.comPort = comPort;
	}
}
