package com.addrecord.model;

import java.util.List;

import com.mem.model.MemVO;
import com.store.model.StatisticsAllVO;

public interface AddRecordDAO_interface {
	public void insert(AddRecordVO addRecordVO);
	public void update(AddRecordVO addRecordVO);
	public void delete(AddRecordVO addRecordVO);
	public AddRecordVO findByPrimaryId(int id);
	public List<AddRecordVO> getListByUsername(MemVO memVO);
	public List<AddRecordVO> getListByDid(String startDate, String endDate, int did);
	public List<AddRecordVO> getAfter30(MemVO memVO);
	public TodayTotalVO getTodayAddValue();
	public List<AddRecordVO> queryRangeDateAndStoreName(String startDate, String endDate, String storename); 
	public List<AddRecordVO> getAll();
	
	public List<StatisticsAllVO> getAddStatistics();
}
