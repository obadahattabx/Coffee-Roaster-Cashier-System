package model;

public class Users {

	private int id ;
	private String namefull;
	
	private String username;
	private String password;
	private String state;
	public String getNamefull() {
		return namefull;
	}
	public void setNamefull(String namefull) {
		this.namefull = namefull;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Users(int id,String namefull, String username, String password, String state) {
		super();
		this.id = id;
		this.namefull=namefull;
		this.username = username;
		this.password = password;
		this.state = state;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
