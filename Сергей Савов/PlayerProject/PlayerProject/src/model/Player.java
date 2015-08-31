package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the player database table.
 * 
 */
@Entity
@Table(name="player")
@NamedQuery(name="Player.findAll", query="SELECT p FROM Player p")
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	private double balance;

	@Column(name="login_name", nullable=false, length=45)
	private String loginName;

	@Column(name="login_password", nullable=false, length=45)
	private String loginPassword;

	//bi-directional one-to-one association to PlayerProfile
	@OneToOne(mappedBy="player")
	private PlayerProfile playerProfile;

	public Player() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public PlayerProfile getPlayerProfile() {
		return this.playerProfile;
	}

	public void setPlayerProfile(PlayerProfile playerProfile) {
		this.playerProfile = playerProfile;
	}

}