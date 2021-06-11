package com.saleprice.model;

import java.util.List;

public interface SalePrice_interface {
	public void insert(SalePriceVO salePriceVO);
	public void update(SalePriceVO salePriceVO);
	public void delete(SalePriceVO salePriceVO);
	public SalePriceVO findByPrimaryId(int id);
	public List<SalePriceVO> getAll();
}
