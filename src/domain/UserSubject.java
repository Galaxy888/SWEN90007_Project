package domain;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import datasource.DBConnection;

public class UserSubject extends DomainObject {
	private int user_id;
	private String code;
	private int status;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

	
}