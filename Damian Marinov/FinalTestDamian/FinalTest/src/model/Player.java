package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the player database table.
 * 
 */
@Entity
@Table(name="Player")
@NamedQuery(name="Player.findAll", query="SELECT p FROM Player p")
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int balance;
	private String loginName;
	private String loginPassword;
	private PlayerProfile playerProfile;

	public Player() {
	}


	public Player(String loginName2, String loginPass, int balance2) {
		loginName = loginName2;
		loginPassword = loginPass;
		balance = balance2;
	}


	@Id
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getBalance() {
		return this.balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}


	@Column(name="login_name")
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	@Column(name="login_password")
	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}


	//bi-directional one-to-one association to PlayerProfile
	@OneToOne
	@JoinColumn(name="id", referencedColumnName="fk_player")
	public PlayerProfile getPlayerProfile() {
		return this.playerProfile;
	}

	public void setPlayerProfile(PlayerProfile playerProfile) {
		this.playerProfile = playerProfile;
	}

}