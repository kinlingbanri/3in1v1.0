package com.saleprice.model;

import java.util.List;

public class SalepriceService {
	private SalePrice_interface dao;
	
	public SalepriceService() {
		dao = new SalePriceDAO();
	}

	public void insertStore(SalePriceVO salePriceVO) {
		dao.insert(salePriceVO);
	}
	
	public void updateStore(SalePriceVO salePriceVO) {
		dao.update(salePriceVO);
	}
	
	public void deleteStore(SalePriceVO salePriceVO) {
		dao.delete(salePriceVO);
	}
	
	public SalePriceVO getOneStore(int id) {
		return dao.findByPrimaryId(id);
	}
	
	public List<SalePriceVO>getAll(){
		return dao.getAll();
	}
}
