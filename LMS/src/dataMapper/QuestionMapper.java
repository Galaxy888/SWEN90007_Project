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

}
