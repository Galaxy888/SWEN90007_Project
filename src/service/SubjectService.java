package service;

import java.util.List;

import domain.Exam;
import domain.Subject;

public class SubjectService {
	
	public SubjectService() {

	}

	public List<Exam> getAllExams(String subjectCode) {
		Subject subject = new Subject();
		subject.setSubjectCode(subjectCode);
		System.out.println("SubjectService.java: "+subject.getSubjectCode());
		return subject.getAllExams();
	}
	
	

}
