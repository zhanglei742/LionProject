package com.loginmodel.dao;
import com.loginmodel.dao.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 操作redis中的hash
 *
 * @author LZJ
 * @create 2018-08-29 13:20
 **/
public class HashRedisOperator {
 
 
    /**
     * 添加一条记录 如果map_key存在 则更新value
     * hset 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果字段已经存在于哈希表中，旧值将被覆盖
     *
     * @param pool
     * @param key
     * @param field
     * @param value
     * @return
     */
    public  long set(JedisPoolUtils pool, String key, String field, String value) {
 
		Jedis jedis = pool.getJedis();
        long returnStatus = jedis.hset(key, field, value);
        pool.returnJedis(jedis);
        return returnStatus;
    }
 
    /**
     * 批量添加记录
     * hmset 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * 此命令会覆盖哈希表中已存在的域。
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     *
     * @param pool
     * @param key
     * @param map
     * @return
     */
   /* public static String setAll(JedisPoolUtils pool, String key, Map<String, String> map) {
        Jedis jedis = pool.borrowJedis();
        String result = jedis.hmset(key, map);
        pool.returnJedis(jedis);
        return result;
    }
 */
    /**
     * 删除hash中 field对应的值
     * hdel 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略
     *
     * @param pool
     * @param key
     * @param field
     * @return
     */
    public  long delete(JedisPoolUtils pool, String key, String... field) {
        Jedis jedis = pool.getJedis();
        long returnStatus = jedis.hdel(key, field);
        pool.returnJedis(jedis);
        return returnStatus;
    }
 
    /**
     * 获取hash中 指定的field的值
     * hmget 返回哈希表 key 中，一个或多个给定域的值。
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * 不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
     *
     * @param pool
     * @param key
     * @param field
     * @return
     */
    public  List<String> get(JedisPoolUtils redisPool, String key, String... field) {
    	Jedis jedis = redisPool.getJedis();
        List<String> result = jedis.hmget(key, field);
        redisPool.returnJedis(jedis);
        return result;
    }
 
   /* *//**
     * 获取hash中 所有的field value
     *
     * @param redisPool
     * @param key
     * @return 在返回值里，紧跟每个字段名(field name)之后是字段的值(value)，所以返回值的长度是哈希表大小的两倍。
     *//*
    public static Map<String, String> getAll(MyRedisPool redisPool, String key) {
        Jedis jedis = redisPool.borrowJedis();
        Map<String, String> result = jedis.hgetAll(key);
        redisPool.returnJedis(jedis);
        return result;
    }*/
 
    /**
     * 判断hash中 指定的field是否存在
     *
     * @param redisPool
     * @param key
     * @param field
     * @return 如果哈希不包含字段或key不存在 返回0，如果哈希包含字段 返回1
     */
    public  boolean ifExist(JedisPoolUtils redisPool, String key, String field) {
        Jedis jedis = redisPool.getJedis();
        boolean result = jedis.hexists(key, field);
        redisPool.returnJedis(jedis);
        return result;
    }
 /*
    *//**
     * 获取hash 的size
     * hlen 获取哈希表中字段的数量
     *
     * @param redisPool
     * @param key
     * @return
     *//*
    public static long size(MyRedisPool redisPool, String key) {
        Jedis jedis = redisPool.borrowJedis();
        long size = jedis.hlen(key);
        redisPool.returnJedis(jedis);
        return size;
    }
 */
 
}