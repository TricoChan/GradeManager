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

	// �ϴ��ļ��洢Ŀ¼
	private static final String UPLOAD_DIRECTORY = "upload";

	// �ϴ�����
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 16; // 16MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 20; // 20MB

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	/**
	 * �ϴ����ݼ������ļ�
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����Ƿ�Ϊ��ý���ϴ�
		if (!ServletFileUpload.isMultipartContent(request)) {
			// ���������ֹͣ
			AlertUtils.setPrintWriter("Error: ��������� enctype=multipart/form-data", response);
			return;
		}
		// �����ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// ������ʱ�洢Ŀ¼
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// ��������ļ��ϴ�ֵ
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// �����������ֵ (�����ļ��ͱ�����)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// ���Ĵ���
		upload.setHeaderEncoding("UTF-8");

		// ������ʱ·�����洢�ϴ����ļ�
		// ���·����Ե�ǰӦ�õ�Ŀ¼
		String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;

		// ���Ŀ¼�������򴴽�
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			// ���������������ȡ�ļ�����
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			FileItem fileItem = null;
			int workNum = 0;
			// System.out.println("1111111");
			if (formItems != null && formItems.size() > 0) {
				// ����������
				for (FileItem item : formItems) {
					// �����ڱ��е��ֶ�
					// System.out.println("222222222");
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						System.out.println(fileName);
						// ��ȡ�ļ���׺��
						String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
						// System.out.println("333333333333");
						if (!suffix.equals("doc") && !suffix.equals("docx")) {
							// ���������ֹͣ
							AlertUtils.setPrintWriter("Error: �ϴ��˷�.dox/.docx���ĵ���ʽ��δѡ���ļ��������ԣ�", response);
							// System.out.println("4444444444");
							return;
						}
						// System.out.println("555555555");
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						// �ڿ���̨����ļ����ϴ�·��
						System.out.println(filePath);
						// �����ļ���Ӳ��
						// item.write(storeFile);
						fileItem = item;

					} else {
						// �����ֶ�
						if (item.getFieldName().equals("show-num")) {
							workNum = Integer.parseInt(item.getString());
							// System.out.println("66666666");
						}
					}
				}
				// �ļ�д�����ݿ�
				// System.out.println("77777777");
				// HttpSession session = request.getSession();
				// int studentNum = (int) session.getAttribute("studentNum");
				int studentNum = Integer.parseInt(CookieUtils.getCookieValue("studentNum", request));
				boolean result = UploadOperation.insertFile(studentNum, workNum, fileItem, response);
				if (result == false)
					return;
				request.setAttribute("message", "�ļ��ϴ��ɹ�!");
				// System.out.println("8888888888");
			} else {
				// System.out.println("999999999");
				AlertUtils.setPrintWriter("Error: δѡ���ļ�", response);
				return;
			}
		} catch (Exception ex) {
			System.out.println("1010101010");
			AlertUtils.setPrintWriter("������Ϣ: " + ex.getMessage() + "��ҵ��Ŵ���", response);
			return;
		}
		// ��ת�� success.jsp
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
