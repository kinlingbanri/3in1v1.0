package com.addrecord.model;

import java.util.List;

import com.mem.model.MemVO;

public class AddRecordService {
	private AddRecordDAO_interface dao;
	
	public AddRecordService() {
		dao = new AddRecordDAO();
	}
	
	public void insertRecord(AddRecordVO addRecordVO) {
		dao.insert(addRecordVO);
	}
	
	public void updateRecord(AddRecordVO addRecordVO) {
		dao.update(addRecordVO);
	}
	
	public void deleteRecord(AddRecordVO addRecordVO) {
		dao.delete(addRecordVO);
	}
	
	public AddRecordVO getOneAddRecord(int id) {
		AddRecordVO addRecordVO = dao.findByPrimaryId(id);
		return addRecordVO;
	}
	
	public List<AddRecordVO>getListByUsername(MemVO memVO){
		return dao.getListByUsername(memVO);
	}
	
	public List<AddRecordVO> getAfter30(MemVO memVO){
		return dao.getAfter30(memVO);
	}
	
	public List<AddRecordVO> getListByDid(String startDate, String endDate, int did){
		return dao.getListByDid(startDate, endDate, did);
	}
	
	public TodayTotalVO getTodayTotal(){
		return dao.getTodayAddValue();
	}
	
	public List<AddRecordVO> queryRangeDateStoreName(String startDate, String endDate, String storeName){
		return dao.queryRangeDateAndStoreName(startDate, endDate, storeName);
	}
	
	public List<AddRecordVO>getAll(){
		return dao.getAll();
	}
}
