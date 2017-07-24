package com.newnew.wechatservice.support;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.newnew.wechatservice.support.dto.Constant;
import com.newnew.wechatservice.support.util.PropertiesUtil;

@Component
public class InitDataToRedis {
	@Resource
	private RedisClient redisClient;

	@Resource
	private ZkClient zkclient;

	@PostConstruct
	public void initDataToRedis() {
		String appids = PropertiesUtil.get("/common/common-appid.properties", Constant.APPID);
		String[] ids = appids.split(Constant.COMMA);
		List<String> appidList = Arrays.asList(ids);
		redisClient.set(Constant.ZK_NODE_APPIDS, JSON.toJSONString(appidList), Constant.REDIS_DB_INDEX);
		boolean flag = zkclient.exists(Constant.ZK_TEMP_PATH);
		if (flag) {
			zkclient.writeData(Constant.ZK_TEMP_PATH, JSON.toJSONString(appidList));
		} else {
			zkclient.createPersistent(Constant.ZK_TEMP_PATH, appidList);
		}
	}
}
