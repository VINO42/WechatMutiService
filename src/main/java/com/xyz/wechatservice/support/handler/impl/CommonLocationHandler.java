package com.xyz.wechatservice.support.handler.impl;

import org.springframework.stereotype.Component;

import com.xyz.wechatservice.support.config.WxConfig;
import com.xyz.wechatservice.support.handler.LocationHandler;

@Component
public class CommonLocationHandler extends LocationHandler {
	private WxConfig wxConfig;

	@Override
	protected WxConfig getWxConfig() {
		return this.wxConfig;
	}

	@Override
	public CommonLocationHandler setWxConfig(WxConfig wxConfig) {
		this.wxConfig = wxConfig;
		return this;
	}

}
