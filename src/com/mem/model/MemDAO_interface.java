package com.mem.model;

import java.util.List;

public interface MemDAO_interface {
	public void insert(MemVO memVO);
	public void update(MemVO memVO);
	public void delete(String username);
	public MemVO findByPrimaryKey(String username);
	public List<MemVO> findByEmail(String email);
	public List<MemVO> findByPhone(String phone);
	public List<MemVO> getAll();
}
