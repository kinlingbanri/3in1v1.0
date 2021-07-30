package com.device.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device")
public class DeviceVO  implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "did")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int did;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "coin")
	private int coin;
	
	@Column(name = "paper")
	private int paper;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "refund")
	private int refund;
	
	@Column(name = "uid")
	private int uid;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "point")
	private int point;
	
	@Column(name = "add_status")
	private int add_status;

	@Column(name = "add_money")
	private int add_money;
	
	@Column(name = "add_point")
	private int add_point;
	
	@Column(name = "100_count")
	private int count_100;	
	
	@Column(name = "500_count")
	private int count_500;
	
	@Column(name = "1000_count")
	private int count_1000;	
	
	@Column(name = "sid")
	private int sid;
	
	@Column(name = "mid")
	private String mid;
	
	@Column(name = "error")
	private int error;
	
	@Column(name = "machid")
	private int machid;
	
	@Column(name = "freecount")
	private int freecount;
	
	@Column(name = "freecountset")
	private int freecountset;
	
	@Column(name = "mach_00")
	private int mach_00;
	@Column(name = "mach_01")
	private int mach_01;
	@Column(name = "mach_02")
	private int mach_02;
	@Column(name = "mach_03")
	private int mach_03;
	@Column(name = "mach_04")
	private int mach_04;
	@Column(name = "mach_05")
	private int mach_05;
	@Column(name = "mach_06")
	private int mach_06;
	@Column(name = "mach_07")
	private int mach_07;
	@Column(name = "mach_08")
	private int mach_08;
	@Column(name = "mach_09")
	private int mach_09;
	@Column(name = "mach_00")
	private int mach_10;
	@Column(name = "mach_11")
	private int mach_11;
	@Column(name = "mach_12")
	private int mach_12;
	@Column(name = "mach_13")
	private int mach_13;
	@Column(name = "mach_14")
	private int mach_14;
	@Column(name = "mach_15")
	private int mach_15;
	@Column(name = "mach_16")
	private int mach_16;
	@Column(name = "mach_17")
	private int mach_17;
	@Column(name = "mach_18")
	private int mach_18;
	@Column(name = "mach_19")
	private int mach_19;
	@Column(name = "mach_20")
	private int mach_20;
	@Column(name = "mach_21")
	private int mach_21;
	@Column(name = "mach_22")
	private int mach_22;
	@Column(name = "mach_23")
	private int mach_23;
	@Column(name = "mach_24")
	private int mach_24;
	@Column(name = "mach_25")
	private int mach_25;
	@Column(name = "mach_26")
	private int mach_26;
	@Column(name = "mach_27")
	private int mach_27;
	@Column(name = "mach_28")
	private int mach_28;
	@Column(name = "mach_29")
	private int mach_29;

	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public int getPaper() {
		return paper;
	}
	public void setPaper(int paper) {
		this.paper = paper;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getRefund() {
		return refund;
	}
	public void setRefund(int refund) {
		this.refund = refund;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}	
	public int getAdd_status() {
		return add_status;
	}
	public void setAdd_status(int add_status) {
		this.add_status = add_status;
	}
	public int getCount_100() {
		return count_100;
	}
	public void setCount_100(int count_100) {
		this.count_100 = count_100;
	}
	public int getCount_500() {
		return count_500;
	}
	public void setCount_500(int count_500) {
		this.count_500 = count_500;
	}
	public int getCount_1000() {
		return count_1000;
	}
	public void setCount_1000(int count_1000) {
		this.count_1000 = count_1000;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public int getMachid() {
		return machid;
	}
	public void setMachid(int machid) {
		this.machid = machid;
	}
	public int getFreecount() {
		return freecount;
	}
	public void setFreecount(int freecount) {
		this.freecount = freecount;
	}
	public int getFreecountset() {
		return freecountset;
	}
	public void setFreecountset(int freecountset) {
		this.freecountset = freecountset;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getAdd_point() {
		return add_point;
	}
	public void setAdd_point(int add_point) {
		this.add_point = add_point;
	}
	public int getAdd_money() {
		return add_money;
	}
	public void setAdd_money(int add_money) {
		this.add_money = add_money;
	}
	public int getMach_00() {
		return mach_00;
	}
	public void setMach_00(int mach_00) {
		this.mach_00 = mach_00;
	}
	public int getMach_01() {
		return mach_01;
	}
	public void setMach_01(int mach_01) {
		this.mach_01 = mach_01;
	}
	public int getMach_02() {
		return mach_02;
	}
	public void setMach_02(int mach_02) {
		this.mach_02 = mach_02;
	}
	public int getMach_03() {
		return mach_03;
	}
	public void setMach_03(int mach_03) {
		this.mach_03 = mach_03;
	}
	public int getMach_04() {
		return mach_04;
	}
	public void setMach_04(int mach_04) {
		this.mach_04 = mach_04;
	}
	public int getMach_05() {
		return mach_05;
	}
	public void setMach_05(int mach_05) {
		this.mach_05 = mach_05;
	}
	public int getMach_06() {
		return mach_06;
	}
	public void setMach_06(int mach_06) {
		this.mach_06 = mach_06;
	}
	public int getMach_07() {
		return mach_07;
	}
	public void setMach_07(int mach_07) {
		this.mach_07 = mach_07;
	}
	public int getMach_08() {
		return mach_08;
	}
	public void setMach_08(int mach_08) {
		this.mach_08 = mach_08;
	}
	public int getMach_09() {
		return mach_09;
	}
	public void setMach_09(int mach_09) {
		this.mach_09 = mach_09;
	}
	public int getMach_10() {
		return mach_10;
	}
	public void setMach_10(int mach_10) {
		this.mach_10 = mach_10;
	}
	public int getMach_11() {
		return mach_11;
	}
	public void setMach_11(int mach_11) {
		this.mach_11 = mach_11;
	}
	public int getMach_12() {
		return mach_12;
	}
	public void setMach_12(int mach_12) {
		this.mach_12 = mach_12;
	}
	public int getMach_13() {
		return mach_13;
	}
	public void setMach_13(int mach_13) {
		this.mach_13 = mach_13;
	}
	public int getMach_14() {
		return mach_14;
	}
	public void setMach_14(int mach_14) {
		this.mach_14 = mach_14;
	}
	public int getMach_15() {
		return mach_15;
	}
	public void setMach_15(int mach_15) {
		this.mach_15 = mach_15;
	}
	public int getMach_16() {
		return mach_16;
	}
	public void setMach_16(int mach_16) {
		this.mach_16 = mach_16;
	}
	public int getMach_17() {
		return mach_17;
	}
	public void setMach_17(int mach_17) {
		this.mach_17 = mach_17;
	}
	public int getMach_18() {
		return mach_18;
	}
	public void setMach_18(int mach_18) {
		this.mach_18 = mach_18;
	}
	public int getMach_19() {
		return mach_19;
	}
	public void setMach_19(int mach_19) {
		this.mach_19 = mach_19;
	}
	public int getMach_20() {
		return mach_20;
	}
	public void setMach_20(int mach_20) {
		this.mach_20 = mach_20;
	}
	public int getMach_21() {
		return mach_21;
	}
	public void setMach_21(int mach_21) {
		this.mach_21 = mach_21;
	}
	public int getMach_22() {
		return mach_22;
	}
	public void setMach_22(int mach_22) {
		this.mach_22 = mach_22;
	}
	public int getMach_23() {
		return mach_23;
	}
	public void setMach_23(int mach_23) {
		this.mach_23 = mach_23;
	}
	public int getMach_24() {
		return mach_24;
	}
	public void setMach_24(int mach_24) {
		this.mach_24 = mach_24;
	}
	public int getMach_25() {
		return mach_25;
	}
	public void setMach_25(int mach_25) {
		this.mach_25 = mach_25;
	}
	public int getMach_26() {
		return mach_26;
	}
	public void setMach_26(int mach_26) {
		this.mach_26 = mach_26;
	}
	public int getMach_27() {
		return mach_27;
	}
	public void setMach_27(int mach_27) {
		this.mach_27 = mach_27;
	}
	public int getMach_28() {
		return mach_28;
	}
	public void setMach_28(int mach_28) {
		this.mach_28 = mach_28;
	}
	public int getMach_29() {
		return mach_29;
	}
	public void setMach_29(int mach_29) {
		this.mach_29 = mach_29;
	}
	
}
