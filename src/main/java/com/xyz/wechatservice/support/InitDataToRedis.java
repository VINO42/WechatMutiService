package com.xyz.wechatservice.support;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.xyz.wechatservice.support.dto.Constant;
import com.xyz.wechatservice.support.util.PropertiesUtil;

@Component
public class InitDataToRedis {
	@Resource
	private RedisClient redisClient;

	@PostConstruct
	public void init() {
		String appids = PropertiesUtil.get(Constant.COMMON_APPID_PATH, Constant.REDIS_APPIDS);
		String[] ids = appids.split(Constant.COMMA);
		List<String> appidList = Arrays.asList(ids);
		redisClient.set(Constant.REDIS_APPIDS, JSON.toJSONString(appidList), Constant.REDIS_DB_INDEX);
	}
}
