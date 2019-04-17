package com.loginmodel.entity;

public class User {
	//对应数据库的User表，若数据库表格有改动，可以来这里修改
	//这里的属性要求和数据库的表的属性一致（mybatis会自动帮映射），否则需要自己写映射文件，
	private long phonenumber;
	@Override
	public String toString() {
		return "User [phonenumber=" + phonenumber + ", pwd=" + pwd + ", name=" + name + ", email=" + myemail + "]";
	}
	private String pwd;
	private String name;
	private String myemail;
	public long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return myemail;
	}
	public void setEmail(String email) {
		this.myemail = email;
	}
	
	
	
	
}

