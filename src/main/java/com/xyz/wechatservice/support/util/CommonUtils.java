package com.xyz.wechatservice.support.util;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class CommonUtils {
	
	public static String md5(String value) {
		String md5Str = Hashing.md5().newHasher().putString(value, Charsets.UTF_8).hash().toString().substring(8, 24);
		return md5Str;
	}
}
