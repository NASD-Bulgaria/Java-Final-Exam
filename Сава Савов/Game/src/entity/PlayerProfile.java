package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PlayerProfile
 *
 */
@Entity
@Table(name="player_profile")

public class PlayerProfile implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String firstName;
	private String lastName;
	@OneToOne(targetEntity = Player.class)
	private Player fkPlayer;
	private static final long serialVersionUID = 1L;

	public PlayerProfile() {
		super();
	} 
	public PlayerProfile(String firstName, String lastName, Player player) {
		super();
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setFkPlayer(player);
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
		return fkPlayer;
	}
	public void setFkPlayer(Player fkPlayer) {
		this.fkPlayer = fkPlayer;
	}
	
   
}
