package com.xyz.wechatservice.support.zk;

import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class DynamicPropertiesSpringConfigurer extends PropertyPlaceholderConfigurer {
	private static final Logger logger = LoggerFactory.getLogger(DynamicPropertiesSpringConfigurer.class);
	private DynamicPropertiesHelperFactory helperFactory;
	private String[] propertiesKeys;

	public void setHelperFactory(DynamicPropertiesHelperFactory helperFactory) {
		this.helperFactory = helperFactory;
	}

	public void setPropertiesKeys(String[] propertiesKeys) {
		this.propertiesKeys = propertiesKeys;
	}

	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		if (this.propertiesKeys != null) {
			for (String propsKey : this.propertiesKeys) {
				DynamicPropertiesHelper helper = this.helperFactory.getHelper(propsKey);
				if (helper != null) {
					Enumeration<String> keys = helper.getPropertyKeys();
					while (keys.hasMoreElements()) {
						String key = (String) keys.nextElement();
						props.put(key, helper.getProperty(key));
					}
				} else {
					logger.warn("配置不存在: " + propsKey);
				}
			}
		}
		super.processProperties(beanFactoryToProcess, props);
	}
}