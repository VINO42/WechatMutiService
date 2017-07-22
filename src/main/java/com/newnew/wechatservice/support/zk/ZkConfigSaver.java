package com.newnew.wechatservice.support.zk;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.io.FileUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newnew.wechatservice.support.dto.Constant;

/**
 * zk配置文件下载类
 * 
 * @author june
 *
 */
public class ZkConfigSaver {
	public static final String CONF_ENCODING = Constant.CONF_ENCODING;
	private static final int String = 0;
	public static String ZK_CONFIG_ROOTNODE = Constant.ZK_CONFIG_ROOTNODE;
	public static String ZK_CONF_ENCODING = Constant.ZK_CONF_ENCODING;
	public static int ZK_TIMEOUT = Constant.ZK_TIMEOUT;
	public static String ZK_ADDRESS = Constant.ZK_ADDRESS;
	private DynamicPropertiesHelperFactory helperFactory;

	public DynamicPropertiesHelperFactory getHelperFactory() {
		return helperFactory;
	}

	public void setHelperFactory(DynamicPropertiesHelperFactory helperFactory) {
		this.helperFactory = helperFactory;
	}

	private static final void loadProperties() {
		InputStream is = ZkConfigPublisher.class.getResourceAsStream(Constant.ZK_CONFIG_PATH);
		if (is == null) {
			throw new RuntimeException("找不到config.properties资源文件.");
		}
		Properties props = new Properties();
		try {
			props.load(new BufferedReader(new InputStreamReader(is, Constant.DEFAULT_ENCODE)));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ZK_CONFIG_ROOTNODE = props.getProperty("ZK_CONFIG_ROOTNODE");
		ZK_CONF_ENCODING = props.getProperty("ZK_CONF_ENCODING");
		ZK_TIMEOUT = Integer.parseInt(props.getProperty("ZK_TIMEOUT"));
		ZK_ADDRESS = props.getProperty("ZK_ADDRESS");
	}

	public static void main(String[] args) {
		// if ((args == null) || (args.length < 1)) {
		// throw new RuntimeException("需要指定输出目录名");
		// }
		loadProperties();
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");

		DynamicPropertiesHelperFactory helperFactory = ((DynamicPropertiesHelperFactory) ctx
				.getBean(DynamicPropertiesHelperFactory.class));
		ZkClient client = new ZkClient(ZK_ADDRESS, ZK_TIMEOUT);
		client.setZkSerializer(new ZkUtils.StringSerializer(ZK_CONF_ENCODING));

		File confDir = new File(ZK_CONFIG_ROOTNODE);
		confDir.mkdirs();

		try {
			saveConfigs(client, ZK_CONFIG_ROOTNODE, confDir, helperFactory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void saveConfigs(ZkClient client, String rootNode, File confDir,
			DynamicPropertiesHelperFactory helperFactory) throws IOException {
		List<String> configs = client.getChildren(rootNode);
		for (String config : configs) {
			String content = (String) client.readData(rootNode + Constant.FORWARD_SLASH + config);
			File confFile = new File(confDir, config);
			try {
				FileUtils.writeStringToFile(confFile, content, Constant.DEFAULT_ENCODE);
				if (config.equals(Constant.CONF_FILE_NAME)) {
					DynamicPropertiesHelper helper = helperFactory.getHelper(config);
					String property = helper.getProperty(Constant.APPID);
					if (!client.exists(Constant.ZK_TEMP_PATH)) {
						client.createPersistent(Constant.ZK_TEMP_PATH, true);
					}
					client.writeData(Constant.ZK_TEMP_PATH, property);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("配置成功保存到本地: " + confFile.getAbsolutePath());
		}
	}
}