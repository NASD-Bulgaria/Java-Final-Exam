package entity;

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

	private String loginName;
	private String loginPassword;
	private double balance;

	private String token;

	public Player() {
		super();
	}

	public Player(String loginName, String loginPassword, double balance) {
		super();
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.balance = balance;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", loginName=" + loginName
				+ ", loginPassword=" + loginPassword + ", balance=" + balance
				+ ", token=" + token + "]";
	}

}
