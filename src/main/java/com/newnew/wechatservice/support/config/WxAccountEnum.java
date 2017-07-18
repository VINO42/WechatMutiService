package com.newnew.wechatservice.support.config;

public enum WxAccountEnum {
	GZH1(1, " 公众号 1"), GZH2(2, " 公众号 2");

	private int pubid;
	private String name;

	private WxAccountEnum(int pubid, String name) {
		this.name = name;
		this.pubid = pubid;
	}

	public int getPubid() {
		return this.pubid;
	}

	public String getName() {
		return this.name;
	}

	public static int queryPubid(String wxCode) {
		return WxAccountEnum.valueOf(wxCode.toUpperCase()).getPubid();
	}

	public static String queryWxCode(int pubid) {
		for (WxAccountEnum e : values()) {
			if (e.getPubid() == pubid) {
				return e.name().toLowerCase();
			}
		}

		return null;
	}
}
