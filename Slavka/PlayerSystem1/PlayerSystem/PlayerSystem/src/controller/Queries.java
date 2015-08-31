package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Player;
import model.PlayerProfile;

public class Queries {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlayerSystem");

	public static Player addNewPlayer(String loginName, String loginPassword, String salt, String token) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Player player = new Player(loginName, loginPassword, 0, salt, token);
		manager.persist(player);
		manager.getTransaction().commit();
		manager.close();
		return player;
	}

	public static void addDeposit(String name, double balance) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("Select p from Player p where p.loginName='" + name + "'");
		Player player = (Player) query.getSingleResult();
		double temp = player.getBalance();

		temp += balance;
		player.setBalance(temp);
		em.persist(player);
		em.getTransaction().commit();
		em.close();
	}

	public static void withdraw(String name, double balance) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("Select p from Player p where p.loginName='" + name + "'");
		Player player = (Player) query.getSingleResult();
		double temp = player.getBalance();
		if (temp > balance) {
			temp -= balance;
			player.setBalance(temp);
			em.persist(player);
			em.getTransaction().commit();
		}
		em.close();
	}

	public static List<Player> showAllPlayers() {
		List<Player> players = new ArrayList<>();
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select p from Player p");
		players = query.getResultList();
		em.close();
		return players;
	}

	public static PlayerProfile showPlayerByProfileByName(String playerFirstName) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select p from PlayerProfile p where p.playerFirstName='" + playerFirstName + "'");
		PlayerProfile result = (PlayerProfile) query.getSingleResult();
		em.close();
		return result;

	}

	public static List<PlayerProfile> showAllPlayerProfile() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select p from PlayerProfile p");
		List<PlayerProfile> result = query.getResultList();
		em.close();
		return result;

	}

	public static Player showPlayerByLogin_name(String loginName) {
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery("select p from Player p where p.loginName='" + loginName + "'");
		Player result = (Player) query.getSingleResult();
		return result;

	}

	public static void insertPlayer_Profile(String player_name, List<Player> player) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		PlayerProfile result = new PlayerProfile(player_name, player);
		manager.persist(result);
		manager.getTransaction().commit();
		manager.close();
	}

	public static void updatePlayerProfile(String playerFK, Player player) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Query query = manager.createQuery("Select p from PlayerProfile p where p.playerFK='" + playerFK + "'");
		PlayerProfile result = (PlayerProfile) query.getSingleResult();
		List<Player> temp = result.getPlayerFK();
		temp.add(player);
		result.setPlayerFK(temp);
		manager.persist(result);
		manager.getTransaction().commit();
		manager.close();
	}
	
	
}
