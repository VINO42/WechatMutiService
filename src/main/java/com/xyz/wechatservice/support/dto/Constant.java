package com.xyz.wechatservice.support.dto;

/**
 * 
 * @doc:常量
 * @author Andreby
 * @date 2017年7月20日 下午8:20:55
 */
public class Constant {
	public final static int TICKET_TTL = 10;

	public final static int REDIS_DB_INDEX = 14;
	/**
	 * 默认编码
	 */
	public final static String DEFAULT_ENCODE = "UTF-8";

	public final static String COMMA = ",";

	public final static String FORWARD_SLASH = "/";

	public final static String BACK_SLASH = "\\";

	public final static String APPID = "appid";

	public final static String REDIS_APPIDS = "appids";
	/**
	 * 工程内配置文件路径
	 */
	// public final static String ZK_CONFIG_ROOTNODE =
	// "/usr/local/soft/apache-tomcat-8.0.45/webapps/WechatMutiService/WEB-INF/classes/common";
	public final static String ZK_CONFIG_ROOTNODE = "/common"; // 工程内需要进行更新的文件根目录

	/**
	 * windows E:\\OwnCodes\\WechatMutiService\\common linux ../../../common
	 * 工程外的配置文件地址
	 */
	// public final static String OUT_CONF_PATH =
	// "/usr/local/soft/apache-tomcat-8.0.45/common";
	public final static String OUT_CONF_PATH = "../../../common"; // 工程外放置进行更新的文件目录
	/**
	 * 工程内配置文件编码
	 */
	public final static String CONF_ENCODING = "UTF-8";
	/**
	 * 工程外配置文件编码--->zk解析编码
	 */
	public final static String ZK_CONF_ENCODING = "UTF-8";
	/**
	 * zk超时时间
	 */
	public final static int ZK_TIMEOUT = 30000;
	/**
	 * 默认 zk地址
	 */
	public final static String ZK_ADDRESS = "127.0.0.1:2181";
	/**
	 * 默认zk 配置文件地址
	 */
	public final static String ZK_CONFIG_PATH = "/properties/zkpublisher.properties";

	public final static String ZK_PRE_CONFIG_PATH = "/properties";
	public final static String ZK_SUFFIX_CONFIG_PATH = ".properties";

	/**
	 * 配置文件中zk的key
	 */
	public final static String PROP_ZK_CONFIG_ROOTNODE_KEY = "ZK_CONFIG_ROOTNODE";

	public final static String PROP_ZK_CONF_ENCODING_KEY = "ZK_CONF_ENCODING";

	public final static String PROP_ZK_TIMEOUT_KEY = "ZK_TIMEOUT";

	public final static String PROP_ZK_ADDRESS_KEY = "ZK_ADDRESS";

	public static final String CONF_FILE_NAME = "common-appid.properties";

	public static final String ZK_TEMP_PATH = "/common/temp";

	public final static String ACCESS_TOKEN_KEY = "wechat_access_token_";

	public final static String JSAPI_TICKET_KEY = "wechat_jsapi_ticket_";

	public final static String CARDAPI_TICKET_KEY = "wechat_cardapi_ticket_";

	public final static String PROP_APPID = "wx_appid";

	public final static String PROP_APPSECRET = "wx_appsecret";

	public final static String PROP_APPTOKEN = "wx_token";

	public final static String PROP_APPAESKEY = "wx_aeskey";

	public final static String APPID_PROPERTIES_PREFIX = "wx-";

	public static final String APPID_PROPERTIES_SUFFIX = ".properties";

	public static final String WEB_SUF_PATH = "/WEB-INF/classes";
	
	public static final String COMMON_APPID_PATH = "/common/common-appid.properties";
}
