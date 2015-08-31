package queries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Player;
import model.PlayerProfile;

public class Queries {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalTest");

	/**
	 * Persists a Player in the database.
	 * Uses the given arguments to create the Object
	 * 
	 * @param loginName
	 * @param loginPass
	 * @param firstName
	 * @param lastName
	 * @param balance
	 */
	public static void addPlayer(String loginName, String loginPass,
			String firstName, String lastName, int balance) throws IllegalArgumentException{
		EntityManager manager = emf.createEntityManager();
		if(findPlayerByLoginName(loginName) != -1 || balance < 0 ){
			manager.close();
			throw new IllegalArgumentException();			
		}
		Player player = new Player(loginName, loginPass, balance);
		PlayerProfile playerProf = new PlayerProfile(firstName, lastName,
				player);
		player.setPlayerProfile(playerProf);
		synchronized (manager) {
			manager.getTransaction().begin();
			manager.persist(player);
			manager.getTransaction().commit();
		}
		manager.close();
	}
	
	/**
	 * Searches in the database for a Player with given login name and return their
	 * id; Returns -1 if the Player was not found;
	 * 
	 * @param String loginName
	 * @return Id of the given player or -1 if they do not exist
	 */
	public static int findPlayerByLoginName(String loginName) {
		EntityManager manager = emf.createEntityManager();
		int retval;
		synchronized (manager) {
			manager.getTransaction().begin();
			TypedQuery<Player> query = manager.createQuery(
					"SELECT p FROM Player p WHERE p.loginName = '" + loginName
							+ "'", Player.class);
			int found = query.getFirstResult();
			if (found == 0) {
				manager.getTransaction().rollback();
				manager.close();
				return -1;
			}
			retval = query.getResultList().get(0).getId();
			manager.getTransaction().commit();
			
		}
		manager.close();
		return retval;
	}
	
	/**
	 * Searches the database for a record with the given ID and returns the 
	 * record found. Returns null if there was no record with such ID; 
	 * 
	 * @param Id
	 * @return Player with the given ID or null if there was no such record
	 */
	public static Player findPlayerById(int Id){
		EntityManager manager = emf.createEntityManager();
		Player retval = null;
		synchronized (manager) {
			manager.getTransaction().begin();
			retval = manager.find(Player.class, Id);
			manager.getTransaction().commit();
		}
		manager.close();
		return retval;
	}
	
	private static void setBalance(Player play, int value)throws IllegalArgumentException{
		EntityManager manager = emf.createEntityManager();
		if(play == null || value<0){
			manager.close();
			throw new IllegalArgumentException();
		}
		synchronized (manager) {
			manager.getTransaction().begin();
			play.setBalance(play.getBalance() + value);
			manager.getTransaction().commit();
		}
		manager.close();
	}
	
	/**
	 * Withdraws the given value from the balance of the Player with the given ID
	 * Throws Exception if the value to be withdrawn is negative or there is no
	 * player with the given ID.
	 * 
	 * @param id
	 * @param value
	 * @throws IllegalArgumentException
	 */
	public static void withdraw(int id, int value) throws IllegalArgumentException{
		Player p = findPlayerById(id);
		if(p.getBalance() - value <0 ){
			throw new IllegalArgumentException();
		}
		setBalance(p, -1*value);
	}
	
	/**
	 * Deposits the given value to the balance of the Player with the given ID
	 * Throws Exception if the value to be deposited is negative or there is no
	 * player with the given ID.
	 * 
	 * @param id
	 * @param value
	 * @throws IllegalArgumentException
	 */
	public static void deposit(int id, int value) throws IllegalArgumentException{
		Player p = findPlayerById(id);
		setBalance(p, value);
	}
}
