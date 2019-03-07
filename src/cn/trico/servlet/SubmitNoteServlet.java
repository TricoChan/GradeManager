package cn.trico.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.trico.entity.NoteContent;
import cn.trico.utils.CookieUtils;
import cn.trico.utils.StringUtils;

import cn.trico.operation.NoteOperation;

/**
 * Servlet implementation class SubmitNoteServlet
 */
@WebServlet("/SubmitNoteServlet")
public class SubmitNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitNoteServlet() {
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
		NoteContent note = new NoteContent();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		String selectType = request.getParameter("note-selectType").trim();
		String content = request.getParameter("note-content").trim();
		selectType = StringUtils.handleString(selectType);
		content = StringUtils.handleString(content);
		/*
		 * if (selectType == null || selectType.length() == 0) { // js输出对话框
		 * AlertUtils.setPrintWriter("Error:    未选择类别，请重试！", response);
		 * System.out.println("bbbbbb"); return; } if (content == null ||
		 * content.length() == 0) { // js输出对话框 PrintWriter out = resp.getWriter();
		 * AlertUtils.setPrintWriter("Error:    未输入留言！", response);
		 * System.out.println("cccccc"); return; }
		 */
		note.setQuestion(content);
		note.setType(selectType);
		note.setTime(time);
		// HttpSession session = request.getSession();
		// int studentNum = (int) session.getAttribute("studentNum");
		int studentNum = Integer.parseInt(CookieUtils.getCookieValue("studentNum", request));
		NoteOperation.insertNote(studentNum, note, response);
		request.setAttribute("message", "留言提交成功");
		request.getRequestDispatcher("success.jsp").forward(request, response);
	}

}
