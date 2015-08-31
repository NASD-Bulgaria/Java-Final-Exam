package queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entities.Player;
import entities.PlayerProfile;

public class JPAQueries {
	public static final EntityManagerFactory emfactory = Persistence
			.createEntityManagerFactory("PlayersProject");

	public void addCompany(Player player, PlayerProfile playerProfil) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(player);
		playerProfil.setFkPlayer(player);
		em.persist(playerProfil);
		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	public boolean isLoginNameExist(String loginName) {
		boolean result = false;
		EntityManager em = emfactory.createEntityManager();
		Query query = em
				.createQuery("SELECT p FROM Player p WHERE p.loginName = '"
						+ loginName + "'");
		List<Player> temp = query.getResultList();
		if (temp.size() != 0) {
			result = true;
		}
		em.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public Player isLoginSuccess(String loginName, String password) {
		Player company = null;
		EntityManager em = emfactory.createEntityManager();
		Query query = em
				.createQuery("SELECT p FROM Player p WHERE p.loginName ='"
						+ loginName + "'");
		List<Player> temp = query.getResultList();
		if (temp.size() != 0 && temp.get(0).getLoginPassword().equals(password)) {
			company = temp.get(0);
		}
		em.close();
		return company;
	}

	public void setToken(int playerId, String token) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Player company = em.find(Player.class, playerId);
		company.setToken(token);
		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	public boolean deposit(double deposit, String token) {
		boolean isSucces = false;		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT p FROM Player p WHERE p.token ='"
				+ token + "'");
		List<Player> temp = query.getResultList();
		if (temp.size() != 0) {
			Player company = temp.get(0);
			double balance = company.getBalance();
			double result = balance + deposit;
			company.setBalance(result);
			isSucces = true;
		}
		em.getTransaction().commit();
		em.close();
		return isSucces;
	}

	@SuppressWarnings("unchecked")
	public boolean withdraw(double withdraw, String token) {
		boolean isSucces = false;
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT p FROM Player p WHERE p.token ='"
				+ token + "'");
		List<Player> temp = query.getResultList();
		if (temp.size() != 0) {
			Player company = temp.get(0);
			double balance = company.getBalance();
			double result = balance - withdraw;
			if (result > 0) {
				company.setBalance(result);
				isSucces = true;
			}
		}
		em.getTransaction().commit();
		em.close();
		return isSucces;
	}

	@SuppressWarnings("unchecked")
	public boolean isTokenExist(String token) {
		boolean isSucces = false;
		EntityManager em = emfactory.createEntityManager();
		Query query = em.createQuery("SELECT p FROM Player p WHERE p.token ='"
				+ token + "'");
		List<Player> temp = query.getResultList();
		if (temp.size() != 0) {
			isSucces = true;
		}
		em.close();
		return isSucces;
	}
	
	@SuppressWarnings("unchecked")
	public boolean deleteToken(String token) {
		boolean isSucces = false;
		EntityManager em = emfactory.createEntityManager();
		
		Query query = em.createQuery("SELECT p FROM Player p WHERE p.token ='"
				+ token + "'");
		List<Player> temp = query.getResultList();
		if (temp.size() != 0) {
			em.getTransaction().begin();
			Player p = temp.get(0);
			p.setToken(" ");
			isSucces = true;
			em.getTransaction().commit();
		}
		
		em.close();
		return isSucces;
	}
}
