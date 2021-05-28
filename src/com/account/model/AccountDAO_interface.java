package com.account.model;

import java.util.List;

public interface AccountDAO_interface {
	public void insert(AccountVO accountVO);
	public void update(AccountVO accountVO);
	public void delete(AccountVO accountVO);
	public AccountVO findByPrimaryId(int uid);
	public List<AccountVO> getAll();
}
