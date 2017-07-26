package com.xyz.wechatservice.support.zk;

/**
 * 监听器，监听配置的改变
 * 
 * @author june
 * 
 */
public abstract interface ConfigChangeListener {
	public abstract void configChanged(String paramString1, String paramString2);
}