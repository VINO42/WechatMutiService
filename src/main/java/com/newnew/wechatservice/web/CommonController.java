package com.newnew.wechatservice.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newnew.wechatservice.support.WxRouterFactory;
import com.newnew.wechatservice.support.dto.Constant;
import com.newnew.wechatservice.support.util.Result;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

@RestController
@RequestMapping("/common")
public class CommonController extends AbstractWxPortalController {
	private WxMpService wxMpService;

	public WxMpService getWxMpService() {
		return wxMpService;
	}

	public void setWxMpService(WxMpService wxMpService) {
		this.wxMpService = wxMpService;
	}

	@Resource
	private ZkClient zkclient;

	@RequestMapping(name="getSign/{appid}")
	public Result<String> getSign(@PathVariable("appid") String appid, String url) throws WxErrorException {
		try {
			url = URLDecoder.decode(url, Constant.DEFAULT_ENCODE);
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException @XxWxPortalController @ getSign ");
		}
		wxMpService = WxRouterFactory.createSendOrderAndQueryResponseAction(appid);
		WxJsapiSignature wxJsapiSignature = wxMpService.createJsapiSignature(url);
		Result<String> result = new Result<String>();
		result.setData(wxJsapiSignature.getSignature());
		result.setErrorcode(000);
		return result;
	}
}
