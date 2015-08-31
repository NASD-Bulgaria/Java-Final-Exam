package controller;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;


import yPlayer.Player;

public class Querries {

	public static void addPlayer(String loginName,String password){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("yPlayer");
		EntityManager eMan = emf.createEntityManager();
		Player play = new Player();
		play.setLoginName(loginName);
		play.setLoginPassword(password);
		play.setBalance(0);
		eMan.getTransaction().begin();
		eMan.persist(play);
		eMan.getTransaction().commit();
		eMan.close();
		emf.close();
	}
	
	public static Player findplayer(String userName){
		Player play = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("yPlayer");
		EntityManager eMan = emf.createEntityManager();
		try{
		Query querry = eMan.createNamedQuery("findByName");
		play =  (Player) querry.setParameter("name", userName).getSingleResult();
	}
	catch(Exception e){
		System.err.println("Error find by name");
	}
		
		eMan.close();
		emf.close();
	
		return play;
		
	}

	public static Player playDeposit(int id ,double newSum){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("yPlayer");
		EntityManager eMan = emf.createEntityManager();
		Player play = eMan.find(Player.class, id);
		if (newSum<0) {
			newSum = 0;
		}
		eMan.getTransaction().begin();
		play.setBalance(play.getBalance()+newSum);
		eMan.persist(play);
		eMan.getTransaction().commit();
		eMan.close();
		emf.close();
		
		return null;
	}
	
	public static Player playWithdraw(int id,double newSum){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("yPlayer");
		EntityManager eMan = emf.createEntityManager();
		Player play = eMan.find(Player.class, id);
		if (newSum>play.getBalance()) {
			newSum = 0;
		}
		eMan.getTransaction().begin();
		play.setBalance(play.getBalance()-newSum);
		eMan.persist(play);
		eMan.getTransaction().commit();
		eMan.close();
		emf.close();
		
		return null;
	}
	
	public static String hashFunction(String hash){
		
		HashFunction hf = Hashing.sha1();
		HashCode hc = hf.newHasher().putString(hash, Charsets.UTF_8).hash();
		Random generator = new Random();
		int numb = generator.nextInt(10000000);
		String conNumb = String.valueOf(numb);
		String hashedResult = hc.toString().concat(conNumb);
		return hashedResult;
		
	}
	
	
	
}
