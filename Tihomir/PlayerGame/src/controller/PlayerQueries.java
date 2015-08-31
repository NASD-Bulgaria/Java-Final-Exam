package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Player;
import model.PlayerProfile;

public class PlayerQueries {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlayerGame");
	
	// register new player ok
	public static Player registerNewPlayer(String login_name, String login_password, String salt){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Player newPlayer = new Player(login_name, login_password, salt);
		em.persist(newPlayer);
		em.getTransaction().commit();
		em.close();
		return newPlayer;
	}
	// Add player profile ok
	public static void addPlayerProfile(String first_name, String last_name, Player player) {
		EntityManager manager = emf.createEntityManager();
		PlayerProfile player_profile = new PlayerProfile(first_name, last_name, player);
		manager.getTransaction().begin();
		manager.persist(player_profile);
		manager.getTransaction().commit();
		manager.close();
	}
	// View player name ok
	public static Player showPlayerName(String login_name){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT p FROM Player p WHERE p.login_name='" + login_name + "'");
		Player newPlayer = (Player) query.getSingleResult();
		em.close();
		return newPlayer;
		
	}

	// deposit ok
	public static void deposit(String token, double amount){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT p FROM Player p WHERE p.token='" + token + "'");
		Player newPlayer = (Player) query.getSingleResult();
		em.getTransaction().begin();
		double funds = newPlayer.getBalance() + amount;
		newPlayer.setBalance(funds);
		em.persist(newPlayer);
		em.getTransaction().commit();
		em.close();
	}
	// withdraw ok
	public static void withdraw(String token, double amount){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT p FROM Player p WHERE p.token='" + token + "'");
		Player newPlayer = (Player) query.getSingleResult();
		em.getTransaction().begin();
		double funds = newPlayer.getBalance() - amount;
		newPlayer.setBalance(funds);
		em.persist(newPlayer);
		em.getTransaction().commit();
		em.close();
	}
	// add token when logging is ok
	public static void addToken(String username, String token) {
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery("SELECT p FROM Player p WHERE p.login_name='" + username + "'");
		Player result = (Player) query.getSingleResult();
		result.setToken(token);
		manager.getTransaction().begin();
		manager.persist(result);
		manager.getTransaction().commit();
		manager.close();

	}
	// show playerBalance ok
	public static double showBalance(String token){
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery("SELECT p FROM Player p WHERE p.token='" + token + "'");
		Player player = (Player) query.getSingleResult();
		double temp = player.getBalance();
		manager.close();
		return temp;
		
	}
	
	// check PlayerProfiles ok
	public static PlayerProfile showPlayersProfiles(String token){
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery("SELECT pp FROM PlayerProfile pp JOIN pp.player p WHERE p.token='" + token + "'");
		PlayerProfile result = (PlayerProfile) query.getSingleResult();
		manager.close();
		return result;
	}
}
