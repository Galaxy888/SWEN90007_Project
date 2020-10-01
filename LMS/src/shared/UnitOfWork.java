package shared;

import java.util.ArrayList;
import java.util.List;

import dataMapper.DataMapper;
import domain.DomainObject;

public class UnitOfWork {

	@SuppressWarnings("rawtypes")
	private static ThreadLocal current = new ThreadLocal();

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

	/**
	 * 
	 * @param obj = obj to be inserted in the new list
	 */
	public void registerNew(DomainObject obj) {
		// make sure that the obj is not in any other list
		if (!objctInAnyList(obj)) {
			newObjects.add(obj);
		}
	}

	/**
	 * 
	 * @param obj = obj to be inserted in the dirty list
	 */
	public void registerDiry(DomainObject obj) {
		if (!objctInAnyList(obj)) {
			dirtyObjects.add(obj);
		}
	}

	/**
	 * 
	 * @param obj = obj to be inserted in the deleted lists
	 */
	public void registerDeleted(DomainObject obj) {
		if (!objctInAnyList(obj)) {
			deletedObjects.add(obj);
		}
	}

	public Boolean commit() {
		Boolean result = true;
		
		

		// commit new objects
		for (DomainObject obj : newObjects) {
			try {
				DataMapper mapper = (DataMapper) Class.forName("dataMapper." + obj.getClass().getSimpleName() + "Mapper")
						.getDeclaredConstructor().newInstance();
				
				System.out.println("UOW: new: "+ "dataMapper." + obj.getClass().getSimpleName() + "Mapper");
				result = mapper.insert(obj);

				if (!result) {
					return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		return result;

	}

	private boolean objctInAnyList(DomainObject obj) {
		if (newObjects.contains(obj) || dirtyObjects.contains(obj) || deletedObjects.contains(obj)) {
			return true;
		}
		return false;
	}

}
