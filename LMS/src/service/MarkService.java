package service;

import domain.Mark;
import shared.UnitOfWork;

public class MarkService {
	
	public MarkService() {

	}

	public Boolean updateResult(int id, int exam_id, int mark, int status) {
		UnitOfWork.newCurrent();
		
		Mark result = new Mark();
		result.setMark(mark);
		result.setStatus(status);
		result.setId(id);
		result.setEId(exam_id);
		UnitOfWork.getCurrent().registerDiry(result);
		
		return UnitOfWork.getCurrent().commit();
	}


}
