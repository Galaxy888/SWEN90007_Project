package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;
import mapper.MarkMapper;
import mapper.QuestionMapper;
import mapper.UserMapper;

public class Exam extends DomainObject implements Comparable<Exam>{
	private int id;
	private String title;
	private int status;
	private String subject_code;
//	private int version;
	
	private List<Question> allQuestions;
	private List<Mark> allMarks;
	


	public Exam(int id, String title, int status, String subject_code) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.subject_code = subject_code;
//		this.version = version;
		this.allQuestions = null;
		this.allMarks = null;
		
		
	}
//	public int getVersion() {
//		return version;
//	}
//	public void setVersion(int version) {
//		this.version = version;
//	}
	public Exam(String title, int status, String subject_code) {
		this.title = title;
		this.status = status;
		this.subject_code = subject_code;
		this.allQuestions = null;
		this.allMarks = null;
		
		
	}

	public Exam() {
		this.allQuestions = null;
		this.allMarks = null;
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSubject() {
		return subject_code;
	}

	public void setSubject(String subject_code) {
		this.subject_code = subject_code;
	}
	
	//lazy load
	public List<Question> getAllQuestions() {
		if(this.allQuestions==null) {
			List<Question> allQuestions = new ArrayList<>();
			QuestionMapper questionMapper = new QuestionMapper();
			allQuestions = questionMapper.getAllQuestions(this.id);
			this.allQuestions = allQuestions;
		}
		return allQuestions;
	}
	
	public void setAllQuestions( List<Question> allQuestions) {
		this.allQuestions = allQuestions;
	}
	
	
	//lazy load
	public List<Mark> getAllMarks() {
		if(this.allMarks==null) {
			List<Mark> allMarks = new ArrayList<>();
			MarkMapper markMapper = new MarkMapper();
			allMarks = markMapper.getAllMarks(this.id);
			this.allMarks = allMarks;
		}
		return allMarks;
	}
	
	public void setAllMarks( List<Mark> allMarks) {
		this.allMarks = allMarks;
	}
	
	
	@Override
	public int compareTo(Exam o) {
		return this.id-o.id;
	}


}
