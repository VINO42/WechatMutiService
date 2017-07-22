package com.newnew.wechatservice.support.zk;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.io.FileUtils;

import com.newnew.wechatservice.support.dto.Constant;

/**
 * zk配置文件发布者类
 * 
 * @author june
 *
 */
public class ZkConfigPublisher {
	public static String CONF_DIR = Constant.OUT_CONF_PATH;// 工程外放置进行更新的文件目录
	public static final String CONF_ENCODING = Constant.CONF_ENCODING;
	public static String ZK_CONFIG_ROOTNODE = Constant.ZK_CONFIG_ROOTNODE;// 工程内需要进行更新的文件根目录

	public static String ZK_CONF_ENCODING = Constant.ZK_CONF_ENCODING;
	public static int ZK_TIMEOUT = Constant.ZK_TIMEOUT;
	public static String ZK_ADDRESS = Constant.ZK_ADDRESS;

	private static final void loadProperties() {
		InputStream is = ZkConfigPublisher.class.getResourceAsStream(Constant.ZK_CONFIG_PATH);
		if (is == null) {
			throw new RuntimeException("找不到zkpublisher.properties资源文件.");
		}
		Properties props = new Properties();
		try {
			props.load(new BufferedReader(new InputStreamReader(is, Constant.DEFAULT_ENCODE)));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ZK_CONFIG_ROOTNODE = props.getProperty(Constant.PROP_ZK_CONFIG_ROOTNODE_KEY);
		ZK_CONF_ENCODING = props.getProperty(Constant.PROP_ZK_CONF_ENCODING_KEY);
		ZK_TIMEOUT = Integer.parseInt(props.getProperty(Constant.PROP_ZK_TIMEOUT_KEY));
		ZK_ADDRESS = props.getProperty(Constant.PROP_ZK_ADDRESS_KEY);
	}

	public static void main(String[] args) {
		loadProperties();

		ZkClient client = new ZkClient(ZK_ADDRESS, ZK_TIMEOUT);
		client.setZkSerializer(new ZkUtils.StringSerializer(ZK_CONF_ENCODING));

		File confDir = new File(CONF_DIR);
		if ((!confDir.exists()) || (!confDir.isDirectory())) {
			System.err.println("错误： 配置目录" + confDir + "不存在或非法! ");
			System.exit(1);
		}

		publishConfigs(client, ZK_CONFIG_ROOTNODE, confDir);
	}

	private static void publishConfigs(ZkClient client, String rootNode, File confDir) {
		File[] confs = confDir.listFiles();
		int success = 0;
		int failed = 0;
		for (File conf : confs) {
			if (!conf.isFile()) {
				continue;
			}
			String name = conf.getName();
			String path = ZkUtils.getZkPath(rootNode, name);
			ZkUtils.mkPaths(client, path);
			String content;
			try {
				content = FileUtils.readFileToString(conf, Constant.DEFAULT_ENCODE);
			} catch (IOException e) {
				System.err.println("错误: 读取文件内容时遇到异常:" + e.getMessage());
				failed++;
				continue;
			}
			if (!client.exists(path)) {
				try {
					client.createPersistent(path);
					client.writeData(path, content);
				} catch (Throwable e) {
					System.err.println("错误: 尝试发布配置失败: " + e.getMessage());
					failed++;
					continue;
				}
				System.out.println("提示: 已经成功将配置文件" + conf + "内容发布为新的ZK配置" + path);
			} else {
				try {
					client.writeData(path, content);
				} catch (Throwable e) {
					System.err.println("错误: 尝试发布配置失败: " + e.getMessage());
					failed++;
					continue;
				}
				System.out.println("提示: 已经成功将配置文件" + conf + "内容更新到ZK配置" + path);
			}
			success++;
		}
		System.out.println("提示: 完成配置发布，成功" + success + "，失败" + failed + "。");
	}
}