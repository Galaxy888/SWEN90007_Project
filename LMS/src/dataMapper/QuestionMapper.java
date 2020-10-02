package dataMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		Question question = (Question) obj;
		
	    String insertQuestionStatement = "INSERT INTO questions VALUES (?, ?, ?,?,?,?,?)";
	    
		try {
			PreparedStatement stmt = DBConnection.prepare(insertQuestionStatement);
			stmt.setInt(1,question.getId());
			stmt.setInt(2, question.getType());
			stmt.setString(3, question.getTitle());
			stmt.setString(4, question.getContent());
			stmt.setString(5,question.getAnswer());
			stmt.setInt(6,question.getMark());
			stmt.setInt(7, question.getExam());
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
		
		System.out.println("QuestionMapper update");
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
	

}
