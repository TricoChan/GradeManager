package cn.trico.utils;
/**
 * 
 * 字符串工具类，处理utf-8格式的转码
 * 
 * @author Trico
 *
 */
public class StringUtils {
	public static String handleString(String string) {
		try {
			byte bb[] = string.getBytes("iso-8859-1");
			string = new String(bb,"utf-8");
		}catch(Exception e) {}
		return string;
	}
}
