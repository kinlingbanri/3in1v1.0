package com.record.model;

import java.util.List;

public interface TransactionRecordDAO_interface {
	public List<TransactionRecordVO> get30Record(String username);
}
