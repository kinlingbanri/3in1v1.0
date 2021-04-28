package com.history.model;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HistoryService {
	
	public HistoryDAO_interface dao;
	
	public HistoryService() {
		dao = new HistoryDAO();
	}
	
	public void insertHistory(HistoryVO historyVO) {
		dao.insert(historyVO);
	}
	
	public void updateHistory(HistoryVO historyVO) {
		dao.update(historyVO);
	}
	
	public void deleteHistory(HistoryVO historyVO) {
		dao.delete(historyVO);
	}
	
	public List<HistoryVO> getByMemberId(String mid) {
		return dao.getByMemberId(mid);
	}
	
	public List<HistoryVO> getAll(){
		return dao.getAll();
	}
	
	
}
