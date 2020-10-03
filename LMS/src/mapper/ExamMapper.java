package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import datasource.DBConnection;
import domain.DomainObject;
import domain.Exam;

public class ExamMapper extends DataMapper {

	/**
	 * 
	 * @param obj = exam to be inserted
	 * @return indication whether the insert is successful or not
	 */
	@Override
	public Boolean insert(DomainObject obj) {
		Exam exam = (Exam) obj;

		String insertExamStatement = "INSERT INTO exams (title,status,subject_code) VALUES (?, ?,?)";

		try {
			PreparedStatement stmt = DBConnection.prepare(insertExamStatement);
			//stmt.setInt(1, exam.getId());
			stmt.setString(1, exam.getTitle());
			stmt.setInt(2, exam.getStatus());
			stmt.setString(3, exam.getSubject());
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
	 * @param obj= exam to be deleted
	 * @return indication whether the delete is successful or not
	 */
	@Override
	public Boolean delete(DomainObject obj) {
		Exam exam = (Exam) obj;

		String deleteExamStatement = "delete from exams where id=? and status = 0";

		try {
			PreparedStatement stmt = DBConnection.prepare(deleteExamStatement);
			stmt.setInt(1, exam.getId());
			System.out.println("ExamMapper delete ");
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

	/**
	 * 
	 * @param obj = exam to be updated
	 * @return indication whether the update is successful or not
	 * @throws SQLException
	 */
	@Override
	public Boolean update(DomainObject obj) throws SQLException {
		Exam exam = (Exam) obj;

		String updateExamStatement = "update exams set title=?,status=?,subject_code=? where id=?";

		PreparedStatement stmt = DBConnection.prepare(updateExamStatement);
		try {
			stmt.setString(1, exam.getTitle());
			stmt.setInt(2, exam.getStatus());
			stmt.setString(3, exam.getSubject());
			stmt.setInt(4, exam.getId());
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

	public List<Exam> getAllExams(String subject_code) {

		List<Exam> exams = new ArrayList<>();
		try {
			String stm = "select * from exams where subject_code='" + subject_code + "'";
			PreparedStatement stmt = DBConnection.prepare(stm);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				String title = rs.getString(2);
				int status = Integer.parseInt(rs.getString(3));
				String subject = rs.getString(4);
				exams.add(new Exam(id, title, status, subject));
			}

		} catch (SQLException e) {

		}
		Collections.sort(exams);
		return exams;
	}

}
