package cn.trico.operation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.trico.utils.AlertUtils;
import cn.trico.utils.CookieUtils;
import cn.trico.utils.DataBaseConnection;

/**
 * 
 * 账户验证方法类
 * 
 * @author Trico
 *
 */
public class AccountOperation {
	/**
	 * 登录逻辑
	 * 
	 * @param studentNum
	 * @param password
	 * @param response
	 */
	public static void loginRequest(int studentNum, String password, HttpServletResponse response,
			HttpServletRequest request) {
		Connection conn = DataBaseConnection.getConnection();
		Statement stat = null;
		ResultSet rs = null;
		String sql = "SELECT password FROM student WHERE studentNum = " + studentNum;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				String pwd = rs.getString("password");
				System.out.println(pwd);
				if (password.equals(pwd)) {
					// System.out.println("session1");
					// HttpSession session = request.getSession();
					// session.removeAttribute("studentNum");
					// session.invalidate();
					// System.out.println("session2");
					// 把学号保存在session对象中
					// session.setAttribute("studentNum", studentNum);
					// System.out.println("session已存储");

					// 创建 Cookie
					CookieUtils.addCookie("studentNum", Integer.toString(studentNum), request, response);
					AlertUtils.setPrintWriter("success", response);
				} else {
					AlertUtils.setPrintWriter("notmatch", response);
				}
			} else {
				AlertUtils.setPrintWriter("error", response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.release(conn, stat, rs);
		}
	}

	/**
	 * 注册逻辑
	 * 
	 * @param studentNum
	 * @param password
	 * @param response
	 * @throws IOException
	 */
	public static void registerRequest(int studentNum, String password, HttpServletResponse response)
			throws IOException {
		Connection conn = DataBaseConnection.getConnection();
		Statement stat = null;
		ResultSet rs = null;
		String sql = "UPDATE student SET password = " + password + " WHERE studentNum = " + studentNum;
		try {
			stat = conn.createStatement();
			if (stat.executeUpdate(sql) == 1) {
				AlertUtils.setPrintWriter("correct", response);
			}
			AlertUtils.setPrintWriter("exist", response);
		} catch (Exception e) {
			AlertUtils.setPrintWriter("exist", response);
			e.printStackTrace();
		} finally {
			DataBaseConnection.release(conn, stat, rs);
		}
	}
}
