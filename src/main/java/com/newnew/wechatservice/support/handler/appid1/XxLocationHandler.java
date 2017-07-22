package com.newnew.wechatservice.support.handler.appid1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newnew.wechatservice.support.config.WxConfig;
import com.newnew.wechatservice.support.config.WxXxConfig;
import com.newnew.wechatservice.support.handler.LocationHandler;

@Component
public class XxLocationHandler extends LocationHandler {
	@Autowired
	private WxXxConfig wxConfig;

	@Override
	protected WxConfig getWxConfig() {
		return this.wxConfig;
	}

}
