package com.newnew.wechatservice.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	/**
	 * @RequestParam("url") String url
	 * @doc:
	 * @author Andreby
	 * @date 2017年7月24日 下午8:46:07 
	 * @param appid
	 * @return
	 * @throws WxErrorException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("getSign/{appid}")
	@ResponseBody
	public Result<String> getSign(@PathVariable("appid") String appid) throws WxErrorException {
//		try {
////			url = URLDecoder.decode(url, Constant.DEFAULT_ENCODE);
//		} catch (UnsupportedEncodingException e) {
//			logger.error("UnsupportedEncodingException @XxWxPortalController @ getSign ");
//		}
		wxMpService = WxRouterFactory.getInstance().createServiceHandler(appid);
		WxJsapiSignature wxJsapiSignature = wxMpService.createJsapiSignature("www.baidu.com");
		Result<String> result = new Result<String>();
		result.setData(wxJsapiSignature.getSignature());
		result.setErrorcode(000);
		return result;
	}
}
