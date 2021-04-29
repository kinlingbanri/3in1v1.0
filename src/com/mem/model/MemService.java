package com.mem.model;

import java.util.List;

public class MemService {
	private MemDAO_interface dao;
	
	public MemService() {
		dao = new MemDAO();
	}
	
	public MemVO addMem(String username, String email,
			String password, Integer point){
		
		MemVO memVO = new MemVO();

		memVO.setUsername(username);
		memVO.setEmail(email);
		memVO.setPassword(password);
		memVO.setPoint(point);
		dao.insert(memVO);

		return memVO;
	};
	
	public void updateMem(MemVO memVO){
		dao.update(memVO);
	}
	
	public void deleteMem(String username) {
		dao.delete(username);
	}
	
	public MemVO getOneMem(String username) {
		return dao.findByPrimaryKey(username);
	}
	
	public List<MemVO> getMemEmail(String email){
		return dao.findByEmail(email);
	}
	
	public List<MemVO>getAll(){
		return dao.getAll();
	}
}
