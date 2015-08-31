package queries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Player;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class Queries 
{
	public static void addPlayer(String username, String password, double balance)
	{
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalExam");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String loginPassword = hashingPassword(password);

		Player player = new Player();
		
		player.setLoginName(username);
		player.setLoginPassword(loginPassword);
		player.setBalance(balance);
		
		entitymanager.persist(player);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}
	
	public static boolean authentification(String username, String password)
	{
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalExam");

		EntityManager entitymanager = emfactory.createEntityManager();
		
		Player player = null;
		
		try
		{
			Query query = entitymanager.createNamedQuery("Player.findByName").setParameter("name", username);
			player = (Player) query.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(player == null)
		{
			entitymanager.close();
			emfactory.close();
			
			return false;
		}
		
		String loginPassword = hashingPassword(password);
		
		if(player.getLoginPassword().equals(loginPassword))
		{
			entitymanager.close();
			emfactory.close();
			
			return true;
		}
		else
		{
			entitymanager.close();
			emfactory.close();
			
			return false;
		}
		
	}
	
	public static boolean doesPlayerExist(String username)
	{
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalExam");

		EntityManager entitymanager = emfactory.createEntityManager();
		
		Player player = null;
		
		try
		{
			Query query = entitymanager.createNamedQuery("Player.findByName").setParameter("name", username);
			player = (Player) query.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(player == null)
		{
			entitymanager.close();
			emfactory.close();
			
			return false;
		}
		else
		{
			entitymanager.close();
			emfactory.close();
			
			return true;
		}
		
	}
	
	public static Player getPlayerByName(String username)
	{
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalExam");

		EntityManager entitymanager = emfactory.createEntityManager();
		
		Player player = null;
		
		try
		{
			Query query = entitymanager.createNamedQuery("Player.findByName").setParameter("name", username);
			player = (Player) query.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(player != null)
			return player;
		else
			return null;
	}
	
	public static void updateDeposit(int id, double deposit)
	{
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalExam");

		EntityManager entitymanager = emfactory.createEntityManager();
		
		entitymanager.getTransaction().begin();
		
		Player player = entitymanager.find(Player.class, id);
		
		double updatedDeposit = player.getBalance() + deposit;
		
		player.setBalance(updatedDeposit);
		
		entitymanager.persist(player);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}
	
	public static void updateWithdraw(int id, double withdraw)
	{
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalExam");

		EntityManager entitymanager = emfactory.createEntityManager();
		
		entitymanager.getTransaction().begin();
		
		Player player = entitymanager.find(Player.class, id);
		
		double balance = player.getBalance() - withdraw;
		
		player.setBalance(balance);
		
		entitymanager.persist(player);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}
	
	private static String hashingPassword(String password) {
		HashFunction hf = Hashing.sha1();
		HashCode hc = hf.hashString(password, Charsets.UTF_8);

		return hc.toString();
	}
}
