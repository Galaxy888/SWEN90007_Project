package domain;

import java.sql.*;
import java.util.*;


import datasource.DBConnection;

public class Question {
	private int id;
	private int type;
	private String title;
	private String content;
	private String answer;
	private int mark;
    private int exam_id;    

   	public Question() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
   
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}


	public int getExam() {
		return exam_id;
	}

	public void setExam(int exam_id) {
		this.exam_id = exam_id;
	}
}