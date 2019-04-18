package com.loginmodel.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.loginmodel.entity.User;

public class DBhelper {
    
    public static Map<String, User>  getUser(long phone)
    {
    	//mybatis的配置文件
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = DBhelper.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        
        /**
         * 映射sql的标识字符串，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        
        String statement = "con.loginmodel.mapping.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement,phone);
        Map<String, User> info=new HashMap<String,User>();
        info.put("user", user);
    	session.close();
        return info;
    }
    public static void addUser_phone_pwd(long phone,String pwd)
    {
    	String resource = "conf.xml";
    	InputStream is = DBhelper.class.getClassLoader().getResourceAsStream(resource);
    	SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
    	SqlSession session = sessionFactory.openSession();
    	String statement = "con.loginmodel.mapping.userMapper.addUser_phone_pwd";//映射sql的标识字符串
    	User user=new User();
    	user.setPwd(pwd);
    	user.setPhonenumber(phone);
    	user.setName(Long.toString(phone));
    	int result= session.insert(statement,user);
    	session.commit();
    	session.close();
    	System.out.println("DBhelperRun :" +result);
    	
    }
    public static void UpdateUserInfo(User user)
    {
    	String resource = "conf.xml";
    	InputStream is = DBhelper.class.getClassLoader().getResourceAsStream(resource);
    	SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
    	SqlSession session = sessionFactory.openSession();
    	int [] result= {0,0,0};
    	String statement ;
    	System.out.println("DBhelper开始调用");
    	if(user.getName()!=null){//输入名字不为空
    	statement = "con.loginmodel.mapping.userMapper.updateUser_name";//映射sql的标识字符串
    	result[0]=session.update(statement,user);}
    	if(user.getEmail()!=null) {//输入邮箱不为空
    	statement = "con.loginmodel.mapping.userMapper.updateUser_email";//映射sql的标识字符串
    	result[1]=session.update(statement,user);System.out.println("邮箱");}
    	if(user.getPwd()!=null) {//输入密码不为空
    	statement = "con.loginmodel.mapping.userMapper.updateUser_pwd";//映射sql的标识字符串
    	result[2]=session.update(statement,user);}
    	for(int i=0;i<3;i++)
    	{
    		System.out.println(i+" : "+result[i]);
    	}
    	session.commit();
    	session.close();
    	
    	
    	
    }
}
