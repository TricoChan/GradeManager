package cn.trico.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接处理类
 * 
 * @author Trico
 *
 */
public class DataBaseConnection {
		//mysql驱动
		public static String driver = "com.mysql.cj.jdbc.Driver";
		//mysql数据库服务器ip地址、端口号、数据库实例
		public static String url = "jdbc:mysql://localhost:3306/grademanager?serverTimezone=GMT&charsetEncoding=gb2312";
		//数据库用户名
		public static String username = "GradeManager";
		//数据库用户口令
		public static String password = "1";
		//与数据库建立连接
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
		//释放数据库连接
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
