package com.machine.model;

import java.util.List;

import com.history.model.HistoryVO;

public interface MachineDAO_interface {
	public void insert(MachineVO machineVO);
	public void update(MachineVO machineVO);
	public void delete(MachineVO machineVO);
	public MachineVO findByPrimaryId(int machid);
	public MachineVO findByPrimaryNumber(String number);
	public List<MachineVO> getAll();
	public int checkAutoIncrement();
}
