package com.history.model;

import java.util.List;

public interface HistoryDAO_interface {
	public void insert(HistoryVO historyVO);
	public void update(HistoryVO historyVO);
	public void delete(HistoryVO historyVO);
	public HistoryVO findByPrimaryId(int hid);
	public List<HistoryVO> getByMemberId(String mid);
	public List<HistoryVO> get30(String mid);
	public List<HistoryVO> getAll();
	public int getCount(int did);
	public List<HistoryVO> getAllByDid(int did, int index, int total); 
}
