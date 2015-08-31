package model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Player
 *
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"loginName"}))
public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String login_name;
	private String login_password;
	private String salt;
	private String token;
	private double balance;
	
	
	public Player(){
		
	}
	

	public Player(String login_name, String login_password, String salt) {
		super();
		setLogin_name(login_name);
		setLogin_password(login_password);
		setSalt(salt);
		setBalance(0);
		setToken("");
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
   
}
