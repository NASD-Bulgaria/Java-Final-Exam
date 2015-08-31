package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class PlayerProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	private Player fkPlayer;

	private String firstName;
	private String lastName;

	public PlayerProfile() {
		super();
	}

	public PlayerProfile(Player fkPlayer, String firstName, String lastName) {
		super();
		this.fkPlayer = fkPlayer;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getFkPlayer() {
		return fkPlayer;
	}

	public void setFkPlayer(Player fkPlayer) {
		this.fkPlayer = fkPlayer;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "PlayerProfile [id=" + id + ", fkPlayer=" + fkPlayer
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
