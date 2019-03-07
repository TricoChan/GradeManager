package cn.trico.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.trico.utils.AlertUtils;
import cn.trico.utils.CookieUtils;

import cn.trico.operation.UploadOperation;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "upload";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 16; // 16MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 20; // 20MB

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	/**
	 * 上传数据及保存文件
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是则停止
			AlertUtils.setPrintWriter("Error: 表单必须包含 enctype=multipart/form-data", response);
			return;
		}
		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 中文处理
		upload.setHeaderEncoding("UTF-8");

		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;

		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			// 解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			FileItem fileItem = null;
			int workNum = 0;
			// System.out.println("1111111");
			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					// System.out.println("222222222");
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						System.out.println(fileName);
						// 获取文件后缀名
						String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
						// System.out.println("333333333333");
						if (!suffix.equals("doc") && !suffix.equals("docx")) {
							// 如果不是则停止
							AlertUtils.setPrintWriter("Error: 上传了非.dox/.docx的文档格式或未选择文件，请重试！", response);
							// System.out.println("4444444444");
							return;
						}
						// System.out.println("555555555");
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(filePath);
						// 保存文件到硬盘
						// item.write(storeFile);
						fileItem = item;

					} else {
						// 处理字段
						if (item.getFieldName().equals("show-num")) {
							workNum = Integer.parseInt(item.getString());
							// System.out.println("66666666");
						}
					}
				}
				// 文件写入数据库
				// System.out.println("77777777");
				// HttpSession session = request.getSession();
				// int studentNum = (int) session.getAttribute("studentNum");
				int studentNum = Integer.parseInt(CookieUtils.getCookieValue("studentNum", request));
				boolean result = UploadOperation.insertFile(studentNum, workNum, fileItem, response);
				if (result == false)
					return;
				request.setAttribute("message", "文件上传成功!");
				// System.out.println("8888888888");
			} else {
				// System.out.println("999999999");
				AlertUtils.setPrintWriter("Error: 未选择文件", response);
				return;
			}
		} catch (Exception ex) {
			System.out.println("1010101010");
			AlertUtils.setPrintWriter("错误信息: " + ex.getMessage() + "作业编号错误", response);
			return;
		}
		// 跳转到 success.jsp
		// System.out.println("zzzzzzzzzz");
		getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
