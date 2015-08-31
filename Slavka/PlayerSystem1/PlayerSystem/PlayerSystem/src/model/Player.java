package model;

import javax.annotation.Resource.AuthenticationType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table
public class Player {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String loginName;
	private String loginPassword;
	private double balance;
	private String salt;
	private String token;
	
	
	public Player() {
		super();
	}
	
	
	public Player(int id, String loginName, String loginPassword, double balance, String salt, String token) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.balance = balance;
		this.salt = salt;
		this.token = token;
	}





	public Player(String loginName, String loginPassword, double balance, String salt, String token) {
		super();
		
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.balance = balance;
		this.salt = salt;
		this.token = token;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
