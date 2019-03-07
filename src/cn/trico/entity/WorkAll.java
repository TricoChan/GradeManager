package cn.trico.entity;

import java.util.Date;

public class WorkAll {
	int workNum;
	String workContent;
	String deadLine;
	Date time;
	
	public int getWorkNum() {
		return workNum;
	}
	public void setWorkNum(int workNum) {
		this.workNum = workNum;
	}
	public String getWorkContent() {
		return workContent;
	}
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
