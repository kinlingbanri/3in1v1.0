package com.adminconfig.model;

public class AdminConfigService {
	AdminConfigDAO_interface dao;
	
	public AdminConfigService() {
		dao = new AdminConfigDAO();
	}
	
	public AdminConfigVO getAdminConfig() {
		return dao.getAdminConfig();
	}
}
