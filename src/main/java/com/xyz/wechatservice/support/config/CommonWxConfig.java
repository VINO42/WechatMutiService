package com.xyz.wechatservice.support.config;

/**
 * 
 * @doc:TODO: 配置读取文件list参数
 * @author
 * @date 2017年7月18日 下午8:19:33
 */
// @Configuration
// @PropertySource("classpath:/wx/wx-appid2.properties")
public class CommonWxConfig extends WxConfig {
	// @Value("${wx_token}")
	private String token;

	// @Value("${wx_appid}")
	private String appid;

	// @Value("${wx_appsecret}")
	private String appsecret;

	// @Value("${wx_aeskey}")
	private String aesKey;

	@Override
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
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

}
