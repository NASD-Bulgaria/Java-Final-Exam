package request;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import entity.Player;
import entity.PlayerProfile;

public class PlayerRequest {

	private static final EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("Exam29.08.2015");

	@SuppressWarnings("unchecked")
	public static List<Player> showAllPlayers() {
		List<Player> playerList = null;

		EntityManager manager = entityManagerFactory.createEntityManager();

		Query query = manager.createQuery("Select p From Player p");
		playerList = query.getResultList();

		manager.close();
		return playerList;
	}

	// register new player
	public static void registerNewPlayer(Player player,
			PlayerProfile playerProfile) {

		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(player);
		playerProfile.setFkPlayer(player);
		entityManager.persist(playerProfile);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	// check for existing loginname
	public static boolean existingLoginName(String loginName) {
		try {
			EntityManager entityManager = entityManagerFactory
					.createEntityManager();

			Query query = entityManager
					.createQuery("Select p from Player p where p.loginName = '"
							+ loginName + "'");

			@SuppressWarnings("unused")
			Player player = (Player) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return false;
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			return false;

		} catch (IllegalStateException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	// hashing
	public static String hashing(String variable, String loginName) {

		HashFunction hf = Hashing.sha256();
		HashCode hc = hf.newHasher().putString(variable, Charsets.UTF_8)
				.putString(loginName, Charsets.UTF_8).hash();

		return hc.toString();
	}

	// set token to player
	public static void setTokenToPlayer(String loginName, String token) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		entityManager.getTransaction().begin();
		Query query = entityManager
				.createQuery("Select p from Player p where p.loginName='"
						+ loginName + "'");
		Player player = (Player) query.getSingleResult();

		player.setToken(token);
		entityManager.persist(player);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	// public static void deposit(String token, double amount) {
	// EntityManager manager = entityManagerFactory.createEntityManager();
	//
	// manager.getTransaction().begin();
	// Query query = manager
	// .createQuery("Select p from Player p where p.token= '"
	// + token + "'");
	//
	// Player player = (Player) query.getSingleResult();
	//
	// player.setBalance(player.getBalance() + amount);
	// manager.persist(player);
	// manager.getTransaction().commit();
	// manager.close();
	// }

	// public static void withdraw(String token, double amount) {
	// EntityManager manager = entityManagerFactory.createEntityManager();
	//
	// manager.getTransaction().begin();
	//
	// Query query = manager
	// .createQuery("Select p from Player p where p.token= '"
	// + token + "'");
	//
	// Player player = (Player) query.getSingleResult();
	//
	// if (player.getBalance() >= amount) {
	// player.setBalance(player.getBalance() - amount);
	// manager.persist(player);
	// manager.getTransaction().commit();
	// manager.close();
	//
	// }
	// }

	public static void setTokenToNull(String loginName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("Select p from Player p where p.loginName='" + loginName + "'");
		Player player = (Player) query.getSingleResult();

		player.setToken(null);
		entityManager.persist(player);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
