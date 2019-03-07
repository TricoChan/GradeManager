

package cn.trico.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.trico.utils.CookieUtils;
import cn.trico.operation.GradeOperation;

/**
 * 
 * ≤È—Ø≥…º®Servlet
 * 
 * @author Trico
 *
 */
public class GradeQueryServlet extends HttpServlet {
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
		//int studentNum = Integer.parseInt(session.getAttribute("studentNum"));
		//int studentNum = (int) session.getAttribute("studentNum");
		
		int studentNum = Integer.parseInt(CookieUtils.getCookieValue("studentNum", req));
		GradeOperation.queryGrade(studentNum, req, resp);
		req.getRequestDispatcher("grade-query.jsp").forward(req, resp);
	}
}
