package service;

import java.util.List;

import domain.Exam;
import domain.Subject;
import domain.UserQuestion;
import shared.UnitOfWork;

public class UserQuestionService {
	
	public UserQuestionService() {

	}
	
	public Boolean createNewUserQuestion(int user_id,int question_id,int exam_id,String answer) {
		
		UnitOfWork.newCurrent();
		
		//create the new user_question
		UserQuestion userQuestion = new UserQuestion();
		userQuestion.setUser_id(user_id);
		userQuestion.setQuestion_id(question_id);
		userQuestion.setAnswer(answer);
		userQuestion.setExam_id(exam_id);
		UnitOfWork.getCurrent().registerNew(userQuestion);
		
		
		return UnitOfWork.getCurrent().commit();
	}
	
	public List<UserQuestion> getAllQuesitonById(int user_id,int exam_id) {
		UserQuestion userQuestion = new UserQuestion();
		userQuestion.setUser_id(user_id);
		return userQuestion.getAllQuestionsbyId(user_id,exam_id);	
		}

}
