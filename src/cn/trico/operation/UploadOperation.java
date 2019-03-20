package cn.trico.operation;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import cn.trico.utils.AlertUtils;
import cn.trico.utils.DataBaseConnection;

/**
 * 
 * �ϴ�������
 * 
 * ���ϴ��ļ�������д�����ݿ�
 * 
 * @author Trico
 *
 */

public class UploadOperation {
	public static boolean insertFile(int studentNum, int workNum, FileItem item, HttpServletResponse resp)
			throws IOException {
		Connection conn = DataBaseConnection.getConnection();
		PreparedStatement stat = null;
		ResultSet rs = null;
		InputStream in = null;
		String sql = "REPLACE INTO homework(studentNum,workNum,workFile)VALUES(" + studentNum + "," + workNum + ",?)";
		System.out.println(sql);
		System.out.println("000000000");
		try {
			stat = conn.prepareStatement(sql);
			// ����ļ���������Ϊ��1��������ֵΪ������
			in = item.getInputStream();
			stat.setBinaryStream(1, in);
			stat.executeUpdate();
			System.out.println("qqqqqqqqqqq");
		} catch (Exception e) {
			// js����Ի���
			AlertUtils.setPrintWriter("������̷������������ԣ�", resp);
			System.out.println("ppppppp");
			return false;
		} finally {
			DataBaseConnection.release(conn, stat, rs);
			System.out.println("rrrrrrr");
			in.close();
			item.delete();
		}
		return true;
	}
}
