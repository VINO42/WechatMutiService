package com.xyz.wechatservice.support.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.xyz.wechatservice.support.config.WxConfig;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;

/**
 * 
 * @author Binary Wang
 *
 */
public abstract class AbstractHandler implements WxMpMessageHandler {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected final Gson gson = new Gson();

	protected abstract WxConfig getWxConfig();

	public abstract AbstractHandler setWxConfig(WxConfig wxConfig);

}
