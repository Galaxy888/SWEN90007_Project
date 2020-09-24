package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;

public class Subject {
	
	private String subjectCode;
    private String name;  
    private int coordinator_id;
    
    private static final String findAllSubjectsStatement =
			"SELECT * from subjects";
    private static final String insertSubjectStatement =
			"INSERT INTO subjects VALUES (?, ?, ?)";
    
    public Subject(String subjectCode, String name, int coordinator_id) {
        this.subjectCode = subjectCode;
        this.coordinator_id = coordinator_id;
        this.name = name;
    }

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoordinator() {
		return coordinator_id;
	}

	public void setCoordinator(int coordinator_id) {
		this.coordinator_id = coordinator_id;
	}
	
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
	public String insert() {
		try {
			PreparedStatement insertStatement = DBConnection.prepare(insertSubjectStatement);
			insertStatement.setString(1,subjectCode);
			insertStatement.setString(2, name);
			insertStatement.setInt(3, coordinator_id);
			insertStatement.execute();
		} catch (SQLException e) {
		}
		return getSubjectCode();

	}

}
