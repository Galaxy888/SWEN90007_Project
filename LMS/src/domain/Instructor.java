package domain;

public class Instructor {
	private String name;

	public Instructor(String coordinatorName) {
		
		this.setName(coordinatorName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
