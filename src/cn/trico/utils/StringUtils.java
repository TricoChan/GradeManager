package cn.trico.utils;
/**
 * 
 * �ַ��������࣬����utf-8��ʽ��ת��
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
