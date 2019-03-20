package cn.trico.operation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.trico.entity.MyNote;
import cn.trico.entity.NoteAll;
import cn.trico.entity.NoteContent;
import cn.trico.utils.AlertUtils;
import cn.trico.utils.DataBaseConnection;

/**
 * 
 * �������ݿ������
 * 
 * @author Trico
 *
 */

public class NoteOperation {
	/**
	 * ��������
	 * 
	 * @param student
	 * @param note
	 * @param resp
	 * @throws IOException
	 */
	public static void insertNote(int studentNum, NoteContent note, HttpServletResponse resp)
			throws IOException {
		Connection conn = DataBaseConnection.getConnection();
		Statement stat = null;
		ResultSet rs = null;
		String sql = "INSERT INTO note (studentNum,question,type,time) VALUES ('" + studentNum + "','"
				+ note.getQuestion() + "','" + note.getType() + "','" + note.getTime() + "')";
		System.out.println(sql);
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("ffffff");
			AlertUtils.setPrintWriter("Error:     ���ݿ�д���쳣��ѧ���ѱ�ע����", resp);
			return;
		} finally {
			DataBaseConnection.release(conn, stat, rs);
		}
	}

	/**
	 * ��ѯ��Ӧѧ�ŵ��������Լ��ظ�
	 * 
	 * @param studentNum
	 * @param req
	 */
	public static void queryOneNote(int studentNum,HttpServletRequest req) {
		Connection conn = DataBaseConnection.getConnection();
		Statement stat = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM note WHERE studentNum = "+studentNum+" ORDER BY time DESC" ;
		List<MyNote> myNoteList = new ArrayList<MyNote>();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				MyNote myNote = new MyNote();
				myNote.setQuestion(rs.getString("question"));
				myNote.setReply(rs.getString("reply"));
				myNote.setTime(rs.getDate("time"));
				myNote.setType(rs.getString("type"));
				myNoteList.add(myNote);
				req.setAttribute("myNoteList", myNoteList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.release(conn, stat, rs);
		}
	}

	/**
	 * 
	 * ��ѯ��������
	 * 
	 * @param req
	 */
	public static void queryAllNote(HttpServletRequest req) {
		Connection conn = DataBaseConnection.getConnection();
		Statement stat = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM note ORDER BY time DESC";
		List<NoteAll> noteAllList = new ArrayList<NoteAll>();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				NoteAll noteAll = new NoteAll();
				noteAll.setContent(rs.getString("question"));
				noteAll.setDate(rs.getDate("time"));
				noteAll.setType(rs.getString("type"));
				noteAllList.add(noteAll);
				req.setAttribute("noteAllList", noteAllList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.release(conn, stat, rs);
		}
	}

	/*
	 * 
	 * ����ظ�
	 * 
	 */
	public static void ReplyNote() {
		Connection conn = DataBaseConnection.getConnection();
		Statement stat = null;
		ResultSet rs = null;
		// String sql = "insert into user(name,password,date)
		// values('"+user.getName()+"','"+user.getPassword()+"',now())";
		try {
			stat = conn.createStatement();
			// stat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.release(conn, stat, rs);
		}
	}
}
