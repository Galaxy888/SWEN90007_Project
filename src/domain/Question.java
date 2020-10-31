package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;
import mapper.AnswerMapper;
import mapper.QuestionMapper;

public class Question extends DomainObject implements Comparable<Question>{
	private int id;
	private int question_type;
	private String title;
	private String content;
	private String answer;
	private int mark;
	private int exam_id;
	private Timestamp modifiedTime;
	private String modifiedBy;
//	private int version;
	
	private List<Answer> allAnswers;

	public Question(int id, int type, String title, String content, String answer, int mark, int exam_id) {
		this.id = id;
		this.question_type = type;
		this.title = title;
		this.content = content;
		this.answer = answer;
		this.mark = mark;
		this.exam_id = exam_id;
//		this.version = version;
		this.allAnswers = null;
	}

	public Question() {
		this.allAnswers = null;
	}

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

//	public int getVersion() {
//		return version;
//	}
//
//	public void setVersion(int version) {
//		this.version = version;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return question_type;
	}

	public void setType(int type) {
		this.question_type = type;
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
	
	//lazy load
	public List<Answer> getAllAnswers() {
		if(this.allAnswers==null) {
			List<Answer> allAnswers = new ArrayList<>();
			AnswerMapper answerMapper = new AnswerMapper();
			allAnswers = answerMapper.getAllAnswers(this.id);
			this.allAnswers = allAnswers;
		}
		return allAnswers;
	}
	
	public void setAllAnswers( List<Answer> allAnswers) {
		this.allAnswers = allAnswers;
	}
	

	// Update a question info
//	public void updateQuestion(int id, int type, String title, String content, String answer, int mark, int exam_id)
//			throws SQLException {
//		// TODO Auto-generated method stub
//		String sql = "update questions set question_type=?,title=?,content=?,answer=?,mark=?,exam_id=? where id=?";
//		PreparedStatement stmt = DBConnection.prepare(sql);
//		try {
//			stmt.setInt(1, type);
//			stmt.setString(2, title);
//			stmt.setString(3, content);
//			stmt.setString(4, answer);
//			stmt.setInt(5, mark);
//			stmt.setInt(6, exam_id);
//			stmt.setInt(7, id);
//			stmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	// Get all questions
	public List<Question> getAllQuestions(int exam_id) {
		List<Question> questions = new ArrayList<>();
		try {
			String stm = "select * from questions where exam_id='" + exam_id + "'";
			PreparedStatement stmt = DBConnection.prepare(stm);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				int type = Integer.parseInt(rs.getString(2));
				String title = rs.getString(3);
				String content = rs.getString(4);
				String answer = rs.getString(5);
				int mark = Integer.parseInt(rs.getString(6));
				int examId = Integer.parseInt(rs.getString(7));
//				int version = Integer.parseInt(rs.getString(10));
				questions.add(new Question(id, type, title, content, answer, mark, examId));
			}

		} catch (SQLException e) {

		}
		return questions;
	}

	@Override
	public int compareTo(Question o) {
		return this.id-o.id;
	}

	// Insert an new question
//	public int insert() {
//		try {
//			String stm = "INSERT INTO questions VALUES (?, ?, ?,?,?,?,?)";
//			;
//			PreparedStatement insertStatement = DBConnection.prepare(stm);
//			insertStatement.setInt(1, id);
//			insertStatement.setInt(2, question_type);
//			insertStatement.setString(3, title);
//			insertStatement.setString(4, content);
//			insertStatement.setString(5, answer);
//			insertStatement.setInt(6, mark);
//			insertStatement.setInt(7, exam_id);
//			insertStatement.execute();
//		} catch (SQLException e) {
//		}
//		return getId();
//
//	}

	// Delete a question
//	public void deleteQuestion(int id) {
//		// TODO Auto-generated method stub
//		String sql = "delete from questions where id=?";
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