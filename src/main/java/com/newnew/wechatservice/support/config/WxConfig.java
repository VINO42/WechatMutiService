package com.newnew.wechatservice.support.config;

public abstract class WxConfig {
	public abstract String getToken();

	public abstract String getAppid();

	public abstract String getAppsecret();

	public abstract String getAesKey();

	public abstract WxAccountEnum getWxAccountEnum();

	public int getPubId() {
		return getWxAccountEnum().getPubid();
	}
}
