package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;



@Entity
@NamedQueries({
	@NamedQuery(name = "Player.findAll", query="SELECT p FROM Player p"),
	@NamedQuery(name = "Player.findByName", query = "SELECT p FROM Player p WHERE p.loginName = :name")
})

public class Player implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private double balance;

	@Column(name="login_name")
	private String loginName;

	@Column(name="login_password")
	private String loginPassword;

	@OneToMany(mappedBy="player")
	private List<PlayerProfile> playerProfiles;

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

	public List<PlayerProfile> getPlayerProfiles() {
		return this.playerProfiles;
	}

	public void setPlayerProfiles(List<PlayerProfile> playerProfiles) {
		this.playerProfiles = playerProfiles;
	}

	public PlayerProfile addPlayerProfile(PlayerProfile playerProfile) {
		getPlayerProfiles().add(playerProfile);
		playerProfile.setPlayer(this);

		return playerProfile;
	}

	public PlayerProfile removePlayerProfile(PlayerProfile playerProfile) {
		getPlayerProfiles().remove(playerProfile);
		playerProfile.setPlayer(null);

		return playerProfile;
	}

}