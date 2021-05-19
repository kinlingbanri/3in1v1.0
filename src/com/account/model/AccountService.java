package com.account.model;

import java.util.List;

public class AccountService {
	private AccountDAO_interface dao;
	
	public AccountService() {
		dao = new AccountDAO();
	}

	public void insertAccount(AccountVO accountVO) {
		dao.insert(accountVO);
	}

	public void updateAccount(AccountVO accountVO) {
		dao.update(accountVO);
	}

	public void deleteAccount(AccountVO accountVO) {
		dao.delete(accountVO);
	}

	public AccountVO findByPrimaryId(int uid) {
		return dao.findByPrimaryId(uid);
	}

	public List<AccountVO> getAll() {
		return dao.getAll();
	}
}
