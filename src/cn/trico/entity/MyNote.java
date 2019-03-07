package cn.trico.entity;

import java.util.Date;

/**
 * 
 * 列出所有该学号的留言及回复
 * 
 * @author Trico
 *
 */

public class MyNote {
	String question;
	String type;
	String reply;
	Date time;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
