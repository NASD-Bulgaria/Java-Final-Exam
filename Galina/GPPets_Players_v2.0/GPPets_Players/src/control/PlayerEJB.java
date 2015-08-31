package control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Player;
import model.PlayerProfile;

/**
 * Session Bean implementation class PlayerEJB
 */
@Stateless
public class PlayerEJB {
	@PersistenceContext(unitName="GPPets_Players")
	EntityManager em;
	
    public PlayerEJB() {
    }
  
    //List players in DB (not included in the user application)
  	public List<Player> playersList(){
  		List<Player> playersList = new ArrayList<Player>();
  		TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p ORDER BY p.loginName", Player.class);
  		playersList = query.getResultList();
		return playersList;
  	}
  	
  	//AddPlayer via registration
  	//Registration requires login_name; password; initial balance will be set to zero
  	public Player addPlayer(double balance, String loginName, String loginPassword){
  		Player newPlayer = new Player();
  		newPlayer.setLoginName(loginName);
  		newPlayer.setLoginPassword(loginPassword);
  		newPlayer.setBalance(balance);
  		newPlayer.setSessionToken("");
  		
		try {
			if(findPlayerByName(loginName).isEmpty()){
				em.persist(newPlayer);
			}
		} catch (Exception e) {
			System.err.println("Problem in search?!");
		} 
		return newPlayer;
	}
  	
  //Set token in DB at login
  	public void setTokenInDB(int id, String token){
  		Player found = em.find(Player.class, id);	
  		found.setSessionToken(token);
  		em.persist(found);
  	}
  	
  	public List<Player> findPlayerByName(String loginName){
		String search = "SELECT p FROM Player p "
						+ "WHERE p.loginName = '" + loginName + "' ";
		@SuppressWarnings("unchecked")
		List<Player> found = em.createQuery(search).getResultList();
		return found;
	}
  	
  	
  	//CheckUp login page?
  	public boolean isLoginInfoCorrect(String loginName, String password){
  		boolean isLoginInfoCorrect = false;
  		String search = "SELECT p FROM Player p WHERE p.loginName = '"+loginName+"' AND "
  				+ "p.loginPassword = '" + password + "'";
  		Player found = null;
  		try {
			found = (Player) em.createQuery(search).getSingleResult();
		} catch (Exception e) {
			System.err.println("Object not found!");
			found = null;
		}
  		
  		if(found!=null){
  			isLoginInfoCorrect = true;
  		}
  		return isLoginInfoCorrect;
  	}
  	
  	//Get playerID
	public Player getPlayer(String loginName){
		String search = "SELECT p FROM Player p WHERE p.loginName = '"+loginName+"'";
  		Player player = (Player) em.createQuery(search).getSingleResult();
  		return player;
  	}
  	
	//Get playerID by session token
	public Player getPlayerByToken(String session_token){
		String search = "SELECT p FROM Player p WHERE p.sessionToken = '"+session_token+"'";
		Player player = (Player) em.createQuery(search).getSingleResult();
	 	return player;
	 }
	
  	//AddPlayer profile
  	//Requires first and last name and player_id
  	public void addPlayerProfile(int id, String firstName, String lastName){
  		PlayerProfile profile = new PlayerProfile();
  		profile.setPlayer(em.find(Player.class, id));
  		profile.setFirstName(firstName);
  		profile.setLastName(lastName);
  		em.persist(profile);
  	}
  	
  	//balance
  	public double getBalance(int id){
  		Player player = em.find(Player.class,id);
  		return player.getBalance();
    }
  	
  	//depositMoney
  	public void depositMoney(int id, double amount){
  		Player player = em.find(Player.class,id);
  		double balance = player.getBalance();
//  		System.out.println("EJB balance" + balance);
  		if(amount > 0){
  			balance += amount;
//  			System.out.println("EJB balance2" + balance);
  			player.setBalance(balance);
  			em.persist(player);
  		} 
  		
    }
      
  	//withdrawMoney
  	public void withdrawMoney(int id, double amount){
  		Player player = em.find(Player.class, id);
  		double balance = player.getBalance() - amount;
  		if(amount> 0 && balance > 0){
  			player.setBalance(balance);
  			em.persist(player);
  	    } 
  	}
  	
  	
  	

}
