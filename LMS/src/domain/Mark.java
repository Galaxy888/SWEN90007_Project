package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;

public class Mark extends DomainObject {
	private int user_id;
	private int exam_id;
	private int mark;
	private int status;

	public Mark(int user_id, int exam_id, int mark, int status) {
		this.user_id = user_id;
		this.exam_id = exam_id;
		this.mark = mark;
		this.status = status;
	}

	public Mark() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return user_id;
	}

	public void setId(int id) {
		this.user_id = id;
	}

	public int getEId() {
		return exam_id;
	}

	public void setEId(int id) {
		this.exam_id = id;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int id) {
		this.mark = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	// Get mark
//	public static List<Mark> getAllMark(int exam_id) {
//		List<Mark> marks = new ArrayList<>();
//		try {
//			String stm = "select * from users_exams where exam_id='" + exam_id + "'";
//			PreparedStatement stmt = DBConnection.prepare(stm);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				int id = Integer.parseInt(rs.getString(1));
//				int eid = Integer.parseInt(rs.getString(2));
//				int mark = Integer.parseInt(rs.getString(3));
//				int status = Integer.parseInt(rs.getString(4));
//				marks.add(new Mark(id, eid, mark, status));
//			}
//
//		} catch (SQLException e) {
//
//		}
//		return marks;
//	}

	// Update an result info
//	public void updateResult(int id, int exam_id, int mark, int status) throws SQLException {
//		// TODO Auto-generated method stub
//		String sql = "update users_exams set mark=?,status=? where user_id=? and exam_id=? ";
//		PreparedStatement stmt = DBConnection.prepare(sql);
//		try {
//			stmt.setInt(1, mark);
//			stmt.setInt(2, status);
//			stmt.setInt(3, id);
//			stmt.setInt(4, exam_id);
//			stmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
