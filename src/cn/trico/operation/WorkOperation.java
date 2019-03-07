package cn.trico.operation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.trico.entity.WorkAll;
import cn.trico.utils.DataBaseConnection;

/**
 * 
 * 公告功能数据库相关操作类
 * 
 * @author Trico
 *
 */
public class WorkOperation {
	public static void queryAllWork(HttpServletRequest req) {
		Connection conn = DataBaseConnection.getConnection();
		Statement stat = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM assignment ORDER BY time DESC";
		List<WorkAll> workAllList = new ArrayList<WorkAll>();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				WorkAll workAll = new WorkAll();
				workAll.setWorkNum(rs.getInt("workNum"));
				workAll.setWorkContent(rs.getString("workContent"));
				workAll.setDeadLine(rs.getString("deadline"));
				workAll.setTime(rs.getDate("time"));
				workAllList.add(workAll);
				req.setAttribute("workAllList", workAllList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.release(conn, stat, rs);
		}
	}
}
