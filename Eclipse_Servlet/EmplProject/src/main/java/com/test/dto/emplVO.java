package com.test.dto;

import java.sql.Timestamp;

public class emplVO {
	public String id;
	public String pass;
	public String name;
	public String lev;
	public String bigo;
	public String phone;
	private Timestamp enter;
	public int gender;
	
	public Timestamp getEnter() {
		return enter;
	}
	public void setEnter(Timestamp enter) {
		this.enter = enter;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	public String getBigo() {
		return bigo;
	}
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
}
