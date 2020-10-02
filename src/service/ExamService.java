package service;

import domain.Exam;
import mapper.ExamMapper;
import shared.UnitOfWork;

public class ExamService {
	
	private ExamMapper examMapper;
	
	public ExamService() {
		examMapper = new ExamMapper();
	}

	public Boolean createNewExam(int id, String title, int status, String subject_code) {
		
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
	
	
	
	public Boolean updateExam(int id, String title, int status, String subject_code) {
		
//		Exam exam = examMapper.findByID(id)

		UnitOfWork.newCurrent();
		
		//create the new exam
		Exam exam = new Exam();
		exam.setId(id);
		exam.setTitle(title);
		exam.setStatus(status);
		exam.setSubject(subject_code);
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

}
