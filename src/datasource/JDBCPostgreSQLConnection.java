package datasource;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

public class JDBCPostgreSQLConnection {
	private static final String url = "jdbc:postgresql://localhost:5432/myDB";
	private static final String user = "haobei";
	private static final String password = "12345678";
	
	public static Connection connect() { 
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
	
	public static void main(String[] args) throws SQLException { 
		JDBCPostgreSQLConnection app = new JDBCPostgreSQLConnection();
		Connection conn = app.connect(); 
		String stm = ("Select * FROM users;");
		PreparedStatement pst = conn.prepareStatement(stm);
		ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            System.out.println(rs.getString(2));
        }
	}
	
	
	

}
