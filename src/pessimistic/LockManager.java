package pessimistic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import datasource.DBConnection;
import domain.Exam;

public class LockManager {
	

	public void acquireLock(int lockable, String owner) {
		
		
//		PreparedStatement stmt = DBConnection.prepare(updateExamStatement);
		String insertLockStmt = "INSERT INTO locks VALUES (?, ?)";
		
		if (!hasLock(lockable, owner)) {
			System.out.println("LockManager acquireLock lock");
			System.out.println(lockable+"  "+owner);
			try {
				
		
				PreparedStatement stmt = DBConnection.prepare(insertLockStmt);
				stmt.setInt(1, lockable);
				stmt.setString(2, owner);
				stmt.execute();
				stmt.close();
//				stmt.executeUpdate();
			} catch (SQLException e) {
//				throw new ConcurrencyException("Unable to lock " + lockable);
				System.out.println("Unable to lock " + lockable);
			}
		}
	}

	public void releaseLock(int lockable, String owner) {

		String deleteLockStmt = "DELETE FROM locks WHERE lockable = ? and owner = ?";
		
		if(lockable==00000) {
			String deleteLockStmt2 = "DELETE FROM locks WHERE owner = ?";
			try {
				PreparedStatement stmt = DBConnection.prepare(deleteLockStmt2);
				System.out.println("LockManager releaseLock delete0");
				stmt.setString(1, owner);
				stmt.executeUpdate();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
//				return false;
			}
			
		}
		
		try {
			PreparedStatement stmt = DBConnection.prepare(deleteLockStmt);
			System.out.println("LockManager releaseLock delete");
			stmt.setInt(1, lockable);
			stmt.setString(2, owner);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			return false;
		}

//		return true;

	}
	
	
	public boolean hasLock(int lockable, String owner) {
		
		try {
//			String stm = "select * from locks where lockable='" + lockable+ "' and owner='"+owner+"'";
			String stm = "select * from locks where lockable='" + lockable+ "'";
			PreparedStatement stmt = DBConnection.prepare(stm);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				 int lockable2 = Integer.parseInt(rs.getString(1));
//				 System.out.println("has lock " + lockable2);
				 
				 return true;

			}

		} catch (SQLException e) {

		}

		return false;
	}
	
	public boolean lockOwner(int lockable, String owner) {
		
		try {
			String stm = "select * from locks where lockable='" + lockable+ "' and owner='"+owner+"'";
//			String stm = "select * from locks where lockable='" + lockable+ "'";
			PreparedStatement stmt = DBConnection.prepare(stm);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				 int lockable2 = Integer.parseInt(rs.getString(1));
				 System.out.println("lock owner");
				 
				 return true;

			}

		} catch (SQLException e) {

		}
		
		
		return false;
		
	}

}
