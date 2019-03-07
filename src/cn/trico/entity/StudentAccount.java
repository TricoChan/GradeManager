package cn.trico.entity;

public class StudentAccount {
	private int studentNum;
	private String password;
	private String studentName;
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
