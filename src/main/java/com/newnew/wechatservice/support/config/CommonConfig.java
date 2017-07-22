package com.newnew.wechatservice.support.config;

import org.springframework.stereotype.Component;

@Component
public class CommonConfig {
	public String token;

	public String appid;

	public String appsecret;

	public String aesKey;

	public static CommonConfig build() {
		return new CommonConfig();
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

}
