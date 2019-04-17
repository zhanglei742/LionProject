package com.loginmodel.controller;

import java.util.HashMap;
import java.util.Map;

import com.loginmodel.entity.User;

class LoginDaoImpl implements LoginDao
{ 	
	
	
	
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
		if(PhoneExist(phone))return false;
		
		com.loginmodel.dao.DBhelper.addUser_phone_pwd(phone,pwd);
		return true;
		
	}
	@Override
	public boolean UpdateUserInfo(long phone, User user) {
		if(!PhoneExist(phone))return false;
		// TODO Auto-generated method stub
		com.loginmodel.dao.DBhelper.UpdateUserInfo(user);
		
		return true;
	}
	
};