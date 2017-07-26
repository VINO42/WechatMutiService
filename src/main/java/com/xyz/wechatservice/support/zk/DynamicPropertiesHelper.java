package com.xyz.wechatservice.support.zk;

import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.xyz.wechatservice.support.ApplicationContextUtil;
import com.xyz.wechatservice.support.RedisClient;
import com.xyz.wechatservice.support.config.CommonWxConfig;
import com.xyz.wechatservice.support.dto.Constant;

/**
 * 动态配置文件辅助类
 * 
 * @author june
 *
 */
public class DynamicPropertiesHelper {
	private ConcurrentHashMap<String, String> properties = new ConcurrentHashMap<String, String>();
	private ConcurrentHashMap<String, List<PropertyChangeListener>> propListeners = new ConcurrentHashMap<String, List<PropertyChangeListener>>();

	public DynamicPropertiesHelper(String initValue) {
		Properties props = parse(initValue);
		for (Map.Entry<Object, Object> propEn : props.entrySet())
			this.properties.put((String) propEn.getKey(), (String) propEn.getValue());
	}

	private Properties parse(String value) {
		Properties props = new Properties();
		if (!StringUtils.isEmpty(value))
			try {
				props.load(new StringReader(value));
			} catch (IOException localIOException) {
			}
		return props;
	}

	public synchronized void refresh(String propertiesAsStr) {
		Properties props = parse(propertiesAsStr);
		CommonWxConfig wxConfig = new CommonWxConfig();
		for (Map.Entry<Object, Object> propEn : props.entrySet()) {
			wxConfig = setToRedis((String) propEn.getKey(), (String) propEn.getValue(), wxConfig);
			setValue((String) propEn.getKey(), (String) propEn.getValue());
		}
		RedisClient client = (RedisClient) ApplicationContextUtil.getBean(RedisClient.class);
		client.set(wxConfig.getAppid(), JSON.toJSONString(wxConfig), Constant.REDIS_DB_INDEX);
	}

	private CommonWxConfig setToRedis(String key, String value, CommonWxConfig wxConfig) {
		switch (key) {
		case Constant.PROP_APPID:
			wxConfig.setAppid(value);
			break;
		case Constant.PROP_APPSECRET:
			wxConfig.setAesKey(value);
			break;
		case Constant.PROP_APPTOKEN:
			wxConfig.setToken(value);
			break;
		case Constant.PROP_APPAESKEY:
			wxConfig.setAppsecret(value);
			break;
		default:
			break;
		}
		return wxConfig;
	}

	private void setValue(String key, String newValue) {
		String oldValue = (String) this.properties.get(key);
		this.properties.put(key, newValue);
		if (!Objects.equals(oldValue, newValue))
			// 如果新值旧值都是null 那么就是新添加的 那么进行firePropertyChanged
			firePropertyChanged(key, oldValue, newValue);
	}

	public boolean containsProperty(String key) {
		return this.properties.containsKey(key);
	}

	public String getProperty(String key) {
		return (String) this.properties.get(key);
	}

	public String getProperty(String key, String defaultValue) {
		if (!containsProperty(key) || this.properties.get(key) == null) {
			return defaultValue;
		}
		return (String) this.properties.get(key);
	}

	public Boolean getBooleanProperty(String key, Boolean defaultValue) {
		if (!containsProperty(key) || this.properties.get(key) == null) {
			return defaultValue;
		}
		return Boolean.valueOf((String) this.properties.get(key));
	}

	public Integer getIntProperty(String key, Integer defaultValue) {
		Integer retValue = defaultValue;
		try {
			retValue = Integer.parseInt((String) this.properties.get(key));
		} catch (NumberFormatException e) {
		}
		return retValue;
	}

	public Long getLongProperty(String key, Long defaultValue) {
		Long retValue = defaultValue;
		try {
			retValue = Long.parseLong((String) this.properties.get(key));
		} catch (NumberFormatException e) {
		}
		return retValue;
	}

	public Double getDoubleProperty(String key, Double defaultValue) {
		Double retValue = defaultValue;
		try {
			retValue = Double.parseDouble((String) this.properties.get(key));
		} catch (NumberFormatException e) {
		}
		return retValue;
	}

	public Enumeration<String> getPropertyKeys() {
		return this.properties.keys();
	}

	/**
	 * 
	 * @param key
	 *            listener名字
	 * @param listener
	 */
	public void registerListener(String key, PropertyChangeListener listener) {
		List<PropertyChangeListener> listeners = new CopyOnWriteArrayList<PropertyChangeListener>();
		List<PropertyChangeListener> old = this.propListeners.putIfAbsent(key, listeners);
		if (old != null) {
			listeners = old;
		}
		listeners.add(listener);
	}

	private void firePropertyChanged(String key, String oldValue, String newValue) {
		List<PropertyChangeListener> listeners = (List<PropertyChangeListener>) this.propListeners.get(key);
		if ((listeners == null) || (listeners.size() == 0)) {
			return;
		}
		for (PropertyChangeListener listener : listeners)
			listener.propertyChanged(oldValue, newValue);
	}

	public static abstract interface PropertyChangeListener {
		public abstract void propertyChanged(String paramString1, String paramString2);
	}
}