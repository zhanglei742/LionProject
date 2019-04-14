package com.loginmodel.dao;
import com.loginmodel.Pojo.Property;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.Properties;

public class JedisPoolUtils {
	private static Logger logger = Logger.getLogger(JedisPoolUtils.class);
    // Redis 服务器 IP
  //private Properties properties = new Properties();
   // private String address = "";
    private String address = "127.0.0.1";
    //private String address =  properties.getProperty("redis_address");
    // Redis的端口号
    private int port = 6379;
   // private int port;
    // 访问密码
    private String password = "12345";
    //private String password ="";
    //port =properties.getProperty("redis_port");
    // 连接 redis 等待时间
   // private int timeOut = 10000;
    private int timeOut;
    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
    private int maxTotal = 1024;
    
    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8
    private int maxIdle = 200;
    
    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private int maxWait = 10000;
    
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
    private boolean testOnBorrow = true;
    /**
     * 在return给pool时，是否提前进行validate操作；
     */
    private static boolean TEST_ON_RETURN = true;

    /**
     * 如果为true，表示有一个idle object evitor线程对idle object进行扫描，如果validate失败，此object会被从pool中drop掉；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义；
     */
    private static boolean TEST_WHILE_IDLE = true;

    // 连接池
    private JedisPool jedisPool = null;
    
    // 构造函数
    public JedisPoolUtils() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(testOnBorrow);
            config.setTestWhileIdle(TEST_WHILE_IDLE);//Idle时进行连接扫描
            config.setTestOnReturn(TEST_ON_RETURN);//还回线程池时进行扫描
            //表示idle object evitor两次扫描之间要sleep的毫秒数
            config.setTimeBetweenEvictionRunsMillis(30000);
            //表示idle object evitor每次扫描的最多的对象数
            config.setNumTestsPerEvictionRun(50);
            //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
            config.setMinEvictableIdleTimeMillis(60000);
            jedisPool = new JedisPool(config, address, port, timeOut, password);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public  void returnResource( Jedis jedis) {
        //logger.debug("执行释放jedis资源方法returnResource:");
        if (jedis != null && jedisPool != null) {
            jedisPool.returnResource(jedis);
        }
    }
    /**
     * 返还一个jedis
     * @param jedis
     */
    public void returnJedis(Jedis jedis){
        jedis.close();
    }

    /**
     * 关闭连接池
     */
    public  void closePool() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

    // 获取 Jedis 实例
    public   Jedis getJedis() {
        if (jedisPool != null) {
        	
            return jedisPool.getResource();
        }
        return null;    
    } 
}