package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;
import mapper.ExamMapper;

public class Subject extends DomainObject {

	private String subjectCode;
	private String name;
	
	private List<Exam> allExams;
	private List<Exam> allStudentExams;

	private static final String findAllSubjectsStatement = "SELECT * from subjects";
	private static final String insertSubjectStatement = "INSERT INTO subjects VALUES (?, ?)";

	public Subject(String subjectCode, String name) {
		this.subjectCode = subjectCode;
		this.name = name;
		this.allExams = null;
		this.allStudentExams = null;
	}

	public Subject() {
		this.allExams = null;
		this.allStudentExams = null;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	//lazy load
	public List<Exam> getAllExams() {
		if(this.allExams==null) {
			List<Exam> allExams = new ArrayList<>();
			ExamMapper examMapper = new ExamMapper();
			allExams = examMapper.getAllExams(this.subjectCode);
			this.allExams = allExams;
		}
		return allExams;
	}
	
	public void setAllExams( List<Exam> allExams) {
		this.allExams = allExams;
	}
	
	
	//lazy load
//	public Map<Integer, List<Exam>> getAllStudentExams() {
//		if(this.allStudentExams==null) {
//			List<Exam> allStudentExams = new ArrayList<>();
//			ExamMapper examMapper = new ExamMapper();
//			result = 
//			allStudentExams = examMapper.allStudentExams(this.subjectCode);
//			this.allStudentExams = allStudentExams;
//		}
//		return allStudentExams;
//	}
//	
//	public void setAllStudentExams( List<Exam> allStudentExams) {
//		this.allStudentExams = allStudentExams;
//	}

	public static List<Subject> getAllSubjects() {
	List<Subject> subjects = new ArrayList<>();
		try {
		PreparedStatement stmt = DBConnection.prepare(findAllSubjectsStatement);

			ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String code = rs.getString(1);
			String name = rs.getString(2);
			subjects.add(new Subject(code, name));
		}

	} catch (SQLException e) {

}
	return subjects;
	}
	

	

	public String insert() {
		try {
			PreparedStatement insertStatement = DBConnection.prepare(insertSubjectStatement);
			insertStatement.setString(1, subjectCode);
			insertStatement.setString(2, name);
			insertStatement.execute();
		} catch (SQLException e) {
		}
		return getSubjectCode();

	}

}
