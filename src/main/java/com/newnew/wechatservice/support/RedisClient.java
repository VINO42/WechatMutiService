package com.newnew.wechatservice.support;

public interface RedisClient {
	  void disconnect();

	    /**
	     * 
	     * @doc:存对象
	     * @author Andreby
	     * @date 2017年5月16日 下午3:54:35 
	     * @param key 键
	     * @param value 值
	     * @param dbIndex db号码
	     * @return
	     */
	    String set(byte[] key, Object object, int dbIndex);

	    /**
	     * 
	     * @doc:获取存在redis中的Object对象
	     * @author Andreby
	     * @date 2017年5月16日 下午3:54:35 
	     * @param key 键
	     * @param value 值
	     * @param dbIndex db号码
	     * @return
	     */
	    Object get(byte[] key, int dbIndex);
	    /**
	     * 
	     */
	    String set(String key, String value, int dbIndex);

	    /**
	     * 
	     */
	    String set(String key, String value, int dbIndex,int time);
	    /**
	     * 获取单个值
	     * 
	     * @param key
	     * @return
	     */
	    String get(String key);

	    /**
	     * 获取单个值
	     * 
	     * @param key
	     * @return
	     */
	    String get(String key, int dbIndex);
	    /**
	     * 获取单个值
	     * 
	     * @param key
	     * @param key
	     * @return
	     */
	    Boolean exists(String key, int index);

	    Boolean exists(String key);
	    /**
	     * 在某段时间后实现
	     * 
	     * @param key
	     * @param unixTime
	     * @return
	     */
	    Long expire(String key, int seconds,int dbindex);
}
