package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
@Table
public class PlayerProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany(targetEntity=Player.class, orphanRemoval=true, cascade={CascadeType.REMOVE})
	@CascadeOnDelete
	private List<Player> playerFK;
	private String playerFirstName;
	private String playerLastName;
	
	
	public PlayerProfile() {
		super();
	}
	public PlayerProfile(String playerFirstName, List<Player> player) {
		setPlayerFirstName(playerFirstName);
		setPlayerFK(player);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Player> getPlayerFK() {
		return playerFK;
	}
	public void setPlayerFK(List<Player> playerFK) {
		this.playerFK = playerFK;
	}
	public String getPlayerFirstName() {
		return playerFirstName;
	}
	public void setPlayerFirstName(String playerFirstName) {
		this.playerFirstName = playerFirstName;
	}
	public String getPlayerLastName() {
		return playerLastName;
	}
	public void setPlayerLastName(String playerLastName) {
		this.playerLastName = playerLastName;
	}
	
	
}
