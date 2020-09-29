package shared;

import java.util.ArrayList;
import java.util.List;

import domain.DomainObject;

public class UnitOfWork {
	
	@SuppressWarnings("rawtypes")
	private static ThreadLocal current =new ThreadLocal();
	
	private List<DomainObject> newObjects = new ArrayList<DomainObject>();
	private List<DomainObject> dirtyObjects = new ArrayList<DomainObject>();
	private List<DomainObject> deletedObjects = new ArrayList<DomainObject>();
	
	
	public static void newCurrent() {
		setCurrent(new UnitOfWork());
	}
	
	@SuppressWarnings("unchecked")
	public static void setCurrent(UnitOfWork uow) {
		current.set(uow);
	};
	
	public static UnitOfWork getCurrent() {
		return (UnitOfWork) current.get();
	}
	
	public void registerNew(DomainObject obj) {
		newObjects.add(obj);
	}
	
	
	

}
