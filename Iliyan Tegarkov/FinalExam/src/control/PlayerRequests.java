package control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import model.Player;
import model.Player_profile;

public class PlayerRequests {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalExam");

	// creating a new player by connecting the tables
	public static void createNewPlayer(Player player, Player_profile playerProfile) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(player);
		playerProfile.setFk_player(player);
		em.persist(playerProfile);
		em.getTransaction().commit();
		em.close();
	}

	// cheking if a username allready exists
	public static boolean existingLoginName(String loginName) {

		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("select p from Player p where p.login_name = '" + loginName + "'");
			@SuppressWarnings("unused")
			Player player = (Player) query.getSingleResult();

		} catch (NoResultException e) {
			return true;
		}
		return false;
	}

	// hashing the password
	public static String passwordHashin(String loginName, String password) {
		HashFunction hf = Hashing.sha256();
		HashCode hc = hf.newHasher().putString(password, Charsets.UTF_8).putString(loginName, Charsets.UTF_8).hash();
		return hc.toString();
	}

	// validating the input data for login
	public static boolean loginValidate(String username, String password) {

		EntityManager em = emf.createEntityManager();
		HashFunction hf = Hashing.sha256();
		HashCode hc = hf.newHasher().putString(password, Charsets.UTF_8).putString(username, Charsets.UTF_8).hash();
		try {
			Query query = em.createQuery("select p from Player p where p.login_name ='" + username + "'");
			Player player = (Player) query.getSingleResult();
			if (player.getLogin_name().equalsIgnoreCase(username)
					&& player.getLogin_password().equalsIgnoreCase(hc.toString())) {
				return true;

			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	// adding token to a user
	public static void addToken(String name, String token) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select p from Player p where p.login_name='" + name + "'");
		Player player = (Player) query.getSingleResult();
		player.setToken(token);
		em.persist(player);
		em.getTransaction().commit();
		em.close();
	}

	// methot for deposit
	public static boolean deposit(String token, double amount) {
		if (amount > 0) {
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			Query query = em.createQuery("Select p From Player p where p.token = '" + token + "'");
			Player player = (Player) query.getSingleResult();

			player.setBalance(player.getBalance() + amount);

			em.persist(player);
			em.getTransaction().commit();
			em.close();
			return true;
		}
		return false;
	}

	// methot for withdraw
	public static int withdraw(String token, double amount) {
		if (amount <= 0) {
			return -1;
		}
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("Select p From Player p where p.token = '" + token + "'");
		Player player = (Player) query.getSingleResult();
		if (player.getBalance() > amount) {
			player.setBalance(player.getBalance() - amount);
			em.persist(player);
			em.getTransaction().commit();
			em.close();
			return 1;
		}
		em.getTransaction().commit();
		em.close();
		return 0;
	}

	public static void removeToken(String token) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select p from Player p where p.token = '" + token + "'");

		Player player = (Player) query.getSingleResult();
		player.setToken(null);
		em.persist(player);
		em.getTransaction().commit();
		em.close();

	}

}
