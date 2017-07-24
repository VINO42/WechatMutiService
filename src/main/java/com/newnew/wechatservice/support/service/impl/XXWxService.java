package com.newnew.wechatservice.support.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newnew.wechatservice.support.RedisClient;
import com.newnew.wechatservice.support.WXServiceHandler;
import com.newnew.wechatservice.support.anotation.WX;
import com.newnew.wechatservice.support.config.WxConfig;
import com.newnew.wechatservice.support.config.WxXxConfig;
import com.newnew.wechatservice.support.handler.AbstractHandler;
import com.newnew.wechatservice.support.handler.MenuHandler;
import com.newnew.wechatservice.support.handler.MsgHandler;
import com.newnew.wechatservice.support.handler.SubscribeHandler;
import com.newnew.wechatservice.support.handler.UnsubscribeHandler;
import com.newnew.wechatservice.support.handler.appid1.Gzh1MsgHandler;
import com.newnew.wechatservice.support.handler.appid1.Gzh1SubscribeHandler;
import com.newnew.wechatservice.support.handler.appid1.XxLocationHandler;
import com.newnew.wechatservice.support.handler.appid1.XxMenuHandler;
import com.newnew.wechatservice.support.handler.appid1.XxUnSubscribeHandler;
import com.newnew.wechatservice.support.service.BaseWxService;

/**
 * 
 * @author Binary Wang
 *
 */
@Service("xXWxService")
@WX("1")
public class XXWxService extends BaseWxService implements WXServiceHandler {
	@Autowired
	private WxXxConfig wxConfig;

	@Autowired
	private XxLocationHandler locationHandler;

	@Autowired
	private XxMenuHandler menuHandler;

	@Autowired
	private Gzh1MsgHandler msgHandler;

	@Autowired
	private XxUnSubscribeHandler unSubscribeHandler;

	@Autowired
	private Gzh1SubscribeHandler subscribeHandler;

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
		return this.menuHandler;
	}

	@Override
	protected SubscribeHandler getSubscribeHandler() {
		return this.subscribeHandler;
	}

	@Override
	protected UnsubscribeHandler getUnsubscribeHandler() {
		return this.unSubscribeHandler;
	}

	@Override
	protected AbstractHandler getLocationHandler() {
		return this.locationHandler;
	}

	@Override
	protected MsgHandler getMsgHandler() {
		return this.msgHandler;
	}

	@Override
	protected AbstractHandler getScanHandler() {
		return null;
	}

}
