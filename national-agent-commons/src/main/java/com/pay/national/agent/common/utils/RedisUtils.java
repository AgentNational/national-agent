/**
 * 
 */
package com.pay.national.agent.common.utils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.commons.cache.util.CacheUtils.CacheCallback;
import com.pay.commons.cache.util.CacheUtils.Redis;
import com.pay.commons.utils.io.JdkSerializeUtils;
import com.pay.commons.utils.lang.JsonUtils;
import com.pay.commons.utils.lang.StringUtils;

/**
 * @Description: 缓存操作工具类
 * @see: 需要参考的类
 * @version 2017年1月5日 上午11:44:06
 * @author zhenhui.liu
 */
public class RedisUtils {
	
	private final static int defaultDbIndex = 3;
	
	/**
	 * 编码
	 */
	private static final Charset charset = Charset.forName("UTF-8");
	
	/**
	 * 使用字符串key向默认数据库存入缓存值并设置超时时间（使用字符串为缓存key，使用jdk序列化缓存值）
	 * @param key 字符串缓存key
	 * @param value 缓存值
	 * @param seconds 超时时间，秒
	 */
	public static void setEx(final String key, final String value, final int seconds) {
		if (key == null) throw new IllegalArgumentException("cache key is blank");
		CacheUtils.execute(defaultDbIndex, new CacheCallback<Object>() {
			@Override
			public Object doCallback(Redis redis) {
				redis.setex(key, seconds,  value);
				return null;
			}
		});
	}
	
	/**
	 * @Description  设置string类型的value
	 * @param key
	 * @param value
	 * @see 需要参考的类或方法
	 */
	public static void set(final String key, final String value) {
		if (key == null) throw new IllegalArgumentException("cache key is blank");
		CacheUtils.execute(defaultDbIndex, new CacheCallback<Object>() {
			@Override
			public Object doCallback(Redis redis) {
				redis.set(key,  value);
				return null;
			}
		});
	}
	
	
	/**
	 * @Description 获取string类型的value
	 * @param key 
	 * @param filed
	 * @param value
	 * @see 需要参考的类或方法
	 */
	public static String get(final String key){
		if (key == null) throw new IllegalArgumentException("cache key is blank");
		return CacheUtils.execute(defaultDbIndex,new CacheCallback<String>() {
			@Override
			public String doCallback(Redis redis) {
				return redis.get(key);
			}
		});
	}
	
	/**
	 * @Description 往hash表里添加元素如果不存在则创建该hash表。
	 * @param key 
	 * @param filed
	 * @param value
	 * @see 需要参考的类或方法
	 */
	public static void hset(String key,String filed,Object value){
		hset(StringUtils.serialize(key, charset), StringUtils.serialize(filed, charset), JdkSerializeUtils.serialize(value));
	}
	/**
	 * @Description 往hash表里添加元素如果不存在则创建该hash表。
	 * @param key
	 * @param filed
	 * @param value
	 * @see 需要参考的类或方法
	 */
	public static void hset(final byte[] key, final byte[] filed ,final byte[] value ){
		if (key == null) throw new IllegalArgumentException("cache key is blank");
		CacheUtils.execute(defaultDbIndex,new CacheCallback<Object>() {
			@Override
			public Object doCallback(Redis redis) {
				redis.hset(key,filed, value);
				return null;
			}
		});
	}
	
	/**
	 * @Description 删除指定filed的元素
	 * @param key
	 * @param filed
	 * @return
	 * @see 需要参考的类或方法
	 */
	public static Long hdel(String key,String filed){
		return hdel(StringUtils.serialize(key, charset), StringUtils.serialize(filed, charset));
	}
	
	/**
	 * @Description 删除指定fied的元素
	 * @param serialize
	 * @param serialize2
	 * @return
	 * @see 需要参考的类或方法
	 */
	private static Long hdel(final byte[] key, final byte[] filed) {
		return CacheUtils.execute(defaultDbIndex,new CacheCallback<Long>() {
			@Override
			public Long doCallback(Redis redis) {
				return redis.hdel(key,filed);
			}
		});
		
	}
	
