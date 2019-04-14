package com.loginmodel.dao;
//import redistest.*;
import redis.clients.jedis.Jedis;
 
import java.util.Map;
import java.util.Set;
 
/**
 * Created by qx.zhangbj02320 on 2017/11/13.
 */
public class BaseRedisServiceImpl {
    private  String redisHost = "127.0.0.1";
    private  Integer redisPort = 6379;
    private  Jedis jedis = new Jedis(redisHost, redisPort);
 
    public Jedis getValidJedis() {
        try {
            System.out.println(jedis.ping());
        } catch (Exception e) {
            jedis = new Jedis(redisHost, redisPort);
            e.printStackTrace();
        }
        return jedis;
    }
 
 
    /**
     * 将字符串保存到redis中
     *
     * @param key
     * @param value
     * @return 成功 OK
     */
    public String set(String key, String value) {
        getValidJedis();
        return jedis.set(key, value);
    }
 
    /**
     * 获取指定的值
     *
     * @param key redis中值的键
     * @return
     */
    public String get(String key) {
        getValidJedis();
        return jedis.get(key);
    }
 
    /**
     * 追加字符串
     *
     * @param key
     * @param value
     * @return
     */
    public long append(String key, String value) {
        getValidJedis();
        return jedis.append(key, value);
    }
 
    /**
     * 判断指定的键是否存在
     *
     * @param key
     * @return true-存在，false-不存在
     */
    public boolean exists(String key) {
        getValidJedis();
        return jedis.exists(key);
    }
 
    /**
     * 删除指定的键，
     *
     * @param key
     * @return 0表示指定的键不存在， 大于0表示删除一个或多个。
     */
    public long del(String key) {
        getValidJedis();
        return jedis.del(key);
    }
 
    /**
     * 将Map保存到redis中
     *
     * @param key
     * @param value
     * @return
     */
    public String setMap(String key, Map<String, String> value) {
        getValidJedis();
        return jedis.hmset(key, value);
    }
 
    /**
     * 向已有Map中添加键值对
     *
     * @param key   Map的key
     * @param field map集合中的key
     * @param value map集合中的value
     * @return 0-更新   1-创建
     */
    public long setMapValue(String key, String field, String value) {
        getValidJedis();
        for (int i=0;i<10000;i++){
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jedis.hset(key, field, value+i);
        }
        return 0;
//        Jedis jedis = JedisPoolUtils.getJedis();
//        try {
//            return jedis.hset(key, field, value);
//        } finally {
//            JedisPoolUtils.returnResource(jedis);
//        }
    }
 
    /**
     * 获取redis中的Map
     *
     * @param key
     * @return
     */
    public Map<String, String> getMap(String key) {
        getValidJedis();
        return jedis.hgetAll(key);
    }
 
    /**
     * 获取指定Map中的某键对应的值
     *
     * @param key   Map对应的key值
     * @param field Map中键值对中的键
     * @return
     */
    public String getMapValue(String key, String field) {
        getValidJedis();
        String value = jedis.hget(key, field);
        return value;
    }
 
    /**
     * 删除已有Map中的键值对
     *
     * @param key
     * @param fields
     * @return
     */
    public long delMapValues(String key, String... fields) {
        getValidJedis();
        return jedis.hdel(key, fields);
    }
 
    /**
     * 删除Map
     *
     * @param key
     * @return
     */
    public long delMap(String key) {
        getValidJedis();
        return jedis.del(key);
    }
 
    /**
     * 设置键的超时时间
     *
     * @param key     键
     * @param seconds 秒
     * @return
     */
    public long setExpire(String key, int seconds) {
        getValidJedis();
        return jedis.expire(key, seconds);
    }
   
    /**
     * 向集合中添加元素
     *
     * @param key
     * @param members
     * @return
     */
    public long setList(String key, String... members) {
        getValidJedis();
        return jedis.sadd(key, members);
    }
 
    /**
     * 取出集合中所有的元素
     *
     * @param key
     * @return
     */
    public Set<String> getList(String key) {
        getValidJedis();
        return jedis.smembers(key);
    }
 
    /**
     * 移除集合中指定元素
     *
     * @param key
     * @param members
     * @return
     */
    public long delList(String key, String... members) {
        getValidJedis();
        return jedis.srem(key, members);
    }
 
}