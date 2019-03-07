package cn.trico.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * cookie工具方法类
 * 
 * @author Trico
 *
 */

public class CookieUtils {
	/**
	 * 添加cookie
	 * 
	 * @param key
	 * @param value
	 */
	public static void addCookie(String key, String value, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = null;
		//cookies可以覆盖，不需要删除旧cookies
		/*
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			// 循环遍历cookies
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				System.out.println(cookie.getName());
				if ((cookie.getName()).compareTo(key) == 0) {
					// 删除旧的cookie
					cookie.setMaxAge(0);
				}
			}
		}
		*/
		// 设置新cookie
		cookie = new Cookie(key, value);
		// 设置过期日期为 7天
		cookie.setMaxAge(7 * 60 * 60 * 24);
		response.addCookie(cookie);
	}

	/**
	 * 获得cookie值
	 * 
	 * @param key
	 */
	public static String getCookieValue(String key, HttpServletRequest request) {
		Cookie cookie = null;
		Cookie[] cookies = null;
		// 获取与该域相关的 Cookie 的数组
		cookies = request.getCookies();
		if (cookies != null) {
			// 循环遍历cookies
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