	/**
	 * @Description 获取hash表里的所以记录
	 * @param key 
	 * @param filed
	 * @param value
	 * @see 需要参考的类或方法
	 */
	@SuppressWarnings("unchecked")
	public static<T> Map<String, T> hgetall(String key,final Class<T> clazz){
		Map<byte[], byte[]> map =  hgetall(StringUtils.serialize(key, charset));
		Map<String, T> map1 = new HashMap<String, T>();
		for(Map.Entry<byte[], byte[]> entry : map.entrySet()){
			map1.put(StringUtils.deserialize(entry.getKey(), charset), 
					(T) JdkSerializeUtils.deserialize(entry.getValue()));
		}
		return map1;
	}
	
	/**
	 * @Description 获取hash表里的所以记录
	 * @param key
	 * @see 需要参考的类或方法
	 */
	private static Map<byte[], byte[]> hgetall(final byte[] key) {
		return CacheUtils.execute(defaultDbIndex,new CacheCallback<Map<byte[], byte[]>>() {
			@Override
			public Map<byte[], byte[]> doCallback(Redis redis) {
				return redis.hgetAll(key);
			}
		});
	}
	
	/**
	 * 使用字符串key向默认数据库存入缓存值（使用字符串为缓存key，使用jdk序列化缓存值）
	 * @param key 字符串缓存key
	 * @param value 缓存值
	 * @return
	 */
	public static Long setnx(final String key, final Object value) {
		return setnx(null, key, value, false);
	}

	/**
	 * 使用字符串key向默认数据库存入缓存值（使用字符串为缓存key）
	 * @param key 字符串缓存key
	 * @param value 缓存值
	 * @param useJsonSerialize true时使用json格式进行反序列化缓存值，false时使用jdk进行反序列化
	 * @return
	 */
	public static Long setnx(final String key, final Object value, final boolean useJsonSerialize) {
		return setnx(null, key, value, useJsonSerialize);
	}

	/**
	 * 使用字符串key向dbIndex数据库存入缓存值（使用字符串为缓存key）
	 * @param dbIndex 数据库编号（当dbIndex==null时，使用默认数据库）
	 * @param key 字符串缓存key
	 * @param value 缓存值
	 * @param useJsonSerialize true时使用json格式进行反序列化缓存值，false时使用jdk进行反序列化
	 * @return
	 */
	public static Long setnx(final Integer dbIndex, final String key, final Object value, final boolean useJsonSerialize) {
		if (useJsonSerialize) return setnx(dbIndex, StringUtils.serialize(key, charset), JsonUtils.toJsonBytes(value));
		else return setnx(dbIndex, StringUtils.serialize(key, charset), JdkSerializeUtils.serialize(value));
	}

	/**
	 * 使用key向dbIndex数据库存入缓存值
	 * @param dbIndex 数据库编号（当dbIndex==null时，使用默认数据库）
	 * @param key 字符串缓存key
	 * @param value 缓存值
	 * @return
	 */
	public static Long setnx(final Integer dbIndex, final byte[] key, final byte[] value) {
		if (key == null || key.length == 0) throw new IllegalArgumentException("cache key is blank");
		return  CacheUtils.execute(dbIndex, new CacheCallback<Long>() {
			@Override
			public Long doCallback(Redis redis) {
				return redis.setnx(key, value);
			}
		});
	}
	
	/**
	 * 
	 * 设置KEY缓存数据的失效时间
	 * @param key  字符串缓存key
	 * @param seconds 失效时间（秒）
	 * @see 需要参考的类或方法
	 */
	public static Long expire(final String key,final int seconds)
	{
		return expire(null, key, seconds);
	}
	
	/**
	 * 
	 * 设置KEY缓存数据的失效时间
	 * @param key  字符串缓存key
	 * @param seconds 失效时间（秒）
	 * @see 需要参考的类或方法
	 */
	public static Long expire(final Integer dbIndex,String key,final int seconds)
	{
		return expire(dbIndex, StringUtils.serialize(key, charset),seconds);
	}
	
