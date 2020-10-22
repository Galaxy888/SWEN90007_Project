package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import datasource.DBConnection;
import domain.Exam;
import domain.Subject;
import domain.User;
import domain.UserQuestion;
import domain.UserSubject;
import mapper.ExamMapper;
import mapper.UserSubjectMapper;
import shared.UnitOfWork;

public class UserSubjectService {
	
	public UserSubjectService() {
		
	}
    
	public Boolean createNewUserSubject(int id,String code) {
		
		UnitOfWork.newCurrent();
		
		//create the new user_question
		UserSubject userSubject = new UserSubject();
		userSubject.setUser_id(id);
		userSubject.setCode(code);
		UnitOfWork.getCurrent().registerNew(userSubject);
		
		
		return UnitOfWork.getCurrent().commit();
	}



	
	

}
