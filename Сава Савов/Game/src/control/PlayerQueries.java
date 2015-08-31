package control;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Player;
import entity.PlayerProfile;

public class PlayerQueries {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Game");

	public static void registerNewPlayer(String logName,String password){
		
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		
		Player newReg = new Player(logName, password, 0);
		manager.persist(newReg);
		manager.getTransaction().commit();
		manager.close();
	}
	public static void insertNewPlayer(String firstName,String lastName,Player player){
	    EntityManager manager = emf.createEntityManager();
	    manager.getTransaction().begin();
	    
	    PlayerProfile newPlayer = new PlayerProfile(firstName, lastName, player);
	    manager.persist(newPlayer);
	    manager.getTransaction().commit();
	    manager.close();
	}
	public static Player findUserByUserName(String loginName){
		
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery("SELECT p FROM Player p WHERE p.loginName =  '"+ loginName+"'");
		
		Player player = (Player) query.getSingleResult();
		manager.close();
		
		return player;
	}
	@SuppressWarnings("unchecked")
	public static List<PlayerProfile> showAllPlayers(){
		
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery("SELECT p FROM PlayerProfile p");
		
		List<PlayerProfile> list = query.getResultList();
		manager.close();
		
		return list;
	}

	public static void depositIntoAccount(String token, double amount) {

		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery("SELECT p FROM Player p WHERE p.loginName =  '" + token + "'");

		Player player = (Player) query.getSingleResult();
		
		manager.getTransaction().begin();
		double depositBalance = player.getBalance()+amount;
		player.setBalance(depositBalance);
		manager.persist(player);
		manager.getTransaction().commit();
		manager.close();
	}
	public static void withdrawBalance(String token, double amount){
		
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery("SELECT p FROM Player p WHERE p.loginName =  '" + token + "'");

		Player player = (Player) query.getSingleResult();
		
		manager.getTransaction().begin();
		double withdrawBalance = player.getBalance()-amount;
		player.setBalance(withdrawBalance);
		manager.persist(player);
		manager.getTransaction().commit();
		manager.close();
	}
}
