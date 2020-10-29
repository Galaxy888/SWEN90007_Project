//package mapper;
//
//public class LockingMapper extends DataMapper {
//	private DataMapper impl;
//	private String sessionId;
//
//	public LockingMapper(DataMapper impl) {
//		this.impl = impl;
//		this.sessionId = AppSessionManager.getSession().getId();
//	}
//
//	@Override
//	public void insert(DomainObject obj) {
//		impl.insert(obj);
//	}
//
//	@Override
//	public void update(DomainObject obj) {
//	if(ExclusiveReadLockManager.getInstance().hasLock(obj.getId(), sessionId)) {
//	impl.update(obj);
//	} else {//handle error}
//	}
//
//	@Override
//	public void delete(DomainObject obj) {
//	if(ExclusiveReadLockManager.getInstance().hasLock(obj.getId(), sessionId)) {
//	impl.delete(obj);
//	} else {//handle error}
//	}
//
//
//}