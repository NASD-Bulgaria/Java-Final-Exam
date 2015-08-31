package logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Player;
import model.PlayerProfile;

public class GameQueries {
	public static final EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("OnlineGameSystemProject");

	public void addPlayer(Player player, PlayerProfile playerProfil) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(player);
		playerProfil.setFkPlayer(player);
		manager.persist(playerProfil);
		manager.getTransaction().commit();
		manager.close();
	}


	public static boolean isUserExist(String loginName) {
		boolean flag = false;
		EntityManager manager = factory.createEntityManager();
		Query query = manager
				.createQuery("SELECT p FROM Player p WHERE p.loginName = '"
						+ loginName + "'");
		@SuppressWarnings("unchecked")
		List<Player> temp = query.getResultList();
		if (temp.size() != 0) {
			flag = true;
		}
		manager.close();
		return flag;
	}

	
	public Player isLoginSuccess(String loginName, String password) {
		Player player = null;
		EntityManager manager = factory.createEntityManager();
		Query query = manager
				.createQuery("SELECT p FROM Player p WHERE p.loginName ='"
						+ loginName + "'");
		@SuppressWarnings("unchecked")
		List<Player> temp = query.getResultList();
		if (temp.size() != 0 && temp.get(0).getLoginPassword().equals(password)) {
			player = temp.get(0);
		}
		manager.close();
		return player;
	}

	
	public boolean depositBalance(double deposit, String token) {
		boolean isTrue = false;		
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query query = manager.createQuery("SELECT p FROM Player p WHERE p.token ='"
				+ token + "'");
		@SuppressWarnings("unchecked")
		List<Player> temp = query.getResultList();
		if (temp.size() != 0) {
			Player player = temp.get(0);
			double balance = player.getBalance();
			double result = balance + deposit;
			player.setBalance(result);
			isTrue = true;
		}
		manager.getTransaction().commit();
		manager.close();
		return isTrue;
	}
	
	public boolean withdrawBalance(double withdraw, String token) {
		boolean isTrue = false;
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query query = manager.createQuery("SELECT p FROM Player p WHERE p.token ='"
				+ token + "'");
		@SuppressWarnings("unchecked")
		List<Player> temp = query.getResultList();
		if (temp.size() != 0) {
			Player player = temp.get(0);
			double balance = player.getBalance();
			double result = balance - withdraw;
			if (result > 0) {
				player.setBalance(result);
				isTrue = true;
			}
		}
		manager.getTransaction().commit();
		manager.close();
		return isTrue;
	}

	public void addToken(int playerId, String token) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Player player = manager.find(Player.class, playerId);
		player.setToken(token);
		manager.getTransaction().commit();
		manager.close();
	}
	
	
	public boolean isTokenExist(String token) {
		boolean flag = false;
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("SELECT p FROM Player p WHERE p.token ='"
				+ token + "'");
		@SuppressWarnings("unchecked")
		List<Player> temp = query.getResultList();
		if (temp.size() != 0) {
			flag = true;
		}
		manager.close();
		return flag;
	}
	
	
	public boolean deleteToken(String token) {
		boolean flag = false;
		EntityManager manager = factory.createEntityManager();
		
		Query query = manager.createQuery("SELECT p FROM Player p WHERE p.token ='"
				+ token + "'");
		@SuppressWarnings("unchecked")
		List<Player> temp = query.getResultList();
		if (temp.size() != 0) {
			manager.getTransaction().begin();
			Player p = temp.get(0);
			p.setToken(" ");
			flag = true;
			manager.getTransaction().commit();
		}
		
		manager.close();
		return flag;
	}

	
	
}
