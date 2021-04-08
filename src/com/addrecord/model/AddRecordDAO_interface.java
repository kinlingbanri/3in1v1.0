package com.addrecord.model;

import java.util.List;

import com.mem.model.MemVO;

public interface AddRecordDAO_interface {
	public void insert(AddRecordVO addRecordVO);
	public void update(AddRecordVO addRecordVO);
	public void delete(AddRecordVO addRecordVO);
	public AddRecordVO findByPrimaryId(int id);
	public List<AddRecordVO> getListByUsername(MemVO memVO);
	public List<AddRecordVO> getAfter30(MemVO memVO);
	public List<AddRecordVO> getAll();
}