	/**
	 * 
	 * 设置KEY缓存数据的失效时间
	 * @param dbIndex 数据库编号（当dbIndex==null时，使用默认数据库）
	 * @param key  字符串缓存key
	 * @param seconds 失效时间（秒）
	 * @see 需要参考的类或方法
	 */
	public static Long expire(final Integer dbIndex,final byte[] key,final int seconds)
	{
		if (key == null || key.length == 0) throw new IllegalArgumentException("cache key is blank");
		return  CacheUtils.execute(dbIndex, new CacheCallback<Long>() {
			@Override
			public Long doCallback(Redis redis) {
				return redis.expire(key, seconds);
			}
		});
	}
	
	
	
	/**
	 * 删除缓存中对应key
	 * @param key  字符串缓存key
	 * @see 需要参考的类或方法
	 */
	public static Long del(final String key)
	{
		return del(null, key);
	}
	
	/**
	 * 
	 * 删除缓存中对应key
	 * @param key  字符串缓存key
	 * @param useJsonSerialize true时使用json格式进行反序列化缓存值，false时使用jdk进行反序列化
	 * @see 需要参考的类或方法
	 */
	public static Long del(final Integer dbIndex,String key)
	{
		return del(dbIndex, StringUtils.serialize(key, charset));
	}
	
	/**
	 * 
	 * 设置KEY缓存数据的失效时间
	 * @param dbIndex 数据库编号（当dbIndex==null时，使用默认数据库）
	 * @param key  字符串缓存key
	 * @param seconds 失效时间（秒）
	 * @see 需要参考的类或方法
	 */
	public static Long del(final Integer dbIndex,final byte[] key)
	{
		if (key == null || key.length == 0) throw new IllegalArgumentException("cache key is blank");
		return  CacheUtils.execute(dbIndex, new CacheCallback<Long>() {
			@Override
			public Long doCallback(Redis redis) {
				return redis.del(key);
			}
		});
	}
	

	/**
	 * 数值加1
	 * @param key 字符串缓存key
	 * @return
	 */
	public static Long incr(final String key){
		return incr(defaultDbIndex,key);
	}
	
	public static Long incr(final int dbIndex, String key) {
		return incr(defaultDbIndex,StringUtils.serialize(key, charset));
	}

	public static Long incr(final Integer dbIndex,final byte[] key){
		if (key == null || key.length == 0) throw new IllegalArgumentException("cache key is blank");
		return CacheUtils.execute(dbIndex, new CacheCallback<Long>() {
			@Override
			public Long doCallback(Redis redis) {
				return redis.incr(key);
			}
		});
	}
	

	/**
	 * map存入缓存
	 * @param key 键值
	 * @param map 
	 * @return
	 */
	public static String hmset(final String key,final Map<String,String> map){
		if (StringUtils.isBlank(key)) throw new IllegalArgumentException("cache key is blank");
		return CacheUtils.execute(defaultDbIndex, new CacheCallback<String>() {
			@Override
			public String doCallback(Redis redis) {
				return redis.hmset(key,map);
			}
		});
	}
	
	/**
	 * 获取map键值对个数
	 * @param key 键值
	 * @return
	 */
	public static Long hlen(final String key){
		if (StringUtils.isBlank(key)) throw new IllegalArgumentException("cache key is blank");
		return CacheUtils.execute(defaultDbIndex, new CacheCallback<Long>() {
			@Override
			public Long doCallback(Redis redis) {
				return redis.hlen(key);
			};
		});
	}
	
	/**
	 * 获取map中对应键的值
	 * @param key 缓存键值
	 * @param mapKeys map键值
	 * @return
	 */
	public static List<String> hmget(final String key,final String ... mapKeys){
		if (StringUtils.isBlank(key)) throw new IllegalArgumentException("cache key is blank");
		return CacheUtils.execute(defaultDbIndex, new CacheCallback<List<String>>() {
			@Override
			public List<String> doCallback(Redis redis) {
				return redis.hmget(key, mapKeys);
			};
		});
	}
	
	/**
	 * 获取map中所有的键
	 * @param key
	 * @return
	 */
	public static Set<String> hkeys(final String key){
		if (StringUtils.isBlank(key)) throw new IllegalArgumentException("cache key is blank");
		return CacheUtils.execute(defaultDbIndex, new CacheCallback<Set<String>>() {
			@Override
			public Set<String> doCallback(Redis redis) {
				return redis.hkeys(key);
			};
		});
	}
	
	

}
