package com.newnew.wechatservice.support;

import java.net.SocketTimeoutException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

	
@Component("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {
	private static final Logger log = LoggerFactory.getLogger(RedisDataSourceImpl.class);
	@Resource
	private JedisPool jedisPool;

	public Jedis getSingleRedisClient() {
		int count = 0;
		while (true) {

			try {
				Jedis jedis = jedisPool.getResource();
				return jedis;
			} catch (Exception e) {
				if (e instanceof JedisConnectionException || e instanceof SocketTimeoutException) {
					count++;
					log.warn("get Redis TimeoutCount=" + count);
					if (count > 3) {
						break;
					}
				} else {
					log.error("jedisPool is closed");
					break;
				}
			}

		}
		return null;
	}

	public void returnResource(Jedis jedis) {
		jedis.close();
	}
}
