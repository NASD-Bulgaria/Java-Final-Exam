package models;

public class Player {
	private int id;
	private String username;
	private String password;
	private double balance;
	private String token;
	
	//constructor with no arguments
	public Player(){
		
	}
	
	//constructor with two arguments
	public Player(double balance, int id){
		this.id = id;
		this.balance = balance;
	}
	
	//constructor with two arguments
	public Player(String password, String token){
		this.password = password;
		this.token = token;
		
	}
	
	//constructor with four arguments
	public Player(int id, String username, String password, double balance,
			String token) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.token = token;
	}
	//get and set of fields
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
