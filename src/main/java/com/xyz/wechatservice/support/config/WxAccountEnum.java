package com.xyz.wechatservice.support.config;

public enum WxAccountEnum {
	GZH1(1, " 公众号 1"), GZH2(2, " 公众号 2");

	private int appid;
	private String name;

	private WxAccountEnum(int pubid, String name) {
		this.name = name;
		this.appid = pubid;
	}


	public int getAppid() {
		return appid;
	}


	public void setAppid(int appid) {
		this.appid = appid;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return this.name;
	}

	public static int queryPubid(String wxCode) {
		return WxAccountEnum.valueOf(wxCode.toUpperCase()).getAppid();
	}

	public static String queryWxCode(int pubid) {
		for (WxAccountEnum e : values()) {
			if (e.getAppid() == pubid) {
				return e.name().toLowerCase();
			}
		}

		return null;
	}
}
