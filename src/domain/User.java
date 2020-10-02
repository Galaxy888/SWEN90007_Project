package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private int type;
	private static final String findAllUsersStatement =
				"SELECT * from users";
	private static final String insertUsersStatement =
				"INSERT INTO users VALUES (?, ?, ?, ?, ?)";
	public User(int id,String name,String email,String password,int type) {
		this.id=id;
		this.name=name;
		this.email=email;
		this.password=password;
		this.type=type;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public int getId(){
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getEmail() {
	   return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getPassword() {
		return password;
	}
    public void setPassword(String password) {
    	this.password=password;
    }
    public int getType() {
    	return type;
    }
    public void setType(int type) {
    	this.type=type;
    }
  //Get all users by id
  	public static User getUser(int id) {
          User user=new User();
          try {
          	String stm = "select * from users where id='"+id+"' limit 1";
          	PreparedStatement stmt = DBConnection.prepare(stm);
          	ResultSet rs = stmt.executeQuery();
  			while (rs.next()) {	
  				int user_id = Integer.parseInt(rs.getString(1));
  				String name = rs.getString(2);
  				String email = rs.getString(3);
  				String password = rs.getString(4);
  				int type = Integer.parseInt(rs.getString(5));
  				user=new User(user_id,name,email,password,type);
  			}
  	
  		} catch (SQLException e) {
  	
  		}
          return user;
      }
}