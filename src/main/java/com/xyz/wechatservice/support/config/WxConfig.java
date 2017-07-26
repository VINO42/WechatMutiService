package com.xyz.wechatservice.support.config;

public abstract class WxConfig {
	public abstract String getToken();

	public abstract String getAppid();

	public abstract String getAppsecret();

	public abstract String getAesKey();

	public abstract void setToken(String token);

	public abstract void setAppid(String appid);

	public abstract void setAppsecret(String appsecret);

	public abstract void setAesKey(String aesKey);

}
