package com.loginmodel.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.loginmodel.dao.*;
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
}
