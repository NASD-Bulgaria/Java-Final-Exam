package responces;


public class Token {
	
	private String hash;
	private int userId;

	public Token(String hash){
		this.hash = hash;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getUserId(String hash) {
		if(hash.equals(this.hash))
			return userId;
		return -1;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
}
