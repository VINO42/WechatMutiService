package com.xyz.wechatservice.support;

import redis.clients.jedis.Jedis;

public class RedisClientImpl implements RedisClient {
	private RedisDataSource redisDataSource;

	public RedisDataSource getRedisDataSource() {
		return redisDataSource;
	}

	public void setRedisDataSource(RedisDataSource redisDataSource) {
		this.redisDataSource = redisDataSource;
	}

	@Override
	public String set(byte[] key, Object object, int dbIndex) {
		return null;
	}

	@Override
	public Object get(byte[] key, int dbIndex) {
		return null;
	}

	@Override
	public String set(String key, String value, int dbIndex) {
		String result = null;

		Jedis jedis = redisDataSource.getSingleRedisClient();
		if (jedis == null) {
			return result;
		}
		jedis.select(dbIndex);
		try {
			jedis.select(dbIndex);
			result = jedis.set(key, value);
		} catch (Exception e) {

		} finally {
			redisDataSource.returnResource(jedis);
		}
		return result;
	}

	@Override
	public String set(String key, String value, int dbIndex, int time) {
		return null;
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(String key, int dbIndex) {
		String result = null;

		Jedis jedis = redisDataSource.getSingleRedisClient();
		if (jedis == null) {
			return result;
		}
		jedis.select(dbIndex);
		try {
			jedis.select(dbIndex);
			result = jedis.get(key);
		} catch (Exception e) {

		} finally {
			redisDataSource.returnResource(jedis);
		}
		return result;
	}

	@Override
	public Boolean exists(String key, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean exists(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long expire(String key, int seconds, int dbindex) {
		Long result = null;

		Jedis jedis = redisDataSource.getSingleRedisClient();
		if (jedis == null) {
			return result;
		}
		jedis.select(dbindex);
		try {
			jedis.select(dbindex);
			result = jedis.expire(key, seconds);
		} catch (Exception e) {

		} finally {
			redisDataSource.returnResource(jedis);
		}
		return result;

	}

	@Override
	public Long ttl(String key, int dbIndex) {
		Long result = null;

		Jedis jedis = redisDataSource.getSingleRedisClient();
		if (jedis == null) {
			return result;
		}
		jedis.select(dbIndex);
		try {
			jedis.select(dbIndex);
			result = jedis.ttl(key);
		} catch (Exception e) {

		} finally {
			redisDataSource.returnResource(jedis);
		}
		return result;
	}

}
