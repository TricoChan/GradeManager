package cn.trico.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.trico.operation.AccountOperation;
import cn.trico.utils.AlertUtils;

/**
 * 
 * 登录验证servlet
 * 
 * @author Trico
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//System.out.println("还哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或");
			int studentNum = Integer.parseInt(request.getParameter("studentNum"));
			System.out.println(request.getParameter("studentNum"));
			String password = request.getParameter("password");
			System.out.println(password);
			AccountOperation.loginRequest(studentNum, password, response, request);
		} catch (NumberFormatException e) {
			AlertUtils.setPrintWriter("number", response);
		}
	}

}
