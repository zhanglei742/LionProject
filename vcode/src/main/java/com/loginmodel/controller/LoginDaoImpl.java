package com.loginmodel.controller;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeInfo.None;
import com.loginmodel.entity.User;

class LoginDaoImpl implements LoginDao
{ 	
	public  boolean InsertInfoToDB(String number,String password) 
	{
	if(com.loginmodel.dao.DBhelper.SearchMessageExit("user","number",number))return false;//号码已经存在，插入失�?
	//否则，可以插?
	
	String[] col= {"number","password"};
	String[] info= {number,password};
	
	//TTOO 调用数据库的插入功能，在用户表中插入indo.getNumber(),info.getPassword()�?
	if(!com.loginmodel.dao.DBhelper.Insert("user", col,info))return false;//插入失败
	return true;//否则，插入成功
}
	public boolean MatchInfo(String number,String password) {
		String[] col= {"number","password"};
		String[] info= {number,password};
		if(com.loginmodel.dao.DBhelper.MatchInfo("user",col , info))
		return true;
		else return false;
		
	}
	public  boolean UpdateName(String phone,String name)
	{
		if(com.loginmodel.dao.DBhelper.UpdateNameByPhone("user", phone, name))
			return true;
		else return false;
	}
	
	public boolean PhoneExist(long phone) {
		// TODO Auto-generated method stub
		 Map<String, User> info=new HashMap<String,User>();
		 info=com.loginmodel.dao.DBhelper.getUser(phone);
		 if(info.get("user")==null)
		{
			return false;
		}
		else return true;
	}
	public boolean AddUser(long phone,String pwd)
	{
		if(!PhoneExist(phone))
		{com.loginmodel.dao.DBhelper.addUser_phone_pwd(phone,pwd);
		return true;}
		else return false;
		
	}
	
};