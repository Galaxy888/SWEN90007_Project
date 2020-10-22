package mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import datasource.DBConnection;
import domain.DomainObject;
import domain.UserSubject;

public class UserSubjectMapper extends DataMapper {
	
	/**
	 * 
	 * @param obj = user and subject to be inserted
	 * @return indication whether the insert is successful or not
	 */
	@Override
	public Boolean insert(DomainObject obj) {
		
		UserSubject userSubject=(UserSubject) obj;
		String insertSubjectStatement = "INSERT INTO users_subjects VALUES (?, ?, 0)";
		try {
			PreparedStatement insertStatement = DBConnection.prepare(insertSubjectStatement);
			insertStatement.setInt(1, userSubject.getUser_id());
			System.out.print(userSubject.getUser_id());
			insertStatement.setString(2, userSubject.getCode());
			System.out.print(userSubject.getCode());
			insertStatement.execute();
			insertStatement.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	
	}

}