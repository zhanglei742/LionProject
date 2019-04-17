package com.loginmodel.entity;

public class User {
	//对应数据库的User表，若数据库表格有改动，可以来这里修改
	//这里的属性要求和数据库的表的属性一致（mybatis会自动帮映射），否则需要自己写映射文件，
	private long phonenumber;
	private String pwd;
	private String name;
	private String emali;
	public long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(long phone) {
		this.phonenumber = phone;
	}
	public String getpwd() {
		return pwd;
	}
	public void setpwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmali() {
		return emali;
	}
	public void setEmali(String emali) {
		this.emali = emali;
	}
	
	
}

