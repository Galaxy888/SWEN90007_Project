package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;
import domain.Subject;
import domain.User;

public class UserService {
	
	public UserService() {

	}
	
	public static List<Subject> getAllSubjects(int id) {
		User user = new User();
		user.setId(id);
		System.out.println("UserService.java: "+user.getId());
		return user.getAllSubjects();
	}


}
