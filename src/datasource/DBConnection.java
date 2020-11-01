package datasource;

import java.sql.*;

public class DBConnection {

	public static PreparedStatement prepare(String stm) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			Connection dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(stm);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return preparedStatement;
	}

	private static Connection getDBConnection() {
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
			String dbUrl = System.getenv("JDBC_DATABASE_URL");
			Connection dbConnection = DriverManager.getConnection(dbUrl);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Connection problem");
		return null;

	}


}
