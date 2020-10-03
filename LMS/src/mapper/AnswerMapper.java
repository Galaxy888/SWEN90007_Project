package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;
import domain.Answer;

public class AnswerMapper {
	
	// Get all answers by question id
	public List<Answer> getAllAnswers(int question_id) {
		List<Answer> answers = new ArrayList<>();
		try {
			String stm = "select * from users_questions where question_id='" + question_id + "'";
			PreparedStatement stmt = DBConnection.prepare(stm);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				int q_id = Integer.parseInt(rs.getString(2));
				String answer = rs.getString(3);
				int mark = Integer.parseInt(rs.getString(4));
				int status = Integer.parseInt(rs.getString(5));
				answers.add(new Answer(id, q_id, answer, mark, status));
			}

		} catch (SQLException e) {

		}
		return answers;
	}

}
