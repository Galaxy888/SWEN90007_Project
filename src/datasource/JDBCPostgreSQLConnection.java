package datasource;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

public class JDBCPostgreSQLConnection {
	private static final String url = "jdbc:postgresql://localhost:5432/LMS";
	private static final String user = "postgres";
	private static final String password = "asdzxc123";
	
	public Connection connect() { 
		Connection conn = null;
		try{ 
			conn = DriverManager.getConnection(url, user, password); 
			if (conn !=   null) { 
				System.out.println("Connected to the PostgreSQL server successfully."); 
			} else { 
				System.out.println("Failed to   make connection!"); 
			} 
		} catch (SQLException e) {
			System.out.println(e.getMessage()); 
		} 
		return conn; 
	}
	
	public static void main(String[] args) { 
		JDBCPostgreSQLConnection app = new JDBCPostgreSQLConnection();
		app.connect(); 
	} 

}
