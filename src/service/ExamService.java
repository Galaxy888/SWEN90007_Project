package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;
import domain.Exam;
import domain.Mark;
import domain.Question;
import mapper.ExamMapper;
import shared.UnitOfWork;

public class ExamService {
	
	private ExamMapper examMapper;
	
	public ExamService() {
		examMapper = new ExamMapper();
	}

	public Boolean createNewExam(String title, int status, String subject_code) {
		
//		Exam exam = examMapper.findByID(id)
		
		UnitOfWork.newCurrent();
		
		//create the new exam
//		Exam exam = new Exam(title,status,subject_code);
		Exam exam = new Exam();
//		exam.setId(id);
		exam.setTitle(title);
		exam.setStatus(status);
		exam.setSubject(subject_code);
		exam.setVersion(0);
		UnitOfWork.getCurrent().registerNew(exam);
		
		return UnitOfWork.getCurrent().commit();
	}
	
	
	
	public Boolean updateExam(int id, String title, int status, String subject_code, int version) {
		
//		Exam exam = examMapper.findByID(id)

		UnitOfWork.newCurrent();
		
		//create the new exam
		Exam exam = new Exam();
		exam.setId(id);
		exam.setTitle(title);
		exam.setStatus(status);
		exam.setSubject(subject_code);
		exam.setVersion(version);
		UnitOfWork.getCurrent().registerDiry(exam);
		
		
		return UnitOfWork.getCurrent().commit();
	}

	public Boolean deleteExam(int id) {		
		UnitOfWork.newCurrent();
		Exam exam = new Exam();
		exam.setId(id);		
		UnitOfWork.getCurrent().registerDeleted(exam);
		
		return UnitOfWork.getCurrent().commit();
	}

	
	// Get all questions
	public List<Question> getAllQuestions(int exam_id) {
		
		Exam exam = new Exam();
		exam.setId(exam_id);
		System.out.println("ExamService.java: "+exam.getId());
		return exam.getAllQuestions();
	}
	

	
	public List<Mark> getAllMarks(int exam_id) {
		Exam exam = new Exam();
		exam.setId(exam_id);
		System.out.println("ExamService.java: "+exam.getId());
		return exam.getAllMarks();
	}

	public int getExamStatus(int id) {
		Exam exam = new Exam();
		exam.setId(id);
		ExamMapper examMapper = new ExamMapper();
		
		return examMapper.getExamStatus(exam);
	}

}
