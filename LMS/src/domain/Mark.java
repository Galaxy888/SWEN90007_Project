package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;

public class Mark extends DomainObject implements Comparable<Mark> {
	private int user_id;
	private int exam_id;
	private int mark;
	private int status;
	private int version;

	public Mark(int user_id, int exam_id, int mark, int status,int version) {
		this.user_id = user_id;
		this.exam_id = exam_id;
		this.mark = mark;
		this.status = status;
		this.version = version;
	}

	public Mark() {
		// TODO Auto-generated constructor stub
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getId() {
		return user_id;
	}

	public void setId(int id) {
		this.user_id = id;
	}

	public int getEId() {
		return exam_id;
	}

	public void setEId(int id) {
		this.exam_id = id;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int id) {
		this.mark = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public int compareTo(Mark o) {
		return this.user_id-o.user_id;
	}

}
