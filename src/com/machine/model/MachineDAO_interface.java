package com.machine.model;

import java.util.List;

import com.history.model.HistoryVO;

public interface MachineDAO_interface {
	public void insert(MachineVO machineVO);
	public long insertGetMachid(MachineVO machineVO);
	public void update(MachineVO machineVO);	
	public void delete(MachineVO machineVO);
	public MachineVO findByPrimaryId(int machid);
	public MachineVO findByPrimaryNumber(String number);
	public List<MachineVO> getAll();
	public List<MachineVO> getAllBySid(int sid);
	public List<MachineVO> getAllByDid(int did);
	public int checkAutoIncrement();
}
