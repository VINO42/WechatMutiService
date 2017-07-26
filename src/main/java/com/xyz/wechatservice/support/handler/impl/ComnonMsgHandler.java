package com.xyz.wechatservice.support.handler.impl;

import org.springframework.stereotype.Component;

import com.xyz.wechatservice.support.config.WxConfig;
import com.xyz.wechatservice.support.handler.MsgHandler;

@Component
public class ComnonMsgHandler extends MsgHandler {
	private WxConfig wxConfig;

	@Override
	protected WxConfig getWxConfig() {
		return this.wxConfig;
	}

	@Override
	public ComnonMsgHandler setWxConfig(WxConfig wxConfig) {
		this.wxConfig = wxConfig;
		return this;
	}

}
