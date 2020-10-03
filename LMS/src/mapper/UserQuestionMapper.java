package mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import datasource.DBConnection;
import domain.DomainObject;
import domain.UserQuestion;

public class UserQuestionMapper extends DataMapper {
	
	/**
	 * 
	 * @param obj = exam to be inserted
	 * @return indication whether the insert is successful or not
	 */
	@Override
	public Boolean insert(DomainObject obj) {
		UserQuestion userQuestion = (UserQuestion) obj;

		String stm = "INSERT INTO users_questions VALUES (?, ?, ?,0,0)";

		try {
			PreparedStatement stmt = DBConnection.prepare(stm);
			stmt.setInt(1, userQuestion.getUser_id());
			stmt.setInt(2, userQuestion.getQuestion_id());
			stmt.setString(3, userQuestion.getAnswer());
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
