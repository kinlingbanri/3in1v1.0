package com.record.model;

import java.util.List;

public class TransactionRecordService {
	public TransactionRecordDAO_interface dao;
	
	public TransactionRecordService() {
		dao = new TransactionRecordDAO();
	}

	public List<TransactionRecordVO> get30Records(String username){
		return dao.get30Record(username);
	}
	
}
