package service;

import domain.UserQuestion;
import shared.UnitOfWork;

public class UserQuestionService {
	
	public UserQuestionService() {

	}
	
	public Boolean createNewUserQuestion(int user_id,int question_id, String answer) {
		
		UnitOfWork.newCurrent();
		
		//create the new user_question
		UserQuestion userQuestion = new UserQuestion();
		userQuestion.setUser_id(user_id);
		userQuestion.setQuestion_id(question_id);
		userQuestion.setAnswer(answer);
		UnitOfWork.getCurrent().registerNew(userQuestion);
		
		
		return UnitOfWork.getCurrent().commit();
	}

}
