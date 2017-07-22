package com.newnew.wechatservice.support;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;

import com.newnew.wechatservice.support.anotation.WX;

public class WxRouterFactory {
	private static final String SERVICE_PACKAGE = "com.newnew.wechatservice.support.service.impl";
	private ClassLoader classLoader = getClass().getClassLoader();
	private static List<Class<? extends WXServiceHandler>> wXServiceHandlerList;// 策略列表
	private static Map<String, WXServiceHandler> wXServiceHandlerObjMap = new HashMap<>();

	private WxRouterFactory() {
		super();
		init();
	}

	public WXServiceHandler createServiceHandler(String appid) {
		WXServiceHandler wxServiceHandler = null;
		return wxServiceHandler;
	}

	private WxRouterFactory init() {
		wXServiceHandlerList = new ArrayList();
		File[] resources = getResources();// 获取到包下所有的class文件
		Class<WXServiceHandler> clas = null;
		try {
			clas = (Class<WXServiceHandler>) classLoader.loadClass(WXServiceHandler.class.getName());// 使用相同的加载器加载策略接口
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException("未找到策略接口");
		}
		for (int i = 0; i < resources.length; i++) {
			try {
				// 载入包下的类
				Class<?> clazz = classLoader
						.loadClass(SERVICE_PACKAGE + "." + resources[i].getName().replace(".class", ""));
				// 判断满足的话加入到策略列表

				if (WXServiceHandler.class.isAssignableFrom(clazz) && clazz != clas && clazz.getAnnotations() != null
						&& clazz.getAnnotations().length != 0) {
					try {
						WXServiceHandler handler = (WXServiceHandler) ApplicationContextUtil
								.getBean(firstLetterLower(clazz.getSimpleName()));
						wXServiceHandlerObjMap.put(clazz.getName(), handler);// 获得小写类名
					} catch (BeansException e) {
						e.printStackTrace();
					}
					wXServiceHandlerList.add((Class<? extends WXServiceHandler>) clazz);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return this;
	}

	public static WXServiceHandler createSendOrderAndQueryResponseAction(String appid) {
		WXServiceHandler wXServiceHandler = null;
		for (Class<? extends WXServiceHandler> claz : wXServiceHandlerList) {
			Annotation annotation = handleAnnotation(claz);
			if (annotation instanceof WX) {
				WX wx = (WX) annotation;
				String value = wx.value();
				if (value.equals(appid)) {
					wXServiceHandler = wXServiceHandlerObjMap.get(claz.getName());
					break;
				}
			}
		}
		return wXServiceHandler;
	}

	private static Annotation handleAnnotation(Class<? extends WXServiceHandler> claz) {
		Annotation[] annotations = claz.getDeclaredAnnotations();
		if (annotations == null || annotations.length == 0) {
			return null;
		}
		for (int i = 0; i < annotations.length; i++) {
			if (annotations[i] instanceof WX) {
				return annotations[i];
			}
		}
		return null;
	}

	private File[] getResources() {
		try {
			File file = new File(classLoader.getResource(SERVICE_PACKAGE.replace(".", "/")).toURI());
			return file.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					if (pathname.getName().endsWith(".class")) {// 我们只扫描class文件
						return true;
					}
					return false;
				}
			});
		} catch (URISyntaxException e) {
			throw new RuntimeException("未找到策略资源");
		}
	}

	private String firstLetterLower(String simpleName) {
		char charAt = simpleName.charAt(0);
		char newChar = simpleName.toLowerCase().charAt(0);
		simpleName = org.apache.commons.lang3.StringUtils.replace(simpleName, String.valueOf(charAt),
				String.valueOf(newChar), 1);
		return simpleName;
	}

}
