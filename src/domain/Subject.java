package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;

public class Subject {
	
	private String subjectCode;
    private String name;  
    private Instructor coordinator;
    
    private static final String findAllSubjectsStatement =
			"SELECT * from subjects";
    private static final String insertSubjectStatement =
			"INSERT INTO subjects VALUES (?, ?, ?)";
    
    public Subject(String subjectCode, String name, String coordinatorName) {
        this.subjectCode = subjectCode;
        this.coordinator = new Instructor(coordinatorName);
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

	public String getCoordinator() {
		return coordinator.getName();
	}

	public void setCoordinator(Instructor coordinator) {
		this.coordinator = coordinator;
	}
	
	public static List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try {
        	PreparedStatement stmt = DBConnection.prepare(findAllSubjectsStatement);

        	ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				String coordinator = rs.getString(3);
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
			insertStatement.setString(3, coordinator.getName());
			insertStatement.execute();
		} catch (SQLException e) {
		}
		return getSubjectCode();

	}

}
