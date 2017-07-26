package com.xyz.wechatservice.support;

import redis.clients.jedis.Jedis;

public interface RedisDataSource {

	public abstract Jedis getSingleRedisClient();

	public void returnResource(Jedis jedis);

}
