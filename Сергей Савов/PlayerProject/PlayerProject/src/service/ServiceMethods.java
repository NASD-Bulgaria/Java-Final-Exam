package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import model.Player;

public class ServiceMethods {

	ArrayList<Player> playerList = null;
	/*
	 * create login profile on player
	 */

	public static void createPlayer(String loginName, String loginPass) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlayerProject");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Player player = new Player();
		player.setLoginName(loginName);
		player.setLoginPassword(loginPass);
		if (!checkForExisting(findAllUsers(), loginName)) {
			em.persist(player);
			em.getTransaction().commit();
		}
		em.close();
		emf.close();
	}

	/*
	 * find all logins
	 */

	public static List<Player> findAllUsers() {
		List<Player> myList = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlayerProject");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		myList = em.createNamedQuery("Player.findAll", Player.class).getResultList();

		em.getTransaction().commit();

		em.close();
		emf.close();

		return myList;
	}

	/*
	 * and check for existing login name
	 */
	public static boolean checkForExisting(List<Player> myList, String login) {

		boolean flag = false;
		for (Player company : myList) {
			if (company.getLoginName().equals(login)) {
				flag = true;

			}

		}
		return flag;
	}

	public static boolean checkUserAndPass(List<Player> myList, String login, String pass) {

		boolean flag = false;
		for (Player company : myList) {
			if (company.getLoginName().equals(login) && company.getLoginPassword().equals(pass)) {
				flag = true;

			}

		}
		return flag;
	}

	public static void deletePlayer(int login_id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlayerProject");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Player player = em.find(Player.class, login_id);
		em.remove(player);
		em.getTransaction().commit();

		em.close();
		emf.close();

	}
	
	public static void addBalance(String loginName, double balance) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlayerProject");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Player player = em.find(Player.class, loginName);
		player.setBalance(balance);
		
		em.persist(player);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		
	}
	
	public static void showBalance(String loginName) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlayerProject");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Player player = em.find(Player.class, loginName);
		player.getBalance();

		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
