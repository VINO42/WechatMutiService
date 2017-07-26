package com.xyz.wechatservice.support.handler.impl;

import org.springframework.stereotype.Component;

import com.xyz.wechatservice.support.config.WxConfig;
import com.xyz.wechatservice.support.handler.MenuHandler;

@Component
public class CommonMenuHandler extends MenuHandler {
	private WxConfig wxConfig;

	@Override
	protected WxConfig getWxConfig() {
		return this.wxConfig;
	}

	@Override
	public MenuHandler setWxConfig(WxConfig wxConfig) {
		this.wxConfig = wxConfig;
		return this;
	}
}
