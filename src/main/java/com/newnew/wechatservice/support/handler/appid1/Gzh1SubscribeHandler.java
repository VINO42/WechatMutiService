package com.newnew.wechatservice.support.handler.appid1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newnew.wechatservice.support.config.WxConfig;
import com.newnew.wechatservice.support.config.WxXxConfig;
import com.newnew.wechatservice.support.handler.SubscribeHandler;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class Gzh1SubscribeHandler extends SubscribeHandler {
	@Autowired
	private WxXxConfig wxConfig;

	@Override
	protected WxConfig getWxConfig() {
		return this.wxConfig;
	}

	@Override
	protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) {
		return null;
	}

}
