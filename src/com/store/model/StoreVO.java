package com.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class StoreVO {
	
	@Id
	@Column(name = "sid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "pause")
	private int pause;
	
	@Column(name = "single_count")
	private int single_count;
	
	@Column(name = "multi_count")
	private int multi_count;
	
	@Column(name = "discount_1_money")
	private int discount_1_money;
	
	@Column(name = "discount_1_point")
	private int discount_1_point;
	
	@Column(name = "discount_2_money")
	private int discount_2_money;
	
	@Column(name = "discount_2_point")
	private int discount_2_point;
	
	@Column(name = "discount_3_money")
	private int discount_3_money;
	
	@Column(name = "discount_3_point")
	private int discount_3_point;
	
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPause() {
		return pause;
	}

	public void setPause(int pause) {
		this.pause = pause;
	}

	public int getSingle_count() {
		return single_count;
	}

	public void setSingle_count(int single_count) {
		this.single_count = single_count;
	}

	public int getMulti_count() {
		return multi_count;
	}

	public void setMulti_count(int multi_count) {
		this.multi_count = multi_count;
	}

	public int getDiscount_1_money() {
		return discount_1_money;
	}

	public void setDiscount_1_money(int discount_1_money) {
		this.discount_1_money = discount_1_money;
	}

	public int getDiscount_1_point() {
		return discount_1_point;
	}

	public void setDiscount_1_point(int discount_1_point) {
		this.discount_1_point = discount_1_point;
	}

	public int getDiscount_2_money() {
		return discount_2_money;
	}

	public void setDiscount_2_money(int discount_2_money) {
		this.discount_2_money = discount_2_money;
	}

	public int getDiscount_2_point() {
		return discount_2_point;
	}

	public void setDiscount_2_point(int discount_2_point) {
		this.discount_2_point = discount_2_point;
	}

	public int getDiscount_3_money() {
		return discount_3_money;
	}

	public void setDiscount_3_money(int discount_3_money) {
		this.discount_3_money = discount_3_money;
	}

	public int getDiscount_3_point() {
		return discount_3_point;
	}

	public void setDiscount_3_point(int discount_3_point) {
		this.discount_3_point = discount_3_point;
	}
	
}
