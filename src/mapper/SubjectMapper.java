package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;
import domain.Subject;

public class SubjectMapper extends DataMapper {
	
    private static final String findAllSubjectsStatement =
			"SELECT * from subjects";
	
	public static List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try {
        	PreparedStatement stmt = DBConnection.prepare(findAllSubjectsStatement);

        	ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				int coordinator = Integer.parseInt(rs.getString(3));
				subjects.add(new Subject(code,name,coordinator));
			}
	
		} catch (SQLException e) {
	
		}
        return subjects;
    }

}
