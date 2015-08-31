package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String login_name;
	private String login_password;
	private double balance;
	private String token;
	
	public Player(){
		
	}
	

	public Player(String login_name, String login_password, double balance, String token) {
		super();
		this.login_name = login_name;
		this.login_password = login_password;
		this.balance = balance;
		this.token = token;
	}


	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getLogin_password() {
		return login_password;
	}

	public void setLogin_password(String login_password) {
		this.login_password = login_password;
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

	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Player [id=" + id + ", login_name=" + login_name + ", login_password=" + login_password + ", balance="
				+ balance + ", token=" + token + "]";
	}

	
	
	
}
