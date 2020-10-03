package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;
import domain.DomainObject;
import domain.Exam;
import domain.Question;

public class QuestionMapper extends DataMapper{
	
	
	/**
	 * 
	 * @param obj = question to be inserted
	 * @return indication whether the insert is successful or not
	 */
	@Override
	public Boolean insert(DomainObject obj) {
	    String insertQuestionStatement = "INSERT INTO questions(question_type,title,content,answer,mark,exam_id) VALUES (?,?,?,?,?,?)";
	    Question question = (Question) obj;
		try {
			PreparedStatement stmt = DBConnection.prepare(insertQuestionStatement);
			stmt.setInt(1, question.getType());
			stmt.setString(2, question.getTitle());
			stmt.setString(3, question.getContent());
			stmt.setString(4,question.getAnswer());
			stmt.setInt(5,question.getMark());
			stmt.setInt(6, question.getExam());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
//		finally {
//			DBConnection.closeConnection()
//		}

	}
	
	
	/**
	 * 
	 * @param obj = question to be updated
	 * @return indication whether the update is successful or not
	 * @throws SQLException
	 */
	@Override
	public Boolean update(DomainObject obj) throws SQLException {
		
		Question question = (Question) obj;
		
		String updateQuestionStatement = "update questions set question_type=?,title=?,content=?,answer=?,mark=?,exam_id=? where id=?";
		
		PreparedStatement stmt = DBConnection.prepare(updateQuestionStatement);
		try {			
			stmt.setInt(1, question.getType());
			stmt.setString(2,question.getTitle());
			stmt.setString(3, question.getContent());
			stmt.setString(4, question.getAnswer());
			stmt.setInt(5, question.getMark());
			stmt.setInt(6, question.getExam());
			stmt.setInt(7,question.getId());
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
//		finally {
//		DBConnection.closeConnection()
//	}
	}
	
	
	
	/**
	 * 
	 * @param obj= question to be deleted
	 * @return indication whether the delete is successful or not
	 */
	@Override
	public Boolean delete(DomainObject obj) {
		Question question = (Question) obj;

		String deleteQuestionStatement =  "delete from questions where id=?";
		
		try {
			PreparedStatement stmt = DBConnection.prepare(deleteQuestionStatement);
			stmt.setInt(1, question.getId());
			System.out.println("QuestionMapper delete ");
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
//		finally {
//		DBConnection.closeConnection()
//	}
		return true;
		
	}
	
	
	//Get all questions
	public List<Question> getAllQuestions(int exam_id) {
        List<Question> questions = new ArrayList<>();
        try {
        	String stm = "select * from questions where exam_id='"+exam_id+"'";
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
				questions.add(new Question(id,type,title,content,answer,mark,examId));
			}
	
		} catch (SQLException e) {
	
		}
        return questions;
    }
	

}
