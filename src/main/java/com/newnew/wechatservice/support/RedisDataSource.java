package com.newnew.wechatservice.support;

import redis.clients.jedis.Jedis;

public interface RedisDataSource {

	public abstract Jedis getSingleRedisClient();

	public void returnResource(Jedis jedis);

	public void returnResource(Jedis jedis, boolean broken);
}
