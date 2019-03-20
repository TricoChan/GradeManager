package cn.trico.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���ݿ����Ӵ�����
 * 
 * @author Trico
 *
 */
public class DataBaseConnection {
		//mysql����
		public static String driver = "com.mysql.cj.jdbc.Driver";
		//mysql���ݿ������ip��ַ���˿ںš����ݿ�ʵ��
		public static String url = "jdbc:mysql://localhost:3306/grademanager?serverTimezone=GMT&charsetEncoding=gb2312";
		//���ݿ��û���
		public static String username = "GradeManager";
		//���ݿ��û�����
		public static String password = "1";
		//�����ݿ⽨������
		public static Connection getConnection(){
			Connection conn = null;
			try {
				Class.forName(driver);
				try {
					conn = DriverManager.getConnection(url,username,password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
		}
		//�ͷ����ݿ�����
		public static void release(Connection conn,Statement stat,ResultSet rs){
			try{
				if(conn!=null){
					conn.close();
					conn = null;
				}
				if(stat!=null){
					stat.close();
					stat = null;
				}
				if(rs!=null){
					rs.close();
					rs = null;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
