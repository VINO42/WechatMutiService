package com.newnew.wechatservice.support;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

/**
 * 
 * @doc:复写 WxMpInMemoryConfigStorage
 * @author Andreby
 * @date 2017年6月17日 下午10:49:29
 */
@Service
public class CustomWxMpInRedisConfigStorage extends WxMpInMemoryConfigStorage {
	private final static String ACCESS_TOKEN_KEY = "wechat_access_token_";

	private final static String JSAPI_TICKET_KEY = "wechat_jsapi_ticket_";

	private final static String CARDAPI_TICKET_KEY = "wechat_cardapi_ticket_";
	@Resource
	protected RedisClient redisClient;

	@Override
	public String getAccessToken() {
		return redisClient.get(ACCESS_TOKEN_KEY.concat(appId));
	}

	@Override
	public boolean isAccessTokenExpired() {
		return getAccessToken() == null ? true : false;
	}

	@Override
	public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
		redisClient.set(ACCESS_TOKEN_KEY.concat(appId), accessToken,14);
		redisClient.expire(ACCESS_TOKEN_KEY.concat(appId), expiresInSeconds - 200,14);
	}

	@Override
	public void expireAccessToken() {
		redisClient.expire(ACCESS_TOKEN_KEY.concat(appId), 0,14);
	}

	@Override
	public String getJsapiTicket() {
		return redisClient.get(JSAPI_TICKET_KEY.concat(appId));
	}

	@Override
	public boolean isJsapiTicketExpired() {
		return getJsapiTicket() == null ? true : false;
	}

	@Override
	public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
		redisClient.set(JSAPI_TICKET_KEY.concat(appId), jsapiTicket,14);
		redisClient.expire(JSAPI_TICKET_KEY.concat(appId), expiresInSeconds - 200,14);
	}

	@Override
	public void expireJsapiTicket() {
		redisClient.expire(JSAPI_TICKET_KEY.concat(appId), 0,14);
	}

	/**
	 * 卡券api_ticket
	 */
	@Override
	public String getCardApiTicket() {
		return redisClient.get(CARDAPI_TICKET_KEY.concat(appId));
	}

	@Override
	public boolean isCardApiTicketExpired() {
		return getCardApiTicket() == null ? true : false;
	}

	@Override
	public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
		redisClient.set(CARDAPI_TICKET_KEY.concat(appId), cardApiTicket,14);
		redisClient.expire(CARDAPI_TICKET_KEY.concat(appId), expiresInSeconds - 200,14);
	}

	@Override
	public void expireCardApiTicket() {
		redisClient.expire(CARDAPI_TICKET_KEY.concat(appId), 0,14);
	}

}
