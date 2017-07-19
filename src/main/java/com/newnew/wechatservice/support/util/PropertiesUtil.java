package com.newnew.wechatservice.support.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * properies工具类
 * 暂不能set
 * 自动关闭流
 * 缓存流
 * @author xy
 * @version 2016年8月29日 下午8:12:01
 */
public class PropertiesUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

	public PropertiesUtil() {
	}

	/**
	 * 获取值
	 * @author xy
	 * @version 2016年8月29日 下午9:56:58
	 * @param file
	 * @param key
	 * @return
	 */
	public static String get(InputStream file, String key) {
		return get(getProjectFileReader(file), key);
	}

	/**
	 * 获取值
	 * @author xy
	 * @version 2016年8月29日 下午9:56:58
	 * @param file
	 * @param key
	 * @return
	 */
	public static String get(Reader file, String key) {
		return load(file).get(key);
	}

	/**
	 * 获取值
	 * @author xy
	 * @version 2016年8月29日 下午9:57:10
	 * @param name
	 * @param key
	 * @return
	 */
	public static String get(String file, String key) {
		return get(getProjectFileReader(file), key);
	}

	/**
	 * 加载Properties
	 * @author xy
	 * @version 2016年8月29日 下午10:17:25
	 * @param file
	 * @return
	 */
	public static Properties load(String file) {
		return load(getProjectFileReader(file));
	}

	/**
	 * 加载Properties
	 * @author xy
	 * @version 2016年8月29日 下午10:17:37
	 * @param file
	 * @return
	 */
	public static Properties load(InputStream file) {
		return load(getProjectFileReader(file));
	}

	/**
	 * 加载Properties
	 * @author xy
	 * @version 2016年8月29日 下午10:17:37
	 * @param file
	 * @return
	 */
	public static Properties load(Reader file) {
		return Properties.load(file);
	}

	/**
	 * 获取Reader
	 * @author xy
	 * @version 2016年8月29日 下午10:17:05
	 * @param name
	 * @return
	 */
	private static Reader getProjectFileReader(String name) {
		return getProjectFileReader(getProjectFileInputStream(name));
	}

	/**
	 * 获取Reader
	 * @author xy
	 * @version 2016年8月29日 下午10:14:58
	 * @param inputStream
	 * @return
	 */
	private static Reader getProjectFileReader(InputStream inputStream) {
		if (inputStream == null)
			return null;
		Reader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			LOGGER.error(e.toString(), e);
			return null;
		}
		return reader;
	}

	/**
	 * 获取项目中的文件流(包含jar包中- 优先使用)
	 * @author xy
	 * @version 2016年8月29日 下午8:21:23
	 */
	private static InputStream getProjectFileInputStream(String name) {
		if (name == null || name.length() == 0)
			return null;
		return PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
	}

	/**
	 * 内置Properties对象
	 * @author xy
	 * @version 2016年8月29日 下午10:50:59
	 */
	public static class Properties {
		private java.util.Properties properties;

		private static Properties load(Reader file) {
			if (file == null)
				return null;
			Properties properties = new Properties();
			properties.properties = new java.util.Properties();
			try {
				properties.properties.load(file);
				return properties;
			} catch (IOException e) {
				properties.properties = null;
				LOGGER.error(e.toString(), e);
			} finally {
				if (file != null)
					try {
						file.close();
					} catch (IOException e) {
					}
			}
			return null;
		}

		/**
		 * 获取值
		 * @author xy
		 * @version 2016年8月29日 下午11:23:12
		 * @param key
		 * @return
		 */
		public String get(String key) {
			return properties.getProperty(key);
		}

		/**
		 * 获取值
		 * @author xy
		 * @version 2016年8月29日 下午11:23:20
		 * @param key
		 * @param defaultValue
		 * @return
		 */
		public String get(String key, String defaultValue) {
			return properties.getProperty(key, defaultValue);
		}
	}
}
