package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="player_profile")
@NamedQuery(name="PlayerProfile.findAll", query="SELECT p FROM PlayerProfile p")

public class PlayerProfile implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@ManyToOne
	@JoinColumn(name="fk_player")
	private Player player;

	public PlayerProfile() {
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