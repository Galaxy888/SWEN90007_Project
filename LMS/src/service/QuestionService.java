package service;

import domain.Exam;
import domain.Question;
import shared.UnitOfWork;

public class QuestionService {
	
	public QuestionService() {

	}
	
	public Boolean createNewQuestion(int id, int type, String title, String content, String answer, int mark,
			int exam_id) {
		
//		Exam exam = examMapper.findByID(id)

		
		UnitOfWork.newCurrent();
		
		//create the new question
		Question question = new Question();
		question.setId(id);
		question.setType(type);
		question.setTitle(title);
		question.setContent(content);
		question.setContent(content);
		question.setMark(mark);
		question.setExam(exam_id);
		UnitOfWork.getCurrent().registerNew(question);
		
		
		return UnitOfWork.getCurrent().commit();
	}
	

	public Boolean updateQuestion(int id, int type, String title, String content, String answer, int mark,
			int exam_id) {
		
		UnitOfWork.newCurrent();
		
		Question question = new Question();
		question.setId(id);
		question.setType(type);
		question.setTitle(title);
		question.setContent(content);
		question.setContent(content);
		question.setMark(mark);
		question.setExam(exam_id);
		UnitOfWork.getCurrent().registerDiry(question);
		
		
		return UnitOfWork.getCurrent().commit();

	}


	

}
