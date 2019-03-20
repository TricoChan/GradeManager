package cn.trico.operation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.trico.utils.AlertUtils;
import cn.trico.utils.DataBaseConnection;

/**
 * 
 * Ԥ��������� ����index.htmlʱͨ��ajaxԤ�ȼ������ݲ�����չʾ
 * 
 * @author Trico
 *
 */

public class PreFetchOperation {
	public static void queryNum(HttpServletRequest req, HttpServletResponse resp) {
		Connection conn = DataBaseConnection.getConnection();
		Statement stat = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String sql1 = "SELECT DISTINCT workNum FROM assignment";
		String sql2 = "SELECT * FROM assignment ORDER BY time DESC LIMIT 3";
		String data = null;
		try {
			stat = conn.createStatement();
			rs1 = stat.executeQuery(sql1);
			while (rs1.next()) {
				data += "<option value=" + rs1.getString("workNum") + ">" + rs1.getString("workNum") + "</option>";
			}
			// �ָ���
			data += "  ";

			rs2 = stat.executeQuery(sql2);
			while (rs2.next()) {
				data += "����: " + Integer.toString(rs2.getInt("workNum")) + "<br>" + "����: "
						+ rs2.getString("workContent") + "<br>" + "ʱ��: " + rs2.getDate("time") + "<br>" + "��ֹ: "
						+ rs2.getString("deadline");
				data += "  ";
			}
			AlertUtils.setPrintWriter(data, resp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.release(conn, stat, rs1);
			DataBaseConnection.release(conn, stat, rs2);
		}
	}
}
