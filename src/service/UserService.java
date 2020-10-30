package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mapper.UserMapper;
import datasource.DBConnection;
import domain.Exam;
import domain.Subject;
import domain.User;
import mapper.ExamMapper;
import shared.UnitOfWork;

public class UserService {
	
    private UserMapper userMapper;
	
	public UserService() {
		userMapper = new UserMapper();
	}
    
	public Boolean createNewUser(String name,String password, int type) {
		
//		Exam exam = examMapper.findByID(id)
		
		UnitOfWork.newCurrent();
		//create the new exam
		User user = new User(name,password,type);
		//exam.setId(id);
		//exam.setTitle(title);
		//exam.setStatus(status);
		//exam.setSubject(subject_code);
		UnitOfWork.getCurrent().registerNew(user);
		
		return UnitOfWork.getCurrent().commit();
	}


	
	

}
