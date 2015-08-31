package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the player_profile database table.
 * 
 */
@Entity
@Table(name="player_profile")
@NamedQuery(name="PlayerProfile.findAll", query="SELECT p FROM PlayerProfile p")
public class PlayerProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	//bi-directional one-to-one association to Player
	@OneToOne(mappedBy="playerProfile")
	private Player player;

	public PlayerProfile() {
	}

	public PlayerProfile(String firstName2, String lastName2, Player player2) {
		firstName = firstName2;
		lastName = lastName2;
		player = player2;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}