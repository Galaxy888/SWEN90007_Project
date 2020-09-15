package domain;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private int type;
	 private static final String findAllUsersStatement =
				"SELECT * from users";
	    private static final String insertUsersStatement =
				"INSERT INTO users VALUES (?, ?, ?, ?, ?)";

}
