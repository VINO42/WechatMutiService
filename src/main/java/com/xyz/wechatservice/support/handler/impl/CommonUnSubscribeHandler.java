package com.xyz.wechatservice.support.handler.impl;

import org.springframework.stereotype.Component;

import com.xyz.wechatservice.support.config.WxConfig;
import com.xyz.wechatservice.support.handler.UnsubscribeHandler;

@Component
public class CommonUnSubscribeHandler extends UnsubscribeHandler {
	private WxConfig wxConfig;

	@Override
	protected WxConfig getWxConfig() {
		return this.wxConfig;
	}

	@Override
	public CommonUnSubscribeHandler setWxConfig(WxConfig wxConfig) {
		this.wxConfig = wxConfig;
		return this;
	}

}
