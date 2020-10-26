package service;

import domain.Mark;
import shared.UnitOfWork;

public class MarkService {
	
	public MarkService() {

	}

	public Boolean updateResult(int id, int exam_id, int mark, int status, int version) {
		UnitOfWork.newCurrent();
		
		Mark result = new Mark();
		result.setMark(mark);
		result.setStatus(status);
		result.setId(id);
		result.setEId(exam_id);
		result.setVersion(version);
		UnitOfWork.getCurrent().registerDiry(result);
		
		return UnitOfWork.getCurrent().commit();
	}


}
