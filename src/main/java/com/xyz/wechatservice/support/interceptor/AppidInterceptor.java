package com.xyz.wechatservice.support.interceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xyz.wechatservice.support.RedisClient;
import com.xyz.wechatservice.support.dto.Constant;
import com.xyz.wechatservice.support.util.PropertiesUtil;

public class AppidInterceptor implements HandlerInterceptor {

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		NativeWebRequest webRequest = new ServletWebRequest(request);
		Map<String, String> uriTemplateVars = (Map<String, String>) webRequest
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
		String appidMd5 = uriTemplateVars.get(Constant.APPID);
		if (StringUtils.isBlank(appidMd5)) {
			return false;
		}
		ServletContext sc = request.getSession().getServletContext();
		XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils
				.getWebApplicationContext(sc);
		RedisClient redisClient = cxt.getBean(RedisClient.class);
		String jsonData = redisClient.get(Constant.REDIS_APPIDS, Constant.REDIS_DB_INDEX);
		List<String> appids = null;
		if (StringUtils.isBlank(jsonData)) {
			String appid2 =  PropertiesUtil.get(Constant.COMMON_APPID_PATH,Constant.REDIS_APPIDS);
			String[] split = appid2.split(Constant.COMMA);
			appids = Arrays.asList(split);
		} else {
			appids = JSON.parseArray(jsonData, String.class);
		}
		if (!appids.contains(appidMd5)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
