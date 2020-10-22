package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;

public class Answer extends DomainObject {
	private int user_id;
	private int question_id;
	private int exam_id;
	private String answer;
	private int mark;
	private int status;

	public Answer(int id, int question_id, int exam_id, String answer, int mark, int status) {
		this.user_id = id;
		this.question_id = question_id;
		this.exam_id = exam_id;
		this.answer = answer;
		this.mark = mark;
		this.status = status;
	}

	public Answer() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return user_id;
	}

	public void setId(int id) {
		this.user_id = id;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int id) {
		this.question_id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	// Get all answers by question id
//	public static List<Answer> getAllAnswer(int question_id) {
//		List<Answer> answers = new ArrayList<>();
//		try {
//			String stm = "select * from users_questions where question_id='" + question_id + "'";
//			PreparedStatement stmt = DBConnection.prepare(stm);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				int id = Integer.parseInt(rs.getString(1));
//				int q_id = Integer.parseInt(rs.getString(2));
//				String answer = rs.getString(3);
//				int mark = Integer.parseInt(rs.getString(4));
//				int status = Integer.parseInt(rs.getString(5));
//				answers.add(new Answer(id, q_id, answer, mark, status));
//			}
//
//		} catch (SQLException e) {
//
//		}
//		return answers;
//	}

}
