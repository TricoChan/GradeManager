package cn.trico.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 添加回写信息类
 * 
 * @author Trico
 *
 */
public class AlertUtils {
	/**
	 * 
	 *  ------弃用 
	 * 
	 * 设置alert
	 * @param string
	 * @param resp
	 */
	public static void setAlert(String string,HttpServletResponse resp) {
		string = StringUtils.handleString(string);
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.println("<script>alert(decodeURIComponent('"+string+"'));history.back();</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.close();
		}
	}
	/**
	 * ------弃用 
	 * 
	 * @param string
	 * @param resp
	 */
	public static void setPage(String string,HttpServletResponse resp) {
		string = StringUtils.handleString(string);
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.println("<script>alert(decodeURIComponent('"+string+"'));history.back();</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.close();
		}
	}
	/**
	 * printwriter写回信息
	 * 
	 * @param string
	 * @param response
	 * @throws IOException
	 */
	public static void setPrintWriter(String string,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(string);
		writer.flush();
		writer.close();
	}
}
