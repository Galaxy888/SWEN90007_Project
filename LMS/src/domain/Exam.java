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
	
	private List<Question> allQuestions;
	private List<Mark> allMarks;
	

//	private static final String findAllSubjectsStatement = "SELECT * from exams";
//	private static final String insertSubjectStatement = "INSERT INTO exams VALUES (?, ?, ?,?)";

	public Exam(int id, String title, int status, String subject_code) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.subject_code = subject_code;
		this.allQuestions = null;
		this.allMarks = null;
		
		
	}
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

	// Update an exam info
//	public void updateExam(int id, String title, int status, String subject_code) throws SQLException {
//		// TODO Auto-generated method stub
//		String sql = "update exams set title=?,status=?,subject_code=? where id=?";
//		PreparedStatement stmt = DBConnection.prepare(sql);
//		try {
//			stmt.setString(1, title);
//			stmt.setInt(2, status);
//			stmt.setString(3, subject_code);
//			stmt.setInt(4, id);
//			stmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	
	
	
	
	// Get all exams
//	public static List<Exam> getAllExams(String subject_code) {
//		List<Exam> exams = new ArrayList<>();
//		try {
//			String stm = "select * from exams where subject_code='" + subject_code + "'";
//			PreparedStatement stmt = DBConnection.prepare(stm);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				int id = Integer.parseInt(rs.getString(1));
//				String title = rs.getString(2);
//				int status = Integer.parseInt(rs.getString(3));
//				String subject = rs.getString(4);
//				exams.add(new Exam(id, title, status, subject));
//			}
//
//		} catch (SQLException e) {
//
//		}
//		return exams;
//	}

	// Insert an new exam
//	public int insert() {
//		try {
//			PreparedStatement insertStatement = DBConnection.prepare(insertSubjectStatement);
//			insertStatement.setInt(1, id);
//			insertStatement.setString(2, title);
//			insertStatement.setInt(3, status);
//			insertStatement.setString(4, subject_code);
//			insertStatement.execute();
//		} catch (SQLException e) {
//		}
//		return getId();
//
//	}

	// Delete an exam
//	public void deleteExam(int id) {
//		// TODO Auto-generated method stub
//		String sql = "delete from exams where id=? and status = 0";
//		try {
//			PreparedStatement stmt = DBConnection.prepare(sql);
//			stmt.setInt(1, id);
//			stmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
