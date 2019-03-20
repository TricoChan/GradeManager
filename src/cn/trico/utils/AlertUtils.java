package cn.trico.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * ��ӻ�д��Ϣ��
 * 
 * @author Trico
 *
 */
public class AlertUtils {
	/**
	 * 
	 *  ------���� 
	 * 
	 * ����alert
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
	 * ------���� 
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
	 * printwriterд����Ϣ
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
