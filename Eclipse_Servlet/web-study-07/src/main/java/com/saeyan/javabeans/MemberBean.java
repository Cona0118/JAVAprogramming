package com.saeyan.javabeans;

public class MemberBean {
	// 매개변수가 있는 생성자
	public MemberBean(String name, String userid) {
		super();
		this.name = name;
		this.userid = userid;
	}
	
	private String name;
	private String userid;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	// default 생성자
	public MemberBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// toString() 메소드 오버라이딩
	@Override
	public String toString() {
		return "MemberBean [name=" + name + ", userid=" + userid + "]";
	}
}
