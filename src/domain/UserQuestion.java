package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;

public class UserQuestion extends DomainObject {
	private int user_id;
	private int question_id;
	private int exam_id;
	private String answer;
	private int mark;
	private int status;
	private int version;
	
	public UserQuestion(int id, int question_id,int exam_id, String answer,int mark,int status) {
		this.user_id = id;
		this.question_id = question_id;
		this.exam_id = exam_id;
		this.answer = answer;
		this.mark = mark;
		this.status = status;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public UserQuestion() {
		// TODO Auto-generated constructor stub
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getAnswer() {
		return answer;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public static ArrayList<UserQuestion> getAllQuestionsbyId(int user_id, int exam_id) {
		ArrayList<UserQuestion> questions = new ArrayList<>();
		try {
//			String stm = "Select * from users_questions where user_id ='" + user_id + "'";
			String stm = "Select * from users_questions where user_id ='" + user_id + "'and exam_id= '"+exam_id+"'";
			PreparedStatement stmt = DBConnection.prepare(stm);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				int quesiton_id = Integer.parseInt(rs.getString(2));
				int exam = Integer.parseInt(rs.getString(3));
				String answer = rs.getString(4);
				int mark = Integer.parseInt(rs.getString(5));
				int status = Integer.parseInt(rs.getString(6));
				questions.add(new UserQuestion(id,quesiton_id,exam,answer,mark,status));
				
			}

		} catch (SQLException e) {

		}
		return questions;
	}


}
