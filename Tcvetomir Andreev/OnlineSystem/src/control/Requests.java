package control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Player;
import model.Player_profile;

public class Requests {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("OnlineSystem");

	public static Player showPlayerByLoginName(String login_name) {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("Select p from Player p where p.login_name='" + login_name + "'");
		Player result = (Player) query.getSingleResult();
		manager.close();
		return result;

	}

	public static Player regPlayer(String login_name, String login_password, String salt) {
		EntityManager manager = factory.createEntityManager();
		Player player = new Player(login_name, login_password, salt);
		manager.getTransaction().begin();
		manager.persist(player);
		manager.getTransaction().commit();
		manager.close();
		return player;
	}

	public static void regPlayer_Profile(String first_name, String last_name, Player player) {
		EntityManager manager = factory.createEntityManager();
		Player_profile player_profile = new Player_profile(first_name, last_name, player);
		manager.getTransaction().begin();
		manager.persist(player_profile);
		manager.getTransaction().commit();
		manager.close();
	}

	public static void depositBalance(String token, double balance) {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("select p from Player p where p.token='" + token + "'");
		Player player = (Player) query.getSingleResult();
		double temp = player.getBalance();
		temp += balance;
		player.setBalance(temp);
		manager.getTransaction().begin();
		manager.persist(player);
		manager.getTransaction().commit();
		manager.close();

	}

	public static void withdrawBalance(String token, double balance) {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("select p from Player p where p.token='" + token + "'");
		Player player = (Player) query.getSingleResult();
		double temp = player.getBalance();
		temp -= balance;
		player.setBalance(temp);
		manager.getTransaction().begin();
		manager.persist(player);
		manager.getTransaction().commit();
		manager.close();

	}

	public static void addToken(String username, String token) {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("Select p from Player p where p.login_name='" + username + "'");
		Player result = (Player) query.getSingleResult();
		result.setToken(token);
		manager.getTransaction().begin();
		manager.persist(result);
		manager.getTransaction().commit();
		manager.close();

	}
	public static double showBalance(String token){
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("select p from Player p where p.token='" + token + "'");
		Player player = (Player) query.getSingleResult();
		double temp = player.getBalance();
		manager.close();
		return temp;
		
	}
	public static  Player_profile showPlayerProfile(String token){
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("select f from Player_profile f join f.player p where p.token='" + token + "'");
		Player_profile result = (Player_profile) query.getSingleResult();
		manager.close();
		return result;
	}
}
