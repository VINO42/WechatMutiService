package com.xyz.wechatservice.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.xyz.wechatservice.support.config.CommonWxConfig;
import com.xyz.wechatservice.support.config.WxConfig;
import com.xyz.wechatservice.support.dto.Constant;
import com.xyz.wechatservice.support.service.impl.CommonWxService;

public class WxRouterFactory {

	private WxRouterFactory() {
		super();
	}

	public static WXServiceHandler createServiceHandler(String appid) {
		WXServiceHandler wXServiceHandler = (CommonWxService) ApplicationContextUtil.getBean(CommonWxService.class);
		RedisClient redisClient = (RedisClient) ApplicationContextUtil.getBean(RedisClient.class);
		String json = redisClient.get(appid, Constant.REDIS_DB_INDEX);
		WxConfig wxConfig = new CommonWxConfig();
		if (StringUtils.isNotBlank(json)) {
			wxConfig = JSON.parseObject(json, CommonWxConfig.class);
		} else {
			Properties prop = new Properties();
			String fileName = Constant.ZK_CONFIG_ROOTNODE + Constant.FORWARD_SLASH + Constant.APPID_PROPERTIES_PREFIX
					+ appid + Constant.APPID_PROPERTIES_SUFFIX;
			InputStream in = WxRouterFactory.class.getResourceAsStream(fileName);
			try {
				prop.load(new BufferedReader(new InputStreamReader(in, Constant.DEFAULT_ENCODE)));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String appids = prop.getProperty(Constant.PROP_APPID);
			String asekey = prop.getProperty(Constant.PROP_APPAESKEY);
			String secret = prop.getProperty(Constant.PROP_APPSECRET);
			String token = prop.getProperty(Constant.PROP_APPTOKEN);
			wxConfig.setAppid(appids);
			wxConfig.setAppsecret(secret);//
			wxConfig.setToken(token);//
			wxConfig.setAesKey(asekey);//
			redisClient.set(appids, JSON.toJSONString(wxConfig), Constant.REDIS_DB_INDEX);
		}
		CustomWxMpInRedisConfigStorage config = new CustomWxMpInRedisConfigStorage();
		config.setRedisClient(redisClient);
		config.setAppId(wxConfig.getAppid());//
		config.setSecret(wxConfig.getAppsecret());//
		config.setToken(wxConfig.getToken());//
		config.setAesKey(wxConfig.getAesKey());//
		wXServiceHandler.setWxConfig(wxConfig);
		wXServiceHandler.setWxMpConfigStorage(config);
		return wXServiceHandler;

	}

	public static WxRouterFactory getInstance() {
		return ResponseActionFactoryInstance.instance;
	}

	private static class ResponseActionFactoryInstance {

		private static WxRouterFactory instance = new WxRouterFactory();
	}
}
