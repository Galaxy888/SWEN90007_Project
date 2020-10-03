package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;
import domain.DomainObject;
import domain.Mark;

public class MarkMapper extends DataMapper{
	
	/**
	 * 
	 * @param obj = mark to be updated
	 * @return indication whether the update is successful or not
	 * @throws SQLException
	 */
	@Override
	public Boolean update(DomainObject obj) throws SQLException {
		Mark mark = (Mark) obj;
		
		String updateMarkStatement = "update users_exams set mark=?,status=? where user_id=? and exam_id=? ";
		
		PreparedStatement stmt = DBConnection.prepare(updateMarkStatement);
		try {
			stmt.setInt(1,mark.getMark());
			stmt.setInt(2, mark.getStatus());
			stmt.setInt(3,mark.getId());
			stmt.setInt(4,mark.getEId());
		
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

	public List<Mark> getAllMarks(int exam_id) {
		List<Mark> marks = new ArrayList<>();
		try {
			String stm = "select * from users_exams where exam_id='" + exam_id + "'";
			PreparedStatement stmt = DBConnection.prepare(stm);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				int eid = Integer.parseInt(rs.getString(2));
				int mark = Integer.parseInt(rs.getString(3));
				int status = Integer.parseInt(rs.getString(4));
				marks.add(new Mark(id, eid, mark, status));
			}

		} catch (SQLException e) {

		}
		return marks;
	}

}
