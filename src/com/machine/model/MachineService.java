package com.machine.model;

import java.util.List;

public class MachineService {
	private MachineDAO_interface dao;
	
	public MachineService() {
		dao = new MachineDAO();
	}

	public void insertMachine(MachineVO machineVO) {
		dao.insert(machineVO);
	}
	
	public void updateMachine(MachineVO machineVO){
		dao.update(machineVO);
	}
	
	public void deleteMachine(MachineVO machineVO) {
		dao.delete(machineVO);
	}
	
	public MachineVO getOneMachine(int machid) {
		return dao.findByPrimaryId(machid);
	}
	
	public MachineVO getOneMachineNumber(String number) {
		return dao.findByPrimaryNumber(number);
	}
	
	public List<MachineVO>getAll(){
		return dao.getAll();
	}
}
