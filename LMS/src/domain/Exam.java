<<<<<<< HEAD
package domain;

import java.sql.*;
import java.util.*;


import datasource.DBConnection;

public class Exam {
	private int id;
	private String title;
	private int status;
    private String subject_code;
    
    private static final String findAllSubjectsStatement =
			"SELECT * from exams";
    private static final String insertSubjectStatement =
			"INSERT INTO exams VALUES (?, ?, ?,?)";
    
    public Exam(int id, String title, int status, String subject_code) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.subject_code = subject_code;
    }

	public Exam() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String getSubject() {
		return subject_code;
	}

	public void setSubject(String subject_code) {
		this.subject_code = subject_code;
	}
	
	//Update an exam info
	public void updateExam(int id, String title, int status,String subject_code) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update exams set title=?,status=?,subject_code=? where id=?";
		PreparedStatement stmt = DBConnection.prepare(sql);
		try {
			stmt.setString(1,title);
			stmt.setInt(2, status);
			stmt.setString(3,subject_code);
			stmt.setInt(4,id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Get all exams
	public static List<Exam> getAllExams(String subject_code) {
        List<Exam> exams = new ArrayList<>();
        try {
        	String stm = "select * from exams where subject_code='"+subject_code+"'";
        	PreparedStatement stmt = DBConnection.prepare(stm);
        	ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				String title = rs.getString(2);
				int status = Integer.parseInt(rs.getString(3));
				String subject = rs.getString(4);
				exams.add(new Exam(id,title,status,subject));
			}
	
		} catch (SQLException e) {
	
		}
        return exams;
    }
	
	//Insert an new exam
	public int insert() {
		try {
			PreparedStatement insertStatement = DBConnection.prepare(insertSubjectStatement);
			insertStatement.setInt(1,id);
			insertStatement.setString(2, title);
			insertStatement.setInt(3, status);
			insertStatement.setString(4, subject_code);
			insertStatement.execute();
		} catch (SQLException e) {
		}
		return getId();

	}
	
	//Delete an exam
	public void deleteExam(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from exams where id=? and status = 0";
		try {
			PreparedStatement stmt = DBConnection.prepare(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
=======
package domain;

import java.sql.*;
import java.util.*;


import datasource.DBConnection;

public class Exam {
	private int id;
	private String title;
	private int status;
    private String subject_code;
    
    private static final String findAllSubjectsStatement =
			"SELECT * from exams";
    private static final String insertSubjectStatement =
			"INSERT INTO exams VALUES (?, ?, ?,?)";
    
    public Exam(int id, String title, int status, String subject_code) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.subject_code = subject_code;
    }

	public Exam() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String getSubject() {
		return subject_code;
	}

	public void setSubject(String subject_code) {
		this.subject_code = subject_code;
	}
	
	//Update an exam info
	public void updateExam(int id, String title, int status,String subject_code) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update exams set title=?,status=?,subject_code=? where id=?";
		PreparedStatement stmt = DBConnection.prepare(sql);
		try {
			stmt.setString(1,title);
			stmt.setInt(2, status);
			stmt.setString(3,subject_code);
			stmt.setInt(4,id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Get all exams
	public static List<Exam> getAllExams(String subject_code) {
        List<Exam> exams = new ArrayList<>();
        try {
        	String stm = "select * from exams where subject_code='"+subject_code+"'";
        	PreparedStatement stmt = DBConnection.prepare(stm);
        	ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				String title = rs.getString(2);
				int status = Integer.parseInt(rs.getString(3));
				String subject = rs.getString(4);
				exams.add(new Exam(id,title,status,subject));
			}
	
		} catch (SQLException e) {
	
		}
        return exams;
    }
	
	//Insert an new exam
	public int insert() {
		try {
			PreparedStatement insertStatement = DBConnection.prepare(insertSubjectStatement);
			insertStatement.setInt(1,id);
			insertStatement.setString(2, title);
			insertStatement.setInt(3, status);
			insertStatement.setString(4, subject_code);
			insertStatement.execute();
		} catch (SQLException e) {
		}
		return getId();

	}
	
	//Delete an exam
	public void deleteExam(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from exams where id=? and status = 1";
		try {
			PreparedStatement stmt = DBConnection.prepare(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
>>>>>>> master
