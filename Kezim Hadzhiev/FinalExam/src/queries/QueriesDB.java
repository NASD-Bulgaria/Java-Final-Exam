package queries;

import java.util.Calendar;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Player;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class QueriesDB {

	public static void AddPlayerDB(String username, String password,double balance) {
		
		
		String sault = getSault();		
		String hashPassword = hashingText(password);		
		String playerPassword = hashingText(sault + hashPassword);
		
		Player player = new Player();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalExamKezim");
		EntityManager entitymanager = emf.createEntityManager();
		entitymanager.getTransaction().begin();

		player.setLoginName(username);
		player.setLoginPassword(playerPassword);
		player.setBalance(balance);
		player.setSault(sault);

		entitymanager.persist(player);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emf.close();
	}
	
	
	public static boolean isPlayerExist(String name){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalExamKezim");
		EntityManager entitymanager = emf.createEntityManager();
		Player checkPlayerExist = null;
		try{
		checkPlayerExist = (Player) entitymanager.createNamedQuery("Player.findByName").setParameter("name", name).getSingleResult();
		}
		catch(NoResultException e){
			System.out.println("No result!");
		}
		if(checkPlayerExist==null){
			entitymanager.close();
			emf.close();
			return false;
		}
		else {
			entitymanager.close();
			emf.close();
			return true;
		}
		
		
	}
	
	private static String getSault(){
		Calendar cal = Calendar.getInstance();
		Random random = new Random();
		long num = cal.getTimeInMillis();
		int number = random.nextInt(30000000);

		StringBuilder saultBuild = new StringBuilder();
		saultBuild.append(num);
		saultBuild.append(number);
		String sault = hashingText(saultBuild.toString());
		return sault;
	}
	
	
	public static boolean isPlayerLogCorrect(String name, String password) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalExamKezim");
		EntityManager entitymanager = emf.createEntityManager();
		Player player = null;

		try {
			
			Query query = entitymanager.createNamedQuery("Player.findByName").setParameter("name", name);
			player = (Player) query.getSingleResult();
		
		if (player == null) {
			entitymanager.close();
			emf.close();
			return false;
		} 
		else {
			String loginPassword = hashingText(player.getSault()+ hashingText(password));
			if (player.getLoginPassword().equals(loginPassword)) {
				entitymanager.close();
				emf.close();
				return true;
			} 
			else {
				entitymanager.close();
				emf.close();
				return false;
			}
		
		}
		}catch (Exception e) {
			System.out.println(" Cannot find PLAYER!");
			return false;
		}
		}

	

	private static String hashingText(String text) {
		HashFunction hf = Hashing.sha256();
		HashCode hc = hf.hashString(text, Charsets.UTF_8);
		return hc.toString();
	}



	public static Player getPlayerByName(String username) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("FinalExamKezim");
		EntityManager entitymanager = emf.createEntityManager();

		Player player = null;

		try {
			Query query = entitymanager.createNamedQuery("Player.findByName").setParameter("name", username);
			player = (Player) query.getSingleResult();
		} catch (Exception e) {
			entitymanager.close();
			emf.close();
			return null;
		}

		entitymanager.close();
		emf.close();
		return player;

	}

	public static void depositeAmmount(int id, double deposit) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalExamKezim");
		EntityManager entitymanager = emf.createEntityManager();

		entitymanager.getTransaction().begin();

		Player player = entitymanager.find(Player.class, id);

		player.setBalance(player.getBalance() + deposit);

		entitymanager.flush();
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emf.close();
	}

	public static void withdrawAmount(int id, double withdraw) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("FinalExamKezim");
		EntityManager entitymanager = emf.createEntityManager();

		entitymanager.getTransaction().begin();

		Player player = entitymanager.find(Player.class, id);

		double balance = player.getBalance() - withdraw;

		player.setBalance(balance);

		entitymanager.flush();
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emf.close();
	}
	

}
