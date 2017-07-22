package com.newnew.wechatservice.support.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @doc:TODO: 配置读取文件list参数
 * @author 
 * @date 2017年7月18日 下午8:19:33
 */
@Configuration
@PropertySource("classpath:/wx/wx-appid2.properties")
public class WxXxConfig extends WxConfig {
	@Value("${wx_token}")
	public String token;

	@Value("${wx_appid}")
	public String appid;

	@Value("${wx_appsecret}")
	private String appsecret;

	@Value("${wx_aeskey}")
	public String aesKey;

	@Override
	public String getToken() {
		return this.token;
	}

	@Override
	public String getAppid() {
		return this.appid;
	}

	@Override
	public String getAppsecret() {
		return this.appsecret;
	}

	@Override
	public String getAesKey() {
		return this.aesKey;
	}

	@Override
	public WxAccountEnum getWxAccountEnum() {
		return WxAccountEnum.GZH1;
	}

}
