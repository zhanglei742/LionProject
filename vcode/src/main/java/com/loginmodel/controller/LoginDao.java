package com.loginmodel.controller;

public interface LoginDao {
	//public  void  InputInfo(String number,String password);
	public boolean InsertInfoToDB(String number,String password);
	public boolean MatchInfo(String number,String password);
	public boolean UpdateName(String phone,String name);
	public boolean PhoneExist(long number);
	public boolean AddUser(long phone,String pwd);
}
