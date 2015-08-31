package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Player_profile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne
	private Player fk_player;
	
	private String first_name;
	private String last_name;
	
	public Player_profile(){
		
	}
	

	public Player_profile(Player fk_player, String first_name, String last_name) {
		super();
		this.fk_player = fk_player;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public Player getFk_player() {
		return fk_player;
	}

	public void setFk_player(Player fk_player) {
		this.fk_player = fk_player;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Player_profile [id=" + id + ", fk_player=" + fk_player + ", first_name=" + first_name + ", last_name="
				+ last_name + "]";
	}
	
	
	
}
