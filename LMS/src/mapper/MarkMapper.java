package mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
