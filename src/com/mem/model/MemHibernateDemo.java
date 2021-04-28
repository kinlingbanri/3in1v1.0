package com.mem.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import utils.HibernateUtil;

public class MemHibernateDemo {

	public static void main(String[] args) {
		
		MemVO memVO = new MemVO();
		memVO.setUsername("Van001");
		memVO.setEmail("hello@hotmail.com");
		memVO.setPassword("123");
		memVO.setPoint(150);
		
		//HibernateUtil.saveMemVO(memVO);
		
		//HibernateUtil.updateMemVO(memVO);
		
		//HibernateUtil.deleteMemVO(memVO);
		
//		List<MemVO> list = HibernateUtil.getOneByUsername(memVO);
//		list.forEach(mem -> {
//			System.out.println(mem.getUsername());
//			System.out.println(mem.getEmail());
//			System.out.println(mem.getPassword());
//			System.out.println(mem.getPoint());
//		});
		
		List<MemVO> list = HibernateUtil.getAll();
		list.forEach(mem -> {
			System.out.println(mem.getUsername());
			System.out.println(mem.getEmail());
			System.out.println(mem.getPassword());
			System.out.println(mem.getPoint());
		});
		
		System.out.println("OKK");
	}
}
