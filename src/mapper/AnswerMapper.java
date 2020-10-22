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
	public static List<Answer> getAllAnswer(int question_id,int user_id) {
		List<Answer> answers = new ArrayList<>();
		try {
			String stm = "select * from users_questions where question_id='" + question_id + "' and user_id='"+user_id+"' ";
			PreparedStatement stmt = DBConnection.prepare(stm);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				int q_id = Integer.parseInt(rs.getString(2));
				int e_id = Integer.parseInt(rs.getString(3));
				String answer = rs.getString(4);
				int mark = Integer.parseInt(rs.getString(5));
				int status = Integer.parseInt(rs.getString(6));
				answers.add(new Answer(id, q_id, e_id,answer, mark, status));
			}

		} catch (SQLException e) {

		}
		return answers;
	}
	// Get all answers by question id
	public static List<Answer> getAllAnswers(int question_id) {
		List<Answer> answers = new ArrayList<>();
		try {
			String stm = "select * from users_questions where question_id='" + question_id + "'";
			PreparedStatement stmt = DBConnection.prepare(stm);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				int q_id = Integer.parseInt(rs.getString(2));
				int e_id = Integer.parseInt(rs.getString(3));
				String answer = rs.getString(4);
				int mark = Integer.parseInt(rs.getString(5));
				int status = Integer.parseInt(rs.getString(6));
				answers.add(new Answer(id, q_id, e_id,answer, mark, status));
			}

		} catch (SQLException e) {

		}
		return answers;
	}

}
