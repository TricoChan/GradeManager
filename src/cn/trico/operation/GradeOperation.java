package cn.trico.operation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.trico.entity.HomeworkGrade;
import cn.trico.entity.StudentAccount;
import cn.trico.entity.SumGrade;
import cn.trico.utils.AlertUtils;
import cn.trico.utils.DataBaseConnection;

public class GradeOperation {
	/**
	 * 连接数据库查询成绩信息
	 * 
	 * @param studentNum
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void queryGrade(int studentNum, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Connection conn = DataBaseConnection.getConnection();
		Statement stat = null;
		ResultSet rsHomework = null;
		ResultSet rsSumGrade = null;
		String sqlHomework = "SELECT homework.studentNum,student.studentName,homework.workNum,homework.grade"
				+ " FROM homework,student" + " WHERE homework.studentNum = " + studentNum
				+ " AND homework.studentNum = student.studentNum";
		String sqlSumGrade = "SELECT sumgrade.studentNum,student.studentName,sumgrade.dailyGrade,sumgrade.pageGrade,sumgrade.sumGrade"
				+ " FROM sumgrade,student" + " WHERE sumgrade.studentNum = " + studentNum
				+ " AND sumgrade.studentNum = student.studentNum";
		System.out.println(sqlHomework);
		System.out.println(sqlSumGrade);
		List<HomeworkGrade> hwList  =new ArrayList<HomeworkGrade>();
		List<SumGrade> sgList  =new ArrayList<SumGrade>();
		try {
			stat = conn.createStatement();
			rsHomework = stat.executeQuery(sqlHomework);
			while(rsHomework.next()) {
				HomeworkGrade workGrade = new HomeworkGrade();
				workGrade.setStudentName(rsHomework.getString("studentName"));
				workGrade.setStudentNum(rsHomework.getInt("studentNum"));
				workGrade.setWorkNum(rsHomework.getInt("workNum"));
				workGrade.setSingleGrade(rsHomework.getInt("grade"));
				hwList.add(workGrade);
			}
			//System.out.println("home正常");
			rsSumGrade = stat.executeQuery(sqlSumGrade);
			while(rsSumGrade.next()) {
				SumGrade sumGrade = new SumGrade();
				sumGrade.setStudentName(rsSumGrade.getString("studentName"));
				sumGrade.setStudentNum(rsSumGrade.getInt("studentNum"));
				sumGrade.setDailyGrade(rsSumGrade.getInt("dailyGrade"));
				sumGrade.setPageGrade(rsSumGrade.getInt("pageGrade"));
				sumGrade.setSumGrade(rsSumGrade.getInt("sumGrade"));
				sgList.add(sumGrade);
			}
			//System.out.println("query正常");
			req.setAttribute("hwList", hwList);
			req.setAttribute("sgList", sgList);
			
		} catch (Exception e) {
			System.out.println("ffffff");
			// js输出对话框
			AlertUtils.setAlert("成绩信息未录入！", resp);
			return;
		} finally {
			DataBaseConnection.release(conn, stat, rsHomework);
			DataBaseConnection.release(conn, stat, rsSumGrade);
		}
		System.out.println("aaaaa");
	}
}
