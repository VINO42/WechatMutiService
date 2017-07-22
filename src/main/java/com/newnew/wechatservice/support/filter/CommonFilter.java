package com.newnew.wechatservice.support.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.newnew.wechatservice.support.dto.Constant;
import com.newnew.wechatservice.support.zk.DynamicPropertiesHelper;
import com.newnew.wechatservice.support.zk.DynamicPropertiesHelperFactory;

/**
 * 
 * @doc:过滤
 * @author Andreby
 * @date 2017年7月20日 下午7:51:22
 */
public class CommonFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String appid = request.getParameter(Constant.APPID);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		ServletContext sc = req.getSession().getServletContext();
		XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils
				.getWebApplicationContext(sc);
		ZkClient zkClient = (ZkClient) cxt.getBean("zkClient");
		DynamicPropertiesHelperFactory helperFac = cxt.getBean(DynamicPropertiesHelperFactory.class);
		DynamicPropertiesHelper helper = helperFac.getHelper(Constant.CONF_FILE_NAME);
		List<String> appids = null;
		if (zkClient.exists(Constant.ZK_TEMP_PATH)) {
			appids = zkClient.readData(Constant.ZK_TEMP_PATH);
		}
		if (appids == null || appids.size() == 0) {
			String appid2 = helper.getProperty(Constant.APPID);
			appids = Arrays.asList(appid2);
		}
		if (!appids.contains(appid)) {
			return;
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {

	}
}
