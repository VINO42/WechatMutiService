package com.xyz.wechatservice.support;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.xyz.wechatservice.support.dto.Constant;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;

/**
 * 
 * @doc:复写 WxMpInMemoryConfigStorage
 * @author Andreby
 * @date 2017年6月17日 下午10:49:29
 */
public class CustomWxMpInRedisConfigStorage implements WxMpConfigStorage {

	protected volatile String appId;
	protected volatile String secret;
	protected volatile String token;
	protected volatile String accessToken;
	protected volatile String aesKey;
	protected volatile long expiresTime;

	protected volatile String oauth2redirectUri;

	protected volatile String httpProxyHost;
	protected volatile int httpProxyPort;
	protected volatile String httpProxyUsername;
	protected volatile String httpProxyPassword;

	protected volatile String jsapiTicket;
	protected volatile long jsapiTicketExpiresTime;

	protected volatile String cardApiTicket;
	protected volatile long cardApiTicketExpiresTime;

	protected Lock accessTokenLock = new ReentrantLock();
	protected Lock jsapiTicketLock = new ReentrantLock();
	protected Lock cardApiTicketLock = new ReentrantLock();
	protected volatile ApacheHttpClientBuilder apacheHttpClientBuilder;

	private RedisClient redisClient;

	public void setRedisClient(RedisClient redisClient) {
		this.redisClient = redisClient;
	}

	@Override
	public String getAccessToken() {
		return redisClient.get(Constant.ACCESS_TOKEN_KEY.concat(appId), Constant.REDIS_DB_INDEX);
	}

	@Override
	public boolean isAccessTokenExpired() {
		Long ttl = redisClient.ttl(Constant.ACCESS_TOKEN_KEY.concat(appId), Constant.REDIS_DB_INDEX);
		return ttl < 2 ? true : false;
	}

	@Override
	public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
		redisClient.set(Constant.ACCESS_TOKEN_KEY.concat(appId), accessToken, Constant.REDIS_DB_INDEX);
		redisClient.expire(Constant.ACCESS_TOKEN_KEY.concat(appId), expiresInSeconds - 200, Constant.REDIS_DB_INDEX);
	}

	@Override
	public void expireAccessToken() {
		redisClient.expire(Constant.ACCESS_TOKEN_KEY.concat(appId), Constant.TICKET_TTL, Constant.REDIS_DB_INDEX);
	}

	@Override
	public String getJsapiTicket() {
		return redisClient.get(Constant.JSAPI_TICKET_KEY.concat(appId), Constant.REDIS_DB_INDEX);
	}

	@Override
	public boolean isJsapiTicketExpired() {
		Long ttl = redisClient.ttl(Constant.JSAPI_TICKET_KEY.concat(appId), Constant.REDIS_DB_INDEX);
		return ttl < 2 ? true : false;
	}

	@Override
	public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
		redisClient.set(Constant.JSAPI_TICKET_KEY.concat(appId), jsapiTicket, Constant.REDIS_DB_INDEX);
		redisClient.expire(Constant.JSAPI_TICKET_KEY.concat(appId), expiresInSeconds - 200, Constant.REDIS_DB_INDEX);
	}

	@Override
	public void expireJsapiTicket() {
		redisClient.expire(Constant.JSAPI_TICKET_KEY.concat(appId), Constant.TICKET_TTL, Constant.REDIS_DB_INDEX);
	}

	/**
	 * 卡券api_ticket
	 */
	@Override
	public String getCardApiTicket() {
		return redisClient.get(Constant.CARDAPI_TICKET_KEY.concat(appId), Constant.REDIS_DB_INDEX);
	}

	@Override
	public boolean isCardApiTicketExpired() {
		return getCardApiTicket() == null ? true : false;
	}

	@Override
	public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
		redisClient.set(Constant.CARDAPI_TICKET_KEY.concat(appId), cardApiTicket, Constant.REDIS_DB_INDEX);
		redisClient.expire(Constant.CARDAPI_TICKET_KEY.concat(appId), expiresInSeconds - 200, Constant.REDIS_DB_INDEX);
	}

	@Override
	public void expireCardApiTicket() {
		redisClient.expire(Constant.CARDAPI_TICKET_KEY.concat(appId), 0, Constant.REDIS_DB_INDEX);
	}

	@Override
	public Lock getAccessTokenLock() {
		return this.accessTokenLock;
	}

	@Override
	public synchronized void updateAccessToken(WxAccessToken accessToken) {
		updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
	}

	@Override
	public Lock getJsapiTicketLock() {
		return this.jsapiTicketLock;
	}

	@Override
	public Lock getCardApiTicketLock() {
		return this.cardApiTicketLock;
	}

	@Override
	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override
	public String getSecret() {
		return this.secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public long getExpiresTime() {
		return this.expiresTime;
	}

	public void setExpiresTime(long expiresTime) {
		this.expiresTime = expiresTime;
	}

	@Override
	public String getAesKey() {
		return this.aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	@Override
	public String getOauth2redirectUri() {
		return this.oauth2redirectUri;
	}

	public void setOauth2redirectUri(String oauth2redirectUri) {
		this.oauth2redirectUri = oauth2redirectUri;
	}

	@Override
	public String getHttpProxyHost() {
		return this.httpProxyHost;
	}

	public void setHttpProxyHost(String httpProxyHost) {
		this.httpProxyHost = httpProxyHost;
	}

	@Override
	public int getHttpProxyPort() {
		return this.httpProxyPort;
	}

	public void setHttpProxyPort(int httpProxyPort) {
		this.httpProxyPort = httpProxyPort;
	}

	@Override
	public String getHttpProxyUsername() {
		return this.httpProxyUsername;
	}

	public void setHttpProxyUsername(String httpProxyUsername) {
		this.httpProxyUsername = httpProxyUsername;
	}

	@Override
	public String getHttpProxyPassword() {
		return this.httpProxyPassword;
	}

	public void setHttpProxyPassword(String httpProxyPassword) {
		this.httpProxyPassword = httpProxyPassword;
	}

	@Override
	public String toString() {
		return ToStringUtils.toSimpleString(this);
	}

	@Override
	public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
		return this.apacheHttpClientBuilder;
	}

	public void setApacheHttpClientBuilder(ApacheHttpClientBuilder apacheHttpClientBuilder) {
		this.apacheHttpClientBuilder = apacheHttpClientBuilder;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public long getJsapiTicketExpiresTime() {
		return this.jsapiTicketExpiresTime;
	}

	public void setJsapiTicketExpiresTime(long jsapiTicketExpiresTime) {
		this.jsapiTicketExpiresTime = jsapiTicketExpiresTime;
	}

	public void setCardApiTicket(String cardApiTicket) {
		this.cardApiTicket = cardApiTicket;
	}

	public long getCardApiTicketExpiresTime() {
		return this.cardApiTicketExpiresTime;
	}

	public void setCardApiTicketExpiresTime(long cardApiTicketExpiresTime) {
		this.cardApiTicketExpiresTime = cardApiTicketExpiresTime;
	}

	@Override
	public boolean autoRefreshToken() {
		return true;
	}

	@Override
	public File getTmpDirFile() {
		return null;
	}

}
