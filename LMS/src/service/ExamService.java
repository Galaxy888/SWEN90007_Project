package service;

import dataMapper.ExamMapper;
import domain.Exam;
import shared.UnitOfWork;

public class ExamService {
	
	private ExamMapper examMapper;
	
	public ExamService() {
		examMapper = new ExamMapper();
	}

	public Boolean createNewExam(int id, String title, int status, String subject_code) {
		// TODO Auto-generated method stub
		
//		Exam exam = examMapper.findByID(id)

		
		UnitOfWork.newCurrent();
		
		//create the new exam
		Exam exam = new Exam();
		exam.setId(id);
		exam.setTitle(title);
		exam.setStatus(status);
		exam.setSubject(subject_code);
		UnitOfWork.getCurrent().registerNew(exam);
		
		
		
		
		return UnitOfWork.getCurrent().commit();
	}

}
