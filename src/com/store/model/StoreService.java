package com.store.model;

import java.util.List;

public class StoreService{
	private StoreDAO_interface dao;
	
	public StoreService() {
		dao = new StoreDAO();
	}
	
	public void insertStore(StoreVO storeVO) {
		dao.insert(storeVO);
	}
	
	public void updateStore(StoreVO storeVO) {
		dao.update(storeVO);
	}
	
	public void deleteStore(StoreVO storeVO) {
		dao.delete(storeVO);
	}
	
	public StoreVO getOneStore(int id) {
		return dao.findByPrimaryId(id);
	}
	
	public List<StoreVO>getAll(){
		return dao.getAll();
	}
}
