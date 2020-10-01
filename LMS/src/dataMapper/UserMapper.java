package dataMapper;

import datasource.DBConnection;
<<<<<<< HEAD
import domain.User;

=======
>>>>>>> master
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserMapper {

<<<<<<< HEAD
	public User authenticateUser(String userName, String password)
=======
	public String authenticateUser(String userName, String password)
>>>>>>> master
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
<<<<<<< HEAD
            
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
=======

        	while(resultSet.next())
 	        {
 	            userNameDB = resultSet.getString("name");
 	            passwordDB = resultSet.getString("password");
 	            userTypeDB = resultSet.getInt("user_type");
// 	            System.out.println(userName+password);
// 	            System.out.println(userNameDB+passwordDB+userTypeDB);
 	 
 	            if(userName.equals(userNameDB) && password.equals(passwordDB) && userTypeDB==0)
 	            return "Admin";
 	            else if(userName.equals(userNameDB) && password.equals(passwordDB) && userTypeDB==1)
 	            return "Instructor";
 	            else if(userName.equals(userNameDB) && password.equals(passwordDB) && userTypeDB==2)
 	            return "Student";
>>>>>>> master
 	        }
	
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
<<<<<<< HEAD
		User user2 = new User();
		return user2;
		
		//return "Invalid username or password ";
=======
		
		return "Invalid username or password ";
>>>>>>> master
	 
	}
}
