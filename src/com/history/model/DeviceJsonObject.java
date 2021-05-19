package com.history.model;

import java.util.List;

public class DeviceJsonObject {
	private int recordsTotal;
	private int recordsFiltered;
	private List<HistoryVO> data;
	
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<HistoryVO> getHistoryVOs() {
		return data;
	}
	public void setHistoryVOs(List<HistoryVO> historyVOs) {
		this.data = historyVOs;
	}

}
