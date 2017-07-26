package com.xyz.wechatservice.support.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.wechatservice.support.RedisClient;
import com.xyz.wechatservice.support.WXServiceHandler;
import com.xyz.wechatservice.support.config.WxConfig;
import com.xyz.wechatservice.support.handler.AbstractHandler;
import com.xyz.wechatservice.support.handler.MenuHandler;
import com.xyz.wechatservice.support.handler.MsgHandler;
import com.xyz.wechatservice.support.handler.SubscribeHandler;
import com.xyz.wechatservice.support.handler.UnsubscribeHandler;
import com.xyz.wechatservice.support.handler.impl.CommonLocationHandler;
import com.xyz.wechatservice.support.handler.impl.CommonMenuHandler;
import com.xyz.wechatservice.support.handler.impl.CommonSubscribeHandler;
import com.xyz.wechatservice.support.handler.impl.CommonUnSubscribeHandler;
import com.xyz.wechatservice.support.handler.impl.ComnonMsgHandler;
import com.xyz.wechatservice.support.service.BaseWxService;

/**
 * 
 * @author Binary Wang
 *
 */
@Service("xXWxService")
public class CommonWxService extends BaseWxService implements WXServiceHandler {
	private WxConfig wxConfig;

	public WxConfig getWxConfig() {
		return wxConfig;
	}

	@Override
	public void setWxConfig(WxConfig wxConfig) {
		this.wxConfig = wxConfig;
	}

	@Autowired
	private CommonLocationHandler locationHandler;

	@Autowired
	private CommonMenuHandler menuHandler;

	@Autowired
	private ComnonMsgHandler msgHandler;

	@Autowired
	private CommonUnSubscribeHandler unSubscribeHandler;

	@Autowired
	private CommonSubscribeHandler subscribeHandler;

	@Resource
	private RedisClient redisClient;

	public RedisClient getRedisClient() {
		return redisClient;
	}

	public void setRedisClient(RedisClient redisClient) {
		this.redisClient = redisClient;
	}

	@Override
	protected WxConfig getServerConfig() {
		return this.wxConfig;
	}

	@Override
	protected MenuHandler getMenuHandler() {
		return this.menuHandler.setWxConfig(wxConfig);
	}

	@Override
	protected SubscribeHandler getSubscribeHandler() {
		return this.subscribeHandler.setWxConfig(wxConfig);
	}

	@Override
	protected UnsubscribeHandler getUnsubscribeHandler() {
		return this.unSubscribeHandler.setWxConfig(wxConfig);
	}

	@Override
	protected AbstractHandler getLocationHandler() {
		return this.locationHandler.setWxConfig(wxConfig);
	}

	@Override
	protected MsgHandler getMsgHandler() {
		return this.msgHandler.setWxConfig(wxConfig);
	}

	@Override
	protected AbstractHandler getScanHandler() {
		return null;
	}

}
