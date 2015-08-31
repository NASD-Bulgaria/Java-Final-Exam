package models;

public class PlayerProfile {
	private int id;
	private String first_name;
	private String last_name;
	private int fk_player;

	//constructor with four arguments
	public PlayerProfile(int id, String first_name, String last_name,
			int fk_player) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.fk_player = fk_player;
	}
	//get and set of all fields
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getFk_player() {
		return fk_player;
	}

	public void setFk_player(int fk_player) {
		this.fk_player = fk_player;
	}

}
