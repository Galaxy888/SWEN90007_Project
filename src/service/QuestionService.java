package service;

import java.sql.*;
import java.util.List;
import java.util.Date;

import domain.Answer;
import domain.Exam;
import domain.Mark;
import domain.Question;
import shared.UnitOfWork;

public class QuestionService {
	
	public QuestionService() {

	}
	
	public Boolean createNewQuestion(int type, String title, String content, String answer, int mark,
			int exam_id) {
		
//		Exam exam = examMapper.findByID(id)

		
		UnitOfWork.newCurrent();
		
		//create the new question
		Question question = new Question();
//		Date date = new Date();  
//        Timestamp modifiedTime=new Timestamp(date.getTime());  
		Timestamp modifiedTime = new Timestamp(System.currentTimeMillis());
		//question.setId(id);
		question.setType(type);
		question.setTitle(title);
		question.setContent(content);
		question.setAnswer(answer);
		question.setMark(mark);
		question.setExam(exam_id);
		question.setModifiedTime(modifiedTime);
		question.setVersion(0);
		UnitOfWork.getCurrent().registerNew(question);
		
		
		return UnitOfWork.getCurrent().commit();
	}
	

	public Boolean updateQuestion(int id, int type, String title, String content, String answer, int mark,
			int exam_id, int version) {
		
		UnitOfWork.newCurrent();
		
		Question question = new Question();
		question.setId(id);
		question.setType(type);
		question.setTitle(title);
		question.setContent(content);
		question.setAnswer(answer);
		question.setMark(mark);
		question.setExam(exam_id);
		question.setVersion(version);
		UnitOfWork.getCurrent().registerDiry(question);
		
		
		return UnitOfWork.getCurrent().commit();

	}

	public Boolean deleteQuestion(int id) {
		
		UnitOfWork.newCurrent();
		Question question = new Question();
		question.setId(id);
//		exam.setTitle(title);
//		exam.setStatus(status);
//		exam.setSubject(subject_code);
		
		UnitOfWork.getCurrent().registerDeleted(question);
		
		return UnitOfWork.getCurrent().commit();
	}
	
	public List<Answer> getAllAnswers(int question_id)  {
		Question question = new Question();
		question.setId(question_id);
		System.out.println("QuestionService.java: "+question.getId());
		return question.getAllAnswers();
	}


	

}
