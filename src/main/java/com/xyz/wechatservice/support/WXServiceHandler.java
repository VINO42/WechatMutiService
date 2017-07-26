package com.xyz.wechatservice.support;

import com.xyz.wechatservice.support.config.WxConfig;

import me.chanjar.weixin.mp.api.WxMpService;

public interface WXServiceHandler extends WxMpService {
	public void setWxConfig(WxConfig wxConfig);
}
