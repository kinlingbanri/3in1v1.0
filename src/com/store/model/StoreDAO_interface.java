package com.store.model;

import java.util.List;

public interface StoreDAO_interface {
	public void insert(StoreVO storeVO);
	public void update(StoreVO storeVO);
	public void delete(StoreVO storeVO);
	public StoreVO findByPrimaryId(int sid);
	public List<StoreVO> getAll();
}
