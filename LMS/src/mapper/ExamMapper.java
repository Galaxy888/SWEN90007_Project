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
import pessimistic.LockManagerEX;

public class ExamMapper extends DataMapper {

	/**
	 * 
	 * @param obj = exam to be inserted
	 * @return indication whether the insert is successful or not
	 */
	@Override
	public Boolean insert(DomainObject obj) {
		Exam exam = (Exam) obj;

//		String insertExamStatement = "INSERT INTO exams (title,status,subject_code,version) VALUES (?, ?,?,?)";
		String insertExamStatement = "INSERT INTO exams (title,status,subject_code) VALUES (?, ?,?)";


		try {
			PreparedStatement stmt = DBConnection.prepare(insertExamStatement);
			//stmt.setInt(1, exam.getId());
			stmt.setString(1, exam.getTitle());
			stmt.setInt(2, exam.getStatus());
			stmt.setString(3, exam.getSubject());
//			stmt.setInt(4, exam.getVersion());
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

		String deleteExamStatement = "delete from exams where id=?";

		try {
			PreparedStatement stmt = DBConnection.prepare(deleteExamStatement);
			stmt.setInt(1, exam.getId());
			System.out.println("ExamMapper delete");
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

//		String updateExamStatement = "update exams set title=?,status=?,subject_code=?, version=? where id=? and version=?";
		String updateExamStatement = "update exams set title=?,status=?,subject_code=? where id=? ";
		
//		LockManagerEX.getInstance().acquireLock(exam.getId(), Thread.currentThread().getName());

		PreparedStatement stmt = DBConnection.prepare(updateExamStatement);
		try {
			stmt.setString(1, exam.getTitle());
			stmt.setInt(2, exam.getStatus());
			stmt.setString(3, exam.getSubject());
//			stmt.setInt(4,exam.getVersion()+1);
			stmt.setInt(4, exam.getId());
//			stmt.setInt(6,exam.getVersion());
			int rowCount = stmt.executeUpdate();
			if (rowCount==0) {
				System.out.println("Exam throwConcurrencyException");
				return false;
			}
			stmt.close();
//			LockManagerEX.getInstance().releaseLock(exam.getId(), Thread.currentThread().getName());
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
//				int version = Integer.parseInt(rs.getString(5));
				exams.add(new Exam(id, title, status, subject));
			}

		} catch (SQLException e) {

		}
		Collections.sort(exams);
		return exams;
	}
	
	
	public int getExamStatus(DomainObject obj) {
		Exam exam = (Exam) obj;
		int status=1;

		try {
			String stm = "select status from exams where id='" + exam.getId() + "'";
			PreparedStatement stmt = DBConnection.prepare(stm);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				 status = Integer.parseInt(rs.getString(1));

			}

		} catch (SQLException e) {

		}

		return status;
	}

}
