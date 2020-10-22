package mapper;

import datasource.DBConnection;
import domain.DomainObject;
import domain.Exam;
import domain.Mark;
import domain.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserMapper  extends DataMapper {
	/**
	 * 
	 * @param obj = exam to be inserted
	 * @return indication whether the insert is successful or not
	 */
	@Override
	public Boolean insert(DomainObject obj) {
		User user = (User) obj;

		String insertExamStatement = "INSERT INTO users (name,email,password,user_type) VALUES (?, ?,?,?)";

		try {
			PreparedStatement stmt = DBConnection.prepare(insertExamStatement);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setInt(4, user.getType());
			stmt.execute();

			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
//		finally {
//			DBConnection.closeConnection()
//		}

	}
	
	
	public User authenticateUser(String userName, String password)
	{
	    Connection con = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
	 
	    String userNameDB = "";
	    String passwordDB = "";
	    int userTypeDB;
	    
		String loginInfo = ("Select * FROM users WHERE name = '" + userName + "' AND password = '" + password + "';" );
	    
		try {
        	PreparedStatement stmt = DBConnection.prepare(loginInfo);

        	resultSet = stmt.executeQuery();
            
        	while(resultSet.next())
 	        {
        		
 	            userNameDB = resultSet.getString("name");
 	            passwordDB = resultSet.getString("password");
 	            userTypeDB = resultSet.getInt("user_type");
 	            int id = resultSet.getInt("id");
 	            String email = resultSet.getString("email");
 	            User user =new User(id,userNameDB,email,passwordDB,userTypeDB);
// 	            System.out.println(userName+password);
// 	            System.out.println(userNameDB+passwordDB+userTypeDB);
 	            return user;
 	           // if(userName.equals(userNameDB) && password.equals(passwordDB) && userTypeDB==0)
 	           // return "Admin";
 	           // else if(userName.equals(userNameDB) && password.equals(passwordDB) && userTypeDB==1)
 	           // return "Instructor";
 	           // else if(userName.equals(userNameDB) && password.equals(passwordDB) && userTypeDB==2)
 	           // return "Student";
 	        }
	
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
		User user2 = new User();
		return user2;
		
		//return "Invalid username or password ";
	 
	}
	

}
