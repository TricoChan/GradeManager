package cn.trico.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.trico.utils.CookieUtils;
import cn.trico.operation.NoteOperation;

/**
 * 
 * 列出我的留言请求Servlet
 * 
 * @author Trico
 *
 */

public class MyNoteServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = req.getSession();
		//int studentNum = (int) session.getAttribute("studentNum");
		//System.out.println(studentNum);
		int studentNum = Integer.parseInt(CookieUtils.getCookieValue("studentNum", req));
		NoteOperation.queryOneNote(studentNum, req);
		req.getRequestDispatcher("my-note.jsp").forward(req, resp);
	}

}
