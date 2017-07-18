package com.newnew.wechatservice.support;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("redisClient")
public class RedisClientImpl implements RedisClient {
	@Resource
	private RedisDataSource redisDataSource;

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public String set(byte[] key, Object object, int dbIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(byte[] key, int dbIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String set(String key, String value, int dbIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String set(String key, String value, int dbIndex, int time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(String key, int dbIndex) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
