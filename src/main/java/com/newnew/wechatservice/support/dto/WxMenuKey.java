package com.newnew.wechatservice.support.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * @doc:菜单的 dto 对象
 * @author  
 * @date 2017年6月17日 下午2:04:04
 */
public class WxMenuKey {
	private String type;
	private String content;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public WxMenuKey() {

	}

	public WxMenuKey(String type, String content) {
		this.type = type;
		this.content = content;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
