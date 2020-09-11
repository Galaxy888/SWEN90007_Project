package datasource;

import java.sql.*;

public class DBConnection {

	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5433/myDB";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "123456";


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
			Class.forName("org.postgresql.Driver");
			DriverManager.registerDriver(new org.postgresql.Driver());

			Connection dbConnection = DriverManager.getConnection(
					DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connection problem");
		return null;

	}


}
