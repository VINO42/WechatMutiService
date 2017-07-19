package com.newnew.wechatservice.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newnew.wechatservice.support.service.BaseWxService;
import com.newnew.wechatservice.support.service.XXWxService;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;

@RestController
@RequestMapping("/api")
public class XxWxPortalController extends AbstractWxPortalController {
	@Autowired
	private XXWxService xXWxService;

	@Override
	protected BaseWxService getWxService() {
		return this.xXWxService;
	}

	@RequestMapping(value = "getSign")
	public Map<String, Object> getSign(String appid, String url) throws WxErrorException {
		try {
			url = URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException @LeDongWxPortalController @ getSign ");
		}
		//helper.getproperties 从zk中获得 配置属性  a b c d '
		//组装配置config
		//create
		WxJsapiSignature wxJsapiSignature = xXWxService.createJsapiSignature(url);
		return null;
	}

}
