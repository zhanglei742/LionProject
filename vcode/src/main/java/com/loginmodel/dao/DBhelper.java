package com.loginmodel.dao;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.loginmodel.entity.User;

import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jboss.logging.Param;
public class DBhelper {
    static String sql = null;
    static DBUTIL db1 = null;
    static ResultSet ret = null;


    public static boolean Insert(String tableName, String[] colName, String[] values)
    {
        boolean finded=true;

        String temp_col=colName[0];//数组装换成对应格式的字符串?
        for(int i=1;i<colName.length;i++)
        {
            temp_col+=" , "+colName[i];
        }
        String temp_val=values[0];//数组装换成对应格式的字符串?
        for(int i=1;i<values.length;i++)
        {
            temp_val+="','"+values[i];
        }
        
        //编写sql语句
        String sql="insert into "+tableName+" ( "+temp_col+" ) values( '"+temp_val+"')";
        System.out.println(sql);
        //获得连接
        db1 = new DBUTIL(sql);//创建DBHelper对象

        //实例化一个User对象
        try {
            db1.pst.execute();//执行语句，得到结果集

            //ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            finded=false;
            e.printStackTrace();
        }
        return finded;

    }


    public static boolean MatchInfo(String tableName,String[] col,String[] val)
    {
        if(col.length!=val.length)return false;//数量不对应，失败，退出
        for(int i=0;i<col.length;i++)
            if(!SearchMessageExit("user", col[i], val[i]))    //ifNull函数
            {
                return false;//有一项不匹配，则退出，返回失败
            }



        return true;//匹配成功
    }
    //表名冗余
    public static boolean UpdateNameByPhone(String tableName,String number,String name)
    {
        boolean finded=true;
        String sql=" UPDATE "+ tableName+" SET name = \""+name+"\" where number= "+number;
        db1 = new DBUTIL(sql);//创建DBHelper对象

        //实例化一个User对象
        try {
            db1.pst.execute();
            //ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            finded=false;
            e.printStackTrace();
        }
        return finded;

    }


    public static boolean SearchMessageExit(String tableName,String col, String value )
    {
    	
        
        boolean finded=false;

        //编写sql语句
        System.out.println("search"+value);
        String sql="select * from "+tableName+" where "+col+"= \""+value+"\"";
        //获得连接
        db1 = new DBUTIL(sql);//创建DBHelper对象

        //实例化一个User对象
        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            if(ret.next()) {
                finded=true;
            }//显示数据
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finded;
    }
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
    	user.setpwd(pwd);
    	user.setPhonenumber(phone);
    	user.setName(Long.toString(phone));
    	int result= session.insert(statement,user);
    	session.commit();
    	session.close();
    	System.out.println("DBhelperRun :" +result);
    	
    }
}
