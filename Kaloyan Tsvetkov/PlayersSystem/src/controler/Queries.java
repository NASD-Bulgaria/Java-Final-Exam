package controler;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import entity.model.Player;
import entity.model.PlayerProfile;

public class Queries {

	private final static EntityManagerFactory entityManager = Persistence
			.createEntityManagerFactory("PlayersSystem");
	private final static int OCCUPIED_USERNAME = -1;
	private final static int ROLLBACK_EXEPTION = -2;
	private final static int WRONG_USERNAME_PASS = -5;

	// get All Players
	public static List<Player> showAllPlayers() {
		List<Player> playerList = null;
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Query query = manager.createNamedQuery("Player.findAll");
		playerList = query.getResultList();

		manager.close();
		return playerList;
	}

	// get All PlayersProfiles
	public static List<PlayerProfile> showAllPLayersProfiles() {
		List<PlayerProfile> playersList = null;
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Query query = manager.createNamedQuery("PlayerProfile.findAll");
		playersList = query.getResultList();

		manager.close();
		return playersList;
	}

	// add new User
	public static int addUser(String firstName, String lastName,
			String userName, String pass) {
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		boolean flag = true;
		String hcPass = heshingPass(pass);

		List<Player> myPLayers = Queries.showAllPlayers();
		for (int i = 0; i < myPLayers.size(); i++) {
			if (myPLayers.get(i).getLoginName().equalsIgnoreCase(userName)) {
				flag = false;
				break;
			}
		}

		if (flag) {
			try {
				Player newUser = new Player();
				newUser.setLoginName(userName);
				newUser.setLoginPassword(hcPass);
				newUser.setBalance(0.00);
				newUser.setToken("");

				manager.persist(newUser);
				

				PlayerProfile newProfile = new PlayerProfile();
				newProfile.setFirstName(firstName);
				newProfile.setLastName(lastName);
				newProfile.setPlayer(newUser);

				manager.persist(newProfile);

				manager.getTransaction().commit();

				manager.close();
				return newUser.getId();
			} catch (RollbackException e) {
				return ROLLBACK_EXEPTION;
			}
		} else {
			manager.close();
			return OCCUPIED_USERNAME;
		}
	}

	// log in User
	public static int logInUser(String userName, String pass) {
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		boolean flag = false;
		String hcPass = heshingPass(pass);

		List<Player> myPlayers = Queries.showAllPlayers();

		manager.close();

		Player tempPlayer = null;
		for (int i = 0; i < myPlayers.size(); i++) {
			if (myPlayers.get(i).getLoginName().equalsIgnoreCase(userName)) {
				if (myPlayers.get(i).getLoginPassword().equals(hcPass)) {
					flag = true;
					tempPlayer = myPlayers.get(i);
					break;
				}
			}
		}
		if (flag) {
			return tempPlayer.getId();
		} else {
			return WRONG_USERNAME_PASS;
		}
	}

	// set Token to DB
	public static void setToken(int id, String token) {
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Player tempPlayer = manager.find(Player.class, id);

		tempPlayer.setToken(token);
		manager.persist(tempPlayer);

		manager.getTransaction().commit();

		manager.close();
	}

	// Create a unique token using SHA-1
	public static String getUniqueToken() {
		String token = "";
		Calendar c = Calendar.getInstance();
		Random rand = new Random();
		long z = c.getTimeInMillis();
		int y = rand.nextInt(1000000);
		token = Long.toString(z) + Integer.toString(y);
		HashFunction hf = Hashing.sha1();
		HashCode hcToken = hf.newHasher().putString(token, Charsets.UTF_8)
				.hash();
		return hcToken.toString();
	}

	// Get Token from DB by id
	public static String getTokenByID(int id) {
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Player tempPlayer = manager.find(Player.class, id);

		manager.getTransaction().commit();
		manager.close();

		return tempPlayer.getToken();
	}

	// Deposit sum to balance
	public static boolean depositSum(int id, double sum) {
		double result = 0.00;

		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Player tempPlayer = manager.find(Player.class, id);
		if (sum > 0) {
			result = tempPlayer.getBalance() + sum;
			tempPlayer.setBalance(result);

			manager.persist(tempPlayer);
			manager.getTransaction().commit();

			manager.close();
			return true;
		} else {
			manager.getTransaction().commit();
			manager.close();
			return false;
		}
	}

	// Withdraw sum from balance
	public static boolean withdrawSum(int id, double sum) {
		double result = 0.00;

		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Player tempPlayer = manager.find(Player.class, id);
		if (sum > 0 && tempPlayer.getBalance() >= sum) {
			result = tempPlayer.getBalance() - sum;
			tempPlayer.setBalance(result);

			manager.persist(tempPlayer);
			manager.getTransaction().commit();

			manager.close();
			return true;
		} else {
			manager.getTransaction().commit();
			manager.close();
			return false;
		}
	}

	//Get Player Balance by id
	public static double getBalanceByID(int id) {
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();

		Player tempComp = manager.find(Player.class, id);
		manager.getTransaction().commit();

		manager.close();
		return tempComp.getBalance();
	}

	//Hashing password
	private static String heshingPass(String pass) {
		HashFunction hf = Hashing.sha256();
		HashCode hcPass = hf.newHasher().putString(pass, Charsets.UTF_8).hash();
		return hcPass.toString();
	}
}
