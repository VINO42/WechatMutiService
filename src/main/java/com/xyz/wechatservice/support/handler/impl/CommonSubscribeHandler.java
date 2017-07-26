package com.xyz.wechatservice.support.handler.impl;

import org.springframework.stereotype.Component;

import com.xyz.wechatservice.support.config.WxConfig;
import com.xyz.wechatservice.support.handler.SubscribeHandler;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class CommonSubscribeHandler extends SubscribeHandler {
	private WxConfig wxConfig;

	@Override
	protected WxConfig getWxConfig() {
		return this.wxConfig;
	}

	@Override
	protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) {
		return null;
	}

	@Override
	public CommonSubscribeHandler setWxConfig(WxConfig wxConfig) {
		this.wxConfig = wxConfig;
		return this;
	}

}
