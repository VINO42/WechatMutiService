package com.xyz.wechatservice.support;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.xyz.wechatservice.support.dto.Constant;
import com.xyz.wechatservice.support.util.PropertiesUtil;

public class InitDataToRedis implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(InitDataToRedis.class);
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("getRedisFromContextBegin");
		ServletContext sc = sce.getServletContext();
		XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils
				.getWebApplicationContext(sc);
		RedisClient redisClient = cxt.getBean(RedisClient.class);
		logger.info("getRedisFromContextEnd");
		String md5Appids = PropertiesUtil.get(Constant.COMMON_APPID_PATH, Constant.REDIS_APPIDS);
		String[] ids = md5Appids.split(Constant.COMMA);
		List<String> appidList = Arrays.asList(ids);
		redisClient.set(Constant.REDIS_APPIDS, JSON.toJSONString(appidList), Constant.REDIS_DB_INDEX);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
