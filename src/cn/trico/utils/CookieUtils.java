package cn.trico.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * cookie���߷�����
 * 
 * @author Trico
 *
 */

public class CookieUtils {
	/**
	 * ���cookie
	 * 
	 * @param key
	 * @param value
	 */
	public static void addCookie(String key, String value, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = null;
		//cookies���Ը��ǣ�����Ҫɾ����cookies
		/*
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			// ѭ������cookies
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				System.out.println(cookie.getName());
				if ((cookie.getName()).compareTo(key) == 0) {
					// ɾ���ɵ�cookie
					cookie.setMaxAge(0);
				}
			}
		}
		*/
		// ������cookie
		cookie = new Cookie(key, value);
		// ���ù�������Ϊ 7��
		cookie.setMaxAge(7 * 60 * 60 * 24);
		response.addCookie(cookie);
	}

	/**
	 * ���cookieֵ
	 * 
	 * @param key
	 */
	public static String getCookieValue(String key, HttpServletRequest request) {
		Cookie cookie = null;
		Cookie[] cookies = null;
		// ��ȡ�������ص� Cookie ������
		cookies = request.getCookies();
		if (cookies != null) {
			// ѭ������cookies
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				System.out.println(cookie.getName());
				if ((cookie.getName()).compareTo(key) == 0) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
