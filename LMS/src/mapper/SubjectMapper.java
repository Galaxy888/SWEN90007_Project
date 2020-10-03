package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;
import domain.Subject;

public class SubjectMapper extends DataMapper {
	
	
	public static List<Subject> getAllSubjects(int id) {
		List<Subject> subjects = new ArrayList<>();
		try {
			String stm = "Select * from subjects where coordinator_id ='" + id + "'";
			PreparedStatement stmt = DBConnection.prepare(stm);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				int coordinator = Integer.parseInt(rs.getString(3));
				subjects.add(new Subject(code, name, coordinator));
			}

		} catch (SQLException e) {

		}
		return subjects;
	}
	
	
	public static List<Subject> getAllStudentSubjects(int id) {
		List<Subject> subjects = new ArrayList<>();
		try {
			String stm = "Select * from subjects where coordinator_id ='" + id + "'";
			PreparedStatement stmt = DBConnection.prepare(stm);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				int coordinator = Integer.parseInt(rs.getString(3));
				subjects.add(new Subject(code, name, coordinator));
			}

		} catch (SQLException e) {

		}
		return subjects;
	}
	
	
	public static List<Subject> getAllAdminSubjects() {
		List<Subject> subjects = new ArrayList<>();
		try {
			String findAllSubjectsStatement = "SELECT * from subjects";
			PreparedStatement stmt = DBConnection.prepare(findAllSubjectsStatement);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				int coordinator = Integer.parseInt(rs.getString(3));
				subjects.add(new Subject(code, name, coordinator));
			}

		} catch (SQLException e) {

		}
		return subjects;
	}

}
