package com.xxm.parking.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

/**
 * 加密工具类
 * 
 * @author 许晓敏
 *
 */
public class KeyUtil {

	/** 利用MD5进行加密 */
	public static String encodeByMd5(String encodeText) {
		try {
			// 创建md5的加密方式
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 使用md5方式对输入值进行加密
			byte[] encodeData = messageDigest.digest(encodeText.getBytes("utf-8"));
			// 使用BASE64Encoder的encode方法，把字节数组转成字符串
			String result = new BASE64Encoder().encode(encodeData);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断用户密码是否正确 newpasswd 用户输入的密码 oldpasswd 正确密码
	 */
	public static boolean checkpassword(String newpasswd, String oldpasswd) {
		if (encodeByMd5(newpasswd).equals(oldpasswd))
			return true;
		else
			return false;
	}
	
}
