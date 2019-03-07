package cn.trico.entity;
/**
 * 查询SumGrade表成绩对应的实体类
 * @author Trico
 *
 */
public class SumGrade {
	int studentNum;
	String studentName;
	int dailyGrade;
	int pageGrade;
	int sumGrade;
	
	public int getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getDailyGrade() {
		return dailyGrade;
	}
	public void setDailyGrade(int dailyGrade) {
		this.dailyGrade = dailyGrade;
	}
	public int getPageGrade() {
		return pageGrade;
	}
	public void setPageGrade(int pageGrade) {
		this.pageGrade = pageGrade;
	}
	public int getSumGrade() {
		return sumGrade;
	}
	public void setSumGrade(int sumGrade) {
		this.sumGrade = sumGrade;
	}

	
}
