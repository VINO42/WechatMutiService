package com.xyz.wechatservice.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.wechatservice.support.WXServiceHandler;
import com.xyz.wechatservice.support.WxRouterFactory;
import com.xyz.wechatservice.support.dto.Constant;
import com.xyz.wechatservice.support.util.Result;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;

@RestController
@RequestMapping("/wxApi")
@SuppressWarnings("static-access")
public class CommonController extends AbstractWxPortalController {

	/**
	 * 
	 * @doc:签名生成
	 * @author Andreby
	 * @date 2017年8月1日 下午7:50:08
	 * @param appid
	 * @param url
	 * @return
	 * @throws WxErrorException
	 */
	@RequestMapping("sign/{appid}")
	public Result<String> getSign(@PathVariable("appid") String appid, @RequestParam String url)
			throws WxErrorException {
		try {
			logger.info("进入获取签名方法:::::" + url);
			url = URLDecoder.decode(url, Constant.DEFAULT_ENCODE);
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException @XxWxPortalController @ getSign ");
		}
		WXServiceHandler wxMpService = WxRouterFactory.getInstance().createServiceHandler(appid);
		logger.info("wxMpService对象::::::" + wxMpService.toString());
		WxJsapiSignature wxJsapiSignature = wxMpService.createJsapiSignature(url);
		Result<String> result = new Result<String>();
		result.setData(wxJsapiSignature.getSignature());
		result.setErrorcode(000);
		logger.info("进入获取签名方法:::::签名为:" + wxJsapiSignature.getSignature());
		return result;
	}

	/**
	 * 
	 * @doc:短连接生成
	 * @author Andreby
	 * @date 2017年8月1日 下午7:50:40
	 * @param appid
	 * @param url
	 * @return
	 * @throws WxErrorException
	 */
	@RequestMapping("shortUrl/{appid}")
	public Result<String> shortUrl(@PathVariable("appid") String appid, @RequestParam String url)
			throws WxErrorException {
		try {
			logger.info("进入获取短连接方法:::::url为" + url + "appid为:::" + appid);
			url = URLDecoder.decode(url, Constant.DEFAULT_ENCODE);
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException @XxWxPortalController @ getSign ");
		}
		WXServiceHandler wxMpService = WxRouterFactory.getInstance().createServiceHandler(appid);
		logger.info("wxMpService对象::::::" + wxMpService.toString());
		String shortUrl = wxMpService.shortUrl(url);
		Result<String> result = new Result<String>();
		result.setData(shortUrl);
		result.setErrorcode(000);
		logger.info("进入获取短连接方法:::::shortUrl为" + shortUrl);
		return result;
	}
}
